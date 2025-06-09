package pruebas.adivinaquien;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Alonso
 */
public class instrucciones extends JFrame {
    
    public instrucciones(){
        setTitle("instrucciones par gente rara");
        setSize(960, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        
        //imagen
        ImageIcon fondoImg = new ImageIcon(getClass().getResource("/fondos/fondo1.jpg"));
        JLabel fondo = new JLabel(fondoImg);
        fondo.setLayout(new BorderLayout());
        setContentPane(fondo);
        
        //panel transparente
        JPanel panelContenido = new JPanel(new BorderLayout());
        panelContenido.setOpaque(false);
        
        JPanel fondoTexto = new JPanel(new BorderLayout());
        fondoTexto.setBackground(new Color(0, 0, 0, 150));
        fondoTexto.setBorder(BorderFactory.createEmptyBorder(30,50,30,50));
        
        //texto
        JTextArea area = new JTextArea(
            "INSTRUCCIONES: \n\n" + 
            "1. el objetivo es adivinar objeto secreto del otro jugador \n" +
            "2. escribe por el chat lo que creas que es \n" +
            "3. hazlo antes que tu rival \n" +
            "4. gana el primero que acierte el objeto del otro \n \n" + 
            "BUENA SUERTE NENE");
        
        area.setFont(new Font("Old English Text MT",Font.PLAIN, 45));
        area.setEditable(false);
        area.setOpaque(false);
        area.setForeground(Color.BLACK);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        
        
        JScrollPane scroll = new JScrollPane(area);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(true);
        scroll.setBorder(null);
        
        fondoTexto.add(scroll, BorderLayout.CENTER);
        
        
        //boton
        JButton regresarbtn = new JButton("echarse pa tras");
        regresarbtn.setFont(new Font("Old English Text MT",Font.BOLD, 18));
        regresarbtn.setPreferredSize(new Dimension(150,40));
        regresarbtn.addActionListener(e -> dispose());
        
        JPanel panelbtn = new JPanel();
        panelbtn.setOpaque(false);
        panelbtn.add(regresarbtn);
        
        panelContenido.add(fondoTexto, BorderLayout.CENTER);
        panelContenido.add(panelbtn, BorderLayout.SOUTH);
        fondo.add(panelContenido, BorderLayout.CENTER);
        
        setVisible(true);
        
        
    }
}
