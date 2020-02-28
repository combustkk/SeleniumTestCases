import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TestCase09 {
    private WebDriver driver;

    @BeforeMethod
    public void beforeTest() {
        System.setProperty("webdriver.gecko.driver", "/opt/geckodriver");
        driver = new FirefoxDriver();
        driver.get("http://107.170.213.234/catalog/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test(groups = {"test"})
    public void test() {
        Random random = new Random();
        int randomId = random.nextInt();
        driver.findElement(By.cssSelector("a:nth-child(3) > u")).click();
        driver.findElement(By.cssSelector(".fieldValue > input:nth-child(2)")).click();
        driver.findElement(By.name("firstname")).sendKeys("nina" + randomId);
        driver.findElement(By.name("lastname")).sendKeys("wa");
        driver.findElement(By.id("dob")).click();
        driver.findElement(By.cssSelector(".ui-datepicker-year")).click();
        driver.findElement(By.linkText("12")).click();
        driver.findElement(By.name("email_address")).sendKeys("ninawrandomId@test.com");
        driver.findElement(By.name("street_address")).sendKeys("Santa Ana Canyon");
        driver.findElement(By.name("postcode")).sendKeys("92612");
        driver.findElement(By.name("city")).sendKeys("Irvine");
        driver.findElement(By.name("state")).sendKeys("CA");
        {
            WebElement dropdown = driver.findElement(By.name("country"));
            dropdown.findElement(By.xpath("//option[. = 'United States']")).click();
        }
        driver.findElement(By.cssSelector("option:nth-child(224)")).click();
        driver.findElement(By.name("telephone")).sendKeys("6504837065");
        driver.findElement(By.name("password")).sendKeys("test123");
        driver.findElement(By.name("confirmation")).sendKeys("test123");
        driver.findElement(By.cssSelector("#tdb4 > .ui-button-text")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("h1")).getText(), "Your Account Has Been Created!");
    }
    @AfterMethod
    public void afterTest() {
        driver.quit();
    }
}
