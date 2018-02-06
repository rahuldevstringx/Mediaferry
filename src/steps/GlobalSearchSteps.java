package steps;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import pages.ActiveProjectsPage;
import pages.CreateNewProjectPage;
import pages.GlobalSearchPage;

public class GlobalSearchSteps {
	
	SoftAssert softAssert = new SoftAssert();
	String projectName = null;
	GlobalSearchPage globalSearchPage = new GlobalSearchPage();
	CreateNewProjectSteps createNewProjectSteps = new CreateNewProjectSteps();
	CreateNewProjectPage createNewProjectPage = new CreateNewProjectPage();
	UploadDownloadAssetsSteps uploadDownlaodAssetsSteps = new UploadDownloadAssetsSteps();
	ActiveProjectsPage activeProjectsPage = new ActiveProjectsPage();
			
	public void assetUpload(WebDriver driver,String filePath) throws InterruptedException {
		createNewProjectPage.fileUploadWhileCreating(driver, filePath);		
	}
	
	public void globalSearchStepsByProjectName(WebDriver driver,SoftAssert softAssert,String compaign,String asset,String projectName,String filePath, String sampleFilePath) throws InterruptedException 
	{	
		globalSearchPage.searchByCompaign(driver, compaign);
		globalSearchPage.searchByAsset(driver, asset);
		globalSearchPage.searchByProjectAndClick(driver, softAssert, projectName);
		uploadDownlaodAssetsSteps.uploadFileProofingWindowGlobalSearch(driver, filePath, sampleFilePath, softAssert);
		globalSearchPage.clickSaveChangesButton(driver);
	}

	public void globalSearchProjectSteps(WebDriver driver,SoftAssert softAssert, String projectName) throws InterruptedException
	{
		globalSearchPage.searchByProjectFromGlobalSearch(driver, softAssert, projectName);
		globalSearchPage.clickOnEmailLnk(driver);
	}

	public void emailProofFromGlobalSearch(WebDriver driver, String emailForProofing){
		activeProjectsPage.enterEmailForProofing(driver, emailForProofing);
		activeProjectsPage.clickOnArtworkCheckbox(driver);
		activeProjectsPage.clickOnEmailProofButton(driver);
		activeProjectsPage.waitSucessSentEmailProof(driver);
		activeProjectsPage.clickOnCloseProofButton(driver);
	}
}

