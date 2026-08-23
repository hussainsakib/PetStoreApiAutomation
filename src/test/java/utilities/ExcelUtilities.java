package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {
	
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook wb;
	public XSSFSheet sh;
	public XSSFRow row;
	public XSSFCell cell;
	public String cellStyle;
	 String path;
	
	 public ExcelUtilities(String path) {
		 
		 this.path=path;
	 }
	 
	 public int getRowCount(String sheetName) throws IOException {
		 
		 fi=new FileInputStream(path);
		 wb=new XSSFWorkbook(fi);
		 sh=wb.getSheet(sheetName);
		 int row=sh.getLastRowNum();
		 wb.close();
		 fi.close();
		 
		  return row;
		  }
	 
	   public int getCellCount(String sheetName, int rowNum) throws IOException {
	   
	   fi=new FileInputStream(path);
		 wb=new XSSFWorkbook(fi);
		 sh=wb.getSheet(sheetName);
		  row=sh.getRow(0);
		  int cell=row.getLastCellNum();
		  wb.close();
		  fi.close();
		   return cell;
	   }
	   
	   public String getCellData(String sheetName, int rowNum, int cellNum) throws IOException {
		   
		   
		   fi=new FileInputStream(path);
		   wb= new XSSFWorkbook(fi);
		   sh=wb.getSheet(sheetName);
		   row=sh.getRow(rowNum);
		   cell=row.getCell(cellNum);
		  
		   DataFormatter formatter = new DataFormatter();
		   String data;
		   try {
			   
			   data=formatter.formatCellValue(cell);
		   }
		   catch(Exception e) {
			   e.printStackTrace();
			   data="";
		   }
		   wb.close();
		  fi.close();
		 
		   return data;
		   
	   }
	   
	   public void setCellData(String sheetName , int rowNum , int cellNum , String data) throws InvalidFormatException, IOException {
		   
		   File myFile = new File(path);
		   if(!myFile.exists()) {
			   
			   wb= new XSSFWorkbook();
			   fo= new FileOutputStream(path);
			   wb.write(fo);
		   }
		   
		   fi=new FileInputStream(path);
		   wb=new XSSFWorkbook(fi);
		   
		   if(wb.getSheetIndex(sheetName)==-1) {
			   
			   wb.createSheet(sheetName);
			   sh=wb.getSheet(sheetName);
		   }
		   
		   if(sh.getRow(rowNum)==null) {
			   
			   sh.createRow(rowNum);
			   row=sh.getRow(rowNum);
		   }
		   if(row.getCell(cellNum)==null) {
			   
			   row.createCell(cellNum);
			   cell=row.getCell(cellNum);
			   
		   }
		   
		   cell.setCellValue(data);
		   fo=new FileOutputStream(path);
		   wb.write(fo);
		   wb.close();
		   fo.close();
		   fi.close();
		   
		   
	   }
}
