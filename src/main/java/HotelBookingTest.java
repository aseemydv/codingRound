import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

import static org.openqa.selenium.support.PageFactory.*;

public class HotelBookingTest extends BaseSetup{

    private WebDriver driver;
    BaseSetup bs = new BaseSetup();

    @FindBy(linkText = "Hotels")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;

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
    public void shouldBeAbleToSearchForHotels() {
        PageFactory.initElements(driver, this);
        hotelLink.click();
        localityTextBox.sendKeys("Indiranagar, Bangalore");
        new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
        searchButton.click();
    }

    @AfterClass
    private void tearDown(){
        //close the browser
        driver.close();
    }

}
