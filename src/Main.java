/*----------------------------------------------------------------------------------------------------------------------------------
 * Author: Kensukeken
 * Date: 03/01/2024
 * Feel free to see my github: https://github.com/Kensukeken/GeometryDash. [I did not put my real name on there, just a nickname :)].
------------------------------------------------------------------------------------------------------------------------------------
 * Main.java - The main class to run the game
------------------------------------------------------------------------------------------------------------------------------------ */
package src;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Geometry Dash"); // Create a new JFrame (window) for the game
        GeometryDash game = new GeometryDash(); // Create an instance of the GeometryDash game
        frame.add(game); // Add my game panel to the JFrame
        frame.setSize(GeometryDash.WIDTH, GeometryDash.HEIGHT); // Set the size of my JFrame to the dimensions specified
                                                                // in GeometryDash class
        frame.setVisible(true); // Make my JFrame visible
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set my default close operation to exit the application
                                                              // when my JFrame is closed.
    }
}
