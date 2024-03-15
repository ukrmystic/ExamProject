package BlogTests;

import baseTest.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CounterOfPostTest extends BaseTest {

    @Test
    @Description("Проверка работы счётка просмотров")
    public void WorksCounterViewsOnPost(){
        pageProvider.mainPage()
                .openMainPage()
                .scrollToBlogBlock();
        Integer viewsOfPostOnMainPage = pageProvider.mainPage().getViewsOfFirstPost() + 1;
        pageProvider.mainPage()
                .clickOnPhotoInFirstPostFromList();
        Integer viewsOfPostOnPostPage = pageProvider.postPage().getViewsOfPost();
        assertEquals("Количество просмотров увеличено на +1", viewsOfPostOnMainPage, viewsOfPostOnPostPage);
    }
}
