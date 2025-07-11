package com.unsa.cooperativa.ui;

import java.awt.BorderLayout;
import java.sql.Connection;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.unsa.cooperativa.dao.UsuarioDAO;
import com.unsa.cooperativa.dao.UsuarioDAOImpl;
import com.unsa.cooperativa.service.UsuarioService;
import com.unsa.cooperativa.service.UsuarioServiceImpl;

public class MasterTablesPanel extends JPanel {
  public MasterTablesPanel(Connection connection) {
    setLayout(new BorderLayout());

    // Initialize DAO and Service for Usuario (and others similarly)
    UsuarioDAO usuarioDAO = new UsuarioDAOImpl(connection);
    UsuarioService usuarioService = new UsuarioServiceImpl(usuarioDAO);

    // Create tabbed pane for master tables
    JTabbedPane tabbedPane = new JTabbedPane();
    tabbedPane.addTab("Usuarios", new UsuarioPanel(usuarioService));
    // Add other master table panels here (Rol, Cooperativa, etc.)
    // Example:
    // tabbedPane.addTab("Roles", new RolPanel(rolService));
    // tabbedPane.addTab("Cooperativas", new CooperativaPanel(cooperativaService));
    // etc.

    add(tabbedPane, BorderLayout.CENTER);
  }
}