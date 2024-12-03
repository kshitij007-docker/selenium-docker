package org.kshitijselenium.tests.vendorportal;



import io.github.bonigarcia.wdm.WebDriverManager;
import org.kshitijSelenium.vendorportal.DashboardPage;
import org.kshitijSelenium.vendorportal.LoginPage;
import org.kshitijselenium.tests.AbstractTest;
import org.kshitijselenium.tests.AbstractTestSeleniumGrid;
import org.kshitijselenium.tests.vendorportal.model.VendorPortalTestData;
import org.kshitijselenium.util.Config;
import org.kshitijselenium.util.Constants;
import org.kshitijselenium.util.JsonUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

public class VendorPortalTest extends AbstractTestSeleniumGrid {

   // private WebDriver driver;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private VendorPortalTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void setPageObjects(String testDataPath) throws IOException {
        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
       // this.testData = JsonUtil.getTestData(testDataPath);
        this.testData = JsonUtil.getTestDataGeneric(testDataPath,VendorPortalTestData.class);
    }

  /* public void setDriver() //we will call it from abstract class
    {
        WebDriverManager.chromedriver().setup();
        this.driver=new ChromeDriver();
        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
    }*/
    @Test
    public void loginTest(){
     //   loginPage=new LoginPage(driver);
       // loginPage.gotTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/vendor-app/index.html");
        loginPage.gotTo(Config.get(Constants.VENDOR_PORTAL_URL));
        Assert.assertTrue(loginPage.isAt());
    //    loginPage.login("sam","sam");
        loginPage.login(testData.username(), testData.password());
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest(){
      //  dashboardPage=new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.isAt());

        // finance metrics
      //  Assert.assertEquals(dashboardPage.getMonthlyEarning(), "$40,000");
        Assert.assertEquals(dashboardPage.getAnnualEarning(), testData.annualEarning());

        Assert.assertEquals(dashboardPage.getMonthlyEarning(), testData.monthlyEarning());
        Assert.assertEquals(dashboardPage.getAnnualEarning(), testData.annualEarning());
        Assert.assertEquals(dashboardPage.getProfitMargin(), testData.profitMargin());
        Assert.assertEquals(dashboardPage.getAvailableInventory(), testData.availableInventory());



         //order history search
     /*   dashboardPage.searchOrderHistoryBy(testData.searchKeyword());
        Assert.assertEquals(dashboardPage.getSearchResultsCount(), testData.searchResultsCount());*/
    }

    @Test(dependsOnMethods = "dashboardTest")
    public void logoutTest(){
        dashboardPage.logout();

        Assert.assertTrue(loginPage.isAt());
    }

   /* @AfterTest
    public void quitSession()
    {
        driver.quit();
    }*/ //since we will use it in abstract class therefore removed it

//    @AfterMethod //This method is to create wait so that we can see dockerised selenium grid container
//    public void logoutTest()
//    {
//        dashboardPage.logout();
//        Assert.assertTrue(loginPage.isAt());
//
//    }
}
