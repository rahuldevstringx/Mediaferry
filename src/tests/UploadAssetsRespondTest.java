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

public class UploadAssetsRespondTest {

    Driver driverObj = new Driver();
    WebDriver driver = null;
    LoginSteps loginSteps = new LoginSteps();
    HomeSteps homeSteps = new HomeSteps();
    ActiveProjectsSteps activeProjectsSteps = new ActiveProjectsSteps();
    UploadDownloadAssetsSteps uploadDownloadAssetsSteps = new UploadDownloadAssetsSteps();
    SoftAssert softAssert = new SoftAssert();
    CreateNewProjectSteps createNewProjectSteps = new CreateNewProjectSteps();
    UtilityMethods utilityMethods = new UtilityMethods();
    ActivityFeedSteps activityFeedSteps = new ActivityFeedSteps();

    String projectName = null;
    int projectCountBefore, projectCountAfter;

    @BeforeTest
    public void start() throws IOException {
        driver=driverObj.createDriver();
        driver.get(driverObj.getUrl());
    }

    @Test(priority = 1)
    public  void uploadingAssetsByRespondWindow() throws Exception {
        loginSteps.login(driver, softAssert, driverObj.getUsername(), driverObj.getPassword());
        projectName = utilityMethods.createUniqueProjectName(driverObj.getProjectName());
        projectCountBefore = homeSteps.projectCountBefore(driver);
        homeSteps.creatingNewProject(driver);
        createNewProjectSteps.fillingDetails(driver, projectName, driverObj.getCampaign(), driverObj.getBrandName(), driverObj.getCreativeLevel(), driverObj.getFilePath(), driverObj.getPriority(), driverObj.getProjectOwner(), driverObj.getInstructions(), driverObj.getTeam(), driverObj.getWidth(), driverObj.getHeight());
        createNewProjectSteps.submittingJob(driver,softAssert,projectName);
        projectCountAfter = homeSteps.projectCountAfter(driver);
        homeSteps.verifyProjectCount(driver, softAssert, projectCountBefore+1, projectCountAfter);
        activeProjectsSteps.selectProjectForQuerying(driver, softAssert, projectName);
        activeProjectsSteps.doQuery(driver, "Test Query");
        activeProjectsSteps.respondQuery(driver, "This is response to Test Query");
        uploadDownloadAssetsSteps.uploadingAssetQueryWindow(driver, driverObj.getFilePath(), softAssert);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}
