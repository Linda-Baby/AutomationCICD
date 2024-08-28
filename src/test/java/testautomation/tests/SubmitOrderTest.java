package testautomation.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import testautomation.pageobjects.CartPage;
import testautomation.pageobjects.CheckoutPage;
import testautomation.pageobjects.ConfirmationPage;
import testautomation.pageobjects.LoginPage;
import testautomation.pageobjects.OrderPage;
import testautomation.pageobjects.ProductCatalogue;
import testautomation.testcomponents.BaseTest;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";
	
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(String email, String password) throws IOException, InterruptedException
	
	{
	
		// TODO Auto-generated method stub
		//String productName = "ZARA COAT 3";
		//LoginPage loginpage = launchApplication();
		ProductCatalogue productCatalogue = loginpage.loginApplication(email,password);
				
		List<WebElement> products = productCatalogue.getProductList();		
		productCatalogue.addProductToCart(productName);
		Thread.sleep(5000);
		CartPage cartPage = productCatalogue.goToCartPage();
						
		Assert.assertTrue(cartPage.verifyCartProduct(productName));
		CheckoutPage checkoutPage = cartPage.clickCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		
		Thread.sleep(3000);
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertEquals(confirmMessage, "THANKYOU FOR THE ORDER.");
		//driver.close();
				
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	
	public void OrderHistoryTest() throws InterruptedException
	{
		ProductCatalogue productCatalogue = loginpage.loginApplication("lindababy@test.com", "linda92@TEST");
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyCartProduct(productName));
	} 
	
	
	
	
	@DataProvider
	public Object[][] getData()
	{
		return new Object[] [] {{"lindababy@test.com", "linda92@TEST"}, {"lindababy@test.com", "linda92@TEST"}};
	}
	
	
}
