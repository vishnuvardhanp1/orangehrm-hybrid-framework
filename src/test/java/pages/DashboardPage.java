package pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.BaseClass;

public class DashboardPage extends BaseClass {

    public DashboardPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[text()='Dashboard']")
    private WebElement dashboardHeader;

    @FindBy(xpath = "//span[text()='PIM']")
    private WebElement pimMenu;

    @FindBy(xpath = "//span[text()='Admin']")
    private WebElement adminMenu;

    @FindBy(xpath = "//span[text()='Leave']")
    private WebElement leaveMenu;

    @FindBy(xpath = "//span[text()='Recruitment']")
    private WebElement recruitmentMenu;

    @FindBy(xpath = "//p[@class='oxd-userdropdown-name']")
    private WebElement profileDropdown;

    @FindBy(xpath = "//a[text()='Logout']")
    private WebElement logout;

    public boolean isDashboardDisplayed() {
        return isElementDisplayed(dashboardHeader);
    }

    public void clickPIM() {
        clickOnAElement(pimMenu);
    }

    public void clickAdmin() {
        clickOnAElement(adminMenu);
    }

    public void clickLeave() {
        clickOnAElement(leaveMenu);
    }

    public void clickRecruitment() {
        clickOnAElement(recruitmentMenu);
    }

    public void logout() {
        clickOnAElement(profileDropdown);
        clickOnAElement(logout);
    }
}
