package ai.tookitaki.assignment.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    @FindBy(id="txtUsername")
    WebElement userNameTextbox;

    @FindBy(id="txtPassword")
    WebElement passwordTextbox;

    @FindBy(id="btnLogin")
    WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public DashboardPage login(String username, String password) {
        userNameTextbox.sendKeys(username);
        passwordTextbox.sendKeys(password);
        loginButton.click();
        return new DashboardPage(driver);
    }
}