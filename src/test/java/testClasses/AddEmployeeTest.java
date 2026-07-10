package testClasses;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pages.AddEmployeePage;
import pages.DashboardPage;
import pages.LoginPage;
import pages.PIMPage;
import utilities.BaseClass;
import utilities.ExcelUtility;
import utilities.ListenerImplementation;
import utilities.RetryImplementation;

@Listeners(ListenerImplementation.class)
public class AddEmployeeTest extends BaseClass {

    LoginPage loginPage;
    DashboardPage dashboardPage;
    PIMPage pimPage;
    AddEmployeePage addEmployeePage;

    @BeforeMethod
    public void setUp() {

        launchBrowser();

        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        pimPage = new PIMPage();
        addEmployeePage = new AddEmployeePage();
    }

    @Test(retryAnalyzer = RetryImplementation.class)
    public void verifyAddEmployee() {

        loginPage.login(
                ExcelUtility.readData(1,0,"LoginSheet"),
                ExcelUtility.readData(1,1,"LoginSheet"));

        dashboardPage.clickPIM();

        pimPage.clickAddEmployee();

        addEmployeePage.addEmployee(
                ExcelUtility.readData(1,0,"EmployeeSheet"),
                ExcelUtility.readData(1,1,"EmployeeSheet"),
                ExcelUtility.readData(1,2,"EmployeeSheet"));
        
        Assert.assertTrue(
                addEmployeePage.isPersonalDetailsDisplayed(),
                "Employee was not added successfully");

    }

    @AfterMethod
    public void tearDown() {

        closeBrowser();

    }

}
