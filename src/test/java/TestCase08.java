import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestCase08 {
    private WebDriver driver;
    @BeforeMethod
    public void beforeTest() {
        System.setProperty("webdriver.gecko.driver", "/opt/geckodriver");
        driver = new FirefoxDriver();
        driver.get("http://107.170.213.234/catalog/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test(groups = {"test"})
    public void testCase08() {
        Select dropDown = new Select(driver.findElement(By.name("manufacturers_id")));
        List<WebElement> options = dropDown.getOptions();
        String expectedStr = "Please Select\n" +
                "Canon\n" +
                "Fox\n" +
                "GT Interactive\n" +
                "Hewlett Packard\n" +
                "Logitech\n" +
                "Matrox\n" +
                "Microsoft\n" +
                "Samsung\n" +
                "Sierra\n" +
                "Warner";
        for(int i = 0; i < 10; ++i)
        {
            Assert.assertEquals(dropDown.getWrappedElement().getText(), expectedStr);
        }
    }

    @AfterMethod
    public void afterTest()
    {
        driver.quit();
    }
}
