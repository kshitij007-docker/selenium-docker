package org.kshitijselenium.tests;

import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.kshitijselenium.listener.TestListener;
import org.kshitijselenium.util.Config;
import org.kshitijselenium.util.Constants;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.tracing.opentelemetry.SeleniumSpanExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@Listeners({TestListener.class}) //here listners are added so that we can take screenshot on test failures.
//Here it is array we can give multiple Listener classes if we want
public class AbstractTestSeleniumGrid {

    @BeforeSuite
    public void setupConfig()
    {
        Config.initialize();
    }

    private static final Logger log= LoggerFactory.getLogger(AbstractTestSeleniumGrid.class);
    protected WebDriver driver;

        @BeforeTest
       // @Parameters({"browser"}) //to run the test with different browser
       // public void setDriver(String browser) throws MalformedURLException {  //to run the test with different browser
           // public void setDriver() throws MalformedURLException {
        //    WebDriverManager.chromedriver().setup(); //Since we are initialising driver in following methods we dnt need it here
           // System.getProperty("selenium.grid.enabled"); // we need  boolean value therefore we used Boolean.getBoolean

//           if( Boolean.getBoolean("selenium.grid.enabled"))
//            {
//                this.driver=getRemoteDriver();
//              //  this.driver=getRemoteDriver(browser); //to run the test with different browser
//            }
//           else {
//               this.driver=getLocalDriver();
//           }
// //**************** note -- This method can be used if we dnt want refactored code
//        }

        public void setDriverAfterRefactor(ITestContext ctx) throws MalformedURLException {
            //*** It is refactored driver lect. 113
//           if(Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED)))
//            {
//                this.driver=getRefactoredRemoteDriver();
//            } else
//            {
//                this.driver=getLocalDriver();
//            }

            //further refactoring
           this.driver=Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED))?getRefactoredRemoteDriver() : getLocalDriver();

           ctx.setAttribute(Constants.DRIVER,this.driver);
           //Lect. 119 This attribute will give information about the current test which is being executed by this driver
            //We have just store that driver information in the variable 'DRIVER' to use it later on
            //This information will go to listener and hence we can take screenshot of failed test

        }

       // private WebDriver getRemoteDriver(String browser) throws MalformedURLException {//When selenium grid property in pom.xml is true than we will call this method
         //http://localhost:4444/wd/hub  --This is the path where we will check UI of selenium Grid
            private WebDriver getRemoteDriver() throws MalformedURLException {
            Capabilities capabilities = null;
            if(System.getProperty("browser").equalsIgnoreCase("chrome"))
           // if(browser.equalsIgnoreCase("chrome")) //to run the test with different browser
                {
                capabilities=new ChromeOptions();
            }

            if(System.getProperty("browser").equalsIgnoreCase("firefox"))
           // if(browser.equalsIgnoreCase("firefox")) //to run the test with different browsers
            {
                capabilities=new FirefoxOptions();
            }



            return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);

        }

    private WebDriver getRefactoredRemoteDriver() throws MalformedURLException
    {
        //Lect. 113

        Capabilities capabilities=new ChromeOptions();

        if(Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER)))
            capabilities=new FirefoxOptions();

        String urlFormat=Config.get(Constants.GRID_URL_FORMAT);
        String hubHost=Config.get(Constants.GRID_HUB_HOST);

        String url=String.format(urlFormat,hubHost); //it will replace the value of %s from the value of key 'urlFormat'
        //by value of key 'hubHost' i.e. local Host-- check default.properties file


        log.info("grid url :{}",url);
        return new RemoteWebDriver(new URL(url),capabilities);
    }

        private WebDriver getLocalDriver() //When selenium grid property in pom.xml is false than we will call this method
        {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        }


        @AfterTest
        public void quitSession()
        {
            driver.quit();
        }

    @AfterMethod //This method is to create wait so that we can see dockerised selenium grid container
    public void sleep()
    {
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));

    }
    }


