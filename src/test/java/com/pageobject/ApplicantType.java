package com.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utility.PageTransitionWait;

public class ApplicantType extends PageTransitionWait {
	
	WebDriver driver;
	WebDriverWait wait;
	public ApplicantType(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		this.driver = driver;
		this.wait = wait;
	}

	public void selectApplicantType(By employeeOptionLocator) {
		System.out.println("Answering initial questions...");
        WebElement employeeOption = wait.until(ExpectedConditions.elementToBeClickable(employeeOptionLocator));
        employeeOption.click();
        waitForPageTransition();
	}
}
