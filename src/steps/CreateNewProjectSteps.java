package steps;

import BaseClasses.BasePage;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import pages.ActiveProjectsPage;
import pages.CreateNewProjectPage;
import pages.HomePage;
import utilities.UtilityMethods;

public class CreateNewProjectSteps {

    CreateNewProjectPage createNewProjectPage = new CreateNewProjectPage();
    UtilityMethods utilityMethods = new UtilityMethods();
    HomePage homePage = new HomePage();

    public void fillingDetails(WebDriver driver, String projectName, String campaign, String brandName, String creativeLevel, String filePath, String priority, String projectowner, String instructions, String team, String projectwidth, String projectheight){
        createNewProjectPage.enterProjectName(driver, projectName);
        createNewProjectPage.selectCampaign(driver, campaign);
        createNewProjectPage.selectBrandName(driver, brandName);
        createNewProjectPage.selectCreativeLevel(driver, creativeLevel);
        createNewProjectPage.fileUpload(driver, filePath);
        createNewProjectPage.selectPriority(driver, priority);
        createNewProjectPage.enterProjectOwner(driver, projectowner);
        createNewProjectPage.enterInstructions(driver, instructions);
        createNewProjectPage.enterTeam(driver, team);
        createNewProjectPage.selectJobType(driver);
        createNewProjectPage.selectProjectSubType(driver);
        createNewProjectPage.selectJobCategory(driver);
        createNewProjectPage.selectColumns(driver);
        createNewProjectPage.enterWidth(driver, projectwidth);
        createNewProjectPage.enterHeight(driver, projectheight);
        createNewProjectPage.selectProductionStatus(driver, "1");
        createNewProjectPage.selectColor(driver, "2");
    }

    public void fillingDetailsWithoutUploadingAssets(WebDriver driver, String projectName, String campaign, String brandName, String creativeLevel, String priority, String projectowner, String instructions, String team, String projectwidth, String projectheight){
        createNewProjectPage.enterProjectName(driver, projectName);
        createNewProjectPage.selectCampaign(driver, campaign);
        createNewProjectPage.selectBrandName(driver, brandName);
        createNewProjectPage.selectCreativeLevel(driver, creativeLevel);
        createNewProjectPage.selectPriority(driver, priority);
        createNewProjectPage.enterProjectOwner(driver, projectowner);
        createNewProjectPage.enterInstructions(driver, instructions);
        createNewProjectPage.enterTeam(driver, team);
        createNewProjectPage.selectJobType(driver);
        createNewProjectPage.selectProjectSubType(driver);
        createNewProjectPage.selectJobCategory(driver);
        createNewProjectPage.selectColumns(driver);
        createNewProjectPage.enterWidth(driver, projectwidth);
        createNewProjectPage.enterHeight(driver, projectheight);
        createNewProjectPage.selectProductionStatus(driver, "1");
        createNewProjectPage.selectColor(driver, "2");
    }

    public void changeProductionStatus(WebDriver driver) throws InterruptedException {
        Thread.sleep(2000);
        createNewProjectPage.selectProductionStatus(driver, "13");
        createNewProjectPage.clickOnFinishedArtwork(driver);
    }

    public void uploadFinishedArtwork(WebDriver driver, SoftAssert softAssert, String filePath, String validationMsg) throws InterruptedException {
        createNewProjectPage.clickOnFinishedArtwork(driver);
        createNewProjectPage.fileUploadWhileEditing(driver, filePath);
        createNewProjectPage.acceptingWarnings(driver);
        createNewProjectPage.acceptingWarnings(driver);
        createNewProjectPage.waitAfterUploadSpinner(driver);
        verifyAssetsUpload(driver, softAssert, validationMsg);
    }

    public void verifyAssetsUpload(WebDriver driver, SoftAssert softAssert, String validationMsg){
        softAssert.assertEquals(createNewProjectPage.getValidationMsg(driver), validationMsg);
    }

    public void createNewVersion(WebDriver driver) {
    	createNewProjectPage.clickCreateNewVersion(driver);    	
    }
    
