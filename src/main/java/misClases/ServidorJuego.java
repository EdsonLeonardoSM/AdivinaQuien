package misClases;

import java.io.*;
import java.net.*;
import java.util.List;

public class ServidorJuego {
    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(12345);
            System.out.println("Servidor esperando conexión...");

            Socket socket = servidor.accept();
            System.out.println("Cliente conectado: " + socket.getInetAddress());

            // 1. Generar tablero
            List<Personaje> tablero = GeneradorPersonajes.obtenerPersonajesAleatorios();

            // 2. Enviar tablero al cliente
            ObjectOutputStream outObj = new ObjectOutputStream(socket.getOutputStream());
            outObj.writeObject(tablero);
            outObj.flush();

            // 3. Crear conexión de chat
            ChatConexion chat = new ChatConexion(socket);

            // 4. Abrir ventana de juego
            pruebas.adivinaquien.gameplay juego = new pruebas.adivinaquien.gameplay(tablero,"192.168.0.159");
            juego.setChat(chat);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
