package com.cc.qa.util;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cc.qa.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class JavaScriptUtil extends TestBase {
	static JavascriptExecutor js = ((JavascriptExecutor) driver);
	private static AlertUtil alertUtil = new AlertUtil();

	public static void clickElementByJS(By locator, String text) {
		try {
			js.executeScript("arguments[0].click();", ElementUtil.getElement(locator));
			extentTest.log(LogStatus.INFO, "Clicked Successfully on : " + text);
			System.out.println("Clicked Successfully on : " + text);
			alertUtil.checkLoader();
		} catch (Exception e) {
			extentTest.log(LogStatus.FAIL, "Not able to click on :" + text);
			System.out.println("Not able to click on : " + text);
			throw new java.lang.RuntimeException("RUNTIME_ERROR : : Not able to click on: " + text);
		}
	}

	public static void refreshBrowserByJS() {
		try {
			js.executeScript("history.go(0)");
			extentTest.log(LogStatus.INFO, "Able to Refresh");
			System.out.println("Able to Refresh ");
		} catch (Exception e) {
			extentTest.log(LogStatus.FAIL, "Not able to Refresh");
			System.out.println("Not able to Refresh ");
			throw new java.lang.RuntimeException("RUNTIME_ERROR : : Not able to Refresh ");
		}
	}

	public static void scrollPageDown() {
		try {
			js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
			extentTest.log(LogStatus.INFO, "Scrolled down Successfully");
			System.out.println("Scrolled down Successfully ");
		} catch (Exception e) {
			extentTest.log(LogStatus.INFO, "Not able to scroll down");
			System.out.println("Not able to scroll down");
			throw new java.lang.RuntimeException("RUNTIME_ERROR : : Not able to Scroll down");
		}
	}

	public static void scrollIntoView(By locator, String text) {
		WebElement element = ElementUtil.getElement(locator);
		try {
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			extentTest.log(LogStatus.INFO, "Scrolled Successfully to" + text);
			System.out.println("Scrolled Successfully to" + text);
		} catch (Exception e) {
			extentTest.log(LogStatus.INFO, "Not able to scroll" + text);
			System.out.println("Not able to scroll" + text);
			throw new java.lang.RuntimeException("RUNTIME_ERROR : : Not able to Scroll to " + text);
		}
	}

	public static String getBrowserInfo() {
		String uAgent = null;
		try {
			uAgent = js.executeScript("return navigator.userAgent;").toString();
			extentTest.log(LogStatus.INFO, "Getting Browser Info Successfully");
			System.out.println("Getting Browser Info Successfully");
		} catch (Exception e) {
			extentTest.log(LogStatus.INFO, "Not able to get Browser Info");
			System.out.println("Not able to get Browser Info");
			throw new java.lang.RuntimeException("RUNTIME_ERROR : : Not able to get browser info");
		}
		return uAgent;
	}

	public static void sendKeysUsingJSWithID(String id, String value, String text) {
		try {
			js.executeScript("document.getElementById('" + id + "').value='" + value + "'");
			extentTest.log(LogStatus.INFO, "Text Entered Successfully : " + text);
			System.out.println("Text Entered Successfully : " + text);
		} catch (Exception e) {
			extentTest.log(LogStatus.INFO, "Not able to enter :" + text);
			System.out.println("Not able to enter : " + text);
			throw new java.lang.RuntimeException("RUNTIME_ERROR : : Not able to enter text " + text);
		}

	}

	

	public static void scrollTop() {

		try {
			alertUtil.checkLoader();
			js.executeScript("window.scrollBy(0,-document.body.scrollHeight)");
			Thread.sleep(3000);
			extentTest.log(LogStatus.INFO, "Scrolled Up Successfully");
			System.out.println("Scrolled Up Successfully ");
		} catch (Exception e) {
			extentTest.log(LogStatus.INFO, "Not able to scroll up");
			System.out.println("Not able to scroll up");
			throw new java.lang.RuntimeException("RUNTIME_ERROR : : Not able to scroll top");
		}
	}

	public static void scrollBottom() {

		try {
			alertUtil.checkLoader();
			js.executeScript("window.scrollBy(0,4000)");
			Thread.sleep(2000);
			extentTest.log(LogStatus.INFO, "Scrolled down Successfully");
			System.out.println("Scrolled down Successfully ");
		} catch (Exception e) {
			extentTest.log(LogStatus.INFO, "Not able to scroll down");
			System.out.println("Not able to scroll down");
			throw new java.lang.RuntimeException("RUNTIME_ERROR : : Not able to scroll down");
		}
	}


}
