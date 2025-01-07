package utilits;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener
{
    Logger logger = LoggerFactory.getLogger(TestNGListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        logger.info("METHOD ===> " + result.getMethod());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        logger.info(result.getMethod() + " <=== PASS");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        logger.info(result.getMethod() + " <=== FAIL");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
        logger.info(result.getMethod() + " <=== IGNORE");
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
        logger.info("DATE OF THE START ===> "+context.getStartDate());
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        logger.info("DATE OF THE END ===> "+context.getEndDate());
    }
}
