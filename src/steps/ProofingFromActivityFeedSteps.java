package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import pages.ActiveProjectsPage;
import pages.ProofingFromActivityFeedPage;

public class ProofingFromActivityFeedSteps {

	ProofingFromActivityFeedPage proofingFromActivityFeedPage = new ProofingFromActivityFeedPage();
	ActiveProjectsPage activeProjectsPage = new ActiveProjectsPage();
	

	public void proofingFromActivityFeed(WebDriver driver, SoftAssert softAssert, String projectName,String filepath) throws InterruptedException{
		proofingFromActivityFeedPage.clickOnActivityFeed(driver);	
		proofingFromActivityFeedPage.enterProjectNameInSearchField(driver, projectName);
		proofingFromActivityFeedPage.clickOnFilterBtn(driver);	
		activeProjectsPage.verifyFilterFunctionality(driver, softAssert, projectName);
		activeProjectsPage.mouseHoverAtCreatedPoject(driver, projectName);
		activeProjectsPage.clickOnProofLink(driver);
		proofingFromActivityFeedPage.proofingUpload(driver,filepath);
		
}
}
