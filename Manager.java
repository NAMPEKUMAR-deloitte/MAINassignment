package com.cc.qa.pages;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.cc.qa.base.TestBase;
import com.cc.qa.util.AlertUtil;

import com.cc.qa.util.ElementUtil;
import com.cc.qa.util.TestUtil;

public class Manager extends TestBase{
	ElementUtil eUtil = new ElementUtil();

	AlertUtil alertUtil = new AlertUtil();

	
//here i have written the xpaths
	
	public static By home = By.xpath("//button[contains(text(),'Home')]");
	
	public static By bankMangrLoginbtn = By.xpath("//button[contains(text(),'Bank Manager Login')]");
	
	public static By bankcstmrLoginbtn = By.xpath("//button[contains(text(),'Customer Login')]");
	
	public static By addcstmr = By.xpath("//button[contains(text(),'Add Customer')]");
	
	public static By cstmrFirstname = By.xpath("//div[@class='form-group']//input[@placeholder='First Name']");
	
	public static By cstmrLastname = By.xpath("//div[@class='form-group']//input[@placeholder='Last Name']");
	
	public static By postalCode = By.xpath("//div[@class='form-group']//input[@placeholder='Post Code']");
	
	public static By cstmrSbmit = By.xpath("//button[contains(@type,'submit')]");
	
	public static By openAcnt = By.xpath("//button[contains(text(),'Open Account')]");
	
	public static By cName = By.xpath("//select[@id='userSelect']");
	
	public static By currency = By.xpath("//select[@id='currency']");
	
	public static By submit = By.xpath("//button[@type='submit']");
	
//	public static By customerLogn = By.xpath("//button[contains(text(),'Customer Login')]");
	
	public static By transactn = By.xpath("//button[contains(text(),'Transactions')]");
	
	public static By deposit = By.xpath("//button[contains(text(),'Deposit')]");
	
	public static By withdrawl = By.xpath("//button[contains(text(),'Withdrawl')]");
	
	public static By login = By.xpath("//button[contains(text(),'Login')]");
	
	public static By enterDepositAmnt = By.xpath("//div[@class='form-group']//input[@placeholder='amount']");
	
	public static By cstmrdeposit = By.xpath("//button[@type='submit']");
	
	public static By depositsuccessmsg = By.xpath("//span[contains(text(),'Deposit Successful')]");
	
	public static By cstmrWithdrawl = By.xpath("//button[@type='submit']");
	
	public static By getTransc = By.xpath("//table[contains(@class,'table-bordered')]");
	
	
	//Actions(here these are the methods)
	
	public void bankManagerLogn(String fname,String lname, String postcde) throws Exception{
		alertUtil.checkLoader();
		ElementUtil.doClick(bankMangrLoginbtn, "Click on Bank manager Button");
		ElementUtil.doClick(addcstmr, "click on Add Customer");
		ElementUtil.doSendKeys(cstmrFirstname, fname, "Enter Customer First Name");
		ElementUtil.doSendKeys(cstmrLastname, lname, "Enter Customer last Name");
		ElementUtil.doSendKeys(postalCode, postcde, "Enter Postal code");
		ElementUtil.doClick(cstmrSbmit, "Click on Submit");
		ElementUtil.doClick(home, "Navigate Back to home");
		
		
	}
	
	
	public void customerLogin(String customername, String deptAmnt,String withdrawAmnt) throws Exception {
		alertUtil.checkLoader();
		ElementUtil.doClick(bankcstmrLoginbtn, "Click on Customer Login");
		ElementUtil.selectByVisibleText(cName, customername, "Select Customer");
		ElementUtil.doClick(login, "Click on Login");
		ElementUtil.doClick(deposit, "Select Deposit");
		ElementUtil.clearAnddoSendKeys(enterDepositAmnt, deptAmnt, "Enter Deposit Amount");
		ElementUtil.doClick(cstmrdeposit, "");
		TestUtil.takeScreenshotAtEndOfTest();
		ElementUtil.doClick(withdrawl, "Click on Withdrwal");
		ElementUtil.clearAnddoSendKeys(enterDepositAmnt, withdrawAmnt, "Enter Withdraw Amount");
		ElementUtil.doClick(cstmrWithdrawl, "CLick on Withdrawl");
		TestUtil.takeScreenshotAtEndOfTest();
		ElementUtil.doClick(home, "Navigate back to home page");
	}
	
	
	public void customerLoginwithError() throws Exception {
		alertUtil.checkLoader();
		ElementUtil.doClick(bankcstmrLoginbtn, "Click on Customer Login");
		ElementUtil.selectByVisibleText(cName, "Albus Dumbledore", "Select Customer");
		ElementUtil.doClick(login, "Click on Login");
		ElementUtil.doClick(deposit, "Select Deposit");
		ElementUtil.clearAnddoSendKeys(enterDepositAmnt, "500", "Enter Deposit Amount");
		ElementUtil.doClick(cstmrdeposit, "");
		TestUtil.takeScreenshotAtEndOfTest();
		ElementUtil.doClick(withdrawl, "Click on Withdrwal");
		ElementUtil.clearAnddoSendKeys(enterDepositAmnt, "900", "Enter Withdraw Amount");
		ElementUtil.doClick(cstmrWithdrawl, "CLick on Withdrawl");
		TestUtil.takeScreenshotAtEndOfTest();
		System.out.println("Transaction Failed. You can not withdraw amount more than the balance.");
		ElementUtil.doClick(home, "Navigate back to home page");
	}
	
	
	public void getTransactions() throws Exception{
		alertUtil.checkLoader();
		ElementUtil.doClick(bankcstmrLoginbtn, browserName);
		ElementUtil.selectByVisibleText(cName, "Albus Dumbledore", "Select Customer");
		ElementUtil.doClick(login, "Click on Login");
		ElementUtil.doClick(transactn, "Click on Transaction");
		if(ElementUtil.getWebElementSize(getTransc, "Get Traction")>0) {
			System.out.println("Transaction details are Present");
			TestUtil.takeScreenshotAtEndOfTest();
		}
		else{
			System.out.println("Transaction Details are not present");
		}
		
		
	}
	
	
}
