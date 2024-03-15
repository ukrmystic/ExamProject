package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends ParentPage {

    @FindBy(xpath = "//div[@class='prod-p-main']//h1[@class='prod-p-main__title']")
    public WebElement titleOfProductCard;
    @FindBy(xpath = "//button[contains(@class,'add2cartP')]")
    public WebElement addToCartButton;
    @FindBy(xpath = "//div[contains(@class,'prod-counter__plus_in')]")
    public WebElement addPlusOneItemInCounter;

    public ProductPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "catalog/product/";
    }

    /**
     * Проверяет наличие слова в заголовке карточки товара.
     *
     * @param word Слово для поиска в заголовке.
     * @return true, если слово присутствует в заголовке, иначе false.
     */
    public boolean isWordPresentInProductTitle(String word) {
        String titleText = titleOfProductCard.getText();
        return titleText.contains(word);
    }

    @Step("Проскролить до кнопки добавления в корзину")
    public ProductPage scrollToAddToCardButton() {
        scrollToElement(addToCartButton);
        return this;
    }

    @Step("Нажатие на кнопку добавления товара в корзину")
    public ProductPage addPlusOneProductInCounter() {
        clickOnElement(addPlusOneItemInCounter);
        return this;
    }
    @Step("Нажатие на кнопку добавления товара в корзину")
    public ProductPage addProductToCart() {
        clickOnElement(addToCartButton);
        return this;
    }


}
