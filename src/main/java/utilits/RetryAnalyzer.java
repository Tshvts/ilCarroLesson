package utilits;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer
{
    int retryCount = 0;
    private static int maxValue = 3;

    @Override
    public boolean retry(ITestResult result)
    {
        if(retryCount<maxValue)
        {
            retryCount++;
            return true;
        }
        return false;
    }
}
