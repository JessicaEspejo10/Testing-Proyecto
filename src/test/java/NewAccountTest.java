import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NewAccountTest {
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

        NewAccountPage newAccountPage = new NewAccountPage(driver, wait);
        driver.manage().window().maximize();
        newAccountPage.url("https://parabank.parasoft.com/parabank/index.htm");
        Thread.sleep(1000);

        login();
    }

    @Test
    public void newAccountExitoso() throws InterruptedException {
        NewAccountPage newAccountPage = new NewAccountPage(driver, wait);

        newAccountPage.clickOpen();
        newAccountPage.writeType("SAVINGS");
        newAccountPage.clickOpenAccount();
        newAccountPage.nowOpen();

    }

    @AfterEach
    public void cerrar() {
        NewAccountPage newAccountPage = new NewAccountPage(driver, wait);
        newAccountPage.close();
    }

}
