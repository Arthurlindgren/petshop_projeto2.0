package com.petshop.forms;

import javax.swing.*;
import java.awt.*;
import java.nio.file.*;
import java.util.UUID;
import java.util.List;

public class PetForm {
    private JFrame parent;
    public PetForm(JFrame parent){ this.parent = parent; }

    public void show(){
        JDialog dlg = new JDialog(parent, "Cadastrar Pet", true);
        dlg.setSize(520,420);
        dlg.setLayout(null);
        dlg.setLocationRelativeTo(parent);

        JLabel title = new JLabel("Cadastrar Pet");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setBounds(20,10,300,30);
        dlg.add(title);

        
        JLabel clientL = new JLabel("Cliente (ID - Nome):"); clientL.setBounds(20,50,140,25); dlg.add(clientL);
        JComboBox<String> clientsCb = new JComboBox<>();
        clientsCb.setBounds(170,50,320,25); dlg.add(clientsCb);
        try{
            Path cp = Paths.get("data","clients.csv");
            if(Files.exists(cp)){
                List<String> lines = Files.readAllLines(cp);
                for(String l: lines){
                    if(l.trim().isEmpty()) continue;
                    String[] parts = l.split(";", -1);
                    String id = parts.length>0?parts[0]:"";
                    String name = parts.length>1?parts[1]:"";
                    clientsCb.addItem(id + " - " + name);
                }
            }
        }catch(Exception ex){ ex.printStackTrace(); }

        dlg.add(new JLabel("Nome do Pet:")).setBounds(20,90,120,25);
        JTextField nome = new JTextField(); nome.setBounds(150,90,340,25); dlg.add(nome);

        dlg.add(new JLabel("Espécie:")).setBounds(20,130,120,25);
        JTextField especie = new JTextField(); especie.setBounds(150,130,340,25); dlg.add(especie);

        dlg.add(new JLabel("Raça:")).setBounds(20,170,120,25);
        JTextField raca = new JTextField(); raca.setBounds(150,170,340,25); dlg.add(raca);

        dlg.add(new JLabel("Idade:")).setBounds(20,210,120,25);
        JTextField idade = new JTextField(); idade.setBounds(150,210,340,25); dlg.add(idade);

        dlg.add(new JLabel("Peso (kg):")).setBounds(20,250,120,25);
        JTextField peso = new JTextField(); peso.setBounds(150,250,340,25); dlg.add(peso);

        JButton save = new JButton("Salvar"); save.setBounds(150,300,120,36); save.setBackground(new Color(76,175,80)); save.setForeground(Color.WHITE); save.setFocusPainted(false);
        JButton cancel = new JButton("Cancelar"); cancel.setBounds(300,300,120,36); dlg.add(save); dlg.add(cancel);

        save.addActionListener(e->{
            String clientSel = (String) clientsCb.getSelectedItem();
            if(clientSel==null || clientSel.trim().isEmpty()){ JOptionPane.showMessageDialog(dlg,"Selecione o cliente (ID)."); return; }
            String clientId = clientSel.split(" - ")[0];
            String idpet = UUID.randomUUID().toString();
            String n = nome.getText().trim();
            String esp = especie.getText().trim();
            String rc = raca.getText().trim();
            String idd = idade.getText().trim();
            String pg = peso.getText().trim();
            if(n.isEmpty()){ JOptionPane.showMessageDialog(dlg,"Nome do pet é obrigatório."); return; }
            try{
                Path pth = Paths.get("data","pets.csv");
                Files.createDirectories(pth.getParent());
                String line = String.join(";", idpet, clientId, n.replaceAll(";", " "), esp.replaceAll(";", " "), rc.replaceAll(";", " "), idd.replaceAll(";", " "), pg.replaceAll(";", " "));
                Files.write(pth, (line + System.lineSeparator()).getBytes("UTF-8"), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                JOptionPane.showMessageDialog(dlg, "Pet salvo com sucesso.");
                dlg.dispose();
            }catch(Exception ex){ ex.printStackTrace(); JOptionPane.showMessageDialog(dlg, "Erro ao salvar: "+ex.getMessage()); }
        });
        cancel.addActionListener(e-> dlg.dispose());

        dlg.setVisible(true);
    }
}
