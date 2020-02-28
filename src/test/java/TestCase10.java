import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class TestCase10 {
    private WebDriver driver;
    private String[] productName;
    private int[] productQuantity;
    @BeforeMethod
    public void beforeTest() {
        System.setProperty("webdriver.gecko.driver", "/opt/geckodriver");
        driver = new FirefoxDriver();
        driver.get("http://107.170.213.234/catalog/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        productName = new String[5];
        productQuantity = new int[5];
        try {
            Workbook workbook = WorkbookFactory.create(new FileInputStream("testCase10.xlsx"));
            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter dataFormatter = new DataFormatter();
            Iterator<Row> rowIterator = sheet.rowIterator();
            int i = 0;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                productName[i] = dataFormatter.formatCellValue(row.getCell(0));
                productQuantity[i] = Integer.parseInt(dataFormatter.formatCellValue(row.getCell(1)));
                i++;
            }
            System.out.println(Arrays.toString(productName));
            System.out.println(Arrays.toString(productQuantity));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test(groups = {"test"})
    public void test()
    {
        driver.findElement(By.cssSelector("a:nth-child(2) > u")).click();
        driver.findElement(By.name("email_address")).sendKeys("ninaw@test.com");
        driver.findElement(By.name("password")).sendKeys("test123");
        driver.findElement(By.cssSelector("#tdb5 > span:nth-child(2)")).click();
        for(int i = 0; i < 5; ++i)
        {
            for(int j = 0; j < productQuantity[i]; ++j)
            {
                addItem(productName[i]);
            }
        }

        double sum = 0;
        for(WebElement e: driver.findElements(By.cssSelector("td > strong")))
        {
            sum += Double.parseDouble(e.getText().replaceAll("[^0-9.]",""));
        }
        Assert.assertEquals(sum,
                Double.parseDouble(driver.findElement(By.cssSelector("p > strong")).getText().replaceAll("[^0-9.]","")),
                10);
        driver.findElement(By.cssSelector(".ui-button-text:nth-child(1)")).click();

    }


    public void addItem(String productName)
    {
        driver.findElement(By.name("keywords")).clear();
        driver.findElement(By.name("keywords")).sendKeys(productName);
        driver.findElement(By.cssSelector("input:nth-child(3)")).click();
        driver.findElement(By.cssSelector("#tdb5 > .ui-button-text")).click();
    }
    @AfterMethod
    public void afterTest() {
        driver.quit();
    }
}
