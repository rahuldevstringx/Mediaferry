package steps;

import pages.ActiveProjectsPage;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
public class ApproveActionSteps {
	
 
	ActiveProjectsPage activeProjectsPage = new ActiveProjectsPage();
	
	public void approveActionSteps(WebDriver driver,SoftAssert softAssert,String compaign) throws InterruptedException {
		activeProjectsPage.checkProject(driver);
     	activeProjectsPage.clickApprove(driver);
	    activeProjectsPage.clickConfirmYes(driver);
	    activeProjectsPage.verifyProjectStatus(driver,softAssert,compaign);
	}

}
