package Utils;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.BorderStyle;

import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.CellStyle;

import org.apache.poi.ss.usermodel.FillPatternType;

import org.apache.poi.ss.usermodel.Font;

import org.apache.poi.ss.usermodel.HorizontalAlignment;

import org.apache.poi.ss.usermodel.IndexedColors;

import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

	static String bookList = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
			+ File.separator + "resources" + File.separator + "bookList.xlsx";
	public ArrayList<String> list;
	static String excelFile = System.getProperty("user.dir") + File.separator + "target" + File.separator

			+ "bookSearchResult.xlsx";

	static Workbook workbook = new XSSFWorkbook();

	static Sheet sheet = workbook.createSheet("test");

	static FileOutputStream fileOut;

	static Font font = workbook.createFont();

	static CellStyle cellStyle = workbook.createCellStyle();

	static String columnNames[] = { "Book_Name", "Book_Info", "Book_ISBN", "Max_Book_Price", "Min_Book_Price" };

	public ArrayList<String> getBookList() throws EncryptedDocumentException, IOException {
		list = new ArrayList<>();
		Workbook workbook = WorkbookFactory.create(new File(bookList));
		Sheet workSheet = workbook.getSheet("Sheet1");
		int rowNum = workSheet.getPhysicalNumberOfRows();
		System.out.println(rowNum);
		for (int i = 1; i < rowNum; i++) {
			String book = workSheet.getRow(i).getCell(0).getStringCellValue().toString();
			System.out.println(book);

			System.out.println("***");
			list.add(book);
		}
		return list;
	}

	public static void main(String[] args) {

	}

	// prepare excel file with headers

	public static void prepareFile() {

		Font headerFont = workbook.createFont();

		headerFont.setBold(true);

		headerFont.setFontHeightInPoints((short) 16);

		headerFont.setColor(IndexedColors.BLACK.index);

		CellStyle headerStyle = workbook.createCellStyle();

		headerStyle.setFont(headerFont);

		headerStyle.setBorderBottom(BorderStyle.MEDIUM);

		headerStyle.setBorderLeft(BorderStyle.MEDIUM);

		headerStyle.setBorderRight(BorderStyle.MEDIUM);

		headerStyle.setBorderTop(BorderStyle.MEDIUM);

		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);

		headerStyle.setAlignment(HorizontalAlignment.CENTER);

		Row headerRow = sheet.createRow(0);

		for (int i = 0; i < columnNames.length; i++) {

			Cell cell = headerRow.createCell(i);

			cell.setCellValue(columnNames[i]);

			cell.setCellStyle(headerStyle);

			sheet.setColumnWidth(i, 15000);

		}

		System.out.println("Excel file is ready!");

	}

	public static void writeFile(List<String> info) {
		font.setFontHeightInPoints((short) 12);
		font.setColor(IndexedColors.BLACK.index);
		cellStyle.setFont(font);
		cellStyle.setBorderBottom(BorderStyle.MEDIUM);
		cellStyle.setBorderLeft(BorderStyle.MEDIUM);
		cellStyle.setBorderRight(BorderStyle.MEDIUM);
		cellStyle.setBorderTop(BorderStyle.MEDIUM);
		cellStyle.setAlignment(HorizontalAlignment.LEFT);
		cellStyle.setWrapText(true);

		int rowNum = sheet.getLastRowNum();
		Row row = sheet.createRow(rowNum + 1);

		for (int i = 0; i < columnNames.length; i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(info.get(i));
			cell.setCellStyle(cellStyle);
		}

		try {
			fileOut = new FileOutputStream(excelFile);
			workbook.write(fileOut);
			fileOut.close();

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
