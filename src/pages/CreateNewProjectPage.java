package pages;

import BaseClasses.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

public class CreateNewProjectPage extends BasePage{

    private By projectNameLocator = By.id("JobNumberId");
    // Campaign Field Locator
    private By selectCampaignLocator = By.xpath("//div[@id='s2id_select_campaign']/a");
    private By campaignInputLocator = By.xpath("//div[@id='select2-drop']/div[@class='select2-search']/input");
    private By campaignResultLocator = By.xpath("//div[@id='select2-drop']/ul/li[1]/div/span[contains(text(),'SFA')]");
    // Brand Name Field Locator
    private By selectbrandNameLocator = By.xpath("//div[@id='s2id_account_Advertiser']/a");
    private By brandNameInputLocator = By.xpath("//div[@id='select2-drop']/div/input");
    private By brandNameResultLocator = By.xpath("//div[@id='select2-drop']/ul/li[2]/div");
    // Project Owner Field Locator
    private By projectOwnerLocator = By.xpath("//div[@id='s2id_account_SalesRep']/a");
    private By projectOwnerInputLocator=By.xpath("//div[@id='select2-drop']/div/input");
    private By projectOwnerResultLocator=By.xpath("//div[@id='select2-drop']/ul/li[1]/div");

    private By instructionsLocator=By.id("copyinstructions");
    private By teamInputLocator = By.id("metadata_2");
    private By jobTypeSelectLocator = By.id("account_JobTypeId");
    private By projectSubTypeSelectLocator = By.id("account_JobSubTypeId");
    private By jobCategorySelectLocator = By.id("mf_Jobcategory");
    private By columnsSelectLocator = By.id("mf_columns");
    private By widthFieldLocator=By.id("coltypeid");
    private By heightFieldLocator=By.id("HeightId");
    private By productionStatusSelectLocator = By.id("mf_ProductionStatus");
    private By colorSelectorLocator = By.id("metadata_2");
    private By creativeLevelSelectLocator = By.xpath("//select[@id='mf_creative_level']");
    private By prioritySelectLocator = By.id("account_PriorityId");
    private By fileUploadInputCNPLocator = By.xpath("//img[@src='https://devso.mediaferry.com/mf-s49qa/sitetheme_new/image/popup.png']/following-sibling::input");
    private By fileUploadLocator = By.id("file_attachedId1");
    private By newFileUploadLocator = By.xpath("//img[@src='https://devso.mediaferry.com/mf-s49qa/sitetheme_new/image/popup2.png']/following-sibling::input");
    private By submitBtnLocator=By.id("btn-submit");
    private By assetsUploadedSuccessfullyLocator = By.xpath("//table[@id='filesContainer']/tbody/tr");
    private By positionLocator=By.id("metadata_1");
    private By fpStyleLocator= By.xpath("//input[@id='metadata_3']");
    private By targetLocator= By.xpath("//input[@id='metadata_8']");
    private By saveBtnLocator= By.id("save_view_edit");
    private By proofPagestLocator = By.xpath("//select[@id='metadata_13']");
    private By classificationLocator = By.xpath("//select[@id='account_WebSubTypeId']");
    private By jobSubTypeLocator = By.xpath("//select[@id='account_SizeDefinitionId']");
    private By orderValueLocator = By.xpath("//input[@id='metadata_6']");
    private By finishedArtworkLocator = By.xpath("//li[@id='Proof']/a");
    private By proofBtnLocator = By.xpath("//span[text()='Proof']");
    private By yesPopUpBtnLocator = By.xpath("//div[@class='bootbox modal fade in']//button[text()='Yes']");
    private By spinnerLocator = By.xpath("//img[@src='https://devso.mediaferry.com/mf-s49qa/sitetheme_new/img/loading-spinner-grey.gif']");
    private By closeBtnLocator = By.xpath("//div[@id='close']");
    private By spinner = By.xpath("//img[@src='./../assets/images/loading-spinner-grey.gif']");
    private By stickyNotesBtnLocator = By.xpath("//div[@id='annoStickyIcon']/i");
    private By warningAcceptanceLocator = By.xpath("//h4[text()='Warning']/.. /following-sibling::div[2]/button[1]");
    private By waitForDeleteButtonLocator = By.xpath("//span[text()='Delete']");
    private By waitForProofButtonLocator = By.xpath("//span[text()='Proof']");
    
