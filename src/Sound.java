package src;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {
    public static void playSound(String filename) {
        try {
            File soundFile = new File(filename);
            if (!soundFile.exists()) {
                System.err.println("File not found: " + filename);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
