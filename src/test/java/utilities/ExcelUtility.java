package utilities;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility extends BaseClass {
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
}
