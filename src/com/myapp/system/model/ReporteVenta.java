package com.myapp.system.model;

// Una fila de la tabla del reporte (resultado del INNER JOIN)
public class ReporteVenta {

    private int idVenta;
    private String fecha;
    private String cliente;
    private String producto;
    private int cantidad;
    private String total;

    public ReporteVenta(int idVenta, String fecha, String cliente, String producto, int cantidad, String total) {
        this.idVenta = idVenta;
        this.fecha = fecha;
        this.cliente = cliente;
        this.producto = producto;
        this.cantidad = cantidad;
        this.total = total;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public String getFecha() {
        return fecha;
    }

    public String getCliente() {
        return cliente;
    }

    public String getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getTotal() {
        return total;
    }
}