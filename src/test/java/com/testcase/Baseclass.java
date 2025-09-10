package com.testcase;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.utility.*;
public class Baseclass{
	///
	
    
	public static void main(String[] args) throws InterruptedException {
		
		By spinnerLocator = By.cssSelector("span[aria-label='spinner']");
	    By getStartedBtnLocator = By.cssSelector("button[title='Get Started']");
	    By employeeOptionLocator = By.xpath("//input[@id='option-item-0']");
	    By nextbutton = By.xpath("//button[@id='btn-next']");
	    By firstname=By.id("first_name");
	    By lastname=By.id("last_name");
	    By email=By.id("email-input");
	    By suggestionbar= By.xpath("//div[@class='pac-container pac-logo hdpi']");
	    By annualsalary= By.id("input-number");
	    By slider=By.cssSelector(".rc-slider-handle");
	    By DOB= By.id("date-input");
	    By gender=By.xpath("//div[contains(text(),'Male')]");
	    By phone=By.id("input-phone");
	    By canfindloc=By.xpath("//div[@id='btn-toggle-autocomplete']");
	    By Address=By.id("address-input-autocomplete");
	    By height=By.id("dropdown-height");
	    By menuLocator = By.xpath("//div[@role='menuitem'][last()-1]");
	    By finalsubmit=By.xpath("//button[@type='submit']");  ///submit
	    
	    
	    
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.get("https://d28j9pfwubj8q5.cloudfront.net/5U5PU/4oKeg/welcome"); //https://d28j9pfwubj8q5.cloudfront.net/5U5PU/4oKeg/UGFnZV8x
		
		//click on "start new application"
		
		System.out.println("Waiting for initial page load...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(spinnerLocator));
		// get started button
        WebElement getStartedBtn = wait.until(ExpectedConditions.elementToBeClickable(getStartedBtnLocator));
        getStartedBtn.click();
        
        //wait for button is disable and loading after the loader will come 
        wait.until(ExpectedConditions.attributeToBe(nextbutton, "disabled", "true"));
        
        //this is fallback mechanism with first duration is 30 sec and next duration is another 20 sec 
        Fallbackwithtimeout.untilWithFallback(driver, drv -> {
            boolean gone = ExpectedConditions.invisibilityOfElementLocated(spinnerLocator).apply(drv);
            return gone;
        });
        
		//enter the Employee 
        
        WebElement employeeOption = wait.until(ExpectedConditions.elementToBeClickable(employeeOptionLocator));
        employeeOption.click();
        
      //wait for button is disable and loading after the loader will come 
        wait.until(ExpectedConditions.attributeToBe(nextbutton, "disabled", "true"));
        
        //this is fallback mechanism with first duration is 30 sec and next duration is another 20 sec 
        Fallbackwithtimeout.untilWithFallback(driver, drv -> {
            boolean gone = ExpectedConditions.invisibilityOfElementLocated(spinnerLocator).apply(drv);
            return gone;
        });
       
        
        driver.findElement(By.xpath("//div[@id='label-item-3']")).click();
        driver.findElement(nextbutton).click();
      //wait for button is disable and loading after the loader will come 
        wait.until(ExpectedConditions.attributeToBe(nextbutton, "disabled", "true"));
        
        //this is fallback mechanism with first duration is 30 sec and next duration is another 20 sec 
        Fallbackwithtimeout.untilWithFallback(driver, drv -> {
            boolean gone = ExpectedConditions.invisibilityOfElementLocated(spinnerLocator).apply(drv);
            return gone;
        });
        
        
		// enter the first and last name 
        WebElement user_firstname=Handlewait.waitforelementvisible(wait, firstname, driver);
        WebElement user_lastname=driver.findElement(lastname);
        user_firstname.sendKeys("Ayan");
        user_lastname.sendKeys("Banerjee");
        driver.findElement(nextbutton).click();
      //wait for button is disable and loading after the loader will come 
        wait.until(ExpectedConditions.attributeToBe(nextbutton, "disabled", "true"));
        
        //this is fallback mechanism with first duration is 30 sec and next duration is another 20 sec 
        Fallbackwithtimeout.untilWithFallback(driver, drv -> {
            boolean gone = ExpectedConditions.invisibilityOfElementLocated(spinnerLocator).apply(drv);
            return gone;
        });
        
        
        
        
        WebElement user_email=Handlewait.waitforelementvisible(wait, email, driver);
        user_email.sendKeys("ayanbanerjee@gmail.com");
        driver.findElement(nextbutton).click();
      //wait for button is disable and loading after the loader will come 
        wait.until(ExpectedConditions.attributeToBe(nextbutton, "disabled", "true"));
        
        //this is fallback mechanism with first duration is 30 sec and next duration is another 20 sec 
        Fallbackwithtimeout.untilWithFallback(driver, drv -> {
            boolean gone = ExpectedConditions.invisibilityOfElementLocated(spinnerLocator).apply(drv);
            return gone;
        });
        
        
        
        WebElement salary =Handlewait.waitforelementvisible(wait, annualsalary, driver);
        salary.sendKeys("98754");
        driver.findElement(nextbutton).click();
      //wait for button is disable and loading after the loader will come 
        wait.until(ExpectedConditions.attributeToBe(nextbutton, "disabled", "true"));
        
        //this is fallback mechanism with first duration is 30 sec and next duration is another 20 sec 
        Fallbackwithtimeout.untilWithFallback(driver, drv -> {
            boolean gone = ExpectedConditions.invisibilityOfElementLocated(spinnerLocator).apply(drv);
            return gone;
        });
        
        
        WebElement sliderElement =Handlewait.waitforelementvisible(wait, slider, driver);
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(sliderElement, 150, 0).perform();
        String finalValue = sliderElement.getAttribute("aria-valuenow");
        System.out.println("Final Slider Value after moving: " + finalValue);
        driver.findElement(nextbutton).click();
      //wait for button is disable and loading after the loader will come 
        wait.until(ExpectedConditions.attributeToBe(nextbutton, "disabled", "true"));
        
        //this is fallback mechanism with first duration is 30 sec and next duration is another 20 sec 
        Fallbackwithtimeout.untilWithFallback(driver, drv -> {
            boolean gone = ExpectedConditions.invisibilityOfElementLocated(spinnerLocator).apply(drv);
            return gone;
        });
        
        
        
        
        WebElement Date_of_birth=Handlewait.waitforelementvisible(wait, DOB, driver);
        Date_of_birth.sendKeys("05-08-2000");
        driver.findElement(nextbutton).click();
      //wait for button is disable and loading after the loader will come 
        wait.until(ExpectedConditions.attributeToBe(nextbutton, "disabled", "true"));
        
        //this is fallback mechanism with first duration is 30 sec and next duration is another 20 sec 
        Fallbackwithtimeout.untilWithFallback(driver, drv -> {
            boolean gone = ExpectedConditions.invisibilityOfElementLocated(spinnerLocator).apply(drv);
            return gone;
        });
        
        
        
        
        WebElement gender_of_person=Handlewait.waitforelementvisible(wait, gender, driver);
        gender_of_person.click();
      //wait for button is disable and loading after the loader will come 
        wait.until(ExpectedConditions.attributeToBe(nextbutton, "disabled", "true"));
        
        //this is fallback mechanism with first duration is 30 sec and next duration is another 20 sec 
        Fallbackwithtimeout.untilWithFallback(driver, drv -> {
            boolean gone = ExpectedConditions.invisibilityOfElementLocated(spinnerLocator).apply(drv);
            return gone;
        });
        
        
        
        
        WebElement phone_no=Handlewait.waitforelementvisible(wait, phone, driver);
        phone_no.sendKeys("9193394277");
        driver.findElement(nextbutton).click();
      //wait for button is disable and loading after the loader will come 
        wait.until(ExpectedConditions.attributeToBe(nextbutton, "disabled", "true"));
        
        //this is fallback mechanism with first duration is 30 sec and next duration is another 20 sec 
        Fallbackwithtimeout.untilWithFallback(driver, drv -> {
            boolean gone = ExpectedConditions.invisibilityOfElementLocated(spinnerLocator).apply(drv);
            return gone;
        });
        
        
        
        
        WebElement address=Handlewait.waitforelementvisible(wait, Address, driver);
        address.click(); // Ensure focus
        actions.sendKeys(address, "kol").perform();
        Fallbackwithtimeout.sleeptheThread(driver);
        driver.manage().window().minimize(); 
        Fallbackwithtimeout.sleeptheThread(driver);
        driver.manage().window().maximize(); // Bring it back
        Fallbackwithtimeout.sleeptheThread(driver);
        WebElement parent = driver.findElement(suggestionbar);
        try {
        	
        	List<WebElement> suggestionElements=parent.findElements(By.xpath(".//div"));
        	List<WebElement> items= suggestionElements.size() >= 3 ? suggestionElements : null;
        	items.get(2).click();
        	Fallbackwithtimeout.sleeptheThread(driver);
        }
        catch(Exception e){
        	driver.findElement(canfindloc).click();
        	driver.findElement(By.xpath("//input[@id='address-input-street']")).sendKeys("test123");
        	driver.findElement(By.id("address-input-city")).sendKeys("Test city");
        	driver.findElement(By.id("address-input-state")).click();
        	By location_dropdown=By.xpath("//div[@tabindex='0']//div[@id]");
        	wait.until(ExpectedConditions.visibilityOfElementLocated(location_dropdown));
        	List<WebElement> items=driver.findElements(location_dropdown);
        	if (!items.isEmpty()) {
        	    items.get(0).click(); 
        	}
            driver.findElement(By.id("address-input-zipcode")).sendKeys("10369");
        }
        
        driver.findElement(By.id("address-check-auth-release-agree")).click();
        WebElement concent= driver.findElement(By.id("address-check-consent-business"));
        actions.scrollByAmount(0, 100).build().perform();
        concent.click();
        actions.scrollByAmount(0, 900).build().perform();
        WebElement cliclbutton=driver.findElement(nextbutton);
        cliclbutton.click();
      //wait for button is disable and loading after the loader will come 
        wait.until(ExpectedConditions.attributeToBe(nextbutton, "disabled", "true"));
        
        //this is fallback mechanism with first duration is 30 sec and next duration is another 20 sec 
        Fallbackwithtimeout.untilWithFallback(driver, drv -> {
            boolean gone = ExpectedConditions.invisibilityOfElementLocated(spinnerLocator).apply(drv);
            return gone;
        });
        
        
        
        
        WebElement heightButton = Handlewait.waitforelementvisible(wait, height, driver);
        heightButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(menuLocator));
        System.out.println("Menu is now visible.");
        WebElement lastElementInList = driver.findElement(menuLocator);
        actions.scrollToElement(lastElementInList).perform(); // This performs the scroll
        System.out.println("Successfully scrolled using Actions class.");
        lastElementInList.click();
        driver.findElement(By.id("input-weight")).sendKeys("77");
        driver.findElement(nextbutton).click();
        wait.until(ExpectedConditions.attributeToBe(nextbutton, "disabled", "true"));
        Fallbackwithtimeout.untilWithFallback(driver, drv -> {
            boolean gone = ExpectedConditions.invisibilityOfElementLocated(spinnerLocator).apply(drv);
            return gone;
        });
        
        
        
        //taking medication 
        driver.findElement(By.xpath("//label[@tabindex='2']")).click();
        Handlewait.waitfornextelemet(wait, nextbutton, spinnerLocator);
        
        
        //In the past ten years, or as indicated below, have you been treated for, or been diagnosed by a 
        js.executeScript("window.scrollBy(0, 900);");
        actions.scrollByAmount(0, 900).build().perform();
        WebElement non_of_above=driver.findElement(By.xpath("//label[@tabindex='13']"));
        non_of_above.click();
        driver.findElement(nextbutton).click();
        //Handlewait.waitfornextelemet(wait, nextbutton, spinnerLocator);
      //wait for button is disable and loading after the loader will come 
        wait.until(ExpectedConditions.attributeToBe(nextbutton, "disabled", "true"));
        
        //this is fallback mechanism with first duration is 30 sec and next duration is another 20 sec 
        Fallbackwithtimeout.untilWithFallback(driver, drv -> {
            boolean gone = ExpectedConditions.invisibilityOfElementLocated(spinnerLocator).apply(drv);
            return gone;
        });
        
        //In the past ten years, or as indicated below, have you been treated for, or been diagnosed by a member of the medical profession as having any of the following:
        
        
        js.executeScript("window.scrollBy(0, 900);");
        WebElement label = driver.findElement(By.xpath("//label[@tabindex='14']"));
        js.executeScript("arguments[0].scrollIntoView(true);", label);
        label.click();
        driver.findElement(nextbutton).click();
      //wait for button is disable and loading after the loader will come 
        wait.until(ExpectedConditions.attributeToBe(nextbutton, "disabled", "true"));
        
        //this is fallback mechanism with first duration is 30 sec and next duration is another 20 sec 
        Fallbackwithtimeout.untilWithFallback(driver, drv -> {
            boolean gone = ExpectedConditions.invisibilityOfElementLocated(spinnerLocator).apply(drv);
            return gone;
        });
        
        //Have you consulted, been advised or been examined by any healthcare provider for any other medical reason within the last ten years, or as previously indicated?
        driver.findElement(By.xpath("//label[@tabindex='2']")).click();
      //wait for button is disable and loading after the loader will come 
        wait.until(ExpectedConditions.attributeToBe(nextbutton, "disabled", "true"));
        
        //this is fallback mechanism with first duration is 30 sec and next duration is another 20 sec 
        Fallbackwithtimeout.untilWithFallback(driver, drv -> {
            boolean gone = ExpectedConditions.invisibilityOfElementLocated(spinnerLocator).apply(drv);
            return gone;
        });
        
        
        js.executeScript("window.scrollBy(0, 900);");
        driver.findElement(By.id("first_name")).sendKeys("ayan");
        driver.findElement(By.id("last_name")).sendKeys("Banerjee");
        //WebElement firstname=driver.findElement(By.id("first_name"));
        //WebElement lastname=driver.findElement(By.id("last_name"));
        //js.executeScript("arguments[0].value='Ayan';", firstname);
        //js.executeScript("arguments[0].value='Ayan';", lastname);

        driver.findElement(finalsubmit).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(spinnerLocator));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(spinnerLocator));
        
	}
		
}
