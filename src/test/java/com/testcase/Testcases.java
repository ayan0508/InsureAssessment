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
import org.testng.annotations.*;
import com.insure.*;
import com.utility.Fallbackwithtimeout;
import com.utility.Handlewait;

public class Testcases {
	 private WebDriver driver;
	 private WebDriverWait wait;
	 private Actions actions;
	// private JavascriptExecutor js;
	 // General & Navigation
	    private static final By spinnerLocator = By.cssSelector("span[aria-label='spinner']"); // Global loading spinner
	    private static final By nextbutton = By.xpath("//button[@id='btn-next']"); // The 'Next' button used on most pages
	    private static final By finalsubmit = By.xpath("//button[@type='submit']"); // Final 'Submit' button on the last page
	    private static final By getStartedBtnLocator = By.cssSelector("button[title='Get Started']");
	    private static final By employeeOptionLocator = By.xpath("//input[@id='option-item-0']");
	    private static final By firstname = By.id("first_name");
	    private static final By lastname = By.id("last_name");
	    private static final By email = By.id("email-input");
	    private static final By DOB = By.id("date-input");
	    private static final By gender = By.xpath("//div[contains(text(),'Male')]");
	    private static final By phone = By.id("input-phone");
	    private static final By height = By.id("dropdown-height");
	    private static final By menuLocator = By.xpath("//div[@role='menuitem'][last()-1]"); // A specific item in a dropdown menu
	    private static final By annualsalary = By.id("input-number");
	    private static final By slider = By.cssSelector(".rc-slider-handle"); // The handle of a slider control
	    private static final By Address = By.id("address-input-autocomplete"); // Address auto-complete input
	    private static final By suggestionbar = By.xpath("//div[@class='pac-container pac-logo hdpi']"); // Dropdown for address suggestions
	    private static final By canfindloc = By.xpath("//div[@id='btn-toggle-autocomplete']"); // Button for manual address entry
	    
