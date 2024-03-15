package pages.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;

public class FooterElements extends CommonActionsWithElements {

    public FooterElements(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//footer[@class='footer']")
    private WebElement footer;
    @FindBy(xpath = "//a[@class='footer__logo']")
    private WebElement footerLogo;
    @FindBy(xpath = "//form[@class='callback-form']")
    private WebElement callbackForm;
    @FindBy(xpath = "//input[@name='CallRequests[phone]']")
    private WebElement inputInCallbackForm;
    @FindBy(xpath = "//form[@class='callback-form']//button")
    private WebElement buttonInCallbackForm;

    @Step("Проскролить до футера")
    public FooterElements goToFooter() {
        scrollToElement(footer);
        return this;
    }

    @Step("Проскролить до формы обратной связи")
    public FooterElements goToCallBackForm() {
        scrollToElement(callbackForm);
        return this;
    }

}
