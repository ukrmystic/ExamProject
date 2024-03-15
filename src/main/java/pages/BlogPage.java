package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BlogPage extends ParentPage {
    public BlogPage(WebDriver webDriver) {
        super(webDriver);
    }
    @Override
    protected String getRelativeUrl() {
        return "blog/";
    }

    protected String secondPageUrl() {
        return "blog/?page=2";
    }

    @FindBy(xpath = "//ul[@class='pagination']")
    private WebElement paginationBlock;
    @FindBy(xpath = "//ul[@class='pagination']//li[contains(@class,'prev')]")
    private WebElement prevButtonOfPaginationBlock;
    @FindBy(xpath = "//ul[@class='pagination']//li[contains(@class,'next')]")
    private WebElement nextButtonOfPaginationBlock;

    @Step
    public void assertUrl() {
        Assert.assertEquals("Invalid url " + webDriver.getCurrentUrl(), baseUrl + getRelativeUrl(), webDriver.getCurrentUrl());
        logger.info("Url is correct");
    }
    @Step
    public void assertUrlForSecondPage() {
        Assert.assertEquals("Invalid url " + webDriver.getCurrentUrl(), baseUrl + secondPageUrl(), webDriver.getCurrentUrl());
        logger.info("Url is correct");
    }

    @Step("Проскролить до блока пагинации")
    public BlogPage scrollToPaginationBlock() {
        scrollToElement(paginationBlock);
        return this;
    }

    @Step("Нажать на кнопку 'next' в пагинаторе")
    public ParentPage clickOnNextButton() {
        clickOnElement(nextButtonOfPaginationBlock);
        return new MainPage(webDriver);
    }

    @Step("Нажать на кнопку 'prev' в пагинаторе")
    public ParentPage clickOnPrevButton() {
        clickOnElement(prevButtonOfPaginationBlock);
        return new MainPage(webDriver);
    }
}
