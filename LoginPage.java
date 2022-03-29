package com.cc.qa.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;

import com.cc.qa.base.TestBase;
import com.cc.qa.util.AlertUtil;
import com.cc.qa.util.ElementUtil;
import com.cc.qa.util.JavaScriptUtil;
import com.cc.qa.util.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class LoginPage extends TestBase {

	AlertUtil alertUtil = new AlertUtil();
	HomePage homePage = new HomePage();

	public LoginPage() {
		TestUtil.IMPLICIT_WAIT = 10;
		TestUtil.PAGE_LOAD_TIMEOUT = 10;
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/cc/qa/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Page Factory - OR:
	public static By username = By.xpath("//input[contains(@id,'loginId')]");

	public static By password = By.xpath("//input[contains(@name,'password')]");

	public static By loginBtn = By
			.xpath("//button[contains(@id,'portalSignIn')]|//button[contains(@id,'ccPortalSignIn')]");

	public static By userProfileMenu = By.id("cc_usrName");

	public static By drpdwnMenu = By.xpath("//li[contains(@class,'dropdown')]//span[contains(@id,'Name')]");

	public static By userMenu = By.xpath("//li[contains(@class,'userMenu')]");

	public static By logoutBtn = By.xpath("//*[contains(@class,'myaccount')]//a[contains(@href,'signoff')]");

	// Actions:
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}

	public void navigateToCustomerCentral() throws InterruptedException {
		driver.manage().deleteAllCookies();
			ElementUtil.goToUrl(prop.getProperty("url"), "XYZ is Opened");
		extentTest.log(LogStatus.PASS, "Url loaded successfully");
	}

	
	
}
