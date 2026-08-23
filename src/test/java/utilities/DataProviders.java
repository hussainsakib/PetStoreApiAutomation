package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	@DataProvider(name="apiData")
	public String[][] getAllData() throws IOException{
		
		String path=System.getProperty("user.dir")+"//testData//RestAssuredDDT.xlsx";
		
		ExcelUtilities xlUtils = new ExcelUtilities(path);
		
		int rowCount=xlUtils.getRowCount("sheet1");
		int cellCount=xlUtils.getCellCount("Sheet1", 1);
		
		String apiData[][]= new String[rowCount][cellCount];
		
		
		for(int i=1;i<=rowCount;i++) {
			
			for(int j=0;j<cellCount;j++) {
				
				apiData[i-1][j]=xlUtils.getCellData("sheet1", i, j);
			}
			
		}
		return apiData;
	}
	@DataProvider(name="userName")
	public String[] getUserName() throws IOException {
		
		String path=System.getProperty("user.dir")+"//testData//RestAssuredDDT.xlsx";
		
		ExcelUtilities xlUtils = new ExcelUtilities(path);
		
		int rowCount=xlUtils.getRowCount("Sheet1");
		
		String userName[]=new String[rowCount];
		
		for(int i=1;i<=rowCount;i++) {
			
			userName[i-1]=xlUtils.getCellData("sheet1", i, 1);
		}
		return userName;
	}

}
