package Base;

import io.qameta.allure.Allure;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

public class BaseClass {
    public static WebDriver driver;

    protected String AccountManagerUserName = "sumasri@frugaltesting.com";
    protected String AccountManagerPassword = "AutochekF123";
    protected String FranchiseAdminUserName = "suhail@frugaltestingin.com";
    protected String FranchiseAdminPassword = "Autochek@123";
    protected String URL = "https://dealerplus.staging.myautochek.com/auth/sign-in";
    protected String MT940AdminUserName = "suhail+10@frugaltestingin.com";
    protected String MT940AdminPassword = "Autochek@123";
    protected String URLRetool = "https://autochek.retool.com/p/i-login/homepage?_environment=staging";

    // Generate a unique Chrome profile directory for each session
    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();

        // CI-safe flags
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-notifications");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--force-device-scale-factor=1");
        options.addArguments("--high-dpi-support=1");
        options.addArguments("--start-maximized");

        // Run headless in CI
        if (System.getenv("GITHUB_ACTIONS") != null) {
            options.addArguments("--headless=new");
        }

        // Unique profile
        String tmpDir = System.getProperty("java.io.tmpdir");
        File uniqueProfile = new File(tmpDir, "chrome-profile-" + System.currentTimeMillis());
        uniqueProfile.mkdirs();
        options.addArguments("--user-data-dir=" + uniqueProfile.getAbsolutePath());

        return options;
    }

    // Start browser (DealerPlus)
    public void SetUp() {
        if (driver == null) {
            ChromeOptions options = getChromeOptions();
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        }
        driver.get(URL);
//        ((JavascriptExecutor) driver).executeScript("document.body.style.zoom='100%';");
    }

    // Start browser (Retool)
    public void SetUpRetool() {
        if (driver == null) {
            ChromeOptions options = getChromeOptions();
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        }
        driver.get(URLRetool);
//        ((JavascriptExecutor) driver).executeScript("document.body.style.zoom='100%';");
    }

    // Quit browser safely
    public void TearDown() {
        if (driver != null) {
            try {
                driver.quit();
            } finally {
                driver = null;
            }
        }
    }
}
