package co.za.Main;

import java.sql.*;
import java.util.Scanner;

public class QueryImplementation {

    static Scanner scanner = new Scanner(System.in);
    static VariableDatabase db = new VariableDatabase();

    Long aValue; 
    Long bValue;   
    Long cValue;

    public QueryImplementation(Long a, Long b, Long c) {
        this.aValue = a;
        this.bValue = b;
        this.cValue = c;
    }

    public void runQuery() {
        // Example values for testing
        Long aValue = 5L;   
        Long bValue = 3L;   
        Long cValue = 2L;   

        try {
            // Create & populate the database table
            db.createTable();
            System.out.println("Database table created and populated with default values.");
            
            // Update "value" column
            db.updateValue("a", "value", aValue);
            db.updateValue("b", "value", bValue);
            db.updateValue("c", "value", cValue);
            System.out.println("Variables updated with 'value' data.");

            // Update both "value" and "query" for variables
            db.populateQueryVariables(); // This will set test data for value/query
            System.out.println("'query' column populated for each variable.");
  
            // Export data to files
            db.exportToSQL();


            // Query interactively
            QueryTable();
            System.out.println("Query completed.");
            
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        } finally {
            try {
                scanner.close();
                db.close();
            } catch (SQLException e) {
                System.out.println("Error closing database: " + e.getMessage());
            }
        }
    }

    private static void QueryTable() {
        System.out.print("\nEnter a variable to query (a, b, c): ");
        String variable = scanner.nextLine().trim().toLowerCase();
        
        if (!variable.matches("[abc]")) {
            System.out.println("Invalid variable. Please enter 'a', 'b', or 'c'.");
            return;
        }
        
        System.out.print("Enter a column to query (variable, value, query): ");
        String column = scanner.nextLine().trim().toLowerCase();
        
        if (!column.matches("variable|value|query")) {
            System.out.println("Invalid column. Please enter one of: variable, value, query");
            return;
        }
        
        try {
            if (column.equals("variable")) {
                System.out.println("The value of " + variable + " in column " + column + " is: " + variable);
            } else {
                Long
                 value = db.getValueFromColumn(variable, column);
                System.out.println("The value of " + variable + " in column " + column + " is: " + value);
            }
        } catch (SQLException e) {
            System.out.println("Error querying the database: " + e.getMessage());
        }
    }
}
