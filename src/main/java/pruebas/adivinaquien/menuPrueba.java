package pruebas.adivinaquien;

import javax.swing.*;
import java.awt.*;

public class menuPrueba extends JFrame {

    public menuPrueba() {
        setTitle("Menú Principal");
        setSize(960, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar en pantalla

        // Cargar la imagen de fondo
        ImageIcon fondoIcon = new ImageIcon(getClass().getResource("/fondos/fondo1.jpg"));
        JLabel fondo = new JLabel(fondoIcon);
        fondo.setLayout(new BoxLayout(fondo, BoxLayout.Y_AXIS));
        setContentPane(fondo); 

        // Espaciado superior
        fondo.add(Box.createVerticalStrut(170));

        // Crear y centrar botones
        String[] nombres = {"Jugar", "instrucciones", "creditos", "Registros"};
        for (String texto : nombres) {
            JButton boton = new JButton(texto);
            boton.setAlignmentX(Component.CENTER_ALIGNMENT);
            boton.setMaximumSize(new Dimension(200, 50));
            boton.setFont(new Font("Old English Text MT", Font.PLAIN, 20));
            
            if(texto.equals("creditos")){
                boton.addActionListener(e -> new creditos().setVisible(true));
            }
            
            if(texto.equals("Jugar")){
                boton.addActionListener(e -> new pantallaDeCarga().setVisible(true));
            }
            
            if(texto.equals("instrucciones")){
                boton.addActionListener(e -> new instrucciones().setVisible(true));
            }
            
            fondo.add(boton);
            fondo.add(Box.createVerticalStrut(20)); //espacio entre botones
        }

        setVisible(true);
        
    }

    public static void main(String[] args) {
        new menuPrueba();
    }
}
