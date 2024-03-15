package loginTests;

import baseTest.BaseTest;
import io.qameta.allure.Description;
import org.junit.Test;

import static data.TestData.*;

public class LoginTests extends BaseTest {

    @Test
    @Description("Проверка работы логина с валидными креденшелами")
    public void validLogin() {
        pageProvider.mainPage()
                .openMainPage()
                .getSecondMenu()
                .clickOnLoginLink();
        pageProvider.loginPage()
                .checkIsRedirectToLoginPage()
                .enterTextIntoInputEmail(VALID_PHONE)
                .enterTextIntoInputPassword(VALID_PASSWORD)
                .clickOnSubmitButton()
                .checkIsRedirectToMyCabinetPage();
    }
}
