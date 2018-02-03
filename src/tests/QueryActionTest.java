package tests;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import BaseClasses.Driver;
import steps.ActiveProjectsSteps;
import steps.CreateNewProjectSteps;
import steps.HomeSteps;
import steps.LoginSteps;
import utilities.UtilityMethods;

public class QueryActionTest {
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
    public void verifyValidationMessage() {
        activeProjectsSteps.selectProjectForQuerying(driver, softAssert, projectName);
        activeProjectsSteps.verifyValiationMessageOnQueryFields(driver, softAssert);
    }

    @Test(priority = 3)
    public void statusVerificationAfterClickOnCloseBtn() {
        activeProjectsSteps.statusVerificationOnClickCloseBtn(driver, softAssert, projectName);
	}

    @Test(priority = 4)
    public void sendQuery() {
       activeProjectsSteps.sendQuerySteps(driver, softAssert, projectName, "This is query message text");
    }

    @Test(priority = 5)
    public void verifyQueryLink() {
        activeProjectsSteps.verifyingQueryLnkSteps(driver, softAssert, projectName, false, true);
    }

    @Test(priority = 6)
    public void verifyStatusLink() {
        activeProjectsSteps.verifyingStatusLnkSteps(driver, softAssert, projectName, false, true);
    }

	@AfterTest
	public void tearDown() {
		driver.quit();
		driver = null;
	}

}
