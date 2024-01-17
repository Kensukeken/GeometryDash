package src;
// Sound.java

import javax.sound.sampled.*; // Import Sound sampled
import java.io.File; // Import File
import java.io.IOException; // Import IOException
import java.io.InputStream; // Import InputStream

public class Sound {
    private Clip c;

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

    // Added a new method to play sound from InputStream
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
        c = AudioSystem.getClip();
        c.open(audioStream);
        c.start();
    }

    // Method to stop the sound
    public void stopSound() {
        if (c != null) {
            c.stop();
        }
    }
}