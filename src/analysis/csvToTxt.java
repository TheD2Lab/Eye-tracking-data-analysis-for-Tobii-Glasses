package analysis;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class csvToTxt {
    public static void main(String[] args) throws Exception{
        String inputFilePath = "input/p3/ArunData.tsv";
        String outputFilePath = "output/p3/p3_test.txt";
        String[][] resultArray;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));

            List<String> lines = Files.readAllLines(Paths.get(inputFilePath), StandardCharsets.UTF_8);

            //lines.removeAll(Arrays.asList("", null)); // <- remove empty lines

            resultArray = new String[lines.size()][];

            for (int i = 1; i < lines.size(); i++) {
                resultArray[i] = lines.get(i).split("\t", -1); //tab-separated
                writer.write(resultArray[i][26] + " " + resultArray[i][54] + " " + resultArray[i][55] + " " + resultArray[i][56] + " " + resultArray[i][75] + " " + resultArray[i][76]
                        + " " + resultArray[i][79] + " " + resultArray[i][81] + " " + resultArray[i][83] + " " + resultArray[i][57] + " " + resultArray[i][58] + " " + resultArray[i][77] + " " + resultArray[i][78]
                        + " " + resultArray[i][80] + " " + resultArray[i][82] + " " + resultArray[i][84]);
                writer.newLine();
            }

            /*for(int x = 0; x < resultArray[0].length; x++) {
                System.out.println(x + ": " + resultArray[x].length);
            }*/
            writer.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
