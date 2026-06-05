package frameworkProject.PageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reusableComponents.AbstractReusableClass;

public class ProductCatalog extends AbstractReusableClass {

	WebDriver driver;

	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> Products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By ProductsBy = By.cssSelector(".mb-3");
	By AddToCartBtn = By.cssSelector("div[class='card-body'] button:last-of-type");
	By ToastMsg = By.id("toast-container");
	
	
	public List<WebElement> GetProductList() {
		waitForElementToAppear(ProductsBy);
		return Products;
	}
	
	public WebElement GetProductByName(String productName) {
		
		WebElement desiredProduct = GetProductList().stream()
				.filter(product -> product.findElement(By.tagName("b")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);
		return desiredProduct;
	}
	
	public void AddProductToCart(String productName) throws InterruptedException {
		
		WebElement desiredProduct =  GetProductByName(productName);
		desiredProduct.findElement(AddToCartBtn).click();
		waitForElementToAppear(ToastMsg);
		waitForElementToDisappear();

	}

}
