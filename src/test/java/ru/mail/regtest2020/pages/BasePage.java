package ru.mail.regtest2020.pages;

import com.opencsv.CSVReader;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class BasePage {
    public WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        //инициализация полей класса
        PageFactory.initElements(driver, this);
    }

    @Step
    public boolean containsText(String text) {
        Boolean res = false;
        //проверка наличия текста на странице
        try {
            driver.findElement(By.xpath(String.format("//*[contains(text(), '%s')]", text)));
            res = true;
        } catch (NoSuchElementException e){
            res = false;
        }
        return res;
    }

    //вычитывание данных из csv
    public List<String[]> readCSVFileWithData(String fileName) throws IOException {
        String path = Paths.get("src", "test", "resources","csv")
            .resolve(fileName)
            .toAbsolutePath()
            .toString();
        CSVReader reader = new CSVReader(new FileReader(path));
        List<String[]> allRows = reader.readAll();
        return allRows;
    }

    //удаление искомого элемента из списка
    public void removeRow(List<String[]> data, String requiredField) {
        Iterator iter = data.iterator();
        while (iter.hasNext()) {
            String[] s = (String[]) iter.next();
            if (s[0].equals(requiredField)) {
                iter.remove();
            }
        }
    }

    //изменение значения поля
    public void changeValueInRow(List<String[]> data, String field, String value) {
        Iterator iter = data.iterator();
        while (iter.hasNext()) {
            String[] s = (String[]) iter.next();
            if (s[0].equals(field)) {
                s[1] = value;
            }
        }
    }

    @Step
    public void clearField(WebElement element){
        element.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
    }

    @Step
    public void goToNextField(WebElement element) {
        element.sendKeys(Keys.chord(Keys.TAB));
    }

    @Step
    public boolean containsTextUnderField(String text) {
        Boolean res = false;
        //проверка наличия текста на странице
        try {
            driver.findElement(By.xpath(String.format("//span[contains(text(), '%s')]", text)));
            res = true;
        } catch (NoSuchElementException e){
            res = false;
        }
        return res;
    }

    public String getRandomString(int length) {
        String symbols = "1234567890abcdefghijklmnopqrstuvwxyz";
        String random = new Random().ints(length, 0, symbols.length())
            .mapToObj(symbols::charAt)
            .map(Object::toString)
            .collect(Collectors.joining());
        return random;
    }
}
