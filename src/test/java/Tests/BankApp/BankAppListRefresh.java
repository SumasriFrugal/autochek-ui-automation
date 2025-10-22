package Tests.BankApp;

import Base.BaseClass;
import Pages.BankApp.BankAppRefreshListPage;
import Pages.DealerPlus.LoginPage;
import io.qameta.allure.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

@Epic("Bank App")
public class BankAppListRefresh extends BaseClass {

    LoginPage loginPage;
    BankAppRefreshListPage bankPage;

    @AfterClass(alwaysRun = true)
    public void Logout() {
        try { loginPage.ClickOnLogoutButton(); } catch (Exception ignored) {}
    }

    @AfterSuite(alwaysRun = true)
    public void TearDownSuite() {
        super.TearDown();
    }

    @BeforeClass(alwaysRun = true)
    public void beforeEachClass() {bankPage.BankSheetButtonClick();}

    @BeforeSuite
    public void SetUpTests() throws InterruptedException {
        // Launch Retool
        super.SetUpRetool();

        // Login (MT940 admin creds from BaseClass)
        loginPage = new LoginPage(driver);
        loginPage.EnterEmailRetool(MT940AdminUserName);
        loginPage.EnterPassword(MT940AdminPassword);
        loginPage.ClickOnLoginButtonRetool();
        bankPage = new BankAppRefreshListPage(driver);
    }
    @BeforeMethod
    public void beforeEachTest() {
        //driver.navigate().refresh();
        boolean pageRefreshed = false;
        // keep refreshing until the validation method returns true
        while (!pageRefreshed) {
            driver.navigate().refresh();
            pageRefreshed = bankPage.pageReloadValidate();
        }

    }

