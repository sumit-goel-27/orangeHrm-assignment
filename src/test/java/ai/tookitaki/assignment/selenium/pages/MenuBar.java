package ai.tookitaki.assignment.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Iterator;
import java.util.List;

public class MenuBar {

    WebDriver driver;
    Actions actions;

    @FindBy(xpath="//div[@class='menu']/ul/li")
    List<WebElement> firstLevelMenuList;

    // This second level menu list will be initialized based on the selection in the first level menu
    List<WebElement> secondLevelMenuList;

    // This third level menu list will be initialized based on the selection in the second level menu
    List<WebElement> thirdLevelMenuList;

    public MenuBar(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public void showSecondLevelMenuOptions(String firstLevelOption) {
        Iterator<WebElement> iter = firstLevelMenuList.iterator();
        while(iter.hasNext()) {
            WebElement currentOption = iter.next();
            if(currentOption.findElement(By.tagName("a")).getText().equalsIgnoreCase(firstLevelOption)) {
                secondLevelMenuList = currentOption.findElements(By.xpath("//ul/li"));
                actions.moveToElement(currentOption).perform();
                break;
            }
        }
    }

    public void selectOptionInSecondLevelList(String secondLevelOption) {
        Iterator<WebElement> iter = secondLevelMenuList.iterator();
        while(iter.hasNext()) {
            WebElement currentOption = iter.next();
            if(currentOption.getText().equalsIgnoreCase(secondLevelOption)) {
                currentOption.click();
                break;
            }
        }
    }

    public void showThirdLevelMenuOptions(String secondLevelOption) {
        Iterator<WebElement> iter = secondLevelMenuList.iterator();
        while(iter.hasNext()) {
            WebElement currentOption = iter.next();
            if(currentOption.getText().equalsIgnoreCase(secondLevelOption)) {
                thirdLevelMenuList = currentOption.findElements(By.xpath("//ul/li"));
                actions.moveToElement(currentOption).perform();
                break;
            }
        }
    }

    public boolean isOptionPresentInThirdLevelList(String thirdLevelOption) {
        Iterator<WebElement> iter = thirdLevelMenuList.iterator();
        while(iter.hasNext()) {
            WebElement currentOption = iter.next();
            if(currentOption.getText().equalsIgnoreCase(thirdLevelOption)) {
                return true;
            }
        }
        return false;
    }
}