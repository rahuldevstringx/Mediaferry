package tests;

import BaseClasses.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import steps.ActiveProjectsSteps;
import steps.CreateNewProjectSteps;
import steps.HomeSteps;
import steps.LoginSteps;
import utilities.MailReadVerifyingProof;
import utilities.UtilityMethods;

import java.io.IOException;

public class EmailProofActiveProjectsTest {
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
    public void emailProofing() throws InterruptedException {
        activeProjectsSteps.selectProjectForEdit(driver, softAssert, projectName);
        createNewProjectSteps.emailProofPrerequisitesSteps(driver, driverObj.getFilePath());
        activeProjectsSteps.selectProject(driver, softAssert, projectName);
        activeProjectsSteps.emailProofFromGrid(driver, driverObj.getSignUpEmail(), projectName);

    }

    @Test(priority = 3)
    public void verifyEmailProof(){
        String expectedSubject = "[MediaFerry]" + projectName + "Proof is ready for you to review";
        activeProjectsSteps.verifyEmailProofSteps(driver, softAssert, projectName, expectedSubject);
    }
}
