package com.utility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Fallbackwithtimeout {
	private static final long PRIMARY_TIMEOUT_SEC   = 20;
    private static final long FALLBACK_TIMEOUT_SEC  = 20;
    public static <T> T untilWithFallback(WebDriver driver,ExpectedCondition<T> condition) {
        try {
            return new WebDriverWait(driver, java.time.Duration.ofSeconds(PRIMARY_TIMEOUT_SEC)).until(condition);
        } 
        catch (TimeoutException primaryEx) {
            System.out.println("Primary wait of " + PRIMARY_TIMEOUT_SEC + "s timed out, retrying...");
            return new WebDriverWait(driver,java.time.Duration.ofSeconds(FALLBACK_TIMEOUT_SEC)).until(condition);
        }
    }
    public static void sleeptheThread(WebDriver driver)
    {
    	((JavascriptExecutor) driver).executeAsyncScript("const cb = arguments[arguments.length - 1]; setTimeout(cb, 1000);");
    }

}
