package steps;

import BaseClasses.BasePage;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import pages.LoginPage;

public class LoginSteps extends BasePage {

    LoginPage loginPage = new LoginPage();

    public void login(WebDriver driver, SoftAssert softAssert, String username, String password) {
        loginPage.enterUsername(driver, username);
        loginPage.enterPassword(driver, password);
        loginPage.clickOnLoginButton(driver);
        loginPage.verifyLogin(driver, softAssert);
    }

    public void logout(WebDriver driver, SoftAssert softAssert) throws InterruptedException {
        Thread.sleep(2000);
    	loginPage.mouseHoverOnProfileName(driver);
        loginPage.clickOnLogout(driver);
        loginPage.verifyLogout(driver, softAssert);
    }

    public void verifyValidation(WebDriver driver, SoftAssert softAssert){
        loginPage.checkValidationOfSession(driver, softAssert);
    }

    public void invalidLogin(WebDriver driver, SoftAssert softAssert, String invalidUsername, String password) {
        loginPage.enterUsername(driver, invalidUsername);
        loginPage.enterPassword(driver, password);
        loginPage.clickOnLoginButton(driver);
        loginPage.verifyInvalidLogin(driver, softAssert);
    }

}
