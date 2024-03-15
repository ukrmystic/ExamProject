package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {
    MainPage mainPage = new MainPage(webDriver);

    @FindBy(id = "login-form-login")
    private WebElement phoneInput;

    @FindBy(id = "login-form-password")
    private WebElement passwordInput;

    @FindBy(xpath = ".//button[@type='submit' and text()='Увійти']")
    private WebElement submitLoginButton;


    @FindBy(xpath = ".//a[@class='to_reg']")
    private WebElement registrationLink;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "site/login/";
    }

    @Step("Проверить редирект на страницу логина")
    public LoginPage checkIsRedirectToLoginPage() {
        checkUrl();
        checkIsElementVisible(submitLoginButton);
        return this;
    }

    @Step("Ввести phone в поле 'Phone'")
    public LoginPage enterTextIntoInputEmail(String phone) {
        enterTextIntoInput(phoneInput, phone);
        return this;
    }

    @Step("Ввести email в поле 'Password'")
    public LoginPage enterTextIntoInputPassword(String password) {
        enterTextIntoInput(passwordInput, password);
        return this;
    }

    @Step("Нажать на кнопку 'Увійти'")
    public MyCabinetPage clickOnSubmitButton() {
        clickOnElement(submitLoginButton);
        return new MyCabinetPage(webDriver);
    }

}
