import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import reports.ExtentFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NewAccountTest {

    public WebDriver driver;
    public WebDriverWait wait;

    static ExtentSparkReporter info = new ExtentSparkReporter("target/Reporte-NuevaCuenta.html");
    static ExtentReports extent;

    public void login() throws InterruptedException {
        NewAccountPage newAccountPage = new NewAccountPage(driver, wait);
        newAccountPage.writeUser("Sergio42");
        newAccountPage.writePasswordlogin("123456");

        newAccountPage.login();
        if(newAccountPage.error().matches("Error!")) {
            newAccountPage.beginRegister();
            newAccountPage.writeFirstName("Sergio");
            newAccountPage.writeLastName("Pace");
            newAccountPage.writeAddress("Av 1 # 45 - 68");
            newAccountPage.writeCity("Tunja");
            newAccountPage.writeState("Boyacá");
            newAccountPage.writeZipCode("111911");
            newAccountPage.writePhone("7626543");
            newAccountPage.writeSsn("123");
            newAccountPage.writeUsername("Sergio4");
            newAccountPage.writePassword("123456");
            newAccountPage.confirmPassword("123456");

            newAccountPage.clickRegister();

            newAccountPage.writeUser("Sergio4");
            newAccountPage.writePasswordlogin("123456");
            newAccountPage.login();
        }
    }

    @BeforeAll
    public static void createReport() {
        //report
        extent = ExtentFactory.getInstance();
        extent.attachReporter(info);
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
    @Tag("NUEVA_CUENTA")
    public void newAccountExitoso() throws InterruptedException {
        ExtentTest test = extent.createTest("Nueva cuenta Exitoso");
        test.log(Status.INFO, "Comienza nuestro test de crear nueva cuenta");

        NewAccountPage newAccountPage = new NewAccountPage(driver, wait);
        try {

            newAccountPage.clickOpen();
            test.log(Status.PASS, "Se dió click en Abrir nueva cuenta");
            newAccountPage.writeType("SAVINGS");
            test.log(Status.PASS, "Se seleccionó tipo de cuenta SAVINGS");
            newAccountPage.clickOpenAccount();
            test.log(Status.PASS, "Se dió click en Abrir nueva cuenta");

            Thread.sleep(1000);

            if(newAccountPage.nowOpen().equals("Congratulations, your account is now open.")){
                test.log(Status.PASS, "Se comprobó el texto 'Congratulations, your account is now open.' visible en pantalla");
            }else{
                test.log(Status.FAIL, "No es visible el texto 'Congratulations, your account is now open.'en pantalla");
            }


        } catch (Exception error) {
            test.log(Status.FAIL, "Se produjo una excepción durante la ejecución del test: " + error.getMessage());
        }

    }

    @AfterEach
    public void cerrar() {
        NewAccountPage newAccountPage = new NewAccountPage(driver, wait);
        newAccountPage.close();
    }

    @AfterAll
    public static void saveReport() {
        extent.flush();
    }

}
