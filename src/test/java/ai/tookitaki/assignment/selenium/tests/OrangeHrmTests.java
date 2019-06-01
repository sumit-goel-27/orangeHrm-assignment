package ai.tookitaki.assignment.selenium.tests;

import ai.tookitaki.assignment.selenium.dto.EmployeeDetails;
import ai.tookitaki.assignment.selenium.pages.DashboardPage;
import ai.tookitaki.assignment.selenium.pages.LoginPage;
import ai.tookitaki.assignment.selenium.pages.MenuBar;
import ai.tookitaki.assignment.selenium.pages.PimPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.*;
import java.net.URL;
import java.util.Properties;

public class OrangeHrmTests {

    WebDriver driver;
    MenuBar menuBar;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    PimPage pimPage;

    File chromeDriver;
    Properties props;

    @BeforeClass
    public void propertiesSetup() throws IOException {
        URL driverFileUrl = getClass().getResource("/chromedriver.exe");
        chromeDriver = new File(driverFileUrl.getFile());
        System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());

        URL propsFileUrl = getClass().getResource("/hrm.properties");
        File propsFile = new File(propsFileUrl.getFile());
        InputStream input = new FileInputStream(propsFile);
        props = new Properties();
        props.load(input);
    }

    @BeforeMethod
    public void driverSetup() {
        driver = new ChromeDriver();
        driver.get(props.getProperty("baseUrl"));
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        dashboardPage = loginPage.login(props.getProperty("username"), props.getProperty("password"));
    }

    /*
    Check for successful login into Orange HRM
     */
    @Test
    public void TS_PIM_01() {
        Assert.assertEquals("Welcome "+props.getProperty("username"), dashboardPage.getUserWelcomeMessage());
    }

    /*
    Verify 'General Information' option is present under Admin->Organization
     */
    @Test
    public void TS_PIM_02() {
        menuBar = new MenuBar(driver);
        menuBar.showSecondLevelMenuOptions("Admin");
        menuBar.showThirdLevelMenuOptions("Organization");
        Assert.assertTrue(menuBar.isOptionPresentInThirdLevelList("General Information"));
    }

    /*
    Edit & save contact details of an existing employee
     */
    @Test
    public void TS_PIM_03() {
        menuBar = new MenuBar(driver);
        menuBar.showSecondLevelMenuOptions("PIM");
        menuBar.selectOptionInSecondLevelList("Employee List");
        pimPage = new PimPage(driver);
        pimPage.selectFirstEmployeeInList();
        pimPage.showContactDetails();
        EmployeeDetails empDetails = EmployeeDetails.generateRandomEmpDetails();
        pimPage.editContactDetails(empDetails);

        //Verify that changes are saved
        Assert.assertEquals(pimPage.getContactStreet1(), empDetails.getStreet1());
        Assert.assertEquals(pimPage.getContactStreet2(), empDetails.getStreet2());
        Assert.assertEquals(pimPage.getContactCity(), empDetails.getCity());
        Assert.assertEquals(pimPage.getContactState(), empDetails.getState());
        Assert.assertEquals(pimPage.getContactZipcode(), empDetails.getZipcode());
        Assert.assertEquals(pimPage.getContactCountry(), empDetails.getCountry());

        Assert.assertEquals(pimPage.getContactMobile(), empDetails.getMobile());
        Assert.assertEquals(pimPage.getContactEmail(), empDetails.getWorkEmail());
    }

    /*
    Add a new employee and verify it in the employee list
     */
    @Test
    public void TS_PIM_04() {
        menuBar = new MenuBar(driver);
        menuBar.showSecondLevelMenuOptions("PIM");
        menuBar.selectOptionInSecondLevelList("Add Employee");
        pimPage = new PimPage(driver);
        String firstName = RandomStringUtils.randomAlphabetic(3);
        String lastName = RandomStringUtils.randomAlphabetic(3);
        pimPage.addEmployee(firstName, lastName);
        menuBar = new MenuBar(driver);
        menuBar.showSecondLevelMenuOptions("PIM");
        menuBar.selectOptionInSecondLevelList("Employee List");

        // Verify that added employee is present in the employee list
        pimPage = new PimPage(driver);
        pimPage.isEmployeePresentInEmployeeList(firstName+" "+lastName);
    }

    /*
    Add a user and upload a picture format
     */
    @Test
    public void TS_PIM_05() {
        URL fileUrl = getClass().getResource("/printer_icon.png");
        File pictureFile = new File(fileUrl.getFile());
        menuBar = new MenuBar(driver);
        menuBar.showSecondLevelMenuOptions("PIM");
        menuBar.selectOptionInSecondLevelList("Add Employee");
        PimPage pimPage = new PimPage(driver);
        String firstName = RandomStringUtils.randomAlphabetic(3);
        String lastName = RandomStringUtils.randomAlphabetic(3);
        pimPage.addEmployee(firstName, lastName, pictureFile);
    }

    /*
    Mouseover on employee distribution graph
     */
    @Test
    public void TS_PIM_06() {
        dashboardPage.mouseOverEmpDistributionGraph();
    }

    @AfterMethod
    public void cleanup() {
        driver.quit();
    }
}