package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;
import BaseClasses.BasePage;
import steps.CreateNewProjectSteps;

public class ActivityFeedPage extends BasePage {
    private By activityHeadingLocator = By.xpath("//div[@id='1']/ul/li/div[1]/div[1]/div[2]/div/span[1]");
    private By fileUploadFormLocator = By.id("fileupload");
    private By searchFieldLocator = By.id("af_project");
    private By filterBtnLocator = By.xpath("//button[text()='Filter']");
    private By fileBtnOnQueryWindowLocator = By.id("files");
    private By respondBtnLocator = By.id("respond_action");
    private By fileUploadLocator = By.xpath("//form[@id='fileupload']//input[@id='file']");
    private By fileUploadListLocator = By.id("filesContainer");
    private By spinnerLocator=By.id("loadOverlay");
    private By assetNameFieldLocator = By.className("name padding alerthover-1");
    private By closeAssetWindowLocator = By.xpath("//div[text()='Close']");
    private By spinnerOnUploadAssetWindowLocator = By.xpath("//img[@src='https://devso.mediaferry.com/mf-s49qa/sitetheme_new/img/loading-spinner-grey.gif']");
    private By assetsDownloadButtonLocator = By.xpath("//table[@id='filesContainerTBL']/tbody/tr[1]/td[4]/button[2]//span");
    private By uploadAssetsLnkLocator = By.xpath("//div[@id='1']/ul/li/div[1]/div[1]/div[2]/div/span[2]/a[2]");
    private By respondToQueryLnkLocator = By.xpath("//div[@id='1']/div[1]/ul/li[2]//a");
    private By responseTextAreaLocator = By.id("user_response2");
    private By statusSpanLocator = By.xpath("//span[@id='pro_861251']/span/span");
    private By spinnerOnLoadingActivityFeedLocator = By.xpath("//img[@src='https://devso.mediaferry.com/mf-s49qa/sitetheme_new/img/loading-spinner-grey.gif']");
    private By queryLnkLocator = By.xpath("//a[text()='Query']");
    private By queryTextAreaLocator = By.xpath("//textarea[@id='user_query']");
    private By queryBtnLocator = By.xpath("//button[text()='Query']");
    private By statusFldLocator = By.xpath("//div[@id='1']/ul/li/div[1]/div[2]/span/span/span/span");
    private By latestActivity = By.xpath("//div[@id='1']/div[1]/ul[1]//strong");
    private By latestActivityContent = By.xpath("//div[@id='1']/div[1]/ul[1]/li[2]//strong/..");
    private By postCommentIconLocator = By.xpath("//a[@class='btn blue icn-only']");
    private By yesPopUpBtnLocator = By.xpath("//div[@class='bootbox modal fade in']//button[text()='Yes']");


    public void mouseHoverOnActivityHeader(WebDriver driver) {
        Actions action = new Actions(driver);
        waitForElementVisibility(driver, activityHeadingLocator);
        action.moveToElement(driver.findElement(activityHeadingLocator)).build().perform();
    }

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

    public void clickOnUploadAssetsLnk(WebDriver driver){
        waitForElementVisibility(driver, uploadAssetsLnkLocator);
        driver.findElement(uploadAssetsLnkLocator).click();
    }

    public void uploadFilesWhileEditing(WebDriver driver, SoftAssert softAssert, String filePath) {
        String fileUploaderPath = getWorkingDirectoryPath() + filePath;
        scrollToElement(driver, fileUploadFormLocator);
        driver.findElement(fileUploadLocator).sendKeys(fileUploaderPath);
    }

    public void uploadFilesOnSameWindow(WebDriver driver,SoftAssert softAssert, String filePath) {
        String fileUploaderPath = getWorkingDirectoryPath() + filePath;
        scrollToElement(driver, fileUploadFormLocator);
        driver.findElement(fileUploadLocator).sendKeys(fileUploaderPath);
    }

    public void uploadFilesOnQueryWindow(WebDriver driver,SoftAssert softAssert, String filePath) throws InterruptedException {
        String fileUploaderPath = getWorkingDirectoryPath() + filePath;
        driver.findElement(fileBtnOnQueryWindowLocator).sendKeys(fileUploaderPath);
        driver.findElement(respondBtnLocator).click();
        Thread.sleep(3000);
    }

