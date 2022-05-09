package it.Gui.ActionListener;

import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

import it.App;
import it.Gui.Window;
import it.Gui.MyComponent.MyFileChooser;
import it.Gui.MyComponent.MyTextField;


public class OutputActionListener implements ActionListener{

    MyTextField outputTextField;
    Window window;
    String dir;
    String system;

    public OutputActionListener(MyTextField outputTextField,Window window){
        this.outputTextField=outputTextField;
        this.window = window;
        this.system=App.system;
    }

    private void runOnMac(){
        System.setProperty("apple.awt.fileDialogForDirectories", "true");
        FileDialog fd=new FileDialog(window,"Scegli una cartella dove salvare i file ",FileDialog.LOAD);
        fd.setDirectory(App.homePath);
        fd.setVisible(true);
        dir =fd.getDirectory()+fd.getFile();
        if (!(dir== null) && !dir.equals("nullnull")){
            outputTextField.setText(dir);
        }
        System.setProperty("apple.awt.fileDialogForDirectories", "false");
    }

    private void runOnWin(){
        try {UIManager.setLookAndFeel(new FlatLightLaf());} catch (Exception e) {}
        MyFileChooser chooser= new MyFileChooser();
        chooser.setDialogTitle("Scegli una Directory");
        chooser.setCurrentDirectory(new File(App.homePath));
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.showOpenDialog(window);
        if(!(chooser.getSelectedFile()==null)){
            outputTextField.setText(chooser.getSelectedFile().getAbsolutePath());
        }
    }

    private void runOnUnix(){
        try {UIManager.setLookAndFeel(new FlatLightLaf());} catch (Exception e) {}
        MyFileChooser chooser= new MyFileChooser();
        chooser.setDialogTitle("Scegli una Directory");
        chooser.setCurrentDirectory(new File(App.homePath));
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.showOpenDialog(window);
        if(!(chooser.getSelectedFile()==null)){
            outputTextField.setText(chooser.getSelectedFile().getAbsolutePath());
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
}