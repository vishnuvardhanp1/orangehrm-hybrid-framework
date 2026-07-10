package testClasses;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import utilities.BaseClass;
import utilities.ExcelUtility;
import utilities.RetryImplementation;
import utilities.ListenerImplementation;

@Listeners(ListenerImplementation.class)
public class LoginTest extends BaseClass {

    LoginPage loginPage;
    DashboardPage dashboardPage;

    @BeforeMethod
    public void setUp() {
        launchBrowser();
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
    }

    @Test(retryAnalyzer = RetryImplementation.class)
    public void verifyValidLogin() {

        String username = ExcelUtility.readData(1, 0, "LoginSheet");
        String password = ExcelUtility.readData(1, 1, "LoginSheet");

        loginPage.login(username, password);

        Assert.assertTrue(dashboardPage.isDashboardDisplayed(),
                "Dashboard is not displayed");
    }

    @AfterMethod
    public void tearDown() {
        closeBrowser();
    }
}