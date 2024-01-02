package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

// The main class that represents the Geometry Dash game
public class GeometryDash extends JPanel implements ActionListener {

    public static final int WIDTH = 737; // Width of the game window
    public static final int HEIGHT = 545; // Height of the game window

    // Constants for the game physics
    public static final int GRAVITY = 1;
    public static final int JUMP_HEIGHT = 16;
    public static final int TIME_TICK = 10;
    public static final int LOGO_SCREEN_DURATION = 5000; // Duration to show the game logo (5 seconds)

    private Timer timer; // Timer for the game loop
    private int attempt; // Number of attempts to complete the game
    private boolean dead; // Indicates if the player has lost
    private int score; // Player's score

    private Cube cube; // The player's cube
    private List<Actor> spikes; // List of spike obstacles

    private Image background; // Background image of the game
    private ZRect floor; // Represents the ground in the game

    private Image logo; // Game logo image
    private boolean showLogo = true; // Indicates whether to show the game logo

    private Timer logoTimer; // Timer for displaying the game logo
    private Timer startGameTimer; // Timer to delay the start of the game

    private Image scaledLogo; // Scaled version of the game logo

    // Constructor for the GeometryDash class
    public GeometryDash() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);

        setBackground(Color.blue);

        spikes = new ArrayList<>();

        background = new ImageIcon("images/background.png").getImage();
        logo = new ImageIcon("images/logo-game.png").getImage();
        scaleLogo();

        spikes.add(new Actor("images/spike.png", 550, 310));
        spikes.add(new Actor("images/spike.png", 700, 310));

        floor = new ZRect(0, 340, WIDTH, 3);
        cube = new Cube(floor);

        timer = new Timer(TIME_TICK, this);

        // Timer to show the logo and then start the game
        logoTimer = new Timer(LOGO_SCREEN_DURATION, e -> {
            showLogo = false;
            repaint();

            // Start a timer for the delay before starting the game
            startGameTimer = new Timer(1000, startGameActionListener);
            startGameTimer.setRepeats(false);
            startGameTimer.start();
        });
        logoTimer.setRepeats(false);
        logoTimer.start();

        // Event listeners for keyboard and mouse input
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                KeyPressed(e);
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MouseClick();
            }
        });
    }

    // Method to scale the game logo
    private void scaleLogo() {
        int scaledWidth = 700;  // Set the desired width
        int scaledHeight = 300; // Set the desired height
        scaledLogo = logo.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
    }

    // Method to handle mouse clicks
    private void MouseClick() {
        cube.jump();
    }

    // Method to handle key presses
    private void KeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !dead) {
            cube.jump();
        } else if (e.getKeyCode() == KeyEvent.VK_R) {
            resetGame();
        }
    }

    // Method to paint the game screen
    public void paint(Graphics g) {
        super.paint(g);

        if (showLogo) {
            drawLogoScreen(g);
        } else {
            Image bufferImage = createImage(WIDTH, HEIGHT);
            Graphics bufferGraphics = bufferImage.getGraphics();

            bufferGraphics.drawImage(background, 0, 0, this);

            if (!dead) {
                drawGame(bufferGraphics);
            } else {
                drawGameOver(bufferGraphics);
            }
            g.drawImage(bufferImage, 0, 0, this);
        }
    }

    // Method to draw the game logo screen
    private void drawLogoScreen(Graphics g) {
        int x = (getWidth() - scaledLogo.getWidth(this)) / 2;
        int y = (getHeight() - scaledLogo.getHeight(this)) / 2;
        g.drawImage(scaledLogo, x, y, this);
    }

    // Method to draw the main game screen
    private void drawGame(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(floor.getX(), floor.getY(), floor.getWidth(), floor.getHeight());

        for (Actor spike : spikes) {
            spike.draw(g);
        }

        cube.draw(g);

        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 40, 40);
        g.drawString("Attempt: " + attempt, 600, 40);
    }

    // Method to draw the game over screen
    private void drawGameOver(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 40));
        g.drawString("Game Over!", 250, 200);
        g.drawString("Your Score: " + score, 225, 300);
    }

    // Method called on each game loop iteration
    public void actionPerformed(ActionEvent e) {
        if (!dead) {
            updateGame();
            repaint();
        }
    }

    // Method to update the game state
    private void updateGame() {
        cube.update();
        for (Actor spike : spikes) {
            Spike(spike);

            if (spike.getX() < cube.getRight() && spike.getRight() > cube.getX()
                    && spike.getY() < cube.getBottom() && spike.getBottom() > cube.getY()) {
            }
        }
        score++;
    }

    // Method to update the spike position and check for collisions
    private void Spike(Actor spike) {
        spike.update();
        if (spike.collidesWith(cube)) {
            dead = true;
        }
        if (spike.getX() + spike.getWidth() <= 0) {
            spike.setX(WIDTH + 5);
        }
    }

    // Method to reset the game state
    private void resetGame() {
        for (Actor spike : spikes) {
            spike.setX(WIDTH + new java.util.Random().nextInt(200));
        }
        score = 0;
        attempt++;
        dead = false;
    }

    // ActionListener for starting the game after the logo screen
    private ActionListener startGameActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            startGame();
        }
    };

    // Method to start the game
    private void startGame() {
        timer.start(); // Start the main game timer
    }

    // The entry point of the program
    public static void main(String[] args) {
        JFrame frame = new JFrame("Geometry Dash");
        GeometryDash game = new GeometryDash();
        frame.add(game);
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
