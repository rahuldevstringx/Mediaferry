package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import BaseClasses.Driver;
import pages.CreateNewProjectPage;
import pages.GlobalSearchPage;
import steps.CreateNewProjectSteps;
import steps.GlobalSearchSteps;
import steps.HomeSteps;
import steps.LoginSteps;
import steps.ProofingFromActivityFeedSteps;
import steps.UploadDownloadAssetsSteps;
import utilities.UtilityMethods;

public class ProofingFromActivityFeedTest {
	Driver driverObj = new Driver();
	WebDriver driver = null;
	SoftAssert softAssert = new SoftAssert();
	LoginSteps loginSteps = new LoginSteps();
	String projectName = null;
	String asset = null;
	UtilityMethods utilityMethods = new UtilityMethods();
	int projectCountBefore;
	HomeSteps homeSteps = new HomeSteps();
	CreateNewProjectSteps createNewProjectSteps = new CreateNewProjectSteps();
	GlobalSearchSteps globalSearchSteps = new GlobalSearchSteps();
	GlobalSearchPage globalSearchPage = new GlobalSearchPage();
	CreateNewProjectPage createNewProjectPage = new CreateNewProjectPage();
	int projectCountAfter; 
	UploadDownloadAssetsSteps uploadDownloadAssetsSteps = new UploadDownloadAssetsSteps();
	ProofingFromActivityFeedSteps proofingFromActivityFeedSteps = new ProofingFromActivityFeedSteps();
	
	@BeforeTest 
	public void start() throws IOException {
		driver = driverObj.createDriver();
		driver.get(driverObj.getUrl());
	}
	
	@Test(priority=1)
	public void ApproveActionFunctionality() throws Exception {
		loginSteps.login(driver, softAssert, driverObj.getUsername(), driverObj.getPassword());
		projectName = utilityMethods.createUniqueProjectName(driverObj.getProjectName());
		projectCountBefore = homeSteps.projectCountBefore(driver);
		homeSteps.creatingNewProject(driver);
		createNewProjectSteps.fillingDetails(driver, projectName, driverObj.getCampaign() ,driverObj.getBrandName(), driverObj.getCreativeLevel(), driverObj.getCreationFilePath(), driverObj.getPriority(), driverObj.getProjectOwner(), driverObj.getInstructions(), driverObj.getTeam(), driverObj.getWidth(), driverObj.getHeight());
		globalSearchSteps.assetUpload(driver, driverObj.getGlobalFilePath());
		createNewProjectSteps.submittingJob(driver,softAssert, projectName);
		projectCountAfter = homeSteps.projectCountAfter(driver);
		globalSearchPage.searchByProjectAndClick(driver, softAssert, projectName);
		uploadDownloadAssetsSteps.uploadFileOnProof(driver, driverObj.getFilePath(), softAssert);	
		proofingFromActivityFeedSteps.proofingFromActivityFeed(driver, softAssert, projectName,driverObj.getSampleFilePath());
		
	}
	@AfterTest
    public void tearDown(){
        driver.quit();
        driver=null;
    }
}
