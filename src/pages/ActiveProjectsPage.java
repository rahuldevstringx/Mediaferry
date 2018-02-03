package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;
import BaseClasses.BasePage;

public class ActiveProjectsPage extends BasePage {

	private By searchFieldLocator = By.id("f_project");
	private By filterBtnLocator = By.xpath("//button[text()='Filter']");
	private By filterTitleLocator = By.xpath("//div[@class='portlet-title']");
	private By queryLinkLocator = By.xpath("//a[text()='Query']");
	private By emailProofLinkLocator = By.xpath("//a[text()='Email Proof']");
	private By shareLinkLocator = By.xpath("//a[text()='Share']");
	//private By proofLnkLocator = By.xpath("//a[text()='Proof']");
	private By closeProofBtnLocator = By.xpath("//div[@id='close']");
	private By proofLnkLocator = By.xpath("//span[@class='portfolio-info-text']/a[text()='Proof']"); 
	private By sharingEmailsFieldLocator = By.xpath("//input[@id='email_field']");
	private By closeWindowShareBtnLocator = By.xpath("//button[text()='Share']");
	private By closeWindowCloseBtnLocator = By.xpath("//button[text()='Share']/preceding-sibling::button");
	private By alertMessageLocator = By.xpath("//span[@id='error_display']");
	private By queryBtnLocator = By.xpath("//button[text()='Query']");
	private By validationMessageLocator = By.id("user_query_error");
	private By changeStatusLnkLocator = By.xpath("//a[text()='Change Status']");
	private By statusQueryToTrafficLocator = By.xpath("//p[contains(text(),'Query to Traffic')]");
	private By closeBtnLocator = By.xpath("//div[@class='bootbox modal fade in']//button[text()='Close']");
	private By closeBtnResponseLocator = By.xpath("//button[@id='respond_action']/preceding-sibling::button");
	private By queryTextBox =By.id("user_query");
	private By viewOrEditProjectLinkLocator = By.xpath("//a[contains(text(),'View or Edit')]");
	private By validationTypeResponseLocator = By.id("user_response2_error");
	private By respondQueryLnkLocator = By.xpath("//img[@src='https://devso.mediaferry.com/mf-s49qa/sitetheme_new/image/respond-icon.gif']");
	private By status = By.xpath("//p[contains(text(),'Query to Traffic')]");
	private By respondBtnLocator = By.id("respond_action");
	//private By respondBtnLocator = By.xpath("//button[text()='Respond']");
	private By responseMsgBoxLocator = By.xpath("//textarea[@id='user_response2']");
	private By spinner = By.xpath("//img[@src='./../assets/images/loading-spinner-grey.gif']");
	private By approveSpinner = By.xpath("//img[@src='https://devso.mediaferry.com/mf-s49qa/sitetheme_new/img/loading-spinner-grey.gif']");
	private By checkProjectLocator = By.xpath("//div[@id='1']//input[@id='case']");
	private By clickApproveLocator = By.xpath("//div[@data-original-title='Approve']/img");
	private By clickConfirmYesLocator = By.xpath("//button[@class='btn btn blue']");
	private By getPreReleaseTextLocator = By.xpath("//p[@class='authertext']");
	private By emailProofFieldLocator = By.id("email_container_proof");
	private By artworkCheckboxLocator = By.id("proof_attach");
	private By emailProofButtonLocator = By.xpath("//div[@id='emailProofpopup']//button[text()='Email Proof']");
	private By sucessfullSentMsgLocator = By.xpath("//span[contains(text(),'The proof has been emailed to the recipients.')]");

	public void enterProjectNameInSearchField(WebDriver driver, String projectName) {
		waitForElementVisibility(driver, searchFieldLocator);
		driver.findElement(searchFieldLocator).sendKeys(projectName);
	}

	public void clickOnFilterBtn(WebDriver driver) {
		waitForElementClickable(driver, filterBtnLocator);
		driver.findElement(filterBtnLocator).click();
	}

	public boolean verifyFilterFunctionality(WebDriver driver, SoftAssert softAssert, String ProjectName) {
		String xpath = "//span[contains(text(),'" + ProjectName + "')]";
		waitForElementVisibility(driver, By.xpath(xpath));
		return driver.findElement(By.xpath(xpath)).isDisplayed();
	}

