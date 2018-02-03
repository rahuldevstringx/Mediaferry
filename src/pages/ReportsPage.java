package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import BaseClasses.BasePage;
import utilities.UtilityMethods;

public class ReportsPage extends BasePage {

	UtilityMethods utilityMethods = new UtilityMethods();
	private By clickOnReportsLocator = By.xpath("//span[@class='username']");
	private By clickOnProjectStatusReportLocator = By.xpath("//a[contains(text(),'Project Status Report')]");
	private By clickOnDropDownLocator = By.xpath("//select[@class='form-control margin-bottom-5']");
	private By clickOnSelectedSavedFilterLocator = By.id("filter_views");
	private By clickOnSearchFieldLocator = By.id("btnSearch");
	private By clickOnSelectJobTypeLocator = By.xpath("//span[text()='Select Job Type']/..");
	private By clickOnDropDownFieldLocator = By.xpath("//span[text()='Print']");
	private By clickOnPublicationLocator = By.id("s2id_autogen6");
    private By clickOnResetButtonLocator = By.xpath("//button[text()='Reset']");
    
	//private By clickOnSaveFilterButtonLocator = By.id("btnSave");
	//private By enterTextLocator = By.id("view_name");
	//private By saveButtonLocator = By.xpath("//button[text()='Save']");
	//private By clickOnOkButtonLocator = By.xpath("//button[@class='btn btn-primary']");
	//private By clickOnSelectJobTypeLocator = By.id("select2-drop");


	public void mouseHoverOnReportsHeader(WebDriver driver) {
		Actions action = new Actions(driver);
		waitForElementVisibility(driver,clickOnReportsLocator);
		action.moveToElement(driver.findElement(clickOnReportsLocator)).build().perform();


	}

	public void clickOnProjectStatus(WebDriver driver) {
		waitForElementVisibility(driver,clickOnProjectStatusReportLocator);
		driver.findElement(clickOnProjectStatusReportLocator).click();
	}

	/*public void clickOnDropDown(WebDriver driver,String index) {
		waitForElementVisibility(driver,clickOnDropDownLocator);
		driver.findElement(clickOnDropDownLocator).click();
		selectDropDownElement(driver,clickOnDropDownLocator, index, "Index");
		driver.findElement(clickOnDropDownLocator).click();
	}*/

	public void selectDateRange(WebDriver driver,String selectdaterange) {
		waitForElementVisibility(driver,clickOnDropDownLocator);
		driver.findElement(clickOnDropDownLocator).click();
		Select dropdown = new Select(driver.findElement(By.xpath("//select[@class='form-control margin-bottom-5']")));
		dropdown.selectByValue(selectdaterange);
		driver.findElement(clickOnDropDownLocator).click();

	}

	public void selectFilter(WebDriver driver,String index) {
		waitForElementVisibility(driver,clickOnSelectedSavedFilterLocator);
		driver.findElement(clickOnSelectedSavedFilterLocator).click();
		selectDropDownElement(driver,clickOnSelectedSavedFilterLocator, index,"Index");
		driver.findElement(clickOnSelectedSavedFilterLocator).click();
	}

	public void clickOnJobType(WebDriver driver,String index) throws InterruptedException {
		Thread.sleep(2000);
		waitForElementVisibility(driver,clickOnSelectJobTypeLocator);
		driver.findElement(clickOnSelectJobTypeLocator).click();
		selectDropDownElement(driver,clickOnDropDownFieldLocator, index,"Index");
		driver.findElement(clickOnDropDownFieldLocator).click();
	}
	
	public void clickOnPublication(WebDriver driver,String index) {
		waitForElementVisibility(driver,clickOnPublicationLocator);
		driver.findElement(clickOnPublicationLocator).click();
		selectDropDownElement(driver,clickOnPublicationLocator, index,"Index");
		driver.findElement(clickOnPublicationLocator).click();
	}

	public void searchButton(WebDriver driver) {
		waitForElementVisibility(driver,clickOnSearchFieldLocator);
		driver.findElement(clickOnSearchFieldLocator).click();


	}
	
	public void resetButton(WebDriver driver) {
		waitForElementVisibility(driver,clickOnResetButtonLocator);
		driver.findElement(clickOnResetButtonLocator).click();
	}
	/*public void saveFilter(WebDriver driver) {
		driver.findElement(clickOnSaveFilterButtonLocator).click();
	}

	public void enterText(WebDriver driver ,String text) {
		waitForElementVisibility(driver, enterTextLocator);
		driver.findElement(enterTextLocator).sendKeys("enterText1");
	}

	public void saveButton(WebDriver driver) {
		waitForElementVisibility(driver, saveButtonLocator);
		driver.findElement(saveButtonLocator).click();
	}

	public void okButton(WebDriver driver) {
		waitForElementVisibility(driver, clickOnOkButtonLocator);
		driver.findElement(clickOnOkButtonLocator).click();
	}*/
	/**/
}
