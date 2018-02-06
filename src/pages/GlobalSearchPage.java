package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import BaseClasses.BasePage;
import steps.UploadDownloadAssetsSteps;

public class GlobalSearchPage extends BasePage{
	
	String getCompaignText;
	UploadDownloadAssetsSteps uploadDownloadAssetsSteps = new UploadDownloadAssetsSteps();
	
	private By searchByProjectLocator = By.id("SearchBar");
	private By waitForProjectLocator = By.xpath("//li[@class='projectTitle']");
	private By waitForAssetLocator = By.xpath("//li[@class='assetTitle']");
	private By clickOnProjectLocator = By.xpath("//p[@class='projectimgheading']//span[@class='highlight']");
	private By saveChangesButtonLocator = By.id("save_view_edit");
	
	public void searchByCompaign(WebDriver driver, String compaign)
	{
		driver.findElement(searchByProjectLocator).clear();
		driver.findElement(searchByProjectLocator).sendKeys(compaign);
		waitForElementVisibility(driver, waitForProjectLocator);	
		} 
	
	public void searchByAsset(WebDriver driver, String asset)
	{
		driver.findElement(searchByProjectLocator).clear();
		driver.findElement(searchByProjectLocator).sendKeys(asset);
		waitForElementVisibility(driver, waitForAssetLocator);	
	} 
	
	public void searchByProjectAndClick(WebDriver driver,SoftAssert softAssert, String projectName) throws InterruptedException {
		driver.findElement(searchByProjectLocator).clear();
		driver.findElement(searchByProjectLocator).sendKeys(projectName);
		waitForElementVisibility(driver, waitForProjectLocator);
		driver.findElement(waitForProjectLocator).click();	
		waitForElementVisibility(driver, clickOnProjectLocator);
		driver.findElement(clickOnProjectLocator).click();
		Thread.sleep(2000);
		uploadDownloadAssetsSteps.uploadAssetsOnProofPrerequisitesSteps(driver);

	}

	public void searchByProjectFromGlobalSearch(WebDriver driver,SoftAssert softAssert, String projectName) throws InterruptedException {
		Thread.sleep(5000);
		driver.findElement(searchByProjectLocator).clear();
		driver.findElement(searchByProjectLocator).sendKeys(projectName);
		waitForElementVisibility(driver, waitForProjectLocator);
		driver.findElement(waitForProjectLocator).click();
		Thread.sleep(2000);
	}

	public void clickOnEmailLnk(WebDriver driver){
		By emailProofLnkLocator = By.xpath("//span[@class='projecticon']/a[8]");
		waitForElementVisibility(driver, emailProofLnkLocator);
		driver.findElement(emailProofLnkLocator).click();
	}
	
	public void clickSaveChangesButton(WebDriver driver) throws InterruptedException
	{
		driver.findElement(saveChangesButtonLocator).click();
		Thread.sleep(4000);
	}
	
}


