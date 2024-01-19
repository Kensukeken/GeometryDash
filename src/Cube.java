/* ----------------------------------------------------------------------------------------
 * Author: Hia Al Saleh
 * Date: 23/12/2023
 ------------------------------------------------------------------------------------------
 * Cube.java: The Cube class represents the player-controlled cube in the GeometryDash game. 
-------------------------------------------------------------------------------------------*/

package src;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

class Cube {
    private static final int ROTATION_LIMIT = 360;

    private int x, y; // Coordinates of the cube
    private int width, height; // Dimensions of the cube's image
    private int ySpeed; // Vertical speed of the cube
    private int angle; // Rotation angle of the cube
    private Image cubeImage; // Image representing the cube
    private ZRect floor; // Reference to the floor object

    private boolean isRotating; // Flag to indicate if the cube is currently rotating

    // Constructor for creating a Cube with a specified floor reference
    public Cube(ZRect floor) {
        this.floor = floor;
        width = 50;
        height = 50;
        x = 120;
        y = 340 - height;
        ySpeed = 0;
        angle = 0;

        // Load cube image from file
        cubeImage = new ImageIcon("images/square.jpg").getImage();
        isRotating = false;
    }

    // Method to make the cube jump
    public void jump() {
        if (y + height >= floor.getY()) {
            ySpeed = -20;
            isRotating = true;
        }
        y += ySpeed;
    }

    // Method to update the cube's position and rotation
    public void update() {
        if (y + height < floor.getY()) {
            ySpeed += GeometryDash.GRAVITY;
            y += ySpeed;

            // Adjust rotation angle based on the jump velocity
            if (isRotating) {
                angle += 10;
                if (angle >= ROTATION_LIMIT) {
                    isRotating = false;
                    angle = 0; // Reset the angle when rotation is complete
                }
            }
        } else {
            y = -height + floor.getY();
            ySpeed = 0;
            isRotating = false; // Stop rotating when on the floor
        }
    }

    // Method to draw the cube on the graphics context
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform oldTransform = g2d.getTransform();

        // Translate and rotate the cube
        g2d.translate(x + width / 2, y + height / 2);
        g2d.rotate(Math.toRadians(angle));
        g2d.drawImage(cubeImage, -width / 2, -height / 2, width, height, null);

        // Reset the transform to its original state
        g2d.setTransform(oldTransform);
    }

    // Getter methods for various properties of the cube
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRight() {
        return x + width;
    }

    public int getBottom() {
        return y + height;
    }
}