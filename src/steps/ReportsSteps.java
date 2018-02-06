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
	}

	public void filter(WebDriver driver) {
		reportsPage.selectFilter(driver, "4");
	}
	
	public void selectJobType(WebDriver driver) throws InterruptedException {
		reportsPage.clickOnJobType(driver, "2");
		reportsPage.searchButton(driver);
	}
	
	public void selectPublication(WebDriver driver) {
		reportsPage.clickOnPublication(driver, "3");
	}
}

