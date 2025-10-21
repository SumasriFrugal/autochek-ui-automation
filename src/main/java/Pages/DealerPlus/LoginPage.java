package Pages.DealerPlus;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    public WebDriver driver;
    public WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    final By EmailField = By.xpath("//input[@type='email']");
    final By PasswordField = By.xpath("//input[@type='password']");
    final By LoginButton = By.xpath("//button//span[contains(text(),'Login')]");

    @Step("Entering email in email field")
    public void EnterEmail(String UserName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(EmailField));
        driver.findElement(EmailField).sendKeys(UserName);
    }

    @Step("Enter password in password field")
    public void EnterPassword(String Password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PasswordField));
        driver.findElement(PasswordField).sendKeys(Password);
    }

    @Step("Clicking on Login Button")
    public void ClickOnLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(LoginButton));
        driver.findElement(LoginButton).click();
    }

    @Step("Verifying if login is successfully completed")
    public boolean VerifySuccessfulLogin() {
        try {
            wait.until(ExpectedConditions.urlContains("dashboard"));
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        return driver.getCurrentUrl().contains("dashboard");
    }
}
