package analysis;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class xlsxToTxt {
    public static void main(String[] args) throws Exception{
        String inputFilePath = "input/Tobii Exports/ASD Raw Eye Tracking Data XLSX/ASD Eye Tracking_ASD Eye Tracking Data_SD1_013.xlsx";
        String outputFilePath = "output/p3/p13_test.txt";
        String[][] resultArray;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));
            InputStream reader = new FileInputStream(inputFilePath);
            XSSFWorkbook wb = new XSSFWorkbook(inputFilePath);

//            System.out.println(wb.getSheetAt(0).getRow(0).getCell(3));
//            System.out.println(wb.getSheetAt(0).getRow(0).getCell(wb.getSheetAt(0).getRow(0).getLastCellNum()-1));

            for(int x = 0; x < wb.getSheetAt(0).getRow(0).getLastCellNum(); x++)
            {
                System.out.println("[" + x + "] " + wb.getSheetAt(0).getRow(0).getCell(x));
            }
            System.out.println();

            writer.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
