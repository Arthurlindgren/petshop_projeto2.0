package com.petshop.forms;

import javax.swing.*;
import java.awt.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.UUID;

public class ServicoForm {
    private JFrame parent;
    private String category;
    private String service;

    public ServicoForm(JFrame parent){ this.parent = parent; }
    public ServicoForm(JFrame parent, String category, String service){ this.parent = parent; this.category = category; this.service = service; }

    public void show(){
        JDialog dlg = new JDialog(parent, "Agendar Serviço", true);
        dlg.setSize(520,380);
        dlg.setLayout(null);
        dlg.setLocationRelativeTo(parent);

        JLabel title = new JLabel("Agendar Serviço");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setBounds(20,10,300,30);
        dlg.add(title);

        dlg.add(new JLabel("Categoria:")).setBounds(20,60,100,25);
        JTextField cat = new JTextField(category!=null?category:""); cat.setBounds(130,60,320,25); cat.setEditable(false); dlg.add(cat);

        dlg.add(new JLabel("Serviço:")).setBounds(20,100,100,25);
        JTextField serv = new JTextField(service!=null?service:""); serv.setBounds(130,100,320,25); serv.setEditable(false); dlg.add(serv);

        dlg.add(new JLabel("Cliente (ID):")).setBounds(20,140,100,25);
        JTextField cliente = new JTextField(); cliente.setBounds(130,140,320,25); dlg.add(cliente);

        dlg.add(new JLabel("Pet (ID):")).setBounds(20,180,100,25);
        JTextField pet = new JTextField(); pet.setBounds(130,180,320,25); dlg.add(pet);

        dlg.add(new JLabel("Data (YYYY-MM-DD):")).setBounds(20,220,140,25);
        JTextField data = new JTextField(LocalDate.now().toString()); data.setBounds(170,220,280,25); dlg.add(data);

        JButton save = new JButton("Salvar"); save.setBounds(140,270,120,36); save.setBackground(new Color(76,175,80)); save.setForeground(Color.WHITE); save.setFocusPainted(false);
        JButton cancel = new JButton("Cancelar"); cancel.setBounds(280,270,120,36);
        dlg.add(save); dlg.add(cancel);

        save.addActionListener(e->{
            // append to data/services.csv -> id;category;service;clientId;petId;date
            try{
                Path pth = Paths.get("data","services.csv");
                Files.createDirectories(pth.getParent());
                String id = UUID.randomUUID().toString();
                String line = String.join(";", id, category!=null?category:"", service!=null?service:"", cliente.getText().trim(), pet.getText().trim(), data.getText().trim());
                Files.write(pth, (line+System.lineSeparator()).getBytes("UTF-8"), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                JOptionPane.showMessageDialog(dlg,"Serviço agendado com sucesso.");
                dlg.dispose();
            }catch(Exception ex){ ex.printStackTrace(); JOptionPane.showMessageDialog(dlg,"Erro: "+ex.getMessage()); }
        });
        cancel.addActionListener(e-> dlg.dispose());

        dlg.setVisible(true);
    }
}
