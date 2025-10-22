package Tests.DealerPlus;

import Base.BaseClass;
import Pages.DealerPlus.LoginPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.*;


public class Login extends BaseClass {
    LoginPage loginPage;

    @BeforeSuite
    public void SetUpTests() {
        super.SetUp();
        loginPage = new LoginPage(driver);
    }

    @AfterClass
    public void Logout() {
        loginPage.ClickOnLogoutButton(); // ✅ Use instance method via object
    }

    @AfterSuite
    public void TearDown() {
        driver.quit();
    }


    @Epic("Login Module")
    @Feature("Positive Flow")
    @Story("Successful Login Scenario")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Test: Login successfully using valid credentials and verify URL post-login")
    public void VerifyLoginWithUrlValidation() throws InterruptedException {
        loginPage.EnterEmail(AccountManagerUserName);
        loginPage.EnterPassword(AccountManagerPassword);
        loginPage.ClickOnLoginButton();
        if (!loginPage.VerifySuccessfulLogin())
            Assert.fail("Dashboard Landing failed!");
    }
}
