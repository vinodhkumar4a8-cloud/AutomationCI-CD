package stepDefinations;

import java.io.IOException;

import org.testng.Assert;

import frameworkBaseComponents.BaseTest;
import frameworkProject.PageObjectModel.CartPage;
import frameworkProject.PageObjectModel.CheckOutPage;
import frameworkProject.PageObjectModel.LandingPage;
import frameworkProject.PageObjectModel.OrderConfirmation;
import frameworkProject.PageObjectModel.ProductCatalog;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinationImplementation extends BaseTest {

	public LandingPage LP;
	public ProductCatalog PC;
	public CartPage CP;
	public CheckOutPage COP;
	public OrderConfirmation OC;

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		LP = LaunchApplication();
	}

	@Given("^User logged in using username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password) throws InterruptedException {
		PC = LP.LoginApp(username, password);
	}

	@When("^I add the product (.+) to cart$")
	public void i_add_product_to_cart(String productname) throws InterruptedException {
		PC.GetProductList();
		PC.AddProductToCart(productname);
	}

	@And("^Checkout (.+) and submit the order$")
	public void checkout_order(String productname) {
		CP = PC.GoToCartPage();
		Boolean match = CP.VerifyProductDisplay(productname);
		Assert.assertTrue(match);
		COP = CP.CheckOutBtn();
		COP.SelectCountry("india");
		OC = COP.SubmitOrder();
	}
	
	@Then ("{string} message is displayed on confirmation page")
	public void message_displayed_confirmation_page(String string) {
		String Message = OC.GetOrderConfirmationMsg();
		Assert.assertTrue(Message.equalsIgnoreCase(string));
		driver.quit();
	}
	
	@Then("Then {string} message is displayed on landing page")
	public void ErrorMsg_On_LandingPage(String string, String username, String password) throws InterruptedException {
		Assert.assertEquals(string, LP.GetErrorMsg());
		driver.quit();
	}

}
