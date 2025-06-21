package pruebas.adivinaquien;

import javax.swing.*;
import java.awt.*;
import misClases.ClienteJuego;
import misClases.ServidorJuego;

public class PantallaUsuario extends JFrame {

    public PantallaUsuario() {
        setTitle("Pantalla de Jugador");
        setSize(960, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);


        JPanel fondo = new JPanel();
        fondo.setLayout(null);
        fondo.setBackground(Color.DARK_GRAY);
        setContentPane(fondo);

        // Sombra
        JLabel sombra = new JLabel("Bienvenido");
        sombra.setFont(new Font("Old English Text MT", Font.PLAIN, 77));
        sombra.setForeground(Color.BLACK);
        sombra.setBounds(301, 101, 500, 50);
        fondo.add(sombra);

        // Texto principal
        JLabel titulo = new JLabel("Bienvenido");
        titulo.setFont(new Font("Old English Text MT", Font.BOLD, 77));
        titulo.setForeground(Color.YELLOW);
        titulo.setBounds(300, 100, 500, 50);
        fondo.add(titulo);

        // Etiqueta usuario
        JLabel etiqueta = new JLabel("Nombre del jugador:");
        etiqueta.setFont(new Font("Old English Text MT", Font.PLAIN, 24));
        etiqueta.setForeground(Color.WHITE);
        etiqueta.setBounds(330, 180, 300, 30);
        fondo.add(etiqueta);

        // Campo de texto
        JTextField campoUsuario = new JTextField();
        campoUsuario.setFont(new Font("Old English Text MT", Font.PLAIN, 22));
        campoUsuario.setBounds(330, 220, 300, 40);
        fondo.add(campoUsuario);

        // Botón continuar
        JButton boton = new JButton("Continuar");
        boton.setFont(new Font("Old English Text MT", Font.BOLD, 20));
        boton.setBackground(Color.BLACK);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBounds(380, 280, 200, 45);
        fondo.add(boton);

        boton.addActionListener(e -> {
            String nombre = campoUsuario.getText().trim();
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "⚠️ Por favor ingresa tu nombre.");
            } else {
                // Pregunta si es servidor o cliente
                String[] opciones = {"Servidor", "Cliente"};
                int seleccion = JOptionPane.showOptionDialog(
                    this,
                    "¿Deseas iniciar como servidor o cliente?",
                    "Selecciona rol de juego",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
                );

                if (seleccion == 0) {
                    // Servidor
                    new ServidorJuego(nombre); 
                    
                } else if (seleccion == 1) {
                    // Cliente
                    new ClienteJuego(nombre);

                }

                dispose(); // cerrar esta ventana
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new PantallaUsuario();
    }
}
