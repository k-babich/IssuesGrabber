package com.apptimized.tools.randomizer.Utils;
import com.apptimized.tools.randomizer.JiraEntities.Issue;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ExcelWorker {

    String[] titles = {
            "#",
            "Project",
            "Key",
            "Summary",
            "Issue type",
            "Status",
            "Priority",
            "Software ID",
            "Application name",
            "Application vendor",
            "Application version",
            "Description",
            "Category",
            "Assignee",
            "Reporter",
            "Packager",
            "QA Engineer",
            "Packaging technology",
            "SitePkg",
            "SiteQA",
            "Cost centre",
            "Created",
            "Due date",
            "Resolved"
    };

    public void writeXLSXFileWithIssues(String filename, String sheetName, ArrayList<Issue> issues) throws IOException {

        String excelFileName = filename + ".xlsx";//name of excel file

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet(sheetName);


        XSSFRow row = sheet.createRow(0);
        row.setHeightInPoints(14f);

        for (int c = 0; c < titles.length; c++ )
        {
            XSSFCell cell = row.createCell(c);
            cell.setCellValue(titles[c]);
        }

        for (int r = 0; r < issues.size(); r++ )
        {
            row = sheet.createRow(r + 1);
            row.setHeightInPoints(14f);

            String[] buffer = issues.get(r).getParamsAsArray();

            XSSFCell cell = row.createCell(0);
            cell.setCellValue(r + 1);

            for (int i = 0; i < buffer.length; i++) {
                cell = row.createCell(i + 1);
                cell.setCellValue(buffer[i]);
            }
        }

        for(int i = 0; i < titles.length; i++) {
            sheet.autoSizeColumn(i);
        }

        FileOutputStream fileOut = new FileOutputStream(excelFileName);

        wb.write(fileOut);
        fileOut.flush();
        fileOut.close();
    }


}
