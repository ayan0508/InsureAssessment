package com.insure;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class RetryListener implements IAnnotationTransformer  {
	 @Override
	    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
	        // This method applies the RetryAnalyzer to all @Test methods
	        annotation.setRetryAnalyzer(RetryAnalyzer.class);
	    }
}
