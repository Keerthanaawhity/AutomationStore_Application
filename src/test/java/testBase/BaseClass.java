package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BaseClass {

    // IMPORTANT: Use ThreadLocal to manage WebDriver instances for parallel execution
    // Each thread (for each browser in parallel) will have its own independent driver
    private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    public Logger logger = LogManager.getLogger(this.getClass());
    public static Properties p;  // Properties can also remain non-static, loaded per class instance.

    // Helper method to get the WebDriver instance for the current thread
    public static WebDriver getDriver() {
        return threadLocalDriver.get();
    }

    @SuppressWarnings("deprecation")
    @BeforeClass(groups= {"Sanity", "Regression", "Master", "DataDriven"}, alwaysRun=true)
    @Parameters({"os","browser"})
    public void setup(String os, String browser) throws IOException
    {
        // Loading config.properties file
        // Note: For parallel execution, ensure this file reading is thread-safe
        // or properties are loaded once per suite if they are truly static for all tests.
        // For simplicity, loading per class instance here is fine.
        FileReader fr = new FileReader("./src//test//resources//config.properties");
        p = new Properties();
        p.load(fr);

        logger = LogManager.getLogger(this.getClass());

        WebDriver driver = null; // Local WebDriver variable for current setup method

        String executionEnv = p.getProperty("execution_env");
        if(executionEnv.equalsIgnoreCase("remote"))
        {
            DesiredCapabilities capabilities = new DesiredCapabilities();

            // OS setup
            if(os.equalsIgnoreCase("Windows"))
            {
                capabilities.setPlatform(Platform.WINDOWS);
            }
            else if(os.equalsIgnoreCase("mac"))
            {
                capabilities.setPlatform(Platform.MAC);
            }
            else
            {
                System.out.println("No Matching Platform specified in TestNG XML.");
                logger.error("No Matching Platform specified: " + os);
                return; // Exit setup if platform is not matched
            }

            // Browser setup
            switch(browser.toLowerCase())
            {
                case "chrome": capabilities.setBrowserName("chrome"); break;
                case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
                default:
                    System.out.println("No Matching Browser specified in TestNG XML.");
                    logger.error("No Matching Browser specified: " + browser);
                    return; // Exit setup if browser is not matched
            }

            // Launching URL in the remote grid
            // Ensure your Selenium Grid URL is correct and reachable
            driver = new RemoteWebDriver(new URL("http://192.168.174.126:4444/wd/hub"), capabilities);
            logger.info("Remote WebDriver initialized for " + browser + " on " + os);

        }
        else if(executionEnv.equalsIgnoreCase("local"))
        {
            // Local execution setup (ensure drivers are in system PATH or specified via System.setProperty)
            switch(browser.toLowerCase())
            {
                case "chrome": driver = new ChromeDriver(); break;
                case "edge": driver = new EdgeDriver(); break;
                default:
                    System.out.println("Invalid Browser specified for local execution.");
                    logger.error("Invalid Browser specified for local execution: " + browser);
                    return; // Exit setup if browser is invalid
            }
            logger.info("Local WebDriver initialized for " + browser);
        }
        else {
            System.out.println("Invalid execution_env in config.properties: " + executionEnv);
            logger.error("Invalid execution_env: " + executionEnv);
            return;
        }

        // Store the initialized driver in ThreadLocal for the current thread
        threadLocalDriver.set(driver);

        // Common driver configurations
        getDriver().get(p.getProperty("appURL"));
        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies(); // Clearing cookies can sometimes help with session issues
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        logger.info("Application launched: " + getDriver().getTitle() + " on " + browser);
    }

    @SuppressWarnings("deprecation")
    public String randomString()
    {
        String genString = RandomStringUtils.randomAlphabetic(5);
        return genString;
    }

    @SuppressWarnings("deprecation")
    public String randomNum()
    {
        String genNumber = RandomStringUtils.randomNumeric(5);
        return genNumber;
    }

    @SuppressWarnings("deprecation")
	@AfterClass(alwaysRun = true) // alwaysRun=true ensures it runs even if setup failed
    public void tearDown() {
    	if (logger != null) {
    	    logger.info("Attempting to enter tearDown method..."+ Thread.currentThread().getId());
    	} else {
    	    System.out.println("Logger is null — setup() may have failed.");
    	}
        WebDriver driver = getDriver(); // Get the driver instance for the current thread

        if (driver == null) {
            logger.warn("Driver is null in tearDown for thread: " + Thread.currentThread().getId() + ". Could not quit browser.");
        } else {
            try {
                driver.quit();
                logger.info("Browser closed successfully by AfterClass for thread: " + Thread.currentThread().getId());
            } catch (Exception e) {
                logger.error("Error during browser quit in AfterClass for thread " + Thread.currentThread().getId() + ": " + e.getMessage(), e);
            } finally {
                // IMPORTANT: Remove the driver from ThreadLocal to prevent memory leaks and ensure clean state
                threadLocalDriver.remove();
                logger.info("Removed driver from ThreadLocal for thread: " + Thread.currentThread().getId());
            }
        }
        logger.info("Exiting tearDown method (AfterClass) for thread: " + Thread.currentThread().getId());
    }

    // Capture screenshot method - now retrieves driver from ThreadLocal
    public static String captureScreen(String name) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        // Get the WebDriver instance for the current thread statically
        // This relies on the ThreadLocal being set in the calling thread (e.g., from ExtentReportManager)
        WebDriver currentDriver = threadLocalDriver.get();
        if (currentDriver == null) {
            System.err.println("WebDriver instance is null when attempting to capture screenshot.");
            return "NoScreenshotCaptured_DriverIsNull";
        }

        TakesScreenshot ts = (TakesScreenshot) currentDriver;
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);

        File targetDir = new File(System.getProperty("user.dir") + "\\screenshots");
        if (!targetDir.exists()) targetDir.mkdirs();

        String targetFilePath = targetDir + "\\" + name + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);

        FileUtils.copyFile(sourceFile, targetFile);

        return targetFilePath;
    }
    
    public void switchToNewTab() {
        String mainHandle = getDriver().getWindowHandle();
        for (String handle : getDriver().getWindowHandles()) {
            if (!handle.equals(mainHandle)) {
                getDriver().switchTo().window(handle);
                break;
            }
        }
    }

    public String CurrentDate(String[] args) {
            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            String formattedDate = today.format(formatter);
            return formattedDate;
        }
}