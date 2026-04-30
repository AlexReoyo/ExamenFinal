package com.politecnicomalaga.tienda.controller;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.politecnicomalaga.tienda.MainActivity;
import com.politecnicomalaga.tienda.dataservice.BBDDAccess;
import com.politecnicomalaga.tienda.model.*;

import java.lang.reflect.Type;
import java.util.*;


public class Controlador {
    // instance variables
    private MainActivity miPantalla;
    private List<Cliente> clientes;
    private List<Producto> products;
    
    //Singleton poner aquí
    private static Controlador singleton;
    
    private Controlador(MainActivity miPantalla) {
        this.miPantalla = miPantalla;
        clientes = new ArrayList<>();
        products= new ArrayList<>();
    }
    public static Controlador getSingleton(MainActivity miPantalla) {
        // put your code here
        if (singleton == null) singleton = new Controlador(miPantalla);
        return singleton;
    }

    public void listarTodosProductos() {
        BBDDAccess miBBDD = new BBDDAccess(this);
        miBBDD.listarTodosProductos();

    }
    public void listarTodosClientes(){
        BBDDAccess miBBDD = new BBDDAccess(this);
        miBBDD.listarTodosClientes();
    }

    public List<Map<String,String>> getDataProductos() {

        List<Map<String,String>> resultado = new ArrayList<>();

        //Cambiar del List<Producto> y List<ProductoPerecedero> a
        //List de maps

        for(Producto p: products) {
            Map<String,String> productoMapeado = new HashMap<>();
            productoMapeado.put("id",""+p.getId_producto());
            productoMapeado.put("d",p.getDescripcion());
            productoMapeado.put("p",""+p.getPrecio_unitario());
            resultado.add(productoMapeado);
        }

        return resultado;
    }
    public List<Map<String,String>> getDataClientes() {

        List<Map<String,String>> resultado = new ArrayList<>();

        //Cambiar del List<Producto> y List<ProductoPerecedero> a
        //List de maps

        for(Cliente c: clientes) {
            Map<String,String> productoMapeado = new HashMap<>();
            productoMapeado.put("n",""+c.getNombre());
            productoMapeado.put("a",""+c.getApellidos());
            productoMapeado.put("d",""+c.getDni());
            productoMapeado.put("dir",""+c.getDireccion());
            productoMapeado.put("t",""+c.getTelefono());
            productoMapeado.put("e",""+c.getEmail());

            resultado.add(productoMapeado);
        }

        return resultado;
    }

    //Este método es llamado por OKhttp cuando se produce la respuesta a la
    // petición de datos a nuestro backend
    public void setData(String jsonData, boolean error, String tipo) {

        try {
            JsonParser.parseString(jsonData);
            //si estamos aquí, es un json
        } catch (JsonSyntaxException e) {
            error = true; //se que no tengo un json
        }

        if (!error) {
            if (tipo.equals("clientes")){
                clientes.clear();
                Type tipoListaProductos = new TypeToken<List<Cliente>>() {
                }.getType();
                clientes = (new Gson().fromJson(jsonData, tipoListaProductos));
                this.miPantalla.reaccionar("","clientes");
            } else  {
                products.clear();
                Type tipoListaProductos = new TypeToken<List<Producto>>() {
                }.getType();
                products = (new Gson().fromJson(jsonData, tipoListaProductos));
                this.miPantalla.reaccionar("","productos");
            }
        } else {
            this.miPantalla.reaccionar("Error de acceso a Backend " + jsonData, "error");
        }

    }

}