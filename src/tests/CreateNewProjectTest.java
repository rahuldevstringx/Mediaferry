package tests;

import BaseClasses.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import steps.CreateNewProjectSteps;
import steps.HomeSteps;
import steps.LoginSteps;
import utilities.UtilityMethods;

import java.io.IOException;

public class CreateNewProjectTest {

    Driver driverObj = new Driver();
    WebDriver driver = null;
    SoftAssert softAssert = new SoftAssert();
    LoginSteps loginSteps = new LoginSteps();
    HomeSteps homeSteps = new HomeSteps();
    UtilityMethods utilityMethods = new UtilityMethods();
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

    @AfterTest
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}
