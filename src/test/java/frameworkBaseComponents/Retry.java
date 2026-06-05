package frameworkBaseComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry  implements IRetryAnalyzer{
	private int retryCount = 0;
    private static final int maxRetryCount = 2; // retry twice

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            return true; // re-run the test
        }
        return false; // stop retrying
    }

}
