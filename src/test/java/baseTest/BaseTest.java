package baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import libs.ScreenShot;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import pages.PageProvider;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.util.ArrayList;

public class BaseTest {
    WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());
    protected PageProvider pageProvider;
    protected ArrayList<ScreenShot> listOfScreenShots = new ArrayList<>();

    @Before
    public void setUp() {
        logger.info("----- " + testName.getMethodName() + " was started ------------");
        webDriver = initDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        logger.info("Browser was opened");
        pageProvider = new PageProvider(webDriver);
    }

    @Rule
    public TestName testName = new TestName();

    @Rule()
    public final TestWatcher watchman = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            screenshot();
        }

        public void saveScreenshot(ArrayList<ScreenShot> screenShots) {
            screenShots.forEach(screenShot -> Allure.addAttachment(screenShot.getName(),
                    new ByteArrayInputStream(screenShot.getScreenShotImg())));
        }

        public void screenshot() {
            if (webDriver == null) {
                logger.info("Driver for screenshot not found");
                return;
            }
            byte[] screen = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
            listOfScreenShots.add(new ScreenShot("Default screenShot after failed test", screen));
            saveScreenshot(listOfScreenShots);
        }


        @Override
        protected void finished(Description description) {
            logger.info(
                    String.format("Finished test: %s::%s", description.getClassName(), description.getMethodName()));
            try {
                webDriver.quit();
                logger.info("Browser was closed");
            } catch (Exception e) {
                logger.error(e);
            }
        }

    };


    private WebDriver initDriver() {
        String browser = System.getProperty("browser");
        if ((browser == null) || ("chrome".equals(browser.toLowerCase()))) {
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
        } else if ("firefox".equals(browser.toLowerCase())) {
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();
        } else if ("ie".equals(browser.toLowerCase())) {
            WebDriverManager.iedriver().setup();
            webDriver = new InternetExplorerDriver();
        } else if ("safari".equalsIgnoreCase(browser)) {
            WebDriverManager.safaridriver().setup();
            webDriver = new SafariDriver();
        } else if ("edge".equalsIgnoreCase(browser)) {
            WebDriverManager.edgedriver().setup();
            webDriver = new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Browser " + browser + " is not supported");
        }
        return webDriver;
    }
}
