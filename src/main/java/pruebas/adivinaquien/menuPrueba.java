package pruebas.adivinaquien;

import javax.swing.*;
import java.awt.*;

public class menuPrueba extends JFrame {

    public menuPrueba() {
        setTitle("Menú Principal");
        setSize(986, 636);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar en pantalla

        // Cargar la imagen de fondo
        ImageIcon fondoIcon = new ImageIcon(getClass().getResource("/imagenes/leche.png")); // adapta la ruta si es necesario
        JLabel fondo = new JLabel(fondoIcon);
        fondo.setLayout(new BoxLayout(fondo, BoxLayout.Y_AXIS)); // layout vertical
        setContentPane(fondo); // usamos el JLabel como fondo

        // Espaciado superior
        fondo.add(Box.createVerticalStrut(100));

        // Crear y centrar botones
        String[] nombres = {"Jugar", "Opciones", "Créditos", "Salir"};
        for (String texto : nombres) {
            JButton boton = new JButton(texto);
            boton.setAlignmentX(Component.CENTER_ALIGNMENT);
            boton.setMaximumSize(new Dimension(200, 50));
            boton.setFont(new Font("Old English Text MT", Font.PLAIN, 20));
            fondo.add(boton);
            fondo.add(Box.createVerticalStrut(20)); // espacio entre botones
        }

        setVisible(true);
    }

    public static void main(String[] args) {
        new menuPrueba();
    }
}
