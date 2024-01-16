package src;

import javax.swing.*;

// The main class to run the game
public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Geometry Dash");
        GeometryDash game = new GeometryDash();
        Sound.playSound("stereo-madness.wav");
        frame.add(game);
        frame.setSize(GeometryDash.WIDTH, GeometryDash.HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}