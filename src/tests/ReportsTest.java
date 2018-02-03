package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import BaseClasses.BasePage;
import BaseClasses.Driver;
import steps.ActiveProjectsSteps;
import steps.ApproveActionSteps;
import steps.CreateNewProjectSteps;
import steps.HomeSteps;
import steps.LoginSteps;
import steps.ReportsSteps;
import utilities.UtilityMethods;

public class ReportsTest extends BasePage {
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
	ReportsSteps reportsSteps = new ReportsSteps();


	@BeforeTest
	public void start() throws IOException {
		driver = driverObj.createDriver();
		driver.get(driverObj.getUrl());
	}

	@Test(priority=1)
	public void ReportsFunctionality() throws Exception {
		loginSteps.login(driver, softAssert, driverObj.getUsername(), driverObj.getPassword());
		reportsSteps.projectStatusReport(driver);

	}

	@Test(priority=2)
	public void FilterFunctionality() throws Exception {
		reportsSteps.filter(driver);
	}
   
	@Test(priority=3)
	public void SelectJobFunctionality() throws Exception {
		reportsSteps.selectJobType(driver);
	}
   
	@Test(priority=4)
	public void SelectPublicationFunctionality() {
		reportsSteps.selectPublication(driver);
	}
	
	@AfterTest
   public void tearDown() {
       driver.quit();
       driver = null;
   }
}

