package BaseTesting.SampleTest;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;
import java.util.Properties;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opera.core.systems.OperaDriver;
import com.sampleTest.PropsHandler;



/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
//	public static String execExtn = "";
//
//	public final static String CR_DRIVER_PATH = System.getProperty("webdriver.chrome.driver",
//			"lib" + File.separator + "chromedriver" + "");
	static Logger logger = LoggerFactory.getLogger(AppTest.class);
	public static String username = System.getProperty("email", "shahin@aims360.com");
	public static String password = System.getProperty("password", "M5ONteHNUT6bvV!#YWV3");
	
	public static String APP_BASE_URL = null;
	public WebDriver driver = null;
	public final static String FF_BROWSER = "firefox";
	public final static String CR_BROWSER = "chrome";
	public final static String IE_BROWSER = "ie";
	public final static String IE_EDGE_BROWSER = "edge";
	public final static String SF_BROWSER = "safari";
	public final static String OP_BROWSER = "opera";
	public final static String HU_BROWSER = "htmlunit";
	public static boolean isWin = false;
	public static boolean isLinux = false;
	public static boolean isUnix = false;
	public static boolean isMac = false;
	public static String execExtn = "";
	public static String defaultBrowser = "chrome";
	static {
		String osName = System.getProperty("os.name").toLowerCase();
		System.out.println("os.name=" + osName);
		if (osName.indexOf("win") != -1) {
			isWin = true;
			execExtn = ".exe";
			defaultBrowser = "ie";
		} else if (osName.indexOf("nix") != -1) {
			isUnix = true;
			defaultBrowser = "firefox";
		} else if (osName.indexOf("mac") != -1) {
			isMac = true;
			defaultBrowser = "safari";
		}
		defaultBrowser = "chrome"; // set it as default for now.
	}
	Properties props = new Properties();
	public final static String DRIVER_PATH = System.getProperty("selenium.drivers", "lib");
	public final static String FIREFOX_DRIVER_PATH = System.getProperty("webdriver.gecko.driver",
			DRIVER_PATH + File.separator + "geckodriver" + execExtn);
	public final static String CR_DRIVER_PATH = System.getProperty("webdriver.chrome.driver",
			DRIVER_PATH + File.separator + "chromedriver" + execExtn);
	public final static String IE_DRIVER_PATH = System.getProperty("webdriver.ie.driver",
			DRIVER_PATH + File.separator + "IEDriverServer" + execExtn);
	public final static String IE_EDGE_DRIVER_PATH = System.getProperty("webdriver.ie.driver",
			DRIVER_PATH + File.separator + "MicrosoftWebDriver" + execExtn);

	public final static long WAIT_TIME = Long.parseLong(System.getProperty("wait.time", "60")); // secs
	public static String TEST_TENANT = null;
	private static long TIMEOUT;
	public static String DEFAULT_USER_NAME = null;
	public static String DEFAULT_PASSWRD = null;
	public static String BOX_USER = null;
	public static String BOX_PASSWRD = null;
	private static final String TEST_PROPERTY_FILE = "test.properties";
	public static String AWS_AKEY = null;
	public static String API_HOST = null;
	public static String AWS_PKEY = null;
	public static String SF_PASSWORD = null;
	public static String SF_USER = null;
	public String browser = System.getProperty("browser", defaultBrowser);

	/*
	 * 
	 */
	public AppTest() {
		// Load Test Properties
		// From File or system
		props = PropsHandler.getProperties(TEST_PROPERTY_FILE);
		APP_BASE_URL = props.getProperty("app.base.url", "http://localhost:8080/WebApp");
		TEST_TENANT = props.getProperty("tenant.name", "KeyTech");
		TIMEOUT = new Integer(props.getProperty("time.out", "25")).intValue();
		DEFAULT_USER_NAME = props.getProperty("user.name", "testuser1");
		DEFAULT_PASSWRD = props.getProperty("user.password", "testuser123");

		API_HOST = props.getProperty("api.host", "http://localhost:8080");
		logger.info("***** UITestHAndler: APP_BASE_URL " + AppTest.APP_BASE_URL);
	}

	/**
	 * Open the new browser window by creating the web driver
	 * 
	 * @param browser
	 * @param driverPath
	 * @return
	 */
	public WebDriver openBrowser(String browser) {
		if (FF_BROWSER.equals(browser)) {
			logger.info("Opening " + FF_BROWSER);
			System.setProperty("webdriver.gecko.driver", FIREFOX_DRIVER_PATH);
			driver = new FirefoxDriver();
			DesiredCapabilities caps = new DesiredCapabilities().firefox();
			caps.setCapability("ignoreProtectedModeSettings", true);
			caps.setCapability("ignoreZoomSetting", true);
			caps.setCapability("draggable", true);
			caps.setCapability("requireWindowFocus", true);
		} else if (CR_BROWSER.equals(browser)) {
			logger.info("Opening " + CR_BROWSER);
			System.setProperty("webdriver.chrome.driver", CR_DRIVER_PATH);
			DesiredCapabilities caps = new DesiredCapabilities().chrome();
			caps.setCapability("ignoreProtectedModeSettings", true);
			caps.setCapability("ignoreZoomSetting", true);
			caps.setCapability("draggable", true);
			caps.setCapability("requireWindowFocus", true);
			caps.setCapability("chrome.switches", Arrays.asList("--incognito"));
			driver = new ChromeDriver();
		} else if (IE_BROWSER.equals(browser)) {
			logger.info("Opening " + IE_BROWSER);
			System.setProperty("webdriver.ie.driver", IE_DRIVER_PATH);
			DesiredCapabilities caps = new DesiredCapabilities().internetExplorer();
			// caps.setCapability("ignoreProtectedModeSettings", true);
			caps.setCapability("ignoreZoomSetting", true);
			caps.setCapability("draggable", true);
			caps.setCapability("requireWindowFocus", true);
			driver = new InternetExplorerDriver();
		} else if (IE_EDGE_BROWSER.equals(browser)) {
			logger.info("Opening " + IE_EDGE_BROWSER);
			System.setProperty("webdriver.edge.driver", IE_EDGE_DRIVER_PATH);
			driver = new EdgeDriver();
		} else if (SF_BROWSER.equals(browser)) {
			logger.info("Opening " + SF_BROWSER);
			driver = new SafariDriver();
		} else if (OP_BROWSER.equals(browser)) {
			logger.info("Opening " + OP_BROWSER);
			driver = new OperaDriver();
		} else if (HU_BROWSER.equals(browser)) {
			logger.info("Opening " + HU_BROWSER);
			driver = new HtmlUnitDriver();
		} else {
			logger.info("Opening default " + FF_BROWSER);
			driver = new FirefoxDriver();
		}
		return driver;
	}

	
   @org.testng.annotations.Test(enabled=true)
    public void shouldAnswerWithTrue()
    {
 
	   openBrowser("chrome");
	   //Maximize the browser  
       driver.manage().window().maximize();  

	   driver.navigate().to("https://dev-losan-d.aims360runway.com/");
	   logger.info("Getting UserName component and send username");
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(username);

		logger.info("Getting password component and send password");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);

		logger.info("Getting Sign In component");
		driver.findElement(By.xpath("//button[contains(@class,'btn-success')]")).click();

       
              
    }
}
