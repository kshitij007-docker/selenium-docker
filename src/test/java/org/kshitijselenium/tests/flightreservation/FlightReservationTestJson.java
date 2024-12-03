package org.kshitijselenium.tests.flightreservation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.kshitijSelenium.flightreservation.*;
import org.kshitijselenium.tests.AbstractTest;
import org.kshitijselenium.tests.AbstractTestSeleniumGrid;
import org.kshitijselenium.tests.flightreservation.model.FlightReservationTestData;
import org.kshitijselenium.util.Config;
import org.kshitijselenium.util.Constants;
import org.kshitijselenium.util.JsonUtil;
import org.kshitijselenium.util.JsonUtilFlightReservation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

public class FlightReservationTestJson extends AbstractTestSeleniumGrid {

  //  private WebDriver driver;
    private int noOfPassengers;
    private String expectedPrice;
    RegistrationPage registrationPage;
    RegistrationConfirmation registrationConfirmation;
    FlightSearchPage flightSearch;
    FlightReservationTestData testData;
    SelectFilghtsPage selectFilghtsPage;
    FlightConfirmationPage flightConfirmationPage;




    @BeforeTest
    @Parameters({"testDataPath"})
    public void setPageObject(String testDataPath) throws IOException {
       // WebDriverManager.chromedriver().setup(); //This dependency will automatically download the chrome driver executable
        //and keep it in the path for us to use.
      //  this.noOfPassengers=noOfPassengers;
       // this.expectedPrice=expectedPrice;
        this.registrationPage=new RegistrationPage(driver);
        this.registrationConfirmation=new RegistrationConfirmation(driver);
        this.flightSearch=new FlightSearchPage(driver);
        this.selectFilghtsPage=new SelectFilghtsPage(driver);
        this.flightConfirmationPage=new FlightConfirmationPage(driver);
       // this.testData= JsonUtilFlightReservation.getTestData(testDataPath);
        this.testData= JsonUtil.getTestDataGeneric(testDataPath, FlightReservationTestData.class);


      //  this.driver=new ChromeDriver();
    }


    @Test
    public void userRegistrationTest()
    {
      //  RegistrationPage registrationPage=new RegistrationPage(driver);
      //  registrationPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html");
        registrationPage.goTo(Config.get(Constants.FLIGHT_RESERVATION_URL));
        Assert.assertTrue(registrationPage.isAt());
        registrationPage.enterUserDetails(testData.firstName(), testData.lastName());
        registrationPage.enterUserCredentials(testData.email(),testData.password());
        registrationPage.enterAddress(testData.street(), testData.zip());
        registrationPage.register();

    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationTest()
    {
       // RegistrationConfirmation registrationConfirmation=new RegistrationConfirmation(driver);
        Assert.assertTrue(registrationConfirmation.isAt());
        registrationConfirmation.searchBtn();
    }

    @Test(dependsOnMethods = "registrationConfirmationTest")
    public void flightSearchTest()
    {
      //  FlightSearchPage flightSearch=new FlightSearchPage(driver);
        Assert.assertTrue(flightSearch.isAt());
       // flightSearch.selectPassengers(3);
        flightSearch.selectPassengers(noOfPassengers);
        flightSearch.searchFlight();
    }

    @Test(dependsOnMethods = "flightSearchTest")
    public void searchFlightsTest()
    {
      //  SelectFilghtsPage selectFilghtsPage=new SelectFilghtsPage(driver);
        Assert.assertTrue(selectFilghtsPage.isAt());
        selectFilghtsPage.selectFlight();
        selectFilghtsPage.confirmFlight();
    }

    @Test(dependsOnMethods = "searchFlightsTest")
    public void flightConfirmationTest()
    {
      //  FlightConfirmationPage flightConfirmationPage=new FlightConfirmationPage(driver);
        Assert.assertTrue(flightConfirmationPage.isAt());
        flightConfirmationPage.getPrice();
     //   Assert.assertEquals(flightConfirmationPage.getPrice(),"$2338 USD");

        Assert.assertEquals(flightConfirmationPage.getPrice(),testData.expectedPrice(),expectedPrice);
    }

    @AfterTest
    public void quitDriver()
    {
        driver.quit();
    }
}
