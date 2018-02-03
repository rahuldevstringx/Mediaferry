package utilities;

import java.security.SecureRandom;
import java.util.Random;
import BaseClasses.BasePage;

import org.openqa.selenium.WebDriver;

public class UtilityMethods {
	
	/**
	 *@param len length of string
	 *@return random string according to the len size 
	 * */
	public String randomStringGenrator(int len){
		final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		SecureRandom rnd = new SecureRandom();
		StringBuilder sb = new StringBuilder( len );
		for( int i = 0; i < len; i++ ) 
			sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
		return sb.toString();


	}

	/**
	 * @param str Any string value that contains special character and numberic 
	 * @return numberOnly it return only numeric value from the str
	 */
	public int getOnlyNumber(String str) {
		int numberOnly=0;
		try {
			numberOnly= Integer.parseInt(str.replaceAll("[^0-9]", ""));
			return numberOnly;
		} catch (NumberFormatException e) {
			return numberOnly;
		}

	}

	public String createUniqueProjectName(String projectName){
		BasePage basePage = new BasePage();
		Random rand = new Random();
		int  projectSuffix = rand.nextInt(100000) + 1;
		String uniqueProjectName = projectName+ "  " + projectSuffix + "_"+ basePage.getCurrentDate();
		// update the project name in singleton list
		JobIdManager.getInstance().putJobId(uniqueProjectName, projectName);
		return uniqueProjectName;

	}
	
	public String enterUniqueFilterName(String enterText) {
		BasePage basePage = new BasePage();
		Random rand = new Random();
		int  textSuffix = rand.nextInt(10000) + 1;
		String uniqueFilterName = enterText+ "  " + textSuffix + "_"+ basePage.getCurrentDate();;
		JobIdManager.getInstance().putJobId(uniqueFilterName, enterText);
		
		return enterText;
		
	}
	
	/**
	 * <h1> Switch the browser window</h1>
	 * <p>
	 * This method is switch windows one
	 * @throws InterruptedException 
	 * */
	public void swichToWindow(WebDriver driver,int index) throws InterruptedException{
		Object[] wh = driver.getWindowHandles().toArray();
		driver.switchTo().window(wh[index].toString());
	}
	
	/**
	 * <h1> Switch the parent browser window</h1>
	 * <p>
	 * This method is switch windows one
	 * @throws InterruptedException 
	 * */
	public void swichToParentWindow(WebDriver driver ){ 
		driver.switchTo().defaultContent();
	}
	
	
}
