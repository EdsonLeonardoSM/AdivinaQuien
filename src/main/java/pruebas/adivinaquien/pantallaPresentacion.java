package pruebas.adivinaquien;

import javax.swing.*;
import java.awt.*;

public class pantallaPresentacion extends JFrame {

    public pantallaPresentacion() {
        setTitle("Bienvenido a Adivina Quién");
        setSize(960, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Panel principal con layout absoluto
        JPanel fondo = new JPanel();
        fondo.setLayout(null);
        fondo.setBackground(Color.DARK_GRAY);
        setContentPane(fondo);

        // Sombra del título (ligeramente desplazada)
        JLabel sombra = new JLabel("ADIVINA QUIÉN");
        sombra.setFont(new Font("Old English Text MT", Font.PLAIN, 57));
        sombra.setForeground(Color.BLACK);
        sombra.setBounds(246, 106, 600, 80);
        fondo.add(sombra);

        // Título principal (centrado)
        JLabel titulo = new JLabel("ADIVINA QUIÉN");
        titulo.setFont(new Font("Old English Text MT", Font.BOLD, 57));
        titulo.setForeground(Color.YELLOW);
        titulo.setBounds(240, 100, 600, 80);
        fondo.add(titulo);

        // Subtítulo centrado bajo el título
        JLabel subtitulo = new JLabel("¡El que adivine primero gana!");
        subtitulo.setFont(new Font("Old English Text MT", Font.PLAIN, 22));
        subtitulo.setForeground(Color.WHITE);
        subtitulo.setBounds(339, 180, 500, 30);
        fondo.add(subtitulo);

        // Botón para ir al menú
        JButton btnMenu = new JButton("MENÚ");
        btnMenu.setFont(new Font("Old English Text MT", Font.BOLD, 24));
        btnMenu.setBackground(Color.BLACK);
        btnMenu.setForeground(Color.WHITE);
        btnMenu.setFocusPainted(false);
        btnMenu.setBounds(380, 260, 200, 50);
        fondo.add(btnMenu);

        btnMenu.addActionListener(e -> {
            new menuPrueba().setVisible(true);
            dispose();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new pantallaPresentacion().setVisible(true));
    }
}
