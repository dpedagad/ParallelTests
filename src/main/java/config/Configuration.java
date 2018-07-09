package config;

import java.io.File;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Configuration {
	
protected IOSDriver<IOSElement> driver = null;
	
	protected AppiumDriverLocalService service;
	
	@Parameters({"wda", "pfv", "iDevice", "port", "NCT", "UDID"})
	@BeforeTest(alwaysRun = true)
	public void setUP(int wda, String pfv, String iDevice, String port, int NCT, String UDID) throws Exception {
		DesiredCapabilities caps = new DesiredCapabilities();
		 service = new AppiumServiceBuilder().usingPort(Integer.valueOf(port)).build();
		service.start();
		
		if (service==null || !service.isRunning()) {
			throw new AppiumServerHasNotBeenStartedLocallyException("An Appium server node is not started");
		}
//		
//		if (!WDAServer.getInstance().isRunning()) {
//		       WDAServer.getInstance().restart();
//		   }
		
		//String url = "http://127.0.0.1:4723/wd/hub";
		
//		File app = new File(pathname = "/Users/pmasur/Downloads/Abercrombie.ipa");
		
		caps.setCapability("platformName", "iOS");
		caps.setCapability("deviceName", iDevice);
		caps.setCapability("platformVersion", pfv);
		caps.setCapability("browserName", "");
		//caps.setCapability("deviceOrientation", "PORTRAIT");		    
		caps.setCapability("bundleId", "com.abercrombie.anfiphone.dev");
		caps.setCapability("automationName", "XCUITest");
		caps.setCapability("clearSystemFiles", true);
		caps.setCapability("newCommandTimeout",NCT);
		caps.setCapability("showXcodeLog", true);
		caps.setCapability(IOSMobileCapabilityType.WDA_LOCAL_PORT, wda);
		caps.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT	, 5000);
		//caps.setCapability("app", "/Users/pmasur/Downloads/Abercrombie.ipa");
		caps.setCapability("udid", UDID);
		caps.setCapability("keychainPath", "/Users/pmasur/Library/Keychains/iOSkeychain.keychain-db");
		caps.setCapability("keychainPassword", "Anf1Hco2");
		 
			  driver = new IOSDriver<IOSElement>(service.getUrl(), caps);

	}
	
	@AfterTest(alwaysRun = true)
	public void tearDown() {
	if (driver != null) {
        driver.quit();
        }
	if (service!=null) {
		service.stop();
	}
	}


}
