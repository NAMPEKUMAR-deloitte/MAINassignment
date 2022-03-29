package com.cc.qa.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;

import com.cc.qa.base.TestBase;
import com.cc.qa.pages.HomePage;
import com.relevantcodes.extentreports.LogStatus;

public class TestUtil extends TestBase {

	public static long PAGE_LOAD_TIMEOUT;
	public static long IMPLICIT_WAIT;

	static Workbook book;
	static Sheet sheet;
	static JavascriptExecutor js;
	AlertUtil alertUtil = new AlertUtil();

	public void switchToFrame() {
		driver.switchTo().frame("mainpanel");
	}

	public static Object[][] getTestData(String TESTDATA_SHEET_PATH, String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		// System.out.println(sheet.getLastRowNum() + "--------"
		// +sheet.getRow(0).getLastCellNum());
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				// System.out.println(data[i][k]);
			}
		}
		return data;
	}

	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}

	public static void runTimeInfo(String messageType, String message) throws InterruptedException {
		js = (JavascriptExecutor) driver;
		// Check for jQuery on the page, add it if need be
		js.executeScript("if (!window.jQuery) {"
				+ "var jquery = document.createElement('script'); jquery.type = 'text/javascript';"
				+ "jquery.src = 'https://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js';"
				+ "document.getElementsByTagName('head')[0].appendChild(jquery);" + "}");
		Thread.sleep(5000);

		// Use jQuery to add jquery-growl to the page
		js.executeScript("$.getScript('https://the-internet.herokuapp.com/js/vendor/jquery.growl.js')");

		// Use jQuery to add jquery-growl styles to the page
		js.executeScript("$('head').append('<link rel=\"stylesheet\" "
				+ "href=\"https://the-internet.herokuapp.com/css/jquery.growl.css\" " + "type=\"text/css\" />');");
		Thread.sleep(5000);

		// jquery-growl w/ no frills
		js.executeScript("$.growl({ title: 'GET', message: '/' });");

		if (messageType.equals("error")) {
			js.executeScript("$.growl.error({ title: 'ERROR', message: '" + message + "' });");
		} else if (messageType.equals("info")) {
			js.executeScript("$.growl.notice({ title: 'Notice', message: 'your notice message goes here' });");
		} else if (messageType.equals("warning")) {
			js.executeScript("$.growl.warning({ title: 'Warning!', message: 'your warning message goes here' });");
		} else
			System.out.println("no error message");

		Thread.sleep(5000);
	}

	public void takeScreenshot(String SSname) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {

			String screenShotPath = System.getProperty("user.dir") + "\\LogsAndReports\\Reports_"
					+ extentDate.substring(0, 10) + "\\Screenshots\\";
			FileUtils.copyFile(scrFile, new File(screenShotPath + SSname + ".png"));
			imgeHtmlPath = extentTest.addScreenCapture(screenShotPath + SSname + ".png")
					.replace(screenShotPath, "Screenshots\\").replace("file:///", "")
					.replace("<img", "<img width=\"150\" height=\"70\"");

		} catch (IOException e) {
			throw new java.lang.RuntimeException(
					"RUNTIME_ERROR : : Exception occur during taking ScreenShot: " + SSname);
		}
		System.out.println("Screenshot has been generated for " + SSname);
	}

	public void deleteFiles(String downloadPath) {
		File file = new File(downloadPath);
		File[] filesToDelete = file.listFiles();
		for (File f : filesToDelete) {
			if (f.isFile() && f.exists()) {
				f.delete();
				System.out.println("successfully deleted");
			} else {
				System.out.println("cant delete a file due to open or error");
			}
		}
	}

	public File getLatestFilefromDir(String dirPath) throws InterruptedException {
		Thread.sleep(5000);
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		System.out.println(files.length);
		if (files == null || files.length == 0) {
			return null;
		}

		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++) {
			Thread.sleep(3000);
			System.out.println("inside for");
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				System.out.println("inside if");
				System.out.println(lastModifiedFile);
				lastModifiedFile = files[i];
			}
			System.out.println(lastModifiedFile);
		}
		return lastModifiedFile;
	}

	public static String getCurrentDate() {
		SimpleDateFormat dateformat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String currentdate = dateformat.format(date);
		return currentdate;
	}

	public static String dateMinusDays(String Dt, int number) throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-uuuu");
		LocalDate date1 = LocalDate.parse(Dt, formatter);
		date1 = date1.minusDays(number);
		Dt = date1.format(formatter);
		return Dt;
	}

	public static String dateMinusMonths(String Dt, int number) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-uuuu");
		LocalDate date1 = LocalDate.parse(Dt, formatter);
		date1 = date1.minusMonths(number);
		Dt = date1.format(formatter);
		return Dt;
	}

	public static String datePlusDays(String Dt, int number) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-uuuu");
		LocalDate date1 = LocalDate.parse(Dt, formatter);
		date1 = date1.plusDays(number);
		Dt = date1.format(formatter);
		return Dt;
	}

	public static String getRandomNumber() {
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmm");
		Date date = new Date();
		String uniqueKey = dateFormat.format(date);
		return uniqueKey;

	}

	public static String getCurrentTime() {
		DateFormat dateFormat = new SimpleDateFormat("hhmmss");
		Date date = new Date();
		String uniqueKey = dateFormat.format(date);
		return uniqueKey;

	}

	public void verifyPDFData(String Url, String pdfContent) throws Exception {
		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.navigate().to(Url);
		URL url = new URL(driver.getCurrentUrl());
		InputStream is = url.openStream();
		BufferedInputStream fileToParse = new BufferedInputStream(is);
		PDDocument document = null;
		String output = null;
		try {
			document = PDDocument.load(fileToParse);
			output = new PDFTextStripper().getText(document);
			// System.out.println(output);
		} finally {
			if (document != null) {
				document.close();
			}
			fileToParse.close();
			is.close();
		}
		Assert.assertTrue(output.contains(pdfContent));
		driver.close();
		driver.switchTo().window(tabs.get(0));
	}

	public void writeToExcel(String filePath, String fileName, String sheetName, String[] dataToWrite)
			throws IOException {
		try {
			File file = new File(filePath + "\\" + fileName);
			FileInputStream inputStream = new FileInputStream(file);
			Workbook workbook = null;
			String fileExtensionName = fileName.substring(fileName.indexOf("."));
			if (fileExtensionName.equals(".xlsx")) {
				workbook = new XSSFWorkbook(inputStream);
			}
			Sheet sheet = workbook.getSheet(sheetName);
			int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
			Row row = sheet.getRow(0);
			Row newRow = sheet.createRow(rowCount + 1);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				Cell cell = newRow.createCell(j);
				cell.setCellValue(dataToWrite[j]);
			}
			inputStream.close();

			FileOutputStream fos = new FileOutputStream(file);
			workbook.write(fos);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean deleteRow(String filePath, String fileName, String sheetName, int rowNo) throws IOException {
		XSSFWorkbook workbook = null;
		XSSFSheet sheet = null;
		String excelPath = filePath + "\\" + fileName;
		try {
			FileInputStream file = new FileInputStream(new File(excelPath));
			workbook = new XSSFWorkbook(file);
			sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				return false;
			}
			int lastRowNum = sheet.getLastRowNum();
			if (rowNo >= 0 && rowNo < lastRowNum) {
				sheet.shiftRows(rowNo + 1, lastRowNum, -1);
			}
			if (rowNo == lastRowNum) {
				XSSFRow removingRow = sheet.getRow(rowNo);
				if (removingRow != null) {
					sheet.removeRow(removingRow);
				}
			}
			file.close();
			FileOutputStream outFile = new FileOutputStream(new File(excelPath));
			workbook.write(outFile);
			outFile.close();

		} catch (Exception e) {
			throw e;
		} finally {
			if (workbook != null)
				workbook.close();
		}
		return true;
	}

	public void verifyEmailContent(String username, String pwd, String content) throws InterruptedException {
		Thread.sleep(2000);
		ElementUtil.goToUrl("https://login.microsoftonline.com", "MS OFFICE");
		alertUtil.acceptAlert();
		ElementUtil.waitForElementPresent(HomePage.usernameInEmail);
		ElementUtil.clearAnddoSendKeys(HomePage.usernameInEmail, username, "username");
		ElementUtil.doClick(HomePage.nextInEmail, "next");
		ElementUtil.clearAnddoSendKeys(HomePage.passwordInEmail, pwd, "pwd");
		JavaScriptUtil.clickElementByJS(HomePage.signInEmail, "Sign In");
		int staySignin = ElementUtil.getWebElementSize(HomePage.yesInEmailPage, "yes");
		if (staySignin > 0) {
			ElementUtil.doClick(HomePage.yesInEmailPage, "yes");
		}
		ElementUtil.doClick(HomePage.outlookIcon, "outlook");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		alertUtil.checkLoader();
		// ElementUtil.doClick(By.xpath("//span[text()='Other']"), "open Other");
		ElementUtil.doClick(By.xpath("//span[contains(text(),'" + content + "')]"), "open mail");
		if (driver.getPageSource().contains(content)) {
			extentTest.log(LogStatus.PASS, "Email content verified");
			System.out.println("Email content verified");
		} else {
			extentTest.log(LogStatus.FAIL, "Email content not verified");
			System.out.println("Email content not verified");
		}
		ElementUtil.doClick(HomePage.profileIcon, "profile");
		ElementUtil.waitForElementPresent(HomePage.signoutEmail);
		ElementUtil.doClick(HomePage.signoutEmail, "signout");
		driver.close();
		driver.switchTo().window(tabs.get(0));
	}

	public String[] getDataFromCSV(String path) throws IOException {
		String row;
		String[] data = null;
		File file = new File(path);
		if (file.isFile()) {
			BufferedReader csvReader = new BufferedReader(new FileReader(path));
			while ((row = csvReader.readLine()) != null) {
				data = row.split(",");
				System.out.println(data);
				break;
			}
			csvReader.close();
		}

		return data;
	}
}
