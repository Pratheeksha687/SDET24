package com.crm.comcast.genericutility;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import 	org.apache.poi.ss.usermodel.Row;
/**
 * 
 * @author Pratheeksha
 *
 */

public class ExcelUtility {
	/**
	 * it is used to read data from excel based on below arguments
	 * @param sheet
	 * @param row
	 * @param cell
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public String getData(String sheet,int row,int cell) throws EncryptedDocumentException, IOException
	{
		String val="";
		FileInputStream fis=new FileInputStream("./data/excel1.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		Cell cell1=wb.getSheet(sheet).getRow(row).getCell(cell);
		val=cell1.toString();
		return val;
	}
	/**
	 * 
	 * @param Sheet
	 * @param row
	 * @param cell
	 * @param val
	 * @throws Throwable
	 */
	public void createData(String Sheet,int row,int cell,String val) throws Throwable
	{
	FileInputStream fis=new FileInputStream("./data/excel1.xlsx");
	Workbook wb=WorkbookFactory.create(fis);
	Cell ce=wb.getSheet(Sheet).getRow(row).getCell(cell);
	ce.setCellValue(val);
	FileOutputStream fout=new FileOutputStream("./data/excel1.xlsx");
		}
	}



