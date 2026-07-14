package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.BaseClass;

public class LoginPage extends BaseClass{
	   public LoginPage() {
	        PageFactory.initElements(getDriver(), this);
	    }

	    @FindBy(name = "username")
	    private WebElement txtUsername;

	    @FindBy(name = "password")
	    private WebElement txtPassword;

	    @FindBy(xpath = "//button[@type='submit']")
	    private WebElement btnLogin;

	    public void enterUsername(String username) {
	        sendKeys(txtUsername, username);
	    }

	    public void enterPassword(String password) {
	        sendKeys(txtPassword, password);
	    }

	    public void clickLoginButton() {
	        clickOnAElement(btnLogin);
	    }

	    public void login(String username, String password) {
	        enterUsername(username);
	        enterPassword(password);
	        clickLoginButton();
	    }
}
