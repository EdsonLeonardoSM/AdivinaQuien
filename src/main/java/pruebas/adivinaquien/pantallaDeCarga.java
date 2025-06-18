package pruebas.adivinaquien;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Alonso
 */
public class pantallaDeCarga extends JFrame{
    private String nombreJugador;
    public pantallaDeCarga(String nombreJugador){
        this.nombreJugador=nombreJugador;
        setTitle("esperando coneXion... ");
        setSize(960,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(!true);
        
        //fondo
        ImageIcon fondoImg = new ImageIcon(getClass().getResource("/fondos/fondo3.jpg"));
        JLabel fondo = new JLabel(fondoImg);
        fondo.setLayout(new BorderLayout());
        setContentPane(fondo);
        
        //panel del gif
        JPanel panelCentral = new JPanel(new GridBagLayout());
        panelCentral.setOpaque(false);
        
        //gif
        JLabel giftLabel = new JLabel(new ImageIcon("/fondos/fondo3.jpg"));
        panelCentral.add(giftLabel);
        
        //btn pa tras
        JButton botonRegresar = new JButton("sas pa tras");
        botonRegresar.setFont(new Font("Old English Text MT",Font.BOLD, 18));
        botonRegresar.setPreferredSize(new Dimension(150,40));
        botonRegresar.setBackground(Color.BLACK);
        botonRegresar.setForeground(Color.WHITE);
        botonRegresar.setFocusPainted(false);
        botonRegresar.setBorderPainted(false);
        botonRegresar.addActionListener(e -> dispose());
        
        JPanel panelBoton = new JPanel();
        panelBoton.setOpaque(false);
        panelBoton.add(botonRegresar);
        
        fondo.add(panelCentral, BorderLayout.CENTER);
        fondo.add(panelBoton, BorderLayout.SOUTH);
        
        setVisible(true);
    }
    
}
