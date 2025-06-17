
package pruebas.adivinaquien;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Alonso
 */
public class PantallaUsuario extends JFrame{
    
    public PantallaUsuario(){
        setTitle("pantalla de jugador");
        setSize(960,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        //fodito bellako como el que lo diseño
        ImageIcon fondoImg = new ImageIcon(getClass().getResource(""));
        JLabel fondo = new JLabel(fondoImg);
        fondo.setLayout(null);
        setContentPane(fondo);
        
        //sombra
        JLabel sombra = new JLabel("bien Venido");
        sombra.setFont(new Font("Old English Text MT", Font.PLAIN, 77));
        sombra.setForeground(Color.BLACK);
        sombra.setBounds(301,101,500,50);
        fondo.add(sombra);
        
        //texto con color
        JLabel titulo = new JLabel("bien Venido");
        titulo.setFont(new Font("Old English Text MT", Font.BOLD, 77));
        titulo.setForeground(Color.YELLOW);
        titulo.setBounds(300,100,500,50);
        fondo.add(titulo);
        
        //campo de usuario
        JLabel etiqueta = new JLabel("nombre de juagor: ");
        etiqueta.setFont(new Font("Old English Text MT", Font.PLAIN, 24));
        titulo.setForeground(Color.WHITE);
        etiqueta.setBounds(330,180,300,30);
        fondo.add(etiqueta);
        
        //textfield
        JTextField campoUsuario = new JTextField();
        campoUsuario.setFont(new Font("Old English Text MT", Font.PLAIN, 22));
        campoUsuario.setBounds(330,220,300,40);
        fondo.add(campoUsuario);
        
        //boton
        JButton boton = new JButton("continuar");
        boton.setFont(new Font("Old English Text MT",Font.BOLD, 20));
        boton.setBackground(Color.BLACK);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBounds(380,280,200,45);
        fondo.add(boton);
        
       boton.addActionListener(e -> {
           String nombre = campoUsuario.getText().trim();//este pasarlo como parametro en la 
           if(nombre.isEmpty()){
               JOptionPane.showMessageDialog(this, "no seas pendejo, pon algo");
           } else{
               JOptionPane.showMessageDialog(this, "bravo pendejo" + nombre + "!");
               new pantallaDeCarga();//hay que pasar el argumento de nombre y poner el costructor a la ventana que se vaya
               dispose();
           }
       });
        
        setVisible(true);
        
    }
    
    public static void main(String[] args){
        new PantallaUsuario();
    }
}