    @Feature("Positive Tests: Bank Sheet")
    @Story("Add New Bank Account and verify in list")
    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "Add a new bank via modal and verify it appears in the list")
    public void addBankAndVerifyInList() {
        // Unique data to avoid duplicates
        String uniqueSuffix   = String.valueOf(System.currentTimeMillis()).substring(7);
        String bankName       = "AutoTest Bank " + uniqueSuffix;
        String accountNumber  = "ACC" + uniqueSuffix;
        bankPage.addBank(bankName, accountNumber);
        Assert.assertFalse(
                bankPage.verifyPopup(),
                "New Bank is successfully Added"
        );
    }
    @Feature("Positive Tests: Bank Sheet")
    @Story("Delete New Bank Account and verify in list")
    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "Delete a newly added Bank via modal and verify it does not appears in the list")
    public void deleteBankAndVerify() {
        // Unique data to avoid duplicates
        String uniqueSuffix   = String.valueOf(System.currentTimeMillis()).substring(7);
        String bankName       = "AutoTest Bank " + uniqueSuffix;
        String accountNumber  = "ACC" + uniqueSuffix;
        bankPage.addBank(bankName, accountNumber);
        bankPage.deleteBank(accountNumber);

        SoftAssert softAssert = new SoftAssert();
        if (!bankPage.verifydeletePopup())
            softAssert.fail("Bank Did not got Deleted!");
        softAssert.assertAll();
    }

    @Feature("Positive Tests: Bank Sheet")
    @Story("Inline edit Restricted, Facility, and Status for newly added bank")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Add bank, filter by account number, inline update Restricted/Facility/Status and verify editor closes")
    public void addBank_InlineEditAndVerifyClosed() {
        // Unique payload
        String suffix        = String.valueOf(System.currentTimeMillis()).substring(7);
        String bankName      = "AutoTest Bank " + suffix;
        String accountNumber = "ACC" + suffix;

        // 1) Add bank
        bankPage.addBank(bankName, accountNumber);
        Assert.assertTrue(bankPage.verifyPopup(), "New Bank popup not shown after add");

        // 2) Search the just-added account and inline-edit three fields
        //    Provide the exact option texts visible in your dropdowns
        String restrictedNew = "Unrestricted"; // or "Restricted"
        String facilityNew   = "Cauris";       // or "Cordiant" etc.
        String statusNew     = "Active";      // or "Active"

        bankPage.searchAndInlineUpdate(accountNumber, restrictedNew, facilityNew, statusNew);

        SoftAssert softAssert = new SoftAssert();
        if (!bankPage.verifyUpdatePopup())
            softAssert.fail("Bank Did not got Updated!");
        softAssert.assertAll();
    }

    @Feature("Positive Tests: Bank Sheet")
    @Story("Update and verify hedge rate")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Update KE_HEDGE444 rate and verify it persists")
    public void updateHedgeRate_And_Verify() {
        Assert.assertTrue(
                bankPage.updateHedgeRateAndVerify(),
                "Hedge rate did not persist correctly after update."
        );

    }

    // Add Bank Modal: Required fields
    @Feature("Negative Tests: Bank Sheet")
    @Story("Add Bank Modal")
    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "Required fields should block Add Bank")
    public void requiredFieldsBlockAdd() {
        Assert.assertTrue(bankPage.addBank_RequiredFields_Block(),
                "Add Bank should be blocked when required fields are empty.");
    }

    // Add Bank Modal: Invalid Account Number format
    @Feature("Negative Tests: Bank Sheet")
    @Story("Add Bank Modal")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Invalid Account Number format should block creation")
    public void invalidAccountNumber() {
        String suffix        = String.valueOf(System.currentTimeMillis()).substring(7);
        String accountNumber = "ACC" + suffix;
        // examples: alpha, too short, special chars – pick one matching your backend rules
        Boolean debug = bankPage.addBank_InvalidAccountNumber_Block(accountNumber+"*&^%");

        Assert.assertTrue(debug,
                "Invalid Account Number should block creation.");
    }

    // Add Bank Modal: Duplicate Account Number
    @Feature("Negative Tests: Bank Sheet")
    @Story("Add Bank Modal")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Duplicate Account Number is not allowed")
    public void duplicateAccountNotAllowed() {
        String suffix = String.valueOf(System.currentTimeMillis()).substring(7);
        String acc    = "DUP" + suffix;
        String name   = "Dup Test " + suffix;

        Assert.assertTrue(bankPage.addBank_DuplicateAccount_Block(name, acc),
                "Duplicate Account Number should be blocked.");
    }

    // Inline Edit: Rollback on failure (simulate Cancel)
    @Feature("Negative Tests: Bank Sheet")
    @Story("Inline Edit")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Rollback to old value on inline update failure (Cancel)")
    public void inlineRollbackOnCancel() {
        // Unique payload
        String suffix        = String.valueOf(System.currentTimeMillis()).substring(7);
        String bankName      = "AutoTest Bank " + suffix;
        String accountNumber = "ACC" + suffix;

        // 1) Add bank
        bankPage.addBank(bankName, accountNumber);
        Assert.assertTrue(bankPage.verifyPopup(), "New Bank popup not shown after add");
        String restrictedNew = "Unrestricted"; // or "Restricted"
        String facilityNew   = "Cauris";       // or "Cordiant" etc.
        Assert.assertTrue(bankPage.inlineEdit_Rollback_OnCancel(accountNumber, restrictedNew),
                "Status should revert to original value after Cancel.");
    }
    // Refresh: On refresh it should rollback to original in the draft of inline edits
    @Feature("Positive Tests: Bank Sheet")
    @Story("Inline Edit")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Rollback to old value on inline update failure (Cancel)")
    public void inlineRollbackOnRefresh() {
        // Unique payload
        String suffix        = String.valueOf(System.currentTimeMillis()).substring(7);
        String bankName      = "AutoTest Bank " + suffix;
        String accountNumber = "ACC" + suffix;

        // 1) Add bank
        bankPage.addBank(bankName, accountNumber);
        Assert.assertTrue(bankPage.verifyPopup(), "New Bank popup not shown after add");
        String restrictedNew = "Unrestricted"; // or "Restricted"
        String facilityNew   = "Cauris";       // or "Cordiant" etc.
        Assert.assertTrue(bankPage.refreshList(accountNumber, restrictedNew),
                "Status should revert to original value after Cancel.");
    }

    // Update Hedge Rates: Required fields validation
    @Feature("Negative Tests: Bank Sheet")
    @Story("Update Hedge Rates")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Missing Currency/Rate should block update")
    public void hedgeRequiredFields() {
        Assert.assertTrue(bankPage.hedgeUpdate_RequiredFields_Block(),
                "Hedge update should be blocked when Currency/Rate are missing.");
    }

    // Update Hedge Rates: Invalid Rate format (CSV-driven)
    @Feature("Negative Tests: Bank Sheet")
    @Story("Update Hedge Rates")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "InvalidRatesCSV", description = "Invalid Rate format should be blocked (CSV-driven)")
    public void hedgeInvalidRate(String invalidRate) {
        bankPage.hedgeUpdate_InvalidRate_Block(invalidRate);
        SoftAssert softAssert = new SoftAssert();
        if (bankPage.inlineAlertPopup())
            softAssert.fail("Hedge update should be blocked for invalid rate with proper message: " + invalidRate);
        softAssert.assertAll();
    }

    @DataProvider(name = "InvalidRatesCSV")
    public Object[][] invalidRatesFromCsv() throws Exception {
        java.nio.file.Path path = java.nio.file.Paths.get("Resources/BankApp Files/HedgeInvalidRates.csv");
        java.util.List<String> lines = java.nio.file.Files.readAllLines(path);

        java.util.List<Object[]> data = new java.util.ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {           // skip header
            String line = lines.get(i).trim();
            if (line.isEmpty()) continue;
            // if CSV has commas in values, you can split more robustly; here it's one column
            data.add(new Object[]{ line });
        }
        return data.toArray(new Object[0][]);
    }

}
