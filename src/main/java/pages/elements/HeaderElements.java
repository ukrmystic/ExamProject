package pages.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.MainPage;

public class HeaderElements extends CommonActionsWithElements {

    @FindBy(xpath = "//div[@class='header__logo']")
    private WebElement logo;
    @FindBy(xpath = "//div[@class='header_nav']")
    private WebElement header;
    @FindBy(xpath = "//div[@class='header_cart_pc']")
    private WebElement cart;
    @FindBy(xpath = "//ul[@class='header_nav__list']//li[contains(@class,'header_nav__item')]//a[text()='Блог']")
    private WebElement blogMenuItem;

    public HeaderElements(WebDriver webDriver) {super(webDriver);}

    @Step("Нажать на пункт меню 'Блог' в Header")
    public MainPage clickOnBlogMenuItem() {
        clickOnElement(blogMenuItem);
        return new MainPage(webDriver);
    }

}
