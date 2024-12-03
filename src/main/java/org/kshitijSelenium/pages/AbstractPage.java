package org.kshitijSelenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractPage {

    protected final WebDriver driver;
    protected  final WebDriverWait wait;


    public AbstractPage(WebDriver driver)
    {
        this.driver=driver;

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver,this);
    }

    public abstract boolean isAt() ;

//This is abstract so that i can force all the classes to use the boolean method to ensure that page is loaded before interacting with web elements

}
