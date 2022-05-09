package it.Gui.MyComponent.Timer;

import java.util.TimerTask;
import java.awt.Color;

import it.Gui.Window;
import it.Gui.MyComponent.MyLabel;

public class TimerTextDissolve extends TimerTask{

    MyLabel popupText;
    int alpha =255;
    int r;
    int g;
    int b;
    public TimerTextDissolve(MyLabel popupText){
        super();
        this.popupText=popupText;
        Color textColor=popupText.getForeground(); 
        r=textColor.getRed();
        g=textColor.getGreen();
        b=textColor.getBlue();
    }

    @Override
    public void run() {
        popupText.setForeground(new Color(r,g,b,alpha));
        this.alpha-=2;
        if (alpha<=0){
            popupText.setForeground(new Color(r,g,b,0));
            Window.timerTextDissolve.cancel();
        }
    }
}
