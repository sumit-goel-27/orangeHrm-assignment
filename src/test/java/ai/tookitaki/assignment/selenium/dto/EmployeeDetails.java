package ai.tookitaki.assignment.selenium.dto;

import org.apache.commons.lang3.RandomStringUtils;

public class EmployeeDetails {

    private String street1;
    private String street2;
    private String city;
    private String state;
    private String zipcode;
    private String country;

    private String homeTelephone;
    private String mobile;
    private String workTelephone;

    private String workEmail;
    private String otherEmail;

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHomeTelephone() {
        return homeTelephone;
    }

    public void setHomeTelephone(String homeTelephone) {
        this.homeTelephone = homeTelephone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWorkTelephone() {
        return workTelephone;
    }

    public void setWorkTelephone(String workTelephone) {
        this.workTelephone = workTelephone;
    }

    public String getWorkEmail() {
        return workEmail;
    }

    public void setWorkEmail(String workEmail) {
        this.workEmail = workEmail;
    }

    public String getOtherEmail() {
        return otherEmail;
    }

    public void setOtherEmail(String otherEmail) {
        this.otherEmail = otherEmail;
    }

    public static EmployeeDetails generateRandomEmpDetails() {
        EmployeeDetails employeeDetails = new EmployeeDetails();

        employeeDetails.setStreet1(RandomStringUtils.randomAlphanumeric(5));
        employeeDetails.setStreet2(RandomStringUtils.randomAlphanumeric(5));
        employeeDetails.setCity(RandomStringUtils.randomAlphanumeric(5));
        employeeDetails.setState(RandomStringUtils.randomAlphanumeric(5));
        employeeDetails.setZipcode(RandomStringUtils.randomNumeric(5));
        employeeDetails.setCountry("Australia");

        employeeDetails.setMobile(RandomStringUtils.randomNumeric(10));
        employeeDetails.setWorkEmail(RandomStringUtils.randomAlphabetic(7)+"@gmail.com");

        return employeeDetails;
    }
}
