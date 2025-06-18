package misClases;

import java.io.*;
import java.net.*;
import java.util.List;
import pruebas.adivinaquien.pantallaDeCarga;
import pruebas.adivinaquien.gameplay;

public class ServidorJuego {


    public ServidorJuego(String nombreJugador) {

        try {
            ServerSocket servidor = new ServerSocket(54321);
            System.out.println("Esperando conexión del cliente...");
            
            // Pantalla de carga puede usar nombre
            //new pantallaDeCarga(nombreJugador).setVisible(true);

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
            gameplay juego = new gameplay(tablero, socket.getInetAddress().getHostAddress(), true, nombreJugador); // true = soy servidor
            juego.setChat(chat);

        } catch (IOException e) {
            System.err.println("Error al iniciar servidor: " + e.getMessage());
        }
    }
}
