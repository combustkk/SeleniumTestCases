import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class TestCase01 {
    private WebDriver driver;
    @BeforeMethod
    public void beforeTest()
    {
        System.setProperty("webdriver.gecko.driver", "/opt/geckodriver");
        driver = new FirefoxDriver();
        driver.get("http://107.170.213.234/catalog/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @Test(groups = {"test"})
    public void testCase01() throws InterruptedException {
        driver.findElement(By.cssSelector("a:nth-child(2) > u")).click();
        driver.findElement(By.name("email_address")).sendKeys("ecalix@test.com");
        driver.findElement(By.name("password")).sendKeys("test123");
        driver.findElement(By.cssSelector("#tdb5 > span:nth-child(2)")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("h1")).getText(),"Welcome to iBusiness");
        driver.findElement(By.cssSelector(".ui-button-text:nth-child(1)")).click();
    }
    @AfterMethod
    public void afterTest()
    {
        driver.quit();
    }
}
