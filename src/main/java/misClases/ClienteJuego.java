package misClases;

import java.io.*;
import java.net.*;
import java.util.List;
import pruebas.adivinaquien.pantallaDeCarga;
import pruebas.adivinaquien.gameplay;

public class ClienteJuego {
    private static Socket socket=null;
    public static void cerrar(){
        try{
            socket.close();
        }catch(Exception e){
            
        }
        
    }

    public ClienteJuego(String nombreJugador) {
        try {
            String ip = "192.168.0.159"; // IP del servidor

            // 1. Mostrar pantalla de carga
            pantallaDeCarga pantalla = new pantallaDeCarga(nombreJugador);
            pantalla.setVisible(true);

            // 2. Conectarse al servidor
            socket = new Socket(ip, 54321);
            System.out.println("Conectado al servidor");

            // 3. Recibir tablero del servidor
            ObjectInputStream inObj = new ObjectInputStream(socket.getInputStream());
            List<Personaje> tablero = (List<Personaje>) inObj.readObject();

            // 🔴 Cerrar pantalla de carga tras recibir los datos
            pantalla.dispose();

            // 4. Crear conexión de chat
            ChatConexion chat = new ChatConexion(socket);

            // 5. Abrir ventana de juego
            gameplay juego = new gameplay(tablero, ip, false, nombreJugador);
            juego.setChat(chat);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
