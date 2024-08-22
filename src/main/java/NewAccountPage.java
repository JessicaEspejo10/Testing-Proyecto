import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewAccountPage extends RegisterPage{

    private By openNewAccount = By.linkText("Open New Account");
    private By accountType = By.xpath("//*[@id=\"type\"]");
    private By openButtom = By.xpath("//*[@id=\"openAccountForm\"]/form/div/input");

    private By congrats = By.xpath("//*[@id=\"openAccountResult\"]/p[1]");

    protected NewAccountPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void clickOpen() throws InterruptedException  {
        this.click(openNewAccount);
    }

    public void writeType(String type) throws InterruptedException {
        this.sendKey(type,accountType);
    }

    public void clickOpenAccount() throws InterruptedException  {
        this.click(openButtom);
    }

    public String nowOpen() throws InterruptedException  {
        System.out.println("Card message: " + this.getText(congrats));
        return this.getText(congrats);
    }


    //login
    private By user = By.xpath("//*[@id=\"loginPanel\"]/form/div[1]/input");
    private By passwordlogin = By.xpath("//*[@id=\"loginPanel\"]/form/div[2]/input");

    private By login = By.xpath("//*[@id=\"loginPanel\"]/form/div[3]/input");

    private By loginerror = By.xpath("//*[@id=\"rightPanel\"]/h1");


    public void writeUser(String name) throws InterruptedException {
        this.sendText(name, user);
    }

    public void writePasswordlogin(String contraseña) throws InterruptedException {
        this.sendText(contraseña, passwordlogin);
    }

    public void login() throws InterruptedException {
        this.click(login);
    }

    public String error() throws InterruptedException {
        System.out.println("Resultado Card value: " + this.getText(loginerror));
        return this.findElement(loginerror).getText();
    }
}
