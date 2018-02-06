package pages;

import BaseClasses.BasePage;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class HomePage extends BasePage {

    private By currentProjectCountLocator = By.id("tra");
    private By createNewProjectBtnLocator=By.xpath("//div[contains(@class, 'dashboard-stat') and contains(., ' Create new project')]");
    //private By activeProjectFieldLocator = By.xpath("//div[@class='number']/div");
    
    private By activeProjectFieldLocator = By.xpath("//div[@id='tra']/../../../..");
    private By homeLnkLocator = By.linkText("Home");
    private By activityFeedLnkLocator = By.linkText("Activity Feed");
    private By proofToReviewLnkLocator = By.xpath("//div[contains(@class, 'dashboard-stat') and contains(., ' Proofs to review')]");
    private By spinnerOnConstantLnk = By.xpath("//img[@src='https://devso.mediaferry.com/mf-s49qa/sitetheme_new/img/loading-spinner-grey.gif']");

    //img[@src='https://devso.mediaferry.com/mf-s49qa/sitetheme_new/img/loading-spinner-grey.gif']
    private By profileFieldLocator = By.id("profile");
    private By myProfileLnkLocator = By.xpath("//a[@class='UpdateProfile']");
    private By saveChangesBtnLocator = By.xpath("//button[contains(text(),'Save Changes')]");
    private By cancelBtnLocator = By.xpath("//button[contains(text(),'Save Changes')]/following-sibling::button");
    private By alertMsgLocator = By.xpath("//div[@id='successfulPinfo']");
    private By changePasswordLnkLocator = By.xpath("//a[text()=' Change Password '] ");
    private By currentPasswordFldLocator = By.id("currentPassword"); //input[@id='currentPassword']
    private By newPasswordFldLocator = By.id("newPassword"); //input[@id='newPassword']
    private By retypePasswordFldLocator = By.id("retypePassword"); //input[@id='retypePassword']
    private By changePasswordBtnLocator = By.xpath("//button[text()='Change Password']");
    private By cancelBtnCpLocator = By.xpath("//button[text()='Cancel']");
    private By alertMsgChangePassLocator = By.xpath("//div[@id='successfulPwd']");
    private By currentPwdValidationLocator = By.xpath("//span[@for='currentPassword']");
    private By newPwdValidationLocator = By.xpath("//span[@for='newPassword']");
    private By retypePwdValidationLocator = By.xpath("//span[@for='retypePassword']");

    private static Integer countBefore = null;
    private static Integer countAfter = null;

    public int getProjectCountBefore(WebDriver driver) {
        waitForElementVisibility(driver, currentProjectCountLocator);
        String projectCountBefore=driver.findElement(currentProjectCountLocator).getText();
        countBefore=Integer.parseInt(projectCountBefore);
        return countBefore;
    }

    public void clickOnCreateNewProject(WebDriver driver) throws Exception  {
        try{
            waitForElementVisibility(driver, By.id("loadOverlay"));
            waitForElementInvisibility(driver, By.id("loadOverlay"));}
        catch (TimeoutException e){
            e.getStackTrace();
        }
        driver.findElement(createNewProjectBtnLocator).click();
    }

    public void clickOnProofsForReview(WebDriver driver){
        driver.findElement(proofToReviewLnkLocator).click();
    }

    public int getProjectCountAfter(WebDriver driver) throws InterruptedException {
        Thread.sleep(15000);
        String projectCountAfter = driver.findElement(currentProjectCountLocator).getText();
        countAfter = Integer.parseInt(projectCountAfter);
        return countAfter;
    }

    public void clickActiveProjectsLnk(WebDriver driver){
        waitForElementVisibility(driver, activeProjectFieldLocator);
        driver.findElement(activeProjectFieldLocator).click();
    }

    public void clickActiveProjectsLnkByScrollTop(WebDriver driver){
        scrollTop(driver);
        waitForElementVisibility(driver, activeProjectFieldLocator);
        driver.findElement(activeProjectFieldLocator).click();
    }

    public void clickOnActiveProject(WebDriver driver) {
        try{
            waitForElementVisibility(driver, By.id("loadOverlay"));
            waitForElementInvisibility(driver, By.id("loadOverlay"));}
        catch (TimeoutException e){
            e.getStackTrace();
        }
        driver.findElement(activeProjectFieldLocator).click();
    }

    public void clickOnActiveProjectsLnkWithoutWait(WebDriver driver) throws InterruptedException{
    	Thread.sleep(3000);
    	waitForElementClickable(driver, activeProjectFieldLocator);	
    	//waitForElementVisibility(driver, activeProjectFieldLocator);
        driver.findElement(activeProjectFieldLocator).click();
    }

    public void clickOnHomeLink(WebDriver driver){
        driver.findElement(homeLnkLocator);
    }

    public void waitForLoadOverlay(WebDriver driver){
        waitForElementVisibility(driver, By.id("loadOverlay"));
        waitForElementInvisibility(driver, By.id("loadOverlay"));
    }

    public void waitingForConstantLinks(WebDriver driver){
        waitForElementVisibility(driver, spinnerOnConstantLnk);
        waitForElementInvisibility(driver, spinnerOnConstantLnk);
    }

    public void mouseHoverOnProfileName(WebDriver driver) {
        waitForElementVisibility(driver, profileFieldLocator);
        Actions action=new Actions(driver);
        action.moveToElement(driver.findElement(profileFieldLocator)).build().perform();
    }

    public void clickMyProfile(WebDriver driver){
        waitForElementVisibility(driver, myProfileLnkLocator);
        driver.findElement(myProfileLnkLocator).click();
    }

    public void clickOnChangePasswordLnk(WebDriver driver){
        waitForElementVisibility(driver, changePasswordLnkLocator);
        driver.findElement(changePasswordLnkLocator).click();
    }

    public void enterCurrentPassword(WebDriver driver, String currentPassword){
        waitForElementVisibility(driver, currentPasswordFldLocator);
        driver.findElement(currentPasswordFldLocator).sendKeys(currentPassword);
    }

    public void enterNewPassword(WebDriver driver, String newPassword){
        waitForElementVisibility(driver, newPasswordFldLocator);
        driver.findElement(newPasswordFldLocator).sendKeys(newPassword);
    }

    public void enterRetypePassword(WebDriver driver, String retypePassword){
        waitForElementVisibility(driver, retypePasswordFldLocator);
        driver.findElement(retypePasswordFldLocator).sendKeys(retypePassword);
    }

    public String getAlertMsgOnChangePassword(WebDriver driver){
        waitForElementVisibility(driver, alertMsgChangePassLocator);
        return driver.findElement(alertMsgChangePassLocator).getText();
    }

    public void clickOnChangePasswordBtn(WebDriver driver){
        waitForElementVisibility(driver, changePasswordBtnLocator);
        driver.findElement(changePasswordBtnLocator).click();
    }

    public void clickOnCancelBtnCp(WebDriver driver){
        waitForElementVisibility(driver, cancelBtnCpLocator);
        driver.findElement(cancelBtnCpLocator).click();
    }

    public String getValidationCurrentPwd(WebDriver driver){
        waitForElementVisibility(driver, currentPwdValidationLocator);
        return driver.findElement(currentPwdValidationLocator).getText();
    }

    public String getValidationNewPwd(WebDriver driver){
        waitForElementVisibility(driver, newPwdValidationLocator);
        return driver.findElement(newPwdValidationLocator).getText();
    }

    public String getValidationRetypePwd(WebDriver driver){
        waitForElementVisibility(driver, retypePwdValidationLocator);
        return driver.findElement(retypePwdValidationLocator).getText();
    }

    public void selectProfileImage(WebDriver driver, String myProfilePath) throws InterruptedException, FindFailed{
        String fileUploaderPath = getWorkingDirectoryPath() + myProfilePath;
        driver.findElement(By.id("file")).click();      
        Screen s = new Screen();
        Pattern usernamePattern = new Pattern(getWorkingDirectoryPath()+ "\\libs\\TypeFileName.png");
        Pattern openbuttonPattern = new Pattern(getWorkingDirectoryPath()+ "\\libs\\ClickOpenButton.png");
        s.wait(usernamePattern, 15);
        s.type(fileUploaderPath);
        s.click(openbuttonPattern);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[text()='Crop Image']")).click();
        Thread.sleep(5000);
       driver.findElement(saveChangesBtnLocator).click();
    }

    public String getSuccessfullAlert(WebDriver driver){
        waitForElementVisibility(driver, alertMsgLocator);
        return driver.findElement(alertMsgLocator).getText();
    }

    public void clickOnSaveChangesBtn(WebDriver driver){
        waitForElementVisibility(driver, saveChangesBtnLocator);
        driver.findElement(saveChangesBtnLocator).click();
    }

    public void clickOnCancelBtn(WebDriver driver){
        waitForElementVisibility(driver, cancelBtnLocator);
        driver.findElement(cancelBtnLocator).click();
    }

    public void clickOnActivityFeedLnk(WebDriver driver){
        driver.findElement(activityFeedLnkLocator).click();
    }

    public void waitingForHeaderLnks(WebDriver driver){
        try{
            waitForElementVisibility(driver, By.id("loadOverlay"));
            waitForElementInvisibility(driver, By.id("loadOverlay"));}
        catch (TimeoutException e){
            e.getStackTrace();
        }
    }

}
