package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import BaseClasses.BasePage;
import steps.CreateNewProjectSteps;
import utilities.UtilityMethods;

public class ProofingFromActivityFeedPage extends BasePage {
	
	CreateNewProjectPage createNewProjectPage = new CreateNewProjectPage();
	UtilityMethods utilityMethods = new UtilityMethods();
	CreateNewProjectSteps createNewProjectSteps = new CreateNewProjectSteps();
	ActivityFeedPage activityFeedPage = new ActivityFeedPage();
	
	
	private By clickOnActivityFeedLocator = By.id("Activity");
	private By searchProjectLocator = By.id("af_project");
	private By clickOnFilterLocator = By.xpath("//button[text()='Filter']");

	public void clickOnActivityFeed(WebDriver driver) 
	{
		driver.findElement(clickOnActivityFeedLocator).click();
	}

	public void enterProjectNameInSearchField(WebDriver driver, String projectName) {
		waitForElementVisibility(driver, searchProjectLocator);
		driver.findElement(searchProjectLocator).sendKeys(projectName);
	}
	public void clickOnFilterBtn(WebDriver driver) {
		waitForElementClickable(driver, clickOnFilterLocator);
		driver.findElement(clickOnFilterLocator).click();
	}
	
	public void proofingUpload(WebDriver driver,String filePath) throws InterruptedException
	{
        utilityMethods.swichToWindow(driver, 1);
        createNewProjectSteps.draggingStickyNotesOnUploadedAsset(driver);
        utilityMethods.swichToWindow(driver, 2);     
        activityFeedPage.uploadFilesOnNewWindow(driver,filePath);
        utilityMethods.swichToWindow(driver, 1);
        createNewProjectSteps.closePopingWindow(driver);
        utilityMethods.swichToWindow(driver,0);
        Thread.sleep(2000); 
	}


}
