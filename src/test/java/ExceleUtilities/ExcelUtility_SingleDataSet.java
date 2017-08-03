package ExceleUtilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility_SingleDataSet {

	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;
	
	/*
	 * Set the File path, open Excel file
	 * @param - Excel Path and Sheet Name
	 */
	public static void setExcelFile (String Path, String SheetName) throws Exception{
		try {
			//Open Excel file
			FileInputStream ExcelFile = new FileInputStream(Path) ;
			
			//Access excel data sheet
			ExcelWBook = new XSSFWorkbook (ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
		} catch (Exception e){
			throw(e);
		}
	}
	
	/*
	 * Read the test Data from the Excel cell
	 * @param - Row number and column number 
	 */
	public static String getCellData (int RowNum, int ColNum) throws Exception{
		try{
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			String cellData = Cell.getStringCellValue();
			return cellData;
		} catch (Exception e) {
			return "";	
		}
	}
	
	/*
	 * Read the test data of date type from the Excel cell
	 * @param - Row number and column number
	 */
	public static String getDateCellData(int RowNum, int ColNum){
		try {
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
			
			//import from java.util
			Date dateValue = Cell.getDateCellValue();
			String dateStringFormat = df.format(dateValue);
			
			return dateStringFormat;
		} catch (Exception e){
		return "";
		}
	}
	
	/*
	 * Write in the Excel cell, String Result
	 * @param - Row number and column number
	 */
	public static void setCellData (String Result, int RowNum, int ColNum) throws Exception{
		try {
			Row = ExcelWSheet.getRow(RowNum);
			Cell = Row.getCell(ColNum);
			if (Cell == null){
				Cell = Row.createCell(ColNum);
				Cell.setCellValue(Result);
			} else {
				Cell.setCellValue(Result);
			}
			// Open the file to write the results
			FileOutputStream fileOut = new FileOutputStream 
					(Constants2_Lec153.FilePath + Constants2_Lec153.FileName);
			
			ExcelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e){
			throw (e);
		}
	}
	/*
	 * Write in the Excel cell, double Result
	 * @param - Row number and column number
	 */
	public static void setCellData (double Result, int RowNum, int ColNum) throws Exception{
		try {
			Row = ExcelWSheet.getRow(RowNum);
			Cell = Row.getCell(ColNum);
			if (Cell == null){
				Cell = Row.createCell(ColNum);
				Cell.setCellValue(Result);
			} else {
				Cell.setCellValue(Result);
				
				//Open the file to write the results
				FileOutputStream fileOut = new FileOutputStream 
						(Constants2_Lec153.FilePath + Constants2_Lec153.FileName);
				ExcelWBook.write(fileOut);
				fileOut.flush();
				fileOut.close();
			}
		}catch (Exception e){
			throw (e);
		}
		
	}
		
}
