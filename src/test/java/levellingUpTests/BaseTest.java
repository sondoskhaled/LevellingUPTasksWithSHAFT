package levellingUpTests;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {
    SHAFT.GUI.WebDriver driver;

    @BeforeSuite
    public void setupGrid() throws MalformedURLException {
        // Define desired capabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setPlatform(Platform.ANY); // Platform can be ANY for standalone mode
        // Add custom browser options (e.g., headless mode, disable notifications)
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // Start browser maximized
        options.addArguments("--headless"); // Enable headless mode
        capabilities.merge(options);

        // Initialize RemoteWebDriver with the Selenium Server URL
        driver = new SHAFT.GUI.WebDriver(new RemoteWebDriver(new URL("http://localhost:4444/"), capabilities));

    }

    @AfterSuite
    public void tearDown() {
      driver.quit();
    }
}
