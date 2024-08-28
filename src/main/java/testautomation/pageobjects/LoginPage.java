package testautomation.pageobjects;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.PageFactory;

import testautomation.AbstractComponents.AbstractComponents;

public class LoginPage extends AbstractComponents{
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	//PageFactory -- To use this include initElements method in constructor 
	@FindBy(id ="userEmail")
	WebElement userEmail;
	
	
	@FindBy(id ="userPassword")
	WebElement userPassword;
	
	@FindBy(id ="login")
	WebElement loginButton;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	//WebElement userPassword = driver.findElement(By.id("userPassword"));
	//WebElement loginButton = driver.findElement(By.id("login"));
	
	public ProductCatalogue loginApplication(String email, String password) throws InterruptedException
	{
		
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		Thread.sleep(3000);
		loginButton.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public void goToUrl()
	{
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}

}
