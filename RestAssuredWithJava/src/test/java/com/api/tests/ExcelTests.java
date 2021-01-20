package com.api.tests;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import com.autoamtion.utilities.ExcelParser;
public class ExcelTests {
	
	// Automation Utilities
		public static FileInputStream file = null;
		public static XSSFWorkbook workbook = null;
		public static XSSFSheet sheet = null;
		public static Properties prop;
		
		@Test
		public void testExcelSheetData() throws Exception {
			try {
				sheet = ExcelParser.getSheetData("src/test/resources/TestData.xlsx", "AppData");
				String empSal = ExcelParser.getFieldValue(sheet, "FA", 3);
				if (!empSal.equalsIgnoreCase("Null")) {
					System.out.println("Emp Salary: " + empSal);
				}
				Thread.sleep(3000);

			} catch (Exception e) {
				throw e;
			}
		}

}
