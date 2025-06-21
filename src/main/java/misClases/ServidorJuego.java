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

            // 1. Mostrar pantalla de carga y guardarla en una variable
            pantallaDeCarga pantalla = new pantallaDeCarga(nombreJugador);
            pantalla.setVisible(true);

            // 2. Esperar conexión
            Socket socket = servidor.accept();
            System.out.println("Cliente conectado: " + socket.getInetAddress());

            // 🔴 3. Cerrar pantalla de carga al conectar
            pantalla.dispose();  // Esto la cierra

            // 4. Generar tablero
            List<Personaje> tablero = GeneradorPersonajes.obtenerPersonajesAleatorios();

            // 5. Enviar tablero al cliente
            ObjectOutputStream outObj = new ObjectOutputStream(socket.getOutputStream());
            outObj.writeObject(tablero);
            outObj.flush();

            // 6. Crear conexión de chat
            ChatConexion chat = new ChatConexion(socket);

            // 7. Abrir juego
            gameplay juego = new gameplay(tablero, socket.getInetAddress().getHostAddress(), true, nombreJugador);
            juego.setChat(chat);

        } catch (IOException e) {
            System.err.println("Error al iniciar servidor: " + e.getMessage());
        }
    }
}