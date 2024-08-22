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

public class TransferFundsTest {

    public WebDriver driver;
    public WebDriverWait wait;

    static ExtentSparkReporter info = new ExtentSparkReporter("target/Reporte-TransferenciaFondos.html");
    static ExtentReports extent;

    public void login() throws InterruptedException {
        TransferFundsPage transferFundsPage = new TransferFundsPage(driver, wait);
        transferFundsPage.writeUser("Sergio42");
        transferFundsPage.writePasswordlogin("123456");

        transferFundsPage.login();
        if(transferFundsPage.error().matches("Error!")) {
            transferFundsPage.beginRegister();
            transferFundsPage.writeFirstName("Sergio");
            transferFundsPage.writeLastName("Pace");
            transferFundsPage.writeAddress("Av 1 # 45 - 68");
            transferFundsPage.writeCity("Tunja");
            transferFundsPage.writeState("Boyacá");
            transferFundsPage.writeZipCode("111911");
            transferFundsPage.writePhone("7626543");
            transferFundsPage.writeSsn("123");
            transferFundsPage.writeUsername("Sergio4");
            transferFundsPage.writePassword("123456");
            transferFundsPage.confirmPassword("123456");

            transferFundsPage.clickRegister();

            transferFundsPage.writeUser("Sergio4");
            transferFundsPage.writePasswordlogin("123456");
            transferFundsPage.login();
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

        TransferFundsPage transferFundsPage = new TransferFundsPage(driver, wait);
        driver.manage().window().maximize();
        transferFundsPage.url("https://parabank.parasoft.com/parabank/index.htm");
        Thread.sleep(1000);

        login();
    }

    @Test
    @Tag("TRANSFERENCIA")
    public void transferFundsExitoso() throws InterruptedException {
        ExtentTest test = extent.createTest("Transferencia de Fondos Exitoso");
        test.log(Status.INFO, "Comienza nuestro test de transferencia de fondos");

        TransferFundsPage transferFundsPage = new TransferFundsPage(driver, wait);
        try {
            transferFundsPage.clickBeginTransfer();
            test.log(Status.PASS, "Se dió click en Transferencia de fondos");
            transferFundsPage.readTitle();
            test.log(Status.PASS, "Se comprobó el texto 'Transferir fondos'");
            transferFundsPage.writeAmount("99999");
            test.log(Status.PASS, "Se ingresó el importe a transferir");
            transferFundsPage.setFrom("28773");
            test.log(Status.PASS, "Se seleccionó la cuenta de origen");
            transferFundsPage.setTo("28884");
            test.log(Status.PASS, "Se seleccionó la cuenta de destino");
            transferFundsPage.clickCompleteTransfer();
            test.log(Status.PASS, "Se dió click en Transferencia");

            Thread.sleep(1000);
            if (transferFundsPage.completeMessage().equals("Transfer Complete!")) {
                test.log(Status.PASS, "Validación del mensaje de transferencia completa");
            } else {
                test.log(Status.FAIL, "Fallo el mensaje de transferencia completa");
            }

        } catch (Exception error) {
            test.log(Status.FAIL, "Se produjo una excepción durante la ejecución del test: " + error.getMessage());
        }

    }

    @AfterEach
    public void cerrar() {
        TransferFundsPage transferFundsPage = new TransferFundsPage(driver, wait);
        transferFundsPage.close();
    }

    @AfterAll
    public static void saveReport() {
        extent.flush();
    }


}
