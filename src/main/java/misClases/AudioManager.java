package misClases;

import javax.sound.sampled.*;
import java.io.IOException;

public class AudioManager {

    private Clip clip;
    private FloatControl volumeControl;
    private float currentVolume = 0.09f;//aqui muevele si quieres cambiar el volumen

    public void reproducirMusica(String ruta) {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(getClass().getResource(ruta));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            

            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            ajustarVolumen(currentVolume); 
            
            clip.loop(Clip.LOOP_CONTINUOUSLY);

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
        if (volumeControl == null) return;
        

        float minDB = -40.0f;
        float maxDB = 6.0f;
        float db = minDB + (maxDB - minDB) * valor;
        

        db = Math.max(minDB, Math.min(db, maxDB));
        volumeControl.setValue(db);
        
        currentVolume = valor; 
    }

    public void detenerMusica() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }

    public float getCurrentVolume() {
        return currentVolume;
    }
}