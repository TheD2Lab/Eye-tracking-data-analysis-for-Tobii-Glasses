package analysis;

import java.io.*;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class xlsxToFxdTxt {
    /**
     * Sums the duration of all GazeEvent Durations(46) from the xlsx
     *
     * @param wb
     * @param rowIndex
     * @return total fixation duration
     */
    public static int getDuration(XSSFWorkbook wb, int rowIndex) {
        int x = 1;
        if (wb.getSheetAt(0).getRow(rowIndex).getCell(46).getStringCellValue().equals("Unclassified")) {
            System.out.println("Error: Wrong Starting Point");
            return -1;
        }
        int duration = (int) (wb.getSheetAt(0).getRow(rowIndex).getCell(47).getNumericCellValue());
        while (rowIndex + x < wb.getSheetAt(0).getLastRowNum() - 1 && wb.getSheetAt(0).getRow(rowIndex + x).getCell(46).getStringCellValue().equals("Unclassified")) {
            duration += (int) (wb.getSheetAt(0).getRow(rowIndex + x).getCell(47).getNumericCellValue());
            x++;
        }
        return duration;
    }

    /**
     * Converts Tobii Export Data(.xlsx) to a readable format for the program
     *
     * @param inputFilePath
     * @param outputFilePath
     */
    public static void convert(String inputFilePath, String outputFilePath) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));
            InputStream reader = new FileInputStream(inputFilePath);
            XSSFWorkbook wb = new XSSFWorkbook(inputFilePath);
            int fixationNumber, timestamp, eachFixationDuration, eachFixationX, eachFixationY;
            double saccadicAmp, absSacDir, relSacDir;

            //Retrieves all the necessary data from the .xlsx file and writes them to an output file
            for (int x = 1; x < wb.getSheetAt(0).getLastRowNum(); x++) {
                if (wb.getSheetAt(0).getRow(x).getCell(44).getCellType() != CellType.STRING) {
                    if (wb.getSheetAt(0).getRow(x).getCell(46).getStringCellValue().equals("Fixation")) {
                        if (!wb.getSheetAt(0).getRow(x).getCell(49).getStringCellValue().equals("")) {
                            fixationNumber = (int) (wb.getSheetAt(0).getRow(x).getCell(44).getNumericCellValue());
                            timestamp = (int) (wb.getSheetAt(0).getRow(x).getCell(26).getNumericCellValue());
                            //eachFixationDuration = (int) (wb.getSheetAt(0).getRow(x).getCell(47).getNumericCellValue()); //Duration of one fixation
                            eachFixationDuration = getDuration(wb, x);
                            eachFixationX = Integer.parseInt(wb.getSheetAt(0).getRow(x).getCell(48).getStringCellValue());
                            eachFixationY = Integer.parseInt(wb.getSheetAt(0).getRow(x).getCell(49).getStringCellValue());
                            if(wb.getSheetAt(0).getRow(x).getCell(50).getCellType().equals(CellType.STRING) || Double.isNaN((wb.getSheetAt(0).getRow(x).getCell(50).getNumericCellValue()))){
                                saccadicAmp = 0.0;
                            } else {
                                saccadicAmp = (wb.getSheetAt(0).getRow(x).getCell(50).getNumericCellValue());
                            }
                            if(wb.getSheetAt(0).getRow(x).getCell(51).getCellType().equals(CellType.STRING) || Double.isNaN((wb.getSheetAt(0).getRow(x).getCell(51).getNumericCellValue()))){
                                absSacDir = 0.0;
                            } else {
                                absSacDir = (wb.getSheetAt(0).getRow(x).getCell(51).getNumericCellValue());
                            }
                            if(wb.getSheetAt(0).getRow(x).getCell(52).getCellType().equals(CellType.STRING) || Double.isNaN((wb.getSheetAt(0).getRow(x).getCell(52).getNumericCellValue()))){
                                relSacDir = 0.0;
                            } else {
                                relSacDir = (wb.getSheetAt(0).getRow(x).getCell(52).getNumericCellValue());
                            }
                            writer.write(fixationNumber + " " + timestamp + " " + eachFixationDuration + " " + eachFixationX + " " + eachFixationY + " " + saccadicAmp + " " + absSacDir + " " + relSacDir);
                            writer.newLine();
                        }
                    }
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        //String inputFilePath = "input/Tobii Exports/ASD Raw Eye Tracking Data XLSX/ASD Eye Tracking_ASD Eye Tracking Data_SD1_013.xlsx";
        String outputFilePath = "output/Tobii FXD Exports/";

        File directoryPath = new File("input/Tobii Exports/ASD Raw Eye Tracking Data XLSX");
        File[] files = directoryPath.listFiles();
        for (File file : files) {
            convert(file.getAbsolutePath(), outputFilePath + file.getName() + ".txt");
        }

        //convert(inputFilePath, outputFilePath);
    }
}
