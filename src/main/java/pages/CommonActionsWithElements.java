package pages;

import io.qameta.allure.Step;
import libs.ConfigProvider;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonActionsWithElements {
    protected static WebDriver webDriver;

    protected Logger logger = Logger.getLogger(getClass());
    protected WebDriverWait webDriverWaitDefault, webDriverWaitLow, webDriverWaitHigh;

        public CommonActionsWithElements(WebDriver webDriver) {
            this.webDriver = webDriver;
            PageFactory.initElements(webDriver, this);
            webDriverWaitDefault = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_DEFAULT_WAIT()));
            webDriverWaitLow = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_EXPLICIT_WAIT_LOW()));
            webDriverWaitHigh = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_EXPLICIT_WAIT_HIGH()));
        }

        protected void enterTextIntoInput(WebElement input, String text) {
            try {
                input.clear();
                input.sendKeys(text);
                logger.info(text + " был добавлен в поле" + getElementName(input));
            } catch (Exception e) {
                printErrorAndStopTest(e);
            }
        }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }

    private String getElementName(WebElement webElement) {
        try {
            return webElement.getAccessibleName();
        } catch (Exception e) {
            return "";
        }
    }

    protected void clickOnElement(WebElement element) {
        try {
            webDriverWaitDefault.withMessage("Элемент не кликабелен").until(ExpectedConditions.elementToBeClickable(element));
            String elementName = getElementName(element);
            element.click();
            logger.info("Элемент был нажат " + elementName);
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(String locator) {
        try {
            clickOnElement(webDriver.findElement(By.xpath(locator)));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            boolean state = element.isDisplayed();
            logger.info("Элемент " + getElementName(element) + " is displayed (" + state + ")");
            return state;
        } catch (Exception e) {
            logger.info("Элемент не отображается (false) " + e);
            return false;
        }
    }

    protected void selectTextInDropDown(WebElement dropDown, String text) {
        try {
            Select select = new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info(text + " был выбран в DropDown" + getElementName(dropDown));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void selectValueInDropDown(WebElement dropDown, String value) {
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info(value + " был выбран в DropDown" + getElementName(dropDown));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    public void checkIsElementVisible(WebElement webElement) {
        Assert.assertTrue("Элемент не виден", isElementDisplayed(webElement));
    }

    protected void checkIsElementNotVisible(WebElement webElement) {
        Assert.assertFalse("Элемент виден", isElementDisplayed(webElement));
    }

    protected void checkTextInElement(WebElement element, String expectedText) {
        try {
            String textFromElement = element.getText();
            Assert.assertEquals("Text in element not matched", expectedText, textFromElement);
            logger.info("Текст '" + expectedText + "' in element matched");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }


    @Step("Проскроллить к элементу")
    public void scrollToElement(WebElement element) {
        try {
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(500);
        } catch (WebDriverException we) {
            fail("Элемент не обнаружен");
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
    public static void fail(String message) {
        throw new AssertionError(message);
    }

}
