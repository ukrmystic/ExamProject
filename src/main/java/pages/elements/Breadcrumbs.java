package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;

import java.util.List;

public class Breadcrumbs  extends CommonActionsWithElements {

    @FindBy(xpath = "//div[@class='breadcrumbs']")
    public WebElement breadcrumbs;
    @FindBy(xpath = "//ul[@class='breadcrumbs__list']")
    public List<WebElement> breadcrumbsList;
    @FindBy(xpath = "//ul[@class='breadcrumbs__list']//li")
    public WebElement breadcrumbsCategory;
    public Breadcrumbs(WebDriver webDriver) {
        super(webDriver);
    }
    public boolean isWordPresentInBreadcrumbs(String word) {
        String breadcrumbsText = breadcrumbsCategory.getText();
        return breadcrumbsText.contains(word);
    }
}
