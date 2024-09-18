package levellingUpTests;

import com.shaft.driver.SHAFT;
import levellingUpPages.LandingPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Task2Tests {
    private SHAFT.GUI.WebDriver driver;

    @Test(description = "Check that the logo is displayed")
    public void checkLogoIsDisplayed(){
        new LandingPage(driver).navigate().checkLogoIsDisplayed();
    }

    @BeforeMethod(description = "Setup browser instance.")
    public void setUp(){
        driver = new SHAFT.GUI.WebDriver();
    }

    @AfterMethod(description = "Tear down browser instance.")
    public void tearDown(){
        driver.quit();
    }
}
