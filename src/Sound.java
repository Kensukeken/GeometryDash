package src;
// Sound.java

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream; // Import InputStream

public class Sound {
    public void playSound(String filename) {
        try {
            File soundFile = new File(filename);
            if (!soundFile.exists()) {
                System.err.println("File not found: " + filename);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            playAudioStream(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Add a new method to play sound from InputStream
    public void playSound(InputStream inputStream) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputStream);
            playAudioStream(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Common method to play audio stream
    private void playAudioStream(AudioInputStream audioStream) throws LineUnavailableException, IOException {
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
    }
}
