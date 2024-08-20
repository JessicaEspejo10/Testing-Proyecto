import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TransferFunds extends BasePage{
    private By beginTransfer = By.xpath("//*[@id=\"leftPanel\"]/ul/li[3]/a");
    private By amount = By.xpath("//*[@id=\"showForm\"]/h1");
    private By from = By.xpath("//*[@id=\"fromAccountId\"]");
    private By to = By.xpath("//*[@id=\"toAccountId\"]");
    private By transferButtom = By.xpath("//*[@id=\"transferForm\"]/div[2]/input");

    private By transferConfirm = By.xpath("//*[@id=\"showResult\"]/h1");

    protected TransferFunds(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void clickBeginTransfer() throws InterruptedException  {
        this.click(beginTransfer);
    }

    public void writeAmount(Integer money) throws InterruptedException {
        this.sendText(String.valueOf(money), amount);
    }
    public void writeFrom(String fromAccount) throws InterruptedException {
        this.sendText(fromAccount,from);
    }
    public void writeTo(String toAccount) throws InterruptedException {
        this.sendText(toAccount,to);
    }
    public void clickCompleteTransfer() throws InterruptedException  {
        this.click(transferButtom);
    }
}
