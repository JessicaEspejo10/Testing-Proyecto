import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewAccountPage extends BasePage{

    private By openNewAccount = By.linkText("Open New Account");
    private By accountType = By.xpath("//*[@id=\"type\"]");
    private By openButtom = By.linkText("Open New Account");

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
        return this.getText(congrats);
    }



}
