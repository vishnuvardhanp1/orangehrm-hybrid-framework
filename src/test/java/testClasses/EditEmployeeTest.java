package testClasses;


import org.testng.Assert;
import org.testng.annotations.*;

import pages.*;

import utilities.*;

@Listeners(ListenerImplementation.class)
public class EditEmployeeTest extends BaseClass {

    LoginPage loginPage;
    DashboardPage dashboardPage;
    PIMPage pimPage;
    EmployeeListPage employeeListPage;
    EditEmployeePage editEmployeePage;

    @BeforeMethod
    public void setUp() {

        launchBrowser();

        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        pimPage = new PIMPage();
        employeeListPage = new EmployeeListPage();
        editEmployeePage = new EditEmployeePage();
    }

    @Test(retryAnalyzer = RetryImplementation.class)
    public void verifyEditEmployee() {

        loginPage.login(
                ExcelUtility.readData(1,0,"LoginSheet"),
                ExcelUtility.readData(1,1,"LoginSheet"));

        dashboardPage.clickPIM();

        pimPage.clickEmployeeList();

        employeeListPage.searchEmployee(
                ExcelUtility.readData(1,0,"EmployeeSheet"),
                "");

        employeeListPage.clickEdit();

        editEmployeePage.editEmployee(
                "Automation",
                "Tester");
        
        Assert.assertTrue(
        	    editEmployeePage.isPersonalDetailsDisplayed(),
        	    "Employee details were not updated successfully");
    }

    @AfterMethod
    public void tearDown() {
        closeBrowser();
    }
}
