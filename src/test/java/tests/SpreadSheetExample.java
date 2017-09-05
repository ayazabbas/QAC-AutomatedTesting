package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

@RunWith(Parameterized.class)
public class SpreadSheetExample {

    @Test
    public void spreadSheet(){
        SpreadSheetReader sheetReader = new SpreadSheetReader(System.getProperty("user.dir") + "/src/main/resources/Example_Spreadsheet.xlsx");
        
        int[] numberOfRows = {1,2,3,4};
        for (int rowNo : numberOfRows){
             sheetReader.readRow(rowNo, "Input Data");
        }

        List<String> row = sheetReader.readRow(1, "Input Data");

        for(String cell : row){
            System.out.println(cell);
        }


    }


}