	public void mouseHoverAtCreatedPoject(WebDriver driver, String projectName) {
		scrollTop(driver);
		String xpath = "//span[contains(text(),'" + projectName + "')]";
		waitForElementVisibility(driver, By.xpath(xpath));
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath(xpath))).build().perform();
	}

	public void clickOnViewOrEditLink(WebDriver driver){
		waitForElementVisibility(driver, viewOrEditProjectLinkLocator);
		driver.findElement(viewOrEditProjectLinkLocator).click();
	}

	public void clickOnQueryLink(WebDriver driver) {
		waitForElementVisibility(driver, queryLinkLocator);
		driver.findElement(queryLinkLocator).click();
	}

	public void clickOnShareLink(WebDriver driver){
		waitForElementVisibility(driver, shareLinkLocator);
		driver.findElement(shareLinkLocator).click();
	}

	public void clickOnProofLink(WebDriver driver){
		waitForElementVisibility(driver, proofLnkLocator);
		driver.findElement(proofLnkLocator).click();
	}

	public void clickOnEmailProofLink(WebDriver driver){
		waitForElementVisibility(driver, emailProofLinkLocator);
		driver.findElement(emailProofLinkLocator).click();
	}

	public void enterSharingWithEmails(WebDriver driver, String sharingWithMailOne, String sharingWithMailTwo){
		waitForElementVisibility(driver, sharingEmailsFieldLocator);
		driver.findElement(sharingEmailsFieldLocator).sendKeys(sharingWithMailOne);
		driver.findElement(sharingEmailsFieldLocator).sendKeys(",");
		driver.findElement(sharingEmailsFieldLocator).sendKeys(sharingWithMailTwo);
	}

	public void waitForLoadingProofingWindow(WebDriver driver){
		try {
			waitForElementVisibility(driver, spinner);
			waitForElementInvisibility(driver, spinner);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public void clickOnShareBtn(WebDriver driver){
		waitForElementVisibility(driver, closeWindowShareBtnLocator);
		driver.findElement(closeWindowShareBtnLocator).click();
	}

	public void clickOnShareWindowCloseBtn(WebDriver driver){
		waitForElementVisibility(driver, closeWindowCloseBtnLocator);
		driver.findElement(closeWindowCloseBtnLocator).click();
	}

	public String getSharedAlertMessage(WebDriver driver){
		waitForElementVisibility(driver, alertMessageLocator);
		return driver.findElement(alertMessageLocator).getText();
	}

	public void clickOnQueryBtn(WebDriver driver) {
		waitForElementVisibility(driver, queryBtnLocator);
		driver.findElement(queryBtnLocator).click();
	}

	public void enterQuery(WebDriver driver, String query){
		WebElement textArea = driver.findElement(By.xpath("//textarea[@id='user_query']"));
		waitForElementVisibility(driver, By.xpath("//textarea[@id='user_query']"));
		textArea.sendKeys(Keys.TAB);
		textArea.clear();
		textArea.sendKeys(query);
	}

	public void clickOnRespondToQuery(WebDriver driver) throws InterruptedException { Thread.sleep(2000);
		waitForElementVisibility(driver, respondQueryLnkLocator);
		driver.findElement(respondQueryLnkLocator).click();
	}

	public void clickOnRespondButton(WebDriver driver){
		waitForElementVisibility(driver, respondBtnLocator);
		driver.findElement(respondBtnLocator).click();
	}

	public void enterMessageInResponseBox(WebDriver driver, String response){
		waitForElementVisibility(driver, responseMsgBoxLocator);
		driver.findElement(responseMsgBoxLocator).sendKeys(response);
	}

	public String getValidationOnTypeRespose(WebDriver driver){
		waitForElementVisibility(driver, validationTypeResponseLocator);
		String actualValidation = driver.findElement(validationTypeResponseLocator).getText();
		return actualValidation;
	}

	public void enterResponceToQuery(WebDriver driver, String response) {
		WebElement textAreaResponse = driver.findElement(By.xpath("//textarea[@name='user_response2']"));
		waitForElementVisibility(driver, By.xpath("//textarea[@name='user_response2']"));
		textAreaResponse.sendKeys(Keys.TAB);
		textAreaResponse.clear();
		textAreaResponse.sendKeys(response);
	}

	public void waitForLoadingActiveProjectPage(WebDriver driver){
		waitForElementVisibility(driver, filterTitleLocator);
	}

	public void verifyValidationMessage(WebDriver driver, SoftAssert softAssert) {
		waitForElementVisibility(driver, validationMessageLocator);
		softAssert.assertEquals(driver.findElement(validationMessageLocator).getText(), "This field is required");
	}

	public String getChangeStatusLnkText(WebDriver driver) {
		waitForElementVisibility(driver, changeStatusLnkLocator);
		return driver.findElement(changeStatusLnkLocator).getText();
	}

	public boolean statusQueryToTrafficPresence(WebDriver driver){
		waitForElementVisibility(driver, statusQueryToTrafficLocator);
		return driver.findElement(statusQueryToTrafficLocator).isDisplayed();
	}

	public void clickOnCloseBtn(WebDriver driver) {
		waitForElementVisibility(driver, closeBtnLocator);
		driver.findElement(closeBtnLocator).click();
	}

	public void clickOnCloseBtnResponse(WebDriver driver){
		waitForElementVisibility(driver, closeBtnResponseLocator);
		driver.findElement(closeBtnResponseLocator).click();
	}

	public void enterMessageInQueryBox(WebDriver driver, String message) {
		waitForElementVisibility(driver, queryTextBox);
		driver.findElement(queryTextBox).sendKeys(message);
	}

	public void verifyStatus(WebDriver driver, SoftAssert softAssert){
		waitForElementVisibility(driver, status);
		softAssert.assertTrue(driver.findElement(status).isDisplayed());
	}

	public void verifyRespondToQuery(WebDriver driver, SoftAssert softAssert,String projectName ) {
		String xpath="//span[contains(text(),'"+projectName+"')]/..//img[@src='https://devso.mediaferry.com/mf-s49qa/sitetheme_new/image/respond-icon.gif']";
		waitForElementVisibility(driver, By.xpath(xpath));
		softAssert.assertTrue(driver.findElement(By.xpath(xpath)).isDisplayed());
	}

	public void verifyQueryLink(WebDriver driver, SoftAssert softAssert, boolean ifCondition, boolean elseCondition) {
		try {
			if (driver.findElement(queryLinkLocator).isDisplayed()) {
				softAssert.assertTrue(ifCondition);
			}
			else
				softAssert.assertTrue(elseCondition);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void clickOnCloseBtnProofWindow(WebDriver driver){
		driver.findElement(closeProofBtnLocator).click();
	}

	public void verifyStatusLink(WebDriver driver, SoftAssert softAssert, boolean ifCondition, boolean elseCondition) {
		try {
			if (driver.findElement(changeStatusLnkLocator).isDisplayed()) {
				softAssert.assertTrue(ifCondition);
			}
			else {
				softAssert.assertTrue(elseCondition);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void checkProject(WebDriver driver) throws InterruptedException {
		scrollTop(driver);
		driver.findElement(checkProjectLocator).click(); 
	}

	public void clickApprove(WebDriver driver) {
		waitForElementVisibility(driver, clickApproveLocator);
		driver.findElement(clickApproveLocator).click(); 
	}

	public void clickConfirmYes(WebDriver driver) throws InterruptedException {
		waitForElementVisibility(driver, clickConfirmYesLocator);
		driver.findElement(clickConfirmYesLocator).click();		
	}
	
	public void verifyProjectStatus(WebDriver driver, SoftAssert softAssert,String compaign)
	{
		waitAfterApprove(driver);
		String getCompleteText = driver.findElement(getPreReleaseTextLocator).getText();
		String[] arrSplit = getCompleteText.split(compaign);
		String arrSplitText = arrSplit[0];
		String arrSplitSubstring = arrSplitText.substring(arrSplitText.length()-14,arrSplitText.length());
		String actualString=arrSplitSubstring.substring(0, 11);
		softAssert.assertEquals(actualString, "Pre Release");
		}
	
	public void waitAfterApprove(WebDriver driver) {
		waitForElementVisibility(driver, approveSpinner);
		waitForElementInvisibility(driver, approveSpinner);
	}

	public void enterEmailForProofing(WebDriver driver, String emailForProofing){
		waitForElementVisibility(driver, emailProofFieldLocator);
		driver.findElement(emailProofFieldLocator).clear();
		driver.findElement(emailProofFieldLocator).sendKeys();
		driver.findElement(emailProofFieldLocator).click();
	}

	public void clickOnArtworkCheckbox(WebDriver driver){
		waitForElementVisibility(driver, artworkCheckboxLocator);
		driver.findElement(artworkCheckboxLocator).click();
	}

	public void clickOnEmailProofButton(WebDriver driver){
		waitForElementVisibility(driver, emailProofButtonLocator);
		driver.findElement(emailProofButtonLocator).click();
	}

	public void waitSucessfullSentEmailProof(WebDriver driver){
		waitForElementVisibility(driver, sucessfullSentMsgLocator);
		waitForElementInvisibility(driver, sucessfullSentMsgLocator);
	}
}
