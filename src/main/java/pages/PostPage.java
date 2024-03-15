package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostPage extends ParentPage {
    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }
    @Override
    protected String getRelativeUrl() {
        return "blog/posts/..";
    }

    @FindBy(xpath = "//div[@class='blog-page-views']//span")
    private WebElement counterOfViews;

    public Integer getViewsOfPost() {
        String viewsText = counterOfViews.getText();
        String numericValue = viewsText.replaceAll("[^0-9]", "");
        try {
            return Integer.parseInt(numericValue);
        } catch (NumberFormatException e) {
            System.err.println("Ошибка преобразования в число: " + e.getMessage());
            return null;
        }
    }
}