    public void replace(WebDriver driver) {
    	createNewProjectPage.clickReplaceBtn(driver);
    }
    
    public void acceptingUpload(WebDriver driver) throws InterruptedException{
        Thread.sleep(2000);
    	createNewProjectPage.clickOnYesBtn(driver);
    }

    public void draggingStickyNotesOnUploadedAsset(WebDriver driver){
    	driver.manage().window().maximize();
        createNewProjectPage.waitForLoadingProofingWindow(driver);
        createNewProjectPage.clickOnStickyNotesIcon(driver);
        createNewProjectPage.clickOnCanvas(driver);
        createNewProjectPage.clickOnUploadBtn(driver);
    }

    public void closePopingWindow(WebDriver driver){
        createNewProjectPage.clickOnCloseBtn(driver);
    }

    public void submittingJob(WebDriver driver, SoftAssert softAssert, String projectName) throws InterruptedException{
        createNewProjectPage.clickOnSubmitButton(driver);
        createNewProjectPage.verifyJobUploadedSuccessfully(driver, softAssert, projectName);
        createNewProjectPage.waitForSpinnerInvisibility(driver);
    }

    public void uploadingAssetsWhileCreatingJob(WebDriver driver, SoftAssert softAssert, String filePath) throws InterruptedException {
        createNewProjectPage.fileUploadWhileCreating(driver, filePath);
        createNewProjectPage.verifyAssetsUploadedWhileCreating(driver,softAssert);
    }

    public void editProjectDetailsSteps(WebDriver driver, String projectOwnerNew, String position, String fpStyle, String target, String classificationNew, String orderValue, String uFilePath){
        createNewProjectPage.enterProjectOwner(driver, projectOwnerNew);
        createNewProjectPage.selectClassification(driver);
        createNewProjectPage.selectJobSubType(driver);
        createNewProjectPage.enterPosition(driver, position);
        createNewProjectPage.enterFpStyle(driver, fpStyle);
        createNewProjectPage.enterOrderValue(driver,orderValue);
        createNewProjectPage.enterTarget(driver, target);
        createNewProjectPage.fileUploadWhileEditing(driver, uFilePath);
    }

    public void updateStatus(WebDriver driver){
        createNewProjectPage.selectProductionStatus(driver, "1");
    }

    public void saveEditedDetails(WebDriver driver){
        createNewProjectPage.clickOnSaveBtn(driver);
    }

    public void openProofWindowWhileEditJob(WebDriver driver) throws InterruptedException {
        createNewProjectPage.scrollAndClickProofBtn(driver);
        utilityMethods.swichToWindow(driver, 1);
        createNewProjectPage.waitForLoadingProofingWindow(driver);
        createNewProjectPage.clickOnCloseBtn(driver);
        utilityMethods.swichToWindow(driver, 2);
    }

    public void editJobForProofing(WebDriver driver, SoftAssert softAssert, String filePath, String saveSuccessMessage, String uploadSuccessMessage) throws InterruptedException {
        createNewProjectPage.productionStatusEdit(driver);
        createNewProjectPage.clickOnSaveBtn(driver);
        verifyChangesSuccessfull(driver, softAssert, saveSuccessMessage);
        createNewProjectPage.clickOnFinishedArtworkByScroll(driver);
        createNewProjectPage.fileUploadWhileEditing(driver, filePath);
        createNewProjectPage.acceptingWarnings(driver);
        verifyUploadSuccessfull(driver, softAssert, uploadSuccessMessage);
        homePage.clickActiveProjectsLnkByScrollTop(driver);
    }

    public void verifyChangesSuccessfull(WebDriver driver, SoftAssert softAssert, String message){
        softAssert.assertEquals(createNewProjectPage.getSaveChangesSuccessMsg(driver), message);
    }

    public void verifyUploadSuccessfull(WebDriver driver, SoftAssert softAssert, String uploadSuccessMsg){
        softAssert.assertEquals(createNewProjectPage.getUploadSuccessMsg(driver), uploadSuccessMsg);
    }
}
