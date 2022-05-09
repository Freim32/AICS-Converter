package it.Gui.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Timer;
import java.awt.Desktop;

import it.Gui.Window;
import it.Gui.MyComponent.MyButton;
import it.Gui.MyComponent.MyButton2;
import it.Gui.MyComponent.Timer.TimerEnableButton;

public class TutorialActionListener implements ActionListener {

    MyButton templateButton;
    MyButton downloadButton;
    MyButton2 tutorialButton;
    long timerButtonEnabled;

    public TutorialActionListener(MyButton templateButton,MyButton downloadButton, MyButton2 tutorialButton,long timerButtonEnabled) {
        this.templateButton=templateButton;
        this.downloadButton=downloadButton;
        this.tutorialButton=tutorialButton;
        this.timerButtonEnabled=timerButtonEnabled;
    }
    @Override
    public void actionPerformed(ActionEvent arg0) {

        String inputPdf = "/it/Files/Tutorial.pdf";
        Path tempOutput;
        try {
            tempOutput = Files.createTempFile("TempManual", ".pdf");
        tempOutput.toFile().deleteOnExit();
        System.out.println("tempOutput: " + tempOutput);
        try (InputStream is = getClass().getResourceAsStream(inputPdf)) {
            Files.copy(is, tempOutput, StandardCopyOption.REPLACE_EXISTING);
        }
        Desktop.getDesktop().open(tempOutput.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        templateButton.setEnabled(false);
        downloadButton.setEnabled(false);
        tutorialButton.setEnabled(false);
        Window.timerEnableButton.cancel();
        Window.timerEnableButton=new Timer();
        Window.timerEnableButton.schedule(new TimerEnableButton(templateButton, downloadButton,tutorialButton), timerButtonEnabled);

        
    }
    
}
