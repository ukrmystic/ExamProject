package addProductToCartTests;

import baseTest.BaseTest;
import io.qameta.allure.Description;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AddProductToCartTests extends BaseTest {

    @Test
    @Description("Проверка работы добавления товар из новинок в корзину")
    public void addNewestProductToCart() throws InterruptedException {
        pageProvider.mainPage()
                .openMainPage()
                .scrollToNewestBlock();
        String codeOfProductOnCard = pageProvider.mainPage().getCodeOfFirstProduct();
        pageProvider.mainPage()
                .clickOnPhotoInFirstCardFromList();
        pageProvider.productPagePage()
                .addPlusOneProductInCounter()
                .addProductToCart();
        pageProvider.cartPage().openCartPage();
        String codeOfProductInCart = pageProvider.cartPage().getCodeOfFirstProduct();
        Thread.sleep(2000);
        assertEquals("Коды продуктов совпадают!", codeOfProductOnCard, codeOfProductInCart);
    }

}
