import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TransferFundsPage extends RegisterPage {
    private By beginTransfer = By.xpath("//*[@id=\"leftPanel\"]/ul/li[3]/a");
    private By transferTitle = By.xpath("//*[@id=\"showForm\"]/h1");
    private By amount = By.xpath("//*[@id=\"amount\"]");
    private By from = By.xpath("//*[@id=\"fromAccountId\"]");
    private By to = By.xpath("//*[@id=\"toAccountId\"]");
    private By transferButtom = By.xpath("//*[@id=\"transferForm\"]/div[2]/input");

    private By transferConfirm = By.xpath("//*[@id=\"showResult\"]/h1");

    protected TransferFundsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void clickBeginTransfer() throws InterruptedException {
        this.click(beginTransfer);
    }

    public void readTitle() throws InterruptedException {
        this.getText(transferTitle);
    }

    public void writeAmount(String money) throws InterruptedException {
        this.sendText(money, amount);
    }

    public void setFrom(String fromAccount) throws InterruptedException {
        this.sendKey(fromAccount, from);
    }

    public void setTo(String toAccount) throws InterruptedException {
        this.sendKey(toAccount, to);
    }

    public void clickCompleteTransfer() throws InterruptedException {
        this.click(transferButtom);
    }

    public String completeMessage() throws InterruptedException {
        System.out.println("Card message: " + this.getText(transferConfirm));
        return this.getText(transferConfirm);

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