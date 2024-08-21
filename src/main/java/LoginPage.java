import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {
    private By user = By.xpath("//*[@id=\"loginPanel\"]/form/div[1]/input");
    private By password = By.xpath("//*[@id=\"loginPanel\"]/form/div[2]/input");

    private By login = By.xpath("//*[@id=\"loginPanel\"]/form/div[3]/input");

    private By loginerror = By.xpath("//*[@id=\"rightPanel\"]/h1");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void writeUser(String name) throws InterruptedException {
        this.sendText(name, user);
    }

    public void writePassword (String contraseña) throws InterruptedException {
        this.sendText(contraseña, password);
    }

    public void login() throws InterruptedException  {
        this.click(login);
    }

    public String error() throws InterruptedException {
        System.out.println("Resultado Card value: " + this.getText(loginerror));
        return this.findElement(loginerror).getText();
    }
}
