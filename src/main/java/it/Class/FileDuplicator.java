package it.Class;

//Import 
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import org.apache.commons.io.FileUtils;

//Class definition
public class FileDuplicator {
    
    //General attributes
    URL template = getClass().getResource("/it/Files/Template.xlsx");  //Get the file to copy 
    File check; 
    File [] files;
    String name;
    Integer i;
    
    //Copy method 
    public void CopyFile(String path) throws Exception {
        try {
            FileUtils.copyURLToFile(template, new File(CreateName(path)));
        } catch (Exception e) {
            throw new Exception("Directory not found");
        }
    }
    private String CreateName(String path) throws Exception{
        check = new File(path);
        name = "/Template.xlsx";
        i = 1; 
            
        if (check.isDirectory()){
        
            files=check.listFiles();
            
            while(Arrays.stream(files).anyMatch(new File (path+name):: equals)){
                name="/Template ("+i.toString()+").xlsx";
                i+=1;
            }
        }else{
            throw new Exception("Directory not found");
        }
        return path+name;
    }
}
