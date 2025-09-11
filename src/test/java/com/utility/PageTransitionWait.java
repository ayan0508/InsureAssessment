package com.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageTransitionWait {
    private final By spinnerLocator = By.cssSelector("span[aria-label='spinner']"); // Global loading spinner
    private final By nextbutton = By.xpath("//button[@id='btn-next']"); // The 'Next' button used on most pages
	
	WebDriver driver;
	WebDriverWait wait;
	public  PageTransitionWait(WebDriver driver, WebDriverWait wait) {
		this.driver=driver;
		this.wait=wait;
	}
	 public void waitForPageTransition() {
	        wait.until(ExpectedConditions.attributeToBe(nextbutton, "disabled", "true"));
	        Fallbackwithtimeout fallbackUtil = new Fallbackwithtimeout(); 
	        fallbackUtil.untilWithFallback(driver,ExpectedConditions.invisibilityOfElementLocated(spinnerLocator));
	    }
}
