package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class ItemPage {
	WebDriver driver;
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
	
	@FindBy(css = "#layer_cart > div.clearfix > div.layer_cart_cart.col-xs-12.col-md-6 > div.button-container > a > span > i")
	WebElement proceedcheckout;


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

	public CartsummaryPage addtocart() throws InterruptedException {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,600)");

		Addtocart.isDisplayed();
		Addtocart.click();
		Thread.sleep(4000);
		proceedcheckout.click();
		Thread.sleep(4000);
		return new CartsummaryPage(driver);
	}
}
