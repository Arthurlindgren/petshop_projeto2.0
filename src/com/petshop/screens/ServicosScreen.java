package com.petshop.screens;

import javax.swing.*;
import java.awt.*;
import java.nio.file.*;
import java.util.*;
import com.petshop.ui.GradientButton;
import com.petshop.forms.ServicoForm;

public class ServicosScreen {
    private JFrame frame;
    private JComboBox<String> categoryCb;
    private JComboBox<String> serviceCb;

    private static final Map<String, String[]> OPTIONS = new LinkedHashMap<>();
    static{
        OPTIONS.put("Higiene e Beleza", new String[]{"Banho","Tosa","Spa"});
        OPTIONS.put("Saúde e Bem-estar", new String[]{"Clínica Veterinária","Vacinação","Medicamentos","Adestramento","Acupuntura"});
    }

    public ServicosScreen(JFrame frame){ this.frame = frame; }

    public void show(){
        JPanel root = new JPanel(null);
        root.setBackground(new Color(250,250,250));
        frame.setContentPane(root);

        JLabel title = new JLabel("Gerenciar Serviços");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setBounds(20,20,300,30);
        root.add(title);

        
        GradientButton back = new GradientButton("⬅ Voltar");
        back.setBounds(20,60,120,40);
        back.setColors(new Color(200,30,30), new Color(150,10,10));
        root.add(back);
        back.addActionListener(e -> { frame.getContentPane().removeAll(); frame.dispose(); new com.petshop.screens.MainMenuScreen().show(); });

        
        JLabel catL = new JLabel("Categoria:");
        catL.setBounds(20,120,120,25);
        root.add(catL);
        categoryCb = new JComboBox<>(OPTIONS.keySet().toArray(new String[0]));
        categoryCb.setBounds(110,120,220,28);
        root.add(categoryCb);

        JLabel servL = new JLabel("Serviço:");
        servL.setBounds(350,120,80,25);
        root.add(servL);
        serviceCb = new JComboBox<>();
        serviceCb.setBounds(420,120,240,28);
        root.add(serviceCb);

        categoryCb.addActionListener(e -> updateServices());

        
        updateServices();

        GradientButton add = new GradientButton("📅 Agendar Serviço");
        add.setBounds(20,170,260,56);
        add.setColors(new Color(76,175,80), new Color(27,94,32));
        root.add(add);

        GradientButton list = new GradientButton("🔍 Listar Serviços");
        list.setBounds(300,170,260,56);
        list.setColors(new Color(76,175,80), new Color(27,94,32));
        root.add(list);

        JPanel listArea = new JPanel(null);
        listArea.setBounds(20,240,640,210);
        listArea.setBackground(Color.WHITE);
        listArea.setBorder(BorderFactory.createLineBorder(new Color(230,230,230)));
        root.add(listArea);

        add.addActionListener(e-> {
            
            String cat = (String)categoryCb.getSelectedItem();
            String serv = (String)serviceCb.getSelectedItem();
            new ServicoForm(frame, cat, serv).show();
        });
        list.addActionListener(e-> JOptionPane.showMessageDialog(frame,"Lista de serviços (placeholder)"));

        frame.revalidate();
        frame.repaint();
    }

    private void updateServices(){
        serviceCb.removeAllItems();
        String sel = (String)categoryCb.getSelectedItem();
        if(sel==null) return;
        String[] opts = OPTIONS.get(sel);
        if(opts!=null){
            for(String s: opts) serviceCb.addItem(s);
        }
    }
}
