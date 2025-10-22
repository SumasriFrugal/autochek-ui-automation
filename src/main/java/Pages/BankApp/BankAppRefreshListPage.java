package Pages.BankApp;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BankAppRefreshListPage {

    public WebDriver driver;
    public WebDriverWait wait;
    private final Actions actions;

    public BankAppRefreshListPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(12));
        this.actions = new Actions(driver);
    }

    // --------- Locators (from your snippet) ----------
    final By BankSheetButton           = By.xpath("//p[text()='Bank Balance']");
    final By AddBankButton             = By.xpath("//button[text()='Add Bank']");
    final By BankNameInput             = By.xpath("//label[contains(normalize-space(),'Bank Name')]/following::input[1]");
    final By BankCountryDD             = By.xpath("//label[contains(normalize-space(),'Country')]/following::input[1]");
    final By BankCountryOptionNG       = By.xpath("//div[text()='Nigeria']");
    final By EntityDD                  = By.xpath("//label[contains(normalize-space(),'Entity')]/following::input[1]");
    final By EntityOptionNG            = By.xpath("//div[text()='AFS Capital SPC - SP1']");
    final By AccountNumberInput        = By.xpath("//label[contains(normalize-space(),'Account Number')]/following::input[1]");
    final By CurrencyDD                = By.xpath("//label[contains(normalize-space(),'Currency')]/following::input[1]");
    final By CurrencyOption            = By.xpath("//div[text()='NGN']");
    final By RateDD                    = By.xpath("//label[contains(normalize-space(),'Rate')]/following::input[1]");
    final By RateOption                = By.xpath("//div[text()='KE_HEDGE1']");
    final By SegmentDD                 = By.xpath("//label[contains(normalize-space(),'Segment')]/following::input[1]");
    final By SegmentOption             = By.xpath("//div[text()='AFS']");
    final By FacilityDD                = By.xpath("//label[contains(normalize-space(),'Facility')]/following::input[1]");
    final By FacilityOption            = By.xpath("//div[text()='Cordiant']");
    final By StatusDD                  = By.xpath("//label[contains(normalize-space(),'Status')]/following::input[1]");
    final By StatusOption              = By.xpath("//div[text()='Dormant']");
    final By AccountRestrictionDD      = By.xpath("//label[contains(normalize-space(),'Account Restriction')]/following::input[1]");
    final By AccountRestrictionOption  = By.xpath("//div[text()='Restricted']");
    final By RestrictionCategoryDD     = By.xpath("//label[contains(normalize-space(),'Restriction Category')]/following::input[1]");
    final By RestrictionCategoryOption = By.xpath("//div[text()='SPV']");
    final By AddBankButtonInner        = By.xpath("//p[text()='Add Bank']");
    final By SearchInput        = By.xpath("//input[contains(@id,'input_search')]");
    final By RestrictedInlineEdit        = By.xpath("(//div[@role='rowgroup']//span[contains(text(),'Restricted')]/parent::div)[1]");
    final By FacilityInlineEdit        = By.xpath("(//div[@role='rowgroup']//span[contains(text(),'Cordiant')]/parent::div)[1]");
    final By StatusInlineEdit        = By.xpath("(//div[@role='rowgroup']//span[contains(text(),'Active')]/parent::div)[1]");
    final By BtnSave      = By.xpath("//button[text()='Save']");
    final By BtnCancel     = By.xpath("//button[text()='Cancel']");
    final By BtnDeleteBank     = By.xpath("//button[@aria-label='Delete bank']");
    final By BankSuccessfullyDeletedPopup      = By.xpath("//h4[text()='Bank Deleted Successfully!']");
    final By BankSuccessfullyAddedPopup      = By.xpath("//h4[text()='New Bank Added Successfully!']");
    final By BankSuccessfullyUpdatedPopup      = By.xpath("//h4[text()='Bank Details Updated!']");
    final By InnerDeleteOk = By.xpath("//span[text()='OK']/parent::button");
    final By CountryLabel = By.xpath("//span[text()='Country']/parent::div");
    final By UpdateHedgeRatesButton = By.xpath("//button[text()='Update Hedge Rate']");
    final By HedgeCurrencyDD = By.xpath("//span[text()='Currency']/parent::div/parent::label/parent::div/following-sibling::div/div/div/div/div/input");
    final By HedgeCurrencyOption = By.xpath("//div[text()='KE_HEDGE444']");
    final By HedgeRateInput = By.xpath("//span[text()='Rate']/parent::div/parent::label/parent::div/following-sibling::div/div/div/div/parent::div/following-sibling::input");
    final By UpdateHedgeRatesButtonInner = By.xpath("//p[text()='Update Hedge Rate']/parent::span/parent::div");
    final By RateUpdatedSuccessfullyPopup = By.xpath("//h4[text()='Rate Updated Successfully!']");
    final By RateInlineAlert = By.xpath("//div[text()='Please enter a valid rate (max 15 digits total, 2 decimals).']");
    // Extra UI bits
    final By RefreshButton             = By.xpath("//button[normalize-space()='Refresh']");
    final By TableRoot                 = By.xpath("//div[contains(@class,'table') or contains(@class,'rt-table') or contains(@role,'table')]");
    final By SuccessToast              = By.xpath("//*[contains(@class,'toast') or contains(@class,'message')][not(self::script)]");

    // --------- Small helpers ----------
    private void safeClick(By by) {
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        scrollIntoView(el);
        el.click();
        //el.click();

    }
    public void BankSheetButtonClick() {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(BankSheetButton));
        scrollIntoView(el);
        el.click();
        //e

    }

    private void type(By by, String text) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        scrollIntoView(el);
        el.click();
        el.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        el.sendKeys(text);
    }
    private void selectFromDropdown(By dropdownInput, By optionToClick) {
        WebElement dd = wait.until(ExpectedConditions.elementToBeClickable(dropdownInput));
        scrollIntoView(dd);
        dd.click();
        WebElement opt = wait.until(ExpectedConditions.elementToBeClickable(optionToClick));
        scrollIntoView(opt);
        opt.click();
    }
    private void scrollIntoView(WebElement el) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", el);
        } catch (Exception ignored) {}
        actions.moveToElement(el).pause(Duration.ofMillis(100)).perform();
    }

    // --------- Page Actions ----------
    @Step("Open 'Add Bank' modal")
    public void openAddBankModal() {
        //safeClick(BankSheetButton);
        safeClick(AddBankButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(BankNameInput));
    }

    @Step("Fill Add Bank form")
    public void fillAddBankForm(String bankName, String accountNumber) {
        type(BankNameInput, bankName);
        selectFromDropdown(BankCountryDD,       BankCountryOptionNG);
        selectFromDropdown(EntityDD,            EntityOptionNG);
        type(AccountNumberInput, accountNumber);
        selectFromDropdown(CurrencyDD,          CurrencyOption);
//        selectFromDropdown(RateDD,              RateOption);
        selectFromDropdown(SegmentDD,           SegmentOption);
        selectFromDropdown(FacilityDD,          FacilityOption);
        selectFromDropdown(StatusDD,            StatusOption);                 // default is Active, but select explicitly
        selectFromDropdown(AccountRestrictionDD,AccountRestrictionOption);
        selectFromDropdown(RestrictionCategoryDD, RestrictionCategoryOption);
    }

    @Step("Verify Bank Successfully Added Popup")
    public boolean verifyPopup() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(BankSuccessfullyAddedPopup));
        } catch (TimeoutException ignored) {}
        return !driver.findElements(BankSuccessfullyAddedPopup).isEmpty();
    }
    @Step("Verify Bank Successfully Added Popup")
    public boolean verifydeletePopup() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(BankSuccessfullyDeletedPopup));
        } catch (TimeoutException ignored) {}
        return !driver.findElements(BankSuccessfullyDeletedPopup).isEmpty();
    }

    @Step("Verify Bank Successfully Added Popup")
    public boolean verifyUpdatePopup() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(BankSuccessfullyUpdatedPopup));
        } catch (TimeoutException ignored) {}
        return !driver.findElements(BankSuccessfullyUpdatedPopup).isEmpty();
    }


    @Step("Submit Add Bank")
    public void submitAddBank() {
        safeClick(AddBankButtonInner);
        // Wait for either toast or modal to close (input disappears)
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(BankNameInput));
        } catch (TimeoutException ignored) {}
        // Toast (if present) should be visible briefly
        try { wait.until(ExpectedConditions.visibilityOfElementLocated(SuccessToast)); } catch (TimeoutException ignored) {}
    }

    @Step("Add new bank end-to-end")
    public void addBank(String bankName, String accountNumber) {
        openAddBankModal();
        fillAddBankForm(bankName, accountNumber);
        submitAddBank();
    }

    @Step("Delete Bank")
    public void deleteBank(String accountNumber) {
        // 1) Search for the bank by account number
        searchAccount(accountNumber);

        // 2) Locate the element you want to hover over (Restricted inline edit cell)
        WebElement restrictedCell = wait.until(
                ExpectedConditions.visibilityOfElementLocated(RestrictedInlineEdit)
        );

        // 3) Create an Actions object and hover over the element
        Actions actions = new Actions(driver);
        actions.moveToElement(restrictedCell).build().perform();

        // 4) Now click the Delete Bank button that appears on hover
        WebElement deleteBtn = wait.until(
                ExpectedConditions.elementToBeClickable(BtnDeleteBank)
        );
        deleteBtn.click();
        WebElement deleteBtnOk = wait.until(
                ExpectedConditions.elementToBeClickable(InnerDeleteOk)
        );
        deleteBtnOk.click();
//        deleteBtnOk.sendKeys(Keys.ENTER);

    }

    @Step("Refresh list")
    public void refreshList() {
        safeClick(RefreshButton);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(TableRoot));
    }

    @Step("Check if account number '{0}' is present in table")
    public boolean refreshList(String accountNumber, String restrictedOption) {
        // capture current status text
        searchAccount(accountNumber);
        String before = wait.until(ExpectedConditions.visibilityOfElementLocated(RestrictedInlineEdit)).getText().trim();
        wait.until(ExpectedConditions.presenceOfElementLocated(RestrictedInlineEdit));
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(RestrictedInlineEdit));
        scrollIntoView(el);
        el.click();
        //safeClick(RestrictedInlineEdit);// open editor
        safeClick(optionByText(restrictedOption));
        // Cancel
        safeClick(RefreshButton);
        // value should remain same
        String after = wait.until(ExpectedConditions.visibilityOfElementLocated(RestrictedInlineEdit)).getText().trim();
        return before.equals(after);
    }
    // Reusable picker for inline dropdowns (options by visible text)
    private By optionByText(String text) {
        return By.xpath("//div[normalize-space()='" + text + "']");
    }

    // Edit-mode detection (row inputs / Save/Cancel)
    //private final By AnyRowInput = By.xpath("(//div[@role='rowgroup']//input)[1]");

    @Step("Search account number in grid: {0}")
    public void searchAccount(String accountNumber) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(SearchInput));
        scrollIntoView(input);
        input.click();
        input.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        input.sendKeys(accountNumber);

        // wait for the row that contains this account number to render
        By rowWithAccount = By.xpath(
                "//div[@role='row' and .//*[self::div or self::span or self::td][normalize-space()='" + accountNumber + "']]"
        );
        wait.until(ExpectedConditions.presenceOfElementLocated(rowWithAccount));
    }

    @Step("Inline edit Restricted to: {0}")
    public void inlineEditRestricted(String optionText) {
        wait.until(ExpectedConditions.presenceOfElementLocated(RestrictedInlineEdit));
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(RestrictedInlineEdit));
        scrollIntoView(el);
        el.click();
        //safeClick(RestrictedInlineEdit);// open editor
        safeClick(optionByText(optionText));           // pick value (e.g., 'Unrestricted' or 'Restricted')
    }

    @Step("Inline edit Facility to: {0}")
    public void inlineEditFacility(String optionText) {
        //scrollIntoView(el);
        WebElement ScrollRight = wait.until(
                ExpectedConditions.presenceOfElementLocated(CountryLabel)
        );
        Actions actions = new Actions(driver);
        actions.moveToElement(ScrollRight).perform();
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(FacilityInlineEdit));
        el.click();// open editor
        safeClick(optionByText(optionText));           // e.g., 'Cauris' or 'Cordiant'
    }

    @Step("Inline edit Status to: {0}")
    public void inlineEditStatus(String optionText) {
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(StatusInlineEdit));
        scrollIntoView(el);
        el.click();
        safeClick(optionByText(optionText));           // e.g., 'Dormant' or 'Active'
    }

    @Step("Save inline edits")
    public void saveInlineEdits() {
        safeClick(BtnSave);
        // Wait until edit mode disappears (no Save button / no inputs in row)
        try { wait.until(ExpectedConditions.invisibilityOfElementLocated(BtnSave)); } catch (TimeoutException ignored) {}
        //try { wait.until(ExpectedConditions.invisibilityOfElementLocated(AnyRowInput)); } catch (TimeoutException ignored) {}
    }

    @Step("Verify row is not in inline-edit mode")
    public boolean isInlineEditClosed() {
        boolean saveGone    = driver.findElements(BtnSave).isEmpty();
        boolean cancelGone  = driver.findElements(BtnCancel).isEmpty();
        //boolean inputsGone  = driver.findElements(AnyRowInput).isEmpty();

        return saveGone && cancelGone;
    }

    @Step("Full flow: search account and update Restricted/Facility/Status, then save")
    public void searchAndInlineUpdate(String accountNumber,
                                      String restrictedOption,
                                      String facilityOption,
                                      String statusOption) {
        searchAccount(accountNumber);
        inlineEditRestricted(restrictedOption);
        inlineEditFacility(facilityOption);
        //inlineEditStatus(statusOption);
        saveInlineEdits();
    }
    // --- helpers for hedge modal ---
    @Step("Open Update Hedge Rates modal")
    public void openUpdateHedgeRates() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(UpdateHedgeRatesButton));
        scrollIntoView(btn);
        btn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(HedgeCurrencyDD));
    }

    @Step("Select hedge currency option")
    public void selectHedgeCurrency() {
        // Uses your provided static option (KE_HEDGE444)
        WebElement dd = wait.until(ExpectedConditions.elementToBeClickable(HedgeCurrencyDD));
        scrollIntoView(dd);
        dd.click();
        WebElement opt = wait.until(ExpectedConditions.elementToBeClickable(HedgeCurrencyOption));
        opt.click();
    }

    @Step("Type hedge rate: {0}")
    public void typeHedgeRate(String rate) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(HedgeRateInput));
        scrollIntoView(input);
        input.click();
        input.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        input.sendKeys(rate);
    }

    @Step("Click inner Update Hedge Rate action")
    public void clickUpdateHedgeRateInner() {
        WebElement inner = wait.until(ExpectedConditions.elementToBeClickable(UpdateHedgeRatesButtonInner));
        scrollIntoView(inner);
        inner.click();
    }

    @Step("Confirm OK in dialog (if present)")
    public void clickOkDialogIfVisible() {
        try {
            WebElement ok = wait.until(ExpectedConditions.elementToBeClickable(InnerDeleteOk));
            ok.click();
        } catch (TimeoutException ignore) {
            // some environments may auto-close; that's fine
        }
    }

    @Step("Wait for 'Rate Updated Successfully' popup")
    public boolean waitForRateUpdatedPopup() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(RateUpdatedSuccessfullyPopup));
            return true;
        } catch (TimeoutException e) {}
        return driver.findElements(RateUpdatedSuccessfullyPopup).isEmpty();
    }

    @Step("Read current hedge rate value from input")
    public String readHedgeRateValue() {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(HedgeRateInput));
        return input.getAttribute("value").trim();
    }

    /** Full E2E: update hedge rate for KE_HEDGE444 and verify persisted */
    @Step("Update hedge rate and verify persisted value")
    public boolean updateHedgeRateAndVerify() {
        // Generate random 3-digit rate
        String newRate = String.valueOf((int)(Math.random() * 900) + 100); // 100-999
        //safeClick(BankSheetButton);
        // 1) Open modal & set values
        openUpdateHedgeRates();
        selectHedgeCurrency();
        typeHedgeRate(newRate);
        clickUpdateHedgeRateInner();
        clickOkDialogIfVisible();

        boolean pageRefreshed = false;
        // keep refreshing until the validation method returns true
        while (!pageRefreshed) {
            driver.navigate().refresh();
            pageRefreshed = pageReloadValidate();
        }
        openUpdateHedgeRates();
        selectHedgeCurrency();                 // ensure the same currency context
        String current = readHedgeRateValue(); // value in the input
        // optionally close modal with Esc:
        try { actions.sendKeys(Keys.ESCAPE).perform(); } catch (Exception ignored) {}

        return newRate.equals(current);
    }
    // ===== Utility =====
    private boolean isVisible(By by, int sec) {
        try { driver.findElements(by).isEmpty(); return false; }
        catch (TimeoutException e) { return true; }
    }
    private boolean isInvisible(By by, int sec) {
        try { new WebDriverWait(driver, Duration.ofSeconds(sec))
                .until(ExpectedConditions.invisibilityOfElementLocated(by)); return true; }
        catch (TimeoutException e) { return false; }
    }

