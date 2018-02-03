package tests;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import BaseClasses.Driver;
import steps.*;
import utilities.UtilityMethods;

public class UploadingAssetsTest {
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
    public void start() throws IOException{
        driver=driverObj.createDriver();
        driver.get(driverObj.getUrl());
    }


    @Test(priority = 1)
    public void uploadAssetsActivityFeeds() throws Exception {
        loginSteps.login(driver, softAssert, driverObj.getUsername(), driverObj.getPassword());
        projectName = utilityMethods.createUniqueProjectName(driverObj.getProjectName());
        projectCountBefore = homeSteps.projectCountBefore(driver);
        homeSteps.creatingNewProject(driver);
        createNewProjectSteps.fillingDetailsWithoutUploadingAssets(driver, projectName, driverObj.getCampaign(), driverObj.getBrandName(), driverObj.getCreativeLevel(), driverObj.getPriority(), driverObj.getProjectOwner(), driverObj.getInstructions(), driverObj.getTeam(), driverObj.getWidth(), driverObj.getHeight());
        createNewProjectSteps.submittingJob(driver,softAssert,projectName);
        projectCountAfter = homeSteps.projectCountAfter(driver);
        homeSteps.verifyProjectCount(driver, softAssert, projectCountBefore+1, projectCountAfter);
        uploadDownloadAssetsSteps.uploadFileByFeedActivity(driver, driverObj.getFilePath(), projectName, softAssert);
        loginSteps.logout(driver, softAssert);
    }

    @Test(priority = 2)
    public void uploadAssetsEditActiveProject() throws Exception {
        loginSteps.login(driver, softAssert, driverObj.getUsername(), driverObj.getPassword());
        projectName = utilityMethods.createUniqueProjectName(driverObj.getProjectName());
        projectCountBefore = homeSteps.projectCountBefore(driver);
        homeSteps.creatingNewProject(driver);
        createNewProjectSteps.fillingDetails(driver, projectName, driverObj.getCampaign(), driverObj.getBrandName(), driverObj.getCreativeLevel(), driverObj.getFilePath(), driverObj.getPriority(), driverObj.getProjectOwner(), driverObj.getInstructions(), driverObj.getTeam(), driverObj.getWidth(), driverObj.getHeight());
        createNewProjectSteps.submittingJob(driver,softAssert, projectName);
        projectCountAfter = homeSteps.projectCountAfter(driver);
        homeSteps.verifyProjectCount(driver, softAssert, projectCountBefore+1, projectCountAfter);
        activeProjectsSteps.selectProjectForEdit(driver, softAssert, projectName);
        uploadDownloadAssetsSteps.uploadFileWhileEdit(driver, driverObj.getFilePath(), softAssert);
        loginSteps.logout(driver, softAssert);
    }

    @Test(priority = 3)
    public void uploadAssetsWhileCreatingProject() throws Exception {
        loginSteps.login(driver, softAssert, driverObj.getUsername(), driverObj.getPassword());
        projectName = utilityMethods.createUniqueProjectName(driverObj.getProjectName());
        projectCountBefore = homeSteps.projectCountBefore(driver);
        homeSteps.creatingNewProject(driver);
        createNewProjectSteps.fillingDetailsWithoutUploadingAssets(driver, projectName, driverObj.getCampaign(), driverObj.getBrandName(), driverObj.getCreativeLevel(), driverObj.getPriority(), driverObj.getProjectOwner(), driverObj.getInstructions(), driverObj.getTeam(), driverObj.getWidth(), driverObj.getHeight());
        createNewProjectSteps.uploadingAssetsWhileCreatingJob(driver, softAssert, driverObj.getFilePath());
        createNewProjectSteps.submittingJob(driver,softAssert, projectName);
        projectCountAfter = homeSteps.projectCountAfter(driver);
        homeSteps.verifyProjectCount(driver, softAssert, projectCountBefore+1, projectCountAfter);
        loginSteps.logout(driver, softAssert);
    }

    @Test(priority = 4)
    public void uploadAssetsByActiveFeedLnk() throws Exception {
        loginSteps.login(driver, softAssert, driverObj.getUsername(), driverObj.getPassword());
        projectName = utilityMethods.createUniqueProjectName(driverObj.getProjectName());
        projectCountBefore = homeSteps.projectCountBefore(driver);
        homeSteps.creatingNewProject(driver);
        createNewProjectSteps.fillingDetailsWithoutUploadingAssets(driver, projectName, driverObj.getCampaign(), driverObj.getBrandName(), driverObj.getCreativeLevel(), driverObj.getPriority(), driverObj.getProjectOwner(), driverObj.getInstructions(), driverObj.getTeam(), driverObj.getWidth(), driverObj.getHeight());
        createNewProjectSteps.submittingJob(driver,softAssert, projectName);
        projectCountAfter = homeSteps.projectCountAfter(driver);
        homeSteps.verifyProjectCount(driver, softAssert, projectCountBefore+1, projectCountAfter);
        homeSteps.goToActivityFeed(driver);
        activityFeedSteps.goToUploadAssetsWindow(driver);
        uploadDownloadAssetsSteps.uploadFileFromActivityFeedPage(driver, softAssert, driverObj.getFilePath());
        loginSteps.logout(driver, softAssert);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}
