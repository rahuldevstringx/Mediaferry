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

public class QueryEmailTriggerTest {

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
        activeProjectsSteps.verifyMailCount(driver, softAssert, projectName, 1);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}