    public void clickOnYesBtn(WebDriver driver){
        waitForElementVisibility(driver, yesPopUpBtnLocator);
        driver.findElement(yesPopUpBtnLocator).click();
    }

    public void uploadFilesOnNewWindow(WebDriver driver, String filePath) throws InterruptedException {
    	String fileUploaderPath = getWorkingDirectoryPath() + filePath;
        Thread.sleep(1000);
        driver.manage().window().maximize();
        ((JavascriptExecutor) driver) .executeScript("window.scrollTo(0, document.body.scrollHeight)");	      
        driver.findElement(fileUploadLocator).sendKeys(fileUploaderPath);
        Thread.sleep(6000);
        ((JavascriptExecutor) driver) .executeScript("window.scrollTo(0, -document.body.scrollHeight)");	      
        By elem = By.xpath("//div[@class='tab-pane active']//tbody/tr/td[4]/button[2]/span");
        waitForElementVisibility(driver, elem);
        driver.findElement(closeAssetWindowLocator).click();
    }

    public void verifyUploadedFile(WebDriver driver, SoftAssert softassert) {
        waitForElementVisibility(driver, fileUploadListLocator);
        softassert.assertTrue(driver.findElement(fileUploadListLocator).isDisplayed());
    }

    public void clickOnUploadBtnFromFeedActivity(WebDriver driver, String projectName) {
        String Locator = "//strong[text()='"+ projectName +"']//../..//a[text()='Upload']";
        waitForElementVisibility(driver, spinnerLocator);
        waitForElementInvisibility(driver, spinnerLocator);
        waitForElementVisibility(driver, By.xpath(Locator));
        driver.findElement(By.xpath(Locator)).click();
    }

    public void clickOnRespondToQueryLnk(WebDriver driver) throws InterruptedException {
        Thread.sleep(3000);
        waitForElementVisibility(driver, respondToQueryLnkLocator);
        driver.findElement(respondToQueryLnkLocator).click();
    }

    public void enterResponseTextArea(WebDriver driver, String respose){
        waitForElementVisibility(driver, responseTextAreaLocator);
        driver.findElement(responseTextAreaLocator).sendKeys(respose);
    }

    public void clickOnRespondBtn(WebDriver driver){
        waitForElementVisibility(driver, respondBtnLocator);
        driver.findElement(respondBtnLocator).click();
    }

    public void enterComment(WebDriver driver, String comment){
        By commentFldLocator = By.xpath("//div[@id='1']/div[3]/div[2]/textarea");
        waitForElementVisibility(driver, commentFldLocator);
        driver.findElement(commentFldLocator).sendKeys(comment);
    }

    public void clickOnPostCommentIcon(WebDriver driver) throws InterruptedException {
        waitForElementVisibility(driver, postCommentIconLocator);
        Thread.sleep(5000);
        driver.findElement(postCommentIconLocator).click();
    }

