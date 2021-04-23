package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import BaseTest.UiBase;

public class CartsummaryPage {
	WebDriver driver;
	JavascriptExecutor js;

	@FindBy(css ="body#order.order.hide-left-column.hide-right-column.lang_en div#page div.columns-container div#columns.container div.row div#center_column.center_column.col-xs-12.col-sm-12 div#order-detail-content.table_block.table-responsive table#cart_summary.table.table-bordered.stock-management-on tbody tr#product_1_1_0_471952.cart_item.last_item.first_item.address_471952.odd td.cart_unit span#product_price_1_1_471952.price span.price")
	WebElement unitprice;

	@FindBy(css = "a[title='View my shopping cart']>span[class='ajax_cart_quantity']")
	WebElement quantitysummary;
	
	@FindBy(css = "a.logout")
	WebElement signout;

	public CartsummaryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	

	public String getQauantity() throws InterruptedException {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,200)");

		UiBase.wait.until(ExpectedConditions.visibilityOf(quantitysummary));
		String Quantity = quantitysummary.getText();
		return Quantity;

	}
	
	public HomePage clickSignout() throws InterruptedException {
		signout.click();
		return new HomePage(driver);

	}

}
