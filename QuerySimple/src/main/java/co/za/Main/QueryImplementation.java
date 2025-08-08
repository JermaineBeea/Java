package co.za.Main;

import java.sql.SQLException;
import java.util.Scanner;

public class QueryImplementation {

    private Scanner scanner = new Scanner(System.in);
    private VariableDatabase db = new VariableDatabase();

    private Long aValue; 
    private Long bValue;   
    private Long cValue;

    public QueryImplementation(Long a, Long b, Long c) {
        this.aValue = a;
        this.bValue = b;
        this.cValue = c;
    }

    public void createAndPopulateTable() {
        try {
            // Create and populate the database table
            db.createTable();
            System.out.println("Database table created and populated with default values.");
            
            // Update "value" column for variables a, b, c
            db.updateValue("a", "value", aValue);
            db.updateValue("b", "value", bValue);
            db.updateValue("c", "value", cValue);
            System.out.println("Variables updated with 'value' data.");

            // Populate "query" column with test data for each variable
            db.populateQueryVariables();
            System.out.println("'query' column populated for each variable.");

            // Export data to SQL file or external output
            db.exportToSQL();

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    /**
     * Start the interactive query loop.
     * User can query variables and columns until they type "exit".
     */
    public void runQuery() {
        System.out.println("You can now query the database. Type 'exit' to quit.");
        while (true) {
            if (!queryTable()) {
                System.out.println("Exiting query loop.");
                break;
            }
        }
    }

    /**
     * Query the user for a variable and a column, then display the value.
     * 
     * @return true to continue loop, false to exit
     */
    private boolean queryTable() {
        System.out.print("\nEnter a variable to query (a, b, c) or 'exit' to quit: ");
        String variable = scanner.nextLine().trim().toLowerCase();

        if (variable.isEmpty() || variable.equals("exit")) {
            return false;  // Exit loop
        }

        if (!variable.matches("[abc]")) {
            System.out.println("Invalid variable. Please enter 'a', 'b', or 'c'.");
            return true;  // Continue loop
        }

        System.out.print("Enter a column to query (variable, value, query) or 'exit' to quit: ");
        String column = scanner.nextLine().trim().toLowerCase();

        if (column.isEmpty() || column.equals("exit")) {
            return false;  // Exit loop
        }

        if (!column.matches("variable|value|query")) {
            System.out.println("Invalid column. Please enter one of: variable, value, query");
            return true;  // Continue loop
        }

        try {
            if (column.equals("variable")) {
                // For 'variable' column, just print the variable name itself
                System.out.println("The value of " + variable + " in column " + column + " is: " + variable);
            } else {
                // For 'value' or 'query' columns, fetch from database
                Long value = db.getValueFromColumn(variable, column);
                System.out.println("The value of " + variable + " in column " + column + " is: " + value);
            }
        } catch (SQLException e) {
            System.out.println("Error querying the database: " + e.getMessage());
        }

        return true; // Continue loop
    }

    /**
     * Close the scanner and database connection resources.
     * Should be called once user finishes all interactions.
     */
    public void close() {
        try {
            if (scanner != null) {
                scanner.close();
            }
        } catch (Exception e) {
            System.out.println("Error closing scanner: " + e.getMessage());
        }

        try {
            if (db != null) {
                db.close();
            }
        } catch (SQLException e) {
            System.out.println("Error closing database: " + e.getMessage());
        }
    }
}
