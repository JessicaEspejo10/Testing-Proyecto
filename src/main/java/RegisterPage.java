import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage extends BasePage{

        private By inicia_registro = By.xpath("//*[@id=\"loginPanel\"]/p[2]/a");
        private By first_name = By.id("customer.firstName");
        private By last_name = By.id("customer.lastName");
        private By address = By.id("customer.address.street");
        private By city = By.id("customer.address.city");
        private By state = By.id("customer.address.state");
        private By zip_code = By.id("customer.address.zipCode");
        private By phone = By.id("customer.phoneNumber");
        private By ssn = By.id("customer.ssn");

        private By username = By.id("customer.username");
        private By password = By.id("customer.password");
        private By confirm = By.id("repeatedPassword");

        private By matchPswd = By.id("repeatedPassword.errors");

        private By finaliza_registro = By.linkText("REGISTER");

        private By bienvenido = By.className("title");
        private By saludo = By.linkText("Your account was created successfully. You are now logged in.");


        protected RegisterPage(WebDriver driver, WebDriverWait wait) {
                super(driver, wait);
        }

        public void beginRegister() throws InterruptedException  {
                this.click(inicia_registro);
        }

        public void writeFirstName(String nombre) throws InterruptedException {
                this.sendText(nombre,first_name);
        }
        public void writeLastName(String apellido) throws InterruptedException {
                this.sendText(apellido,last_name);
        }
        public void writeAddress(String direccion) throws InterruptedException {
                this.sendText(direccion,address);
        }
        public void writeCity(String ciudad) throws InterruptedException {
                this.sendText(ciudad,city);
        }
        public void writeState(String estado) throws InterruptedException {
                this.sendText(estado,state);
        }
        public void writeZipCode(String codigo) throws InterruptedException {
                this.sendText(codigo,zip_code);
        }
        public void writePhone(String telefono) throws InterruptedException {
                this.sendText(telefono,phone);
        }
        public void writeSsn(String ssn_) throws InterruptedException {
                this.sendText(ssn_, ssn);
        }
        public void writeUsername(String user) throws InterruptedException {
                this.sendText(user, username);
        }
        public void writePassword(String pwd) throws InterruptedException {
                this.sendText(pwd, password);
        }

        public void clickRegister() throws InterruptedException  {
                this.click(finaliza_registro);
        }

        public String matchingPasswords() throws InterruptedException {
                System.out.println("MENSAJE DE ERROR: " + this.getText(matchPswd));
                return this.getText(matchPswd);
        }

        public String welcome() throws InterruptedException {
                System.out.println("MENSAJE DE BIENVENIDA: " + this.getText(bienvenido));
                return this.getText(bienvenido);
        }

        public String hello() throws InterruptedException {
                System.out.println("MENSAJE DE SALUDO: " + this.getText(saludo));
                return this.getText(saludo);
        }
}
