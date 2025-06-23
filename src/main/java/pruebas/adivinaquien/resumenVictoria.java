
package pruebas.adivinaquien;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Alonso
 */
public class resumenVictoria extends JFrame{
    
    public resumenVictoria(String jugador1, String jugador2, String ganador, String objeto, String duracion){
        setTitle("¡Ganaste la partida!");
        setSize(960, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        ImageIcon fondoImg = new ImageIcon(getClass().getResource("/fondos/fondo2.jpg"));
        JLabel fondo = new JLabel(fondoImg);
        fondo.setLayout(null);
        setContentPane(fondo);
        
        JLabel titulo = new JLabel("¡FELICIDADES!");
        titulo.setFont(new Font("Old English Text MT", Font.BOLD, 50));
        titulo.setForeground(Color.red);
        titulo.setBounds(150, 20, 660, 60);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        fondo.add(titulo);
        
        ImageIcon gif = new ImageIcon(getClass().getResource("/fondos/ratabailando.gif"));
        JLabel animacion = new JLabel(gif);
        animacion.setBounds(370, 90, 200, 200);
        fondo.add(animacion);
        
        JPanel panelResumen = new JPanel();
        panelResumen.setLayout(new GridLayout(5,1,5,5));
        panelResumen.setBounds(320, 300, 320, 150);
        panelResumen.setOpaque(false);
        
        Font fuente = new Font("Arial", Font.BOLD, 18);
        Color colorTexto = Color.WHITE;
         
        JLabel lblJugador1 = new JLabel("Jugador 1: " + jugador1, SwingConstants.CENTER);
        JLabel lblJugador2 = new JLabel("Jugador 2: " + jugador2, SwingConstants.CENTER);
        JLabel lblGanador = new JLabel("ganador: " + ganador, SwingConstants.CENTER);
        JLabel lblObjeto = new JLabel("objeto ganador: " + objeto, SwingConstants.CENTER);
        JLabel lblDuracion = new JLabel("duracion: " + duracion, SwingConstants.CENTER);
        
        for(JLabel lbl: new JLabel[]{lblJugador1,lblJugador2,lblGanador,lblObjeto,lblDuracion}){
            lbl.setFont(fuente);
            lbl.setForeground(colorTexto);
            panelResumen.add(lbl);
        }
        
        fondo.add(panelResumen);
        

        JButton botonJugarOtra = new JButton("Volver a jugar");
        botonJugarOtra.setFont(fuente);
        botonJugarOtra.setBounds(300, 470, 170, 40);
        botonJugarOtra.setBackground(Color.BLACK);
        botonJugarOtra.setForeground(Color.WHITE);
        botonJugarOtra.setFocusPainted(false);
        botonJugarOtra.addActionListener(e -> {
            dispose(); 
            new PantallaUsuario().setVisible(true);
        });
        JButton btnmenu = new JButton("Regresar al menú");
        btnmenu.setFont(fuente);
        btnmenu.setBounds(500, 470, 190, 40);
        btnmenu.setBackground(Color.BLACK);
        btnmenu.setForeground(Color.WHITE);
        btnmenu.setFocusPainted(false);
        btnmenu.addActionListener(e -> {
            dispose(); 
            new menuPrueba().setVisible(true);
        });
        fondo.add(botonJugarOtra);
        fondo.add(btnmenu);
   
        
    }
 public static void main(String[] args) {
        new resumenVictoria("Tú", "Rival", "Pikachu","ola", "03:25").setVisible(true);
    }
}
