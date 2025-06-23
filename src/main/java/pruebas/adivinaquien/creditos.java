package pruebas.adivinaquien;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class creditos extends JFrame {

    private JPanel panelScroll;
    private int yTexto = 580;

    public creditos() {
        setTitle("Créditos");
        setSize(960, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal
        JPanel mainPanel = new JPanel(null);
        mainPanel.setBackground(Color.BLACK);
        setContentPane(mainPanel);

        // Panel para el scroll
        panelScroll = new JPanel();
        panelScroll.setBackground(Color.BLACK);
        panelScroll.setLayout(null);
        panelScroll.setBounds(0, yTexto, 960, 1500);

// Imagen decorativa (ajustada más pequeña)
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/fondos/foto.jpg"));
        Image imagenEscalada = originalIcon.getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenEscalada);

        JLabel etiquetaImagen = new JLabel(imagen);
        etiquetaImagen.setBounds(405, 10, 150, 200); // centrado aproximado
        panelScroll.add(etiquetaImagen);
        // Texto de créditos
        JLabel creditos = new JLabel("<html><div style='text-align: center;'>"
                + "<h2 style='color:white;'>Créditos del Juego</h2>"
                + "<p style='color:white;'><b>Programación:</b> Diana Ruth Gutiérrez Márquez</p>"
                + "<p style='color:white;'>Alonso Herrera Benavidez</p>"
                + "<p style='color:white;'>Edson Leonardo Sánchez Montalvo</p>"
                + "<p style='color:white;'><b>Universidad:</b> Universidad Autónoma de Aguascalientes</p>"
                + "<p style='color:white;'>4A</p>"
                + "<p style='color:white;'><b>Materia:</b> Programación 3</p>"
                + "<p style='color:white;'><b>Fecha:</b> 20/06/2025</p>"
                + "<p style='color:white;'>2025 - Todos los derechos reservados</p>"
                + "</div></html>");
        creditos.setFont(new Font("Old English Text MT", Font.PLAIN, 35));
        creditos.setBounds(160, 220, 640, 1000);
        panelScroll.add(creditos);

        mainPanel.add(panelScroll);

        // Botón para salir (con estilo unificado y efecto hover)
        JButton back = new JButton("Menu");
        back.setFont(new Font("Old English Text MT", Font.BOLD, 22));
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setFocusPainted(false);
        back.setBorderPainted(false);
        back.setBounds(390, 520, 200, 45);

        // Efecto visual al pasar el mouse
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                back.setBackground(new Color(50, 50, 50)); // gris oscuro
                back.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                back.setBackground(Color.BLACK); // color original
                back.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        back.addActionListener(e -> {
            dispose();
            new menuPrueba().setVisible(true);
        });

        mainPanel.add(back);
        // Movimiento automático del texto
        Timer timer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                yTexto -= 1;
                panelScroll.setLocation(0, yTexto);
                if (yTexto < -creditos.getHeight() - 200) {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        new creditos().setVisible(true);
    }
}
