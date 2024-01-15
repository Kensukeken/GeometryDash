package src;

import javax.swing.*;
import java.awt.*;

class Actor {
    private int x, y;
    private int width, height;
    private int xSpeed;
    private Image image;
    private float speed;

    public Actor(String imageName, int x, int y) {
        this.x = x;
        this.y = y;
        this.xSpeed = 3;

        image = new ImageIcon(imageName).getImage();
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
    }

    public void update() {
        x -= xSpeed;
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }

    public boolean collidesWith(Cube cube) {
        return x < cube.getRight() &&
                x + width > cube.getX() &&
                y < cube.getBottom() &&
                y + height > cube.getY();
    }

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

    public void setX(int x) {
        this.x = x;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }
}
