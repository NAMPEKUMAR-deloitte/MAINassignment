package com.cc.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cc.qa.base.TestBase;

public class ExcelUtil extends TestBase {

	private static XSSFWorkbook workbook = null;
	private static XSSFSheet sheet = null;
	private static XSSFRow row = null;
	public static String data;
	public String path;
	public static FileInputStream fis = null;

	public static String getkeyData(String path, String sheetName, String keyColumn, String valueColumn,
			String keyToGetValue) throws Exception {
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			int index = workbook.getSheetIndex(sheetName);
			int Valuecol_Num = -1;
			int Keycol_Num = -1;
			if (index == -1)
				return "";
			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(0);
			// int lastCellNum = row.getLastCellNum();
			for (int i = 0; i < row.getLastCellNum(); i++) {
				String cellValue = row.getCell(i).getStringCellValue().trim();
				if (cellValue.equalsIgnoreCase(keyColumn)) {
					Keycol_Num = i;
					break;
				} else {
				}
			}
			for (int i = 0; i < row.getLastCellNum(); i++) {
				String cellValue = row.getCell(i).getStringCellValue().trim();
				if (cellValue.equalsIgnoreCase(valueColumn)) {
					Valuecol_Num = i;
					break;
				} else {
				}
			}
			sheet = workbook.getSheetAt(index);
			int number = sheet.getLastRowNum();
			row = sheet.getRow(0);
			for (int i = 1; i <= (number); i++) {
				String text = (sheet.getRow(i).getCell(Keycol_Num).getStringCellValue());
				if (text.equalsIgnoreCase(keyToGetValue)) {
					data = sheet.getRow(i).getCell(Valuecol_Num).getStringCellValue();
					break;
				} else {

				}
			}
			fis.close();
			return data;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "";
		}
	}

}
