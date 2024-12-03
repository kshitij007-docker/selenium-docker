package org.kshitijSelenium.flightreservation;

import org.kshitijSelenium.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlightConfirmationPage extends AbstractPage {

    private static final Logger log= LoggerFactory.getLogger(FlightConfirmationPage.class);

    @FindBy(css="#flights-confirmation-section .card-body .row:nth-child(3) .col:nth-child(2)")
    private WebElement finalPrice;

    @FindBy(css="#flights-confirmation-section .card-body .row:nth-child(1) .col:nth-child(2)")
    private WebElement confirmationId;

    public FlightConfirmationPage(WebDriver driver)
    {
        super(driver);
    }
    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(finalPrice));
        return finalPrice.isDisplayed();
    }

    public String getPrice()
    {
        String confirmation=confirmationId.getText();
        String finalPrice=this.finalPrice.getText();
        log.info("Flight confirmation# :{}",confirmation);
        log.info("Final Price# :{}",finalPrice);
        return this.finalPrice.getText();
    }
}
