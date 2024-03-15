package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends ParentPage {
    public CartPage(WebDriver webDriver) {
        super(webDriver);
    }
    @Override
    protected String getRelativeUrl() {
        return "cart/";
    }

    @FindBy(xpath = "//div[@class='p-cart-table__body']")
    private List<WebElement> listOfProducts;


    @Step("Открыть страницу корзины")
    public CartPage openCartPage() {
        try {
            webDriver.get(baseUrl + getRelativeUrl());
            logger.info("Страница корзины была открыта " + baseUrl + getRelativeUrl());
            return this;
        } catch (Exception e) {
            logger.error("Can not open cart page");
            Assert.fail("Can not open cart page");
            return null;
        }
    }

    public String getCodeOfFirstProduct() {
        if (!listOfProducts.isEmpty()) {
            WebElement firstCard = listOfProducts.get(0);
            WebElement productCodeElement = firstCard.findElement(By.xpath(".//div[@class='p-cart-table__body_row']//span[@class='prod-code-1']"));
            String productCodeText = productCodeElement.getText();
            String numericValue = productCodeText.replaceAll("[^0-9]", "");
            return numericValue;
        } else {
            System.out.println("Список карточек пуст.");
            return "";
        }
    }

}
