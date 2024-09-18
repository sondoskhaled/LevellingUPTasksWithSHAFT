package levellingUpPages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class LandingPage {
    private final SHAFT.GUI.WebDriver driver;
    private String url = "https://duckduckgo.com/";
    private By searchFiled = By.xpath("//input[@id = 'searchbox_input']");
    private By logo = By.xpath("(//a/img)[2]");

    public LandingPage(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
    }

    @Step()
    public LandingPage navigate(){
        driver.browser().navigateToURL(url);
        return this;
    }

    public LandingPage checkPageTitle(String title){
         driver.verifyThat().browser().title().isEqualTo(title);
         return this;
    }

    public LandingPage checkLogoIsDisplayed (){
        driver.verifyThat().element(logo).isVisible();
        return this;
    }

    public SearchPage searchForQuery (String query) {
        driver.element().type(searchFiled,query)
                .keyPress(searchFiled, Keys.ENTER);
        return new SearchPage(driver);
    }

}
