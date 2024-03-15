package pages.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.MainPage;
import pages.ParentPage;


public class SecondMenuElements extends CommonActionsWithElements {
    @FindBy(xpath = "//a[@class='profile_btn_link']")
    private WebElement loginLink;
    @FindBy(xpath = "//div[@class='header_secpth']")
    private WebElement menuButton;
    @FindBy(xpath = "//input[@id='title-search-input']")
    private WebElement inputSearch;
    @FindBy(xpath = "//div[@class='h_m_search_line']//button")
    private WebElement searchButton;

    public SecondMenuElements(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Нажать на линк 'Особистий кабінет'")
    public ParentPage clickOnLoginLink() {
        clickOnElement(loginLink);
        return new MainPage(webDriver);
    }

    @Step("Ввести текст в поле поиска")
    public SecondMenuElements enterTextIntoSearchFill(String nameOfCategory) {
        enterTextIntoInput(inputSearch, nameOfCategory);
        return this;
    }

    @Step("Нажать на кнопку поиска")
    public SecondMenuElements clickOnSubmitButton() {
        clickOnElement(searchButton);
        return new SecondMenuElements(webDriver);
    }

}
