package BaseTesting.SampleTest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */

	
   @org.testng.annotations.Test(enabled=true)
    public void shouldAnswerWithTrue()
    {
    	// System Property for Chrome Driver   
        System.setProperty("webdriver.chrome.driver", "E:\\SampleTest\\lib\\chromedriver.exe");  
          
             // Instantiate a ChromeDriver class.     
        WebDriver driver=new ChromeDriver();  
          
           // Launch Website  
        driver.navigate().to("http://www.javatpoint.com/");  
          
         //Maximize the browser  
          driver.manage().window().maximize();  
    	
    	
        
    }
}
