package com.myapp.dao;

import com.myapp.system.config.ConexionDB;
import com.myapp.system.model.Cliente;
import com.myapp.system.model.Producto;
import com.myapp.system.model.ReporteVenta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class VentaDAO {

    // Lista de clientes para el ComboBox
    public List<Cliente> listarClientes() throws SQLException {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT id_cliente, nombre FROM Clientes ORDER BY nombre";
        try (Connection con = ConexionDB.getInstancia().getConexion();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Cliente(rs.getInt("id_cliente"), rs.getString("nombre")));
            }
        }
        return lista;
    }

    // Lista de productos para el ComboBox
    public List<Producto> listarProductos() throws SQLException {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT id_producto, nombre, precio, stock FROM Productos ORDER BY nombre";
        try (Connection con = ConexionDB.getInstancia().getConexion();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Producto(rs.getInt("id_producto"), rs.getString("nombre"),
                        rs.getDouble("precio"), rs.getInt("stock")));
            }
        }
        return lista;
    }

    // Guarda la venta y resta el stock en UNA sola transacción
    public void guardarVenta(int idCliente, int idProducto, int cantidad, double total) throws SQLException {
        String sqlStock = "UPDATE Productos SET stock = stock - ? WHERE id_producto = ? AND stock >= ?";
        String sqlVenta = "INSERT INTO Ventas (cantidad, total, id_cliente, id_producto) VALUES (?, ?, ?, ?)";

        try (Connection con = ConexionDB.getInstancia().getConexion()) {
            try {
                con.setAutoCommit(false);

                PreparedStatement psStock = con.prepareStatement(sqlStock);
                psStock.setInt(1, cantidad);
                psStock.setInt(2, idProducto);
                psStock.setInt(3, cantidad);
                if (psStock.executeUpdate() == 0) {
                    throw new SQLException("Stock insuficiente");
                }

                PreparedStatement psVenta = con.prepareStatement(sqlVenta);
                psVenta.setInt(1, cantidad);
                psVenta.setDouble(2, total);
                psVenta.setInt(3, idCliente);
                psVenta.setInt(4, idProducto);
                psVenta.executeUpdate();

                con.commit();
            } catch (SQLException e) {
                con.rollback(); // si algo falla, no se guarda nada
                throw e;
            }
        }
    }

    // Reporte con INNER JOIN: muestra nombres en lugar de IDs
    public List<ReporteVenta> listarReporte() throws SQLException {
        List<ReporteVenta> lista = new ArrayList<>();
        String sql = "SELECT v.id_venta, v.fecha, c.nombre AS cliente, p.nombre AS producto, v.cantidad, v.total "
                + "FROM Ventas v "
                + "INNER JOIN Clientes c ON v.id_cliente = c.id_cliente "
                + "INNER JOIN Productos p ON v.id_producto = p.id_producto "
                + "ORDER BY v.id_venta DESC";
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try (Connection con = ConexionDB.getInstancia().getConexion();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String fecha = formato.format(rs.getTimestamp("fecha"));
                String total = String.format(Locale.US, "Q%.2f", rs.getDouble("total"));
                lista.add(new ReporteVenta(rs.getInt("id_venta"), fecha, rs.getString("cliente"),
                        rs.getString("producto"), rs.getInt("cantidad"), total));
            }
        }
        return lista;
    }
}