package com.unsa.cooperativa;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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
				Properties props = new Properties();
				try (InputStream input = MainApp.class.getClassLoader().getResourceAsStream("db.properties")) {
					if (input == null) {
						throw new IOException("Unable to find db.properties");
					}
					props.load(input);
				}

				String url = props.getProperty("db.url");
				String user = props.getProperty("db.user");
				String password = props.getProperty("db.password");
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
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Configuration error: " + e.getMessage());
			}
		});
	}
}