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

public class UploadAssetsProofingTest {

    Driver driverObj = new Driver();
    WebDriver driver = null;
    LoginSteps loginSteps = new LoginSteps();
    HomeSteps homeSteps = new HomeSteps();
    ActiveProjectsSteps activeProjectsSteps = new ActiveProjectsSteps();
    UploadDownloadAssetsSteps uploadDownloadAssetsSteps = new UploadDownloadAssetsSteps();
    SoftAssert softAssert = new SoftAssert();
    CreateNewProjectSteps createNewProjectSteps = new CreateNewProjectSteps();
    UtilityMethods utilityMethods = new UtilityMethods();

    String projectName = null;
    int projectCountBefore, projectCountAfter;

    @BeforeTest
    public void start() throws IOException {
        driver=driverObj.createDriver();
        driver.get(driverObj.getUrl());
    }

    @Test(priority = 1)
    public  void uploadingAssetsByProofingWindow() throws Exception {
        loginSteps.login(driver, softAssert, driverObj.getUsername(), driverObj.getPassword());
        projectName = utilityMethods.createUniqueProjectName(driverObj.getProjectName());
        projectCountBefore = homeSteps.projectCountBefore(driver);
        homeSteps.creatingNewProject(driver);
        createNewProjectSteps.fillingDetails(driver, projectName, driverObj.getCampaign(), driverObj.getBrandName(), driverObj.getCreativeLevel(), driverObj.getCreationFilePath(), driverObj.getPriority(), driverObj.getProjectOwner(), driverObj.getInstructions(), driverObj.getTeam(), driverObj.getWidth(), driverObj.getHeight());
        createNewProjectSteps.submittingJob(driver,softAssert,projectName);
        projectCountAfter = homeSteps.projectCountAfter(driver);
        homeSteps.verifyProjectCount(driver, softAssert, projectCountBefore+1, projectCountAfter);
        activeProjectsSteps.selectProjectForEdit(driver, softAssert, projectName);
        uploadDownloadAssetsSteps.uploadAssetsOnProofPrerequisitesSteps(driver);
        uploadDownloadAssetsSteps.uploadFileOnProofingWindow(driver, driverObj.getFilePath(),driverObj.getSampleFilePath(), softAssert);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}
