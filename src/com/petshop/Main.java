package com.petshop;
import javax.swing.SwingUtilities;
import com.petshop.auth.LoginScreen;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginScreen().show());
    }
}
