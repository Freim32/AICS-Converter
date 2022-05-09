package it.Gui.ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.awt.Color;

import it.Class.FileDuplicator;
import it.Gui.Window;
import it.Gui.MyComponent.MyButton;
import it.Gui.MyComponent.MyButton2;
import it.Gui.MyComponent.MyLabel;
import it.Gui.MyComponent.MyTextField;
import it.Gui.MyComponent.Timer.TimerEnableButton;
import it.Gui.MyComponent.Timer.TimerTextDissolve;

public class TemplateActionListener implements ActionListener{

    FileDuplicator fileDuplicator = new FileDuplicator();
    MyLabel popupText;
    MyButton templateButton;
    MyButton downloadButton;
    MyButton2 tutorialButton;
    MyTextField outputTextField;
    long textTimerStart;
    long textTimerTick;
    long timerButtonEnabled;
    public TemplateActionListener(MyLabel popupText,MyButton templateButton ,MyButton downloadButton ,MyButton2 tutorialButton,MyTextField outputTextField ,long textTimerStart,long textTimerTick ,long timerButtonEnabled){
        this.popupText=popupText;
        this.textTimerStart=textTimerStart;
        this.textTimerTick=textTimerTick;
        this.timerButtonEnabled=timerButtonEnabled;
        this.templateButton=templateButton;
        this.downloadButton=downloadButton;
        this.tutorialButton=tutorialButton;
        this.outputTextField=outputTextField;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

        
        try {
            String path = outputTextField.getText();
            fileDuplicator.CopyFile(path);

            popupText.setForeground(Color.GREEN);
            
            int pathLength = path.length();

            int i = pathLength - 1;

            for (; i >= 0; i--) {
                char c = path.charAt(i);

                if (c == '/' || c == '\\') {
                    break;
                }
            }

            popupText.setText("Template scaricato nella cartella "+ path.substring(i, pathLength)); 
        } catch (Exception e) {
            e.printStackTrace();
            popupText.setForeground(Color.RED);
            popupText.setText("Directory non valida"); 
        }
        
        //timer
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
