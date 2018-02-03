package tests;

import BaseClasses.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import steps.ActiveProjectsSteps;
import steps.CreateNewProjectSteps;
import steps.HomeSteps;
import steps.LoginSteps;
import utilities.UtilityMethods;

import java.io.IOException;

public class RespondFunctionalityTest {

    Driver driverObj = new Driver();
    WebDriver driver = null;
    LoginSteps loginSteps = new LoginSteps();
    SoftAssert softAssert = new SoftAssert();
    UtilityMethods utilityMethods = new UtilityMethods();
    HomeSteps homeSteps = new HomeSteps();
    CreateNewProjectSteps createNewProjectSteps = new CreateNewProjectSteps();
    ActiveProjectsSteps activeProjectsSteps = new ActiveProjectsSteps();

    String projectName = null;
    int projectCountBefore, projectCountAfter;

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

    @Test(priority = 3)
    public void verifyValidationMsgRespond() throws InterruptedException {
        activeProjectsSteps.openAndPressRespondSteps(driver);
        activeProjectsSteps.verifyValidationMsgOnRespondSteps(driver, softAssert, "This field is required");
    }

    @Test(priority = 4)
    public void statusVerifyOnNotRespond() throws InterruptedException {
        activeProjectsSteps.statusVerificationOnNotRespond(driver, softAssert, projectName);
    }

     @Test(priority = 5)
    public void respondToQuery() throws InterruptedException {
        activeProjectsSteps.openRespondWindowSteps(driver);
        activeProjectsSteps.respondQuerySteps(driver, "Test Response for Test Query");
    }

    @Test(priority = 6)
    public void verifyQueryLink() {
        activeProjectsSteps.verifyingQueryLnkSteps(driver, softAssert, projectName, true, false);
    }

    @Test(priority = 7)
    public void verifyStatusLink() {
        activeProjectsSteps.verifyingStatusLnkSteps(driver, softAssert, projectName, true, false);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
        driver = null;
    }

}
