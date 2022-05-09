package it.Gui.ActionListener;


import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;

import com.formdev.flatlaf.FlatLightLaf;

import it.App;
import it.Gui.Window;
import it.Gui.MyComponent.MyFileChooser;
import it.Gui.MyComponent.MyTextField;

public class InputActionListener implements ActionListener{


    MyTextField inputTextField;
    Window window;
    String dir;
    String system;

    public InputActionListener(MyTextField inputTextField,Window window){
        this.inputTextField=inputTextField;
        this.window = window;
        this.system=App.system;
    }

    private void runOnMac(){
        FileDialog fd=new FileDialog(window,"Scegli Un file da trasformare",FileDialog.LOAD);
        fd.setDirectory(App.homePath);
        fd.setFile("*.xlsx");
        fd.setFilenameFilter((dir, name) -> name.endsWith(".xlsx"));
        fd.setVisible(true);
        dir =fd.getDirectory()+fd.getFile();
        if (!(dir== null) && !dir.equals("nullnull")){
            inputTextField.setText(dir);
        }
    }

    private void runOnWin(){
        try {UIManager.setLookAndFeel(new FlatLightLaf());} catch (Exception e) {}
        MyFileChooser chooser= new MyFileChooser();
        chooser.setDialogTitle("Scegli un file da trasformare");
        chooser.setCurrentDirectory(new File(App.homePath));
        chooser.setFileFilter(new FileTypeFilter(".xlsx", "Microsoft Excel Documents"));
        chooser.showOpenDialog(window);
        if(!(chooser.getSelectedFile()==null)){
            inputTextField.setText(chooser.getSelectedFile().getAbsolutePath());
        }
    }

    private void runOnUnix(){
        try {UIManager.setLookAndFeel(new FlatLightLaf());} catch (Exception e) {}
        MyFileChooser chooser= new MyFileChooser();
        chooser.setDialogTitle("Scegli un file da trasformare");
        chooser.setCurrentDirectory(new File(App.homePath));
        chooser.setFileFilter(new FileTypeFilter(".xlsx", "Excel file"));
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.showOpenDialog(window);
        if(!(chooser.getSelectedFile()==null)){
            inputTextField.setText(chooser.getSelectedFile().getAbsolutePath());
        }
    }



    @Override
    public void actionPerformed(ActionEvent arg0) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (system.equals("Mac")){ 
                    runOnMac();   
                }else if (system.equals("Windows")){
                    runOnWin();
                }else if (system.equals("Linux")){
                    runOnUnix();
                }
            }
        });      
    }

    public class FileTypeFilter extends FileFilter {
        private String extension;
        private String description;
     
        public FileTypeFilter(String extension, String description) {
            this.extension = extension;
            this.description = description;
        }
     
        public boolean accept(File file) {
            if (file.isDirectory()) {
                return true;
            }
            return file.getName().endsWith(extension);
        }
     
        public String getDescription() {
            return description + String.format(" (*%s)", extension);
        }
    }
}
