package com.petshop.screens;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import com.petshop.ui.GradientButton;
import com.petshop.forms.PetForm;

public class PetsScreen {
    private JFrame frame;
    private DefaultTableModel model;
    private JTable table;

    public PetsScreen(JFrame frame){ this.frame = frame; }

    public void show(){
        JPanel root = new JPanel(null);
        root.setBackground(new Color(250,250,250));
        frame.setContentPane(root);

        JLabel title = new JLabel("Gerenciar Pets");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setBounds(20,20,300,30);
        root.add(title);

       
        GradientButton back = new GradientButton("⬅ Voltar");
        back.setBounds(20,60,120,40);
        back.setColors(new Color(200,30,30), new Color(150,10,10));
        root.add(back);
        back.addActionListener(e -> { frame.dispose(); new com.petshop.screens.MainMenuScreen().show(); });

        
        int bx = 20, by = 120;
        GradientButton add = new GradientButton("➕ Cadastrar Pet");
        add.setBounds(bx,by,220,50);
        add.setColors(new Color(76,175,80), new Color(27,94,32));
        root.add(add);

        GradientButton list = new GradientButton("🔄 Recarregar Lista");
        list.setBounds(bx+240,by,220,50);
        list.setColors(new Color(76,175,80), new Color(27,94,32));
        root.add(list);

        
        JTextField searchField = new JTextField();
        searchField.setBounds(20,190,300,28);
        root.add(searchField);
        JButton searchBtn = new JButton("Buscar (nome/cliente)");
        searchBtn.setBounds(340,190,160,28);
        root.add(searchBtn);

        JButton deleteBtn = new JButton("Excluir Selecionado");
        deleteBtn.setBounds(520,190,160,28);
        root.add(deleteBtn);

        
        String[] cols = new String[]{"IDPet","IDCliente","Nome","Espécie","Raça","Idade","Peso"};
        model = new DefaultTableModel(cols,0);
        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20,230,760,300);
        sp.setBackground(Color.WHITE);
        sp.setBorder(BorderFactory.createLineBorder(new Color(230,230,230)));
        root.add(sp);

        add.addActionListener(e -> new PetForm(frame).show());
        list.addActionListener(e -> loadPets());

        searchBtn.addActionListener(e -> {
            String q = searchField.getText().trim().toLowerCase();
            if(q.isEmpty()){ loadPets(); return; }
            filterPets(q);
        });

        deleteBtn.addActionListener(e -> {
            int sel = table.getSelectedRow();
            if(sel==-1){ JOptionPane.showMessageDialog(frame, "Selecione um pet na tabela para excluir."); return; }
            String id = (String) model.getValueAt(sel, 0);
            int confirm = JOptionPane.showConfirmDialog(frame, "Confirma excluir o pet selecionado?","Excluir", JOptionPane.YES_NO_OPTION);
            if(confirm!=JOptionPane.YES_OPTION) return;
            try{
                Path p = Paths.get("data","pets.csv");
                if(!Files.exists(p)){ JOptionPane.showMessageDialog(frame, "Arquivo pets.csv não encontrado."); return; }
                List<String> lines = Files.readAllLines(p);
                List<String> kept = lines.stream().filter(l -> !l.startsWith(id + ";")).collect(Collectors.toList());
                Files.write(p, kept, java.nio.charset.StandardCharsets.UTF_8);
                JOptionPane.showMessageDialog(frame, "Pet excluído.");
                loadPets();
            } catch(Exception ex){ ex.printStackTrace(); JOptionPane.showMessageDialog(frame, "Erro ao excluir: "+ex.getMessage()); }
        });

        loadPets();

        frame.revalidate();
        frame.repaint();
    }

    private void loadPets(){
        model.setRowCount(0);
        try{
            Path p = Paths.get("data","pets.csv");
            if(!Files.exists(p)) return;
            List<String> lines = Files.readAllLines(p);
            for(String l: lines){
                if(l.trim().isEmpty()) continue;
                String[] parts = l.split(";", -1);
                String idpet = parts.length>0?parts[0]:"";
                String idcli = parts.length>1?parts[1]:"";
                String nome = parts.length>2?parts[2]:"";
                String especie = parts.length>3?parts[3]:"";
                String raca = parts.length>4?parts[4]:"";
                String idade = parts.length>5?parts[5]:"";
                String peso = parts.length>6?parts[6]:"";
                model.addRow(new Object[]{idpet,idcli,nome,especie,raca,idade,peso});
            }
        }catch(Exception ex){ ex.printStackTrace(); }
    }

    private void filterPets(String q){
        model.setRowCount(0);
        try{
            Path p = Paths.get("data","pets.csv");
            if(!Files.exists(p)) return;
            List<String> lines = Files.readAllLines(p);
            for(String l: lines){
                if(l.trim().isEmpty()) continue;
                String[] parts = l.split(";", -1);
                String idpet = parts.length>0?parts[0]:"";
                String idcli = parts.length>1?parts[1]:"";
                String nome = parts.length>2?parts[2]:"";
                if(nome.toLowerCase().contains(q) || idcli.toLowerCase().contains(q)){
                    String especie = parts.length>3?parts[3]:"";
                    String raca = parts.length>4?parts[4]:"";
                    String idade = parts.length>5?parts[5]:"";
                    String peso = parts.length>6?parts[6]:"";
                    model.addRow(new Object[]{idpet,idcli,nome,especie,raca,idade,peso});
                }
            }
        }catch(Exception ex){ ex.printStackTrace(); }
    }
}
