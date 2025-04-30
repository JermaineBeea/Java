package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class SimpleGame {
    // Game window components
    private JFrame frame;
    private GamePanel gamePanel;
    
    // Game loop timer
    private Timer gameTimer;
    private final int FPS = 60;
    
    // Player control state tracking
    private Map<Integer, Boolean> keyState = new HashMap<>();
    
    public SimpleGame() {
        // Create and set up the window
        frame = new JFrame("Simple Java Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
        // Create the game panel (where all rendering happens)
        gamePanel = new GamePanel();
        frame.add(gamePanel);
        
        // Size the frame according to the panel's preferred size
        frame.pack();
        frame.setLocationRelativeTo(null);  // Center the window
        
        // Set up keyboard listener
        setupKeyboardControls();
        
        // Start the game loop
        startGameLoop();
        
        // Make the window visible
        frame.setVisible(true);
    }
    
    private void setupKeyboardControls() {
        // Key pressed - set the state to true
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                keyState.put(e.getKeyCode(), true);
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                keyState.put(e.getKeyCode(), false);
            }
        });
        
        // Make sure the frame can get keyboard focus
        frame.setFocusable(true);
        frame.requestFocus();
    }
    
    private void startGameLoop() {
        // Create a timer that triggers the game update/render cycle
        gameTimer = new Timer(1000 / FPS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateGame();
                gamePanel.repaint();
            }
        });
        
        gameTimer.start();
    }
    
    private void updateGame() {
        // Update player position based on keyboard input
        int moveSpeed = 5;
        
        if (isKeyPressed(KeyEvent.VK_UP)) {
            gamePanel.movePlayer(0, -moveSpeed);
        }
        if (isKeyPressed(KeyEvent.VK_DOWN)) {
            gamePanel.movePlayer(0, moveSpeed);
        }
        if (isKeyPressed(KeyEvent.VK_LEFT)) {
            gamePanel.movePlayer(-moveSpeed, 0);
        }
        if (isKeyPressed(KeyEvent.VK_RIGHT)) {
            gamePanel.movePlayer(moveSpeed, 0);
        }
    }
    
    private boolean isKeyPressed(int keyCode) {
        return keyState.getOrDefault(keyCode, false);
    }
    
    // Inner class that handles all the drawing
    private class GamePanel extends JPanel {
        private static final int WIDTH = 800;
        private static final int HEIGHT = 600;
        private static final int PLAYER_SIZE = 50;
        
        private int playerX = 100;
        private int playerY = 100;
        
        // Optionally, create a BufferedImage to use as the player sprite
        private BufferedImage playerSprite;
        
        public GamePanel() {
            setPreferredSize(new Dimension(WIDTH, HEIGHT));
            setBackground(Color.WHITE);
            setDoubleBuffered(true);  // This helps eliminate flickering
            
            // Initialize the player sprite (optional)
            // createPlayerSprite();
        }
        
        public void movePlayer(int dx, int dy) {
            playerX += dx;
            playerY += dy;
            
            // Keep player within bounds
            playerX = Math.max(0, Math.min(WIDTH - PLAYER_SIZE, playerX));
            playerY = Math.max(0, Math.min(HEIGHT - PLAYER_SIZE, playerY));
        }
        
        /*
        // Method to create a custom player sprite (optional)
        private void createPlayerSprite() {
            playerSprite = new BufferedImage(PLAYER_SIZE, PLAYER_SIZE, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = playerSprite.createGraphics();
            
            // Draw a simple character (a blue square with details)
            g2d.setColor(Color.BLUE);
            g2d.fillRect(0, 0, PLAYER_SIZE, PLAYER_SIZE);
            g2d.setColor(Color.WHITE);
            g2d.fillOval(10, 10, 10, 10); // Eye
            g2d.fillOval(30, 10, 10, 10); // Eye
            g2d.drawLine(15, 35, 35, 35); // Mouth
            
            g2d.dispose();
        }
        */
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            
            // Enable anti-aliasing for smoother graphics (optional)
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            // Draw the player
            g2d.setColor(Color.BLUE);
            g2d.fillRect(playerX, playerY, PLAYER_SIZE, PLAYER_SIZE);
            
            // If we have a sprite, use it instead
            /*
            if (playerSprite != null) {
                g2d.drawImage(playerSprite, playerX, playerY, null);
            }
            */
            
            // Add some text instructions
            g2d.setColor(Color.BLACK);
            g2d.drawString("Use arrow keys to move the blue square", 10, 20);
        }
    }
    
    // Main method to run the game
    public static void main(String[] args) {
        // Use the Event Dispatch Thread for Swing applications
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SimpleGame();
            }
        });
    }
}