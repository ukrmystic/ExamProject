package searchProducts;

import baseTest.BaseTest;
import io.qameta.allure.Description;
import org.junit.Test;

public class SearchProductTests extends BaseTest {

    @Test
    @Description("Проверка работы поиска товара")
    public void searchProduct() {
        pageProvider.mainPage()
                .openMainPage()
                .getSecondMenu()
                .enterTextIntoSearchFill("Шпинат")
                .clickOnSubmitButton();
        pageProvider.searchPage()
                .clickOnFirstCard();
        pageProvider.productPagePage()
                .isWordPresentInProductTitle("Шпинат");
        pageProvider.breadcrumbs()
                .isWordPresentInBreadcrumbs("Шпинат");
    }
}
