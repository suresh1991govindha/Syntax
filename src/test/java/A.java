import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class A {


	@Test
	public void testRun()
	{
		
	
		  WebDriver driver;
	         
	        //Setting webdriver.gecko.driver property
	        System.setProperty("webdriver.chrome.driver","C:\\Users\\rockstr\\eclipse-workspace\\RestAssured\\chromedriver.exe");
	         
	        //Instantiating driver object and launching browser
	        driver = new ChromeDriver();
	         
	        //Using get() method to open a webpage
	        driver.get("http://artoftesting.com");
	         
	        //Closing the browser
	     //  driver.quit();
	}
}
