import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestCase02 {
    private WebDriver driver;
    private String browser;
    private String url;
    private String username;
    private String pwd;
    @BeforeMethod
    public void beforeTest()
    {
        Properties prop = new Properties();
        try{
            prop.load(new FileInputStream("testCase02.properties"));
            url = prop.getProperty("url");
            browser = prop.getProperty("browser");
            username = prop.getProperty("username");
            pwd = prop.getProperty("pwd");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(browser.equals("firefox"))
        {
            System.setProperty("webdriver.gecko.driver", "/opt/geckodriver");
            driver = new FirefoxDriver();
        }
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test(groups = {"test"})
    public void testCase02() throws InterruptedException {
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
