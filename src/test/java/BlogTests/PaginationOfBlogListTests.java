package BlogTests;

import baseTest.BaseTest;
import io.qameta.allure.Description;
import org.junit.Test;

public class PaginationOfBlogListTests extends BaseTest {

    @Test
    @Description("Пользователь проверяет работу пагинатора")
    public void paginationTest() {
        pageProvider.mainPage()
                .openMainPage()
                .getHeader()
                .clickOnBlogMenuItem();
        pageProvider.blogPage()
                .assertUrl();
        pageProvider.blogPage()
                .scrollToPaginationBlock()
                .clickOnNextButton();
        pageProvider.blogPage()
                .assertUrlForSecondPage();
        pageProvider.blogPage()
                .scrollToPaginationBlock()
                .clickOnPrevButton();
        pageProvider.blogPage()
                .assertUrl();

    }
}

