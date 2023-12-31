package src;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

// Class representing the player's cube
class Cube {
    private static final int ROTATION_LIMIT = 90;

    private int x, y;
    private int width, height;
    private int ySpeed;

    private int angle;
    private Image cubeImage;
    private ZRect floor;

    private boolean isRotating;

    // Constructor for initializing the cube
    public Cube(ZRect floor) {
        this.floor = floor;
        width = 50;
        height = 50;
        x = 50;
        y = 340 - height;
        ySpeed = 0;
        angle = 0;

        cubeImage = new ImageIcon("images/square.jpg").getImage();
        isRotating = false;
    }

    // Method for cube jump action
    public void jump() {
        if (y + height >= floor.getY()) {
            ySpeed = -16;
            isRotating = true;
        }
        y += ySpeed;
    }

    // Method for updating cube position and rotation
    public void update() {
        if (y + height < floor.getY()) {
            ySpeed += GeometryDash.GRAVITY;
            y += ySpeed;
        } else {
            y = -height + floor.getY();
            ySpeed = 0;
            if (isRotating) {
                angle += 90;
                if (angle >= ROTATION_LIMIT) {
                    isRotating = false;
                }
            }
        }
    }

    // Method for drawing the cube
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform oldTransform = g2d.getTransform();

        g2d.translate(x + width / 2, y + height / 2);
        g2d.rotate(Math.toRadians(angle));
        g2d.drawImage(cubeImage, -width / 2, -height / 2, width, height, null);

        g2d.setTransform(oldTransform);
    }

    // Getter methods for cube position and size
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
