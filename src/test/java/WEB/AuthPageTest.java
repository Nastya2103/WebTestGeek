package WEB;

import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AuthPageTest extends AbstractTest{


    public AuthPageTest() {
    }

    @Test
    @DisplayName("Valid username and password")
    @Description("Авторизация с валидными данными")
    @Tag("Positive")
    public void LoginValidTest() throws InterruptedException, IOException {
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginValid();
        Thread.sleep(5000);
        assertEquals("https://test-stand.gb.ru/", getDriver().getCurrentUrl());
        assertEquals("Hello, Nastya2103", authPage.getLogin());
    }
    @Test
    @DisplayName("Invalid corner case.LoginLess3")
    @Description("Авторизация с граничными значениями логина. Логин содержит менее 3 символов")
    @Tag("Negative")
    public void loginLessThen3Test() throws IOException {
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginLessThen3();
        assertEquals("401",getDriver().getCurrentUrl());
    }
    @Test
    @DisplayName("Valid corner case. Max - 20")
    @Description("Авторизация с граничными значениями логина. Логин содержит 20 символов")
    @Tag("Positive")
    public void loginMax20Test() throws InterruptedException, IOException{
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginMax20();
        Thread.sleep(1000);
        assertEquals("https://test-stand.gb.ru/", getDriver().getCurrentUrl());
        assertEquals("Hello, Nastya2103Adreeva198", authPage.getLogin());
    }

    @Test
    @DisplayName("Valid corner case. Min - 3")
    @Description("Авторизация c валидными граничными значениями. Логин - 3 символа")
    @Tag("Positive")
    public void loginMin3Test() throws InterruptedException {
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginMin3();
        Thread.sleep(4000);
        assertEquals("https://test-stand.gb.ru/", getDriver().getCurrentUrl());
        assertEquals("Hello, Nas", authPage.getLogin());
    }

    @Test
    @DisplayName("Unregistered user")
    @Description("Авторизация с невалидными данными: незарегистрированный пользователь")
    @Tag("Negative")
    public void loginUnregisteredTest() throws IOException {
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginUnregistered();
        assertEquals("Invalid credentials.", authPage.getError_message().getText());
        assertEquals("401", authPage.getError_code().getText());
    }

    @Test
    @DisplayName("Invalid username - russian letters")
    @Description("Авторизация с невалидными данными: кирилица")
    @Tag("Negative")
    public void loginRusTest() throws InterruptedException {
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginRus();
        Thread.sleep(3000);
        assertEquals("401",getDriver().getCurrentUrl());
    }

    @Test
    @DisplayName("Invalid login - spec symbols")
    @Description("Авторизация с невалидными данными: логин в символьном значении")
    @Tag("Negative")
    public void loginSpecSymbTest() throws IOException {
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginSpecSymb();
        assertEquals("401",getDriver().getCurrentUrl());
    }

    @Test
    @DisplayName("Invalid corner case. LoginMore20")
    @Description("Авторизация c невалидными граничными значениями. Логин более 20 символов")
    @Tag("Negative")
    public void loginMore20Test() throws IOException {
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginMore20();
        assertEquals("401",getDriver().getCurrentUrl());
    }

    @Test
    @DisplayName("Valid username and incorrect password")
    @Description("Авторизация с валидным логином и невалидным паролем")
    @Tag("Negative")
    public void loginInvalidPasswordTest() throws IOException {
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginInvalidPassword();
        assertEquals("Invalid credentials.", authPage.getError_message().getText());
        assertEquals("401", authPage.getError_code().getText());
    }


    @Test
    @DisplayName("Empty values username and password")
    @Description("Авторизация с пустыми полями")
    @Tag("Negative")
    public void loginEmptyLoginAndPasswordTest() throws IOException {
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginEmptyLoginAndPassword();
        assertEquals("Invalid credentials.", authPage.getError_message().getText());
        assertEquals("401", authPage.getError_code().getText());
    }
}
