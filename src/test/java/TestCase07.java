import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestCase07 {
    private WebDriver driver;
    @BeforeMethod
    public void beforeTest() {
        System.setProperty("webdriver.gecko.driver", "/opt/geckodriver");
        driver = new FirefoxDriver();
        driver.get("http://107.170.213.234/catalog/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test(groups = {"test"})
    public void testCase07() {
        List <WebElement> elements = driver.findElements(By.cssSelector("a[href*=\"http\"]"));
        List <String> links = new ArrayList<String>();
        for(WebElement e: elements)
        {
            if(e.getAttribute("href") != null)
            {
                System.out.println(e.getText());
                links.add(e.getAttribute("href"));
            }
        }
        for(String link: links)
        {
            driver.get(link);
            Assert.assertEquals(driver.getTitle()!=null, true);
        }

    }

    @AfterMethod
    public void afterTest()
    {
        driver.quit();
    }
}