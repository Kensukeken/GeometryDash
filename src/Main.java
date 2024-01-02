package src;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Geometry Dash");
        GeometryDash game = new GeometryDash();
        frame.add(game);
        frame.setSize(GeometryDash.WIDTH, GeometryDash.HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
