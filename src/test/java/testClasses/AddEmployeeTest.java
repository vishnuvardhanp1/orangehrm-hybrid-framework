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
import utilities.EmployeeDataProvider;
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

    
    @Test(
            dataProvider = "EmployeeData",
            dataProviderClass = EmployeeDataProvider.class,
            retryAnalyzer = RetryImplementation.class)

    public void verifyAddEmployee(
            String firstName,
            String middleName,
            String lastName) {

        loginPage.login(
                ExcelUtility.readData(1, 0, "LoginSheet"),
                ExcelUtility.readData(1, 1, "LoginSheet"));

        dashboardPage.clickPIM();

        pimPage.clickAddEmployee();

        addEmployeePage.addEmployee(
                firstName,
                middleName,
                lastName);

        Assert.assertTrue(
                addEmployeePage.isPersonalDetailsDisplayed(),
                "Employee " + firstName + " " + lastName
                        + " was not added successfully.");
    }
    @AfterMethod
    public void tearDown() {

        closeBrowser();

    }

}
