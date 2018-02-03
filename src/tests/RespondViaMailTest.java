package tests;

import BaseClasses.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import steps.*;
import utilities.UtilityMethods;

import java.io.IOException;

public class RespondViaMailTest {

    Driver driverObj = new Driver();
    WebDriver driver = null;
    LoginSteps loginSteps = new LoginSteps();
    SoftAssert softAssert = new SoftAssert();
    UtilityMethods utilityMethods = new UtilityMethods();
    HomeSteps homeSteps = new HomeSteps();
    CreateNewProjectSteps createNewProjectSteps = new CreateNewProjectSteps();
    ActiveProjectsSteps activeProjectsSteps = new ActiveProjectsSteps();
    ActivityFeedSteps activityFeedSteps  = new ActivityFeedSteps();

    String projectName = null;
    int projectCountBefore, projectCountAfter;
    String expectedSubject;

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
    public void sendQuery() throws InterruptedException {
        activeProjectsSteps.selectProject(driver, softAssert, projectName);
        activeProjectsSteps.sendQuerySteps(driver, softAssert, projectName, "This is query message text");
    }

   //@Test(priority = 3)
    public void respondingViaMailWhileLogin() throws InterruptedException {
        expectedSubject = "[MediaFerry] EKCS has a query " + projectName;
        activityFeedSteps.respondQueryViaEmailSteps(driver, expectedSubject, "Response to the Test Query");
        activityFeedSteps.verifyStatus(driver, softAssert);
    }

    //@Test(priority = 4)
    public void verifyQueryLink() throws InterruptedException {
        homeSteps.goToActiveProjectsWithoutWait(driver);
        homeSteps.waitingForConstantLnksSpinner(driver);
        activeProjectsSteps.verifyingQueryLnkSteps(driver, softAssert, projectName, true, false);
    }

    //@Test(priority = 5)
    public void verifyStatusLink() {
        activeProjectsSteps.verifyingStatusLnkSteps(driver, softAssert, projectName, true, false);
   
    }
    
    //@Test(priority = 6)
    public void respondingViaMailNotLogin() throws InterruptedException{
        loginSteps.logout(driver, softAssert);
        activityFeedSteps.respondQueryViaEmailWhenNotLogin(driver, expectedSubject);
        loginSteps.verifyValidation(driver, softAssert);
    }

    //@AfterTest
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}
