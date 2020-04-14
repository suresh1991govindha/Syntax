package Excell;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Config_manager.FileReadManager;

	public class ReadColumn   {
		
		
	
		public static ArrayList<String> getColumn0() throws Throwable
		{
			  String excellpath = FileReadManager.getInstance().getCr().getExcellpath();
			  String getsheetname = FileReadManager.getInstance().getCr().getsheetname();
			  String column0 = FileReadManager.getInstance().getCr().getColumn0();
			
		
			
			
			ArrayList<String> list=new ArrayList<String>();
			
			File file=new File(excellpath);
			FileInputStream fis=new FileInputStream(file);
			XSSFWorkbook workbook=new XSSFWorkbook(fis);
		
			int numberOfSheets = workbook.getNumberOfSheets();
			
			for (int i = 0; i < numberOfSheets; i++) {
				
				if(workbook.getSheetName(i).equalsIgnoreCase(getsheetname))
				{
				XSSFSheet sheetAt = workbook.getSheetAt(i);
			
				Iterator<Row> rows = sheetAt.iterator();
				
				Row next =  rows.next();
				
				Iterator<Cell> cellIterator = next.cellIterator();
				
				int k=0;
				int column=0;
				
				while(cellIterator.hasNext())
				{
					Cell cellvalue = cellIterator.next();
				
					 if(cellvalue.getStringCellValue().equalsIgnoreCase(column0))
					 {
				
					    
						 column=k;
						// System.out.println(column);
					 }
				k++;
				}
				
				while(rows.hasNext())
				{
					Row r = rows.next();
					if(r.getCell(column).getCellTypeEnum()==CellType.STRING)
					{
					
				String stringCellValue = r.getCell(column).getStringCellValue();
			//	System.out.println(stringCellValue);
				list.add(stringCellValue);
				}else if(r.getCell(column).getCellTypeEnum()==CellType.BLANK)
				{
					RichTextString richStringCellValue = r.getCell(column).getRichStringCellValue();
					list.add(" ");
					//System.out.println("blank"+richStringCellValue);
					
				}else {
					

					 Long numericCellValue = (long) r.getCell(column).getNumericCellValue();
					 list.add(String.valueOf(numericCellValue));
					// System.out.println(numericCellValue);
					 
					 
				}
				}
				
				}}
			return list;}}
