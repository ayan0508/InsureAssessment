package com.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utility.PageTransitionWait;

public class SignIn extends PageTransitionWait {
	
	WebDriver driver;
	WebDriverWait wait;
	
	private final By spinnerLocator = By.cssSelector("span[aria-label='spinner']"); // Global loading spinner
	JavascriptExecutor js;
	public SignIn(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		this.driver = driver;
		this.wait = wait;
		
		 js = (JavascriptExecutor) driver;
	}
	
	public void enterCredentials(By finalsubmit) {
		System.out.println("Submitting the application...");
        js.executeScript("window.scrollBy(0, 900);");
        driver.findElement(By.id("first_name")).sendKeys("ayan");
        driver.findElement(By.id("last_name")).sendKeys("Banerjee");
        driver.findElement(finalsubmit).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(spinnerLocator));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(spinnerLocator));

        System.out.println("Application submitted successfully!");
	}

}
