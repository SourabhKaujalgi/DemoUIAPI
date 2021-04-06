package DemoProj.Demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
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

import Pages.CartsummaryPage;
import Pages.HomePage;
import Pages.ItemPage;
import Pages.SigninPage;
import Resources.ApiResources;
import Resources.TestData1;
import Resources.TestDataBuild;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	WebDriver driver;
	TestDataBuild td = new TestDataBuild();
	String Placeid;
	ApiResources ap;
	HomePage hp;
	ItemPage lp;
	CartsummaryPage cp;
	SigninPage sp;
	TestData1 ts;
	String emailid;
	static RequestSpecification req;
	static RequestSpecification tokenreq;

	public void launch() {
		String browser = CommonUtility.configration.getProperty("browser");

		if (browser.equalsIgnoreCase("Chrome")) {

			// System.setProperty("webdriver.chrome.driver",
			// "C:\\Users\\comp\\Desktop\\CromeDriver\\chromedriver.exe");
			// driver= new ChromeDriver();
			ChromeOptions chromeOptions = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(chromeOptions);
		}

		else {
			// System.setProperty("webdriver.gecko.driver","C://Users//comp//Desktop//Selenium
			// Software//geckodriver.exe");
			// driver=new FirefoxDriver();
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		}

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.get(CommonUtility.configration.getProperty("url"));
		driver.manage().window().maximize();
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

	public static RequestSpecification requestspecification() throws FileNotFoundException {

		if (req == null) {
			PrintStream log = new PrintStream(new FileOutputStream("Log.txt"));
			RestAssured.useRelaxedHTTPSValidation();
			req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).addQueryParam("key", "qaclick123")
					.setContentType(ContentType.JSON).build();
		}
		return req;
	}

	public static RequestSpecification userspecification() throws FileNotFoundException {

		if (req == null) {
			PrintStream log = new PrintStream(new FileOutputStream("Log.txt"));
			RestAssured.useRelaxedHTTPSValidation();
			tokenreq = new RequestSpecBuilder().setBaseUri("https://reqres.in")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))

					.build();
		}
		return tokenreq;
	}

	public static String getJsonPath(String response, String key) {
		JsonPath js = new JsonPath(response);
		return js.get(key);
	}

}
