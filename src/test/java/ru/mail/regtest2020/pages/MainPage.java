package ru.mail.regtest2020.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.mail.regtest2020.helpers.EndPoints;

public class MainPage extends BasePage {
    @FindBy(xpath = "//div[@id='header-login']/div")
    private WebElement signInLink;
    @FindBy(xpath = "//input[@id='header-email']")
    private WebElement userLogin;
    @FindBy(xpath = "//input[@id='header-passwd']")
    private WebElement userPassword;
    @FindBy(xpath = "//button[@name='HeaderSubmitLogin']")
    private WebElement signInBtn;
    @FindBy(xpath = "//li[@class='user-data']/p/span[1]")
    private WebElement userName;
    @FindBy (xpath = "//p[@class='logout']")
    private WebElement logout;
    @FindBy(xpath = "//a[contains(text(), 'Создать профиль пользователя')]")
    private WebElement registrationBtn;


    public MainPage(WebDriver driver) {
        super(driver);
        //получение адреса страницы
        driver.get(EndPoints.MAIN_URI);
    }

    @Step
    public void clickSignInLink() {
        signInLink.click();
    }

    @Step
    public void fillLogin(String login){
        //заполнение поля с логином
        userLogin.sendKeys(login);
    }

    @Step
    public void fillPassword(String password){
        //заполнение поля с паролем
        userPassword.sendKeys(password);
    }

    @Step
    public void clickSignInBtn(){
        //нажатие кнопки входа
        signInBtn.click();
    }

    public String getUserName() throws InterruptedException {
        clickSignInLink();
        return userName.getAttribute("textContent");
    }

    @Step
    //выход из лк
    public void logout(){
        clickSignInLink();
        logout.click();
    }

    public void clickRegistrationBtn(){
        registrationBtn.click();
    }

    public void goToRegistrationPage() {
        clickSignInLink();
        clickRegistrationBtn();
    }
}
