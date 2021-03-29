package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

	public boolean enteremailid(String emailinput) {
		email.sendKeys(emailinput + "gamail.com");
		return true;

	}

	public boolean enterpassword(String passwordinput) {
		password.sendKeys(passwordinput);
		return true;

	}

	public HomePage clicksignin() {
		Signinbtn.click();
		return new HomePage(driver);

	}

}