package tests;

import BaseClasses.Driver;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.FindFailed;
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

public class UploadProfilePicTest {
	Driver driverObj = new Driver();
	WebDriver driver = null;
	LoginSteps loginSteps = new LoginSteps();
	SoftAssert softAssert = new SoftAssert();
	UtilityMethods utilityMethods = new UtilityMethods();
	HomeSteps homeSteps = new HomeSteps();

	@BeforeTest
	public void start() throws IOException {
		driver = driverObj.createDriver();
		driver.get(driverObj.getUrl());
	}

	@Test(priority = 1)
	public void loginWithValidCredentials() throws Exception {
		loginSteps.login(driver, softAssert, driverObj.getUsername(), driverObj.getPassword());
		homeSteps.waitingForHeaderLnksSpinner(driver);
	}

	@Test(priority = 2)
	public void uploadProfile() throws InterruptedException, FindFailed {
		homeSteps.uploadProfileSteps(driver, driverObj.getMyProfilePath());
		homeSteps.saveChangesSuccessfully(driver, softAssert, "Personal information has been saved successfully.");
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
		driver = null;
	}
}
