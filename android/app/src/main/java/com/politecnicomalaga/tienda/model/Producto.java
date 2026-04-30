package com.politecnicomalaga.tienda.model;

public class Producto {
    private int id_producto;
    private String descripcion;
    private double precio_unitario;

    public Producto(int id_producto, String descripcion, double precio_unitario) {
        this.id_producto = id_producto;
        this.descripcion = descripcion;
        this.precio_unitario = precio_unitario;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }
}
