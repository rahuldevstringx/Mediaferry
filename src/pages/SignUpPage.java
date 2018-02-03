package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import BaseClasses.BasePage;

public class SignUpPage extends BasePage {
	private By fullNameLocator = By.id("fullname");
	private By fullNameValidationValidatioMessageLocator = By.xpath("//span[@for='fullname']");
	private By companyAndOrganizationLocator = By.id("company");
	private By companyAndOrganizationValidatioMessageLocator = By.xpath("//span[@for='company']");
	private By emailLocator = By.id("email");
	private By emailValidatioMessageLocator = By.xpath("//span[@for='email']");
	private By passwordLocator = By.id("register_password");
	private By passwordValidatioMessageLocator = By.xpath("//span[@for='register_password']");
	private By signupBtnLocator = By.id("register-submit-btn");
	private By thanksMessageAfterRegisterFieldLoctor = By.id("successfullyRegistered");
	private By thisEmailAddressAlreadyRegisteredFieldLocator = By.id("alreadyUser");

	public String getFullNameValidationMessage(WebDriver driver) {
		waitForElementVisibility(driver, fullNameValidationValidatioMessageLocator);
		return driver.findElement(fullNameValidationValidatioMessageLocator).getText();
	}

	public String getCompanyNameValidationMessage(WebDriver driver) {
		return driver.findElement(companyAndOrganizationValidatioMessageLocator).getText();
	}

	public String getEmailidValidationMessage(WebDriver driver) {
		return driver.findElement(emailValidatioMessageLocator).getText();
	}

	public String getPasswordValidationMessage(WebDriver driver) {
		return driver.findElement(passwordValidatioMessageLocator).getText();
	}

	public void clickOnSignupButton(WebDriver driver) {
		waitForElementVisibility(driver, signupBtnLocator);
		driver.findElement(signupBtnLocator).click();
	}

	public void enterFullName(WebDriver driver, String fullname) {
		waitForElementVisibility(driver, fullNameLocator);
		driver.findElement(fullNameLocator).sendKeys(fullname);
	}

	public void enterCompanyAndOrganization(WebDriver driver, String companyName) {
		driver.findElement(companyAndOrganizationLocator).sendKeys(companyName);
	}

	public void selectCountry(WebDriver driver, String country) {
		Select dropdown = new Select(driver.findElement(By.id("select2_sample4")));
		dropdown.selectByValue(country);
	}

	public void enterEmail(WebDriver driver, String email) {
		driver.findElement(emailLocator).sendKeys(email);
	}

	public void enterPassword(WebDriver driver, String password) {
		driver.findElement(passwordLocator).sendKeys(password);
	}

	public void isThanksMessageAfterRegisterDisplayed(WebDriver driver, SoftAssert softAssert) {
		waitForElementVisibility(driver, thanksMessageAfterRegisterFieldLoctor);
		softAssert.assertTrue(driver.findElement(thanksMessageAfterRegisterFieldLoctor).isDisplayed());
	}

	public void verifyMessageAlreadyRegisterBythisEmail(WebDriver driver, SoftAssert softAssert) {
		waitForElementVisibility(driver, thisEmailAddressAlreadyRegisteredFieldLocator);
		softAssert.assertTrue(driver.findElement(thisEmailAddressAlreadyRegisteredFieldLocator).isDisplayed());
	}
}
