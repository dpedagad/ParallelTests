package tests;

import org.testng.annotations.Test;

import config.Configuration;

public class TestCases extends Configuration {

	@Test
	public void Run() throws InterruptedException {
	Thread.sleep(5000);
    driver.findElementByAccessibilityId("PLAYLIST").click();
    Thread.sleep(2000);
    
    Thread.sleep(2000);
    driver.findElementByAccessibilityId("ic play").click();
    Thread.sleep(2000);
    
    driver.findElementByAccessibilityId("ic next").click();
    Thread.sleep(5000);
    
    driver.findElementByAccessibilityId("ic pause").click();
    Thread.sleep(2000);
    

	}
	
}
