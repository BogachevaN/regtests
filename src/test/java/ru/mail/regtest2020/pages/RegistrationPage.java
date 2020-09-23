package ru.mail.regtest2020.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class RegistrationPage extends BasePage {
    @FindBy(xpath = "//input[@id='customer_firstname']")
    private WebElement firstName;
    @FindBy(xpath = "//input[@id='customer_lastname']")
    private WebElement lastName;
    @FindBy(xpath = "//input[@id='customer_email']")
    private WebElement email;
    @FindBy(xpath = "//input[@id='customer_passwd']")
    private WebElement password;
    @FindBy(xpath = "//input[@id='customer_conf_passwd']")
    private WebElement confirmPassword;
    @FindBy(xpath = "//button[@id='btn_save_customer']")
    private WebElement saveBtn;

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getLastName() {
        return lastName;
    }

    public WebElement getFirstName() {
        return firstName;
    }

    public WebElement getEmail() {
        return email;
    }

    public WebElement getPassword() {
        return password;
    }

    public WebElement getConfirmPassword() {
        return confirmPassword;
    }

    @Step
    public void fillLastName(String lastName) {
        //заполнение поля с фамилией
        if (this.lastName.getAttribute("value").equals("")) {
            this.lastName.sendKeys(lastName);
        }
    }

    @Step
    public void fillFirstName(String name) {
        //заполнение поля с именем
        if (firstName.getAttribute("value").equals("")) {
            firstName.sendKeys(name);
        }
    }

    @Step
    public void fillEmail(String email) {
        //заполнение поля с электронной почтой
        if (this.email.getAttribute("value").equals("")) {
            this.email.sendKeys(email);
        }
    }


    @Step
    public void fillPassword(String password) {
        //заполнение поля с паролем
        if (this.password.getAttribute("value").equals("")) {
            this.password.sendKeys(password);
        }
    }

    @Step
    public void fillConfirmPassword(String confirmPassword) {
        //заполнение поля с потором пароля
        if (this.confirmPassword.getAttribute("value").equals("")) {
            this.confirmPassword.sendKeys(confirmPassword);
        }
    }

    @Step
    public void clickSave() {
        //регистрация
        saveBtn.click();
    }

    @Step
    public void setDataInFieldFromFile(List<String[]> data) throws NoSuchFieldException, IllegalAccessException {
        for (String[] row : data) {
            switch (row[0]) {
                case ("firstName"):
                    fillFirstName(row[1]);
                    break;
                case ("email"):
                    fillEmail(row[1]);
                    break;
                case ("password"):
                    fillPassword(row[1]);
                    break;
                case ("confirmPassword"):
                    fillConfirmPassword(row[1]);
                    break;
                case ("lastName"):
                    fillLastName(row[1]);
                    break;
            }
        }
    }

    @Step
    public void clearRequiredField(String requiredField) throws NoSuchFieldException, IllegalAccessException {
        switch (requiredField) {
            case ("firstName"):
                clearField(firstName);
                break;
            case ("lastName"):
                clearField(lastName);
                break;
            case ("email"):
                clearField(email);
                break;
            case ("password"):
                clearField(password);
                break;
            case ("confirmPassword"):
                clearField(confirmPassword);
                break;
        }
    }

    @Step
    public boolean formRegistrationIsOpen() {
        return saveBtn.isEnabled();
    }
}
