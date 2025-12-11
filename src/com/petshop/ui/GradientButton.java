package com.petshop.ui;

import javax.swing.*;
import java.awt.*;


public class GradientButton extends JButton {
    private Color top = new Color(255,107,107); 
    private Color bottom = new Color(198,40,40); 

    public GradientButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setForeground(Color.WHITE);
        setFont(getFont().deriveFont(Font.BOLD, 16f));
    }

    public void setColors(Color top, Color bottom){
        this.top = top; this.bottom = bottom;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        int w = getWidth(), h = getHeight();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gp = new GradientPaint(0,0, top, 0, h, bottom);
        g2.setPaint(gp);
        g2.fillRoundRect(0, 0, w, h, 18, 18);
        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    public void paintBorder(Graphics g) {
       
    }
}
