package levellingUpTests;

import com.shaft.driver.SHAFT;
import levellingUpPages.LandingPage;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class Task3Tests  {
    SHAFT.GUI.WebDriver driver;
    @Test(description = "check that the first search result link is https://www.selenium.dev/documentation/webdriver/]")
    public void checkFirstSearchResultIsAsExpected (){
        new LandingPage( driver).navigate()
                .searchForQuery("Selenium WebDriver")
                .checkSearchResultLink(1,"https://www.selenium.dev/documentation/webdriver/");
    }

    @BeforeMethod(description = "Setup browser instance.")
    public void setUp() throws MalformedURLException {
       driver = driverFactory("remote" , "chrome");
    }

    @AfterMethod(description = "Tear down browser instance.")
    public void tearDown(){
        driver.quit();
    }

    public SHAFT.GUI.WebDriver driverFactory(String type , String browser) throws MalformedURLException {
        if(type.equals("local")){
            return switch (browser) {
                case "chrome" -> new SHAFT.GUI.WebDriver(new ChromeDriver());
                case "firefox" -> new SHAFT.GUI.WebDriver(new FirefoxDriver());
                default -> throw new InvalidArgumentException("the " + browser + " is not supported locally.");
            };

        }
        else if(type.equals("remote"))
        {
            return buildRemoteDriver(browser);
        }
        else {
            throw new InvalidArgumentException(type + " is invalid. choose 'local' or 'remote'");
        }
    }

    private static SHAFT.GUI.WebDriver buildRemoteDriver(String browser) throws MalformedURLException {
        var DOCKER_GRID_HUB_URI = new URL("http://192.168.1.3:4444/wd/hub");
        SHAFT.GUI.WebDriver driver1;

        switch (browser)
        {
            case "chrome":
                // Define desired capabilities
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setBrowserName("chrome");
                capabilities.setPlatform(Platform.LINUX); // Platform can be ANY for standalone mode
                // Add custom browser options (e.g., headless mode, disable notifications)
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized"); // Start browser maximized
                //options.addArguments("--headless"); // Enable headless mode
                capabilities.merge(options);
                driver1 = new SHAFT.GUI.WebDriver(new RemoteWebDriver(DOCKER_GRID_HUB_URI, capabilities));
                break;
            case "firefox":
                // Define desired capabilities
                DesiredCapabilities capabilities1 = new DesiredCapabilities();
                capabilities1.setBrowserName("firefox");
                capabilities1.setPlatform(Platform.LINUX); // Platform can be ANY for standalone mode
                // Add custom browser options (e.g., headless mode, disable notifications)
                FirefoxOptions options1 = new FirefoxOptions();
                options1.addArguments("--start-maximized"); // Start browser maximized
                //options1.addArguments("--headless"); // Enable headless mode
                capabilities1.merge(options1);
                driver1 = new SHAFT.GUI.WebDriver(new RemoteWebDriver(DOCKER_GRID_HUB_URI, capabilities1));
                break;

            default:
                throw new InvalidArgumentException("the "+browser+ " is not supported remotely.");

        }
        return driver1;
    }
}
