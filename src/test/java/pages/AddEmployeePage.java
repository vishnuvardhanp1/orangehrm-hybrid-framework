package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.BaseClass;

public class AddEmployeePage extends BaseClass {

    public AddEmployeePage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "firstName")
    private WebElement txtFirstName;

    @FindBy(name = "middleName")
    private WebElement txtMiddleName;

    @FindBy(name = "lastName")
    private WebElement txtLastName;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement btnSave;
    
    @FindBy(xpath = "//h6[text()='Personal Details']")
    private WebElement personalDetailsHeader;

    public void enterFirstName(String firstName) {
        sendKeys(txtFirstName, firstName);
    }

    public void enterMiddleName(String middleName) {
        sendKeys(txtMiddleName, middleName);
    }

    public void enterLastName(String lastName) {
        sendKeys(txtLastName, lastName);
    }

    public void clickSave() {
        clickOnAElement(btnSave);
    }

    public void addEmployee(String firstName, String middleName, String lastName) {

        enterFirstName(firstName);
        enterMiddleName(middleName);
        enterLastName(lastName);
        clickSave();
    }
    

    public boolean isPersonalDetailsDisplayed() {
        return isElementDisplayed(personalDetailsHeader);
    }
}
