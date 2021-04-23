package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import BaseTest.UiBase;

public class SigninPage {
	WebDriver driver;

	public SigninPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "input#email")
	WebElement email;

	@FindBy(css = "input#passwd")
	WebElement password;

	@FindBy(css = "button#SubmitLogin")
	WebElement Signinbtn;

	public boolean enterEmailid(String emailinput) {
		UiBase.wait.until(ExpectedConditions.visibilityOf(email));
		email.sendKeys(emailinput);
		return true;

	}

	public boolean enterPassword(String passwordinput) {
		password.sendKeys(passwordinput);
		return true;

	}

	public HomePage clickSignin() {
		Signinbtn.click();
		return new HomePage(driver);

	}

}