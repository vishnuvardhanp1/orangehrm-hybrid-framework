package utilities;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	public static String readData(int row, int col, String sheet1) {
		String value = "";//local variables must be initialized
		try {
			FileInputStream fis = new FileInputStream("./testData/OrangeHRMData.xlsx");
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
