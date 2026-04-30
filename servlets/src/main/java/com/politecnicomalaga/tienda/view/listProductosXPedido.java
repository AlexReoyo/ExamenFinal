package com.politecnicomalaga.tienda.view;

import com.politecnicomalaga.tienda.controller.Controlador;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class listProductosXPedido extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        String dni = request.getParameter("DNI");
        String pedido = request.getParameter("id_pedido");
        PrintWriter out = response.getWriter();
        out.println((new Controlador()).listProductosXPedido(dni,pedido));
    }
}
