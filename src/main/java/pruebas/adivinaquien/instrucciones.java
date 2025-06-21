
package pruebas.adivinaquien;

import javax.swing.*;
import java.awt.*;

public class instrucciones extends JFrame {

    public instrucciones() {
        setTitle("Instrucciones del Juego");
        setSize(960, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        // Fondo con imagen
        ImageIcon fondoImg = new ImageIcon(getClass().getResource("/fondos/fondo2.jpg"));
        JLabel fondo = new JLabel(fondoImg);
        fondo.setLayout(null);
        setContentPane(fondo);
        
        JPanel panelCentral = new JPanel();
        panelCentral.setOpaque(false);
        panelCentral.setLayout(new BorderLayout());
        panelCentral.setBounds(150, 100, 660, 350);
        fondo.add(panelCentral);

        // Texto de instrucciones
        JTextArea instrucciones = new JTextArea(
            "1. El objetivo es adivinar el objeto secreto del otro jugador.\n" +
            "2. Escribe por el chat lo que creas que es.\n" +
            "3. ¡Hazlo antes que tu rival!\n" +
            "4. Gana el primero que acierte el objeto del otro.\n\n" +
            "💥 ¡BUENA SUERTE, NENE! 💥"
        );
        instrucciones.setFont(new Font("Old English Text MT", Font.PLAIN, 28));
        instrucciones.setOpaque(false);
        instrucciones.setForeground(Color.pink);
        instrucciones.setEditable(false);
        instrucciones.setHighlighter(null);
        instrucciones.setLineWrap(true);
        instrucciones.setWrapStyleWord(true);
        instrucciones.setAlignmentX(Component.CENTER_ALIGNMENT);
        instrucciones.setAlignmentY(Component.CENTER_ALIGNMENT);
        instrucciones.setBorder(null);
        instrucciones.setFocusable(false);
        instrucciones.setMargin(new Insets(20,20,20,20));
        instrucciones.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        
        JLabel textoC = new JLabel(
            "<html><div style = 'text-align: center; font-family: Old English Text MT; font-size: 24px; color: pink;'>" +
                instrucciones.getText().replaceAll("\n", "<br>") + 
                    "</div></html>"
                    );
        textoC.setHorizontalAlignment(SwingConstants.CENTER);
        panelCentral.add(textoC, BorderLayout.CENTER);
        

        // Botón cerrar
        JButton btnCerrar = new JButton("¡A jugar!");
        btnCerrar.setFont(new Font("Old English Text MT", Font.BOLD, 24));
        btnCerrar.setBackground(Color.BLACK);
        btnCerrar.setForeground(Color.GREEN);
        btnCerrar.setFocusPainted(false);
        btnCerrar.setBounds(380, 480, 200, 50);
        fondo.add(btnCerrar);

        btnCerrar.addActionListener(e -> {
            dispose();
            new menuPrueba().setVisible(true);
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new instrucciones();
    }
}
