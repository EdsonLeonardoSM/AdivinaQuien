package misClases;

import java.io.*;
import java.net.*;
import java.util.List;

public class ClienteJuego {
    public static void main(String[] args) {
        try {
            String ip = "192.168.0.159";
            Socket socket = new Socket(ip, 12345);
            System.out.println("Conectado al servidor");

            // 1. Recibir tablero del servidor
            ObjectInputStream inObj = new ObjectInputStream(socket.getInputStream());
            List<Personaje> tablero = (List<Personaje>) inObj.readObject();

            // 2. Crear conexión de chat
            ChatConexion chat = new ChatConexion(socket);

            // 3. Abrir ventana de juego
            pruebas.adivinaquien.gameplay juego = new pruebas.adivinaquien.gameplay(tablero,ip);
            juego.setChat(chat);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
