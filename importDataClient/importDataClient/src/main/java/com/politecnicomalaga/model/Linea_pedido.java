package com.politecnicomalaga.model;

import java.util.ArrayList;
import java.util.List;

public class Linea_pedido {
    private int id_linea,id_pedido,id_producto,cantidad;
    private double precio_unitario,subtotal;
    private List<Producto> productos;

    public Linea_pedido(int id_linea, int id_pedido, int id_producto, int cantidad, double precio_unitario, double subtotal) {
        this.id_linea = id_linea;
        this.id_pedido = id_pedido;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
        this.subtotal = subtotal;
        productos= new ArrayList<>();
    }

    public int getId_linea() {
        return id_linea;
    }

    public void addPedido(Producto p){
        productos.add(p);
    }

    public void setId_linea(int id_linea) {
        this.id_linea = id_linea;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
