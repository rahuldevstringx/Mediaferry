package steps;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import pages.ActiveProjectsPage;
import pages.ProofingFromActiveProjectsPage;
import pages.ProofingFromActivityFeedPage;

public class ProofingFromActiveProjectsSteps {
	
	ProofingFromActiveProjectsPage proofingFromActiveProjectsPage = new ProofingFromActiveProjectsPage();
	ProofingFromActivityFeedPage proofingFromActivityFeedPage = new ProofingFromActivityFeedPage();
	ActiveProjectsPage activeProjectsPage = new ActiveProjectsPage();
	
	public void proofingFromActiveProjects(WebDriver driver, SoftAssert softAssert, String projectName,String filepath) throws InterruptedException{
		proofingFromActiveProjectsPage.clickOnActiveProjects(driver);
		proofingFromActiveProjectsPage.typeInSearchField(driver, projectName);
		proofingFromActivityFeedPage.clickOnFilterBtn(driver);	
		activeProjectsPage.verifyFilterFunctionality(driver, softAssert, projectName);
		activeProjectsPage.mouseHoverAtCreatedPoject(driver, projectName);
		activeProjectsPage.clickOnProofLink(driver);
		proofingFromActivityFeedPage.proofingUpload(driver,filepath);
}
}

