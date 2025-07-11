package com.unsa.cooperativa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import com.unsa.cooperativa.ui.MaintenancePanel;
import com.unsa.cooperativa.ui.MasterTablesPanel;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Database connection
                String url = "jdbc:mysql://localhost:3306/cooperativa_ahorro_4?useSSL=false";
                String user = "root"; // Update with your DB credentials
                String password = "12345678";
                Connection connection = DriverManager.getConnection(url, user, password);

                // Create main frame
                JFrame frame = new JFrame("Cooperativa Management System");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(1000, 700);

                // Create tabbed pane
                JTabbedPane tabbedPane = new JTabbedPane();
                tabbedPane.addTab("Tablas Maestras", new MasterTablesPanel(connection));
                tabbedPane.addTab("Mantenimiento", new MaintenancePanel(connection));

                frame.add(tabbedPane);
                frame.setVisible(true);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Database connection error: " + e.getMessage());
            }
        });
    }
}