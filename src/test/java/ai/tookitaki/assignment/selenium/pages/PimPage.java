package ai.tookitaki.assignment.selenium.pages;

import ai.tookitaki.assignment.selenium.dto.EmployeeDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

public class PimPage {

    WebDriver driver;

    @FindBy(id="firstName")
    WebElement addEmployee_firstNameInput;

    @FindBy(id="lastName")
    WebElement addEmployee_lastNameInput;

    @FindBy(id="photofile")
    WebElement addEmployee_uploadPhotoInput;

    @FindBy(id="btnSave")
    WebElement saveButton;

    @FindBy(id="empsearch_employee_name_empName")
    WebElement employeeList_searchByNameTextbox;

    @FindBy(id="searchBtn")
    WebElement employeeList_searchButton;

    @FindBy(id="resultTable")
    WebElement searchResultTable;

    @FindBy(xpath = "//div[@id='employee-details']/div[@id='sidebar']/ul/li[2]")
    WebElement employeeDetails_contactDetailsLabel;

    @FindBy(id="contact_street1")
    WebElement editEmpContactDetails_street1Textbox;

    @FindBy(id="contact_street2")
    WebElement editEmpContactDetails_street2Textbox;

    @FindBy(id="contact_city")
    WebElement editEmpContactDetails_cityTextbox;

    @FindBy(id="contact_province")
    WebElement editEmpContactDetails_stateTextbox;

    @FindBy(id="contact_emp_zipcode")
    WebElement editEmpContactDetails_zipcodeTextbox;

    @FindBy(id="contact_country")
    WebElement editEmpContactDetails_countryDropdown;

    @FindBy(id="contact_emp_mobile")
    WebElement editEmpContactDetails_mobileTextbox;

    @FindBy(id="contact_emp_work_email")
    WebElement editEmpContactDetails_emailTextbox;

    public PimPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addEmployee(String firstName, String lastName) {
        addEmployee(firstName, lastName, null);
    }

    public void addEmployee(String firstName, String lastName, File photoFile) {
        addEmployee_firstNameInput.sendKeys(firstName);
        addEmployee_lastNameInput.sendKeys(lastName);
        if(photoFile!=null) {
            addEmployee_uploadPhotoInput.sendKeys(photoFile.getAbsolutePath());
        }
        saveButton.click();
    }

    private void searchEmployeeListByEmpName(String name) {
        employeeList_searchByNameTextbox.sendKeys(name);
        employeeList_searchByNameTextbox.sendKeys(Keys.ENTER);
        employeeList_searchButton.click();
    }

    public boolean isEmployeePresentInEmployeeList(String empName) {
        searchEmployeeListByEmpName(empName);
        return searchResultTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr")).size()>0;
    }

    public void selectFirstEmployeeInList() {
        searchResultTable.findElement(By.tagName("tbody")).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(1).findElement(By.tagName("a")).click();
    }

    public void showContactDetails() {
        employeeDetails_contactDetailsLabel.click();
    }

    public void editContactDetails(EmployeeDetails employeeDetails) {
        saveButton.click();

        clearAndSendKeys(editEmpContactDetails_street1Textbox, employeeDetails.getStreet1());
        clearAndSendKeys(editEmpContactDetails_street2Textbox, employeeDetails.getStreet2());
        clearAndSendKeys(editEmpContactDetails_cityTextbox, employeeDetails.getCity());
        clearAndSendKeys(editEmpContactDetails_stateTextbox, employeeDetails.getState());
        clearAndSendKeys(editEmpContactDetails_zipcodeTextbox, employeeDetails.getZipcode());
        Select countryDropdown = new Select(editEmpContactDetails_countryDropdown);
        countryDropdown.selectByVisibleText(employeeDetails.getCountry());

        clearAndSendKeys(editEmpContactDetails_mobileTextbox, employeeDetails.getMobile());
        clearAndSendKeys(editEmpContactDetails_emailTextbox, employeeDetails.getWorkEmail());

        saveButton.click();
    }

    private void clearAndSendKeys(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }

    public String getContactStreet1() {
        return editEmpContactDetails_street1Textbox.getAttribute("value");
    }

    public String getContactStreet2() {
        return editEmpContactDetails_street2Textbox.getAttribute("value");
    }

    public String getContactCity() {
        return editEmpContactDetails_cityTextbox.getAttribute("value");
    }

    public String getContactState() {
        return editEmpContactDetails_stateTextbox.getAttribute("value");
    }

    public String getContactZipcode() {
        return editEmpContactDetails_zipcodeTextbox.getAttribute("value");
    }

    public String getContactCountry() {
        Select countryDropdown = new Select(editEmpContactDetails_countryDropdown);
        return countryDropdown.getFirstSelectedOption().getText();
    }

    public String getContactMobile() {
        return editEmpContactDetails_mobileTextbox.getAttribute("value");
    }

    public String getContactEmail() {
        return editEmpContactDetails_emailTextbox.getAttribute("value");
    }
}