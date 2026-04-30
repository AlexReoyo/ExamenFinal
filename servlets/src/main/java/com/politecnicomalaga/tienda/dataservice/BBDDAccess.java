package com.politecnicomalaga.tienda.dataservice;



import com.politecnicomalaga.tienda.model.Cliente;
import com.politecnicomalaga.tienda.model.Producto;

import java.awt.color.ProfileDataException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BBDDAccess {

    //método para obtener los productos. Se conecta y ejecuta el select
    //No sabe se "vive" en una aplicación Java tradicional, en un ExecuteService de Android,
    //en un servlet... Simplemente "pide" a la BBDD la ejecución de SQL y obtiene los datos
    //para convertirlo en objetos del modelo.
    public List<Producto> listarTodos() throws SQLException,ClassNotFoundException {

        Connection conn = null;
        List<Producto> listaResultado = new ArrayList<>();

        conn = ConexionBD.getConnection();

        String sql = "SELECT * from productos";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            listaResultado.add(new Producto(
                    rs.getInt("id_producto"),
                    rs.getString("descripcion"),
                    rs.getDouble("precio_unitario")
            ));
        }

        if (rs!=null) rs.close();
        if (stmt!=null) stmt.close();
        if (conn != null) conn.close();

        return listaResultado;
    }

    public List<Producto> findByCode(String code) throws SQLException,ClassNotFoundException {

        Connection conn = null;
        List<Producto> listaResultado = new ArrayList<>();

        conn = ConexionBD.getConnection();

        String sql = "SELECT * from productos where id_producto="+code;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            listaResultado.add(new Producto(
                    rs.getInt("id_producto"),
                    rs.getString("descripcion"),
                    rs.getDouble("precio_unitario")
            ));
        }

        if (rs!=null) rs.close();
        if (stmt!=null) stmt.close();
        if (conn != null) conn.close();

        return listaResultado;
    }
    public List<Cliente> findByDni(String code) throws SQLException,ClassNotFoundException {

        Connection conn = null;
        List<Cliente> listaResultado = new ArrayList<>();

        conn = ConexionBD.getConnection();

        String sql = "SELECT * from clientes where dni="+code;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            listaResultado.add(new Cliente(
                    rs.getString("dni"),
                    rs.getString("nombre"),
                    rs.getString("apellidos"),
                    rs.getString("email"),
                    rs.getString("telefono"),
                    rs.getString("direccion")

            ));
        }

        if (rs!=null) rs.close();
        if (stmt!=null) stmt.close();
        if (conn != null) conn.close();

        return listaResultado;
    }
    public List<Cliente> listClientes() throws SQLException,ClassNotFoundException {

        Connection conn = null;
        List<Cliente> listaResultado = new ArrayList<>();

        conn = ConexionBD.getConnection();

        String sql = "SELECT * from clientes";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            listaResultado.add(new Cliente(
                    rs.getString("dni"),
                    rs.getString("nombre"),
                    rs.getString("apellidos"),
                    rs.getString("email"),
                    rs.getString("telefono"),
                    rs.getString("direccion")

            ));
        }

        if (rs!=null) rs.close();
        if (stmt!=null) stmt.close();
        if (conn != null) conn.close();

        return listaResultado;
    }
    public List<Producto> listProductsXPedido(String dni, String id_pedido) throws SQLException,ClassNotFoundException {

        Connection conn = null;
        List<Producto> listaResultado = new ArrayList<>();

        conn = ConexionBD.getConnection();

        String sql = "SELECT productos.id_producto, productos.descripcion, productos.precio_unitario from productos JOIN lineas_pedido on lineas_pedido.id_producto=productos.id_producto join pedidos on pedidos.id_pedido=lineas_pedido.id_pedido join clientes on clientes.dni=pedidos.dni_cliente;\n ";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            listaResultado.add(new Producto(
                    rs.getInt("productos.id_producto"),
                    rs.getString("productos.descripcion"),
                    rs.getDouble("productos.precio_unitario")

            ));
        }

        if (rs!=null) rs.close();
        if (stmt!=null) stmt.close();
        if (conn != null) conn.close();

        return listaResultado;
    }




}