package utilities;


import java.io.FileReader;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BaseClass {
public static WebDriver driver;//class level or instance variable
	
public String readDataFromPropertyFile(String key) {

    Properties properties = new Properties();

    try (FileReader fileReader = new FileReader("./config.properties")) {

        properties.load(fileReader);
        return properties.getProperty(key);

    } catch(Exception e) {

        e.printStackTrace();
    }

    return null;
}

public void launchBrowser() {
	 	String browserName = readDataFromPropertyFile("browser");

	    if (browserName.equalsIgnoreCase("chrome")) {

	        driver = new ChromeDriver();

	    } else if (browserName.equalsIgnoreCase("edge")) {

	        driver = new EdgeDriver();

	    } else {

	        throw new RuntimeException("Invalid Browser Name : " + browserName);
	    }

	    driver.manage().window().maximize();

	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	    driver.get(readDataFromPropertyFile("url"));
}



public String getPageTitle() {
	return driver.getTitle();
} //this method is giving you the actual value

public void sendKeys(WebElement element, String text) {
	element.sendKeys(text);//sendKeys(username, "GrowSkill")
}

public void clickOnAElement(WebElement element) {
	element.click();//predefined
}

public String getCurrentUrl() {
	return driver.getCurrentUrl();
}
public void scrollToAnElement(WebElement element) {
	JavascriptExecutor js = (JavascriptExecutor)driver;
	js.executeScript("arguments[0].scrollIntoView();", element);
}
public void waitUntilElementIsVisible(WebElement element, int time) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
	wait.until(ExpectedConditions.visibilityOf(element));
}

public void clearText(WebElement element) {
    element.clear();
}

public String getText(WebElement element) {
    return element.getText();
}

public void waitUntilElementIsClickable(WebElement element, int time) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
    wait.until(ExpectedConditions.elementToBeClickable(element));
}

public void waitUntilElementIsInvisible(WebElement element, int time) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
    wait.until(ExpectedConditions.invisibilityOf(element));
}

public void selectDropdownByVisibleText(WebElement element, String text) {
    Select select = new Select(element);
    select.selectByVisibleText(text);
}

public void selectDropdownByIndex(WebElement element, int index) {
    Select select = new Select(element);
    select.selectByIndex(index);
}


public void selectDropdownByValue(WebElement element, String value) {

    Select select = new Select(element);
    select.selectByValue(value);
}

public boolean isElementDisplayed(WebElement element) {
    return element.isDisplayed();
}

public boolean isElementEnabled(WebElement element) {
    return element.isEnabled();
}

public void javascriptClick(WebElement element) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].click();", element);
}

public void closeBrowser() {

    if (driver != null) {
        driver.quit();
    }
}
	
}
