package it.Class;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.commons.compress.utils.FileNameUtils;


public class FromXlsxToCsv {
    
    static ExcelReader excelReader = new ExcelReader();
    static ExcelValidator excelValidator = new ExcelValidator();
    static CsvWriter csvWriter= new CsvWriter();
    static Utils utils= new Utils();


    public FromXlsxToCsv() {
    }

    public static void fromXlsxToCsv(String ExcelPath,String CsvPath) throws Exception{
        
        
        try {
            File excelPath = new File(ExcelPath);
            File csvPath = new File(CreateName(CsvPath,ExcelPath));
            ArrayList<ArrayList<String>> data =excelReader.ReadExcel(excelPath);
            utils.MakeRowEquals(data);
            utils.CheckIfTemplate(data);
            utils.CheckIfEmpty(data);
            excelValidator.validate(data);
            csvWriter.WriteCsv(csvPath, data);

        } catch (Exception e) {
            //JOptionPane.showMessageDialog(new JFrame() ,e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
    
    private static String CreateName(String CsvPath, String ExcelPath){
        File [] files;
        String basename="/"+FileNameUtils.getBaseName(ExcelPath);
        String name=basename+".csv";
        Integer i=1;
        
        files=new File(CsvPath).listFiles();
        
            while(Arrays.stream(files).anyMatch(new File (CsvPath+name):: equals)){
                name="/"+basename+" ("+i.toString()+").csv";
                i+=1;
            }
        
        return CsvPath+name;
    }

}
