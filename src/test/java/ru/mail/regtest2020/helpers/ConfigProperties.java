package ru.mail.regtest2020.helpers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigProperties {
    protected static Properties PROPERTIES;

    static {
        try {
            PROPERTIES = new Properties();
            FileInputStream fis = new FileInputStream(Paths.get("src", "test", "resources")
                .resolve("env.properties").toAbsolutePath().toString());
            try {
                PROPERTIES.load(fis);
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key)
    {
        return PROPERTIES.getProperty(key);
    }
}
