package ru.mail.regtest2020.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.mail.regtest2020.helpers.ConfigProperties;
import ru.mail.regtest2020.helpers.DataProviderRegistration;
import ru.mail.regtest2020.pages.MainPage;
import ru.mail.regtest2020.pages.RegistrationPage;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class RegistrationTests extends BaseTest{
    public static RegistrationPage registrationPage;
    public static MainPage mainPage;

    @BeforeClass
    public static void initPage() {
        mainPage = new MainPage(driver);
        //переход на страницу регистрации
        mainPage.goToRegistrationPage();
        registrationPage = new RegistrationPage(driver);
    }

    @Test
    public void successfulRegistrationTest() throws IOException, NoSuchFieldException, IllegalAccessException, InterruptedException {
        driver.navigate().refresh();
        //получение списка обязательных полей и их значений
        List<String[]> data = registrationPage.readCSVFileWithData("dataRequiredFieldsForRegistration.csv");
        //изменения email на уникальный
        registrationPage.changeValueInRow(data, "email", registrationPage.getRandomString(10) + "@mail.ru");
        //заполнение всех обязательных полей кроме одного
        registrationPage.setDataInFieldFromFile(data);
        registrationPage.clickSave();

        //проверяем что регистрация прошла
        assertTrue(registrationPage.containsText("Добро пожаловать в вашу учетную запись"));
    }

    @Test
    public void registrationWithExistEmailTest() throws IOException, NoSuchFieldException, IllegalAccessException, InterruptedException {
        List<String[]> data = registrationPage.readCSVFileWithData("dataRequiredFieldsForRegistration.csv");
        registrationPage.setDataInFieldFromFile(data);
        //очищение проверяемого поля
        registrationPage.clearField(registrationPage.getEmail());
        //заполнение поля
        registrationPage.fillEmail(ConfigProperties.getProperty("login"));
        registrationPage.goToNextField(registrationPage.getEmail());

        //проверяем ниличие сообщения об ошибке
        assertTrue(registrationPage.containsText("Аккаунт с этим email адресом уже создан"));
    }

    @Test(dataProvider = "requiredFieldsInRegistrationPage", dataProviderClass = DataProviderRegistration.class)
    public void checkRequiredFieldsTest(String requiredField) throws IOException, NoSuchFieldException, IllegalAccessException, InterruptedException {
        //получение списка обязательных полей и их значений
        List<String[]> data = registrationPage.readCSVFileWithData("dataRequiredFieldsForRegistration.csv");
        //удаление одного элемента из списка
        registrationPage.removeRow(data, requiredField);
        //очистка проверяемого поля
        registrationPage.clearRequiredField(requiredField);
        //заполнение всех обязательных полей кроме одного
        registrationPage.setDataInFieldFromFile(data);
        registrationPage.clickSave();

        //проверяем что регистрация не прошла
        assertTrue(registrationPage.formRegistrationIsOpen());
    }

    @Test(dataProvider = "incorrectLastName", dataProviderClass = DataProviderRegistration.class)
    public void fillLastNameIncorrectDataTest(String lastName) throws NoSuchFieldException, IllegalAccessException, IOException {
        List<String[]> data = registrationPage.readCSVFileWithData("dataRequiredFieldsForRegistration.csv");
        registrationPage.setDataInFieldFromFile(data);
        //очищение проверяемого поля
        registrationPage.clearField(registrationPage.getLastName());
        //заполнение поля
        registrationPage.fillLastName(lastName);
        registrationPage.goToNextField(registrationPage.getLastName());
        //есть предупреждение об ошибке
        assertTrue(registrationPage.containsTextUnderField("Не является действительным"));
    }

    @Test(dataProvider = "correctLastName", dataProviderClass = DataProviderRegistration.class)
    public void fillLastNameCorrectDataTest(String lastName) throws NoSuchFieldException, IllegalAccessException, IOException {
        List<String[]> data = registrationPage.readCSVFileWithData("dataRequiredFieldsForRegistration.csv");
        registrationPage.setDataInFieldFromFile(data);
        //очищение проверяемого поля
        registrationPage.clearField(registrationPage.getLastName());
        //заполнение поля
        registrationPage.fillLastName(lastName);
        registrationPage.goToNextField(registrationPage.getLastName());
        //предупреждения об ошибке нет
        assertFalse(registrationPage.containsTextUnderField("Не является действительным"));
    }

    @Test(dataProvider = "incorrectFirstName", dataProviderClass = DataProviderRegistration.class)
    public void fillFirstNameIncorrectDataTest(String firstName) throws NoSuchFieldException, IllegalAccessException, IOException {
        List<String[]> data = registrationPage.readCSVFileWithData("dataRequiredFieldsForRegistration.csv");
        registrationPage.setDataInFieldFromFile(data);
        //очищение проверяемого поля
        registrationPage.clearField(registrationPage.getFirstName());
        //заполнение поля
        registrationPage.fillFirstName(firstName);
        registrationPage.goToNextField(registrationPage.getFirstName());
        //есть предупреждение об ошибке
        assertTrue(registrationPage.containsTextUnderField("Это имя не является действительным"));
    }

    @Test(dataProvider = "correctFirstName", dataProviderClass = DataProviderRegistration.class)
    public void fillFirstNameCorrectDataTest(String firstName) throws NoSuchFieldException, IllegalAccessException, IOException {
        List<String[]> data = registrationPage.readCSVFileWithData("dataRequiredFieldsForRegistration.csv");
        registrationPage.setDataInFieldFromFile(data);
        //очищение проверяемого поля
        registrationPage.clearField(registrationPage.getFirstName());
        //заполнение поля
        registrationPage.fillFirstName(firstName);
        registrationPage.goToNextField(registrationPage.getFirstName());
        //предупреждения об ошибке нет
        assertFalse(registrationPage.containsTextUnderField("Это имя не является действительным"));
    }

    @Test(dataProvider = "incorrectEmail", dataProviderClass = DataProviderRegistration.class)
    public void fillEmailIncorrectDataTest(String email) throws NoSuchFieldException, IllegalAccessException, IOException {
        List<String[]> data = registrationPage.readCSVFileWithData("dataRequiredFieldsForRegistration.csv");
        registrationPage.setDataInFieldFromFile(data);
        //очищение проверяемого поля
        registrationPage.clearField(registrationPage.getEmail());
        //заполнение поля
        registrationPage.fillEmail(email);
        registrationPage.goToNextField(registrationPage.getEmail());
        //есть предупреждение об ошибке
        assertTrue(registrationPage.containsTextUnderField("Этот email адрес не является действительным"));
    }

    @Test(dataProvider = "correctEmail", dataProviderClass = DataProviderRegistration.class)
    public void fillEmailCorrectDataTest(String email) throws NoSuchFieldException, IllegalAccessException, IOException {
        List<String[]> data = registrationPage.readCSVFileWithData("dataRequiredFieldsForRegistration.csv");
        registrationPage.setDataInFieldFromFile(data);
        //очищение проверяемого поля
        registrationPage.clearField(registrationPage.getEmail());
        //заполнение поля
        registrationPage.fillEmail(email);
        registrationPage.goToNextField(registrationPage.getEmail());
        //есть предупреждение об ошибке
        assertFalse(registrationPage.containsTextUnderField("Этот email адрес не является действительным"));
    }

    @Test(dataProvider = "incorrectPassword", dataProviderClass = DataProviderRegistration.class)
    public void fillPasswordIncorrectDataTest(String password) throws NoSuchFieldException, IllegalAccessException, IOException {
        List<String[]> data = registrationPage.readCSVFileWithData("dataRequiredFieldsForRegistration.csv");
        registrationPage.setDataInFieldFromFile(data);
        //очищение проверяемого поля
        registrationPage.clearField(registrationPage.getPassword());
        //заполнение поля
        registrationPage.fillPassword(password);
        registrationPage.goToNextField(registrationPage.getPassword());
        //есть предупреждение об ошибке
        assertTrue(registrationPage.containsTextUnderField("Необходимо использовать как минимум 5 символов"));
    }

    @Test(dataProvider = "correctPassword", dataProviderClass = DataProviderRegistration.class)
    public void fillPasswordCorrectDataTest(String password) throws NoSuchFieldException, IllegalAccessException, IOException {
        List<String[]> data = registrationPage.readCSVFileWithData("dataRequiredFieldsForRegistration.csv");
        registrationPage.setDataInFieldFromFile(data);
        //очищение проверяемого поля
        registrationPage.clearField(registrationPage.getPassword());
        //заполнение поля
        registrationPage.fillPassword(password);
        registrationPage.goToNextField(registrationPage.getPassword());
        //есть предупреждение об ошибке
        assertFalse(registrationPage.containsTextUnderField("Необходимо использовать как минимум 5 символов"));
    }

    @Test
    public void passwordNotConfirmedTest(){
        registrationPage.fillPassword("123456");
        registrationPage.fillConfirmPassword("1234567");
        registrationPage.goToNextField(registrationPage.getConfirmPassword());
        assertTrue(registrationPage.containsTextUnderField("Значения полей не совпадают"));
    }

}
