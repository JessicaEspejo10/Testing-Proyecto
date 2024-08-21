import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AccountOverviewTest {

    public WebDriver driver;
    public WebDriverWait wait;

    public void login() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.writeUser("Sergio42");
        loginPage.writePassword("123456");

        loginPage.login();
        if(loginPage.error().matches("Error!")) {
            register();
            loginPage.writeUser("Sergio4");
            loginPage.writePassword("123456");
            loginPage.login();
        }
    }

    public void register() throws InterruptedException {
        RegisterTest registerTest = new RegisterTest();
        registerTest.setUp();
        registerTest.registerTestExitoso();
        registerTest.cerrar();
    }

    @BeforeEach
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(3000));

        AccountsOverviewPage overviewPage = new AccountsOverviewPage(driver, wait);
        driver.manage().window().maximize();
        overviewPage.url("https://parabank.parasoft.com/parabank/index.htm");
        Thread.sleep(1000);

        login();
    }

    @Test
    public void overviewExitoso() throws InterruptedException {
        AccountsOverviewPage overviewPage = new AccountsOverviewPage(driver, wait);
        overviewPage.clickOverview();
        overviewPage.accountNotes();
        overviewPage.clickAccountNumber();
        overviewPage.accountDet();

        overviewPage.writePeriod("All");
        overviewPage.writeType("All");

        overviewPage.clickGo();
    }

    @AfterEach
    public void cerrar() {
        AccountsOverviewPage overviewPage = new AccountsOverviewPage(driver, wait);
        overviewPage.close();
    }

}
