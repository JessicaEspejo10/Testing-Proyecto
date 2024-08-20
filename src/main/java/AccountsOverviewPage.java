import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountsOverviewPage extends BasePage {
    private By accountOverview = By.xpath("//*[@id=\"leftPanel\"]/ul/li[2]/a");
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
        this.sendText(activity, activityPeriod);
    }

    public void writeType(String type) throws InterruptedException {
        this.sendText(type,activityType);
    }

    public void clickGo() throws InterruptedException  {
        this.click(confirm);
    }
    
}
