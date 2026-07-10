package pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.BaseClass;

public class EmployeeListPage extends BaseClass {

    public EmployeeListPage() {
        PageFactory.initElements(driver, this);
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
}
