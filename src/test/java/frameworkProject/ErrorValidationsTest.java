package frameworkProject;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import frameworkBaseComponents.BaseTest;
import frameworkBaseComponents.Retry;
import frameworkProject.PageObjectModel.CartPage;
import frameworkProject.PageObjectModel.ProductCatalog;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"Error Handling"}, retryAnalyzer= Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {

		LP.LoginApp("123abc123@gmail.com", "Asurion@23");
		Assert.assertEquals("Incorrect email or password.", LP.GetErrorMsg());

	}
	
	@Test
	public void ProductErrorValidation() throws InterruptedException {
		String productName = "ZARA COAT 3";
		ProductCatalog PC = LP.LoginApp("123abc123@gmail.com", "Asurion@123");
		PC.GetProductList();
		PC.AddProductToCart(productName);
		CartPage CP = PC.GoToCartPage();
		Boolean match = CP.VerifyProductDisplay("Adidas");
		Assert.assertFalse(match);


	}

}