// ===== Add Bank NEGATIVE helpers =====

    // Click Add with empty form -> modal should remain, success popup should NOT appear
    @Step("Try submitting empty Add Bank form and expect validation prevents close")
    public boolean addBank_RequiredFields_Block() {
        openAddBankModal();
        safeClick(AddBankButtonInner); // click with all empty
        boolean modalStillOpen = verifyPopup();                   // modal not closed
        //boolean noSuccess      = driver.findElements(BankSuccessfullyAddedPopup).isEmpty();
        return modalStillOpen;
    }

    // Invalid account number format (you choose the bad string)
    @Step("Try Add Bank with invalid Account Number and expect failure")
    public boolean addBank_InvalidAccountNumber_Block(String invalidAcc) {
        openAddBankModal();
        type(BankNameInput, "Invalid Acc Test");
        selectFromDropdown(BankCountryDD, BankCountryOptionNG);
        selectFromDropdown(EntityDD,      EntityOptionNG);
        type(AccountNumberInput, invalidAcc);                 // <-- invalid
        selectFromDropdown(CurrencyDD,    CurrencyOption);
        selectFromDropdown(SegmentDD,     SegmentOption);
        selectFromDropdown(FacilityDD,    FacilityOption);
        selectFromDropdown(StatusDD,      StatusOption);
        selectFromDropdown(AccountRestrictionDD, AccountRestrictionOption);
        selectFromDropdown(RestrictionCategoryDD, RestrictionCategoryOption);

        safeClick(AddBankButtonInner);

        // expect the modal to remain and no success popup
//        boolean noSuccess      = driver.findElements(BankSuccessfullyAddedPopup).isEmpty();
//        return modalStillOpen(BankNameInput) && noSuccess;
        return bankPopUp();
    }

    public boolean bankPopUp(){

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(BankSuccessfullyAddedPopup));
        }catch (Exception e){}
        //System.out.println("-----------------check "+ driver.findElements(BankSuccessfullyAddedPopup).isEmpty());
        return driver.findElements(BankSuccessfullyAddedPopup).isEmpty();
    }

    // Duplicate account number not allowed
    @Step("Try Add Bank with duplicate Account Number and expect failure")
    public boolean addBank_DuplicateAccount_Block(String bankName, String duplicateAcc) {
        // First add valid record
        addBank(bankName, duplicateAcc);
        // ensure success
        if (!verifyPopup()) return false;

        // Try again with same account number
        openAddBankModal();
        type(BankNameInput, bankName);
        selectFromDropdown(BankCountryDD, BankCountryOptionNG);
        selectFromDropdown(EntityDD,      EntityOptionNG);
        type(AccountNumberInput, duplicateAcc); // same as before
        selectFromDropdown(CurrencyDD,    CurrencyOption);
        selectFromDropdown(RateDD,              RateOption);
        selectFromDropdown(SegmentDD,     SegmentOption);
        selectFromDropdown(FacilityDD,    FacilityOption);
        selectFromDropdown(StatusDD,      StatusOption);
        selectFromDropdown(AccountRestrictionDD, AccountRestrictionOption);
        selectFromDropdown(RestrictionCategoryDD, RestrictionCategoryOption);

        safeClick(AddBankButtonInner);

        return bankPopUp();
    }

