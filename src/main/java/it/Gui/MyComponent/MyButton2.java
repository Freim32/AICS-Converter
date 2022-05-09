package it.Gui.MyComponent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.metal.MetalButtonUI;

import it.Gui.Window;



public class MyButton2 extends JButton {   
    public MyButton2(String label, int radius) {
        super(label);
        
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setFocusable(false);
        this.setUI(new MyBasicButtonUI2(this,radius));
        this.setOpaque(false);
     
}

    
}

class MyBasicButtonUI2 extends MetalButtonUI{
    MyButton2 button;
    int radius;
    Graphics2D g2D;
    Color color = Window.BLUE;
    public MyBasicButtonUI2(MyButton2 button ,int radius){
        super();
        this.button=button;
        this.radius=radius;
    }

    @Override
    protected Color getDisabledTextColor() {
        return button.getForeground();
    }



    
    @Override
    public void update(Graphics g, JComponent b) {
        g2D = (Graphics2D) g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color fillColor = b.getBackground();
        ButtonModel model= ((AbstractButton) b).getModel();
        boolean check=true;

        if (model.isPressed()) {
            model.setPressed(false);
            b.getParent().repaint();
        }else if(model.isRollover()&&check){
            b.setForeground(Window.DARK_BLUE);
            check=false;
        }else {
            b.setForeground(color);
            check=true;
        }
        

        g2D.setColor(fillColor);
        g2D.fillRoundRect(0, 0, b.getWidth(),b.getHeight(), radius, radius);
        paint(g,b);
    }
}
