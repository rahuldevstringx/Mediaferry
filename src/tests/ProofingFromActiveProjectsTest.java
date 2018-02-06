package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import BaseClasses.BasePage;
import BaseClasses.Driver;
import pages.ActiveProjectsPage;
import pages.GlobalSearchPage;
import pages.ProofingFromActivityFeedPage;
import steps.ActiveProjectsSteps;
import steps.CreateNewProjectSteps;
import steps.GlobalSearchSteps;
import steps.HomeSteps;
import steps.LoginSteps;
import steps.ProofingFromActiveProjectsSteps;
import steps.UploadDownloadAssetsSteps;
import utilities.UtilityMethods;

public class ProofingFromActiveProjectsTest extends BasePage {
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
	int projectCountAfter;
	GlobalSearchSteps globalSearchSteps = new GlobalSearchSteps();
	ActiveProjectsSteps activeProjectsSteps = new ActiveProjectsSteps();
	UploadDownloadAssetsSteps uploadDownloadAssetsSteps = new UploadDownloadAssetsSteps();
	GlobalSearchPage globalSearchPage = new GlobalSearchPage();
	ProofingFromActiveProjectsSteps proofingFromActiveSteps = new ProofingFromActiveProjectsSteps();
	ActiveProjectsPage activeProjectsPage = new ActiveProjectsPage();
	ProofingFromActivityFeedPage proofingFromActivityFeedPage = new ProofingFromActivityFeedPage();
 	
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
		proofingFromActiveSteps.proofingFromActiveProjects(driver, softAssert, projectName,driverObj.getSampleFilePath());

	}

	@AfterTest
    public void tearDown(){
        driver.quit();
        driver=null;
    }

	
	}
	
