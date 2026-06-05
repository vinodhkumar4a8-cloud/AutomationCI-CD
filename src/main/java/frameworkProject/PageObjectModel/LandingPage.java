package frameworkProject.PageObjectModel;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import reusableComponents.AbstractReusableClass;

public class LandingPage extends AbstractReusableClass {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElement userEmailID = driver.findElement(By.id("userEmail"));

	@FindBy(id = "userEmail")
	WebElement userEmailID;

	@FindBy(id = "userPassword")
	WebElement userPWD;

	@FindBy(id = "login")
	WebElement Login;

	@FindBy(css = "[class*='toast-error']")
	WebElement ToastErrorMsg;

	public String GetErrorMsg() {
		waitForElementToAppear(ToastErrorMsg);
		return ToastErrorMsg.getText();
	}

	public ProductCatalog LoginApp(String email, String password) throws InterruptedException {
		userEmailID.sendKeys(email);
		userPWD.sendKeys(password);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		WebElement login = wait.until(ExpectedConditions.elementToBeClickable(By.id("login")));
		login.click();

		ProductCatalog PC = new ProductCatalog(driver);
		return PC;
	}

	public void Goto() {
		driver.get("https://rahulshettyacademy.com/client");
	}

}
