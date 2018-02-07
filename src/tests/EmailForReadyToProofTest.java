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

public class EmailForReadyToProofTest {
    Driver driverObj = new Driver();
    WebDriver driver = null;
    SoftAssert softAssert = new SoftAssert();
    LoginSteps loginSteps = new LoginSteps();
    HomeSteps homeSteps = new HomeSteps();
    UtilityMethods utilityMethods = new UtilityMethods();
    CreateNewProjectSteps createNewProjectSteps = new CreateNewProjectSteps();
    ActiveProjectsSteps activeProjectsSteps = new ActiveProjectsSteps();

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
    public void sendEmailForReadyForProof() throws InterruptedException {
        String saveSuccessMessage = " Your changes have been saved successfully.";
        String uploadSuccessMessage = "The selected files has been uploaded successfully.";
        activeProjectsSteps.selectProjectForEdit(driver, softAssert, projectName);
        createNewProjectSteps.editJobForProofing(driver, softAssert, driverObj.getSampleFilePath(), saveSuccessMessage, uploadSuccessMessage);
        createNewProjectSteps.sendEmailForReadyForProofSteps(driver);
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
        driver=null;
    }
}
