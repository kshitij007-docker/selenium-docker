package org.kshitijSelenium.flightreservation;

import org.kshitijSelenium.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class FlightSearchPage extends AbstractPage {




    @FindBy(id="passengers")
    private WebElement passengersDropdown;

    @FindBy(css="#search-flights")
    private WebElement searchFlightBtn;

    public FlightSearchPage(WebDriver driver)
    {
        super(driver);
    }
    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.passengersDropdown));
        return this.passengersDropdown.isDisplayed();
    }

    public void selectPassengers(int numberOfPassengers)
    {
       // passengersDropdown.click(); -- not req as per course
        Select pas=new Select(this.passengersDropdown);
        pas.selectByIndex(numberOfPassengers);

    }

    public void searchFlight()
    {

        this.driver.manage().window().fullscreen();

        this.searchFlightBtn.click();
    }
}
