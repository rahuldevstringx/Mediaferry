package steps;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import pages.ActiveProjectsPage;
import utilities.MailCount;
import utilities.MailReadVerifyingProof;
import utilities.UtilityMethods;

public class ActiveProjectsSteps {

    ActiveProjectsPage activeProjectsPage = new ActiveProjectsPage();
    MailCount mailCount = new MailCount();
    UtilityMethods utilityMethods = new UtilityMethods();
    MailReadVerifyingProof mailReadVerifyingProof = new MailReadVerifyingProof();
    CreateNewProjectSteps createNewProjectSteps = new CreateNewProjectSteps();


    public void selectProject(WebDriver driver, SoftAssert softAssert, String projectName){
        activeProjectsPage.enterProjectNameInSearchField(driver, projectName);
        activeProjectsPage.clickOnFilterBtn(driver);
        activeProjectsPage.verifyFilterFunctionality(driver, softAssert, projectName);
    }

    public void selectProjectForEdit(WebDriver driver, SoftAssert softAssert, String projectName){
        activeProjectsPage.enterProjectNameInSearchField(driver, projectName);
        activeProjectsPage.clickOnFilterBtn(driver);
        activeProjectsPage.verifyFilterFunctionality(driver, softAssert, projectName);
        activeProjectsPage.mouseHoverAtCreatedPoject(driver, projectName);
        activeProjectsPage.clickOnViewOrEditLink(driver);
    }

    public void selectProjectForQuerying(WebDriver driver, SoftAssert softAssert, String projectName){
        activeProjectsPage.enterProjectNameInSearchField(driver, projectName);
        activeProjectsPage.clickOnFilterBtn(driver);
        activeProjectsPage.verifyFilterFunctionality(driver, softAssert, projectName);
        activeProjectsPage.mouseHoverAtCreatedPoject(driver, projectName);
        activeProjectsPage.clickOnQueryLink(driver);
    }

    public void statusVerificationOnClickCloseBtn(WebDriver driver, SoftAssert softAssert, String projectName){
        activeProjectsPage.mouseHoverAtCreatedPoject(driver, projectName);
        String statusBeforeCloseBtn = activeProjectsPage.getChangeStatusLnkText(driver);
        activeProjectsPage.clickOnQueryLink(driver);
        activeProjectsPage.clickOnCloseBtn(driver);
        activeProjectsPage.mouseHoverAtCreatedPoject(driver, projectName);
        softAssert.assertTrue(statusBeforeCloseBtn.contains(activeProjectsPage.getChangeStatusLnkText(driver)));
    }

    public void statusVerificationOnNotRespond(WebDriver driver, SoftAssert softAssert, String projectName) throws InterruptedException {
        activeProjectsPage.mouseHoverAtCreatedPoject(driver, projectName);
        boolean statusBeforeCloseBtn = activeProjectsPage.statusQueryToTrafficPresence(driver);
        activeProjectsPage.clickOnRespondToQuery(driver);
        activeProjectsPage.clickOnCloseBtnResponse(driver);
        activeProjectsPage.mouseHoverAtCreatedPoject(driver, projectName);
        boolean statusAfterCloseBtn = activeProjectsPage.statusQueryToTrafficPresence(driver);
        softAssert.assertEquals(statusBeforeCloseBtn, statusAfterCloseBtn);
    }

    public void sendQuerySteps(WebDriver driver, SoftAssert softAssert, String projectName,String query){
        activeProjectsPage.mouseHoverAtCreatedPoject(driver, projectName);
        activeProjectsPage.clickOnQueryLink(driver);
        activeProjectsPage.enterMessageInQueryBox(driver, query);
        activeProjectsPage.clickOnQueryBtn(driver);
    }

    public void verifyMailCount(WebDriver driver, SoftAssert softAssert, String projectName, int expectedCount) throws InterruptedException {
        Thread.sleep(3000);
        int actualCount = mailCount.FetchMails("imap.gmail.com", "qa.gaurav239@gmail.com", "result1029", "[FromName] EKCS has a query "+projectName);
        softAssert.assertEquals(actualCount, expectedCount);
    }

    public void openRespondWindowSteps(WebDriver driver) throws InterruptedException {
        activeProjectsPage.clickOnRespondToQuery(driver);
    }

    public void respondQuerySteps(WebDriver driver, String response){
        activeProjectsPage.enterMessageInResponseBox(driver, response);
        activeProjectsPage.clickOnRespondButton(driver);
    }

    public void openAndPressRespondSteps(WebDriver driver) throws InterruptedException {
        activeProjectsPage.clickOnRespondToQuery(driver);
        Thread.sleep(2000);
        activeProjectsPage.clickOnRespondButton(driver);
    }

    public void verifyValidationMsgOnRespondSteps(WebDriver driver, SoftAssert softAssert, String expectedValidation){
        String actualValidation = activeProjectsPage.getValidationOnTypeResponse(driver);
        softAssert.assertEquals(actualValidation, expectedValidation);
        activeProjectsPage.clickOnCloseBtnResponse(driver);
    }

