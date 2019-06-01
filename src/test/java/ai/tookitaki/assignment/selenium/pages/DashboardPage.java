package ai.tookitaki.assignment.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {

    WebDriver driver;
    Actions actions;
    WebDriverWait wait;

    @FindBy(id="welcome")
    WebElement userWelcomeText;

    @FindBy(id="div_graph_display_emp_distribution")
    WebElement employeeDistributionGraph;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, 10);
    }

    public String getUserWelcomeMessage() {
        return userWelcomeText.getText();
    }

    public void mouseOverEmpDistributionGraph() {
        wait.until(ExpectedConditions.visibilityOf(employeeDistributionGraph));
        actions.moveToElement(employeeDistributionGraph.findElement(By.tagName("span"))).perform();
        actions.moveByOffset(40,30).perform();
    }
}