    public void enterProjectName(WebDriver driver, String projectName) {
        waitForElementVisibility(driver, projectNameLocator);
        driver.findElement(projectNameLocator).sendKeys(projectName);
    }

    public void selectCampaign(WebDriver driver,String campaign){
        waitForElementVisibility(driver, selectCampaignLocator);
        driver.findElement(selectCampaignLocator).click();
        waitForElementVisibility(driver, campaignInputLocator);
        driver.findElement(campaignInputLocator).sendKeys(campaign);
        waitForElementVisibility(driver, campaignResultLocator);
        driver.findElement(campaignResultLocator).click();
    }

    public void selectBrandName(WebDriver driver,String brandName){
        waitForElementVisibility(driver, selectbrandNameLocator);
        driver.findElement(selectbrandNameLocator).click();
        waitForElementVisibility(driver, brandNameInputLocator);
        driver.findElement(brandNameInputLocator).sendKeys(brandName);
        waitForElementVisibility(driver, brandNameResultLocator);
        driver.findElement(brandNameResultLocator).click();
    }

    public void selectCreativeLevel(WebDriver driver,String creativeLevel){
        selectDropDownElement(driver,creativeLevelSelectLocator,creativeLevel,"Text");
    }

    public void fileUpload(WebDriver driver,String filePath){
        String fileUploaderPath = getWorkingDirectoryPath()+filePath;
        scrollToElement(driver,fileUploadLocator);
        driver.findElement(fileUploadLocator).sendKeys(fileUploaderPath);
    }

    public void selectPriority(WebDriver driver,String priority){
        selectDropDownElement(driver,prioritySelectLocator,priority,"Text");
    }

    public void enterProjectOwner(WebDriver driver, String projectOwner){
        waitForElementVisibility(driver, this.projectOwnerLocator);
        driver.findElement(this.projectOwnerLocator).click();
        waitForElementVisibility(driver, projectOwnerInputLocator);
        driver.findElement(projectOwnerInputLocator).sendKeys(projectOwner);
        waitForElementVisibility(driver, projectOwnerResultLocator);
        driver.findElement(projectOwnerResultLocator).click();
    }

    public void enterInstructions(WebDriver driver,String instructions){
        waitForElementVisibility(driver, instructionsLocator);
        driver.findElement(instructionsLocator).sendKeys(instructions);
    }

    public void enterTeam(WebDriver driver,String team){
        waitForElementVisibility(driver, teamInputLocator);
        driver.findElement(teamInputLocator).sendKeys(team);
    }

    public void selectJobType(WebDriver driver){
        scrollToElement(driver,jobTypeSelectLocator);
        selectDropDownElement(driver,jobTypeSelectLocator,"1","Index");
    }

    public void selectProjectSubType(WebDriver driver){
        waitForElementVisibility(driver, projectSubTypeSelectLocator);
        selectDropDownElement(driver,projectSubTypeSelectLocator,"1","Index");
    }

    public void selectJobCategory(WebDriver driver){
        waitForElementVisibility(driver, jobCategorySelectLocator);
        selectDropDownElement(driver,jobCategorySelectLocator,"1","Index");
    }

