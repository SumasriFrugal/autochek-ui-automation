package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

public class BaseClass {
    public static WebDriver driver;

    protected String UserName = "suhail+1@frugaltestingin.com";
    protected String Password = "Autochek@123";
    protected String URL = "https://dealerplus.staging.myautochek.com/auth/sign-in";

    public void SetUp() {

        ChromeOptions options = new ChromeOptions();

        // Always use a unique temporary user data directory to avoid session conflicts
        String userDataDir = System.getProperty("java.io.tmpdir") + File.separator + "chrome-profile-" + System.currentTimeMillis();
        options.addArguments("--user-data-dir=" + userDataDir);

        // Recommended flags for CI environments (like GitHub Actions)
        options.addArguments("--headless=new");          // Run Chrome in headless mode
        options.addArguments("--no-sandbox");            // Disable the sandbox for CI
        options.addArguments("--disable-dev-shm-usage"); // Prevent /dev/shm issues
        options.addArguments("--disable-gpu");           // Disable GPU acceleration
        options.addArguments("--window-size=1920,1080"); // Ensure consistent viewport

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(URL);
    }
}
