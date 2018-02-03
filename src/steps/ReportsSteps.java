package steps;

import org.openqa.selenium.WebDriver;

import BaseClasses.BasePage;
import pages.ReportsPage;
import utilities.UtilityMethods;

public class ReportsSteps extends BasePage {

	ReportsPage reportsPage = new ReportsPage();
	UtilityMethods utilityMethods = new UtilityMethods();
	

	public void projectStatusReport(WebDriver driver) throws InterruptedException {
		reportsPage.mouseHoverOnReportsHeader(driver);
		reportsPage.clickOnProjectStatus(driver);
		reportsPage.selectDateRange(driver,"today");
		//reportsPage.searchButton(driver);
		//reportsPage.selectFilter(driver, "4");
		//reportsPage.saveFilter(driver);
		//reportsPage.enterText(driver, "enterText1");
		//reportsPage.saveButton(driver);
		//reportsPage.okButton(driver);
		//reportsPage.clickOnJobType(driver, "1");
	}

	public void filter(WebDriver driver) {
		//reportsPage.resetButton(driver);
		reportsPage.selectFilter(driver, "4");
		//reportsPage.searchButton(driver);
	}
	
	public void selectJobType(WebDriver driver) throws InterruptedException {
		reportsPage.clickOnJobType(driver, "2");
		reportsPage.searchButton(driver);
	}
	
	public void selectPublication(WebDriver driver) {
		reportsPage.clickOnPublication(driver, "3");
	}
}

