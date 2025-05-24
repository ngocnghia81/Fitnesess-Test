package lib.features.presentation;

// Importing from data layer - this will trigger a dependency violation
import lib.data.UserData;

public class PresentationWithDependency {
    private UserData userData;
    
    public PresentationWithDependency() {
        // Direct dependency on data layer class
        this.userData = new UserData();
    }
    
    public void displayData() {
        // Using data layer directly from presentation
        System.out.println(userData.getUserData());
    }
} 