    public void verifyRespondStatus(WebDriver driver, SoftAssert softAssert) {
        try {
            if (driver.findElement(statusSpanLocator).isDisplayed()) {
                softAssert.assertTrue(true);
            }
            else {
                softAssert.assertTrue(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void waitForActivityFeedToLoad(WebDriver driver){
        waitForElementVisibility(driver, spinnerOnLoadingActivityFeedLocator);
        waitForElementInvisibility(driver, spinnerOnLoadingActivityFeedLocator);
    }

    public void waitForOverlaySpinner(WebDriver driver) {
        By overlayIconLocator = By.xpath("//div[@id='loadOverlay']");
        try{
            waitForElementVisibility(driver, overlayIconLocator);
            waitForElementInvisibility(driver, overlayIconLocator);
        }
        catch (TimeoutException e){
            e.getStackTrace();
        }
    }

    public void mouseHoverAtSelectedPoject(WebDriver driver, String projectName) {
        scrollTop(driver);
        String xpath = "//span[contains(text(),'" + projectName + " project is updated')]";
        waitForElementVisibility(driver, By.xpath(xpath));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath(xpath))).build().perform();
    }

    public void clickOnQueryLnk(WebDriver driver){
        waitForElementVisibility(driver, queryLnkLocator);
        driver.findElement(queryLnkLocator).click();
    }

    public void enterQuery(WebDriver driver, String query){
        waitForElementVisibility(driver, queryTextAreaLocator);
        driver.findElement(queryTextAreaLocator).sendKeys(query);
    }

    public void clickOnQueryBtn(WebDriver driver){
        waitForElementVisibility(driver, queryBtnLocator);
        driver.findElement(queryBtnLocator).click();
    }

    public String getStatus(WebDriver driver){
        waitForElementVisibility(driver, statusFldLocator);
        return driver.findElement(statusFldLocator).getText();
    }

    public String getProfileNameForQuery(WebDriver driver){
        waitForElementVisibility(driver, latestActivity);
        String text = driver.findElement(latestActivity).getText();
        String profileName = text.substring(text.indexOf("Query by") + 9);
        return profileName;
    }

    public String getProfileNameForRespond(WebDriver driver){
        waitForElementVisibility(driver, latestActivity);
        String text = driver.findElement(latestActivity).getText();
        String profileName = text.substring(text.indexOf("Responded by") + 13);
        return profileName;
    }

    public String getProfileNameForComment(WebDriver driver){
        waitForElementVisibility(driver, latestActivity);
        String profileName = driver.findElement(latestActivity).getText();
        return profileName;
    }

    public String getComment(WebDriver driver){
        waitForElementVisibility(driver, latestActivityContent);
        String comment = driver.findElement(latestActivityContent).getText();
        return comment;
    }

    public void selectCampaignName(WebDriver driver, String campaignName){
        By campaignDropDownLocator = By.xpath("//div[@id='s2id_af_campaign']/a");
        By campaignInputLocator = By.xpath("//div[@id='select2-drop']/div/input");
        By selectResultDropdownLocator = By.xpath("//ul[@class='select2-results']/li/div/span");
        waitForElementVisibility(driver, campaignDropDownLocator);
        driver.findElement(campaignDropDownLocator).click();
        waitForElementVisibility(driver, campaignInputLocator);
        driver.findElement(campaignInputLocator).sendKeys(campaignName);
        waitForElementVisibility(driver, selectResultDropdownLocator);
        driver.findElement(selectResultDropdownLocator).click();
    }

    public void selectProductionStatus(WebDriver driver, String prodStatus){
        By prodStatusSelectLocator = By.xpath("//div[@id='s2id_select2_sample2']/ul/li");
        By prodStatusInput = By.xpath("//div[@id='s2id_select2_sample2']/ul/li/input");
        By selectResultProdStatusLocator = By.xpath("//ul[@class='select2-results']/li/div/span");
        waitForElementVisibility(driver, prodStatusSelectLocator);
        driver.findElement(prodStatusSelectLocator).click();
        driver.findElement(prodStatusInput).sendKeys(prodStatus);
        waitForElementVisibility(driver, selectResultProdStatusLocator);
        driver.findElement(selectResultProdStatusLocator).click();

    }

    public void selectPriority(WebDriver driver, String priority){
        By priorityDropDownLocator = By.xpath("//div[@id='s2id_filterPriority']/a");
        By priorityInputLocator = By.xpath("//div[@id='select2-drop']/div/input");
        By selectResultDropdownLocator = By.xpath("//ul[@class='select2-results']/li/div/span");
        waitForElementVisibility(driver, priorityDropDownLocator);
        driver.findElement(priorityDropDownLocator).click();
        waitForElementVisibility(driver, priorityInputLocator);
        driver.findElement(priorityInputLocator).sendKeys(priority);
        waitForElementVisibility(driver, selectResultDropdownLocator);
        driver.findElement(selectResultDropdownLocator).click();
    }

    public void enterWidth(WebDriver driver, String width){
        By widthFieldLocator = By.id("grid_width");
        waitForElementVisibility(driver, widthFieldLocator);
        driver.findElement(widthFieldLocator).sendKeys(width);
    }

    public void enterHeight(WebDriver driver, String height){
        By heightFieldLocator = By.id("grid_height");
        waitForElementVisibility(driver, heightFieldLocator);
        driver.findElement(heightFieldLocator).sendKeys(height);
    }

}
