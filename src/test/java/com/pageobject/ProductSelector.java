package com.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utility.PageTransitionWait;

public class ProductSelector extends PageTransitionWait {

	WebDriver driver;
	WebDriverWait wait;
	private final By nextbutton = By.xpath("//button[@id='btn-next']"); // The 'Next' button used on most pages
	public ProductSelector(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		this.driver = driver;
		this.wait = wait;
	}
	
	public void selectProduct(By product) {
		driver.findElement(product).click();
        driver.findElement(nextbutton).click();
        waitForPageTransition();
	}

}
