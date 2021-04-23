package Pages;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.inject.spi.Element;

import BaseTest.UiBase;

public class HomePage {

	WebDriver driver;
	JavascriptExecutor js;

	@FindBy(css = "img.img-responsive[src='http://automationpractice.com/modules/blockbanner/img/sale70.png']")
	WebElement logoimage;

	@FindBy(css = "input#search_query_top")
	WebElement searchbar;

	@FindBy(css = "button[name='submit_search']")
	WebElement searchbutton;

	@FindBy(css = "ul.product_list.grid.row>li.ajax_block_product.ajax_block_product.col-xs-12.col-sm-6.col-md-4.first-in-line.last-line.first-item-of-tablet-line.first-item-of-mobile-line.last-mobile-line")
	WebElement searchresult;

	@FindBy(css = "p.alert.alert-warning")
	WebElement noresult;

	@FindBy(xpath = "//*[@id='center_column']/ul/li/div//child::a[@class='quick-view']")
	WebElement Item;

	@FindBy(css = "div#center_column>ul>li>div>div:nth-of-type(2)>h5>a")
	WebElement Selectitem;

	@FindBy(css ="a.login")
	WebElement signinbtn;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// WebDriverWait wait=new WebDriverWait(driver,60);
	public String verifyTitle() {
		String title = driver.getTitle();
		return title;
	}

	public boolean verifyLogo() {
		UiBase.wait.until(ExpectedConditions.visibilityOf(logoimage));
		boolean b = logoimage.isDisplayed();
		return b;
	}

	public boolean verifySearchOption() {
		UiBase.wait.until(ExpectedConditions.visibilityOf(searchbar));
		boolean b = searchbar.isDisplayed();
		return b;
	}

	public boolean validateSearchOperation(String searchitem) {
		searchbar.click();
		searchbar.sendKeys(searchitem);
		searchbutton.click();
		UiBase.wait.until(ExpectedConditions.visibilityOf(searchresult));
		if (searchresult.isDisplayed()) {
			return true;
		} else if (noresult.isDisplayed()) {
			return true;

		}

		return false;
	}

	public ItemPage selectingItem() throws InterruptedException {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

		Actions act = new Actions(driver);
		act.moveToElement(Selectitem);
		Selectitem.click();
		return new ItemPage(driver);

	}

	public SigninPage clickSignin() throws InterruptedException {
		
		signinbtn.click();
		return new SigninPage(driver);

	}
}
