package levellingUpTests;

import com.shaft.driver.SHAFT;
import levellingUpPages.LandingPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Task1Tests {
    private SHAFT.GUI.WebDriver driver;

    @Test(description = "Check that the page title is correct")
    public void checkPageTitleIsCorrect(){
        new LandingPage(driver).navigate().checkPageTitle("DuckDuckGo — Privacy, simplified.");
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
