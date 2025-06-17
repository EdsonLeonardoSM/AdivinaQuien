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
import java.util.function.Consumer;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class ChatConexion {
    private Socket socket;
    private BufferedReader entrada;
    private PrintWriter salida;
    public Consumer<String> onPersonajeRecibido;
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
                        if (msg.startsWith("[PERSONAJE]:")) {
                            String nombre = msg.substring(12);
                            if (onPersonajeRecibido != null) {
                                onPersonajeRecibido.accept(nombre);
                            }
                            continue;
                        }

                        // Adivinó mal
                        if (msg.equals("[FALLO]")) {
                            JOptionPane.showMessageDialog(null, 
                                "⚠️ El oponente intentó adivinar tu personaje pero falló.",
                                "¡A salvo!", JOptionPane.WARNING_MESSAGE);
                            continue;
                        }

                        // Adivinó bien
                        if (msg.equals("[GANASTE]")) {
                            JOptionPane.showMessageDialog(null, 
                                "😞 El oponente adivinó tu personaje. ¡Perdiste!",
                                "Derrota", JOptionPane.ERROR_MESSAGE);
                            // bloquear botones o terminar el juego
                            continue;
                        }

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
    
    public void cerrar() {
    try {
        if (entrada != null) entrada.close();
        if (salida != null) salida.close();
        if (socket != null && !socket.isClosed()) socket.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}
