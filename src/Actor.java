package src;

import javax.swing.*;
import java.awt.*;

// Class representing in-game actors, like spikes
class Actor {
    private int x, y;
    private int width, height;
    private int xSpeed;
    private Image image;

    // Constructor for initializing actors
    public Actor(String imageName, int x, int y) {
        this.x = x;
        this.y = y;
        this.xSpeed = 3;

        image = new ImageIcon(imageName).getImage();
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
    }

    // Method for updating actor position
    public void update() {
        x -= xSpeed;
    }

    // Method for drawing actors
    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }

    // Method for checking collisions with the cube
    public boolean collidesWith(Cube cube) {
        return x < cube.getRight() &&
                x + width > cube.getX() &&
                y < cube.getBottom() &&
                y + height > cube.getY();
    }

    // Getter methods for actor position and size
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getRight() {
        return x + width;
    }

    public int getBottom() {
        return y + height;
    }

    // Setter method for setting actor position
    public void setX(int x) {
        this.x = x;
    }
}
