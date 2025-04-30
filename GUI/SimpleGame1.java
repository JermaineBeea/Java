package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SimpleGame1 extends JFrame implements KeyListener {
    
    // Game variables
    private int playerX = 100;    // Starting X position
    private int playerY = 100;    // Starting Y position
    private final int playerSize = 50;  // Size of the player square
    private final int moveSpeed = 5;    // How fast the player moves
    
    // Constructor
    public SimpleGame1() {
        // Set up the window
        setTitle("Simple Java Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);  // Center the window
        
        // Add keyboard listener
        addKeyListener(this);
        
        // Make the window visible
        setVisible(true);
    }
    
    // Paint method to draw the game elements
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        // Clear the screen with a white background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        // Draw the player as a blue square
        g.setColor(Color.BLUE);
        g.fillRect(playerX, playerY, playerSize, playerSize);
        
        // Add some text instructions
        g.setColor(Color.BLACK);
        g.drawString("Use arrow keys to move the blue square", 10, 20);
        
        // Force a repaint to update the screen
        repaint();
    }
    
    // Key listener methods (required by the KeyListener interface)
    @Override
    public void keyPressed(KeyEvent e) {
        // Handle arrow key presses
        int keyCode = e.getKeyCode();
        
        switch (keyCode) {
            case KeyEvent.VK_UP:
                playerY -= moveSpeed;
                break;
            case KeyEvent.VK_DOWN:
                playerY += moveSpeed;
                break;
            case KeyEvent.VK_LEFT:
                playerX -= moveSpeed;
                break;
            case KeyEvent.VK_RIGHT:
                playerX += moveSpeed;
                break;
        }
        
        // Keep the player within the screen bounds
        if (playerX < 0) {
            playerX = 0;
        }
        if (playerX > getWidth() - playerSize) {
            playerX = getWidth() - playerSize;
        }
        if (playerY < 0) {
            playerY = 0;
        }
        if (playerY > getHeight() - playerSize) {
            playerY = getHeight() - playerSize;
        }
        
        // Force repaint to update the player position
        repaint();
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        // Not used, but required by KeyListener interface
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        // Not used, but required by KeyListener interface
    }
    
    // Main method to run the game
    public static void main(String[] args) {
        // Create an instance of the game
        new SimpleGame1();
    }
}