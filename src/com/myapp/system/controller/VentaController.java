package com.myapp.system.controller;

import com.myapp.dao.VentaDAO;
import com.myapp.system.model.Cliente;
import com.myapp.system.model.Producto;
import com.myapp.system.model.ReporteVenta;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

public class VentaController {

    @FXML private ComboBox<Cliente> cmbCliente;
    @FXML private ComboBox<Producto> cmbProducto;
    @FXML private TextField txtCantidad;
    @FXML private TableView<ReporteVenta> tblVentas;
    @FXML private TableColumn<ReporteVenta, Integer> colVenta;
    @FXML private TableColumn<ReporteVenta, String> colFecha;
    @FXML private TableColumn<ReporteVenta, String> colCliente;
    @FXML private TableColumn<ReporteVenta, String> colProducto;
    @FXML private TableColumn<ReporteVenta, Integer> colCantidad;
    @FXML private TableColumn<ReporteVenta, String> colTotal;

    private VentaDAO dao = new VentaDAO();

    @FXML
    public void initialize() {
        // El ComboBox guarda el objeto completo (con su ID).
        // El converter solo define qué texto se muestra.
        cmbCliente.setConverter(new StringConverter<Cliente>() {
            @Override
            public String toString(Cliente c) {
                return c == null ? "" : c.getNombre();
            }

            @Override
            public Cliente fromString(String texto) {
                return null;
            }
        });

        cmbProducto.setConverter(new StringConverter<Producto>() {
            @Override
            public String toString(Producto p) {
                return p == null ? "" : p.getNombre() + " - Q" + p.getPrecio() + " (stock: " + p.getStock() + ")";
            }

            @Override
            public Producto fromString(String texto) {
                return null;
            }
        });

        // Columnas de la tabla: el texto debe coincidir con los getters de ReporteVenta
        colVenta.setCellValueFactory(new PropertyValueFactory<>("idVenta"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        colProducto.setCellValueFactory(new PropertyValueFactory<>("producto"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        cargarDatos();
    }

    private void cargarDatos() {
        try {
            cmbCliente.setItems(FXCollections.observableArrayList(dao.listarClientes()));
            cmbProducto.setItems(FXCollections.observableArrayList(dao.listarProductos()));
            tblVentas.setItems(FXCollections.observableArrayList(dao.listarReporte()));
        } catch (SQLException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error al cargar datos: " + e.getMessage());
        }
    }

    @FXML
    private void onGuardar() {
        Cliente cliente = cmbCliente.getValue();
        Producto producto = cmbProducto.getValue();

        if (cliente == null || producto == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Selecciona un cliente y un producto");
            return;
        }

        int cantidad;
        try {
            cantidad = Integer.parseInt(txtCantidad.getText().trim());
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.WARNING, "La cantidad debe ser un número entero");
            return;
        }

        if (cantidad <= 0) {
            mostrarAlerta(Alert.AlertType.WARNING, "La cantidad debe ser mayor a 0");
            return;
        }

        // Validación del negocio: no vender más de lo que hay en stock
        if (cantidad > producto.getStock()) {
            mostrarAlerta(Alert.AlertType.ERROR, "Stock insuficiente. Disponible: " + producto.getStock());
            return;
        }

        double total = cantidad * producto.getPrecio();

        try {
            dao.guardarVenta(cliente.getIdCliente(), producto.getIdProducto(), cantidad, total);
            mostrarAlerta(Alert.AlertType.INFORMATION, "Venta guardada correctamente");
            txtCantidad.clear();
            cmbCliente.setValue(null);
            cmbProducto.setValue(null);
            cargarDatos(); // actualiza stock y tabla
        } catch (SQLException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "No se guardó la venta: " + e.getMessage());
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}