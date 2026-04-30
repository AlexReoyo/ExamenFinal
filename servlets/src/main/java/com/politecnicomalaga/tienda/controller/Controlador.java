package com.politecnicomalaga.tienda.controller;



import com.google.gson.Gson;
import com.politecnicomalaga.tienda.dataservice.BBDDAccess;
import com.politecnicomalaga.tienda.model.*;

import java.sql.SQLException;
import java.util.*;


public class Controlador implements DataAccess{

    private BBDDAccess miBBDD;

    public Controlador() {
        miBBDD = new BBDDAccess();
    }

    //Implementar lógica definida en el interfaz DataAccess para que los Servlets soliciten lo que quieran

    public String listAllProductos(){
        BBDDAccess bbdd = new BBDDAccess();

        try {
            List<Producto> productos = bbdd.listarTodos();

            return (new Gson()).toJson(productos);

        } catch (SQLException se) {
            return "List: " + se.getMessage();
        } catch (ClassNotFoundException c) {
            return "List: " + c.getMessage();
        }

    }
    public String listAllClients(){
        BBDDAccess bbdd = new BBDDAccess();

        try {
            List<Cliente> clientes = bbdd.listClientes();

            return (new Gson()).toJson(clientes);

        } catch (SQLException se) {
            return "List: " + se.getMessage();
        } catch (ClassNotFoundException c) {
            return "List: " + c.getMessage();
        }

    }

    @Override
    public String findProductoXCodigo(String code) {
        BBDDAccess bbdd = new BBDDAccess();

        try {
            List<Producto> productos = bbdd.findByCode(code);

            return (new Gson()).toJson(productos);

        } catch (SQLException se) {
            return "List: " + se.getMessage();
        } catch (ClassNotFoundException c) {
            return "List: " + c.getMessage();
        }
    }

    @Override
    public String findClienteXDNI(String dni) {
        BBDDAccess bbdd = new BBDDAccess();

        try {
            List<Cliente> clientes = bbdd.findByDni(dni);

            return (new Gson()).toJson(clientes);

        } catch (SQLException se) {
            return "List: " + se.getMessage();
        } catch (ClassNotFoundException c) {
            return "List: " + c.getMessage();
        }
    }

    @Override
    public String listProductosXPedido(String dni, String pedido) {
        BBDDAccess bbdd = new BBDDAccess();

        try {
            List<Producto> productos = bbdd.listProductsXPedido(dni,pedido);

            return (new Gson()).toJson(productos);

        } catch (SQLException se) {
            return "List: " + se.getMessage();
        } catch (ClassNotFoundException c) {
            return "List: " + c.getMessage();
        }
    }

    @Override
    public String importData(String jsonDataFromCSV) {
        return "";
    }

}