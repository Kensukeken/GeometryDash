/* ------------------------------------------------------------------------------------------
 * Author: Kensukeken
 * Date: 25/12/2023
---------------------------------------------------------------------------------------------
 * ZRect.java: The ZRect class represents a simple rectangular shape in the GeometryDash game.
--------------------------------------------------------------------------------------------- */

package src;

class ZRect {
    private int x, y; // Coordinates of the top-left corner of the rectangle
    private int width, height; // Dimensions of the rectangle

    // Constructor for creating a ZRect with specified position and size
    public ZRect(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // Getter methods for retrieving the properties of the rectangle
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
