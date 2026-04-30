package com.politecnicomalaga.DataAccess;

import java.io.IOException;
import java.util.logging.Handler;

public class DataAccess {
    //Cliente HTTP
    String URL = "http://" + IP + "/listarproductos"; //algún día, mi Tomcat estará en un VPS, y será libre :)
    // ... Mientras, a poner la IP de la LAN

    OkHttpClient clientHTTP = new OkHttpClient();

    //Petición a realizar al cliente HTTP. Patrón de diseño "Builder". Es decir "poco a poco"
    Request request = new Request.Builder()
            .url(URL)  //dirección web
            .get()     //función http a utilizar, puede ser post, put,...
            .addHeader("accept", "application/json")  //Qué formato queremos
            .build();   //a construir la request!!

    //realizamos la llamada al server, pero en otro thread (con enqueue)
    //Primero, una llamada al server
    Call llamada = clientHTTP.newCall(request);
    //Ponemos la llamada en cola para que salga por la tarjeta de red que tengamos en el móvil activa, y creamos un
    //objeto anónimo CallBack (llamada de vuelta cuando están los datos)
    //Un callback tiene override de onResponse (la petición se ha atendido perfectamente) y
    //override de onFailure (evento de "algo salió mal")
        llamada.enqueue(new

    Callback() {
        public void onResponse (Call call, Response respuestaServer)
                    throws IOException {
            //Got answer!!!!! cogemos los datos dentro del body del mensaje
            String respuesta = respuestaServer.body().string();

            // Create a handler that associated with Looper of the main thread
            //Un manejador es un "bucle" en esencia que ejecuta uno a uno todos los procesos de la App
            Handler manejador = new Handler(Looper.getMainLooper()); //pedimos el principal de la app
            // Send a task to the MessageQueue of the main thread
            manejador.post(new Runnable() {
                @Override
                public void run() {
                    // Code will be executed on the main thread
                    //Este es código que realmente se ejecuta cuando se recibe la respuesta.
                    c.setData(respuesta, false, "productos");
                }
            });
        }

        public void onFailure (Call call, IOException e){
            //Cuidado, puede que haya alguna vez un fallo en la respuesta. Entonces entra por aquí
            String respuesta = e.getMessage(); //Fijaros que nos pasan la excepción con el problema.
            //Lo típico es "sacar" el string mensaje de la exceptión y mandarla al sistema principal para
            //que se vea que ha pasado
            Handler manejador = new Handler(Looper.getMainLooper());

            // Send a task to the MessageQueue of the main thread
            manejador.post(new Runnable() {
                @Override
                public void run() {
                    // Code will be executed on the main thread

                    c.setData(respuesta, true, "productos");

                }
            });
        }
    });
}

