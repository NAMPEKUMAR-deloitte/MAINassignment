package com.cc.qa.pages;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;

import com.cc.qa.base.TestBase;
import com.cc.qa.util.AlertUtil;
import com.cc.qa.util.ElementUtil;
import com.cc.qa.util.JavaScriptUtil;
import com.cc.qa.util.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class HomePage extends TestBase {

	AlertUtil alertUtil = new AlertUtil();

	@CacheLookup
	public static By userNameLabel = By.xpath("//td[contains(@id,'cc_usrName')]");

	// Logo
	public static By logo = By.xpath("//*[contains(@class,'company-logo')]");

	public static By mainMenu = By.xpath("//*[contains(@id,'mainmenu-toggle')]");

	public static By searchInMainMenu = By.xpath("//input[contains(@placeholder,'Search here')]");

	public static By saveBtn = By.linkText("Save");

	public static By searchButton = By.xpath("//span[contains(text(),'Go')]");

	public static By pageSubTitle = By.xpath("//*[@class='sub-title']");

	// exit
	public static By closeBtn = By
			.xpath("//div[contains(@id,'pagemenu-top')]//a[contains(@class,'btn btn-regular btn-close')]");

	public static By LeavePageBtn = By.xpath("//button[text()='Leave Page']");

	public static By saveButton = By.linkText("Save");

	public static By sucessMessage = By.xpath("//center[contains(@class,'left alert')]");

	public static By failureMessage = By.xpath("//center[@class='left alert-danger']");

	// Submit
	public static By submitButton = By.linkText("Submit");

	public static By clearSucMsg = By.xpath("//*[contains(@class,'alert')]//*[@class='icon icon-cross2']");

	// view
	public static By changeViewBtn = By.xpath("//button[contains(text(),'View')]");

	public static By tabbedView = By.xpath("//div[@id='pagemenu-top']//*[contains(@class,'splitView')]");

	public static By stackedView = By.xpath("//div[@id='pagemenu-top']//*[contains(@class,'allView')]");

	// Activity Tab
	public static By activityTab = By.xpath("//small[contains(@id, 'smalllabel_ACTVTI')]");

	public static By email = By
			.xpath("//ul[contains(@class, 'activity-list no-margin pad10 list-unstyled clearfix')]/li[2]");

	public static By toEmail = By.xpath("//input[contains(@id, 'notification_notificationTo')]");

	public static By emailSubject = By.xpath("//input[contains(@id, 'notification_defns_0_subject')]");

	public static By emailContent = By.xpath("//*[contains(@id, 'cke_2899_contents')]");

	public static By emailBody = By.tagName("body");

	public static By entityAsAttachment = By.xpath("//input[contains(@id, 'notification_entityAsAttachment')]");

	public static By saveActivity = By.xpath("//button[contains(@id, 'saveActivity')]");

	public static By call = By
			.xpath("//ul[contains(@class, 'activity-list no-margin pad10 list-unstyled clearfix')]/li[3]");

	public static By call_Phone = By.xpath("//input[@id= 'contact_phone'][@name= \"contact_phone\"]");

	public static By call_PhoneExt = By.xpath("//input[@id= 'contact_phoneExt'][@name= \"contact_phoneExt\"]");

	public static By call_firstName = By
			.xpath("//form[@id ='entityActivityForm']//input[contains(@id, 'contact_firstName')]");

	public static By call_lastName = By
			.xpath("//form[@id ='entityActivityForm']//input[contains(@id, 'contact_lastName')]");

	public static By activity_priority = By
			.xpath("//form[@id='entityActivityForm']//select[contains(@id, 'priority')]");

	public static By call_date = By.xpath("//form[@id='entityActivityForm']//input[contains(@id, 'date')]");

	public static By activity_Desc = By.xpath("//form[@id='entityActivityForm']//*[contains(@id, 'description')]");

	public static By assign = By
			.xpath("//ul[contains(@class, 'activity-list no-margin pad10 list-unstyled clearfix')]/li[4]");

	public static By assigneeType = By.xpath("//select[contains(@id, 'assigneeType')]");

	public static By assigneeCode = By.xpath("//input[contains(@id, 'assigneeCode')]");

	public static By assign_subType = By.xpath("//select[contains(@id, 'subType')]");

	public static By emailActivityType = By.xpath("//input[@id= 'Email'][@class='activityType']");

	public static By callActivityType = By.xpath("//input[@id= 'Call'][@class='activityType']");

	public static By assignActivityType = By.xpath("//input[@id= 'Assign'][@class='activityType']");

	public static By searchActivityType = By.xpath("//button[contains(@id, 'submitBtn')]");

	public static By expandArrow = By.xpath("//i[contains(@class, 'Expand')]");

	// Payment
	public static By paymentPage = By.xpath("//*[@id='contentPagePayment']");

	public static By ccNumber = By.xpath("//*[@id='cc_number']");

	public static By expMM = By.xpath("//*[@id='expdate_month']");

	public static By expYY = By.xpath("//*[@id='expdate_year']");

	public static By cvv = By.xpath("//*[@id='cvv2_number']");

	public static By firstName = By.xpath("//*[@id='first_name']");

	public static By lastName = By.xpath("//*[@id='last_name']");

	public static By payNow = By.xpath("//*[@id='btn_pay_cc'][@value='Pay Now']");

	// Validations
	public static By validationRules = By.xpath("//*[contains(@class,'vmsg help-block')]");

	// import
	public static By importLink = By.linkText("Import");

	public static By newInImport = By.xpath("//span[text()='New']");

	public static By nameInImport = By.xpath("//input[@id='name']");

	public static By entityType = By.id("entityType");

	public static By availabelMappings = By.id("jobCode");

	public static By format = By.id("importFormat");

	public static By sourceDropdown = By.id("importSource");

	public static By browseInImport = By.xpath("//input[contains(@id,'upload')]");

	public static By yesInpopup = By.xpath("//button[text()='Yes']");

	public static By saveImport = By.id("saveImportTemplate");

	public static By importButton = By.id("importData");

	// Email
	public static By yesInEmailPage = By.id("idSIButton9");

	public static By outlookIcon = By.xpath("//*[contains(@title,'Outlook')]");

	public static By usernameInEmail = By.xpath("//input[@name='loginfmt']");

	public static By nextInEmail = By.id("idSIButton9");

	public static By passwordInEmail = By.xpath("//input[@name='passwd']");

	public static By signInEmail = By.xpath("//input[@value='Sign in']");

	public static By profileIcon = By.xpath("//div[contains(@id,'O365_MainLink')]");

	public static By signoutEmail = By.xpath("//a[contains(@id,'signOut')]");

	public static By otherinMail = By.xpath("//span[text()='Other']");

	// Header Icons
	public static By notificationIconInHeader = By.id("notify-title");

	public static By alertsHeader = By.xpath("//h1[text()='Alerts']");

	public static By cartIconInHeader = By.xpath("//a[@id='viewShoppingCart']//i[@title='Cart']");

	public static By cartHeaderInPopup = By.xpath("//div[contains(@class,'topbar') and text()='Cart']");

	public static By closeCart = By.xpath("//*[@class='icon icon-cross2']");

	public static By inboxIconInHeader = By.xpath("//li[@id='inbox-button']//i[@title='Inbox']");

	public static By InboxPopup = By.xpath("//h1[text()='Inbox']");

	public static By activityIconInHeader = By.xpath("//a[@id='viewActivity']//i[@title='Activity']");

	public static By activityHeader = By.xpath("//h1[text()='Activity']");

	public static By downloadIconInHeader = By.xpath("//li[@id='download-button']//i[@title='Download']");

	public static By downloadsScreen = By.xpath("//label[text()='Download for Offline']");

	public static By favoriteIconInHeader = By.xpath("//i[@id='myFavoritesMenus']");

	public static By favPopup = By.xpath("//input[@id='fav_srch_qry']");

	public static By helpIconInHeader = By.xpath("//span[@title='Help']");

	public static By helpDropdown = By.xpath("//ul[@id='helpMenuOptions']");

	public static By enlargeIconInHeader = By.xpath("//i[@title='Full Screen']");

	public static By cameraIconInHeader = By.xpath("//i[@class='icon-camera']");

	// Drop down

	public static By userAccount = By.id("userAccount");

	public static By editInUserAccount = By.xpath("//div[@id='pagemenu-top']//*[contains(@class,'edit')]");

	// Popup
	public static By saveAnyway = By.xpath("//div[@id='dialog']//*[text()='Save Anyway']");

	public static By userPolicyPopup = By
			.xpath("//form[@data-mize-form='endUserPolicyForm']//input[@value='N' or @value='Y']");

	public static By acceptInPopup = By.xpath("//button[contains(text(),'Accept')]");

	public static By checkBoxInPopup = By.id("isAgreementAccepted");

	// Audit
	public static By auditIcon = By.xpath("//*[@title='Audit'][@type='button']");

	public static By closeAudit = By.xpath("//*[@class='icon icon-cross2']");

	// Related
	public static By relatedIcon = By.xpath("//i[@id = 'entityRelationPopup']");

	// Transaction #
	public static By transactionID = By.xpath("//span[starts-with(@class,'help-block')]/span");

	// Found Updates
	public static By foundUpdatesPopup = By.xpath("//label[contains(text(),'Found updates')]");

	public static By okInFoundUpdatesPopup = By.xpath("//button[contains(text(),'OK')]");

	public static By alertPopUp = By.xpath("//*[@id = 'dialog']");

	public String verifyHomePageTitle() {
		return driver.getTitle();
	}

	public boolean verifyCorrectUserName() {
		return ElementUtil.isElementDisplayed(userNameLabel, "User Name");

	}

	public void verifyNotifctaionsInHeader() throws InterruptedException {
		alertUtil.checkLoader();
		ElementUtil.doClick(notificationIconInHeader, "Notifications");
		ElementUtil.verifyElementPresent(notificationIconInHeader, "Notications Header");
	}

	public void verifyCartInHeader() throws InterruptedException {
		alertUtil.checkLoader();
		ElementUtil.doClick(cartIconInHeader, "cart icon");
		ElementUtil.verifyElementPresent(cartHeaderInPopup, "Cart Header");
		ElementUtil.doClick(closeCart, "close cart");
	}

	public void verifyInboxInHeader() throws InterruptedException {
		alertUtil.checkLoader();
		ElementUtil.doClick(inboxIconInHeader, "Inbox");
		ElementUtil.verifyElementPresent(InboxPopup, "inbox");
	}

	public void verifyActivtyInHeader() throws InterruptedException {
		alertUtil.checkLoader();
		ElementUtil.doClick(activityIconInHeader, "activity header");
		ElementUtil.verifyElementPresent(activityHeader, "activity");
	}

	public void verifyDownloadsFromHomePage() throws InterruptedException {
		alertUtil.checkLoader();
		ElementUtil.doClick(downloadIconInHeader, "downloads");
		ElementUtil.verifyElementPresent(downloadsScreen, "downloads");
	}

	public void verifyFavoritesFromHomePage() throws InterruptedException {
		alertUtil.checkLoader();
		ElementUtil.doClick(favoriteIconInHeader, "favorite");
		ElementUtil.verifyElementPresent(favPopup, "fav popup");
	}

	public void verifyHelpFromHomePage() throws InterruptedException {
		alertUtil.checkLoader();
		ElementUtil.doClick(helpIconInHeader, "Help");
		ElementUtil.verifyElementPresent(helpDropdown, "Help popup");
	}

	public void verifyEnlargeIcon() throws Exception {
		alertUtil.checkLoader();
		ElementUtil.doClick(enlargeIconInHeader, "enlarge");
		Thread.sleep(5000);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
	}

	public ArrayList<String> verifyMediaIconFromHomePage() throws InterruptedException {
		alertUtil.checkLoader();
		ElementUtil.doClick(cameraIconInHeader, "Camera Icon");
		alertUtil.checkLoader();
		List<WebElement> dropdowns = driver.findElements(
				By.xpath("//i[@class='icon-camera']//parent::span//parent::a//following-sibling ::ul/li/a"));
		ArrayList<String> dropdown = new ArrayList<String>();
		Iterator<WebElement> itr = dropdowns.iterator();
		while (itr.hasNext()) {
			String st = itr.next().getText().trim();
			dropdown.add(st);
		}
		return dropdown;
	}

	public void usagePolicyPopup() throws InterruptedException {
		long time = TestUtil.IMPLICIT_WAIT / 2;
		for (int i = 0; i <= time; i++) {
			boolean result = false;
			try {
				if (ElementUtil.getWebElementSize(userPolicyPopup, "UsagePolicy Popup") > 0) {
					result = true;
					System.out.println("UsagePolicy Popup Found");
					extentTest.log(LogStatus.INFO, "UsagePolicy Popup Found");
				}
				if (result == true) {
					if (ElementUtil.getWebElementSize(checkBoxInPopup, "Check Box") > 0) {
						JavaScriptUtil.clickElementByJS(checkBoxInPopup, "Check Box");
					}
					JavaScriptUtil.scrollIntoView(acceptInPopup, "Accept");
					JavaScriptUtil.clickElementByJS(acceptInPopup, "accept");
					if (result == false) {
						break;
					}
				} else {
					break;
				}
			} catch (Exception e) {
				System.out.println("UsagePolicy Popup Not Found");
				extentTest.log(LogStatus.INFO, "UsagePolicy Popup Not Found");
			}
			alertUtil.checkLoader();
		}
	}

	public void verifyUpdatesFoundPopup() throws InterruptedException {
		alertUtil.checkLoader();
		int a = ElementUtil.getWebElementSize(foundUpdatesPopup, "Found Updates");
		if (a > 0) {
			ElementUtil.doClick(okInFoundUpdatesPopup, "OK in Found Updates");
		}
	}
}
