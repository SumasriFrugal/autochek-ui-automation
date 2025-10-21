package Tests.DealerPlus;

import Base.BaseClass;
import Pages.DealerPlus.LoginPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Login extends BaseClass {
    LoginPage loginPage;

    @BeforeMethod
    public void SetUpTests() {
        super.SetUp();
        loginPage = new LoginPage(driver);
    }

    @AfterMethod
    public void TearDown() {
        driver.quit();
    }
    @Epic("Login Module")
    @Feature("Positive Flow")
    @Story("Successful Login Scenario")
    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "Test: Login successfully using valid credentials and verify URL post-login")
    public void VerifyLoginWithUrlValidation() {
        loginPage.EnterEmail(UserName);
        loginPage.EnterPassword(Password);
        loginPage.ClickOnLoginButton();
        if (!loginPage.VerifySuccessfulLogin())
            Assert.fail("Dashboard Landing failed!");
    }
}
