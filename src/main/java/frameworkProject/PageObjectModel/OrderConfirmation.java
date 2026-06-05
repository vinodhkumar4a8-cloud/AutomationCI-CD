package frameworkProject.PageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import reusableComponents.AbstractReusableClass;

public class OrderConfirmation extends AbstractReusableClass {
	
	WebDriver driver;

	public OrderConfirmation(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}


	
	@FindBy(css=".hero-primary")
	WebElement confirmMessage;
	
	public String GetOrderConfirmationMsg() {
		return confirmMessage.getText();
	}
}
