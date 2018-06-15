import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class SignInTest extends BaseSetup{

    WebDriver driver;
    BaseSetup bs = new BaseSetup();


    @Parameters({"url"})
    @BeforeTest
    public void pageSetUp(String url){
        bs.setUpBeforeTest(url);
    }

    @Parameters({"browser"})
    @BeforeClass
    public void driverSetUp(String browser) throws InterruptedException {
        driver = bs.setUpBeforeClass(browser);
    }
    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

        waitFor(2000);

        driver.findElement(By.linkText("Your trips")).click();
        driver.findElement(By.id("SignIn")).click();

        driver.switchTo().frame(driver.findElement(By.id("modal_window")));
        driver.findElement(By.id("signInButton")).click();

        String errors1 = driver.findElement(By.id("errors1")).getText();
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
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
}
