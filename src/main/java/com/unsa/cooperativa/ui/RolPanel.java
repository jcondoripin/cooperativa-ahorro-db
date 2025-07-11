package com.unsa.cooperativa.ui;

import com.unsa.cooperativa.entity.Rol;
import com.unsa.cooperativa.service.RolService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;

public class RolPanel extends JPanel {
  private final RolService rolService;
  private final JTextField rolCodField = new JTextField(5);
  private final JTextField rolRolField = new JTextField(20);
  private final JTextField rolNomField = new JTextField(20);
  private final JComboBox<String> rolActivoCombo = new JComboBox<>(new String[] { "1", "0" });
  private final JTable table;
  private final DefaultTableModel tableModel;

  public RolPanel(RolService rolService) {
    this.rolService = rolService;
    setLayout(new BorderLayout());

    // Form Panel
    JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));
    formPanel.add(new JLabel("Código:"));
    formPanel.add(rolCodField);
    formPanel.add(new JLabel("Rol:"));
    formPanel.add(rolRolField);
    formPanel.add(new JLabel("Nombre:"));
    formPanel.add(rolNomField);
    formPanel.add(new JLabel("Activo:"));
    formPanel.add(rolActivoCombo);

    // Button Panel
    JPanel buttonPanel = new JPanel();
    JButton createButton = new JButton("Crear");
    JButton updateButton = new JButton("Actualizar");
    JButton deleteButton = new JButton("Eliminar");
    JButton clearButton = new JButton("Limpiar");
    buttonPanel.add(createButton);
    buttonPanel.add(updateButton);
    buttonPanel.add(deleteButton);
    buttonPanel.add(clearButton);

    // Table
    String[] columns = { "Código", "Rol", "Nombre", "Activo" };
    tableModel = new DefaultTableModel(columns, 0);
    table = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(table);

    // Add components
    add(formPanel, BorderLayout.NORTH);
    add(scrollPane, BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);

    // Event Listeners
    createButton.addActionListener(e -> createRol());
    updateButton.addActionListener(e -> updateRol());
    deleteButton.addActionListener(e -> deleteRol());
    clearButton.addActionListener(e -> clearForm());
    table.getSelectionModel().addListSelectionListener(e -> selectRol());

    // Load initial data
    loadRoles();
  }

  private void createRol() {
    try {
      Rol rol = new Rol(
          0, // Auto-incremented by DB
          rolRolField.getText(),
          rolNomField.getText(),
          (String) rolActivoCombo.getSelectedItem());
      rolService.createRol(rol);
      loadRoles();
      clearForm();
      JOptionPane.showMessageDialog(this, "Rol creado exitosamente");
    } catch (SQLException | IllegalArgumentException e) {
      JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
  }

  private void updateRol() {
    try {
      Rol rol = new Rol(
          Integer.parseInt(rolCodField.getText()),
          rolRolField.getText(),
          rolNomField.getText(),
          (String) rolActivoCombo.getSelectedItem());
      rolService.updateRol(rol);
      loadRoles();
      clearForm();
      JOptionPane.showMessageDialog(this, "Rol actualizado exitosamente");
    } catch (SQLException | IllegalArgumentException e) {
      JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
  }

  private void deleteRol() {
    try {
      int rolCod = Integer.parseInt(rolCodField.getText());
      rolService.deleteRol(rolCod);
      loadRoles();
      clearForm();
      JOptionPane.showMessageDialog(this, "Rol eliminado exitosamente");
    } catch (SQLException | NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
  }

  private void clearForm() {
    rolCodField.setText("");
    rolRolField.setText("");
    rolNomField.setText("");
    rolActivoCombo.setSelectedIndex(0);
  }

  private void selectRol() {
    int selectedRow = table.getSelectedRow();
    if (selectedRow >= 0) {
      rolCodField.setText(tableModel.getValueAt(selectedRow, 0).toString());
      rolRolField.setText(tableModel.getValueAt(selectedRow, 1).toString());
      rolNomField.setText(tableModel.getValueAt(selectedRow, 2).toString());
      rolActivoCombo.setSelectedItem(tableModel.getValueAt(selectedRow, 3).toString());
    }
  }

  private void loadRoles() {
    try {
      tableModel.setRowCount(0);
      for (Rol rol : rolService.getAllRoles()) {
        tableModel.addRow(new Object[] {
            rol.getRolCod(),
            rol.getRolRol(),
            rol.getRolNom(),
            rol.getRolActivo()
        });
      }
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(this, "Error loading roles: " + e.getMessage());
    }
  }
}