import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TransferFundsTest {

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

        TransferFundsPage transferFundsPage = new TransferFundsPage(driver, wait);
        driver.manage().window().maximize();
        transferFundsPage.url("https://parabank.parasoft.com/parabank/index.htm");
        Thread.sleep(1000);

        login();
    }

    @Test
    public void transferFundsExitoso() throws InterruptedException {
        TransferFundsPage transferFundsPage = new TransferFundsPage(driver, wait);
        transferFundsPage.clickBeginTransfer();
        transferFundsPage.readTitle();
        transferFundsPage.writeAmount("99999");
        transferFundsPage.setFrom("28773");
        transferFundsPage.setTo("28884");
        transferFundsPage.clickCompleteTransfer();



    }

    @AfterEach
    public void cerrar() {
        TransferFundsPage transferFundsPage = new TransferFundsPage(driver, wait);
        transferFundsPage.close();
    }



}
