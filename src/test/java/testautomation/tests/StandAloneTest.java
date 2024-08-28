package testautomation.tests;

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

import io.github.bonigarcia.wdm.WebDriverManager;
import testautomation.pageobjects.LoginPage;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		LoginPage loginpage = new LoginPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("lindababy@test.com");
		driver.findElement(By.id("userPassword")).sendKeys("linda92@TEST");
		driver.findElement(By.id("login")).click();
		
		List<WebElement> products = driver.findElements(By.className("col-lg-4"));
		WebElement prod = products.stream().filter(product->
		product.findElement(By.tagName("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase("ZARA COAT 3"));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("Ind");
		List<WebElement> countryList = driver.findElements(By.cssSelector(".ta-item span"));
		for(WebElement country :countryList )
		{
			if(country.getText().contentEquals("India")) {
				System.out.println(country.getText());
			country.click();
			break;
			}
			

        }
		
		Thread.sleep(5000);
		WebElement nameHtmlElement;
        try {
            // trying to set a new name...
        	nameHtmlElement = driver.findElement(By.cssSelector(".action__submit"));
            nameHtmlElement.click();
        } catch(StaleElementReferenceException e) {
            // retrieving the name input field again
            nameHtmlElement = driver.findElement(By.cssSelector(".action__submit"));
            // now nameHtmlElement is no longer stale
        }

        // you can now use nameHtmlElement without any problems
        nameHtmlElement.click();
		
		Thread.sleep(5000);
		String ConfirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertEquals(ConfirmMessage, "THANKYOU FOR THE ORDER.");
		driver.close();
		
		
	}
	
}
