import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestCase06 {
    private WebDriver driver;

    @BeforeMethod
    public void beforeTest() {
        System.setProperty("webdriver.gecko.driver", "/opt/geckodriver");
        driver = new FirefoxDriver();
        driver.get("http://107.170.213.234/catalog/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test(groups = {"test"})
    public void testCase06() {
        driver.findElement(By.cssSelector("a:nth-child(2) > u")).click();
        driver.findElement(By.linkText("Password forgotten? Click here.")).click();
        driver.findElement(By.cssSelector("#tdb4 > .ui-button-icon-primary")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector(".messageStackError > .messageStackError")).getText(),
                " Error: The E-Mail Address was not found in our records, please try again.");}

    @AfterMethod
    public void afterTest() {
        driver.quit();
    }

}