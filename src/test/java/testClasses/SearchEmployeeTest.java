package testClasses;



import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.EmployeeListPage;
import pages.LoginPage;
import pages.PIMPage;
import utilities.BaseClass;
import utilities.ExcelUtility;
import utilities.ListenerImplementation;
import utilities.RetryImplementation;

@Listeners(ListenerImplementation.class)
public class SearchEmployeeTest extends BaseClass {

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
    public void verifySearchEmployee() {

        loginPage.login(
                ExcelUtility.readData(1,0,"LoginSheet"),
                ExcelUtility.readData(1,1,"LoginSheet"));

        dashboardPage.clickPIM();

        pimPage.clickEmployeeList();

        employeeListPage.searchEmployee(
                ExcelUtility.readData(1,0,"EmployeeSheet"),
                "");

        Assert.assertTrue(
                employeeListPage.isEmployeeDisplayed(),
                "Employee not found");
    }

    @AfterMethod
    public void tearDown() {
        closeBrowser();
    }
}