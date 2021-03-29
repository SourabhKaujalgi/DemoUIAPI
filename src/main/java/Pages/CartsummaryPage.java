package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartsummaryPage {
	WebDriver driver;
	JavascriptExecutor js2;

	@FindBy(xpath = "//span[@id='product_price_1_1_0']/span")
	WebElement unitprice;

	@FindBy(xpath = "//a[@title='View my shopping cart']/span[@class='ajax_cart_quantity']")
	WebElement quantitysummary;

	public CartsummaryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public String getUnitprice() {

		String Unitprice = unitprice.getText();
		return Unitprice;

	}

	public String getQauantity() throws InterruptedException {

		String Quantity = quantitysummary.getText();
		return Quantity;

	}

}
