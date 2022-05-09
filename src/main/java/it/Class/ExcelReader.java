package it.Class;
//java.io
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
//Java.utils
import java.util.ArrayList;
//org.org.apache.poi
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//Class definition
public class ExcelReader {

    ArrayList<ArrayList<String>> data ;
    DataFormatter dataFormatter;
    //Constructor
    public ExcelReader(){
    }

    //Method that reads the excel files and returns a list of lists that contain the row/columns of the file
    public  ArrayList<ArrayList<String>> ReadExcel(File Path) throws Exception{
        dataFormatter = new DataFormatter();//For format cell value
        data = new ArrayList<ArrayList<String>>();//List which will contain the rows/columns
        //Read file from Path -->
        try{
        FileInputStream excelFile = new FileInputStream(Path);
        try (Workbook workbook = new XSSFWorkbook(excelFile)) {
            Sheet sheet = workbook.getSheetAt(0);
            //<-- Read file from Path 
                //Add ArrayList to data for each row in file, excluding the header 
                for(int i=0; i<=sheet.getLastRowNum();i++){data.add(new ArrayList<String>());}

                //For each row in file 
                for (int r=0; r<=sheet.getLastRowNum();r++){
                    Row row= sheet.getRow(r);
                    
                    if (row!=null){
                    //for each cell in row 
                    for (int c=0;c<row.getLastCellNum();c++) {
                        Cell cell = row.getCell(c);
                        //Check the type of cell 
                        if (cell!=null){
                        switch (cell.getCellType()){
                            case BLANK :    // if the cell is blank
                                data.get(r).add("");
                                break;
                            case NUMERIC :  // if the cell is numeric 
                                data.get(r).add(dataFormatter.formatCellValue(cell).strip()); // I transform the cell into a string
                                break;
                            case STRING:    // if the cell is a string 
                                data.get(r).add(cell.toString().strip());
                                break;
                            default : // there is some error in the file
                                throw new Exception("Errore di lettura");    //Rise an error 
                            }
                        }else{
                            data.get(r).add("");
                        }
                    } 
                }
            }return data; //return 

        }catch(Exception e){
            throw new Exception("Il file Excel non è in un formato valido");
        }
    }catch (FileNotFoundException e){
        throw new Exception("File o directory inesistente");
    }
    }
}
