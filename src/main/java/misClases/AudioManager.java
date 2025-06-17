package misClases;

import javax.sound.sampled.*;
import java.io.IOException;

public class AudioManager {

    private Clip clip;
    private FloatControl volumeControl;

    public void reproducirMusica(String ruta) {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(getClass().getResource(ruta));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            // Control de volumen
            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void pausarMusica() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public void reanudarMusica() {
        if (clip != null && !clip.isRunning()) {
            clip.start();
        }
    }

    public void ajustarVolumen(float valor) {
        // valor en decibeles (entre -80.0 y 6.0 normalmente)
        if (volumeControl != null) {
            volumeControl.setValue(valor); 
        }
    }

    public void detenerMusica() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }
}
