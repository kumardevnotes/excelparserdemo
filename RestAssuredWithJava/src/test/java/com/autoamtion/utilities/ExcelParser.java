package com.autoamtion.utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

// import statements
public class ExcelParser {
	public static FileInputStream fileInputStream = null;
	public static XSSFWorkbook workbook = null;
	public static XSSFSheet sheet = null;

	public static XSSFSheet getSheetData(String fileName, String sheeName) throws Exception {
		try {
			fileInputStream = new FileInputStream(new File(fileName)); // "C://Users//VasudevB//Downloads//EC_Test_Scripts_AutomationV1.xlsx"

			// Create Workbook instance holding reference to .xlsx file
			workbook = new XSSFWorkbook(fileInputStream);

			// Get first/desired sheet from the workbook
			sheet = workbook.getSheet(sheeName);
			fileInputStream.close();
			return sheet;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getFieldValue(XSSFSheet sheet, String fieldName, int columnNum) throws Exception {
		boolean isFieldMatched = false;
		String fieldValue = "";

		// Iterate through each rows one by one
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			try {
				Row row = rowIterator.next();
				// For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();

				if (isFieldMatched) {
					break;
				}

				while (cellIterator.hasNext()) {
					try {
						Cell cell = cellIterator.next();
						// Check the cell type and format accordingly
						String value = "";
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							value = String.valueOf(cell.getNumericCellValue());
							if (value.equals(fieldName)) {
								isFieldMatched = true;
								// System.out.print(value + "\t");
								Cell actualCell = row.getCell(columnNum);

								DataFormatter formatter = new DataFormatter();
								String val = formatter.formatCellValue(actualCell);

								fieldValue = val;
								break;
							}
							break;
						case Cell.CELL_TYPE_STRING:
							value = cell.getStringCellValue();
							if (value.equals(fieldName)) {
								isFieldMatched = true;
								// System.out.print(value + "\t");
								Cell actualCell = row.getCell(columnNum);
								DataFormatter formatter = new DataFormatter();
								String val = formatter.formatCellValue(actualCell);
								fieldValue = val;
								break;
							}
							break;
						}
					} catch (NullPointerException e) {
						System.out.println("Caught an exception while getting fieldData: " + e);
					}
				}
			} catch (Exception e) {
				throw e;
			}
		}

		return fieldValue;
	}

	public static LinkedHashMap<Integer, String> getTestIterationCount(XSSFSheet sheet, String testCaseID,
			int columnNum) throws Exception {
		int count = 0;
		LinkedHashMap<Integer, String> testRunDetails = new LinkedHashMap<Integer, String>();
		System.out.println("testCaseID passed " + testCaseID);
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			try {
				Row row = rowIterator.next();
				Cell actualCell = row.getCell(columnNum);
				DataFormatter formatter = new DataFormatter();
				String val = formatter.formatCellValue(actualCell);
				if (val.contains(testCaseID)) {
					count++;
					testRunDetails.put(count, val);

				}
			} catch (Exception e) {
				throw e;
			}
		}

		return testRunDetails;
	}

	public static void setPositionValue(String fileName, String sheetName, String testCaseIterationID,
			int columnTestIteration, int columnPosition, String positionCreated) throws Exception {

		try {
			System.out.println(" ********************** Updated the value " + fileName + sheetName + testCaseIterationID
					+ columnTestIteration + columnPosition + positionCreated);
			FileInputStream fileInputStream = new FileInputStream(new File(fileName));

			workbook = new XSSFWorkbook(fileInputStream);
			sheet = workbook.getSheet(sheetName);
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Cell actualCell = row.getCell(columnTestIteration);
				DataFormatter formatter = new DataFormatter();
				String val = formatter.formatCellValue(actualCell);
				if (val.contains(testCaseIterationID)) {
					actualCell = row.getCell(columnPosition);
					actualCell.setCellValue(positionCreated);
					break;
				}
			}
			// Write the changes to workbook as shown below
			FileOutputStream fileOutputStream = new FileOutputStream(fileName);
			workbook.write(fileOutputStream);
			fileOutputStream.flush();
			fileOutputStream.close();
			fileInputStream.close();
		} catch (Exception e) {
			throw e;
		}
	}

	public static List<Row> getMatchingRows(XSSFSheet sheet, String filterKey) throws Exception {
		List<Row> rowList = new ArrayList<Row>();
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			try {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					try {
						Cell cell = cellIterator.next();
						String value = "";
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							value = String.valueOf(cell.getNumericCellValue());
							if (value.equals(filterKey)) {
								rowList.add(row);
							}
							break;
						case Cell.CELL_TYPE_STRING:
							value = cell.getStringCellValue();
							if (value.equals(filterKey)) {
								rowList.add(row);
							}
							break;
						}
					} catch (NullPointerException e) {
						System.out.println("Caught an exception while getting data: " + e);
					}
				}
			} catch (Exception e) {
				throw e;
			}
		}

		return rowList;
	}

	public static Row getMatchingRow(List<Row> rowList, String filterKey) throws Exception {
		Row myRow = null;
		Iterator<Row> rowIterator = rowList.iterator();
		while (rowIterator.hasNext()) {
			try {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					try {
						Cell cell = cellIterator.next();
						String value = "";
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							value = String.valueOf(cell.getNumericCellValue());
							if (value.equals(filterKey)) {
								myRow = row;
							}
							break;
						case Cell.CELL_TYPE_STRING:
							value = cell.getStringCellValue();
							if (value.equals(filterKey)) {
								myRow = row;
							}
							break;
						}
					} catch (NullPointerException e) {
						System.out.println("Caught an exception while getting data: " + e);
					}
				}
			} catch (Exception e) {
				throw e;
			}
		}

		return myRow;
	}
	
}
