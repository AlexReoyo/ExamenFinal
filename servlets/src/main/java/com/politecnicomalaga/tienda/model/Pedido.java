package com.politecnicomalaga.tienda.model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id_pedido;
    private String dni_cliente;
    private String fecha_pedido;
    private  int num_lineas;
    private double total_pedido;
    private List<Linea_pedido> lineas;

    public Pedido(int id_pedido, String dni_cliente, String fecha_pedido, int num_lineas, double total_pedido) {
        this.id_pedido = id_pedido;
        this.dni_cliente = dni_cliente;
        this.fecha_pedido = fecha_pedido;
        this.num_lineas = num_lineas;
        this.total_pedido = total_pedido;
        lineas= new ArrayList<>();
    }

    public int getId_pedido() {
        return id_pedido;
    }
    public void addPedido(Linea_pedido lp){
        lineas.add(lp);
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getDni_cliente() {
        return dni_cliente;
    }

    public void setDni_cliente(String dni_cliente) {
        this.dni_cliente = dni_cliente;
    }

    public String getFecha_pedido() {
        return fecha_pedido;
    }

    public void setFecha_pedido(String fecha_pedido) {
        this.fecha_pedido = fecha_pedido;
    }

    public int getNum_lineas() {
        return num_lineas;
    }

    public void setNum_lineas(int num_lineas) {
        this.num_lineas = num_lineas;
    }

    public double getTotal_pedido() {
        return total_pedido;
    }

    public void setTotal_pedido(double total_pedido) {
        this.total_pedido = total_pedido;
    }
}
