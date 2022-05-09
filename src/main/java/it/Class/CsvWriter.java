package it.Class;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import com.opencsv.CSVWriter;

public class CsvWriter {
    
    CSVWriter writer;
    FileWriter csvFile;
    File csvPath;

    public CsvWriter(){}


    public void WriteCsv(File csvPath,ArrayList<ArrayList<String>> data) throws Exception{
        
        try {
            // create FileWriter object with file as parameter
            csvFile = new FileWriter(csvPath);
      
            // create CSVWriter with '~' as separator
            CSVWriter writer = new CSVWriter(csvFile,'~',CSVWriter.NO_QUOTE_CHARACTER,CSVWriter.DEFAULT_ESCAPE_CHARACTER,"\n\r");
      
            for(ArrayList<String> array : data ){
                String[] line = array.toArray(new String[0]);
                writer.writeNext(line);
            }
            writer.close();
        }
        catch (Exception e) {
            throw new Exception("Problema con la scrittura del file csv");
        }
    }
}
