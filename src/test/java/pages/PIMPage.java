package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.BaseClass;

public class PIMPage extends BaseClass {

    public PIMPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[text()='Add Employee']")
    private WebElement addEmployee;

    @FindBy(xpath = "//a[text()='Employee List']")
    private WebElement employeeList;

    public void clickAddEmployee() {
        clickOnAElement(addEmployee);
    }

    public void clickEmployeeList() {
        clickOnAElement(employeeList);
    }
}
