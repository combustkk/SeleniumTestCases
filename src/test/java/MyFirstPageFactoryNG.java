import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MyFirstPageFactoryNG {
    @FindBy(how = How.CSS, using="a:nth-child(2) > u")
    private WebElement LOGYOURSELFIN;
    @FindBy(how = How.NAME, using="email_address")
    private WebElement EMAILADRESS;
    @FindBy(how = How.NAME, using="password")
    private WebElement PWD;
    @FindBy(how = How.CSS, using="#tdb5 > span:nth-child(2)")
    private WebElement LOGIN;
    @FindBy(how = How.CSS, using="h1")
    private WebElement LOGINTEXT;
    @FindBy(how = How.CSS, using=".ui-button-text:nth-child(1)")
    private WebElement LOGOFF;
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
    public void test() throws InterruptedException {
        PageFactory.initElements(driver, this);
        LOGYOURSELFIN.click();
        EMAILADRESS.sendKeys("ecalix@test.com");
        PWD.sendKeys("test123");
        LOGIN.click();
        Assert.assertEquals(LOGINTEXT.getText(),"Welcome to iBusiness");
        LOGOFF.click();
    }
    @AfterMethod
    public void afterTest()
    {
        driver.quit();
    }
}
