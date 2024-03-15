package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyCabinetPage extends ParentPage {
    @FindBy(xpath = "//h1[contains(@class,'title') and text()='Мої замовлення']")
    private WebElement titleOfMyOrders;
    @FindBy(xpath = ".//a[@class='logout_btn']")
    private WebElement quiteButton;

    public MyCabinetPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "cabinet/";
    }

    @Step("Проверить редирект на страницу кабинета пользователя")
    public MyCabinetPage checkIsRedirectToMyCabinetPage() {
        checkIsElementVisible(titleOfMyOrders);
        checkIsElementVisible(quiteButton);
        return this;
    }

}
