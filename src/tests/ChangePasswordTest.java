package tests;

import BaseClasses.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import steps.HomeSteps;
import steps.LoginSteps;
import utilities.UtilityMethods;

import java.io.IOException;

public class ChangePasswordTest {

    Driver driverObj = new Driver();
    WebDriver driver = null;
    LoginSteps loginSteps = new LoginSteps();
    SoftAssert softAssert = new SoftAssert();
    UtilityMethods utilityMethods = new UtilityMethods();
    HomeSteps homeSteps = new HomeSteps();

    @BeforeTest
    public void start() throws IOException {
        driver = driverObj.createDriver();
        driver.get(driverObj.getUrl());
    }

    @Test(priority = 1)
    public void loginWithValidCredentials() throws Exception {
        loginSteps.login(driver, softAssert, driverObj.getUsername(), driverObj.getPassword());
        homeSteps.waitingForHeaderLnksSpinner(driver);
    }

    @Test(priority = 2)
    public void verifyValidationMsg(){
        homeSteps.goToChangePasswordWindow(driver);
        homeSteps.fillMandatoryFields(driver, "", "");
        homeSteps.verifyValidationMsgs(driver,softAssert,  "This field is required.");

    }

    @Test(priority = 3)
    public void changePassword() throws InterruptedException {
        homeSteps.goToChangePasswordWindow(driver);
        homeSteps.fillMandatoryFields(driver, driverObj.getPassword(), driverObj.getChangePassword());
        homeSteps.saveChanges(driver, softAssert, "Password has been changed successfully.");
        loginSteps.logout(driver, softAssert);
    }

    @Test(priority = 4)
    public void verifyChangePassword(){
        loginSteps.login(driver, softAssert, driverObj.getUsername(), driverObj.getChangePassword());
        homeSteps.waitingForHeaderLnksSpinner(driver);
        homeSteps.goToChangePasswordWindow(driver);
        homeSteps.fillMandatoryFields(driver, driverObj.getChangePassword(), driverObj.getPassword());
        homeSteps.saveChanges(driver, softAssert, "Password has been changed successfully.");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
        driver = null;
    }

}
