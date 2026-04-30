package com.politecnicomalaga.controlador;

import com.google.gson.Gson;
import com.politecnicomalaga.model.Cliente;
import com.politecnicomalaga.model.Linea_pedido;
import com.politecnicomalaga.model.Pedido;
import com.politecnicomalaga.model.Producto;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controlador {

    private List<Cliente> clientes;
    private List<Pedido> pedidos;
    private List<Linea_pedido> lineasPedido;
    private List<Producto> productos;

    public Controlador(){
        clientes = new ArrayList<>();
    }

    public void formateo(){
        File file= new File("../data/data.csv");
        try {
            Cliente c;
            Pedido pe = null;
            Linea_pedido lp;
            Producto pr=null;

            int contadorLinea = 1;
            double subtotal=0;
            Scanner scFich = new Scanner(new FileReader(file));
            while (scFich.hasNextLine()) {
                String linea = scFich.nextLine();
                String[] lineas= linea.split("\n\n");
                for (String p : lineas){
                    String[] detalles = p.split("\n");
                    for (int i=0;i<detalles.length;i++){
                        String[] campos = detalles[i].split("#");
                        if (campos.length!=5){
                            c = new Cliente(campos[2],campos[0],campos[1],campos[3],campos[5],campos[4]);
                            pe = new Pedido(Integer.valueOf(campos[6]),campos[2],campos[7],Integer.parseInt(campos[8]),Double.parseDouble(campos[9]));
                            contadorLinea=1;
                            subtotal=0;
                        } else {
                            subtotal+= Double.parseDouble(campos[4])*Integer.parseInt(campos[3]);
                            lp = new Linea_pedido(contadorLinea,pe.getId_pedido(),Integer.parseInt(campos[1]),Integer.parseInt(campos[3]),Double.parseDouble(campos[4]),subtotal);
                            contadorLinea++;
                            pr = new Producto(Integer.parseInt(campos[1]),campos[2],Double.parseDouble(campos[4]));
                            lineasPedido.add(lp);
                            addProducto(pr);
                        }
                    }
                }

            }
            Gson gson = new Gson();

            scFich.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addCliente(Cliente cliente){
        for (Cliente c: clientes){
            if (c.getDni().equals(cliente.getDni())){
                return;
            }
        }
        clientes.add(cliente);
    }
    public void addProducto(Producto producto){
        for (Producto p: productos){
            if (p.getId_producto()==producto.getId_producto()){
                return;
            }
        }
        productos.add(producto);
    }
}
