package org.openCart.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;


public class ExcelUtils {

    private static Logger excelLogger =LogManager.getLogger(ExcelUtils.class);
    private FileInputStream fs;
    private XSSFWorkbook wb;
    private XSSFSheet sheet;
    private XSSFRow row;
    private XSSFCell cell;
    private String path;


    public ExcelUtils(String filePath){
        this.path=filePath;
    }

    /**
     * Returns the number of rows in the sheet
     * @param sheetName sheet name of the excel file
     * @return row count
     * @throws IOException throws IOException if file is not found
     * @author shchak
     */
    public int getRowCount(String sheetName) throws  IOException{
        fs = new FileInputStream(path);
        wb = new XSSFWorkbook(fs);
        sheet = wb.getSheet(sheetName);
        fs.close();
        return sheet.getLastRowNum() - sheet.getFirstRowNum();
    }

    /**
     * Returns the number of cells in a row
     * @param sheetName sheet name of excel file
     * @param rowNum row number
     * @return cell count
     * @throws IOException throws IOException if file is not found
     * @author shchak
     */
    public int getCellCount(String sheetName, int rowNum) throws IOException{
        fs = new FileInputStream(path);
        wb = new XSSFWorkbook(fs);
        sheet = wb.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        fs.close();
        return row.getLastCellNum();
    }

    /**
     * Returns the cell data
     * @param sheetName sheet name of excel file
     * @param rowNum row number
     * @param colNum column number
     * @return cell data in string format
     * @throws IOException throws IOException if cell value is empty
     */
    public String getCellData(String sheetName, int rowNum, int colNum) throws IOException{
        fs = new FileInputStream(path);
        wb = new XSSFWorkbook(fs);
        sheet = wb.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        cell=row.getCell(colNum);
        //DataFormatter is used to get data in string format
        DataFormatter formatter = new DataFormatter();
        String data;
        try {
            data = formatter.formatCellValue(cell);
        } catch (Exception e) {
            data = " ";
        }
        fs.close();
        return data;
    }
}
