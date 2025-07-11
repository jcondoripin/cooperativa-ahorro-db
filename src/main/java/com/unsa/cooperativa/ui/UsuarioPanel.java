package com.unsa.cooperativa.ui;

import com.unsa.cooperativa.entity.Usuario;
import com.unsa.cooperativa.service.UsuarioService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;

public class UsuarioPanel extends JPanel {
  private final UsuarioService usuarioService;
  private final JTextField usuCodField = new JTextField(5);
  private final JTextField usuIdeField = new JTextField(20);
  private final JTextField usuUsuField = new JTextField(20);
  private final JTextField usuPasField = new JTextField(20);
  private final JTextField usuEmpField = new JTextField(20);
  private final JComboBox<String> usuActivoCombo = new JComboBox<>(new String[] { "1", "0" });
  private final JTable table;
  private final DefaultTableModel tableModel;

  public UsuarioPanel(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
    setLayout(new BorderLayout());

    // Form Panel
    JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));
    formPanel.add(new JLabel("Código:"));
    formPanel.add(usuCodField);
    formPanel.add(new JLabel("DNI:"));
    formPanel.add(usuIdeField);
    formPanel.add(new JLabel("Usuario:"));
    formPanel.add(usuUsuField);
    formPanel.add(new JLabel("Contraseña:"));
    formPanel.add(usuPasField);
    formPanel.add(new JLabel("Empresa:"));
    formPanel.add(usuEmpField);
    formPanel.add(new JLabel("Activo:"));
    formPanel.add(usuActivoCombo);

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
    String[] columns = { "Código", "DNI", "Usuario", "Contraseña", "Empresa", "Activo" };
    tableModel = new DefaultTableModel(columns, 0);
    table = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(table);

    // Add components
    add(formPanel, BorderLayout.NORTH);
    add(scrollPane, BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);

    // Event Listeners
    createButton.addActionListener(e -> createUsuario());
    updateButton.addActionListener(e -> updateUsuario());
    deleteButton.addActionListener(e -> deleteUsuario());
    clearButton.addActionListener(e -> clearForm());
    table.getSelectionModel().addListSelectionListener(e -> selectUsuario());

    // Load initial data
    loadUsuarios();
  }

  private void createUsuario() {
    try {
      Usuario usuario = new Usuario(
          0, // Auto-incremented by DB
          usuIdeField.getText(),
          usuUsuField.getText(),
          usuPasField.getText(),
          usuEmpField.getText(),
          (String) usuActivoCombo.getSelectedItem());
      usuarioService.createUsuario(usuario);
      loadUsuarios();
      clearForm();
      JOptionPane.showMessageDialog(this, "Usuario creado exitosamente");
    } catch (SQLException | IllegalArgumentException e) {
      JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
  }

  private void updateUsuario() {
    try {
      Usuario usuario = new Usuario(
          Integer.parseInt(usuCodField.getText()),
          usuIdeField.getText(),
          usuUsuField.getText(),
          usuPasField.getText(),
          usuEmpField.getText(),
          (String) usuActivoCombo.getSelectedItem());
      usuarioService.updateUsuario(usuario);
      loadUsuarios();
      clearForm();
      JOptionPane.showMessageDialog(this, "Usuario actualizado exitosamente");
    } catch (SQLException | IllegalArgumentException e) {
      JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
  }

  private void deleteUsuario() {
    try {
      int usuCod = Integer.parseInt(usuCodField.getText());
      usuarioService.deleteUsuario(usuCod);
      loadUsuarios();
      clearForm();
      JOptionPane.showMessageDialog(this, "Usuario eliminado exitosamente");
    } catch (SQLException | NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
  }

  private void clearForm() {
    usuCodField.setText("");
    usuIdeField.setText("");
    usuUsuField.setText("");
    usuPasField.setText("");
    usuEmpField.setText("");
    usuActivoCombo.setSelectedIndex(0);
  }

  private void selectUsuario() {
    int selectedRow = table.getSelectedRow();
    if (selectedRow >= 0) {
      usuCodField.setText(tableModel.getValueAt(selectedRow, 0).toString());
      usuIdeField.setText(tableModel.getValueAt(selectedRow, 1).toString());
      usuUsuField.setText(tableModel.getValueAt(selectedRow, 2).toString());
      usuPasField.setText(tableModel.getValueAt(selectedRow, 3).toString());
      usuEmpField.setText(tableModel.getValueAt(selectedRow, 4).toString());
      usuActivoCombo.setSelectedItem(tableModel.getValueAt(selectedRow, 5).toString());
    }
  }

  private void loadUsuarios() {
    try {
      tableModel.setRowCount(0);
      for (Usuario usuario : usuarioService.getAllUsuarios()) {
        tableModel.addRow(new Object[] {
            usuario.getUsuCod(),
            usuario.getUsuIde(),
            usuario.getUsuUsu(),
            usuario.getUsuPas(),
            usuario.getUsuEmp(),
            usuario.getUsuActivo()
        });
      }
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(this, "Error loading usuarios: " + e.getMessage());
    }
  }
}