package steps;

import org.openqa.selenium.WebDriver;
import org.sikuli.script.FindFailed;
import org.testng.asserts.SoftAssert;
import pages.HomePage;

public class HomeSteps {

    HomePage homePage = new HomePage();

    public int projectCountBefore(WebDriver driver){
        int projectCount = homePage.getProjectCountBefore(driver);
        return projectCount;
    }

    public void creatingNewProject(WebDriver driver) throws Exception {
        homePage.clickOnCreateNewProject(driver);
    }

    public int projectCountAfter(WebDriver driver) throws InterruptedException {
        int projectCount = homePage.getProjectCountAfter(driver);
        return projectCount;
    }

    public void goToActiveProjectsPage(WebDriver driver){
        homePage.clickOnActiveProject(driver);
    }

    public void navigateActiveProjectsPage(WebDriver driver){
        homePage.clickActiveProjectsLnk(driver);
    }

    public void goToActiveProjectsWithoutWait(WebDriver driver) throws InterruptedException{
        homePage.clickOnActiveProjectsLnkWithoutWait(driver);
    }

    public void goToActivityFeed(WebDriver driver){
        homePage.clickOnActivityFeedLnk(driver);
    }

    public void goToProofingWindow(WebDriver driver){
        homePage.clickOnProofsForReview(driver);
        homePage.waitingForConstantLinks(driver);
    }

    public void goToHomeLnkPage(WebDriver driver){
        homePage.clickOnHomeLink(driver);
        driver.navigate().refresh();
    }

    public void waitingForHeaderLnksSpinner(WebDriver driver){
        homePage.waitingForHeaderLnks(driver);
    }

    public void verifyProjectCount(WebDriver driver, SoftAssert softAssert, int projectCountBefore, int projectCountAfter){
        softAssert.assertEquals(projectCountBefore, projectCountAfter);
    }

    public void waitingForConstantLnksSpinner(WebDriver driver){
        homePage.waitingForConstantLinks(driver);
    }

    public void uploadProfileSteps(WebDriver driver, String myProfilePath) throws InterruptedException, FindFailed {
        Thread.sleep(2000);
    	homePage.mouseHoverOnProfileName(driver);
        homePage.clickMyProfile(driver);
        Thread.sleep(2000);
        homePage.selectProfileImage(driver, myProfilePath);
    }

    public void verifyProfileChange(WebDriver driver, SoftAssert softAssert, String expectedAlert){
        softAssert.assertEquals(homePage.getSuccessfullAlert(driver), expectedAlert);
    }

    public void saveChangesSuccessfully(WebDriver driver, SoftAssert softAssert, String expectedAlert){
        //homePage.clickOnSaveChangesBtn(driver);
        verifyProfileChange(driver, softAssert, expectedAlert);
        homePage.clickOnCancelBtn(driver);
    }

    public void goToChangePasswordWindow(WebDriver driver){
        homePage.mouseHoverOnProfileName(driver);
        homePage.clickMyProfile(driver);
        homePage.clickOnChangePasswordLnk(driver);
    }

    public void fillMandatoryFields(WebDriver driver, String currentPassword, String newPassword){
        homePage.enterCurrentPassword(driver, currentPassword);
        homePage.enterNewPassword(driver, newPassword);
        homePage.enterRetypePassword(driver, newPassword);
    }

    public void verifyPasswordChanged(WebDriver driver, SoftAssert softAssert, String expectedAlert){
        softAssert.assertEquals(homePage.getAlertMsgOnChangePassword(driver), expectedAlert);
    }

    public void saveChanges(WebDriver driver, SoftAssert softAssert, String alertMsg){
        homePage.clickOnChangePasswordBtn(driver);
        verifyPasswordChanged(driver, softAssert, alertMsg);
        homePage.clickOnCancelBtnCp(driver);
    }

    public void verifyValidationMsgs(WebDriver driver, SoftAssert softAssert, String validationMsg){
        homePage.clickOnChangePasswordBtn(driver);
        softAssert.assertEquals(homePage.getValidationCurrentPwd(driver), validationMsg);
        softAssert.assertEquals(homePage.getValidationNewPwd(driver), validationMsg);
        softAssert.assertEquals(homePage.getValidationRetypePwd(driver), validationMsg);
        homePage.clickOnCancelBtnCp(driver);
    }
}
