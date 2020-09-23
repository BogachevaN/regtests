package ru.mail.regtest2020.helpers;

import org.testng.annotations.DataProvider;

public class DataProviderRegistration {
    @DataProvider(name = "requiredFieldsInRegistrationPage")
    public Object[][] requiredFieldsInRegistrationPage() {
        return new Object[][]{
            {"firstName"},
            {"lastName"},
            {"email"},
            {"password"},
            {"confirmPassword"}
        };
    }

    @DataProvider(name = "incorrectLastName")
    public Object[][] incorrectLastName() {
        return new Object[][]{
            {"Булкин2"},
            {"Булкин+"},
            {"Булкин*!"},
            {" "},
            {"Bulkin12"},
            {"Bulkin%"},
            {"2"},
            {"="}
        };
    }

    @DataProvider(name = "correctLastName")
    public Object[][] correctLastName() {
        return new Object[][]{
            {"Б"},
            {"Бул-кин"},
            {" Булкин "},
            {"Bulkin"},
            {"B"},
            {"Булкин Плюшкин"}
        };
    }

    @DataProvider(name = "incorrectFirstName")
    public Object[][] incorrectFirstName() {
        return new Object[][]{
            {"Иван2"},
            {"Иван+"},
            {"Иван*!"},
            {" "},
            {"Ivan12"},
            {"Ivan%"},
            {"2"},
            {"="}
        };
    }

    @DataProvider(name = "correctFirstName")
    public Object[][] correctFirstName() {
        return new Object[][]{
            {"И"},
            {"Иван-Иван"},
            {" Иван "},
            {"Ivan"},
            {"I"},
            {"Иван Иван"}
        };
    }

    @DataProvider(name = "incorrectEmail")
    public Object[][] incorrectEmail() {
        return new Object[][]{
            {"bulkin ivan@mail.ru"},
            {"bulkin@mail"},
            {"ivan@.ru"},
            {" "},
            {"ivan#mail.ru"},
            {"Iivan@ma%il.ru"},
            {"ivan@mail mail.ru"},
            {"@mail.ru"},
        };
    }

    @DataProvider(name = "correctEmail")
    public Object[][] correctEmail() {
        return new Object[][]{
            {"bulkin_ivan@mail12.ru"},
            {"bulkin25@mail.ru"},
            {"bulkin.ivan@mail.ru"},
            {"bulkin-ivan@mail.ru"},
            {"ivan@mail_mail.ru "},
            {"ivan@mail-mail.ru"},
            {"IVAN@mail.ru"},
            {"IVAN@почта.рф"}
        };
    }

    @DataProvider(name = "incorrectPassword")
    public Object[][] incorrectPassword() {
        return new Object[][]{
            {" "},
            {"1234"},
            {"1"}
        };
    }

    @DataProvider(name = "correctPassword")
    public Object[][] correctPassword() {
        return new Object[][]{
            {"12345"},
            {" 12345 "},
            {"12345t"}
        };
    }
}
