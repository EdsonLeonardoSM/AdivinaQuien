/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pruebas.adivinaquien;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.color.ColorSpace;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import misClases.AudioManager;
import misClases.ChatConexion;
import misClases.GeneradorPersonajes;
import misClases.Personaje;

/**
 *
 * @author Edson Leonardo
 */
public class gameplay extends javax.swing.JFrame {
    private Personaje objetoAdivinado;
    private static String ipp;
    private JButton btn;
    private JPanel jPanel2;
    private JTextArea chatArea;
    private JTextField chatInput;
    private ChatConexion chat;
    private Personaje personajeElegido;
    private List<Personaje> tablero;
    private Timer temporizador;
    private int segundosTranscurridos = 0;
    private JLabel lblTiempo;
    private JLabel lblPersonajeElegido;
    private String personajeOponenteNombre = null;
    private boolean soyServidor; // Nueva propiedad
    private String nombreJugador;
    private String nombreOponente;
    private int duracion;

public gameplay(List<Personaje> tableroCompartido, String ipp, boolean soyServidor,String nombreJugador) {
    this.tablero = tablero;
    this.nombreJugador = nombreJugador;
    this.soyServidor = soyServidor;
    this.tablero = tableroCompartido;
    this.ipp=ipp;
    
    
    initComponents(); 
    
    lblPersonajeElegido = new JLabel("Sin personaje");
    lblPersonajeElegido.setBounds(720, 50, 250, 270); 
    lblPersonajeElegido.setHorizontalAlignment(SwingConstants.CENTER);
    lblPersonajeElegido.setVerticalAlignment(SwingConstants.BOTTOM);
    lblPersonajeElegido.setOpaque(true);
    lblPersonajeElegido.setBackground(Color.WHITE);
    lblPersonajeElegido.setBorder(BorderFactory.createTitledBorder("Tu personaje"));
    add(lblPersonajeElegido); 


   
    AudioManager audio = new AudioManager();
    audio.reproducirMusica("/audio/cancion.wav");
         

    lblTiempo = new JLabel("Tiempo: 0 s");
    lblTiempo.setBounds(700, 20, 200, 30);
    add(lblTiempo);

    temporizador = new Timer(1000, e -> {
        segundosTranscurridos++;
        lblTiempo.setText("Tiempo: " + segundosTranscurridos + " s");
    });
    temporizador.start();
    
    setTitle("Adivina Quién");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(1040, 680);
    setLayout(null);
    setLocationRelativeTo(null);
    setResizable(!true);
    

    // Medidas del panel
    int panelWidth = (int)(1040 * 5.0 / 8); 
    int panelHeight = (int)(580); 
    int panelX = 20;                       
    int panelY = (680 - panelHeight) / 2;  

    jPanel2 = new JPanel();
    jPanel2.setLayout(new GridLayout(4, 6, 15, 15)); 
    jPanel2.setBackground(Color.DARK_GRAY);
    jPanel2.setBounds(panelX, panelY, panelWidth, panelHeight);
    jPanel2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 

    add(jPanel2);
    //panel lateral chat
    JPanel chatPanel = new JPanel();
    chatPanel.setLayout(new BorderLayout());
    chatPanel.setBounds(695, 330, 300, 290); 
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
        btnAdivinar.addActionListener(e -> {
                if (personajeOponenteNombre == null) {
                    JOptionPane.showMessageDialog(this, 
                        "Aún no se ha recibido el personaje del oponente.",
                        "Espera", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String personajeSeleccionado = p.getNombre(); // El nombre que el jugador intenta adivinar

                if (personajeSeleccionado.equals(personajeOponenteNombre)) {
                     objetoAdivinado = p;  
                    JOptionPane.showMessageDialog(this, " ¡Adivinaste el personaje del oponente!",
                        "¡Victoria!", JOptionPane.INFORMATION_MESSAGE);
                    // Notificar al oponente que perdió
                    if (chat != null) {
                        chat.enviarMensaje("[GANASTE]");
                    }
                    new ganaste().setVisible(true);
                     chat.cerrar();
                     temporizador.stop(); 
                     this.duracion=segundosTranscurridos;
                     new ganaste().setVisible(true); 
                     dispose();
                    
                } else {
                    JOptionPane.showMessageDialog(this, " Ese no es el personaje correcto.",
                        "Intenta de nuevo", JOptionPane.ERROR_MESSAGE);
                    
                    if (chat != null) {
                        chat.enviarMensaje("[FALLO]");
                    }
                }
            
        });
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
    try {
        if (soyServidor) {
            ServerSocket server = new ServerSocket(12345);
            chatArea.append("Esperando conexión...\n");
            Socket socket = server.accept();
            chatArea.append("Conectado con: " + socket.getInetAddress() + "\n");
            chat = new ChatConexion(socket);
            chat.enviarMensaje("[NOMBRE]:" + nombreJugador); // <--- 🔹 Aquí se envía el nombre
            setChat(chat); 
        } else {
            Socket socket = new Socket(ipp, 12345);
            chatArea.append("Conectado al servidor\n");
            chat = new ChatConexion(socket);
            chat.enviarMensaje("[NOMBRE]:" + nombreJugador); // <--- 🔹 También aquí
            setChat(chat); 
        }

        chat.recibirMensajes(chatArea);
        elegirPersonaje();    

    } catch(Exception ex){
        chatArea.append("Error de conexión: " + ex.getMessage() + "\n");
    }
    setVisible(true);
}


        
private void elegirPersonaje() {
    
    Object[] opciones = {"Modo Lista ", "Modo Cuadrícula "};
    int eleccion = JOptionPane.showOptionDialog(this, 
        "¿Cómo deseas ver los personajes?", 
        "Formato de selección", 
        JOptionPane.DEFAULT_OPTION, 
        JOptionPane.QUESTION_MESSAGE, 
        null, opciones, opciones[0]);

    if (eleccion == 0) {
        mostrarSeleccionLista();     //Modo lista
    } else {
        mostrarSeleccionCuadricula(); //Modo cuadrícula
    }
}
private void mostrarSeleccionCuadricula() {
    JDialog dialogo = new JDialog(this, "Selecciona tu personaje secreto", true);
    dialogo.setSize(800, 650);
    dialogo.setLayout(new BorderLayout(10, 10));

    // Panel de botones
    JPanel panelPersonajes = new JPanel(new GridLayout(4, 6, 10, 10));
    panelPersonajes.setBackground(Color.DARK_GRAY);
    panelPersonajes.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    for (Personaje p : tablero) {
        ImageIcon icon = new ImageIcon(p.getImagen().getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
        JButton boton = new JButton(p.getNombre(), icon);
        boton.setVerticalTextPosition(SwingConstants.BOTTOM);
        boton.setHorizontalTextPosition(SwingConstants.CENTER);
        boton.setForeground(Color.WHITE);
        boton.setBackground(Color.GRAY);
        boton.setFont(new Font("Arial", Font.BOLD, 10));
        
        boton.addActionListener(e -> asignarPersonaje(p, dialogo));

        panelPersonajes.add(boton);
    }

    dialogo.add(panelPersonajes, BorderLayout.CENTER);

    // Botón de aleatorio
    JButton btnAleatorio = new JButton(" Elegir personaje aleatorio");
    btnAleatorio.setFont(new Font("Arial", Font.BOLD, 14));
    btnAleatorio.setBackground(Color.YELLOW);
    btnAleatorio.setFocusPainted(false);
    btnAleatorio.setPreferredSize(new Dimension(0, 50)); 

    btnAleatorio.addActionListener(e -> {
        Personaje aleatorio = tablero.get((int)(Math.random() * tablero.size()));
        asignarPersonaje(aleatorio, dialogo);
    });

    dialogo.add(btnAleatorio, BorderLayout.SOUTH);

    dialogo.setLocationRelativeTo(null);
    dialogo.setVisible(true);
}

private void mostrarSeleccionLista() {
    JDialog dialogo = new JDialog(this, "Selecciona tu personaje", true);
    dialogo.setSize(400, 500);
    dialogo.setLayout(new BorderLayout());

    DefaultListModel<Personaje> modelo = new DefaultListModel<>();
    for (Personaje p : tablero) modelo.addElement(p);

    JList<Personaje> lista = new JList<>(modelo);
    lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    lista.setCellRenderer(new DefaultListCellRenderer() {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            Personaje personaje = (Personaje) value;
            label.setText(personaje.getNombre());
            label.setIcon(new ImageIcon(personaje.getImagen().getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
            label.setHorizontalTextPosition(SwingConstants.RIGHT);
            return label;
        }
    });

    JScrollPane scrollPane = new JScrollPane(lista);
    dialogo.add(scrollPane, BorderLayout.CENTER);

    JPanel botones = new JPanel(new FlowLayout());
    JButton btnElegir = new JButton("Elegir");
    JButton btnAleatorio = new JButton("Aleatorio");

    btnElegir.addActionListener(e -> {
        Personaje seleccionado = lista.getSelectedValue();
        if (seleccionado != null) asignarPersonaje(seleccionado, dialogo);
    });

    btnAleatorio.addActionListener(e -> {
        Personaje aleatorio = tablero.get((int)(Math.random() * tablero.size()));
        lista.setSelectedValue(aleatorio, true);
        asignarPersonaje(aleatorio, dialogo);
    });

    botones.add(btnElegir);
    botones.add(btnAleatorio);
    dialogo.add(botones, BorderLayout.SOUTH);
    dialogo.setLocationRelativeTo(null);
    dialogo.setVisible(true);
}

// 🧠 Método auxiliar para centralizar lógica
private void asignarPersonaje(Personaje p, JDialog dialogo) {
    personajeElegido = p;
    Image img = p.getImagen().getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
    lblPersonajeElegido.setIcon(new ImageIcon(img));
    lblPersonajeElegido.setText(p.getNombre());
    JOptionPane.showMessageDialog(dialogo, "Elegiste: " + p.getNombre());
    dialogo.dispose();

    if (chat != null) {
        chat.enviarMensaje("[PERSONAJE]:" + personajeElegido.getNombre());
    }
}
    
    
public void setChat(ChatConexion chat){
    this.chat = chat;
    chat.recibirMensajes(chatArea);

    // Cuando recibes el personaje del oponente
    chat.onPersonajeRecibido = (String nombrePersonaje) -> {
        this.personajeOponenteNombre = nombrePersonaje;
        System.out.println("Personaje del oponente recibido: " + nombrePersonaje);
    };

    // Cuando recibes el nombre del oponente
    chat.onNombreRecibido = (String nombreRecibido) -> {
        this.nombreOponente = nombreRecibido;
        chat.setNombres(this.nombreJugador, nombreRecibido); // ✅ Aquí
        System.out.println("Nombre del oponente: " + nombreRecibido);
    };

    //  Cuando el oponente gana y tú pierdes
    chat.setOnDerrota(() -> {
        temporizador.stop();
        this.duracion = segundosTranscurridos;
        this.objetoAdivinado = this.personajeElegido; // <- el que te adivinaron

        if (chat != null) chat.cerrar();

        JOptionPane.showMessageDialog(this,
            "El oponente adivinó tu personaje.\nDuración: " + duracion + " segundos.",
            "¡Perdiste!", JOptionPane.ERROR_MESSAGE);

        //new VentanaPerdiste(duracion).setVisible(true);  // 👈 si tu clase `perdiste` lo permite
        dispose();

        // Opcional: Regresar al menú
        // new Menu().setVisible(true);
    });
}

    



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
    new gameplay(tableroCompartido, "127.0.0.1", true,"Jose").setVisible(true);  // Jugador 1
    // new gameplay(tableroCompartido, "127.0.0.1", false).setVisible(true); // Jugador 2
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
