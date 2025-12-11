package com.petshop.auth;

import javax.swing.*;
import java.awt.*;
import com.petshop.screens.MainMenuScreen;

public class LoginScreen {
    private JFrame frame;

    public void show(){
        frame = new JFrame("Pet Shop - Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,320);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        JPanel root = new JPanel(null);
        root.setBackground(Color.WHITE);
        frame.setContentPane(root);

        JLabel title = new JLabel("PET SHOP");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBounds(140,20,200,40);
        root.add(title);

        JLabel userL = new JLabel("Usuário:");
        userL.setBounds(60,90,60,25);
        root.add(userL);
        JTextField user = new JTextField("admin");
        user.setBounds(130,90,220,28);
        root.add(user);

        JLabel passL = new JLabel("Senha:");
        passL.setBounds(60,130,60,25);
        root.add(passL);
        JPasswordField pass = new JPasswordField("admin");
        pass.setBounds(130,130,220,28);
        root.add(pass);

        JButton btn = new JButton("Entrar");
        btn.setBounds(150,180,120,36);
        root.add(btn);

        btn.addActionListener(e -> {
            if("admin".equals(user.getText().trim()) && "admin".equals(new String(pass.getPassword()))){
                frame.dispose();
                new MainMenuScreen().show();
            } else {
                JOptionPane.showMessageDialog(frame, "Usuário ou senha incorretos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.setVisible(true);
    }
}
