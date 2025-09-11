package com.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utility.PageTransitionWait;

public class GetStarted extends PageTransitionWait {

	WebDriver driver;
	WebDriverWait wait;
	public GetStarted(WebDriver driver,WebDriverWait wait)
	{
		super(driver,wait);
		this.driver=driver;
		this.wait=wait;
	}
	public void clicktonext(By getStartedBtnLocator)
	{
		WebElement getStartedBtn = wait.until(ExpectedConditions.elementToBeClickable(getStartedBtnLocator));
        getStartedBtn.click();
        waitForPageTransition();
	}
}
