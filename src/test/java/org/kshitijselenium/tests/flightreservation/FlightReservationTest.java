package org.kshitijselenium.tests.flightreservation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.kshitijSelenium.flightreservation.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FlightReservationTest {

    private WebDriver driver;
    private int noOfPassengers;
    private String expectedPrice;



    @BeforeTest
    @Parameters({"noOfPassengers","expectedPrice"})
    public void setDriver(int noOfPassengers,String expectedPrice )
    {
        WebDriverManager.chromedriver().setup(); //This dependency will automatically download the chrome driver executable
        //and keep it in the path for us to use.
        this.noOfPassengers=noOfPassengers;
        this.expectedPrice=expectedPrice;
        this.driver=new ChromeDriver();
    }


    @Test
    public void userRegistrationTest()
    {
        RegistrationPage registrationPage=new RegistrationPage(driver);
        registrationPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html");
        Assert.assertTrue(registrationPage.isAt());
        registrationPage.enterUserDetails("sel","doc");
        registrationPage.enterUserCredentials("se@l","d");
        registrationPage.enterAddress("street","332119");
        registrationPage.register();

    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationTest()
    {
        RegistrationConfirmation registrationConfirmation=new RegistrationConfirmation(driver);
        Assert.assertTrue(registrationConfirmation.isAt());
        registrationConfirmation.searchBtn();
    }

    @Test(dependsOnMethods = "registrationConfirmationTest")
    public void flightSearchTest()
    {
        FlightSearchPage flightSearch=new FlightSearchPage(driver);
        Assert.assertTrue(flightSearch.isAt());
       // flightSearch.selectPassengers(3);
        flightSearch.selectPassengers(noOfPassengers);
        flightSearch.searchFlight();
    }

    @Test(dependsOnMethods = "flightSearchTest")
    public void searchFlightsTest()
    {
        SelectFilghtsPage selectFilghtsPage=new SelectFilghtsPage(driver);
        Assert.assertTrue(selectFilghtsPage.isAt());
        selectFilghtsPage.selectFlight();
        selectFilghtsPage.confirmFlight();
    }

    @Test(dependsOnMethods = "searchFlightsTest")
    public void flightConfirmationTest()
    {
        FlightConfirmationPage flightConfirmationPage=new FlightConfirmationPage(driver);
        Assert.assertTrue(flightConfirmationPage.isAt());
        flightConfirmationPage.getPrice();
     //   Assert.assertEquals(flightConfirmationPage.getPrice(),"$2338 USD");

        Assert.assertEquals(flightConfirmationPage.getPrice(),expectedPrice);
    }

    @AfterTest
    public void quitDriver()
    {
        driver.quit();
    }
}
