/* The Actor class represents game elements such as spikes in the GeometryDash game. */

package src;

import javax.swing.*;
import java.awt.*;

class Actor {
    private int x, y; // Coordinates of the actor
    private int width, height; // Dimensions of the actor's image
    private int xSpeed; // Horizontal speed of the actor
    private Image image; // Image representing the actor
    private float speed; // General speed attribute for future use

    // Constructor for creating an Actor with specified image and position
    public Actor(String imageName, int x, int y) {
        this.x = x;
        this.y = y;
        this.xSpeed = 3;

        // Load image from file
        image = new ImageIcon(imageName).getImage();
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
    }

    // Method to update the actor's position
    public void update() {
        x -= xSpeed;
    }

    // Method to draw the actor on the graphics context
    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }

    // Method to check for collision with a Cube object
    public boolean collidesWith(Cube cube) {
        return x < cube.getRight() &&
                x + width > cube.getX() &&
                y < cube.getBottom() &&
                y + height > cube.getY();
    }

    // Getter methods for various properties of the actor
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

    // Setter method for updating the x-coordinate of the actor
    public void setX(int x) {
        this.x = x;
    }

    // Setter method for updating the speed attribute of the actor
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    // Getter method for retrieving the speed attribute of the actor
    public float getSpeed() {
        return speed;
    }
}
