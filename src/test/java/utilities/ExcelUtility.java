package utilities;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility extends BaseClass {
	
	public static Object[][] getEmployeeData(String sheetName) {

	    Object[][] data = null;

	    try {

	        BaseClass base = new BaseClass();

	        FileInputStream fis = new FileInputStream(
	                base.readDataFromPropertyFile("excelPath"));

	        XSSFWorkbook workbook = new XSSFWorkbook(fis);

	        XSSFSheet sheet = workbook.getSheet(sheetName);

	        int rowCount = sheet.getLastRowNum();
	        int colCount = sheet.getRow(0).getLastCellNum();

	        data = new Object[rowCount][colCount];

	        DataFormatter formatter = new DataFormatter();

	        for (int i = 1; i <= rowCount; i++) {

	            for (int j = 0; j < colCount; j++) {

	                data[i - 1][j] =
	                        formatter.formatCellValue(
	                                sheet.getRow(i).getCell(j));

	            }
	        }

	        workbook.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return data;
	}
	
	
	public static String readData(int row, int col, String sheet1) {
		String value = "";//local variables must be initialized
		try {
			  BaseClass base = new BaseClass();

	            FileInputStream fis = new FileInputStream(
	                    base.readDataFromPropertyFile("excelPath"));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);//.xls==HSSFWorkbook
			XSSFSheet sheet = workbook.getSheet(sheet1); //workbook.getSheet("LoginSheet")
			XSSFCell cell = sheet.getRow(row).getCell(col);
			DataFormatter formatter = new DataFormatter();
			value = formatter.formatCellValue(cell);
			workbook.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}

		return value;
	}
	public static int getRowCount(String sheetName) {

	    int rows = 0;

	    try {
	        BaseClass base = new BaseClass();

	        FileInputStream fis = new FileInputStream(
	                base.readDataFromPropertyFile("excelPath"));

	        XSSFWorkbook workbook = new XSSFWorkbook(fis);

	        XSSFSheet sheet = workbook.getSheet(sheetName);

	        rows = sheet.getLastRowNum();

	        workbook.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return rows;
	}
}