	@BeforeMethod
    public void setup()
    {
    	driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
       
        driver.get("https://d28j9pfwubj8q5.cloudfront.net/5U5PU/4oKeg/welcome");  // Navigate to the application's starting URL.
    }
	@Test()   //retryAnalyzer = RetryAnalyzer.class (for only retry one test cases )
    public void completeApplicationForm() {
		 JavascriptExecutor js = (JavascriptExecutor) driver;
        // --- 2. Welcome Page ---
        System.out.println("Starting application from the Welcome Page...");
        // Wait for the initial page loader to disappear before proceeding.
        wait.until(ExpectedConditions.invisibilityOfElementLocated(spinnerLocator));

        // Click "Get Started" to begin the form.
        WebElement getStartedBtn = wait.until(ExpectedConditions.elementToBeClickable(getStartedBtnLocator));
        getStartedBtn.click();
        waitForPageTransition(driver, wait);

        // ---  Initial Questions (Applicant type)---
        System.out.println("Answering initial questions...");
        WebElement employeeOption = wait.until(ExpectedConditions.elementToBeClickable(employeeOptionLocator));
        employeeOption.click();
        waitForPageTransition(driver, wait);

        //----Select product(s)----
        driver.findElement(By.xpath("//div[@id='label-item-3']")).click();
        driver.findElement(nextbutton).click();
        waitForPageTransition(driver, wait);

        // ---  Personal Details (Your name)---
        System.out.println("Filling out Personal Details...");
        WebElement user_firstname = Handlewait.waitforelementvisible(wait, firstname, driver);
        user_firstname.sendKeys("Ayan");
        driver.findElement(lastname).sendKeys("Banerjee");
        driver.findElement(nextbutton).click();
        waitForPageTransition(driver, wait);

        //---your email----
        WebElement user_email = Handlewait.waitforelementvisible(wait, email, driver);
        user_email.sendKeys("ayanbanerjee@gmail.com");
        driver.findElement(nextbutton).click();
        waitForPageTransition(driver, wait);

        // --- Annual Salary ---
        System.out.println("Filling out Financial Information...");
        Handlewait.waitforelementvisible(wait, annualsalary, driver).sendKeys("98754");
        driver.findElement(nextbutton).click();
        waitForPageTransition(driver, wait);

        // -----Select the amount of Basic Life coverage------.
        WebElement sliderElement = Handlewait.waitforelementvisible(wait, slider, driver);
        actions.dragAndDropBy(sliderElement, 150, 0).perform();
        System.out.println("Final Slider Value: " + sliderElement.getAttribute("aria-valuenow"));
        driver.findElement(nextbutton).click();
        waitForPageTransition(driver, wait);

        // --- Date of Birth ---
        System.out.println("Filling out remaining Personal Details...");
        Handlewait.waitforelementvisible(wait, DOB, driver).sendKeys("05-08-2000");
        driver.findElement(nextbutton).click();
        waitForPageTransition(driver, wait);

        //---gender---
        Handlewait.waitforelementvisible(wait, gender, driver).click();
        waitForPageTransition(driver, wait);

        //--phone no----
        Handlewait.waitforelementvisible(wait, phone, driver).sendKeys("9193394277");
        driver.findElement(nextbutton).click();
        waitForPageTransition(driver, wait);

        // --- 7. Address Information with Fallback ---
        System.out.println("Filling out Address Information...");
        WebElement addressInput = Handlewait.waitforelementvisible(wait, Address, driver);
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
            WebElement parent = Handlewait.waitforelementvisible(wait, suggestionbar, driver);  //wait for suggestions appear 
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
        waitForPageTransition(driver, wait);

        // --- 8. Physical Details ---
        System.out.println("Filling out Physical Details...");
        Handlewait.waitforelementvisible(wait, height, driver).click();         //wait to appear the height item and click to open the dropdown
        wait.until(ExpectedConditions.visibilityOfElementLocated(menuLocator));   // wait to open the height dropdown
        WebElement lastElementInList = driver.findElement(menuLocator);  
        actions.scrollToElement(lastElementInList).perform();  // click last element
        lastElementInList.click();

        driver.findElement(By.id("input-weight")).sendKeys("77");  ///select the weight
        driver.findElement(nextbutton).click();
        waitForPageTransition(driver, wait);

       

        //In the past ten years, or as indicated below, have you been treated for, or been diagnosed by a member
        js.executeScript("window.scrollBy(0, 900);");   //scroll to click the button
        driver.findElement(By.xpath("//label[@tabindex='11']")).click(); // "None of the above"
        driver.findElement(nextbutton).click();
        waitForPageTransition(driver, wait);

        //In the past ten years, or as indicated below, have you been treated for, or been diagnosed by a member
        WebElement label = driver.findElement(By.xpath("//label[@tabindex='12']"));
        js.executeScript("arguments[0].scrollIntoView(true);", label);
        label.click(); // Another "None of the above"
        driver.findElement(nextbutton).click();
        waitForPageTransition(driver, wait);

        //Have you consulted, been advised or been examined by any healthcare provider for any oth
        driver.findElement(By.xpath("//label[@tabindex='2']")).click(); // "No" to other medical reasons
        waitForPageTransition(driver, wait);
        
        // --- Are you currently taking any medication? ---
        System.out.println("Answering Medical History questions...");
        driver.findElement(By.xpath("//label[@tabindex='2']")).click(); // "No" to taking medication
        waitForPageTransition(driver, wait);

        // --- 10. Final Submission ---
        System.out.println("Submitting the application...");
        js.executeScript("window.scrollBy(0, 900);");
        driver.findElement(By.id("first_name")).sendKeys("ayan");
        driver.findElement(By.id("last_name")).sendKeys("Banerjee");

        driver.findElement(finalsubmit).click();

        // Wait for the final processing spinner to appear and then disappear.
        wait.until(ExpectedConditions.presenceOfElementLocated(spinnerLocator));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(spinnerLocator));

        System.out.println("Application submitted successfully!");
	}
	@AfterMethod
    public void teardown() {
        if (driver != null) {
         //   driver.quit();
        }
    }
    private static void waitForPageTransition(WebDriver driver, WebDriverWait wait) {
        wait.until(ExpectedConditions.attributeToBe(nextbutton, "disabled", "true"));
        Fallbackwithtimeout.untilWithFallback(driver,
                ExpectedConditions.invisibilityOfElementLocated(spinnerLocator));
    }

    
}
