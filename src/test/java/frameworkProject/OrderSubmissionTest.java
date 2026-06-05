package frameworkProject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import frameworkBaseComponents.BaseTest;
import frameworkProject.PageObjectModel.CartPage;
import frameworkProject.PageObjectModel.CheckOutPage;
import frameworkProject.PageObjectModel.OrderConfirmation;
import frameworkProject.PageObjectModel.OrdersPage;
import frameworkProject.PageObjectModel.ProductCatalog;

public class OrderSubmissionTest extends BaseTest {

	// String productName = "ZARA COAT 3";

	@Test(dataProvider = "GetData", groups = { "PurchaseOrder" })
	public void SubmitOrder(HashMap<String, String> input)
			throws IOException, InterruptedException {

		String countryName = "India";
		ProductCatalog PC = LP.LoginApp(input.get("email"), input.get("password"));
		PC.GetProductList();
		PC.AddProductToCart(input.get("product"));
		CartPage CP = PC.GoToCartPage();
		Boolean match = CP.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckOutPage COP = CP.CheckOutBtn();
		COP.SelectCountry(countryName);
		OrderConfirmation OC = COP.SubmitOrder();

		String Message = OC.GetOrderConfirmationMsg();

		Assert.assertTrue(Message.equalsIgnoreCase("Thankyou for the order."));

	}

	@Test(dependsOnMethods = { "SubmitOrder" })
	public void OrderHistoryTest() throws InterruptedException {
		ProductCatalog PC = LP.LoginApp("123abc123@gmail.com", "Asurion@123");
		OrdersPage OP = PC.GoToOrdersPage();
		Assert.assertTrue(OP.VerifyOrderDisplay("ZARA COAT 3"));

	}
	

	
	//If test case has more parameters, it'll be difficult to initialize. instead use hashmap(key, pair)
//	@DataProvider
//	public Object[][] GetData() {
//
//		return new Object[][] { { "123abc123@gmail.com", "Asurion@123", "ZARA COAT 3" }, { "1234abc1234@gmail.com", "Asurion@1234", "ADIDAS ORIGINAL"  } };
//
//	}
	
	
//	HashMap<Object, Object> map = new HashMap<Object, Object>();
//	map.put("email", "123abc123@gmail.com");
//	map.put("password", "Asurion@123");
//	map.put("product", "ZARA COAT 3");
//
//	HashMap<Object, Object> map1 = new HashMap<Object, Object>();
//	map1.put("email", "1234abc1234@gmail.com");
//	map1.put("password", "Asurion@1234");
//	map1.put("product", "ADIDAS ORIGINAL");
//
//	return new Object[][] { { map }, { map1 } };
	

	//Retreving data using json file.
	@DataProvider
	public Object[][] GetData() throws IOException {


		List<HashMap<String, String>> data = GetJsonData(System.getProperty("user.dir") + "//src//test//java//frameWorkJson//PurchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};

	}
}
