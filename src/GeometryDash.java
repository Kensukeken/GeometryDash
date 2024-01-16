/* This program represents the GeometryDash game. */
package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class GeometryDash extends JPanel implements ActionListener {

    // Constants for the game window dimensions and physics
    public static final int WIDTH = 737;
    public static final int HEIGHT = 545;
    public static final int GRAVITY = 1;
    public static final int TIME_TICK = 2;
    public static final int LOGO_SCREEN_DURATION = 5000; // Added 5 seconds before the game starts

    // Game variables
    private Timer timer;
    private int attempt;
    private boolean dead;
    private int score;

    // Sound and game elements
    private Sound sound;
    private Cube cube;
    private List<Actor> spikes;

    // Background and floor elements
    private Image background;
    private ZRect floor;

    // Logo screen elements
    private Image logo;
    private boolean showLogo = true;
    private Timer logoTimer;
    private Timer startGameTimer;
    private Image scaledLogo;

    // Constructor for the GeometryDash class
    public GeometryDash() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        setBackground(Color.blue);

        spikes = new ArrayList<>();

        // Load images for background and logo
        background = new ImageIcon("images/background.png").getImage();
        logo = new ImageIcon("images/geometrydash_logo.png").getImage();
        scaleLogo();

        // Initialize spikes, floor, cube, timer, and sound
        spikes.add(new Actor("images/spike.png", 550, 310));
        spikes.add(new Actor("images/spike.png", 700, 310));
        floor = new ZRect(0, 340, WIDTH, 3);
        cube = new Cube(floor);
        timer = new Timer(TIME_TICK, this);
        sound = new Sound();

        // Set up logo screen timer
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

        // Add key and mouse listeners
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

    // Method to scale the logo image
    private void scaleLogo() {
        int scaledWidth = 737;
        int scaledHeight = 515;
        scaledLogo = logo.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
    }

    // Method to handle mouse clicks
    private void MouseClick() {
        cube.jump();
        sound.playSound("StereoMadness.wav");
    }

    // Method to handle key presses
    private void KeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !dead) {
            cube.jump();
            sound.playSound("StereoMadness.wav");
        } else if (e.getKeyCode() == KeyEvent.VK_R) {
            resetGame();
        }
    }

    // Method to paint the game components
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

    // Method to draw the logo screen
    private void drawLogoScreen(Graphics g) {
        int x = (getWidth() - scaledLogo.getWidth(this)) / 2;
        int y = (getHeight() - scaledLogo.getHeight(this)) / 2;
        g.drawImage(scaledLogo, x, y, this);
    }

    // Method to draw the game components
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
        g.drawString("Press 'R' key to play again. ", 130, 400);
    }

    // Method to handle action events (timer ticks)
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

    // Method to update spike movement and check for collisions
    private void Spike(Actor spike) {
        spike.update();
        if (spike.collidesWith(cube)) {
            dead = true;
        }
        if (spike.getX() + spike.getWidth() <= 0) {
            spike.setX(WIDTH + new java.util.Random().nextInt(200));
            float speedFactor = 1.5f;
            spike.setSpeed(spike.getSpeed() * speedFactor);
        }
    }

    // Method to reset the game state
    private void resetGame() {
        for (Actor spike : spikes) {
            spike.setX(WIDTH + 200);
        }
        score = 0;
        attempt++;
        dead = false;
    }

    // Action listener for the timer before starting the game
    private ActionListener startGameActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            startGame();
        }
    };

    // Method to start the game
    private void startGame() {
        sound.playSound("StereoMadness.wav");
        timer.start(); // Start the main game timer
    }

    // Main method to run the game
    public static void main(String[] args) {
        JFrame frame = new JFrame("Geometry Dash");
        GeometryDash game = new GeometryDash();
        frame.add(game);
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.startGame(); // Call startGame method here to play sound and start the game
    }
}
