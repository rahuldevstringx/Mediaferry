package steps;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import BaseClasses.BasePage;
import pages.ActivityFeedPage;
import utilities.MailReadWhileRespond;

public class ActivityFeedSteps extends BasePage{

    ActivityFeedPage activityFeedPage = new ActivityFeedPage();
    MailReadWhileRespond mailReadWhileRespond = new MailReadWhileRespond();

    public void uploadAssetsFromUploadFeedActivity(WebDriver driver, String projectName){
        activityFeedPage.clickOnUploadBtnFromFeedActivity(driver, projectName);
    }

    public void selectProject(WebDriver driver, SoftAssert softAssert, String projectName){
        activityFeedPage.enterProjectNameInSearchField(driver, projectName);
        activityFeedPage.clickOnFilterBtn(driver);
        activityFeedPage.verifyFilterFunctionality(driver, softAssert, projectName);
        scrollTop(driver);
    }


    public void goToUploadAssetsWindow(WebDriver driver) throws InterruptedException {
        activityFeedPage.mouseHoverOnActivityHeader(driver);
        activityFeedPage.clickOnUploadAssetsLnk(driver);
    }

    public void respondQueryViaEmailSteps(WebDriver driver, String expectedSubject, String response){
        String activationUrl = mailReadWhileRespond.readMails(expectedSubject);
        driver.get(activationUrl);
        activityFeedPage.waitForActivityFeedToLoad(driver);
        activityFeedPage.clickOnRespondToQueryLnk(driver);
        activityFeedPage.enterResponseTextArea(driver, response);
        activityFeedPage.clickOnRespondBtn(driver);
    }

    public void respondQueryViaEmailWhenNotLogin(WebDriver driver, String expectedSubject){
        String activationUrl = mailReadWhileRespond.readMails(expectedSubject);
        driver.get(activationUrl);
    }

    public void verifyStatus(WebDriver driver, SoftAssert softAssert){
        activityFeedPage.waitForActivityFeedToLoad(driver);
        activityFeedPage.verifyRespondStatus(driver, softAssert);
    }

    public void doQuerySteps(WebDriver driver, String projectName, String query) throws InterruptedException{
    	scrollTop(driver);
    	activityFeedPage.mouseHoverAtSelectedPoject(driver, projectName);
        Thread.sleep(2000);
        activityFeedPage.clickOnQueryLnk(driver);
        activityFeedPage.enterQuery(driver, query);
        activityFeedPage.clickOnQueryBtn(driver);
    }

    public void verifyStatusActivityFeed(WebDriver driver, SoftAssert softAssert, String status){
        softAssert.assertEquals(activityFeedPage.getStatus(driver), status);
    }

    public void verifyProfileNameWhichQuerying(WebDriver driver, SoftAssert softAssert, String profileName){
        softAssert.assertEquals(activityFeedPage.getProfileNameForQuery(driver), profileName);
    }

    public void verifyProfileNameWhichResponding(WebDriver driver, SoftAssert softAssert, String profileName){
        softAssert.assertEquals(activityFeedPage.getProfileNameForRespond(driver), profileName);
    }

    public void verifyActivityComment(WebDriver driver, SoftAssert softAssert, String profileName, String comment){
        softAssert.assertEquals(activityFeedPage.getProfileNameForComment(driver), profileName);
        softAssert.assertEquals(activityFeedPage.getComment(driver), comment);
    }

    public void doRespondSteps(WebDriver driver, String response){
        activityFeedPage.clickOnRespondToQueryLnk(driver);
        activityFeedPage.enterResponseTextArea(driver, response);
        activityFeedPage.clickOnRespondBtn(driver);
    }

    public void commentOnProjectSteps(WebDriver driver, String comment) throws InterruptedException {
        activityFeedPage.enterComment(driver, comment);
        activityFeedPage.clickOnPostCommentIcon(driver);
    }

}
