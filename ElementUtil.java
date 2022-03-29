package com.cc.qa.util;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cc.qa.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class ElementUtil extends TestBase {
	private static TestUtil testutil = new TestUtil();
	private static AlertUtil alertUtil = new AlertUtil();

	public static WebElement getElement(By locator) {
		waitForElementPresent(locator);
		WebElement element = driver.findElement(locator);
		return element;
	}

	public static void waitForElementPresent(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	public static void waitForElementsVisible(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(locator)));
	}

	public static void waitForElementStaleness(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.or(ExpectedConditions.stalenessOf(ElementUtil.getElement(locator)),
				ExpectedConditions.elementToBeClickable(ElementUtil.getElement(locator)),
				ExpectedConditions.presenceOfAllElementsLocatedBy(locator)));

	}

	public static void doClick(By locator, String info) {
		try {
			getElement(locator).click();
			System.out.println("Clicked successfully on: " + info);
			extentTest.log(LogStatus.INFO, "Clicked successfully on: " + info);
			alertUtil.checkLoader();
		} catch (Exception e) {
			System.out.println("Not able to click on: " + info);
			testutil.takeScreenshot(info.replaceAll(" ", "_"));
			throw new java.lang.RuntimeException("RUNTIME_ERROR : : Not able to click on: " + info);
		}
	}

	public static void mouseOverOnElement(By locator, String info) {
		try {
			Actions action = new Actions(driver);
			// getWait().until(ExpectedConditions.visibilityOf(element));
			action.moveToElement(driver.findElement(locator)).build().perform();
			System.out.println("Mouse over successfull on : " + info);
			extentTest.log(LogStatus.INFO, "Mouse over successfull on : " + info);
		} catch (Exception e) {
			System.out.println("Not able to mouse over on: " + info);
			testutil.takeScreenshot(info.replaceAll("", "_"));
			throw new java.lang.RuntimeException("RUNTIME_ERROR : : Not able to mouse over on : " + info);
		}
	}

	public static void doSendKeys(By locator, String value, String info) {
		try {
			getElement(locator).sendKeys(value);
			System.out.println("Text entered successfully in: " + info);
			extentTest.log(LogStatus.INFO, "Text entered successfully in: " + info);
			alertUtil.checkLoader();
		} catch (Exception e) {
			System.out.println("Not able to enter text in: " + info);
			testutil.takeScreenshot(info.replaceAll(" ", "_"));
			throw new java.lang.RuntimeException("RUNTIME_ERROR : : Not able to enter text in: " + info);
		}
	}

	public static void clearAnddoSendKeys(By locator, String value, String info) {
		try {
			getElement(locator).clear();

			System.out.println("Text cleared successfully from: " + info);
			extentTest.log(LogStatus.INFO, "Text cleared successfully from: " + info);
			alertUtil.checkLoader();
			getElement(locator).sendKeys(value);
			System.out.println("Text entered successfully in: " + info);
			extentTest.log(LogStatus.INFO, "Text entered successfully in: " + info);
			alertUtil.checkLoader();
		} catch (Exception e) {
			System.out.println("Not able to enter text in: " + info);
			testutil.takeScreenshot(info.replaceAll(" ", "_"));
			throw new java.lang.RuntimeException("RUNTIME_ERROR : : Not able to enter text in: " + info);
		}
	}

	public static void clearTextFrom(By locator, String info) {
		try {
			getElement(locator).clear();
			System.out.println("Text cleared successfully from: " + info);
			extentTest.log(LogStatus.INFO, "Text cleared successfully from: " + info);
		} catch (Exception e) {
			System.out.println("Not able to clear text from: " + info);
			testutil.takeScreenshot(info.replaceAll(" ", "_"));
			throw new java.lang.RuntimeException("RUNTIME_ERROR : : Not able to clear text from: " + info);
		}
	}

	public static void tabout(By locator, String info) throws InterruptedException {
		try {
			getElement(locator).sendKeys(Keys.TAB);
			System.out.println("Tab out from :" + info);
			extentTest.log(LogStatus.INFO, " Tab out from :" + info);
			alertUtil.checkLoader();
		} catch (Exception e) {
			System.out.println("Not able to tab out from :" + info);
			testutil.takeScreenshot(info.replaceAll(" ", "_"));
			throw new java.lang.RuntimeException("RUNTIME_ERROR : : Not able to tab out from : " + info);
		}
	}

	public static String doGetText(By locator, String info) {
		String text = null;
		try {
			text = getElement(locator).getText();
			System.out.println("Text stored successfully from : " + info);
			extentTest.log(LogStatus.INFO, "Text stored successfully from : " + info);
		} catch (Exception e) {
			System.out.println(" Not able to get text from :" + info);
			testutil.takeScreenshot(info.replaceAll(" ", "_"));
			throw new java.lang.RuntimeException("RUNTIME_ERROR : : Not able to get text from : " + info);
		}
		return text;
	}

	public static String waitForPageTitle(String title) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();
	}

	public static boolean isElementDisplayed(By locator, String info) {
		boolean isDisplayed = false;
		try {
			isDisplayed = getElement(locator).isDisplayed();
			System.out.println("isDisplayed state stored successfully for: " + info);
			extentTest.log(LogStatus.INFO, "isDisplayed state stored successfully for: " + info);
		} catch (Exception e) {
			System.out.println(" Not able to get isDisplayed state of element " + info);
			testutil.takeScreenshot(info.replaceAll(" ", "_"));
			throw new java.lang.RuntimeException(
					"RUNTIME_ERROR : : Not able to get isDisplayed state of element : " + info);
		}
		return isDisplayed;
	}

	public static void selectByIndex(By locator, int index, String info) {
		try {
			WebElement elm = getElement(locator);
			Select select = new Select(elm);
			select.selectByIndex(index);
			System.out.println("Selected Successfully: " + info);
			extentTest.log(LogStatus.INFO, "Selected Successfully: " + info);
		} catch (Exception e) {
			System.out.println("Not able to Select: " + info);
			testutil.takeScreenshot(info.replaceAll(" ", "_"));
			throw new java.lang.RuntimeException("RUNTIME_ERROR : : Not able to Select : " + info);
		}
	}

	public static void selectByValue(By locator, String value, String info) {
		try {
			waitForElementPresent(locator);
			WebElement elm = getElement(locator);
			Select select = new Select(elm);
			select.selectByValue(value);
			System.out.println("Value selected successfully in: " + info);
			extentTest.log(LogStatus.INFO, "Value selected successfully in: " + info);
		} catch (Exception e) {
			System.out.println("Not able to Select: " + info);
			testutil.takeScreenshot(info.replaceAll(" ", "_"));
			throw new java.lang.RuntimeException("RUNTIME_ERROR : : Not able to select in: " + info);
		}
	}

	public static void selectByVisibleText(By locator, String text, String info) {
		try {
			waitForElementPresent(locator);
			WebElement elm = getElement(locator);
			Select select = new Select(elm);
			select.selectByVisibleText(text);
			System.out.println("Selected successfully in: " + info);
			extentTest.log(LogStatus.INFO, "Selected successfully in: " + info);
		} catch (Exception e) {
			System.out.println("Not able to select in: " + info);
			testutil.takeScreenshot(info.replaceAll(" ", "_"));
			throw new java.lang.RuntimeException("RUNTIME_ERROR : : Not able to select in: " + info);
		}
	}

	public static String getFirstSelectedOption(By locator, String info) {
		String firstSelectedoption = null;
		try {
			waitForElementPresent(locator);
			WebElement elm = getElement(locator);
			Select select = new Select(elm);
			WebElement option = select.getFirstSelectedOption();
			firstSelectedoption = option.getText();
			System.out.println("Getting successfully: " + info);
			extentTest.log(LogStatus.INFO, "Getting successfully: " + info);
		} catch (Exception e) {
			System.out.println("Not able to get: " + info);
			testutil.takeScreenshot(info.replaceAll(" ", "_"));
			throw new java.lang.RuntimeException("RUNTIME_ERROR : : Not able to get : " + info);
		}
		return firstSelectedoption;
	}

	public static int getWebElementSize(By locatorlist, String info) {
		int listSize;
		try {
			listSize = driver.findElements(locatorlist).size();
			System.out.println("Getting Successfully: " + info);
			extentTest.log(LogStatus.INFO, "Getting Successfully: " + info);
		} catch (Exception e) {
			System.out.println("Not able to Get: " + info);
			testutil.takeScreenshot(info.replaceAll(" ", "_"));
			throw new java.lang.RuntimeException("RUNTIME_ERROR : : Not able to Get Size : " + info);
		}
		return listSize;
	}

	public static boolean isEnabled(By locator, String info) {
		boolean isEnabled = false;
		try {
			waitForElementPresent(locator);
			isEnabled = getElement(locator).isEnabled();
			System.out.println("isEnabled state stored successfully for: " + info);
			extentTest.log(LogStatus.INFO, "isEnabled state stored successfully for: " + info);
		} catch (Exception e) {
			System.out.println("Not able to get isEnabled state of element: " + info);
			testutil.takeScreenshot(info.replaceAll(" ", "_"));
			throw new java.lang.RuntimeException(
					"RUNTIME_ERROR : : Not able to get isEnabled state of element : " + info);
		}
		return isEnabled;
	}

	public static boolean isSelected(By locator, String info) {
		boolean isSelected = false;
		try {
			isSelected = getElement(locator).isSelected();
			System.out.println("isSelected state stored successfully for: " + info);
			extentTest.log(LogStatus.INFO, "isSelected state stored successfully for: " + info);
		} catch (Exception e) {
			System.out.println("Not able to get isSelected state of element: " + info);
			testutil.takeScreenshot(info.replaceAll(" ", "_"));
			throw new java.lang.RuntimeException(
					"RUNTIME_ERROR : : Not able to get isSelected state of element : " + info);
		}
		return isSelected;
	}

	public static void goToUrl(String url, String urlInfo) {
		try {
			driver.get(url);
			alertUtil.acceptAlert();
			System.out.println("Navigating to :" + urlInfo);
			extentTest.log(LogStatus.INFO, "Navigating to :" + urlInfo);
			alertUtil.checkLoader();
		} catch (Exception e) {
			System.out.println("Not able to Navigate to :" + urlInfo);
			testutil.takeScreenshot(urlInfo.replaceAll(" ", "_"));
			throw new java.lang.RuntimeException("RUNTIME_ERROR : : Not able to Navigated to  :" + urlInfo);
		}
	}

	public static String getAttribute(By locator, String attribute, String info) {
		String attributeValue;
		try {
			attributeValue = getElement(locator).getAttribute(attribute);
			System.out.println("Attribute value stored successfully from: " + info);
			extentTest.log(LogStatus.INFO, "Attribute value stored successfully from: " + info);
		} catch (Exception e) {
			System.out.println("Not able to get attribute value from: " + info);
			testutil.takeScreenshot(info.replaceAll(" ", "_"));
			throw new java.lang.RuntimeException("RUNTIME_ERROR : : Not able to get attribute value from: " + info);
		}
		return attributeValue;
	}

	public static void refresh(String navigationInfo) {
		try {
			driver.navigate().refresh();
			driver.switchTo().alert().accept();
			System.out.println("refresh" + navigationInfo);
		} catch (Exception e) {
			System.out.println("Not able to refrsh :" + navigationInfo);
			testutil.takeScreenshot(navigationInfo.replaceAll("", "_"));
			throw new java.lang.RuntimeException("RUNTIME_ERROR : : Not able to refrsh :" + navigationInfo);
		}
	}

	public static boolean verifyElementPresent(By locator, String info) {
		boolean verification = false;
		try {
			int elementSize = driver.findElements(locator).size();
			if (elementSize == 1) {
				System.out.println("Verification pass for: " + info);
				extentTest.log(LogStatus.INFO, "Verification pass for: " + info);
				verification = true;
			} else {
				System.out.println("Verification fail for: " + info);
				// extentTest.log(LogStatus.WARNING, "Verification fail for: " + elementInfo);
				testutil.takeScreenshot("vefificationFail_" + info.replaceAll(" ", "_"));
				extentTest.log(LogStatus.FAIL, "Screenshort of verification failure :" + imgeHtmlPath);
			}
		} catch (Exception e) {
			System.out.println("Vefification fail for:: " + info);
			testutil.takeScreenshot(info.replaceAll(" ", "_"));
			extentTest.log(LogStatus.FAIL, "Screenshort of verification failure :" + imgeHtmlPath);
			throw new java.lang.RuntimeException("RUNTIME_ERROR : : Vefification fail for: " + info);
		}
		return verification;
	}
	// Web element

	public static String getAttribute(WebElement element, String attribute, String info) {
		String attributeValue;
		try {
			attributeValue = element.getAttribute(attribute);
			System.out.println("Attribute value stored successfully from: " + info);
			extentTest.log(LogStatus.INFO, "Attribute value stored successfully from: " + info);
		} catch (Exception e) {
			System.out.println("Not able to get attribute value from: " + info);
			testutil.takeScreenshot(info.replaceAll(" ", "_"));
			throw new java.lang.RuntimeException("RUNTIME_ERROR : : Not able to get attribute value from: " + info);
		}
		return attributeValue;
	}

	public static void click(WebElement element, String info) {
		try {
			element.click();
			System.out.println("Clicked successfully on: " + info);
			extentTest.log(LogStatus.INFO, "Clicked successfully on: " + info);
		} catch (Exception e) {
			System.out.println("Not able to click on: " + info);
			testutil.takeScreenshot(info.replaceAll(" ", "_"));
			throw new java.lang.RuntimeException("RUNTIME_ERROR : : Not able to click on: " + info);
		}
	}

	public static String doGetText(WebElement element, String info) {
		String textValue = null;
		try {
			textValue = element.getText();
			System.out.println("Text stored successfully from : " + info);
			extentTest.log(LogStatus.INFO, "Text stored successfully from : " + info);
		} catch (Exception e) {
			System.out.println("Not able to get text from: " + info);
			testutil.takeScreenshot(info.replaceAll(" ", "_"));
			throw new java.lang.RuntimeException("RUNTIME_ERROR : : Not able to get text from : " + info);
		}
		return textValue;
	}

	public static void doSwitchframe(By locator, String info) {
		try {
			driver.switchTo().frame(getElement(locator));
			System.out.println("Switched to successfully in: " + info);
			extentTest.log(LogStatus.INFO, "Mouse over successfull on : " + info);
		} catch (Exception e) {
			System.out.println();
			testutil.takeScreenshot(info.replaceAll("", "_"));
			throw new java.lang.RuntimeException("RUNTIME_ERROR : : Not able to enter into iframe : " + info);
		}
	}

	public static void dragAndDrop(By sourceLocator, By destinationLocator, String info) {
		try {
			WebElement from = driver.findElement(sourceLocator);
			WebElement to = driver.findElement(destinationLocator);
			Actions action = new Actions(driver);
			action.dragAndDrop(from, to).build().perform();
			System.out.println("Dragged successfully : " + info);
			extentTest.log(LogStatus.INFO, "Dragged successfully : " + info);
		} catch (Exception e) {
			System.out.println("Not able to Dragged  on: " + info);
			testutil.takeScreenshot(info.replaceAll("", "_"));
			throw new java.lang.RuntimeException("RUNTIME_ERROR : : Not able to Dragged  on: " + info);
		}
	}
}
