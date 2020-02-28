import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class TestCase03 {
    private WebDriver driver;
    private String browser;
    private String url;
    private String username;
    private String pwd;
    @BeforeMethod
    public void beforeTest()
    {
        try{
            Workbook workbook = WorkbookFactory.create(new FileInputStream("testCase03.xlsx"));
            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter dataFormatter = new DataFormatter();
            Iterator<Row> rowIterator = sheet.rowIterator();
            int browserCol = -1;
            int urlCol = -1;
            int pwdCol = -1;
            int usernameCol = -1;
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    String cellValue = dataFormatter.formatCellValue(cell);
                    if(row.getRowNum() == 0)
                    {
                        if(cellValue.equals("browser"))
                        {
                            browserCol = cell.getColumnIndex();
                        }
                        if(cellValue.equals("url"))
                        {
                            urlCol = cell.getColumnIndex();
                        }
                        if(cellValue.equals("username"))
                        {
                            usernameCol = cell.getColumnIndex();
                        }
                        if(cellValue.equals("pwd"))
                        {
                            pwdCol = cell.getColumnIndex();
                        }
                    }
                    else
                    {
                        if(cell.getColumnIndex() == urlCol)
                        {
                            url = cellValue;
                        }
                        if(cell.getColumnIndex() == pwdCol)
                        {
                            pwd = cellValue;
                        }
                        if(cell.getColumnIndex() == usernameCol)
                        {
                            username = cellValue;
                        }
                        if(cell.getColumnIndex() == browserCol)
                        {
                            browser = cellValue;
                        }
                    }
                }
            }

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
    public void testCase03() throws InterruptedException {
        driver.findElement(By.cssSelector("a:nth-child(2) > u")).click();
        driver.findElement(By.name("email_address")).sendKeys("ecalix@test.com");
        driver.findElement(By.name("password")).    sendKeys("test123");
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
