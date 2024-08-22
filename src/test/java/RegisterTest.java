import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import reports.ExtentFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterTest {

    public WebDriver driver;
    public WebDriverWait wait;

    static ExtentSparkReporter info = new ExtentSparkReporter("target/Reporte-Registro.html");
    static ExtentReports extent;

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

        RegisterPage registerPage = new RegisterPage(driver, wait);
        driver.manage().window().maximize();
        registerPage.url("https://parabank.parasoft.com/parabank/index.htm");
        Thread.sleep(1000);
    }

    @Test
    @Tag("REGISTRO")
    public void registerTestExitoso() throws InterruptedException {
        ExtentTest test = extent.createTest("Registro Exitoso");
        test.log(Status.INFO, "Comienza nuestro test de registro exitoso");

        RegisterPage registerPage = new RegisterPage(driver, wait);

        try {
            registerPage.beginRegister();
            test.log(Status.PASS, "Se dió click en Registro");
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
            test.log(Status.PASS, "Se completaron los datos del registro");

            registerPage.clickRegister();
            test.log(Status.PASS, "Se dió click en Registro");

            if (registerPage.welcome().equals("Signing up is easy!")) {
                test.log(Status.PASS, "Validación texto de bienvenida Exitoso");
            } else {
                test.log(Status.FAIL, "Falló el mensaje de bienvenida");
            }

            if (registerPage.success().equals("If you have an account with us you can sign-up for free instant online access. You will have to provide some personal information.")) {
                test.log(Status.PASS, "Validación de mensaje de Registro Exitoso");
            } else {
                test.log(Status.FAIL, "Falló el mensaje de Registro Exitoso");
            }

        } catch (Exception error) {
            test.log(Status.FAIL, "Se produjo una excepción durante la ejecución del test: " + error.getMessage());
        }

    }

    @Test
    @Tag("REGISTRO")
    public void registerTestFallido() throws InterruptedException {
        ExtentTest test = extent.createTest("Registro Fallido");
        test.log(Status.INFO, "Comienza nuestro test de registro fallido");

        RegisterPage registerPage = new RegisterPage(driver, wait);
        try {
            registerPage.beginRegister();
            test.log(Status.PASS, "Se dió click en Registro");

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
            test.log(Status.PASS, "Se completaron los datos del registro");
            registerPage.clickRegister();
            test.log(Status.PASS, "Se dió click en Registro");
            Thread.sleep(1000);

            if (registerPage.matchingPasswords().equals("Passwords did not match.")){
                test.log(Status.PASS, "Validación de texto de contraseñas no coinciden");
            } else {
                test.log(Status.FAIL, "Falló el texto de contraseñas no coinciden");
            }

        } catch (Exception error) {
            test.log(Status.FAIL, "Se produjo una excepción durante la ejecución del test: " + error.getMessage());
        }

    }

    @AfterEach
    public void cerrar() {
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.close();
    }

    @AfterAll
    public static void saveReport() {
        extent.flush();
    }

}