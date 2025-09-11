package com.testcase;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import com.pageobject.ApplicantType;
import com.pageobject.GetStarted;
import com.pageobject.PersonalInfo;
import com.pageobject.ProductSelector;
import com.pageobject.SignIn;


public class Testcases {
	 private WebDriver driver;
	 private WebDriverWait wait;
	 
	 
	 // General & Navigation
	    private final By spinnerLocator = By.cssSelector("span[aria-label='spinner']"); // Global loading spinnerprivate final By spinnerLocator = By.cssSelector("span[aria-label='spinner']"); // Global loading spinner
	    private final By finalsubmit = By.xpath("//button[@type='submit']"); // Final 'Submit' button on the last page
	    private final By getStartedBtnLocator = By.cssSelector("button[title='Get Started']");
	    private final By employeeOptionLocator = By.xpath("//input[@id='option-item-0']");
	    private final By product = By.xpath("//div[@id='label-item-3']");// Term Life Insurance option
	    private final By firstname = By.id("first_name");
	    private final By lastname = By.id("last_name");
	    private final By email = By.id("email-input");
	    private final By DOB = By.id("date-input");
	    private final By gender = By.xpath("//div[contains(text(),'Male')]");
	    private final By phone = By.id("input-phone");
	    private final By height = By.id("dropdown-height");
	    private final By menuLocator = By.xpath("//div[@role='menuitem'][last()-1]"); // A specific item in a dropdown menu
	    private final By annualsalary = By.id("input-number");
	    private final By slider = By.cssSelector(".rc-slider-handle"); // The handle of a slider control
	    private final By Address = By.id("address-input-autocomplete"); // Address auto-complete input
	    private final By suggestionbar = By.xpath("//div[@class='pac-container pac-logo hdpi']"); // Dropdown for address suggestions
	    private final By canfindloc = By.xpath("//div[@id='btn-toggle-autocomplete']"); // Button for manual address entry
	    
	@BeforeMethod
    public void setup()
    {
    	driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://d28j9pfwubj8q5.cloudfront.net/5U5PU/4oKeg/welcome");  // Navigate to the application's starting URL.
    }
	@Test()   //retryAnalyzer = RetryAnalyzer.class (for only retry one test cases )
    public void completeApplicationForm() {
		 
        // Wait for the initial page loader to disappear before proceeding.
        wait.until(ExpectedConditions.invisibilityOfElementLocated(spinnerLocator));

        GetStarted gs=new GetStarted(driver,wait);  // Click "Get Started" to begin the form.
        gs.clicktonext(getStartedBtnLocator);

        ApplicantType ap=new ApplicantType(driver,wait);  // ---  Initial Questions (Applicant type)---
        ap.selectApplicantType(employeeOptionLocator);

        ProductSelector ps=new ProductSelector(driver,wait);  //----Select product(s)----
        ps.selectProduct(product); // Select "Term Life Insurance"

        // ---  Personal Details (Your name)---
        PersonalInfo pi=new PersonalInfo(driver,wait);
        pi.enterPersonalInfo(firstname, lastname);
        pi.enterEmail(email); //---your email----
        pi.enterSalary(annualsalary); //---your annual salary---
        pi.coverageAmount(slider);// -----Select the amount of Basic Life coverage------.
        pi.DOB(DOB); // --- Date of Birth ---
        pi.gender(gender); //---gender---
        pi.phoneno(phone);
        pi.address(Address, canfindloc, suggestionbar);   // --- 7. Address Information with Fallback ---
        pi.physicaldetails(height, menuLocator);
        pi.tenyearsmedicalhistory1();//In the past ten years, or as indicated below, have you been treated for, or been diagnosed by a member
        pi.tenyearsmedicalhistory2();
        pi.medicalinfo();//Have you consulted, been advised or been examined by any healthcare provider for any oth...
        pi.medication();// --- Are you currently taking any medication? ---

        //Sign in
        SignIn si=new SignIn(driver,wait);
        si.enterCredentials(finalsubmit);
           
  }
        
	
	@AfterMethod
    public void teardown() {
        if (driver != null) {
           driver.quit();
        }
    }
    
}
