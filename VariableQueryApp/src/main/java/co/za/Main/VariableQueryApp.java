package co.za.Main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class VariableQueryApp extends JFrame {
    
    private JTable applicationTable;
    private DefaultTableModel tableModel;
    private JButton runQueryButton;
    private VariableDatabase database;
    
    // Application table data (separate from database)
    private Object[][] applicationData = {
        {"a", 0L, 0L},
        {"b", 0L, 0L},
        {"c", 0L, 0L}
    };
    
    public VariableQueryApp() {
        initializeGUI();
        initializeDatabase();
    }
    
    private void initializeGUI() {
        setTitle("Variable Query Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Create table model with column names
        String[] columnNames = {"Variable", "Value", "Query"};
        tableModel = new DefaultTableModel(applicationData, columnNames) {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 0) return String.class;
                return Long.class;
            }
            
            @Override
            public boolean isCellEditable(int row, int column) {
                // Only allow editing of the Value column (column 1)
                return column == 1;
            }
        };
        
        applicationTable = new JTable(tableModel);
        applicationTable.setFont(new Font("Arial", Font.PLAIN, 14));
        applicationTable.setRowHeight(25);
        
        // Create scroll pane for table
        JScrollPane scrollPane = new JScrollPane(applicationTable);
        scrollPane.setPreferredSize(new Dimension(400, 150));
        
        // Create Run Query button
        runQueryButton = new JButton("Run Query");
        runQueryButton.setFont(new Font("Arial", Font.BOLD, 14));
        runQueryButton.setPreferredSize(new Dimension(150, 40));
        runQueryButton.addActionListener(new RunQueryListener());
        
        // Create panel for button
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(runQueryButton);
        
        // Create info panel
        JPanel infoPanel = new JPanel(new BorderLayout());
        JLabel infoLabel = new JLabel("<html><center>Enter values in the 'Value' column and click 'Run Query' to update the 'Query' column</center></html>");
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        infoPanel.add(infoLabel, BorderLayout.CENTER);
        
        // Add components to frame
        add(infoPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void initializeDatabase() {
        try {
            database = new VariableDatabase();
            System.out.println("Database initialized successfully.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error initializing database: " + e.getMessage(), 
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private class RunQueryListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Step 1: Get values from application table
                Long aValue = (Long) tableModel.getValueAt(0, 1);
                Long bValue = (Long) tableModel.getValueAt(1, 1);
                Long cValue = (Long) tableModel.getValueAt(2, 1);
                
                System.out.println("Values from application table: a=" + aValue + ", b=" + bValue + ", c=" + cValue);
                
                // Step 2: Create QueryImplementation and populate database
                QueryImplementation queryImpl = new QueryImplementation(aValue, bValue, cValue);
                queryImpl.populateTable();
                
                // Step 3: Fetch query results from variables.db and update application table
                updateApplicationTableFromDatabase();
                
                JOptionPane.showMessageDialog(VariableQueryApp.this, 
                    "Query executed successfully!\nQuery column updated with calculated values.", 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                    
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(VariableQueryApp.this, 
                    "Error running query: " + ex.getMessage(), 
                    "Query Error", 
                    JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }
    
    private void updateApplicationTableFromDatabase() {
        try {
            // Fetch query values from variables.db
            Long queryA = database.getValueFromColumn("a", "query");
            Long queryB = database.getValueFromColumn("b", "query");
            Long queryC = database.getValueFromColumn("c", "query");
            
            // Update application table with query results
            tableModel.setValueAt(queryA, 0, 2); // Update query for variable 'a'
            tableModel.setValueAt(queryB, 1, 2); // Update query for variable 'b'
            tableModel.setValueAt(queryC, 2, 2); // Update query for variable 'c'
            
            System.out.println("Application table updated with query results from database:");
            System.out.println("a query: " + queryA + ", b query: " + queryB + ", c query: " + queryC);
            
        } catch (SQLException ex) {
            throw new RuntimeException("Error fetching query results from database", ex);
        }
    }
    
    @Override
    public void dispose() {
        // Clean up database connection when closing
        if (database != null) {
            try {
                database.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        super.dispose();
    }
    
    public static void main(String[] args) {
        // Set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Create and show GUI
        SwingUtilities.invokeLater(() -> new VariableQueryApp());
    }
}