package pruebas.adivinaquien;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class ganaste extends JFrame {

    private Clip musicaClip;

    public ganaste() {
        setTitle("🎉 ¡Ganaste la partida! 🎉");
        setSize(960, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Fondo con imagen
        ImageIcon fondoImg = new ImageIcon(getClass().getResource("/fondos/fondo4.jpg"));
        JLabel fondo = new JLabel(fondoImg);
        fondo.setLayout(null);
        setContentPane(fondo);

        // Texto principal con sombra
        JLabel sombra = new JLabel("¡FELICIDADES!");
        sombra.setFont(new Font("Old English Text MT", Font.BOLD, 55));
        sombra.setForeground(Color.BLACK);
        sombra.setBounds(154, 54, 700, 60);
        sombra.setHorizontalAlignment(SwingConstants.CENTER);
        fondo.add(sombra);

        JLabel titulo = new JLabel("¡FELICIDADES!");
        titulo.setFont(new Font("Old English Text MT", Font.BOLD, 55));
        titulo.setForeground(Color.green);
        titulo.setBounds(150, 50, 700, 60);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        fondo.add(titulo);

        // GIF animado
        ImageIcon gif = new ImageIcon(getClass().getResource("/fondos/ratabailando.gif"));
        JLabel animacion = new JLabel(gif);
        animacion.setBounds(330, 130, 300, 200);
        fondo.add(animacion);

        // Botón volver a jugar
        JButton botonReiniciar = new JButton("Volver a jugar");
        botonReiniciar.setFont(new Font("Old English Text MT", Font.BOLD, 22));
        botonReiniciar.setBackground(Color.BLACK);
        botonReiniciar.setForeground(Color.GREEN);
        botonReiniciar.setFocusPainted(false);
        botonReiniciar.setBounds(330, 370, 300, 50);
        fondo.add(botonReiniciar);

        // Botón regresar al menú
        JButton botonRegresar = new JButton("Regresar al menú");
        botonRegresar.setFont(new Font("Old English Text MT", Font.BOLD, 22));
        botonRegresar.setBackground(Color.BLACK);
        botonRegresar.setForeground(Color.WHITE);
        botonRegresar.setFocusPainted(false);
        botonRegresar.setBounds(330, 440, 300, 50);
        fondo.add(botonRegresar);

        botonReiniciar.addActionListener(e -> {
            detenerMusica();
            // Lógica para reiniciar juego
        });

        botonRegresar.addActionListener(e -> {
            detenerMusica();
            new menuPrueba().setVisible(true);
        });

        reproducirMusica("/audio/victory_music.wav");
    }

    private void reproducirMusica(String rutaAudio) {
        try {
            URL sonidoURL = getClass().getResource(rutaAudio);
            if (sonidoURL != null) {
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(sonidoURL);
                musicaClip = AudioSystem.getClip();
                musicaClip.open(audioIn);
                musicaClip.start();
                musicaClip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                System.out.println("Archivo de música no encontrado: " + rutaAudio);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void detenerMusica() {
        if (musicaClip != null && musicaClip.isRunning()) {
            musicaClip.stop();
            musicaClip.close();
        }
    }

    public static void main(String[] args) {
        new ganaste().setVisible(true);
    }
}
