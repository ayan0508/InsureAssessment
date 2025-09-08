package com.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Handlewait {
	
	public static void waitfornextelemet(WebDriverWait wait, By buttondisable, By spinnerLocator)
	{
		wait.until(ExpectedConditions.attributeToBe(buttondisable, "disabled", "true"));
        wait.until(ExpectedConditions.presenceOfElementLocated(spinnerLocator));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(spinnerLocator));	
	}
	public static WebElement  waitforelementvisible(WebDriverWait wait,By element,WebDriver driver)
	{
		return wait.until(ExpectedConditions.visibilityOf(driver.findElement(element)));
	}
}
