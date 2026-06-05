package frameworkProject.PageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reusableComponents.AbstractReusableClass;

public class CheckOutPage extends AbstractReusableClass {

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "input[placeholder*='Country']")
	WebElement CountryInput;

	@FindBy(css = ".ta-item:nth-of-type(2)")
	WebElement CountrySelection;

	@FindBy(css = ".action__submit")
	WebElement Submit;

	By Results = By.cssSelector(".ta-results");

	public void SelectCountry(String countryName) {
		Actions action = new Actions(driver);

		action.sendKeys(CountryInput, countryName).build().perform();
		waitForElementToAppear(Results);
		CountrySelection.click();
		

	}
	
	public OrderConfirmation SubmitOrder() {
		Submit.click();
		OrderConfirmation OC = new OrderConfirmation(driver);
		return OC;
	}

}
