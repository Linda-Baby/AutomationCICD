package testautomation.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

//import com.sun.net.httpserver.Authenticator.Retry;

import io.github.bonigarcia.wdm.WebDriverManager;
import testautomation.pageobjects.CartPage;
import testautomation.pageobjects.CheckoutPage;
import testautomation.pageobjects.ConfirmationPage;
import testautomation.pageobjects.LoginPage;
import testautomation.pageobjects.ProductCatalogue;
import testautomation.testcomponents.BaseTest;
import testautomation.testcomponents.Retry;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
public class ErrorValidationTest extends BaseTest {

	
	
	@Test(groups={"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException
	
	{
	
		// TODO Auto-generated method stub
		
		//LoginPage loginpage = launchApplication();
		loginpage.loginApplication("lindababy@test.com", "linda92");
		loginpage.getErrorMessage();
		Assert.assertEquals("Incorrect email or password.", loginpage.getErrorMessage());		
		
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException
	
	{
	
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		//LoginPage loginpage = launchApplication();
		ProductCatalogue productCatalogue = loginpage.loginApplication("lindababy@test.com", "linda92@TEST");
				
		List<WebElement> products = productCatalogue.getProductList();		
		productCatalogue.addProductToCart(productName);
		Thread.sleep(5000);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyCartProduct("ZARA COAT 33");			
		Assert.assertFalse(match);
				
		
		
	}
	
	
	
	
	
}
