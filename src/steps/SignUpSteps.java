package steps;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import pages.LoginPage;
import pages.SignUpPage;

public class SignUpSteps {

    SignUpPage signUpPage = new SignUpPage();
    LoginPage loginPage = new LoginPage();

    public void verifyAllMandatoryFields(WebDriver driver, SoftAssert softAssert) {
        loginPage.clickOnTheCreateAnAccountLink(driver);
        signUpPage.clickOnSignupButton(driver);
        softAssert.assertEquals(signUpPage.getFullNameValidationMessage(driver), "Full name is required.");
        softAssert.assertEquals(signUpPage.getCompanyNameValidationMessage(driver), "Company name is required.");
        softAssert.assertEquals(signUpPage.getEmailidValidationMessage(driver), "Email ID is required.");
        softAssert.assertEquals(signUpPage.getPasswordValidationMessage(driver), "Password is required.");
    }

    public void fillSignupDetails(WebDriver driver, SoftAssert softAssert, String fullname, String companyName,
                                  String country, String email, String password) {
        signUpPage.enterFullName(driver, fullname);
        signUpPage.enterCompanyAndOrganization(driver, companyName);
        signUpPage.selectCountry(driver, country);
        signUpPage.enterEmail(driver, email);
        signUpPage.enterPassword(driver, password);
        signUpPage.clickOnSignupButton(driver);
        signUpPage.isThanksMessageAfterRegisterDisplayed(driver,softAssert);
    }

    public void fillSignupDeatilsWithRegisterAccount(WebDriver driver, SoftAssert softAssert, String fullname, String companyName,
                                                     String country, String email, String password) {
        loginPage.clickOnTheCreateAnAccountLink(driver);
        signUpPage.enterFullName(driver, fullname);
        signUpPage.enterCompanyAndOrganization(driver, companyName);
        signUpPage.selectCountry(driver, country);
        signUpPage.enterEmail(driver, email);
        signUpPage.enterPassword(driver, password);
        signUpPage.clickOnSignupButton(driver);
        signUpPage.verifyMessageAlreadyRegisterBythisEmail(driver,softAssert);
    }

}
