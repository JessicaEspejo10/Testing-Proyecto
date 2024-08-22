import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.platform.commons.function.Try;
import reports.ExtentFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AccountOverviewTest {

    public WebDriver driver;
    public WebDriverWait wait;

    static ExtentSparkReporter info = new ExtentSparkReporter("target/Reporte-ResumenCuenta.html");
    static ExtentReports extent;

    public void login() throws InterruptedException {
        AccountsOverviewPage overviewPage = new AccountsOverviewPage(driver, wait);
        overviewPage.writeUser("Sergio42");
        overviewPage.writePasswordlogin("123456");

        overviewPage.login();
        if(overviewPage.error().matches("Error!")) {
            overviewPage.beginRegister();
            overviewPage.writeFirstName("Sergio");
            overviewPage.writeLastName("Pace");
            overviewPage.writeAddress("Av 1 # 45 - 68");
            overviewPage.writeCity("Tunja");
            overviewPage.writeState("Boyacá");
            overviewPage.writeZipCode("111911");
            overviewPage.writePhone("7626543");
            overviewPage.writeSsn("123");
            overviewPage.writeUsername("Sergio4");
            overviewPage.writePassword("123456");
            overviewPage.confirmPassword("123456");

            overviewPage.clickRegister();

            overviewPage.writeUser("Sergio4");
            overviewPage.writePasswordlogin("123456");
            overviewPage.login();
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

        AccountsOverviewPage overviewPage = new AccountsOverviewPage(driver, wait);
        driver.manage().window().maximize();
        overviewPage.url("https://parabank.parasoft.com/parabank/index.htm");
        Thread.sleep(1000);

        login();
    }

    @Test
    @Tag("RESUMEN")
    public void overviewExitoso() throws InterruptedException {
        ExtentTest test = extent.createTest("Resumen Exitoso");
        test.log(Status.INFO, "Comienza nuestro test de resumen de cuenta");

        try {
            AccountsOverviewPage overviewPage = new AccountsOverviewPage(driver, wait);
            overviewPage.clickOverview();
            test.log(Status.PASS, "Se dió click en Resumen de cuentas");

            if (overviewPage.accountNotes().equals("*Balance includes deposits that may be subject to holds")) {
                test.log(Status.PASS, "Se comprobó el texto 'el saldo incluye depósitos que pueden estar sujetos a retenciones' visible en pantalla");
            }else {
                test.log(Status.FAIL, "El texto 'el saldo incluye depósitos que pueden estar sujetos a retenciones' no es visible en pantalla");
            }

            overviewPage.clickAccountNumber();
            test.log(Status.PASS, "Se dió click en la columna Cuenta");

            if (overviewPage.accountDet().equals("Account Details")) {
                test.log(Status.PASS, "Se comprobó que el texto 'Detalles de la cuenta' sea visible en pantalla");
            }else {
                test.log(Status.FAIL, "El texto 'Detalles de la cuenta' no es visible en pantalla");
            }

            overviewPage.writePeriod("All");
            test.log(Status.PASS, "Se seleccionó 'All' en periodo de actividad");
            overviewPage.writeType("All");
            test.log(Status.PASS, "Se seleccionó 'All' en el tipo");
            overviewPage.clickGo();
            test.log(Status.PASS, "Se dió click en Ir");

        } catch (Exception error) {
            test.log(Status.FAIL, "Se produjo una excepción durante la ejecución del test: " + error.getMessage());
        }
    }

    @AfterEach
    public void cerrar() {
        AccountsOverviewPage overviewPage = new AccountsOverviewPage(driver, wait);
        overviewPage.close();
    }

    @AfterAll
    public static void saveReport() {
        extent.flush();
    }

}
