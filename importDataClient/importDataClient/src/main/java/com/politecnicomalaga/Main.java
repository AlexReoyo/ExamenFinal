package com.politecnicomalaga;

import com.politecnicomalaga.controlador.Controlador;

public class Main {
    public static void main(String[] args) {

        System.out.printf("Se procede a realizar el transformado y mandarlo al servidor");

        Controlador c= new Controlador();
        c.formateo();

    }
}