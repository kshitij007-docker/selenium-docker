package org.kshitijSelenium.flightreservation;

import org.kshitijSelenium.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SelectFilghtsPage extends AbstractPage {


    @FindBy(name="departure-flight")
    private List<WebElement> depFlightOpt;

    @FindBy(name="arrival-flight")
    private List<WebElement> arrivalFlightOpt;

    @FindBy(id="confirm-flights")
    private WebElement confirmFlightBtn;


    public SelectFilghtsPage(WebDriver driver)
    {
        super(driver);
    }
    @Override
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOf(this.confirmFlightBtn));
        return confirmFlightBtn.isDisplayed();
    }


    public void selectFlight()
    {
        int random= ThreadLocalRandom.current().nextInt(0,depFlightOpt.size());
        //here this class will generate any random number between 0 to length of list
        this.depFlightOpt.get(random).click();
        this.arrivalFlightOpt.get(random).click();
    }

    public void confirmFlight()
    {
        confirmFlightBtn.click();
    }
}
