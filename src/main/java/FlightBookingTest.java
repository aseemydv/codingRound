import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class FlightBookingTest extends BaseSetup{

    WebDriver driver;
    BaseSetup bs = new BaseSetup();

    @Parameters({"url"})
    @BeforeTest
    public void setUpBeforeTest(String url){
        bs.setUpBeforeTest(url);
    }

    @Parameters({"browser"})
    @BeforeClass
    public void setUp(String browser) throws InterruptedException {
        driver = bs.setUpBeforeClass(browser);
    }
    @Test
    public void testThatResultsAppearForAOneWayJourney() {

        waitFor(2000);
        driver.findElement(By.id("OneWay")).click();

        driver.findElement(By.id("FromTag")).clear();
        driver.findElement(By.id("FromTag")).sendKeys("Bangalore");

        //wait for the auto complete options to appear for the origin

        waitFor(2000);
        List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
        originOptions.get(0).click();

        /**
         * changed ID "toTag" to "ToTag"
         */
        driver.findElement(By.id("ToTag")).clear();
        driver.findElement(By.id("ToTag")).sendKeys("Delhi");

        //wait for the auto complete options to appear for the destination

        waitFor(2000);
        //select the first item from the destination auto complete list
        List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
        destinationOptions.get(0).click();

        /**
         * changed div[1] to div[2] for selecting an available date from next month
         */
        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[2]/table/tbody/tr[3]/td[7]/a")).click();

        //all fields filled in. Now click on search
        driver.findElement(By.id("SearchBtn")).click();

        waitFor(5000);
        //verify that result appears for the provided journey search
        Assert.assertTrue(isElementPresent(By.className("searchSummary")));
    }

    @AfterClass
    private void tearDown(){
        //close the browser
        driver.close();
    }

    private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
