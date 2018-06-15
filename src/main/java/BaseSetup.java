import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseSetup {
    WebDriver driver;
    public static Logger logger = Logger.getLogger("ctLogger");
    private String navigate;

    protected void setUpBeforeTest(String url){
        navigate = url;
    }

    protected WebDriver setUpBeforeClass(String browser) throws InterruptedException {
        setBrowser(browser);
        driver.get(navigate);
        driver.manage().window().maximize();
        return driver;
    }

    private void setBrowser(String browser) throws InterruptedException {
        switch (browser) {
            case "chrome":
                setDriverPath();
                logger.info("-------------Launching Chrome");
                driver = new ChromeDriver();
                break;
            case "firefox":
                logger.info("-------------Launching Firefox");
                driver = new FirefoxDriver();
                Thread.sleep(5000);
                break;
            default:
                logger.warn("Unable to find the specified browser name "+browser+"\n Choosing Chrome as default.");
                logger.info("-------------Launching Chrome");
                setDriverPath();
                driver = new FirefoxDriver();
                break;
        }
    }
    private void setDriverPath() {
        String PLATFORM = System.getProperty("os.name").toLowerCase();
        if (PLATFORM.contains("mac")) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (PLATFORM.contains("windows")) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (PLATFORM.contains("linux")) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }
}
