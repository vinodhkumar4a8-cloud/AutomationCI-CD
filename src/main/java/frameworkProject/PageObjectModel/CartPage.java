package frameworkProject.PageObjectModel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reusableComponents.AbstractReusableClass;

public class CartPage extends AbstractReusableClass {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(css=".cartSection h3")
	List<WebElement> productTitles;
	
	@FindBy(css=".totalRow button")
	WebElement CheckOutToBtn;
	
	
	
	public Boolean VerifyProductDisplay(String productName) {
		Boolean match = productTitles.stream().anyMatch(name -> name.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckOutPage CheckOutBtn() {
		CheckOutToBtn.click();
		CheckOutPage COP= 	new CheckOutPage(driver);
		return COP;
	}
	
	

}
