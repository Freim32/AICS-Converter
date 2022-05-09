package it.Gui.MyComponent.Timer;

import java.util.TimerTask;


import it.Gui.Window;
import it.Gui.MyComponent.MyButton;
import it.Gui.MyComponent.MyButton2;


public class TimerEnableButton extends TimerTask{

    MyButton templateButton;
    MyButton downloadButton;
    MyButton2 tutorialButton;
    public TimerEnableButton(MyButton templateButton ,MyButton downloadButton,MyButton2 tutorialButton){
        super();
        this.templateButton=templateButton;
        this.downloadButton=downloadButton;
        this.tutorialButton=tutorialButton;
    }

    @Override
    public void run() {
            templateButton.setEnabled(true);
            downloadButton.setEnabled(true);
            tutorialButton.setEnabled(true);
            Window.timerEnableButton.cancel();
    }
}

