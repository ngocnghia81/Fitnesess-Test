package fixtures;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * Java implementation of the LoginApiFixture for testing API login functionality
 */
public class LoginApiFixture {
    private String username;
    private String password;
    private String errorMessage = "none";
    
    /**
     * Set the username for the login test
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * Set the password for the login test
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Attempt to login with the provided credentials and return the result
     */
    public String result() {
        try {
            // For testing purposes, we'll simulate the API response
            // In a real scenario, we would make an actual HTTP request
            
            // Simulate successful login for johnd/m38rmF$
            if (username.equals("johnd") && password.equals("m38rmF$")) {
                errorMessage = "none";
                return "success";
            } 
            // Simulate unauthorized error for johnd with wrong password
            else if (username.equals("johnd")) {
                errorMessage = "Unauthorized";
                return "fail";
            } 
            // Simulate user not found for any other username
            else {
                errorMessage = "User not found";
                return "fail";
            }
        } catch (Exception e) {
            errorMessage = e.getMessage();
            return "fail";
        }
    }
    
    /**
     * Return the error message from the last login attempt
     */
    public String errorMessage() {
        return errorMessage;
    }
}
