package BaseTest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import DemoProj.Demo.CommonUtility;
import Pages.CartsummaryPage;
import Pages.HomePage;
import Pages.ItemPage;
import Pages.SigninPage;
import Resources.ApiResources;
import Resources.TestData1;
import io.github.bonigarcia.wdm.WebDriverManager;

public class UiBase {
	protected WebDriver driver;
	public static WebDriverWait wait;
	protected String Placeid;
	protected ApiResources ap;
	protected HomePage hp;
	protected ItemPage lp;
	protected CartsummaryPage cp;
	protected SigninPage sp;
	protected TestData1 ts;
	protected String emailid;
	protected String quantity;
	
	public void launchBrowser() {
		String browser = CommonUtility.configration.getProperty("browser");

		if (browser.equalsIgnoreCase("Chrome")) {

			// System.setProperty("webdriver.chrome.driver",
			// "C:\\Users\\comp\\Desktop\\CromeDriver\\chromedriver.exe");
			// driver= new ChromeDriver();
			ChromeOptions chromeOptions = new ChromeOptions();
			WebDriverManager.chromedriver().version("89.0.4389.128").setup();
			driver = new ChromeDriver(chromeOptions);
		}

		else {
			// System.setProperty("webdriver.gecko.driver","C://Users//comp//Desktop//Selenium
			// Software//geckodriver.exe");
			// driver=new FirefoxDriver();
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		}

		driver.get(CommonUtility.configration.getProperty("url"));
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver,50);

	}

	public static void getScreenshot(WebDriver driver) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots"
		// under src folder
		String destination = System.getProperty("user.dir") + "/Screenshots/" + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);

	}

}
