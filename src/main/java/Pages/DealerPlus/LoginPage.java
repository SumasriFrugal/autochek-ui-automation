package Pages.DealerPlus;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    public WebDriver driver;
    public static WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    final By EmailField = By.xpath("//input[@type='email']");
    final By PasswordField = By.xpath("//input[@type='password']");
    final By LoginButton = By.xpath("//button//span[contains(text(),'Login')]");
    final By RetoolLoginButton = By.xpath("//p[text()='Login']");
    final By LogoutButton = By.xpath("//img[@alt='logout-icon']");
    final By LoggingYouOutPopup = By.xpath("//h4[text()='Logging you out...']");


    @Step("Entering email in email field")
    public void EnterEmail(String UserName) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(EmailField));
        driver.findElement(EmailField).sendKeys(UserName);
    }
    @Step("Entering email in email field")
    public void EnterEmailRetool(String UserName) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoggingYouOutPopup));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(LoggingYouOutPopup));
        wait.until(ExpectedConditions.elementToBeClickable(EmailField));
        driver.findElement(EmailField).sendKeys(UserName);
    }


    @Step("Enter password in password field")
    public void EnterPassword(String Password) throws InterruptedException {
        //Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(PasswordField));
        driver.findElement(PasswordField).sendKeys(Password);
    }

    @Step("Clicking on Login Button")
    public void ClickOnLoginButton() throws InterruptedException {
        //Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(LoginButton));
        driver.findElement(LoginButton).click();
    }

    @Step("Clicking on Login Button for Retool")
    public void ClickOnLoginButtonRetool() {
        wait.until(ExpectedConditions.elementToBeClickable(RetoolLoginButton));
        driver.findElement(RetoolLoginButton).click();
    }

    @Step("Clicking on Logout Button")
    public void ClickOnLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(LogoutButton));
        driver.findElement(LogoutButton).click();
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
