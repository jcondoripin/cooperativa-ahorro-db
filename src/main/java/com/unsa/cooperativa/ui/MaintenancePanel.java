package com.unsa.cooperativa.ui;

import java.awt.BorderLayout;
import java.sql.Connection;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MaintenancePanel extends JPanel {
  public MaintenancePanel(Connection connection) {
    setLayout(new BorderLayout());

    // Create tabbed pane for referential tables
    JTabbedPane tabbedPane = new JTabbedPane();
    // Add referential table panels here (UsuarioRol, Persona, Socio)
    // Example:
    // tabbedPane.addTab("Usuario-Rol", new UsuarioRolPanel(usuarioRolService));
    // tabbedPane.addTab("Personas", new PersonaPanel(personaService));
    // tabbedPane.addTab("Socios", new SocioPanel(socioService));

    add(tabbedPane, BorderLayout.CENTER);
  }
}