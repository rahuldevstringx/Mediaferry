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
    CreateNewProjectSteps createNewProjectSteps = new CreateNewProjectSteps();

    String projectName = null;
    int projectCountBefore;
    int projectCountAfter;

    @BeforeTest
    public void start() throws IOException {
        driver = driverObj.createDriver();
        driver.get(driverObj.getUrl());
    }

    @Test(priority = 1)
    public void loginWithValidCredentials() throws Exception {
        loginSteps.login(driver, softAssert, driverObj.getUsername(), driverObj.getPassword());
        projectName = utilityMethods.createUniqueProjectName(driverObj.getProjectName());
        projectCountBefore = homeSteps.projectCountBefore(driver);
        homeSteps.creatingNewProject(driver);
        createNewProjectSteps.fillingDetails(driver, projectName, driverObj.getCampaign(), driverObj.getBrandName(), driverObj.getCreativeLevel(), driverObj.getFilePath(), driverObj.getPriority(), driverObj.getProjectOwner(), driverObj.getInstructions(), driverObj.getTeam(), driverObj.getWidth(), driverObj.getHeight());
        createNewProjectSteps.submittingJob(driver,softAssert,projectName);
        projectCountAfter = homeSteps.projectCountAfter(driver);
        homeSteps.verifyProjectCount(driver, softAssert, projectCountBefore+1, projectCountAfter);
    }

    @Test(priority = 2)
    public void activityFeedFunctionality() throws InterruptedException {
        homeSteps.navigateToActivityFeed(driver);
        homeSteps.waitingForHeaderLnksSpinner(driver);
        activityFeedSteps.selectProjectByApplyingAllFilters(driver, softAssert, projectName, driverObj.getCampaign(), "Ready for Production", driverObj.getPriority(), driverObj.getWidth(), driverObj.getHeight());
        activityFeedSteps.queryingSteps(driver, projectName, "Test Query");
        activityFeedSteps.verifyStatusActivityFeed(driver, softAssert, "Query to Traffic");
        activityFeedSteps.respondingSteps(driver, "Test Response");
        activityFeedSteps.verifyStatusActivityFeed(driver, softAssert, "Responded");
        activityFeedSteps.commentOnProjectSteps(driver, "Test Comment");
        activityFeedSteps.verifyActivityComment(driver, softAssert, "Ajmal Khan", "Test Comment");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}
