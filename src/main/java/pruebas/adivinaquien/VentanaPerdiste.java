
package pruebas.adivinaquien;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import java.io.*;

/**
 *
 * @author Alonso
 */
public class VentanaPerdiste extends JFrame{
    private JLabel titulo;
    
    public VentanaPerdiste(){
        setTitle("no vales bertha");
        setSize(960,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);  
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.BLACK);
        
        //titulo animado
       
        titulo = new JLabel("estas troll", SwingConstants.CENTER);
        titulo.setFont(new Font("Old English Text MT", Font.BOLD, 69));
        titulo.setForeground(Color.red);
        panel.add(titulo, BorderLayout.NORTH);
        
        //panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.setBackground(Color.BLACK);
        
        //boton reunicair
        JButton botonReinicar = new JButton("volver a jugar");
        botonReinicar.setFont(new Font("Old English Text MT",Font.BOLD, 20));
        botonReinicar.setBackground(Color.RED);
        botonReinicar.setForeground(Color.WHITE);
        
        //bhton pal menu
        JButton botonMenu = new JButton("pal menu");
        botonMenu.setFont(new Font("Old English Text MT",Font.BOLD, 20));
        botonMenu.setBackground(Color.DARK_GRAY);
        botonMenu.setForeground(Color.WHITE);

        botonReinicar.addActionListener(e -> {
            dispose();
            new pantallaDeCarga().setVisible(true);
        });
        
        botonMenu.addActionListener(e -> {
            dispose();
            new pantallaDeCarga().setVisible(true);
        });
        
        panelBotones.add(botonReinicar);
        panelBotones.add(botonMenu);
        
        panel.add(panelBotones, BorderLayout.SOUTH);
        add(panel);
        
        setVisible(true);
        
        reproducirSonido("aki va la puta direccion del audio");
        animarTitulo();
        
    }
    
    private void animarTitulo(){
        Timer timer = new Timer(400, new ActionListener(){
            boolean visible = true;
            
            public void actionPerformed(ActionEvent e){
                titulo.setVisible(visible);
                visible = !visible;
            }
        });
        timer.start();
    }
    
    private void reproducirSonido(String archivoSonido){
        try{
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(archivoSonido));
        } catch(Exception e){
            System.out.println("error papi");
        }
    }
    
    public static void main(String[] args){
        new VentanaPerdiste();
    }
}
