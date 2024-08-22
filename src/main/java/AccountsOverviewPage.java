import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountsOverviewPage extends RegisterPage {
    private By accountOverview = By.linkText("Accounts Overview");
    private By note = By.xpath("//*[@id=\"accountTable\"]/tfoot/tr/td");

    private By accountNumber = By.xpath("//*[@id=\"accountTable\"]/tbody/tr[1]/td[1]/a");
    private By accountDetails = By.xpath("//*[@id=\"accountDetails\"]/h1");

    private By activityPeriod = By.xpath("//*[@id=\"month\"]");
    private By activityType = By.xpath("//*[@id=\"transactionType\"]");
    private By confirm = By.xpath("//*[@id=\"activityForm\"]/table/tbody/tr[3]/td[2]/input");

    protected AccountsOverviewPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void clickOverview() throws InterruptedException  {
        this.click(accountOverview);
    }

    public String accountNotes() throws InterruptedException {
        System.out.println("NOTA: " + this.getText(note));
        return this.getText(note);
    }

    public void clickAccountNumber() throws InterruptedException  {
        this.click(accountNumber);
    }

    public String accountDet() throws InterruptedException {
        System.out.println("MENSAJE DE DETALLE DE CUENTA: " + this.getText(accountDetails));
        return this.getText(accountDetails);
    }

    public void writePeriod(String activity) throws InterruptedException {
        this.sendKey(Keys.EQUALS,activityPeriod);
    }

    public void writeType(String type) throws InterruptedException {
        this.sendKey(Keys.EQUALS,activityType);
    }

    public void clickGo() throws InterruptedException  {
        this.click(confirm);
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
