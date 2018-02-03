package tests;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import BaseClasses.Driver;
import steps.LoginSteps;
import steps.SignUpSteps;
import utilities.MailReadWhileActivating;
import utilities.UtilityMethods;

public class SignUpTest {
    Driver driverObj = new Driver();
    WebDriver driver = null;
    SignUpSteps signUpSteps = new SignUpSteps();
    UtilityMethods utilityMethods = new UtilityMethods();
    LoginSteps loginSteps = new LoginSteps();
    SoftAssert softAssert = new SoftAssert();
    MailReadWhileActivating mailReadWhileActivating = new MailReadWhileActivating();
    private String userName = null;
    private String activationUrl = null;

    @BeforeTest
    public void start() throws IOException {

        driver = driverObj.createDriver();
        driver.get(driverObj.getUrl());
    }

    @Test(priority = 1)
    public void verifyMandatoryFieldsTest() {

        signUpSteps.verifyAllMandatoryFields(driver, softAssert);
    }

    @Test(priority = 2)
    public void signupWithValidDetails() throws InterruptedException {

    	userName = "qa.gaurav239+" + utilityMethods.randomStringGenrator(6) + "@gmail.com";
        signUpSteps.fillSignupDetails(driver, softAssert, "Test Full Name", "Test Company", "IN", userName,
                driverObj.getSignupPassword());
        Thread.sleep(10000);
        activationUrl = mailReadWhileActivating.FetchMails("imap.gmail.com", "qa.gaurav239@gmail.com", "result1029",
                "Welcome to MediaFerry!");
        driver.get(activationUrl);
        System.out.println("**********************************************");
        loginSteps.login(driver, softAssert, userName, driverObj.getSignupPassword());
        loginSteps.logout(driver, softAssert);
    }

    @Test(priority = 3)
    public void signupWithExistingAccount() {

        signUpSteps.fillSignupDeatilsWithRegisterAccount(driver, softAssert, "Test Full Name", "Test Company", "IN",
                userName, driverObj.getSignupPassword());
        softAssert.assertAll();
    }

    @AfterTest
    public void tearDown() {

        driver.quit();
        driver = null;
    }
}
