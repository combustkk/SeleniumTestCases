import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class TestCase04 {
    private WebDriver driver;
    @DataProvider(name="TestData")
    public Object [][] getTestCase01Data()
    {
        Object[][] obj = new Object[2][2];
        obj[0][0] = "ecalix@test.com";
        obj[0][1] = "test123";
        obj[1][0] = "justagile@test.com";
        obj[1][1] = "test123";
        return obj;
    }
    @BeforeMethod
    public void beforeTest()
    {
        System.setProperty("webdriver.gecko.driver", "/opt/geckodriver");
        driver = new FirefoxDriver();
        driver.get("http://107.170.213.234/catalog/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @Test(groups = {"test"}, dataProvider = "TestData")
    public void testCase04(String username, String pwd) throws InterruptedException {
        driver.findElement(By.cssSelector("a:nth-child(2) > u")).click();
        driver.findElement(By.name("email_address")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(pwd);
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
