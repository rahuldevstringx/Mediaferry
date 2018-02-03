package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;

import BaseClasses.BasePage;

public class LoginPage extends BasePage {

	private By usernameTextFieldLocator = By.id("username");
	private By PasswordTextFieldLocator = By.name("password");
	private By loginBtnLocator = By.id("SbmtButton");
	private By homeTitleLocator =By.className("page-title");
	private By profileFieldLocator=By.id("profile");
	private By profileAdjacentDownIconLocator = By.xpath("//i[@class='fa fa-angle-down']");
	private By logoutFieldLocator=By.xpath("//a[text()=' Log Out']");
	private By invalidLoginMessageFieldLocator=By.xpath("//div[@id='alertuperr']/span");
	private By createAnAccountLinkFieldLocator=By.id("register-btn");
	private By createNewProjectBtnLocator=By.xpath("//div[contains(@class, 'dashboard-stat') and contains(., ' Create')]");
	private By activeProjectsLocator=By.xpath("//div[contains(@class, 'dashboard-stat') and contains(., 'Active projects')]");
	private By sessionValidationLocator = By.xpath("//span[contains(text(),'Session expired. Please login again.')]");

	public void enterUsername(WebDriver driver, String username) {
		waitForElementVisibility(driver, usernameTextFieldLocator);
		driver.findElement(usernameTextFieldLocator).sendKeys(username);
	}

	public void enterPassword(WebDriver driver, String password) {
		driver.findElement(PasswordTextFieldLocator).sendKeys(password);
	}

	public void clickOnLoginButton(WebDriver driver) {
		driver.findElement(loginBtnLocator).click();
	}

	public void verifyLogin(WebDriver driver, SoftAssert softAssert) {
		waitForElementVisibility(driver, homeTitleLocator);
		softAssert.assertTrue(driver.findElement(homeTitleLocator).isDisplayed());
	}

	public void mouseHoverOnProfileName(WebDriver driver) {
		waitForElementVisibility(driver, profileFieldLocator);
		Actions action=new Actions(driver);
		action.moveToElement(driver.findElement(profileFieldLocator)).build().perform();
	}

	public void clickOnLogout(WebDriver driver) throws InterruptedException {
		waitForElementVisibility(driver, logoutFieldLocator);
		driver.findElement(logoutFieldLocator).click();
	}

	public void verifyInvalidLogin(WebDriver driver, SoftAssert softAssert) {
			waitForElementVisibility(driver, invalidLoginMessageFieldLocator);
			softAssert.assertEquals("Either username or password is invalid", driver.findElement(invalidLoginMessageFieldLocator).getText());
	}

	public void verifyLogout(WebDriver driver, SoftAssert softAssert) {
		waitForElementVisibility(driver, usernameTextFieldLocator);
		softAssert.assertTrue(driver.findElement(usernameTextFieldLocator).isDisplayed());
	}

	public void checkValidationOfSession(WebDriver driver, SoftAssert softAssert){
		waitForElementVisibility(driver, sessionValidationLocator);
        softAssert.assertTrue(driver.findElement(sessionValidationLocator).isDisplayed());
	}

	public void clickOnTheCreateAnAccountLink(WebDriver driver){
		driver.findElement(createAnAccountLinkFieldLocator).click();
	}

	public void verifyCreateProjectBtn(WebDriver driver, SoftAssert softAssert) {
		waitForElementVisibility(driver, createNewProjectBtnLocator);
		softAssert.assertTrue(driver.findElement(createNewProjectBtnLocator).isDisplayed());
	}

	public void verifyActiveProjectLocator(WebDriver driver, SoftAssert softAssert) {
		waitForElementVisibility(driver, activeProjectsLocator);
		softAssert.assertTrue(driver.findElement(activeProjectsLocator).isDisplayed());
	}

    public void clickOnProfileAdjacentDownIcon(WebDriver driver){
	    waitForElementVisibility(driver, profileAdjacentDownIconLocator);
	    driver.findElement(profileAdjacentDownIconLocator).click();
    }

}
