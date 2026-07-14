package pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.BaseClass;

public class EmployeeListPage extends BaseClass {

    public EmployeeListPage() {
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(xpath = "(//input[@placeholder='Type for hints...'])[1]")
    private WebElement txtEmployeeName;

    @FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[2]")
    private WebElement txtEmployeeId;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement btnSearch;

    @FindBy(xpath = "//button[@type='reset']")
    private WebElement btnReset;
    
    @FindBy(xpath="//div[@role='rowgroup']")
    private WebElement employeeResult;
    
    @FindBy(xpath = "//button[.//i[contains(@class,'bi-pencil-fill')]]")
    private WebElement btnEdit;
    
    @FindBy(xpath = "//button[.//i[contains(@class,'bi-trash')]]")
    private WebElement btnDelete;
    
    @FindBy(xpath = "//button[normalize-space()='Yes, Delete']")
    private WebElement btnConfirmDelete;
    
    @FindBy(xpath = "//span[text()='No Records Found']")
    private WebElement noRecordsFound;

    public void enterEmployeeName(String employeeName) {
        sendKeys(txtEmployeeName, employeeName);
    }

    public void enterEmployeeId(String employeeId) {
        sendKeys(txtEmployeeId, employeeId);
    }

    public void clickSearch() {
        clickOnAElement(btnSearch);
    }

    public void clickReset() {
        clickOnAElement(btnReset);
    }

    public void searchEmployee(String employeeName, String employeeId) {
        enterEmployeeName(employeeName);
        enterEmployeeId(employeeId);
        clickSearch();
    }
    
    public void clickEdit() {
        clickOnAElement(btnEdit);
    }
    
    public boolean isEmployeeDisplayed() {
        return isElementDisplayed(employeeResult);
    }
    
    public void clickDelete() {

    	  waitUntilLoaderDisappears();

    	    waitUntilElementIsClickable(btnDelete, 10);

    	    clickOnAElement(btnDelete);

    }

    public void confirmDelete() {

        waitUntilElementIsClickable(btnConfirmDelete,10);
        clickOnAElement(btnConfirmDelete);

    }

    public boolean isNoRecordsDisplayed() {

        waitUntilElementIsVisible(noRecordsFound,10);

        return isElementDisplayed(noRecordsFound);

    }
}
