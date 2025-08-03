package utilities;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import testBase.BaseClass;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.*;

public class ExtentReportManager implements ITestListener, ISuiteListener {

    public static ExtentReports extent;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    public static ExtentSparkReporter sparkReporter;
    String repName;
    String pathOfExtentReport;

    @Override
    public void onStart(ISuite suite) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timeStamp + ".html";
        pathOfExtentReport = System.getProperty("user.dir") + "/reports/" + repName;

        sparkReporter = new ExtentSparkReporter(pathOfExtentReport);
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("Paperback e-commerce web Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Computer Name", "ASUS");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tester name", "Keerthanaa");
    }

    @Override
    public void onStart(ITestContext context) {
        String os = context.getCurrentXmlTest().getParameter("os");
        String browser = context.getCurrentXmlTest().getParameter("browser");

        extent.setSystemInfo("Operating System", os);
        extent.setSystemInfo("Browser name", browser);
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        extentTest.set(test);  // ✅ Thread-local binding
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test Case PASSED: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().log(Status.FAIL, "Test Case FAILED: " + result.getName());
        if (result.getThrowable() != null) {
            extentTest.get().log(Status.INFO, result.getThrowable().getMessage());
        }
        try {
            String imgPath = BaseClass.captureScreen(result.getName());
            extentTest.get().addScreenCaptureFromPath(imgPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.SKIP, "Test Case SKIPPED: " + result.getName());
        if (result.getThrowable() != null) {
            extentTest.get().log(Status.INFO, result.getThrowable().getMessage());
        }
    }

    @Override
    public void onFinish(ISuite suite) {
        extent.flush();
        try {
            Desktop.getDesktop().browse(new File(pathOfExtentReport).toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
