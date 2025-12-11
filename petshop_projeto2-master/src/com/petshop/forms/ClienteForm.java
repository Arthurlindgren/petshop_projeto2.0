package com.petshop.forms;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.*;
import java.util.UUID;

public class ClienteForm {
    private JFrame parent;
    public ClienteForm(JFrame parent){ this.parent = parent; }

    public void show(){
        JDialog dlg = new JDialog(parent, "Cadastrar Cliente", true);
        dlg.setSize(460,360);
        dlg.setLayout(null);
        dlg.setLocationRelativeTo(parent);

        JLabel title = new JLabel("Cadastrar Cliente");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setBounds(20,10,300,30);
        dlg.add(title);

        dlg.add(new JLabel("Nome:")).setBounds(20,60,80,25);
        JTextField nome = new JTextField(); nome.setBounds(110,60,320,25); dlg.add(nome);

        dlg.add(new JLabel("Telefone:")).setBounds(20,100,80,25);
        JTextField tel = new JTextField(); tel.setBounds(110,100,320,25); dlg.add(tel);

        dlg.add(new JLabel("E-mail:")).setBounds(20,140,80,25);
        JTextField email = new JTextField(); email.setBounds(110,140,320,25); dlg.add(email);

        dlg.add(new JLabel("CPF:")).setBounds(20,180,80,25);
        JTextField cpf = new JTextField(); cpf.setBounds(110,180,320,25); dlg.add(cpf);

        JButton save = new JButton("Salvar");
        save.setBounds(110,230,120,36);
        save.setBackground(new java.awt.Color(76,175,80));
        save.setForeground(Color.WHITE);
        save.setFocusPainted(false);
        dlg.add(save);

        JButton cancel = new JButton("Cancelar");
        cancel.setBounds(260,230,120,36);
        dlg.add(cancel);

        save.addActionListener(e->{
            String id = UUID.randomUUID().toString();
            String n = nome.getText().trim();
            String t = tel.getText().trim();
            String em = email.getText().trim();
            String c = cpf.getText().trim();
            if(n.isEmpty() || t.isEmpty()){
                JOptionPane.showMessageDialog(dlg, "Nome e telefone são obrigatórios.");
                return;
            }
            try{
                Path pth = Paths.get("data","clients.csv");
                Files.createDirectories(pth.getParent());
                String line = String.join(";", id, n.replaceAll(";", " "), t.replaceAll(";", " "), em.replaceAll(";", " "), c.replaceAll(";", " "));
                Files.write(pth, (line + System.lineSeparator()).getBytes("UTF-8"), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                JOptionPane.showMessageDialog(dlg,"Cliente salvo com sucesso.");
                dlg.dispose();
            } catch(Exception ex){ ex.printStackTrace(); JOptionPane.showMessageDialog(dlg, "Erro ao salvar: "+ex.getMessage()); }
        });
        cancel.addActionListener(e-> dlg.dispose());

        dlg.setVisible(true);
    }
}
