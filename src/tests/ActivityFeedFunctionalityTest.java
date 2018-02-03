package tests;

import BaseClasses.BasePage;
import BaseClasses.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import steps.*;
import utilities.UtilityMethods;

import java.io.IOException;

public class ActivityFeedFunctionalityTest extends BasePage{

    Driver driverObj = new Driver();
    WebDriver driver = null;
    LoginSteps loginSteps = new LoginSteps();
    SoftAssert softAssert = new SoftAssert();
    UtilityMethods utilityMethods = new UtilityMethods();
    HomeSteps homeSteps = new HomeSteps();
    ActiveProjectsSteps activeProjectsSteps = new ActiveProjectsSteps();
    ActivityFeedSteps activityFeedSteps = new ActivityFeedSteps();

    String projectName = null;

    @BeforeTest
    public void start() throws IOException {
        driver = driverObj.createDriver();
        driver.get(driverObj.getUrl());
        loginSteps.login(driver, softAssert, driverObj.getUsername(), driverObj.getPassword());
        projectName = "My Test Project  412_2017-10-12";
    }

    @Test(priority = 1)
    public void activityFeedFunctionality() throws InterruptedException {
        homeSteps.waitingForHeaderLnksSpinner(driver);
        homeSteps.goToActivityFeed(driver);
        homeSteps.waitingForHeaderLnksSpinner(driver);
        activityFeedSteps.selectProject(driver, softAssert, projectName);
        //scrollTop(driver);
        activityFeedSteps.doQuerySteps(driver, projectName, "Test Query");
        activityFeedSteps.verifyStatusActivityFeed(driver, softAssert, "Query to Traffic");
        activityFeedSteps.verifyProfileNameWhichQuerying(driver, softAssert, driverObj.getProfileName());
        activityFeedSteps.doRespondSteps(driver, "Test Response");
        activityFeedSteps.verifyStatusActivityFeed(driver, softAssert, "Responded");
        activityFeedSteps.verifyProfileNameWhichResponding(driver, softAssert, driverObj.getProfileName());
        activityFeedSteps.commentOnProjectSteps(driver, "Test Comment");
        activityFeedSteps.verifyActivityComment(driver, softAssert, "Ajmal Khan", "Test Comment");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}
