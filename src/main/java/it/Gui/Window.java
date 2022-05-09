package it.Gui;
//Import
//Java.util
import java.util.Timer;
//Java.awt
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

//Javax.swing
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
//My import 
import it.Gui.MyComponent.MyButton;
import it.Gui.MyComponent.MyButton2;
import it.Gui.MyComponent.MyLabel;
import it.Gui.MyComponent.MyTextField;
import it.App;
import it.Gui.ActionListener.DownloadActionListener;
import it.Gui.ActionListener.InputActionListener;
import it.Gui.ActionListener.OutputActionListener;
import it.Gui.ActionListener.TemplateActionListener;
import it.Gui.ActionListener.TutorialActionListener;

//Class definition
public class Window extends JFrame {
    
    //General attributes
    //Window dimension
    final int windowHeight=500; //Window height
    final int windowWidth=800;  //Window width
    //Timer
    public static Timer timerTextDissolve = new Timer();
    public static Timer timerEnableButton = new Timer();
    //Main Color 
    public final static Color BLUE  = new Color(51, 153, 255); 
    public final static Color DARK_BLUE = new Color(51,51,255);
    final Color WHITE = new Color(255,255,255);
    final Color BLACK = new Color(0,0,0);
    //Logo
    Toolkit tk = Toolkit.getDefaultToolkit();
    final Image logoPNG = tk.getImage(Window.class.getResource("/it/Files/logo.png"));
    final ImageIcon logo130x105 = new ImageIcon (tk.getImage(Window.class.getResource("/it/Files/logo(130x73).png")));
    //Main Font 
    final Font Roboto22 = new Font("Roboto", Font.PLAIN, 22);
    final Font Roboto20 = new Font("Roboto", Font.PLAIN, 20);
    final Font Roboto12 = new Font("Roboto", Font.BOLD, 12);
    //Main Border
    final Border blueBorder = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(BLUE, 2),BorderFactory.createEmptyBorder(0, 5, 0, 5));


    //Objects properties  
    
    //Window ------------------------------------------------------------------

    final static String windowTitle = "AICS-Convertitore Csv"; //Windows title
    final Image windowIcon = logoPNG; //Window Icon
    
    //Timer -------------------------------------------------------------------
    final long textTimerStart=5000;   //after how long the text starts dissolve
    final long textTimerTick=40;    //ow long the text take to dissolve (long * 51)
    final long timerButtonEnabled=2000; // after how long is possible re-press button 
    //JPANEL ------------------------------------------------------------------

    //Background JPanel 
    final Rectangle backgroundBounds = new Rectangle(0, 0, windowHeight, windowWidth); //Background JPanel size and position  
    final Color backgroundColor= WHITE;   //Window background color 
    
    //Colored Section
    final Rectangle coloredSectionBounds = new Rectangle(0,0,150,windowHeight); //Sidebar JPanel size and position 
    final Color coloredSectionColor = BLUE; //Color of the sidebar

    //IMAGE -------------------------------------------------------------------
    final Rectangle logoLabelBounds = new Rectangle(10,175,130,73);
    final ImageIcon logoLabelImage = logo130x105;
    
    //JLABEL ------------------------------------------------------------------

    //1/2 ExplanationText 
    final Font explanationTextFont = Roboto20;  //Font of ExplanationText 
    final Color explanationTextColor= BLACK;    //Color of ExplanationText 
    //ExplanationText1
    final Rectangle explanationText1Bounds= new Rectangle(200,115,400,40);  //ExplanationText1 size and position  
    final String explanation1String = "Directory del file Excel (.xlsx):";  //ExplanationText1 text
    //ExplanationText2
    final Rectangle explanationTest2Bounds= new Rectangle(200,215,400,40);  //ExplanationText2 size and position  
    final String explanation2String = "Directory dove verranno salvati i file:";  //ExplanationText2 text
    //VersionText
    final Rectangle versionTextBounds= new Rectangle(10,435,130,40);    //VersionText size and position
    final String version = "Version: Alpha 0.1.0"; //App version  
    final Color versionTextColor = WHITE;   //Color of VersionText 
    final Font versionTextFont = Roboto12;  //Font of VersionText
    //AuthorText
    final Rectangle authorTextBounds= new Rectangle(660,435,130,40);    //VersionText size and position
    final String author = "By Matteo Benvenuti"; //App version  
    final Color authorTextColor = BLUE;   //Color of VersionText 
    final Font authorTextFont = Roboto12;  //Font of VersionText
    //popupText
    final Rectangle popupTextBounds= new Rectangle(160,400,630,30);    //popText size and position
    final String popupInitialText =""; //popup initial text
    final Font popupTextFont = Roboto22;  //Font of popupText


    //JTextField --------------------------------------------------------------

    //Input/output TextFiled 
    final Border input_outputTextFieldBorder= blueBorder;   //Input/output TextFiled  border
    final Color input_outputTextFieldForegroundColor = BLACK;   //Input/output TextFiled Text Color
    final Color input_outputTextCaretColor= BLUE;   //Input/output TextFiled caret Color 
    //Input TextFiled   
    final Rectangle inputTextFiledBounds= new Rectangle(200, 150, 400, 30); //Input TextFiled size and position
    final String initialInputTextFieldText="";  //Input TextFiled initial text
    //Output TextFiled  
    final Rectangle outputTextFiledBounds= new Rectangle(200, 250, 400, 30);    //Output TextFiled size and position
    final String initialOutputTextFieldText=App.homePath;   //Output TextFiled initial text

    //JBUTTON -----------------------------------------------------------------

    //TemplateButton
    final Rectangle templateButtonBounds= new Rectangle(660, 10, 120, 35);  //TemplateButton size and position
    final int templateButtonRadius = 40; //The rounding of the edges of the Template Button
    final Color templateButtonBackgroundColor=BLUE; //Template button background color
    final Color templateButtonTextColor=WHITE;  //Template button text color
    final Font templateButtonFont= Roboto22; //template button text font
    final String templateButtonText = "Template";   //Template button text 
    //Input/output Button
    final int input_outputButtonRadius   = 30; //The rounding of the edges of the input / output Button
    final Color input_outputButtonBackgroundColor=BLUE; //Input/output button background color
    final Color input_outputButtonTextColor =WHITE; //Input/output button text color 
    final Font input_outputButtonFont= Roboto20; //Input/output button text font
    final String input_outputButtonText = "Sfoglia";    //Input/output button text 
    //Input/output Button
    final Rectangle inputButtonBounds= new Rectangle(650, 150, 100, 30);  //InputButton size and position
    //Input/output Button
    final Rectangle outputButtonBounds= new Rectangle(650, 250, 100, 30);  //OutputButton size and position
    //DownloadButton
    final Rectangle downloadButtonBounds= new Rectangle(410, 330, 130, 40);  //Download Button size and position
    final int downloadButtonRadius = 40; //The rounding of the edges of the download Button
    final Color downloadButtonBackgroundColor=BLUE; //download button background color
    final Color downloadButtonTextColor=WHITE;  //download button text color
    final Font downloadButtonFont= Roboto22; //download button text font
    final String downloadButtonText = "Download";   //download button text 
    //TutorialButton
    final Rectangle tutorialButtonBounds= new Rectangle(15, 10, 120, 35);  //Download Button size and position
    final int tutorialButtonRadius = 40; //The rounding of the edges of the download Button
    final Color tutorialButtonBackgroundColor=WHITE; //download button background color
    final Color tutorialButtonTextColor=BLUE;  //download button text color
    final Font tutorialButtonFont= Roboto22; //download button text font
    final String tutorialButtonText = "Tutorial";   //download button text 

    //Constructor
    public Window() {
        //Window ------------------------------------------------------------------
        
        //Set Window Title
        super(windowTitle);
        //Set Window icon 
        this.setIconImage(windowIcon);

        //JPANEL ------------------------------------------------------------------

        //Create Main JPanel
        JPanel background = new JPanel(null);
        //Set color 
        background.setBackground(backgroundColor);
        //Set size and position 
        background.setBounds(backgroundBounds);
        
        //Create a secondary JPanel
        JPanel coloredSection = new JPanel(null);
        //Set color 
        coloredSection.setBackground(coloredSectionColor);
        //Set size and position 
        coloredSection.setBounds(coloredSectionBounds);
        //Add secondary Jpanel to window
        background.add(coloredSection);
        
        //IMAGE -------------------------------------------------------------------

        //Create Image Container
		JLabel logoLabel = new JLabel();
        //Set size and position 
		logoLabel.setBounds(logoLabelBounds);
        //Insert image
        logoLabel.setIcon(logoLabelImage);
        //Add text to window
        coloredSection.add(logoLabel);

        //JLABEL ------------------------------------------------------------------
        
        //Create first explanation text
        MyLabel explanationText1 = new MyLabel(explanation1String);
        //Set Font 
        explanationText1.setFont(explanationTextFont);
        //Set size and position 
        explanationText1.setBounds(explanationText1Bounds);
        //Set text Color  
        explanationText1.setForeground(explanationTextColor);
        //Add text to window
        background.add(explanationText1);

        //Create second explanation text
        MyLabel explanationText2 = new MyLabel(explanation2String);        
        //Set Font 
        explanationText2.setFont(explanationTextFont);
        //Set size and position 
        explanationText2.setBounds(explanationTest2Bounds);
        //Set text Color
        explanationText2.setForeground(explanationTextColor);
        //Add text to window
        background.add(explanationText2);

        //Create Version text
        MyLabel versionText = new MyLabel(version);
        //Set Font 
        versionText.setFont(versionTextFont);
        //Set size and position 
        versionText.setBounds(versionTextBounds);
        //Set text Color
        versionText.setForeground(versionTextColor);
        //Add text to window
        coloredSection.add(versionText);

        //Create Author text
        MyLabel authorText = new MyLabel(author);
        //Set Font 
        authorText.setFont(authorTextFont);
        //Set size and position 
        authorText.setBounds(authorTextBounds);
        //Set text Color
        authorText.setForeground(authorTextColor);
        //Add text to window
        background.add(authorText);

        //Create Popup text
        MyLabel popupText = new MyLabel(popupInitialText);
        //Set alignment
        popupText.setHorizontalAlignment(SwingConstants.CENTER);
        popupText.setVerticalAlignment(SwingConstants.CENTER);
        //Set Font 
        popupText.setFont(popupTextFont);
        //Set size and position 
        popupText.setBounds(popupTextBounds);
        //Add text to window
        background.add(popupText);
        
        //JTextField --------------------------------------------------------------

        //Create input text field
        MyTextField inputTextField = new MyTextField();
        //Set size and position
        inputTextField.setBounds(inputTextFiledBounds);
        //Set Border
        inputTextField.setBorder(blueBorder);
        //Set Caret Color
        inputTextField.setCaretColor(input_outputTextCaretColor);
        //Set text Color
        inputTextField.setForeground(input_outputTextFieldForegroundColor);
        //Set text 
        inputTextField.setText(initialInputTextFieldText);
        //add input text field to window
        background.add(inputTextField);
        
        //Create output text field
        MyTextField outputTextField = new MyTextField();
        //Set size and position
        outputTextField.setBounds(outputTextFiledBounds);
        //Set Border
        outputTextField.setBorder(blueBorder);
        //Set Caret Color
        outputTextField.setCaretColor(input_outputTextCaretColor);
        //Set text Color
        outputTextField.setForeground(input_outputTextFieldForegroundColor);
        //Set text 
        outputTextField.setText(initialOutputTextFieldText);
        //add output text field to window
        background.add(outputTextField);
        
        //JBUTTON -----------------------------------------------------------------

        //Create template button
        MyButton templateButton = new MyButton(templateButtonText, templateButtonRadius);
        //Set size and position 
        templateButton.setBounds(templateButtonBounds);
        //Set background Color
        templateButton.setBackground(templateButtonBackgroundColor);
        //Set text Color
        templateButton.setForeground(templateButtonTextColor);
        //Set text Font
        templateButton.setFont(templateButtonFont);
        //add template button to window
        background.add(templateButton);
        
        //Create input button
        MyButton inputButton = new MyButton(input_outputButtonText, input_outputButtonRadius);
        //Set size and position 
        inputButton.setBounds(inputButtonBounds);
        //Set background Color
        inputButton.setBackground(input_outputButtonBackgroundColor);
        //Set text Color
        inputButton.setForeground(input_outputButtonTextColor);
        //Set text Font
        inputButton.setFont(input_outputButtonFont);
        //add template button to window
        background.add(inputButton);
        
        //Create output button
        MyButton outputButton = new MyButton(input_outputButtonText, input_outputButtonRadius);
        //Set size and position
        outputButton.setBounds(outputButtonBounds);
        //Set background Color
        outputButton.setBackground(input_outputButtonBackgroundColor);
        //Set text Color
        outputButton.setForeground(input_outputButtonTextColor);
        //Set text Font
        outputButton.setFont(input_outputButtonFont);
        //add output button to window
        background.add(outputButton);
        
        //Create download button
        MyButton downloadButton = new MyButton(downloadButtonText, downloadButtonRadius);
        //Set size and position 
        downloadButton.setBounds(downloadButtonBounds);
        //Set background Color
        downloadButton.setBackground(downloadButtonBackgroundColor);
        //Set text Color
        downloadButton.setForeground(downloadButtonTextColor);
        //Set text Font
        downloadButton.setFont(downloadButtonFont);
        //add template button to window
        background.add(downloadButton);

        //Create tutorial button
        MyButton2 tutorialButton = new MyButton2(tutorialButtonText, tutorialButtonRadius);
        //Set size and position 
        tutorialButton.setBounds(tutorialButtonBounds);
        //Set background Color
        tutorialButton.setBackground(tutorialButtonBackgroundColor);
        //Set text Color
        tutorialButton.setForeground(tutorialButtonTextColor);
        //Set text Font
        tutorialButton.setFont(tutorialButtonFont);
        //add tutorial button to window
        coloredSection.add(tutorialButton);
        
        //ACTIONLISTENER ---------------------------------------------------------
        
        //Add action listener to template Button 
        templateButton.addActionListener(new TemplateActionListener(popupText,templateButton,downloadButton,tutorialButton,outputTextField,textTimerStart,textTimerTick,timerButtonEnabled));
        //Add action listener to input Button 
        inputButton.addActionListener(new InputActionListener(inputTextField,this));
        //Add action listener to output Button 
        outputButton.addActionListener(new OutputActionListener(outputTextField,this));
        //Add action listener to download button 
        downloadButton.addActionListener(new DownloadActionListener(popupText,templateButton,downloadButton,tutorialButton,textTimerStart,textTimerTick,timerButtonEnabled,inputTextField,outputTextField));
        //Add action listener to download button
        tutorialButton.addActionListener(new TutorialActionListener(templateButton, downloadButton, tutorialButton, timerButtonEnabled)); 
        //Window ------------------------------------------------------------------
       
        //Add main Jpanel to window
        this.getContentPane().add(background);
        //Set size
        this.pack();
        this.setSize(windowWidth,windowHeight);
        //Set location on screen
        this.setLocationRelativeTo(null);
        //Set Default close operation 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Make it non-resizable
        this.setResizable(false);
        //Show on screen 
        this.setVisible(true);
    }
}