package pruebas.adivinaquien;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
        fondo.add(Box.createVerticalStrut(160));

        // Crear y centrar botones
        String[] nombres = {"Jugar", "Instrucciones", "Créditos", "Registros"};
        for (String texto : nombres) {
            JButton boton = new JButton(texto);
            boton.setAlignmentX(Component.CENTER_ALIGNMENT);
            boton.setMaximumSize(new Dimension(200, 50));
            boton.setFont(new Font("Old English Text MT", Font.BOLD, 24));
            boton.setBackground(Color.BLACK);
            boton.setForeground(Color.WHITE);
            boton.setFocusPainted(false);
            boton.setBorderPainted(false);

            // Efecto hover
            boton.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent evt) {
                    boton.setBackground(new Color(50, 50, 50)); // gris oscuro
                    boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }

                public void mouseExited(MouseEvent evt) {
                    boton.setBackground(Color.BLACK);
                    boton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            });

            // Acciones
            switch (texto) {
                case "Jugar":
                    boton.addActionListener(e -> {
                        dispose();
                        new PantallaUsuario().setVisible(true);
                    });
                    break;
                case "Instrucciones":
                    boton.addActionListener(e -> {
                        dispose();
                        new instrucciones().setVisible(true);
                    });
                    break;
                case "Créditos":
                    boton.addActionListener(e -> {
                        dispose();
                        new creditos().setVisible(true);
                    });
                    break;
                case "Registros":
                    boton.addActionListener(e -> {
                        int opcion = JOptionPane.showConfirmDialog(
                            null,
                            "¿Eres el servidor?\n(Solo el servidor puede acceder a la base de datos)",
                            "Confirmar acceso",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE
                        );

                        if (opcion == JOptionPane.YES_OPTION) {
                            dispose();
                            new TablaPartidas().setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(
                                null,
                                "Acceso denegado.\nSolo el servidor puede consultar los registros.",
                                "Acceso restringido",
                                JOptionPane.ERROR_MESSAGE
                            );
                        }
                    });
                    break;

            }

            fondo.add(boton);
            fondo.add(Box.createVerticalStrut(20));
        }

        setVisible(true);
    }

    public static void main(String[] args) {
        new menuPrueba();
    }
}
