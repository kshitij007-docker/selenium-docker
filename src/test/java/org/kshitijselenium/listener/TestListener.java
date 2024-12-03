package org.kshitijselenium.listener;

import org.kshitijselenium.util.Constants;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result)
    {


      TakesScreenshot driver=(TakesScreenshot)result.getTestContext().getAttribute(Constants.DRIVER); //this information will come via 'AbstractTestSeleniumGrid'
       String screenshot= driver.getScreenshotAs(OutputType.BASE64); //We have used Base64 because it will be easy to use in jenkins
        String htmlImageFormat = "<img width=700px src='data:image/png;base64,%s' />";
        String htmlImage=String.format(htmlImageFormat,screenshot); //Here %s in htmlImageFormat will be replaced by string 'screenshot'
        Reporter.log(htmlImage); //Here we are embedding our image to our to testng report


    }
}
