package pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.BaseClass;

public class EditEmployeePage extends BaseClass {

    public EditEmployeePage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "middleName")
    private WebElement txtMiddleName;

    @FindBy(name = "lastName")
    private WebElement txtLastName;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement btnSave;
    
    @FindBy(xpath="//h6[text()='Personal Details']")
    private WebElement personalDetails;


    public void updateMiddleName(String middleName) {
        clearText(txtMiddleName);
        sendKeys(txtMiddleName, middleName);
    }

    public void updateLastName(String lastName) {
        clearText(txtLastName);
        sendKeys(txtLastName, lastName);
    }

    public void clickSave() {
    	System.out.println(txtMiddleName.getAttribute("value"));
    	System.out.println(txtLastName.getAttribute("value"));
        clickOnAElement(btnSave);
    }

    public void editEmployee(String middleName, String lastName) {
        updateMiddleName(middleName);
        updateLastName(lastName);
        clickSave();
    }
    

    public boolean isPersonalDetailsDisplayed() {
    return isElementDisplayed(personalDetails);
    }
}
