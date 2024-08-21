import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterTest {
    public WebDriver driver;
    public WebDriverWait wait;


    @BeforeEach
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(3000));

        RegisterPage registerPage = new RegisterPage(driver, wait);
        driver.manage().window().maximize();
        registerPage.url("https://parabank.parasoft.com/parabank/index.htm");
        Thread.sleep(1000);
    }

    @Test
    public void registerTestExitoso() throws InterruptedException {
        RegisterPage registerPage = new RegisterPage(driver, wait);

        registerPage.beginRegister();
        registerPage.writeFirstName("Sergio");
        registerPage.writeLastName("Pace");
        registerPage.writeAddress("Av 1 # 45 - 68");
        registerPage.writeCity("Tunja");
        registerPage.writeState("Boyacá");
        registerPage.writeZipCode("111911");
        registerPage.writePhone("7626543");
        registerPage.writeSsn("123");
        registerPage.writeUsername("Sergio4");
        registerPage.writePassword("123456");
        registerPage.confirmPassword("123456");

        registerPage.clickRegister();

        registerPage.welcome();
        registerPage.success();

    }

    @Test
    public void registerTestFallido() throws InterruptedException {
        RegisterPage registerPage = new RegisterPage(driver, wait);

        registerPage.beginRegister();
        registerPage.writeFirstName("Sergio");
        registerPage.writeLastName("Pace");
        registerPage.writeAddress("Av 1 # 45 - 68");
        registerPage.writeCity("Tunja");
        registerPage.writeState("Boyacá");
        registerPage.writeZipCode("111911");
        registerPage.writePhone("7626543");
        registerPage.writeSsn("123");
        registerPage.writeUsername("Sergio123");
        registerPage.writePassword("123456");
        registerPage.confirmPassword("1234567");

        registerPage.clickRegister();
        Thread.sleep(1000);
        registerPage.matchingPasswords();

    }

    @AfterEach
    public void cerrar() {
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.close();
    }
}