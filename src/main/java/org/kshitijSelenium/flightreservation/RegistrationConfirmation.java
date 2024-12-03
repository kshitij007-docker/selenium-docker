package org.kshitijSelenium.flightreservation;

import org.kshitijSelenium.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationConfirmation extends AbstractPage {


   // private WebDriver driver;

    @FindBy(css="#go-to-flights-search")
    private WebElement flightSearchBtn;

    public RegistrationConfirmation(WebDriver driver)
    {
        super(driver); //here it will go to abstract page class, and once it reaches there automatically it will initialised
       /* this.driver=driver;
        PageFactory.initElements(driver,this);*/

    }

    @Override
    public boolean isAt() { //it means before you interact any web elements of this page please check if this page is completely
        //loaded or not

        this.wait.until(ExpectedConditions.visibilityOf(this.flightSearchBtn));
        return this.flightSearchBtn.isDisplayed();
    }

    public void searchBtn()
    {
        flightSearchBtn.click();
    }

}