// ===== Inline Edit NEGATIVE helpers =====

    // Rollback on inline update failure -> simulate by opening edit then Cancel
    @Step("Open inline edit and click Cancel; verify Status value unchanged")
    public boolean inlineEdit_Rollback_OnCancel(String accountNumber,
                                                String restrictedOption) {
        // capture current status text
        searchAccount(accountNumber);
        String before = wait.until(ExpectedConditions.visibilityOfElementLocated(RestrictedInlineEdit)).getText().trim();
        wait.until(ExpectedConditions.presenceOfElementLocated(RestrictedInlineEdit));
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(RestrictedInlineEdit));
        scrollIntoView(el);
        el.click();
        //safeClick(RestrictedInlineEdit);// open editor
        safeClick(optionByText(restrictedOption));
        // Cancel
        safeClick(BtnCancel);
        // value should remain same
        String after = wait.until(ExpectedConditions.visibilityOfElementLocated(RestrictedInlineEdit)).getText().trim();
        return before.equals(after);
    }

// ===== Hedge Rate NEGATIVE helpers =====

    // Required fields validation on hedge modal (no currency/rate -> block)
    @Step("Hedge update with empty fields should be blocked")
    public boolean hedgeUpdate_RequiredFields_Block() {
        //safeClick(BankSheetButton);
        openUpdateHedgeRates();
        // do not select currency or rate
        clickUpdateHedgeRateInner();

        return waitForRateUpdatedPopup();
    }

    // BA_007: invalid rate format (alpha/negative) should be blocked
    @Step("Hedge update with invalid rate should be blocked")
    public boolean hedgeUpdate_InvalidRate_Block(String invalidRateAlpha ,String invalidRateNegative) {
        //safeClick(BankSheetButton);
        openUpdateHedgeRates();
        selectHedgeCurrency();            // currency chosen
        typeHedgeRate(invalidRateAlpha);
        clickUpdateHedgeRateInner();
        boolean alphaResult = waitForRateUpdatedPopup();
        WebElement in = wait.until(ExpectedConditions.visibilityOfElementLocated(HedgeRateInput));
        in.clear();
        typeHedgeRate(invalidRateNegative);
        clickUpdateHedgeRateInner();
        boolean negativeResult = waitForRateUpdatedPopup();
        return alphaResult && negativeResult;
    }
    // Pages/BankApp/BankAppRefreshListPage.java

    @Step("Hedge update with invalid rate '{0}' should be blocked")
    public void hedgeUpdate_InvalidRate_Block(String invalidRate) {
        // open modal fresh each time
        //safeClick(BankSheetButton);
        openUpdateHedgeRates();

        // select currency, enter invalid rate, submit
        selectHedgeCurrency();
        typeHedgeRate(invalidRate);
        //clickUpdateHedgeRateInner();

        // Negative expectation: modal still visible, no success popup
        //boolean inlineAlert = isVisible(RateInlineAlert, 2);
//        boolean noSuccess = driver.findElements(RateUpdatedSuccessfullyPopup).isEmpty();

        // close modal for next iteration
        try { actions.sendKeys(Keys.ESCAPE).perform(); } catch (Exception ignored) {}

        //return inlineAlert;
    }

    public boolean inlineAlertPopup(){

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(RateInlineAlert));
        }catch (Exception e){}
        return driver.findElements(RateInlineAlert).isEmpty();
    }

    public boolean pageReloadValidate(){
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(AddBankButton));
        }catch (Exception e){}
        return !driver.findElements(AddBankButton).isEmpty();
    }

}
