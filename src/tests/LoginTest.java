package tests;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import BaseClasses.Driver;
import steps.LoginSteps;

public class LoginTest {

    Driver driverObj=new Driver();
    WebDriver driver=null;
    LoginSteps loginSteps=new LoginSteps();
    SoftAssert softAssert=new SoftAssert();

    @BeforeTest
    public void start() throws IOException{
        driver=driverObj.createDriver();
        driver.get(driverObj.getUrl());
    }

    @Test(priority=0)
    public void loginWithValidUserDetails() throws InterruptedException{
        loginSteps.login(driver,softAssert,driverObj.getUsername(),driverObj.getPassword());
        loginSteps.logout(driver,softAssert);
        softAssert.assertAll();
    }

    @Test(priority=1)
    public void loginWithInvalidUserDeatils(){
        loginSteps.invalidLogin(driver,softAssert,driverObj.getInvalidUsername(),driverObj.getPassword());
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
        driver=null;
    }
}
