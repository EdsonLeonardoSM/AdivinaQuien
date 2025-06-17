/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package misClases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JTextArea;

public class ChatConexion {
    private Socket socket;
    private BufferedReader entrada;
    private PrintWriter salida;

    public ChatConexion(Socket socket){
        try {
            this.socket = socket;
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            salida = new PrintWriter(socket.getOutputStream(), true);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void recibirMensajes(JTextArea areaMensajes){
        new Thread(() -> {
            String msg;
            try {
                while((msg = entrada.readLine()) != null){
                    areaMensajes.append("Otro: " + msg + "\n");
                }
            } catch(IOException e){
                areaMensajes.append("Conexión cerrada.\n");
            }
        }).start();
    }

    public void enviarMensaje(String msg){
        salida.println(msg);
    }
}
