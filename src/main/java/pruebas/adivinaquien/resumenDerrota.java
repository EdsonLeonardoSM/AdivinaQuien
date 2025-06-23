package pruebas.adivinaquien;

import javax.swing.*;
import java.awt.*;

public class resumenDerrota extends JFrame {

    public resumenDerrota(String perdedor, String ganador, String personajeElegido, String duracion) {
        setTitle("Resumen de la Derrota");
        setSize(960, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        // Fondo oscuro con texto blanco
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.BLACK);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel lblTitulo = new JLabel("😔 DERROTA 😔");
        lblTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        lblTitulo.setForeground(Color.RED);
        gbc.gridy = 0;
        panel.add(lblTitulo, gbc);

        JLabel lblPerdedor = new JLabel("Jugador: " + perdedor);
        lblPerdedor.setFont(new Font("Arial", Font.BOLD, 24));
        lblPerdedor.setForeground(Color.WHITE);
        gbc.gridy = 1;
        panel.add(lblPerdedor, gbc);

        JLabel lblGanador = new JLabel("Ganador: " + ganador);
        lblGanador.setFont(new Font("Arial", Font.PLAIN, 22));
        lblGanador.setForeground(Color.LIGHT_GRAY);
        gbc.gridy = 2;
        panel.add(lblGanador, gbc);

        JLabel lblPersonaje = new JLabel("Tu personaje era: " + personajeElegido);
        lblPersonaje.setFont(new Font("Arial", Font.PLAIN, 22));
        lblPersonaje.setForeground(Color.LIGHT_GRAY);
        gbc.gridy = 3;
        panel.add(lblPersonaje, gbc);

        JLabel lblTiempo = new JLabel("Duración de la partida: " + duracion);
        lblTiempo.setFont(new Font("Arial", Font.ITALIC, 20));
        lblTiempo.setForeground(Color.GRAY);
        gbc.gridy = 4;
        panel.add(lblTiempo, gbc);

        // Panel de botones
        JPanel botonPanel = new JPanel();
        botonPanel.setBackground(Color.BLACK);
        botonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));

        JButton btnVolverJugar = new JButton("Volver a Jugar");
        btnVolverJugar.setPreferredSize(new Dimension(180, 40));
        btnVolverJugar.setFont(new Font("Arial", Font.BOLD, 18));
        btnVolverJugar.setBackground(Color.DARK_GRAY);
        btnVolverJugar.setForeground(Color.WHITE);
        btnVolverJugar.setFocusPainted(false);
        btnVolverJugar.addActionListener(e -> {
            dispose(); 
            new PantallaUsuario().setVisible(true);
        });

        JButton btnMenu = new JButton("Menú Principal");
        btnMenu.setPreferredSize(new Dimension(180, 40));
        btnMenu.setFont(new Font("Arial", Font.BOLD, 18));
        btnMenu.setBackground(Color.DARK_GRAY);
        btnMenu.setForeground(Color.WHITE);
        btnMenu.setFocusPainted(false);
        btnMenu.addActionListener(e -> {
            dispose(); 
            new menuPrueba().setVisible(true);
        });

        botonPanel.add(btnVolverJugar);
        botonPanel.add(btnMenu);

        gbc.gridy = 5;
        panel.add(botonPanel, gbc);

        add(panel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new resumenDerrota("Tú", "Rival", "Pikachu", "03:25").setVisible(true);
    }
}
