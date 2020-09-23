package ru.mail.regtest2020.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.mail.regtest2020.helpers.ConfigProperties;
import ru.mail.regtest2020.helpers.DataProviderLogin;
import ru.mail.regtest2020.pages.MainPage;

import static org.testng.Assert.*;

public class LoginTests extends BaseTest {
    public static MainPage mainPage;


    @BeforeClass
    public static void initPage() {
        mainPage = new MainPage(driver);
    }

    @BeforeMethod
    public void refreshPage(){
        //TODO: убрать refresh, когда исправят баг(данные сохраняются на форме после закрытия)
        driver.navigate().refresh();
    }

    @Test(dataProvider = "correctPasswordLogin", dataProviderClass = DataProviderLogin.class)
    public void successfulLoginTest(String login, String password) throws InterruptedException {
        mainPage.clickSignInLink();
        mainPage.fillLogin(login);
        mainPage.fillPassword(password);
        mainPage.clickSignInBtn();
        assertEquals(mainPage.getUserName(), ConfigProperties.getProperty("name"));
        mainPage.logout();
    }

    @Test(dataProvider = "wrongPasswordLogin", dataProviderClass = DataProviderLogin.class)
    public void loginWithWrongPasswordOrLoginTest(String login, String password) {
        mainPage.clickSignInLink();
        mainPage.fillLogin(login);
        mainPage.fillPassword(password);
        mainPage.clickSignInBtn();
        assertTrue(mainPage.containsText("Ошибка авторизации"));
        mainPage.clickSignInLink();
    }
}
