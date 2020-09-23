package ru.mail.regtest2020.helpers;

public class EndPoints {
    public static final String MAIN_URI = ConfigProperties.getProperty("MAIN_URI");
    public static final String REG_URI = MAIN_URI + "/quick-order?rc=1&back=my-account";
}
