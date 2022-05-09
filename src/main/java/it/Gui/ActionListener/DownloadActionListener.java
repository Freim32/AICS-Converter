package it.Gui.ActionListener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

import it.Class.FromXlsxToCsv;
import it.Gui.Window;
import it.Gui.MyComponent.MyButton;
import it.Gui.MyComponent.MyButton2;
import it.Gui.MyComponent.MyLabel;
import it.Gui.MyComponent.MyTextField;
import it.Gui.MyComponent.Timer.TimerEnableButton;
import it.Gui.MyComponent.Timer.TimerTextDissolve;

public class DownloadActionListener implements ActionListener{

    
    MyLabel popupText;
    MyButton templateButton;
    MyButton downloadButton;
    MyButton2 tutorialButton;
    MyTextField inputTextField;
    MyTextField outputTextField;
    long textTimerStart;
    long textTimerTick;
    long timerButtonEnabled;
    public DownloadActionListener(MyLabel popupText,MyButton templateButton ,MyButton downloadButton,MyButton2 tutorialButton,long textTimerStart,long textTimerTick,long timerButtonEnabled,MyTextField inputTextField ,MyTextField outputTextField){
        this.popupText=popupText;
        this.textTimerStart=textTimerStart;
        this.textTimerTick=textTimerTick;
        this.timerButtonEnabled=timerButtonEnabled;
        this.templateButton=templateButton;
        this.downloadButton=downloadButton;
        this.tutorialButton=tutorialButton;
        this.inputTextField=inputTextField;
        this.outputTextField=outputTextField;
    }
    @Override
    public void actionPerformed(ActionEvent event) {

        try {
            String Excelpath = inputTextField.getText();
            String CsvPath = outputTextField.getText();
            
            FromXlsxToCsv.fromXlsxToCsv(Excelpath,CsvPath);

            int pathLength = CsvPath.length();
            
            int i = pathLength - 1;
            
            for (; i >= 0; i--) {
                char c = CsvPath.charAt(i);  
                if (c == '/' || c == '\\') {
                    break;
                }
            }
            
            popupText.setForeground(Color.GREEN);
            popupText.setText("Csv scaricato nella cartella "+ CsvPath.substring(i, pathLength)); 
        } catch (Exception e) {
            popupText.setForeground(Color.RED);
            popupText.setText(e.getMessage()); 
        }

    
        templateButton.setEnabled(false);
        downloadButton.setEnabled(false);
        tutorialButton.setEnabled(false);
        Window.timerEnableButton.cancel();
        Window.timerEnableButton=new Timer();
        Window.timerEnableButton.schedule(new TimerEnableButton(templateButton, downloadButton,tutorialButton), timerButtonEnabled);


        Window.timerTextDissolve.cancel();
        Window.timerTextDissolve=new Timer();
        Window.timerTextDissolve.schedule(new TimerTextDissolve(popupText),textTimerStart,textTimerTick);
    }
    
}