    public void verifyingQueryLnkSteps(WebDriver driver, SoftAssert softAssert, String projectName, boolean ifCondition, boolean elseCondition){
        activeProjectsPage.mouseHoverAtCreatedPoject(driver, projectName);
        activeProjectsPage.verifyQueryLink(driver, softAssert, ifCondition, elseCondition);
    }

    public void verifyingStatusLnkSteps(WebDriver driver, SoftAssert softAssert, String projectName, boolean ifCondition, boolean elseCondition){
        activeProjectsPage.mouseHoverAtCreatedPoject(driver, projectName);
        activeProjectsPage.verifyStatusLink(driver, softAssert, ifCondition, elseCondition);
    }

    public void verifyValidationMessageOnQueryFields(WebDriver driver, SoftAssert softAssert){
        activeProjectsPage.clickOnQueryBtn(driver);
        activeProjectsPage.verifyValidationMessage(driver, softAssert);
        activeProjectsPage.clickOnCloseBtn(driver);
    }

    public void doQuery(WebDriver driver, String query){
        activeProjectsPage.enterQuery(driver, query);
        activeProjectsPage.clickOnQueryBtn(driver);
    }

    public void respondQuery(WebDriver driver, String response) throws InterruptedException {
        activeProjectsPage.clickOnRespondToQuery(driver);
        activeProjectsPage.enterResponseToQuery(driver, response);
    }

    public void verifyShareFunctionality(WebDriver driver, SoftAssert softAssert, String alertMessage){
        softAssert.assertEquals(activeProjectsPage.getSharedAlertMessage(driver), alertMessage);
    }

    public void shareProjectSteps(WebDriver driver, SoftAssert softAssert, String projectName, String sharedWithMailOne, String sharedWithEmailTwo, String alertMessage){
        activeProjectsPage.mouseHoverAtCreatedPoject(driver, projectName);
        activeProjectsPage.clickOnShareLink(driver);
        activeProjectsPage.enterSharingWithEmails(driver, sharedWithMailOne, sharedWithEmailTwo);
        activeProjectsPage.clickOnShareBtn(driver);
        verifyShareFunctionality(driver, softAssert, alertMessage);
        activeProjectsPage.clickOnShareWindowCloseBtn(driver);
    }

    public void openProofingWindowSteps(WebDriver driver, SoftAssert softAssert, String projectName) throws InterruptedException {
        selectProject(driver, softAssert, projectName);
        activeProjectsPage.mouseHoverAtCreatedPoject(driver, projectName);
        activeProjectsPage.clickOnProofLink(driver);
        utilityMethods.swichToWindow(driver, 1);
        activeProjectsPage.waitForLoadingProofingWindow(driver);
        activeProjectsPage.clickOnCloseBtn(driver);
        utilityMethods.swichToWindow(driver, 2);
    }

    public void emailProofFromGrid(WebDriver driver, String emailForProofing, String projectName){
        activeProjectsPage.mouseHoverAtCreatedPoject(driver, projectName);
        activeProjectsPage.clickOnEmailProofLink(driver);
        activeProjectsPage.enterEmailForProofing(driver, emailForProofing);
        activeProjectsPage.clickOnArtworkCheckbox(driver);
        activeProjectsPage.clickOnEmailProofButton(driver);
        activeProjectsPage.waitSucessSentEmailProof(driver);
        activeProjectsPage.clickOnCloseProofButton(driver);
    }

    public void verifyEmailProofSteps(WebDriver driver, String expectedSubject){
        String proofingUrl = mailReadVerifyingProof.readMails(expectedSubject);
        activeProjectsPage.verifyEmailProof(driver, proofingUrl);
        createNewProjectSteps.closePopingWindow(driver);
    }

    public void emailProofFromProofingWindow(WebDriver driver, SoftAssert softAssert, String email, String projectName){
        activeProjectsPage.mouseHoverAtCreatedPoject(driver, projectName);
        activeProjectsPage.clickOnProofLink(driver);
    }

    public void emailProofFromGlobalSearchSteps(WebDriver driver, SoftAssert softAssert, String email, String projectName){
        activeProjectsPage.mouseHoverAtCreatedPoject(driver, projectName);
        activeProjectsPage.clickOnProofLink(driver);
    }

    public void proofingEmailProof(WebDriver driver, String emailForProofing) throws InterruptedException
    {
        utilityMethods.swichToWindow(driver, 1);
        driver.manage().window().maximize();
        activeProjectsPage.waitForLoadingProofingWindow(driver);
        activeProjectsPage.clickEmailProofLnk(driver);
        activeProjectsPage.enterEmailForProofingOnProof(driver, emailForProofing);
        activeProjectsPage.clickOnArtworkCheckOnProof(driver);
        activeProjectsPage.clickOnEmailProofOnProofBtn(driver);
        activeProjectsPage.waitSucessSentEmailOnProof(driver);
        activeProjectsPage.clickOnCloseOnProofBtn(driver);
        createNewProjectSteps.closePopingWindow(driver);
        utilityMethods.swichToWindow(driver,0);
        Thread.sleep(2000);
    }

}
