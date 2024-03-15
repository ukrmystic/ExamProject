package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultsPage extends ParentPage {

    @FindBy(xpath = "//div[@class='product-card'][1]")
    private WebElement firstProductCard;
    @FindBy(xpath = "//div[@class='search-result-list']")
    private List<WebElement> searchProductList;

    public SearchResultsPage(WebDriver webDriver) {
        super(webDriver);
    }
    @Override
    protected String getRelativeUrl() {
        return "catalog/search/";
    }

    @Step("Нажать на первую карточку товара результатов поиска")
    public SearchResultsPage clickOnFirstCard() {
        clickOnElement(firstProductCard);
        return new SearchResultsPage(webDriver);
    }

}
