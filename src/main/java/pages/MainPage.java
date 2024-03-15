package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.FooterElements;
import pages.elements.HeaderElements;
import pages.elements.SecondMenuElements;

import java.util.List;

public class MainPage extends ParentPage {

    @FindBy(xpath = "//h3[@class='section_x_products__title' and text()='Новинки']")
    private WebElement newestBlockTitle;
    @FindBy(xpath = "//div[@class='section_x_products'][2]//div[@class='section_x_products__list']")
    private List<WebElement> listOfCardsFromNewestBlock;
    @FindBy(xpath = "//h2[@class='title_blg_m' and text()='Блог садівника']")
    private WebElement blogBlockTitle;
    @FindBy(xpath = "//div[@class='m-blog__list']")
    private List<WebElement> listOfPostsInBlogBlock;
    @FindBy(xpath = "//div[@class='product-card__img']")
    private WebElement photoOnCard;


    public MainPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElements getHeader() {
        return new HeaderElements(webDriver);
    }

    public SecondMenuElements getSecondMenu() {
        return new SecondMenuElements(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "";
    }

    @Step("Открыть главную страницу")
    public MainPage openMainPage() {
        try {
            webDriver.get(baseUrl);
            logger.info("Основная страница была открыта " + baseUrl);
            return this;
        } catch (Exception e) {
            logger.error("Can not open main page");
            Assert.fail("Can not open main page");
            return null;
        }
    }

    @Step("Проскролить до блока новинок")
    public MainPage scrollToNewestBlock() {
        scrollToElement(newestBlockTitle);
        return this;
    }

    @Step("Проскролить до блока блога")
    public MainPage scrollToBlogBlock() {
        scrollToElement(blogBlockTitle);
        return this;
    }

    @Step("Нажать на фото первой карточки из списка")
    public void clickOnPhotoInFirstCardFromList() {
        if (listOfCardsFromNewestBlock.size() > 0) {
            WebElement firstCard = listOfCardsFromNewestBlock.get(0);
            WebElement photoInCard = firstCard.findElement(By.xpath(".//div[@class='product-card__img']"));
            photoInCard.click();
        } else {
            System.out.println("Список элементов пуст.");
        }
    }

    public String getCodeOfFirstProduct() {
        if (!listOfCardsFromNewestBlock.isEmpty()) {
            WebElement firstCard = listOfCardsFromNewestBlock.get(0);
            WebElement productCodeElement = firstCard.findElement(By.xpath(".//div[@class='product-card__code']"));
            String productCodeText = productCodeElement.getText();
            String numericValue = productCodeText.replaceAll("[^0-9]", "");
            return numericValue;
        } else {
            System.out.println("Список карточек пуст.");
            return "";
        }
    }

    public Integer getViewsOfFirstPost() {
        if (!listOfPostsInBlogBlock.isEmpty()) {
            WebElement firstPost = listOfPostsInBlogBlock.get(0);
            WebElement viewsElement = firstPost.findElement(By.xpath(".//div[@class='blog-page-views']//span"));
            String viewsText = viewsElement.getText();
            String numericValueString = viewsText.replaceAll("[^0-9]", "");
            try {
                return Integer.parseInt(numericValueString);
            } catch (NumberFormatException e) {
                System.out.println("Не удалось преобразовать строку в число: " + e.getMessage());
                return null;
            }
        } else {
            System.out.println("Список карточек пуст.");
            return null;
        }
    }

    @Step("Нажать на фото первой карточки из списка")
    public void clickOnPhotoInFirstPostFromList() {
        if (listOfPostsInBlogBlock.size() > 0) {
            WebElement firstCard = listOfPostsInBlogBlock.get(0);
            WebElement photoInCard = firstCard.findElement(By.xpath(".//a[@class='m-blog__img']"));
            photoInCard.click();
        } else {
            System.out.println("Список элементов пуст.");
        }
    }
}
