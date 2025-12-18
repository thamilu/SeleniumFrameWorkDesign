package rahulShettyAcademy.TestComponents;

import org.testng.IRetryAnalyzer;

public class Retry implements IRetryAnalyzer {
	int count = 0;
	int maxTry = 1;

	@Override
	public boolean retry(org.testng.ITestResult result) {
		if (count < maxTry) {
			count++;
			return true;
		}
		return false;
	}

}
