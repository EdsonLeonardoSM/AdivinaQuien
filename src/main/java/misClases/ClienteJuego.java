package misClases;

import java.io.*;
import java.net.*;
import java.util.List;
import pruebas.adivinaquien.pantallaDeCarga;
import pruebas.adivinaquien.gameplay;

public class ClienteJuego {

    public ClienteJuego(String nombreJugador) {
        try {
            String ip = "192.168.0.159"; // IP del servidor
            Socket socket = new Socket(ip, 54321);
            System.out.println("Conectado al servidor");

            // (Opcional) Mostrar pantalla de carga
            //new pantallaDeCarga(nombreJugador).setVisible(true);

            // 1. Recibir tablero del servidor
            ObjectInputStream inObj = new ObjectInputStream(socket.getInputStream());
            List<Personaje> tablero = (List<Personaje>) inObj.readObject();

            // 2. Crear conexión de chat
            ChatConexion chat = new ChatConexion(socket);

            // 3. Abrir ventana de juego
            gameplay juego = new gameplay(tablero, ip, false,nombreJugador); // false = cliente
            juego.setChat(chat);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
