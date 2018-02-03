package BaseClasses;

import java.io.File;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utilities.FileOperations;
import constants.*;

public class Driver {

	private String detectedOS = null;
	public static WebDriver webdriver = null;
	public final String USERNAME = "";
	public final String AUTOMATE_KEY = "";
	private String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

	public String getOperatingSystemType() {

		String OS = System.getProperty("os.name", "generic").toLowerCase();
		if ((OS.indexOf("mac") >= 0)) {
			detectedOS = "MacOS";
		} else if (OS.indexOf("win") >= 0) {
			detectedOS = "Windows";
		} else if (OS.indexOf("nux") >= 0) {
			detectedOS = "Linux";
		} else {
			detectedOS = "Other";
		}

		return detectedOS;
	}

	public String getProjectName() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "projectName");
	}

	public String getCampaign() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "campaign");
	} 

	public String getBrandName() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "brandname");
	}

	public String getCreativeLevel() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "creativeLevel");
	}

	public String getFilePath() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "filePath");
	}
	
	public String getSampleFilePath() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "filePath1");
	}
	
	public String getCreationFilePath() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "filePath2");
	}
	
	public String getGlobalFilePath() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "filePath3");
	}

	public String getLogoPath() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "logoPath");
	}

	public String getMyProfilePath() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "myProfilePath");
	}

	public String getPriority() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "priority");
	}

	public String getProjectOwner() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "projectOwner");
	}

	public String getfpStyle() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "fpStyle");
	}

	public String getTarget() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "target");
	}

	public String getClassificationNew() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "classificationNew");
	}

	public String getOrderValue() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "orderValue");
	}

	public String getBrowserType() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "browserName");
	}

	public String getUrl() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "url");
	}

	public String getEmail() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "mother_email");
	}

	public String getSignUpEmail() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "signupEmail");
	}

	public String getSharedWithMailOne(){
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "sharingMailOne");

	}

    public String getSharedWithMailTwo(){
        FileOperations fileOperations = new FileOperations();
        Constants constants = new Constants();
        return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "sharingMailTwo");

    }

	public WebDriver createDriver() throws IOException {
		String osType = getOperatingSystemType();
		String browserName = getBrowserType();

		if (browserName.equalsIgnoreCase("firefox") || browserName.equalsIgnoreCase("ff")) {
			System.setProperty("webdriver.gecko.driver", "src/../libs/geckodriver");
			if (osType.equals("Linux")) {
				System.setProperty("webdriver.gecko.driver", "src/../libs/geckodriver");
			} else if (osType.equals("MacOS")) {
				System.setProperty("webdriver.gecko.driver", "src/../libs/geckodrivermac");
			} else {
				System.setProperty("webdriver.gecko.driver", "src/../libs/geckodriver.exe");
			}
			webdriver = new FirefoxDriver();
			webdriver.manage().window().maximize();
		} else if (browserName.equalsIgnoreCase("chrome") || browserName.equalsIgnoreCase("ch")) {
			if (osType.equals("Linux")) {
				System.setProperty("webdriver.chrome.driver", "src/../libs/chromedriver");
			} else if (osType.equals("MacOS")) {
				System.setProperty("webdriver.chrome.driver", "src/../libs/chromedrivermac");
			} else {
				System.setProperty("webdriver.chrome.driver", "src/../libs/chromedriver.exe");
			}
			webdriver = new ChromeDriver();
			webdriver.manage().window().maximize();
		} else if (browserName.equalsIgnoreCase("internet explorer") || browserName.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", "src/../libs/IEDriverServer.exe");
			webdriver = new InternetExplorerDriver();
			webdriver.manage().window().maximize();
		} else if (browserName.equalsIgnoreCase("BrowserStack") || browserName.equalsIgnoreCase("bs")) {
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("browser", "Chrome");
			caps.setCapability("browser_version", "54");
			caps.setCapability("os", "WINDOWS");
			caps.setCapability("os_version", "8");
			caps.setCapability("browserstack.debug", "true");
			webdriver = new RemoteWebDriver(new java.net.URL(URL), caps);
		} else if (browserName.equalsIgnoreCase("edge") || browserName.equalsIgnoreCase("Microsoft edge")) {
			System.setProperty("webdriver.edge.driver", "src/../libs/MicrosoftWebDriver.exe");
			webdriver = new EdgeDriver();
			webdriver.manage().window().maximize();
		}
		return webdriver;
	}

	public String getSignupPassword() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "signuppasswword");
	}

	public void takeScreenshot(WebDriver driver, String name) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		Date currDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM_dd_yy_HH_mm_ss");
		String dateAndTime = dateFormat.format(currDate);

		try {
			FileUtils.copyFile(scrFile, new File("screenshots/" + name + "_" + dateAndTime + ".png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getUsername() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "username");
	}

    public String getProfileName() {
        FileOperations fileOperations = new FileOperations();
        Constants constants = new Constants();
        return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "profileName");
    }

	public String getPassword() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "password");
	}

	public String getChangePassword() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "changePassword");
	}

	public String getInvalidUsername() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "invalidUsername");
	}

	public String getInstructions() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "instructions");
	}

	public String getTeam() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "team");
	}

	public String getHeight() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "height");
	}

	public String getWidth() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "width");
	}

	public String getProjectOwnerNew() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "projectOwnerNew");
	}

	public String getPosition() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "position");
	}

	public String getUFilePath() {
		FileOperations fileOperations = new FileOperations();
		Constants constants = new Constants();
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "dropFilePath");
	}
}
