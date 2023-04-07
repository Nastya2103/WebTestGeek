package WEB;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthPage extends AbstractPage{
    public AuthPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@type='text']")
    private WebElement input_username;
    @FindBy(xpath = "//input[@type='password']")
    private WebElement input_password;
    @FindBy(xpath = "//button[@form='login']")
    private WebElement button_login;
    @FindBy(xpath = "//div[@class='error-block svelte-uwkxn9']/h2")
    private WebElement error_code;
    @FindBy(xpath = "//div[@class='error-block svelte-uwkxn9']/p[1]")
    private WebElement error_message;
    @FindBy(xpath = "//li[contains(@class, 'surface')]")
    private WebElement button_hello;

    public AuthPage loginValid() {
        input_username.sendKeys("Nastya2103");
        input_password.sendKeys("7b9b0f8005");
        button_login.click();
        return this;
    }
    public AuthPage loginLessThen3() {
        input_username.sendKeys("hg");
        input_password.sendKeys("40fe9ad494");
        button_login.click();
        return this;
    }
    public AuthPage loginMax20() {
        input_username.sendKeys("Nastya2103Adreeva198");
        input_password.sendKeys("c9dba6ddf9");
        button_login.click();
        return this;
    }
    public AuthPage loginMin3() {
        input_username.sendKeys("Nas");
        input_password.sendKeys("b05c017361");
        button_login.click();
        return this;
    }
    public AuthPage loginRus() {
        input_username.sendKeys("арпово");
        input_password.sendKeys("08738c0ea2");
        button_login.click();
        return this;
    }
    public AuthPage loginSpecSymb() {
        input_username.sendKeys(";%;:%?:№");
        input_password.sendKeys("b7745f0741");
        button_login.click();
        return this;
    }


    public AuthPage loginMore20() {
        input_username.sendKeys("Nastya2103Adreeva1989");
        input_password.sendKeys("f5aca9fcd1");
        button_login.click();
        return this;
    }
    public AuthPage loginUnregistered() {
        input_username.sendKeys("Andreeva");
        input_password.sendKeys("7b9b0f8005");
        button_login.click();
        return this;
    }
    public AuthPage loginInvalidPassword() {
        input_username.sendKeys("Nastya2103");
        input_password.sendKeys("0avfgfv9д");
        button_login.click();
        return this;
    }
    public AuthPage loginEmptyLoginAndPassword() {
        input_username.sendKeys("");
        input_password.sendKeys("");
        button_login.click();
        return this;
    }


    public String getLogin(){
        String login = button_hello.getText();
        return login;
    }

    public WebElement getError_code() {
        return error_code;
    }
    public WebElement getError_message(){
        return error_message;
    }
}
