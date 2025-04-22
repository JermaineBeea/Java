package RobotGame;

import RobotGame.ToyRobot.Direction;
import RobotGame.ToyRobot.Robot;
import java.util.Scanner;
import java.util.Map;

public class Game {

    private Robot robot;

    public Game(int xInitial, int yInitial) {
        this.robot = new Robot(xInitial, yInitial);
    }

    public void playGame(Direction direction, int quantity) {
        robot.Move(direction, quantity);
    }

    public String getGameStatus() {
        return "The robot is now at: " + robot ;
    }
}

class ImplementGame {
    
    private static final Scanner KEYBOARD_INPUT = new Scanner(System.in);
    private static final String EXIT_COMMAND = "end";
    private static final Map<String, Direction> DIRECTION_MAP = Map.of(
        "left", Direction.LEFT,
        "right", Direction.RIGHT,
        "up", Direction.UP,
        "down", Direction.DOWN
    );

    public static void main(String[] args) {
        System.out.println("Welcome to the Robot Game!\nPlease enter your name:");
        String playerName = KEYBOARD_INPUT.nextLine();
        System.out.println("Welcome " + playerName + "!");

        // Initialize robot position
        int x = 0;
        int y = 0;
        
        System.out.println("\nEnter the initial Robot position in format x:y\nPress 'ENTER' to initialize with 0:0");
        String initialPosition = KEYBOARD_INPUT.nextLine();

        if (!initialPosition.isEmpty()) {
            try {
                String[] coordinates = initialPosition.split(":");
                if (coordinates.length != 2) {
                    throw new IllegalArgumentException("Position must be in format x:y");
                }
                x = Integer.parseInt(coordinates[0]);
                y = Integer.parseInt(coordinates[1]);
            } catch (Exception e) {
                System.out.println("Invalid position format. Using default position 0:0.");
                System.out.println("Error: " + e.getMessage());
            }
        }

        Game game = new Game(x, y);
        System.out.println("Robot initialized at position: " + game.getGameStatus());
        
        String userInput = "";
        
        // Main game loop
        while (!EXIT_COMMAND.equalsIgnoreCase(userInput)) {
            System.out.println("\nEnter direction and quantity in format 'direction:steps'");
            System.out.println("Available directions: left, right, up, down");
            System.out.println("Type 'end' to exit the game");
            
            userInput = KEYBOARD_INPUT.nextLine();
            
            if (EXIT_COMMAND.equalsIgnoreCase(userInput)) {
                break;
            }
            
            try {
                String[] command = userInput.split(":");
                if (command.length != 2) {
                    throw new IllegalArgumentException("Command must be in format direction:steps");
                }
                
                Direction direction = DIRECTION_MAP.get(command[0].toLowerCase());
                if (direction == null) {
                    throw new IllegalArgumentException("Invalid direction: " + command[0]);
                }
                
                int steps = Integer.parseInt(command[1]);
                if (steps < 0) {
                    throw new IllegalArgumentException("Steps cannot be negative");
                }
                
                game.playGame(direction, steps);
                System.out.println(game.getGameStatus());
                
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Please try again.");
            }
        }

        System.out.println("Game has been exited by user. Thank you for playing!");
        KEYBOARD_INPUT.close();
    }
}