import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

// Selenium imports
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LoginUiFixture {
    private static WebDriver driver;
    private String username;
    private String password;
    private String action;
    private String element;
    private String errorMessage = "none";
    private String currentValue = "";
    private String resultText = "";
    private File htmlFile;
    
    public LoginUiFixture() {
        try {
            // Create a temporary HTML file for testing
            createTestHtml();
            htmlFile = new File("D:\\Documents\\FitNesseFixtures\\login.html");
            
            // Initialize WebDriver if not already initialized
            initializeDriver();
        } catch (Exception e) {
            System.out.println("Error in constructor: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void initializeDriver() {
        try {
            if (driver == null) {
                System.out.println("Initializing WebDriver...");
                
                // Set up ChromeDriver path
                String chromeDriverPath = "D:\\Documents\\FitNesseFixtures\\chromedriver.exe";
                System.out.println("ChromeDriver path: " + chromeDriverPath);
                System.setProperty("webdriver.chrome.driver", chromeDriverPath);
                
                // Initialize Chrome browser with options
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                
                // Create a new ChromeDriver instance
                driver = new ChromeDriver(options);
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                
                // Open the HTML file
                String fileUrl = "file:///" + htmlFile.getAbsolutePath().replace("\\", "/");
                driver.get(fileUrl);
                System.out.println("Opened browser with login form at: " + fileUrl);
            }
        } catch (Exception e) {
            System.out.println("Error initializing WebDriver: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize WebDriver: " + e.getMessage(), e);
        }
    }
    
    private void createTestHtml() {
        String htmlContent = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "    <title>FitNesse Login Test</title>\n" +
            "    <style>\n" +
            "        body {\n" +
            "            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n" +
            "            background-color: #f5f5f5;\n" +
            "            margin: 0;\n" +
            "            padding: 0;\n" +
            "            display: flex;\n" +
            "            justify-content: center;\n" +
            "            align-items: center;\n" +
            "            height: 100vh;\n" +
            "        }\n" +
            "        .login-container {\n" +
            "            background-color: white;\n" +
            "            border-radius: 10px;\n" +
            "            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);\n" +
            "            padding: 30px;\n" +
            "            width: 350px;\n" +
            "        }\n" +
            "        .login-header {\n" +
            "            text-align: center;\n" +
            "            margin-bottom: 25px;\n" +
            "        }\n" +
            "        .login-header h2 {\n" +
            "            color: #333;\n" +
            "            margin: 0;\n" +
            "        }\n" +
            "        .login-header p {\n" +
            "            color: #666;\n" +
            "            margin-top: 5px;\n" +
            "        }\n" +
            "        .form-group {\n" +
            "            margin-bottom: 20px;\n" +
            "        }\n" +
            "        .form-group label {\n" +
            "            display: block;\n" +
            "            margin-bottom: 5px;\n" +
            "            color: #555;\n" +
            "            font-weight: 500;\n" +
            "        }\n" +
            "        .form-group input {\n" +
            "            width: 100%;\n" +
            "            padding: 10px;\n" +
            "            border: 1px solid #ddd;\n" +
            "            border-radius: 5px;\n" +
            "            font-size: 16px;\n" +
            "            transition: border-color 0.3s;\n" +
            "            box-sizing: border-box;\n" +
            "        }\n" +
            "        .form-group input:focus {\n" +
            "            border-color: #4a90e2;\n" +
            "            outline: none;\n" +
            "        }\n" +
            "        #loginButton {\n" +
            "            background-color: #4a90e2;\n" +
            "            color: white;\n" +
            "            border: none;\n" +
            "            border-radius: 5px;\n" +
            "            padding: 12px;\n" +
            "            width: 100%;\n" +
            "            font-size: 16px;\n" +
            "            cursor: pointer;\n" +
            "            transition: background-color 0.3s;\n" +
            "        }\n" +
            "        #loginButton:hover {\n" +
            "            background-color: #3a7bc8;\n" +
            "        }\n" +
            "        #result {\n" +
            "            margin-top: 20px;\n" +
            "            padding: 10px;\n" +
            "            border-radius: 5px;\n" +
            "            text-align: center;\n" +
            "            font-weight: 500;\n" +
            "            display: none;\n" +
            "        }\n" +
            "        .success {\n" +
            "            background-color: #e7f7e7;\n" +
            "            color: #2e7d32;\n" +
            "            border: 1px solid #c8e6c9;\n" +
            "        }\n" +
            "        .error {\n" +
            "            background-color: #fdecea;\n" +
            "            color: #c62828;\n" +
            "            border: 1px solid #ffcdd2;\n" +
            "        }\n" +
            "        .test-info {\n" +
            "            position: fixed;\n" +
            "            top: 10px;\n" +
            "            right: 10px;\n" +
            "            background-color: rgba(0, 0, 0, 0.7);\n" +
            "            color: white;\n" +
            "            padding: 10px;\n" +
            "            border-radius: 5px;\n" +
            "            font-size: 14px;\n" +
            "        }\n" +
            "    </style>\n" +
            "</head>\n" +
            "<body>\n" +
            "    <div class=\"test-info\">FitNesse Automated Test</div>\n" +
            "    <div class=\"login-container\">\n" +
            "        <div class=\"login-header\">\n" +
            "            <h2>Welcome Back</h2>\n" +
            "            <p>Please login to continue</p>\n" +
            "        </div>\n" +
            "        <div class=\"form-group\">\n" +
            "            <label for=\"username\">Username</label>\n" +
            "            <input type=\"text\" id=\"username\" placeholder=\"Enter your username\">\n" +
            "        </div>\n" +
            "        <div class=\"form-group\">\n" +
            "            <label for=\"password\">Password</label>\n" +
            "            <input type=\"password\" id=\"password\" placeholder=\"Enter your password\">\n" +
            "        </div>\n" +
            "        <button id=\"loginButton\" onclick=\"login()\">Login</button>\n" +
            "        <div id=\"result\"></div>\n" +
            "    </div>\n" +
            "    <script>\n" +
            "        function login() {\n" +
            "            let user = document.getElementById(\"username\").value;\n" +
            "            let pass = document.getElementById(\"password\").value;\n" +
            "            let resultElement = document.getElementById(\"result\");\n" +
            "            \n" +
            "            resultElement.style.display = 'block';\n" +
            "            \n" +
            "            if (user === \"johnd\" && pass === \"m38rmF$\") {\n" +
            "                resultElement.innerText = \"Login successful\";\n" +
            "                resultElement.className = 'success';\n" +
            "            } else {\n" +
            "                resultElement.innerText = \"Login failed\";\n" +
            "                resultElement.className = 'error';\n" +
            "            }\n" +
            "        }\n" +
            "    </script>\n" +
            "</body>\n" +
            "</html>";
            
        try (FileWriter writer = new FileWriter("login.html")) {
            writer.write(htmlContent);
        } catch (IOException e) {
            System.err.println("Error creating HTML file: " + e.getMessage());
        }
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setAction(String action) {
        this.action = action;
    }
    
    public void setElement(String element) {
        // Process element with format id:element_id
        if (element.contains(":")) {
            String[] parts = element.split(":", 2);
            if (parts[0].equals("id")) {
                this.element = parts[1];
            }
        } else {
            this.element = element;
        }
    }
    
    // Variables to track test execution
    private static String currentTestCase = "";
    private static String lastAction = "";
    private static String lastElement = "";
    private static long lastActionTime = 0;
    private static final int DELAY_BETWEEN_ACTIONS = 2000; // 2 seconds between actions
    private static final int DELAY_BETWEEN_TESTCASES = 5000; // 5 seconds between test cases
    
    public String expectedResult() {
        try {
            // Ensure driver is initialized
            if (driver == null) {
                System.out.println("Driver is null, attempting to initialize...");
                initializeDriver();
                if (driver == null) {
                    errorMessage = "Could not initialize WebDriver";
                    return "error";
                }
            }
            
            // Add visual indicator for current action in browser
            addVisualIndicator(action, element);
            
            // Determine if this is a new test case based on username+password combination
            String testCaseId = username + "|" + password;
            String currentAction = action + "|" + element;
            
            // If this is a new test case, add a longer delay
            if (!testCaseId.equals(currentTestCase)) {
                System.out.println("\n=== Starting new test case: " + testCaseId + " ===");
                // Force a delay between test cases
                System.out.println("Waiting " + DELAY_BETWEEN_TESTCASES/1000 + " seconds before starting new test case...");
                Thread.sleep(DELAY_BETWEEN_TESTCASES);
                currentTestCase = testCaseId;
            } 
            // Add delay between actions in the same test case
            else if (!currentAction.equals(lastAction + "|" + lastElement)) {
                System.out.println("Executing action: " + action + " on " + element);
                // Add delay between actions
                System.out.println("Waiting " + DELAY_BETWEEN_ACTIONS/1000 + " seconds between actions...");
                Thread.sleep(DELAY_BETWEEN_ACTIONS);
            }
            
            // Update tracking variables
            lastAction = action;
            lastElement = element;
            lastActionTime = System.currentTimeMillis();
            
            // Add JavaScript to highlight the current element
            highlightElement(element);
            
            // Add a small delay for visual effect
            Thread.sleep(500);
            
            // Take a screenshot if needed
            // takeScreenshot(action + "_" + element);
            
            
            if ("input".equals(action)) {
                // Find the element and input text using Selenium
                WebElement elem = driver.findElement(By.id(element));
                elem.clear();
                elem.sendKeys("username".equals(element) ? username : password);
                System.out.println("Entered " + element + ": " + ("username".equals(element) ? username : password));
                
                errorMessage = "none";
                return elem.getAttribute("value");
            } else if ("click".equals(action)) {
                // Find the element and click it using Selenium
                WebElement elem = driver.findElement(By.id(element));
                elem.click();
                System.out.println("Clicked " + element);
                
                // Short wait for JavaScript to execute
                Thread.sleep(500);
                
                errorMessage = "none";
                return "";
            } else if ("check".equals(action)) {
                // Find the element and get its text using Selenium
                WebElement elem = driver.findElement(By.id(element));
                String result = elem.getText();
                System.out.println("Checking " + element + ": " + result);
                
                // Set error message based on result
                errorMessage = "Login successful".equals(result) ? "none" : "Invalid credentials";
                
                return result;
            }
            return "";
        } catch (Exception e) {
            System.out.println("Error in expectedResult: " + e.getMessage());
            e.printStackTrace();
            errorMessage = e.getMessage();
            return "error";
        }
    }
    
    public String errorMessage() {
        return errorMessage;
    }
    
    // Method to close the browser when done
    public void close() {
        if (driver != null) {
            driver.quit();
            driver = null;
            System.out.println("Closed browser");
        }
    }
    
    // This will be called by FitNesse when the test is done
    public void tearDown() {
        close();
    }
    
    /**
     * Add a visual indicator for the current action in the browser
     */
    private void addVisualIndicator(String action, String element) {
        try {
            String js = "";
            // Add a div to show current action if it doesn't exist
            js += "if (!document.getElementById('fitnesse-action-indicator')) {";
            js += "  var indicator = document.createElement('div');"; 
            js += "  indicator.id = 'fitnesse-action-indicator';"; 
            js += "  indicator.style.position = 'fixed';"; 
            js += "  indicator.style.top = '50px';"; 
            js += "  indicator.style.right = '10px';"; 
            js += "  indicator.style.backgroundColor = 'rgba(74, 144, 226, 0.9)';"; 
            js += "  indicator.style.color = 'white';"; 
            js += "  indicator.style.padding = '10px';"; 
            js += "  indicator.style.borderRadius = '5px';"; 
            js += "  indicator.style.fontFamily = 'Segoe UI, sans-serif';"; 
            js += "  indicator.style.fontSize = '14px';"; 
            js += "  indicator.style.zIndex = '9999';"; 
            js += "  indicator.style.transition = 'opacity 0.3s';"; 
            js += "  document.body.appendChild(indicator);"; 
            js += "}";
            
            // Update the indicator text
            js += "var indicator = document.getElementById('fitnesse-action-indicator');"; 
            js += "indicator.innerHTML = '<strong>Current Action:</strong> " + action + " on <em>" + element + "</em>';"; 
            js += "indicator.style.opacity = '1';"; 
            
            // Execute the JavaScript
            if (driver instanceof org.openqa.selenium.JavascriptExecutor) {
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(js);
            }
        } catch (Exception e) {
            System.out.println("Could not add visual indicator: " + e.getMessage());
        }
    }
    
    /**
     * Highlight the current element being acted upon
     */
    private void highlightElement(String elementId) {
        try {
            // Only proceed if the element exists
            if (driver.findElements(By.id(elementId)).size() > 0) {
                String js = "";
                // Add highlight effect
                js += "var element = document.getElementById('" + elementId + "');"; 
                js += "if (element) {"; 
                js += "  var originalBorder = element.style.border;"; 
                js += "  var originalBackground = element.style.backgroundColor;"; 
                js += "  element.style.border = '2px solid #4a90e2';"; 
                js += "  element.style.backgroundColor = '#e8f4fc';"; 
                js += "  element.style.transition = 'all 0.3s';"; 
                js += "  setTimeout(function() {"; 
                js += "    element.style.border = originalBorder;"; 
                js += "    element.style.backgroundColor = originalBackground;"; 
                js += "  }, 1500);"; 
                js += "}";
                
                // Execute the JavaScript
                if (driver instanceof org.openqa.selenium.JavascriptExecutor) {
                    ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(js);
                }
            }
        } catch (Exception e) {
            System.out.println("Could not highlight element: " + e.getMessage());
        }
    }
}
