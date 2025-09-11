package com.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utility.Fallbackwithtimeout;
import com.utility.Handlewait;
import com.utility.PageTransitionWait;

public class PersonalInfo extends PageTransitionWait {

	WebDriver driver;
	WebDriverWait wait;
	private final By nextbutton = By.xpath("//button[@id='btn-next']"); // The 'Next' button used on most pages
	Handlewait hw = new Handlewait();
	Actions actions;
	Fallbackwithtimeout Fallbackwithtimeout;
	JavascriptExecutor js;
	public PersonalInfo(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		this.driver = driver;
		this.wait = wait;
		actions = new Actions(driver);
		Fallbackwithtimeout = new Fallbackwithtimeout();
		 js = (JavascriptExecutor) driver;
	}
	
	public void enterPersonalInfo(By firstNameLocator, By lastNameLocator) {
		System.out.println("Filling out Personal Details...");
		
        WebElement user_firstname = hw.waitforelementvisible(wait, firstNameLocator, driver);
        user_firstname.sendKeys("Ayan");
        driver.findElement(lastNameLocator).sendKeys("Banerjee");
        driver.findElement(nextbutton).click();
        waitForPageTransition();
	}
	public void enterEmail(By email)
	{
		WebElement user_email = hw.waitforelementvisible(wait, email, driver);
        user_email.sendKeys("ayanbanerjee@gmail.com");
        driver.findElement(nextbutton).click();
        waitForPageTransition();
	}
	public void enterSalary(By annualsalary)
	{
		System.out.println("Filling out Financial Information...");
        hw.waitforelementvisible(wait, annualsalary, driver).sendKeys("98754");
        driver.findElement(nextbutton).click();
        waitForPageTransition();
	}
	
	public void coverageAmount(By slider)
	{
		 WebElement sliderElement = hw.waitforelementvisible(wait, slider, driver);
	        actions.dragAndDropBy(sliderElement, 150, 0).perform();
	        System.out.println("Final Slider Value: " + sliderElement.getAttribute("aria-valuenow"));
	        driver.findElement(nextbutton).click();
	        waitForPageTransition();
	}
	public void DOB(By dob) {
		System.out.println("Filling out remaining Personal Details...");
        hw.waitforelementvisible(wait, dob, driver).sendKeys("05-08-2000");
        driver.findElement(nextbutton).click();
        waitForPageTransition();
	}
	public void gender(By gender)
	{
		hw.waitforelementvisible(wait, gender, driver).click();
        waitForPageTransition();
	}
	public void phoneno(By phone)
	{
		  hw.waitforelementvisible(wait, phone, driver).sendKeys("9193394277");
	        driver.findElement(nextbutton).click();
	        waitForPageTransition();
	}
	
	public void address(By Address,By canfindloc,By suggestionbar)
	{
		System.out.println("Filling out Address Information...");
        WebElement addressInput = hw.waitforelementvisible(wait, Address, driver);
        addressInput.click();
        actions.sendKeys(addressInput, "kol").perform();
        Fallbackwithtimeout.sleeptheThread(driver);

        // This minimize/maximize sequence is a workaround to force the UI to give the human mimic behabiour to trigger the google map  
        // and reliably display the address suggestion dropdown if it's flaky.
        driver.manage().window().minimize();
        Fallbackwithtimeout.sleeptheThread(driver);
        driver.manage().window().maximize();
        Fallbackwithtimeout.sleeptheThread(driver);

        // Try to select from auto-suggestions; if it fails, enter manually.
        try {
            WebElement parent = hw.waitforelementvisible(wait, suggestionbar, driver);  //wait for suggestions appear 
            List<WebElement> suggestions = parent.findElements(By.xpath(".//div"));
            if (suggestions.size() >= 3) {
                suggestions.get(2).click(); // Click the third suggestion
            }
            Fallbackwithtimeout.sleeptheThread(driver);
        } catch (Exception e) {
            System.out.println("Address suggestion failed. Entering address manually.");
            driver.findElement(canfindloc).click();
            driver.findElement(By.xpath("//input[@id='address-input-street']")).sendKeys("123 Test Street");
            driver.findElement(By.id("address-input-city")).sendKeys("Testville");
            driver.findElement(By.id("address-input-state")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@tabindex='0']//div[@id]"))).click();
            driver.findElement(By.id("address-input-zipcode")).sendKeys("10369");
        }

        // Agree to terms and continue.
        driver.findElement(By.id("address-check-auth-release-agree")).click();
        actions.scrollByAmount(0, 100).build().perform();
        driver.findElement(By.id("address-check-consent-business")).click();
        actions.scrollByAmount(0, 900).build().perform();    // show the next button so with action class I scroll the page and click 
        driver.findElement(nextbutton).click();
        waitForPageTransition();
	}
	public void physicaldetails(By height,By menuLocator)
	{
		// --- 8. Physical Details ---
        System.out.println("Filling out Physical Details...");
        hw.waitforelementvisible(wait, height, driver).click();         //wait to appear the height item and click to open the dropdown
        wait.until(ExpectedConditions.visibilityOfElementLocated(menuLocator));   // wait to open the height dropdown
        WebElement lastElementInList = driver.findElement(menuLocator);  
        actions.scrollToElement(lastElementInList).perform();  // click last element
        lastElementInList.click();

        driver.findElement(By.id("input-weight")).sendKeys("77");  ///select the weight
        driver.findElement(nextbutton).click();
        waitForPageTransition();
	}
	
	public void tenyearsmedicalhistory1()
	{
		//In the past ten years, or as indicated below, have you been treated for, or been diagnosed by a member
        js.executeScript("window.scrollBy(0, 900);");   //scroll to click the button
        driver.findElement(By.xpath("//label[@tabindex='11']")).click(); // "None of the above"
        driver.findElement(nextbutton).click();
        waitForPageTransition();
	}
	public void tenyearsmedicalhistory2()
	{
		//In the past ten years, or as indicated below, have you been treated for, or been diagnosed by a member
        WebElement label = driver.findElement(By.xpath("//label[@tabindex='12']"));
        js.executeScript("arguments[0].scrollIntoView(true);", label);
        label.click(); // Another "None of the above"
        driver.findElement(nextbutton).click();
        waitForPageTransition();
	}
	
	public void medicalinfo()
	{
		driver.findElement(By.xpath("//label[@tabindex='2']")).click(); // "No" to other medical reasons
	    waitForPageTransition();
	}
	public void medication()
	{
		System.out.println("Answering Medical History questions...");
        driver.findElement(By.xpath("//label[@tabindex='2']")).click(); // "No" to taking medication
        waitForPageTransition();
	}
	
}
