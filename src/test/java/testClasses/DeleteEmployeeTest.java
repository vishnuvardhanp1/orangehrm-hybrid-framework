package testClasses;


import org.testng.Assert;
import org.testng.annotations.*;

import pages.*;
import utilities.*;

@Listeners(ListenerImplementation.class)
public class DeleteEmployeeTest extends BaseClass {

    LoginPage loginPage;
    DashboardPage dashboardPage;
    PIMPage pimPage;
    EmployeeListPage employeeListPage;

    @BeforeMethod
    public void setUp() {

        launchBrowser();

        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        pimPage = new PIMPage();
        employeeListPage = new EmployeeListPage();

    }

    @Test(retryAnalyzer = RetryImplementation.class)
    public void verifyDeleteEmployee() {

        loginPage.login(
                ExcelUtility.readData(1,0,"LoginSheet"),
                ExcelUtility.readData(1,1,"LoginSheet"));

        dashboardPage.clickPIM();

        pimPage.clickEmployeeList();

        employeeListPage.searchEmployee(
                ExcelUtility.readData(1,0,"EmployeeSheet"),
                "");

        employeeListPage.clickDelete();

        employeeListPage.confirmDelete();

        employeeListPage.searchEmployee(
                ExcelUtility.readData(1,0,"EmployeeSheet"),
                "");

        Assert.assertTrue(
                employeeListPage.isNoRecordsDisplayed(),
                "Employee was not deleted successfully");

    }

    @AfterMethod
    public void tearDown() {

        closeBrowser();

    }
}
