/* ----------------------------------------------
 * Author: Hia Al Saleh
 * Date: 17/01/2024
 ------------------------------------------------
 * Sound.java: Class representing in-game sounds.
------------------------------------------------- */
package src;

import javax.sound.sampled.*; // Import Sound sampled
import java.io.File; // Import File
import java.io.IOException; // Import IOException
import java.io.InputStream; // Import InputStream

public class Sound {
    private Clip c;

    // Method to play sound from a file
    public void playSound(String filename) {
        try {
            // Create a File object representing the sound file
            File soundFile = new File(filename);

            // Check if the file exists
            if (!soundFile.exists()) {
                System.err.println("File not found: " + filename);
                return;
            }

            // Get an AudioInputStream from the sound file
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);

            // Call the common method to play the audio stream
            playAudioStream(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Added a new method to play sound from InputStream
    public void playSound(InputStream inputStream) {
        try {
            // Get an AudioInputStream from the InputStream
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputStream);

            // Call the common method to play the audio stream
            playAudioStream(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Common method to play audio stream
    private void playAudioStream(AudioInputStream audioStream) throws LineUnavailableException, IOException {
        // Get a Clip to play the audio
        c = AudioSystem.getClip();

        // Open the Clip with the audio stream
        c.open(audioStream);

        // Start playing the audio
        c.start();
    }

    // Method to stop the sound
    public void stopSound() {
        if (c != null) {
            // Stop the Clip if it's currently playing
            c.stop();
        }
    }
}
