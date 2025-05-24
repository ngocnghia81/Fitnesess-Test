package fixtures;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.File;

/**
 * Java implementation of the DatabaseFixture for testing database operations
 */
public class DatabaseFixture {
    private Connection conn;
    private String username;
    
    /**
     * Constructor - initializes the database connection and creates the database if it doesn't exist
     */
    public DatabaseFixture() {
        try {
            // Load SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");
            
            // Connect to the database (will create it if it doesn't exist)
            String dbPath = new File("D:\\Documents\\FitNesseFixtures\\users.db").getAbsolutePath();
            conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
            
            // Create the table and add test data if needed
            createDbIfNotExists();
        } catch (Exception e) {
            System.err.println("Database initialization error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Create the users table if it doesn't exist and add a default user
     */
    private void createDbIfNotExists() {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS users (username TEXT PRIMARY KEY, role TEXT)");
            
            // Check if the default user exists
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM users WHERE username = 'johnd'");
            if (rs.getInt(1) == 0) {
                // Add the default user
                stmt.execute("INSERT INTO users (username, role) VALUES ('johnd', 'customer')");
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println("Error creating database: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Set the username to check in the database
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * Get the expected role for the current username
     */
    public String expectedRole() {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT role FROM users WHERE username = ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            
            String role = rs.next() ? rs.getString(1) : "none";
            
            rs.close();
            stmt.close();
            return role;
        } catch (Exception e) {
            System.err.println("Error querying database: " + e.getMessage());
            e.printStackTrace();
            return "none";
        }
    }
    
    /**
     * Close the database connection when the fixture is done
     */
    public void tearDown() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Database connection closed");
            }
        } catch (Exception e) {
            System.err.println("Error closing database: " + e.getMessage());
        }
    }
}
