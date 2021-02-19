package com.api.tests;

import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.autoamtion.utilities.ExcelParser;

public class ExcelTests {

	private static Logger logger;
	public static XSSFSheet sheet = null;

	@BeforeMethod
	public void setUp() throws Exception {
		try {
			sheet = com.autoamtion.utilities.ExcelParser.getSheetData("src/test/resources/BranchLibrary.xlsx", "BranchLib");
		} catch (Exception e) {
			logger.error("Caught an exception: " + e);
		}
	}

	// Return row based on ScenarioType && UserType
	@Test
	public void printValues() {
		try {
			List<Row> rowList = ExcelParser.getMatchingRows(sheet, "branch1");
			int rowSize = rowList.size();
			System.out.println("Matching branch count: " + rowSize);
			if (rowSize > 1) {
				Row row = ExcelParser.getMatchingRow(rowList, "PFA");
				if( row != null && row.cellIterator().hasNext()) {
					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext()) {
						try {
							Cell cell = cellIterator.next();
							String value = "";
							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_NUMERIC:
								value = String.valueOf(cell.getNumericCellValue());
								System.out.print(value+"\t");
								break;
							case Cell.CELL_TYPE_STRING:
								value = cell.getStringCellValue();
								System.out.print(value+"\t");
								break;
							}
						} catch (NullPointerException e) {
							System.out.println("Caught an exception while printing row data: " + e);
						}
					}
					System.out.println("\n");
				}
				
				else
					System.out.println("There is no matching row found as oer the given filterKey");
//				for(Row row : rowData) {
//					if(row.cellIterator().hasNext()) {
//						Iterator<Cell> cellIterator = row.cellIterator();
//						while (cellIterator.hasNext()) {
//							try {
//								Cell cell = cellIterator.next();
//								String value = "";
//								switch (cell.getCellType()) {
//								case Cell.CELL_TYPE_NUMERIC:
//									value = String.valueOf(cell.getNumericCellValue());
//									System.out.print(value+"\t");
//									break;
//								case Cell.CELL_TYPE_STRING:
//									value = cell.getStringCellValue();
//									System.out.print(value+"\t");
//									break;
//								}
//							} catch (NullPointerException e) {
//								System.out.println("Caught an exception while printing row data: " + e);
//							}
//						}
//						System.out.println("\n");
//					}
//				}
				
			} else
				System.out.println("There is no matching row found as oer the given filterKey");
		} catch (Exception e) {
			System.out.println("Caught an exception: " + e);
		}
	}
}
