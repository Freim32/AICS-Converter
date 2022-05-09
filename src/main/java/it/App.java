package it;



import com.formdev.flatlaf.FlatLightLaf;

import it.Gui.Window;






public final class App {

    public static String system = System.getProperty("os.name").toLowerCase();
    public static String homePath = System.getProperty("user.home","Downloads");

    
    public static void main(String[] args) {

       
        System.setProperty("awt.useSystemAAFontSettings","on");
        System.setProperty("swing.aatext", "true");
        
        FlatLightLaf.setup();

        if (system.indexOf("nix") >= 0 || system.indexOf("nux") >= 0){
            system="Linux";
            homePath+="/Scaricati";
        }else if (system.indexOf("win") >= 0){
            system="Windows";
            homePath+="\\Desktop";
        }else if (system.indexOf("mac") >= 0){
            system="Mac";
            homePath+="/Desktop";
        }


        
        new Window();
        
    
    }
}

