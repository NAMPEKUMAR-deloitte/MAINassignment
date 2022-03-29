package com.cc.qa.testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.cc.qa.base.TestBase;
import com.cc.qa.pages.LoginPage;
import com.cc.qa.pages.Manager;
import com.cc.qa.util.TestUtil;

@Listeners(com.cc.qa.util.Listener.class)
public class BankmanagerOperations extends TestBase{
	Manager manager = new Manager();
	LoginPage loginPage = new LoginPage();
	
	
	@BeforeClass(alwaysRun = true)
	public void navigateTo() throws Exception {
		loginPage.navigateToCustomerCentral();
	}
	@DataProvider
	public Object[][] getdata() {
		String WBPath = System.getProperty("user.dir") + "/src/main/resources/Provider/Test.xlsx";
		String sheetName = "Manager";
		Object data[][] = TestUtil.getTestData(WBPath, sheetName);
		return data;
	}
	
	@Test(priority=1,dataProvider = "getdata")
	public void ManagerOperations(String fname,String lname, String postcde,String customername, String deptAmnt,String withdrawAmnt) throws Exception{
		manager.bankManagerLogn(fname,lname,postcde);
		manager.customerLogin(customername,deptAmnt,withdrawAmnt);
		manager.customerLoginwithError();
		manager.getTransactions();
		
	}
}
