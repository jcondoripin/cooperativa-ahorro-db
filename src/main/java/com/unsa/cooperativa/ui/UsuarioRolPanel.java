package com.unsa.cooperativa.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.unsa.cooperativa.entity.Rol;
import com.unsa.cooperativa.entity.Usuario;
import com.unsa.cooperativa.entity.UsuarioRol;
import com.unsa.cooperativa.service.RolService;
import com.unsa.cooperativa.service.UsuarioRolService;
import com.unsa.cooperativa.service.UsuarioService;

public class UsuarioRolPanel extends JPanel {
  private final UsuarioRolService usuarioRolService;
  private final UsuarioService usuarioService;
  private final RolService rolService;
  private final JComboBox<Integer> usuCodCombo = new JComboBox<>();
  private final JComboBox<Integer> rolCodCombo = new JComboBox<>();
  private final JTable table;
  private final DefaultTableModel tableModel;

  public UsuarioRolPanel(UsuarioRolService usuarioRolService, UsuarioService usuarioService, RolService rolService) {
    this.usuarioRolService = usuarioRolService;
    this.usuarioService = usuarioService;
    this.rolService = rolService;
    setLayout(new BorderLayout());

    // Form Panel
    JPanel formPanel = new JPanel(new GridLayout(2, 2, 5, 5));
    formPanel.add(new JLabel("Usuario:"));
    formPanel.add(usuCodCombo);
    formPanel.add(new JLabel("Rol:"));
    formPanel.add(rolCodCombo);

    // Button Panel
    JPanel buttonPanel = new JPanel();
    JButton createButton = new JButton("Crear");
    JButton deleteButton = new JButton("Eliminar");
    JButton clearButton = new JButton("Limpiar");
    buttonPanel.add(createButton);
    buttonPanel.add(deleteButton);
    buttonPanel.add(clearButton);

    // Table
    String[] columns = { "Usuario", "Rol" };
    tableModel = new DefaultTableModel(columns, 0);
    table = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(table);

    // Add components
    add(formPanel, BorderLayout.NORTH);
    add(scrollPane, BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);

    // Event Listeners
    createButton.addActionListener(e -> createUsuarioRol());
    deleteButton.addActionListener(e -> deleteUsuarioRol());
    clearButton.addActionListener(e -> clearForm());
    table.getSelectionModel().addListSelectionListener(e -> selectUsuarioRol());

    // Load initial data
    loadUsuarioRoles();
    loadCombos();
  }

  private void loadCombos() {
    try {
      usuCodCombo.removeAllItems();
      for (Usuario usuario : usuarioService.getAllUsuarios()) {
        usuCodCombo.addItem(usuario.getUsuCod());
      }
      rolCodCombo.removeAllItems();
      for (Rol rol : rolService.getAllRoles()) {
        rolCodCombo.addItem(rol.getRolCod());
      }
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(this, "Error al cargar combos: " + e.getMessage());
    }
  }

  private void createUsuarioRol() {
    try {
      UsuarioRol usuarioRol = new UsuarioRol(
          (Integer) usuCodCombo.getSelectedItem(),
          (Integer) rolCodCombo.getSelectedItem());
      usuarioRolService.createUsuarioRol(usuarioRol);
      loadUsuarioRoles();
      clearForm();
      JOptionPane.showMessageDialog(this, "Usuario-Rol creado exitosamente");
    } catch (SQLException | IllegalArgumentException e) {
      JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
  }

  private void deleteUsuarioRol() {
    try {
      int usuCod = (Integer) usuCodCombo.getSelectedItem();
      int rolCod = (Integer) rolCodCombo.getSelectedItem();
      usuarioRolService.deleteUsuarioRol(usuCod, rolCod);
      loadUsuarioRoles();
      clearForm();
      JOptionPane.showMessageDialog(this, "Usuario-Rol eliminado exitosamente");
    } catch (SQLException | NullPointerException e) {
      JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
  }

  private void clearForm() {
    usuCodCombo.setSelectedIndex(0);
    rolCodCombo.setSelectedIndex(0);
  }

  private void selectUsuarioRol() {
    int selectedRow = table.getSelectedRow();
    if (selectedRow >= 0) {
      usuCodCombo.setSelectedItem(Integer.valueOf(tableModel.getValueAt(selectedRow, 0).toString()));
      rolCodCombo.setSelectedItem(Integer.valueOf(tableModel.getValueAt(selectedRow, 1).toString()));
    }
  }

  private void loadUsuarioRoles() {
    try {
      tableModel.setRowCount(0);
      for (UsuarioRol usuarioRol : usuarioRolService.getAllUsuarioRoles()) {
        tableModel.addRow(new Object[] {
            usuarioRol.getUsuCod(),
            usuarioRol.getRolCod()
        });
      }
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(this, "Error al cargar usuario-roles: " + e.getMessage());
    }
  }
}