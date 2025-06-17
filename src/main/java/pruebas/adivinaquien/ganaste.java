
package pruebas.adivinaquien;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Alonso
 */
public class ganaste extends JFrame{
    public ganaste(){//aqui va el nombre, el string del nombre
        setTitle("chiken chiken dinner");
        setSize(960,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        //fondo de pantalla 
        ImageIcon fondoImg = new ImageIcon(getClass().getResource("/fondos/fondo1.jpg"));
        JLabel fondo = new JLabel(fondoImg);
        fondo.setLayout(null);
        setContentPane(fondo);
        
        //texto de ganaste asi bien perro
        JLabel titulo = new JLabel("FELICIDADES PAPITO");
        titulo.setFont(new Font("Old English Text MT", Font.BOLD, 77));
        titulo.setForeground(Color.YELLOW);
        titulo.setBounds(150,50,700,60);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        fondo.add(titulo);
        
        // animacion del gif 
        ImageIcon gif = new ImageIcon(getClass().getResource("/fondos/fondo1.jpg"));//gifffff
        JLabel animacion = new JLabel(gif);
        titulo.setBounds(330,130,300,200);
        fondo.add(animacion);
        
        JButton botonReiniciar = new JButton("volver a jugar");
        botonReiniciar.setFont(new Font("Old English Text MT",Font.BOLD, 20));
        botonReiniciar.setBackground(Color.BLACK);
        botonReiniciar.setForeground(Color.WHITE);
        botonReiniciar.setFocusPainted(false);
        botonReiniciar.setBounds(330,370,300,50);
        fondo.add(botonReiniciar);
        
        JButton botonRegresar = new JButton("pa tras");
        botonReiniciar.setFont(new Font("Old English Text MT",Font.BOLD, 20));
        botonReiniciar.setBackground(Color.BLACK);
        botonReiniciar.setForeground(Color.WHITE);
        botonReiniciar.setFocusPainted(false);
        botonReiniciar.setBounds(330,440,300,50);
        fondo.add(botonRegresar);
        
        botonReiniciar.addActionListener(e -> {
            //volver a donde putas deba de regresar
        });
        
        botonRegresar.addActionListener(e -> {
            //new menuPrueba();
        });
    }
}
