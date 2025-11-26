package com.asismisr.utils.dataProvide;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import java.io.*;

public class DataSender {

    @DataProvider(name = "dataproviding", parallel = true, t)
    public String[][] dataSending() throws IOException {
        InputStream inputStream=new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\test-data\\cargoRunner\\Datas_Milestone.xlsx"));
        Workbook workbook=WorkbookFactory.create(inputStream);
        Sheet sheet=workbook.getSheetAt(0);
        int rowNum=sheet.getLastRowNum();
        int cellNum=sheet.getRow(0).getLastCellNum();
        String[][] field=new String[rowNum][cellNum];
        DataFormatter dataFormatter=new DataFormatter();
        for (int i=1;i<=rowNum;i++)
        {
            for (int j=0;j<cellNum;j++)
            {
                field[i-1][j]=dataFormatter.formatCellValue(sheet.getRow(i).getCell(j));
            }
        }
        return field;
    }
}