    public void waitForLoadingProofingWindow(WebDriver driver){
        try {
            waitForElementVisibility(driver, spinner);
            waitForElementInvisibility(driver, spinner);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void selectColumns(WebDriver driver){
        waitForElementVisibility(driver, columnsSelectLocator);
        selectDropDownElement(driver,columnsSelectLocator,"1","Index");
    }

    public void enterWidth(WebDriver driver,String projectwidth){
        waitForElementVisibility(driver, widthFieldLocator);
        driver.findElement(widthFieldLocator).sendKeys(projectwidth);
    }

    public void enterHeight(WebDriver driver,String projectheight){
        waitForElementVisibility(driver, heightFieldLocator);
        driver.findElement(heightFieldLocator).sendKeys(projectheight);
    }

    public void selectProductionStatus(WebDriver driver,String index){
        scrollToElement(driver, productionStatusSelectLocator);
        selectDropDownElement(driver,productionStatusSelectLocator,index,"Index");
    }

    public void productionStatusEdit(WebDriver driver) throws InterruptedException {
        Thread.sleep(3000);
        scrollDownToPage(driver);
        Select dropdown = new Select(driver.findElement(By.id("mf_ProductionStatus")));
        dropdown.selectByVisibleText("Ready for Proof");
    }

    public void clickFinishedArtwork(WebDriver driver){
        By finishedArtworkTabLocator = By.xpath("//a[text() = ' Finished Artwork ']");
        driver.findElement(finishedArtworkTabLocator).click();
    }

    public void waitSaveSuccessfully(WebDriver driver){
        By successSaveMsgLocator = By.xpath("//strong[text()='Success:']");
        waitForElementVisibility(driver, successSaveMsgLocator);
        waitForElementInvisibility(driver, successSaveMsgLocator);
    }

    public void selectColor(WebDriver driver, String index){
        selectDropDownElement(driver, colorSelectorLocator, index, "Index");
    }

    public void clickOnFinishedArtwork(WebDriver driver){
        waitForElementVisibility(driver, finishedArtworkLocator);
        driver.findElement(finishedArtworkLocator).click();
    }

    public void clickOnFinishedArtworkByScroll(WebDriver driver) throws InterruptedException {
        Thread.sleep(4000);
        scrollDownToPage(driver);
        waitForElementClickable(driver, finishedArtworkLocator);
        driver.findElement(finishedArtworkLocator).click();
    }

    public void clickOnYesBtn(WebDriver driver){
        waitForElementVisibility(driver, yesPopUpBtnLocator);
        driver.findElement(yesPopUpBtnLocator).click();
    }
    
    public void clickCreateNewVersion(WebDriver driver) {
    	By createNewVersionBtnLocator = By.xpath("//button[text()='Create New Version']");
    	waitForElementVisibility(driver, createNewVersionBtnLocator);
    	driver.findElement(createNewVersionBtnLocator).click();
    }
    
    public void clickReplaceBtn(WebDriver driver) {
    	By replaceBtnLocator = By.xpath("//button[text()='Replace']");
    	waitForElementVisibility(driver, replaceBtnLocator);
    	driver.findElement(replaceBtnLocator).click();
    }

    public void clickOnStickyNotesIcon(WebDriver driver){
        waitForElementVisibility(driver, stickyNotesBtnLocator);
        driver.findElement(stickyNotesBtnLocator).click();
    }

    public void clickOnCanvas(WebDriver driver){
        WebElement canvas = driver.findElement(By.id("page1"));
        Actions builder = new Actions(driver);
        Action drawAction = builder.moveToElement(canvas,511,243).click().build();
        drawAction.perform();
    }

    public void clickOnUploadBtn(WebDriver driver){
        By uploadBtnLocator = By.xpath("//a[@class='UploadBtn']");
        driver.findElement(uploadBtnLocator).click();
    }

    public void clickOnCloseBtn(WebDriver driver){
        driver.findElement(closeBtnLocator).click();
    }

    public void clickOnProofingButton(WebDriver driver) throws InterruptedException{
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Thread.sleep(1000);
        js.executeScript("javascript:window.scrollBy(0,1800)");
        Thread.sleep(1000);
        waitForElementVisibility(driver, proofBtnLocator);
        driver.findElement(proofBtnLocator).click();
    }

    public void clickOnSubmitButton(WebDriver driver){
        waitForElementVisibility(driver, submitBtnLocator);
        scrollToElement(driver, submitBtnLocator);
        driver.findElement(submitBtnLocator).click();
    }

    public void verifyJobUploadedSuccessfully(WebDriver driver, SoftAssert softAssert, String projectName){
        //By xpath = By.xpath("//span[contains(text(),'"+projectName+"')]");
        By xpath = By.xpath("//div[@id='successfulUpload']");
        waitForElementPresence(driver, xpath);
        softAssert.assertTrue(driver.findElement(xpath).isDisplayed());
    }

    public void fileUploadWhileCreating(WebDriver driver,String filePath){
        String fileUploaderPath = getWorkingDirectoryPath() + filePath;
        driver.findElement(fileUploadInputCNPLocator).sendKeys(fileUploaderPath);
    }

    public void verifyAssetsUploadedWhileCreating(WebDriver driver,SoftAssert softAssert){
        waitForElementPresence(driver, assetsUploadedSuccessfullyLocator);
        softAssert.assertTrue(driver.findElement(assetsUploadedSuccessfullyLocator).isDisplayed());
    }

    public void fileUploadWhileEditing(WebDriver driver, String filePath){
        String fileUploaderPath = getWorkingDirectoryPath()+filePath;
        driver.findElement(newFileUploadLocator).sendKeys(fileUploaderPath);
    }

    public void acceptingWarnings(WebDriver driver) throws InterruptedException {
        Thread.sleep(2000);
        waitForElementVisibility(driver, warningAcceptanceLocator);
        driver.findElement(warningAcceptanceLocator).click();
        // scrollToElement(driver, saveChangesBtnLocator);
        waitForElementVisibility(driver, waitForDeleteButtonLocator);
    }

    public void waitAfterUploadSpinner(WebDriver driver){
        By afterUploadSpinnerLocator = By.xpath("//img[@src='https://devso.mediaferry.com/mf-s40qa/sitetheme_new/img/loading-spinner-grey.gif']");
        waitForElementVisibility(driver, afterUploadSpinnerLocator);
        waitForElementInvisibility(driver, afterUploadSpinnerLocator);
    }

    public String getValidationMsg(WebDriver driver){
        By uploadSuccessMsgLocator = By.xpath("//div[@id='invalidErrorSMG']/div");
        waitForElementVisibility(driver, uploadSuccessMsgLocator);
        return driver.findElement(uploadSuccessMsgLocator).getText();
    }

    public void enterPosition(WebDriver driver, String position) {
        scrollToElement(driver,positionLocator);
        driver.findElement(positionLocator).sendKeys(position);
    }

    public void enterFpStyle(WebDriver driver, String fpStyle) {
        scrollToElement(driver,fpStyleLocator);
        driver.findElement(fpStyleLocator).sendKeys(fpStyle);
    }

    public void enterTarget(WebDriver driver, String target) {
        scrollToElement(driver,targetLocator);
        driver.findElement(targetLocator).sendKeys(target);
    }

    public void selectClassification(WebDriver driver)
    {
        waitForElementVisibility(driver, classificationLocator);
        selectDropDownElement(driver,classificationLocator,"1","Index");
    }

    public void selectJobSubType(WebDriver driver)
    {
        waitForElementVisibility(driver, jobSubTypeLocator);
        selectDropDownElement(driver,jobSubTypeLocator,"0","Index");
    }

    public void enterOrderValue(WebDriver driver, String orderValue) {
        scrollToElementWithoutWait(driver,orderValueLocator);
        driver.findElement(orderValueLocator).sendKeys(orderValue);
    }

    public void clickOnSaveBtn(WebDriver driver) {
        javaScriptClick(driver,saveBtnLocator);
    }

    public void waitForProofButton(WebDriver driver)
    {
    	waitForElementVisibility(driver, waitForProofButtonLocator);
    }
    
    public void scrollAndClickProofBtn(WebDriver driver){
        scrollToElement(driver, proofBtnLocator);
        waitForElementVisibility(driver, proofBtnLocator);
        driver.findElement(proofBtnLocator).click();
    }

    public void waitForSpinnerInvisibility(WebDriver driver) throws InterruptedException{
    	Thread.sleep(1000);
        waitForElementVisibility(driver, spinnerLocator);
        waitForElementInvisibility(driver, spinnerLocator);
    }

}
