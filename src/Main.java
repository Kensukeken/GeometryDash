package src; // IDEA editor

import javax.swing.*;

// The main class to run the game
public class Main {
    public static void main(String[] args) {
        // Create a JFrame for the game
        JFrame frame = new JFrame("Geometry Dash");

        // Create an instance of the GeometryDash game
        GeometryDash game = new GeometryDash();

        // Add the game to the frame
        frame.add(game);

        // Set the frame size, visibility, and close operation
        frame.setSize(GeometryDash.WIDTH, GeometryDash.HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
