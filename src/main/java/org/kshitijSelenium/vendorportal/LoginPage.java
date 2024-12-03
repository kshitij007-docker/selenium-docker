package org.kshitijSelenium.vendorportal;

import org.kshitijSelenium.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractPage {


    @FindBy(id="username")
    private WebElement userNameIn;

    @FindBy(id="password")
    private WebElement password;

    @FindBy(css="#login")
    private WebElement loginBtn;

    public LoginPage(WebDriver driver)
    {
        super(driver);
    }
    @Override
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOf(loginBtn));
        return loginBtn.isDisplayed();
    }

    public void gotTo(String url)
    {
        driver.get(url);

    }

    public void login(String username,String password)
    {
        userNameIn.sendKeys(username);
        this.password.sendKeys(password);
        loginBtn.click();
    }
}
