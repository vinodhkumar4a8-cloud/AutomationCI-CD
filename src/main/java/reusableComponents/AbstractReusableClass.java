package reusableComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import frameworkProject.PageObjectModel.CartPage;
import frameworkProject.PageObjectModel.OrdersPage;

public class AbstractReusableClass {
	WebDriver driver;
	
	public AbstractReusableClass(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

	
	@FindBy(css="[routerlink*='cart']")
	WebElement CartHeader;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement MyOrderHeader;
	
	public OrdersPage GoToOrdersPage() {
		MyOrderHeader.click();
		OrdersPage OP = new OrdersPage(driver);
		return OP;
	}

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		
	}
	
	public void waitForElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(findBy));
		
	}
	
	public void waitForElementToDisappear() throws InterruptedException {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.invisibilityOf(ele));
		Thread.sleep(5000);
		
	}
	
	public CartPage GoToCartPage() {
		CartHeader.click();
		CartPage CP = new CartPage(driver);
		return CP;
	}

}
