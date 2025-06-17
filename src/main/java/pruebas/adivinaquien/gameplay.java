/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pruebas.adivinaquien;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import misClases.ChatConexion;
import misClases.GeneradorPersonajes;
import misClases.Personaje;
/**
 *
 * @author Edson Leonardo
 */
public class gameplay extends javax.swing.JFrame {
    
    private static String ipp;
    private JButton btn;
    private JPanel jPanel2;
    private JTextArea chatArea;
    private JTextField chatInput;
    private ChatConexion chat;
    private Personaje personajeElegido;
    private List<Personaje> tablero;

// Constructor modificado
public gameplay(List<Personaje> tableroCompartido,String ipp) {
    this.tablero = tableroCompartido;
    this.ipp=ipp;
    elegirPersonaje(); //  nuevo método al inicio
    initComponents();  // el resto sigue igual

    setTitle("Adivina Quién");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(1040, 680);
    setLayout(null);
    setLocationRelativeTo(null); // Centra la ventana en pantalla
    setResizable(!true);

    // Medidas del panel
    int panelWidth = (int)(1040 * 5.0 / 8);  // 600
    int panelHeight = (int)(580); // 375
    int panelX = 20;                        // Separado 20 px del borde izquierdo
    int panelY = (680 - panelHeight) / 2;   // Centrado verticalmente

    jPanel2 = new JPanel();
    jPanel2.setLayout(new GridLayout(4, 6, 15, 15)); // Espaciado entre botones
    jPanel2.setBackground(Color.DARK_GRAY);
    jPanel2.setBounds(panelX, panelY, panelWidth, panelHeight);
    jPanel2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Borde interno

    add(jPanel2);
    //panel lateral chat
    JPanel chatPanel = new JPanel();
    chatPanel.setLayout(new BorderLayout());
    chatPanel.setBounds(695, 50, 300, 580); 
    chatPanel.setBackground(Color.LIGHT_GRAY);

    chatArea = new JTextArea();
    chatArea.setEditable(false);
    chatArea.setLineWrap(true);
    chatArea.setWrapStyleWord(true);
    chatArea.setFont(new Font("Arial", Font.PLAIN, 12));
    JScrollPane scroll = new JScrollPane(chatArea);

    chatInput = new JTextField();
    chatInput.addActionListener(e -> {
    String texto = chatInput.getText();
    if(!texto.isEmpty() && chat != null){
        chatArea.append("Tú: " + texto + "\n");
        chat.enviarMensaje(texto);
        chatInput.setText("");
    }
    });
    
    chatPanel.add(scroll, BorderLayout.CENTER);
    chatPanel.add(chatInput, BorderLayout.SOUTH);

    add(chatPanel);
    

    for(Personaje p : this.tablero){
        // Escalar imagen a 64x64
        ImageIcon iconoOriginal = p.getImagen();
        Image imgOriginal = iconoOriginal.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        ImageIcon iconoEscalado = new ImageIcon(imgOriginal);

        // JLabel con imagen y nombre
        JLabel labelImagen = new JLabel(p.getNombre(), iconoEscalado, JLabel.CENTER);
        labelImagen.setHorizontalTextPosition(JLabel.CENTER);
        labelImagen.setVerticalTextPosition(JLabel.BOTTOM);
        labelImagen.setForeground(Color.WHITE);
        labelImagen.setFont(new Font("Arial", Font.BOLD, 10));

        // Botones
        JButton btnAdivinar = new JButton("Adivinar");
        btnAdivinar.setBackground(Color.BLUE);
        btnAdivinar.setForeground(Color.WHITE);
        btnAdivinar.setFont(new Font("Arial", Font.PLAIN, 9));

        JButton btnDescartar = new JButton("Descartar");
        btnDescartar.setBackground(Color.RED);
        btnDescartar.setForeground(Color.WHITE);
        btnDescartar.setFont(new Font("Arial", Font.PLAIN, 9));

        // Panel contenedor con GridBagLayout para alinear todo en columna
        JPanel contenedor = new JPanel(new GridBagLayout());
        contenedor.setBackground(Color.DARK_GRAY);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 2, 2, 2);

        gbc.gridy = 0;
        contenedor.add(labelImagen, gbc);
        gbc.gridy = 1;
        contenedor.add(btnAdivinar, gbc);
        gbc.gridy = 2;
        contenedor.add(btnDescartar, gbc);

        // Acción al presionar "Descartar"
        btnDescartar.addActionListener(e -> {
            contenedor.setBackground(Color.GRAY);
            btnAdivinar.setEnabled(false);
            btnDescartar.setEnabled(false);
            labelImagen.setForeground(Color.LIGHT_GRAY);

            // Escala la imagen a grises
            BufferedImage imgGray = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = imgGray.createGraphics();
            g2d.drawImage(imgOriginal, 0, 0, 64, 64, null);
            g2d.setComposite(AlphaComposite.SrcOver);
            ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
            op.filter(imgGray, imgGray);
            g2d.dispose();
            labelImagen.setIcon(new ImageIcon(imgGray));
        });
        
        jPanel2.add(contenedor);
    }   
    String[] opciones = {"Servidor", "Cliente"};
    int eleccion = JOptionPane.showOptionDialog(this, "¿Modo de conexión?", "Chat LAN",
            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

    try {
        if(eleccion == 0){ // Servidor
            ServerSocket server = new ServerSocket(12345);
            chatArea.append("Esperando conexión...\n");
            Socket socket = server.accept();
            chatArea.append("Conectado con: " + socket.getInetAddress() + "\n");
            chat = new ChatConexion(socket);
        } else { // Cliente
            Socket socket = new Socket(ipp, 12345);
            chatArea.append("Conectado al servidor\n");
            chat = new ChatConexion(socket);
        }
        chat.recibirMensajes(chatArea);
    } catch(Exception ex){
        chatArea.append("Error de conexión: " + ex.getMessage() + "\n");
    }



    setVisible(true);
    }


        
    private void elegirPersonaje() {
    JDialog dialogo = new JDialog(this, "Selecciona tu personaje secreto", true);
    dialogo.setSize(800, 600);
    dialogo.setLayout(new GridLayout(4, 6, 10, 10));
    dialogo.getContentPane().setBackground(Color.DARK_GRAY);

    for (Personaje p : tablero) {
        ImageIcon icon = new ImageIcon(p.getImagen().getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
        JButton boton = new JButton(p.getNombre(), icon);
        boton.setVerticalTextPosition(SwingConstants.BOTTOM);
        boton.setHorizontalTextPosition(SwingConstants.CENTER);
        boton.setForeground(Color.WHITE);
        boton.setBackground(Color.GRAY);
        boton.setFont(new Font("Arial", Font.BOLD, 10));
        boton.addActionListener(e -> {
            personajeElegido = p;
            JOptionPane.showMessageDialog(dialogo, "Elegiste: " + p.getNombre());
            dialogo.dispose();
        });
        dialogo.add(boton);
    }

    dialogo.setLocationRelativeTo(null);
    dialogo.setVisible(true);
}
public void setChat(ChatConexion chat){
    this.chat = chat;
    chat.recibirMensajes(chatArea);
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 960, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
public static void main(String[] args) {
    List<Personaje> tableroCompartido = GeneradorPersonajes.obtenerPersonajesAleatorios();
    new gameplay(tableroCompartido,ipp).setVisible(true); // Jugador 1
    // new gameplay(tableroCompartido).setVisible(true); // Jugador 2 (en otra instancia)
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
