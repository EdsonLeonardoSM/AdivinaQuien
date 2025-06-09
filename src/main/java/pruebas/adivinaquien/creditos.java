
package pruebas.adivinaquien;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Alonso
 */
public class creditos extends JFrame{
    
    private JPanel panelScroll;
    private int yTexto = 600;
    
    public creditos(){
        setTitle("creditos");
        setSize(960, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        //panel
        JPanel mainPanel = new JPanel(null);
        mainPanel.setBackground(Color.BLACK);
        setContentPane(mainPanel);
        
        panelScroll = new JPanel();
        panelScroll.setBackground(Color.BLACK);
        panelScroll.setLayout(null);
        panelScroll.setBounds(0, yTexto, 960, 1000);
        
        JLabel creditos = new JLabel("<html><div style= 'text-align: center;'>"
            + "<h2 style='color:white;'> creditos de juego </h2>"
            + "<p style='color:white;'><b>progracion: </b> root y leo y pp </b>"
            + "<p style='color:white;'><b>diseño: </b> root y leo y pp </b>"
            + "<p style='color:white;'><b>musica: </b> root y leo y pp </b>"
            + "<p style='color:white;'><b>agradecimientos: </b> mi mama y gina</b>"
            + "<p style='color:white;'><b>herramientas: </b> java swing, chatgpt, GIMP</b>"
            + "<p style='color:white;'> 2025 - todos los derechos reservados </b>"
            + "</div></html>");
        
        creditos.setFont(new Font("Old English Text MT", Font.PLAIN, 35));
        creditos.setBounds(200, 0, 600, 1000);
        panelScroll.add(creditos);
        
        mainPanel.add(panelScroll);
        
        //boton salirse al quiote
        JButton back = new JButton("ECHARSE PA TRAS");
        back.setBounds(390, 520, 180, 35);
        back.addActionListener(e -> dispose());
        mainPanel.add(back);
        
        Timer timer = new Timer(10, new ActionListener(){
        public void actionPerformed(ActionEvent e){
            yTexto -=1;
            panelScroll.setLocation(0, yTexto);
            if(yTexto < -creditos.getHeight()){
                ((Timer) e.getSource()).stop();
            }
        }
    });
    timer.start();
    }
}
