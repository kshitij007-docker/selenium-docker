package org.kshitijSelenium.flightreservation;

import org.kshitijSelenium.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage extends AbstractPage {

   // private WebDriver driver;  //We will need this webdriver instance later therefore we defined it here

    @FindBy(id="firstName")
    private WebElement firstName;

    @FindBy(id="lastName")
    private WebElement lastName;

    @FindBy(id="email")
    private WebElement email;

    @FindBy(id="password")
    private WebElement password;

    @FindBy(name="street")
    private WebElement streetInput;

    @FindBy(name = "zip")
    private WebElement zip;

    @FindBy(id="register-btn")
    private WebElement registerBtn;

   /* public RegistrationPage(WebDriver driver) //Since all are private Webelement and when we create instance of this page ,all webelemets will be return null value, therefore this
            //constructor is created which are going to accept the webdriver
    {
        this.driver=driver;
        PageFactory.initElements(driver,this); // this class will help to initialise all the webelements mentioned above
        // it will use the driver instance to use the webelements by creating proxy elements of all the
        // webelemts and then at run time it will be identifying that element and we should be able to
        //interact
    }*/

    public RegistrationPage(WebDriver driver)
    {
       super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.firstName));
        return this.firstName.isDisplayed();
    }

    public void goTo(String url)
    {
        this.driver.get(url);
    }

    public void enterUserDetails(String firstName,String lastName)
    {
        this.firstName.sendKeys(firstName);
        this.lastName.sendKeys(lastName);
    }

    public void enterUserCredentials(String email,String password)
    {
        this.email.sendKeys(email);
        this.password.sendKeys(password);
    }

    public void enterAddress(String street,String zip)
    {
        this.streetInput.sendKeys(street);
        this.zip.sendKeys(zip);
    }

    public void register()
    {
        this.registerBtn.click();
    }


}
