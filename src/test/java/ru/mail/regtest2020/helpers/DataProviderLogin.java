package ru.mail.regtest2020.helpers;

import org.testng.annotations.DataProvider;

public class DataProviderLogin {
    @DataProvider(name = "wrongPasswordLogin")
    public Object[][] wrongPasswordLogin() {
        return new Object[][]{
            {ConfigProperties.getProperty("login"),"wrongpassword"},
            {"wronglogin@df.er",ConfigProperties.getProperty("password")}
        };
    }

    @DataProvider(name = "correctPasswordLogin")
    public Object[][] correctPasswordLogin() {
        return new Object[][]{
            {ConfigProperties.getProperty("login"),ConfigProperties.getProperty("password")},
            {" " + ConfigProperties.getProperty("login") + " "," " + ConfigProperties.getProperty("password") + " "}
        };
    }
}
