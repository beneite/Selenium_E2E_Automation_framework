package com.asismisr.utils.dataSender;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import java.io.*;

public class dataSending {
    @DataProvider(name = "dataSendingPath")
    public String[][] sendDatas() throws IOException {
        InputStream inputStream=new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\test-data\\saucedemo\\Dataentry.xlsx"));
        Workbook workbook=WorkbookFactory.create(inputStream);
        Sheet sheet=workbook.getSheetAt(0);
        int totalRows=sheet.getLastRowNum();
        int totalCells=sheet.getRow(0).getLastCellNum();
        String[][] search=new String[totalRows][totalCells];
        DataFormatter formatter=new DataFormatter();
        for (int i=1;i<=search.length;i++)
        {
            for (int j=0;j<search[i-1].length;j++)
            {
                search[i-1][j]=formatter.formatCellValue(sheet.getRow(i).getCell(j));
            }
        }
        return search;
    }
}
