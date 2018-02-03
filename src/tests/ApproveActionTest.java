package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import BaseClasses.Driver;
import steps.ActiveProjectsSteps;
import steps.ApproveActionSteps;
import steps.CreateNewProjectSteps;
import steps.HomeSteps;
import steps.LoginSteps;
import utilities.UtilityMethods;

public class ApproveActionTest {

	Driver driverObj = new Driver();
	WebDriver driver = null;
	SoftAssert softAssert = new SoftAssert();
	LoginSteps loginSteps = new LoginSteps();
	String projectName = null;
	UtilityMethods utilityMethods = new UtilityMethods();
	int projectCountBefore;
	HomeSteps homeSteps = new HomeSteps();
	CreateNewProjectSteps createNewProjectSteps = new CreateNewProjectSteps();
	int projectCountAfter;
	ActiveProjectsSteps activeProjectsSteps = new ActiveProjectsSteps();
	ApproveActionSteps approveActionSteps = new ApproveActionSteps();
	
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
		createNewProjectSteps.fillingDetails(driver, projectName, driverObj.getCampaign(), driverObj.getBrandName(), driverObj.getCreativeLevel(), driverObj.getFilePath(), driverObj.getPriority(), driverObj.getProjectOwner(), driverObj.getInstructions(), driverObj.getTeam(), driverObj.getWidth(), driverObj.getHeight());
		createNewProjectSteps.submittingJob(driver,softAssert, projectName);
		projectCountAfter = homeSteps.projectCountAfter(driver);
		homeSteps.verifyProjectCount(driver, softAssert, projectCountBefore+1, projectCountAfter);
		activeProjectsSteps.selectProject(driver, softAssert, projectName);
		approveActionSteps.approveActionSteps(driver,softAssert,driverObj.getCampaign());		
	}
	
    @AfterTest
    public void tearDown() {
        driver.quit();
        driver = null;
    }

  
}
