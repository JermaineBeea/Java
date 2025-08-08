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

    public void populateTable(){
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

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        } finally {
            try {
                // Don't close scanner here as it might be used by GUI
                db.close();
            } catch (SQLException e) {
                System.out.println("Error closing database: " + e.getMessage());
            }
        }
    }
}