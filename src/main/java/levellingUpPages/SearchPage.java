package levellingUpPages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class SearchPage {
    private final SHAFT.GUI.WebDriver driver;

    public SearchPage (SHAFT.GUI.WebDriver driver){
        this.driver =driver;
    }

    public SearchPage checkSearchResultLink(int index , String expectedLink){
        By searchResultLink = By.xpath("(//article)["+index+"]//h2//a");
        driver.verifyThat().element(searchResultLink).attribute("href")
                .isEqualTo(expectedLink);
        return this;
    }
}
