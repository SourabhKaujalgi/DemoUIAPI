package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.sikuli.script.Screen;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;

public class ItemPage {
	WebDriver driver;
	public Screen screen;
	JavascriptExecutor js;

	public ItemPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "span#our_price_display")
	WebElement price;

	@FindBy(xpath = "//span[text()='Add to cart']")
	WebElement Addtocart;

	@FindBy(css = "input#quantity_wanted")
	WebElement quantity;

	public String getprice() {
		String priceactual = price.getText();
		System.out.println(priceactual);
		return priceactual;
	}

	public String entquantity(String qty) {
		quantity.click();
		quantity.clear();
		quantity.sendKeys(qty);
		return qty;

	}

	public CartsummaryPage addtocart() throws FindFailed, InterruptedException {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,600)");

		Addtocart.isDisplayed();
		Addtocart.click();
		screen = new Screen();
		Pattern ProcedtoCheckButton = new Pattern("C:\\workspace\\RestAutomation\\test-output\\Proceed.PNG");
		screen.click(ProcedtoCheckButton);
		Thread.sleep(4000);
		return new CartsummaryPage(driver);
	}
}
