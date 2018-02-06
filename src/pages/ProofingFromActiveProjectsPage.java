package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import BaseClasses.BasePage;

public class ProofingFromActiveProjectsPage extends BasePage {
	private By clickOnActiveProjectsLocator = By.id("tra");
	private By typeInSearchFieldLocator = By.id("f_project");

	public void clickOnActiveProjects(WebDriver driver) 
	{
		driver.findElement(clickOnActiveProjectsLocator).click();
	}

	public void typeInSearchField(WebDriver driver, String projectName)
	{
		waitForElementVisibility(driver, typeInSearchFieldLocator);
		driver.findElement(typeInSearchFieldLocator).sendKeys(projectName);
	}

}
