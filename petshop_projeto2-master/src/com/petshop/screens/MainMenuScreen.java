package com.petshop.screens;

import javax.swing.*;
import java.awt.*;
import com.petshop.ui.GradientButton;
import com.petshop.forms.ClienteForm;
import com.petshop.forms.PetForm;
import com.petshop.forms.ServicoForm;

public class MainMenuScreen {
    private JFrame frame;

    public void show(){
        frame = new JFrame("Pet Shop - Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700,520);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);

        JPanel root = new JPanel(null);
        root.setBackground(new Color(245,245,245));
        frame.setContentPane(root);

        JLabel title = new JLabel("PET SHOP");
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setBounds(260,20,300,40);
        root.add(title);

        
        int bw = 460, bh = 68;
        int bx = 120, by = 100, gap = 18;

        GradientButton b1 = new GradientButton("Gerenciar Clientes");
        b1.setBounds(bx,by,bw,bh);
        b1.setColors(new Color(255,107,107), new Color(198,40,40)); 
        root.add(b1);

        GradientButton b2 = new GradientButton("Gerenciar Pets");
        b2.setBounds(bx,by+ (bh+gap)*1,bw,bh);
        b2.setColors(new Color(255,107,107), new Color(198,40,40));
        root.add(b2);

        GradientButton b3 = new GradientButton("Gerenciar Serviços");
        b3.setBounds(bx,by+ (bh+gap)*2,bw,bh);
        b3.setColors(new Color(255,107,107), new Color(198,40,40));
        root.add(b3);

        GradientButton b4 = new GradientButton("Sair");
        b4.setBounds(bx,by+ (bh+gap)*3,bw,bh);
        b4.setColors(new Color(200,30,30), new Color(150,10,10)); 
        root.add(b4);

        b1.addActionListener(e -> {
            frame.getContentPane().removeAll();
            new ClientesScreen(frame).show();
            frame.revalidate();
            frame.repaint();
        });
        b2.addActionListener(e -> {
            frame.getContentPane().removeAll();
            new PetsScreen(frame).show();
            frame.revalidate();
            frame.repaint();
        });
        b3.addActionListener(e -> {
            frame.getContentPane().removeAll();
            new ServicosScreen(frame).show();
            frame.revalidate();
            frame.repaint();
        });
        b4.addActionListener(e -> frame.dispose());

        frame.setVisible(true);
    }
}
