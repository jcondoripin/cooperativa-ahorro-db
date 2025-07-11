package com.unsa.cooperativa.ui;

import com.unsa.cooperativa.entity.Departamento;
import com.unsa.cooperativa.service.DepartamentoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;

public class DepartamentoPanel extends JPanel {
  private final DepartamentoService departamentoService;
  private final JTextField depCodField = new JTextField(5);
  private final JTextField depNomField = new JTextField(20);
  private final JComboBox<String> depActivoCombo = new JComboBox<>(new String[] { "1", "0" });
  private final JTable table;
  private final DefaultTableModel tableModel;

  public DepartamentoPanel(DepartamentoService departamentoService) {
    this.departamentoService = departamentoService;
    setLayout(new BorderLayout());

    // Form Panel
    JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));
    formPanel.add(new JLabel("Código:"));
    formPanel.add(depCodField);
    formPanel.add(new JLabel("Nombre:"));
    formPanel.add(depNomField);
    formPanel.add(new JLabel("Activo:"));
    formPanel.add(depActivoCombo);

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
    String[] columns = { "Código", "Nombre", "Activo" };
    tableModel = new DefaultTableModel(columns, 0);
    table = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(table);

    // Add components
    add(formPanel, BorderLayout.NORTH);
    add(scrollPane, BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);

    // Event Listeners
    createButton.addActionListener(e -> createDepartamento());
    updateButton.addActionListener(e -> updateDepartamento());
    deleteButton.addActionListener(e -> deleteDepartamento());
    clearButton.addActionListener(e -> clearForm());
    table.getSelectionModel().addListSelectionListener(e -> selectDepartamento());

    // Load initial data
    loadDepartamentos();
  }

  private void createDepartamento() {
    try {
      Departamento departamento = new Departamento(
          0, // Auto-incremented by DB
          depNomField.getText(),
          (String) depActivoCombo.getSelectedItem());
      departamentoService.createDepartamento(departamento);
      loadDepartamentos();
      clearForm();
      JOptionPane.showMessageDialog(this, "Departamento creado exitosamente");
    } catch (SQLException | IllegalArgumentException e) {
      JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
  }

  private void updateDepartamento() {
    try {
      Departamento departamento = new Departamento(
          Integer.parseInt(depCodField.getText()),
          depNomField.getText(),
          (String) depActivoCombo.getSelectedItem());
      departamentoService.updateDepartamento(departamento);
      loadDepartamentos();
      clearForm();
      JOptionPane.showMessageDialog(this, "Departamento actualizado exitosamente");
    } catch (SQLException | IllegalArgumentException e) {
      JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
  }

  private void deleteDepartamento() {
    try {
      int depCod = Integer.parseInt(depCodField.getText());
      departamentoService.deleteDepartamento(depCod);
      loadDepartamentos();
      clearForm();
      JOptionPane.showMessageDialog(this, "Departamento eliminado exitosamente");
    } catch (SQLException | NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
  }

  private void clearForm() {
    depCodField.setText("");
    depNomField.setText("");
    depActivoCombo.setSelectedIndex(0);
  }

  private void selectDepartamento() {
    int selectedRow = table.getSelectedRow();
    if (selectedRow >= 0) {
      depCodField.setText(tableModel.getValueAt(selectedRow, 0).toString());
      depNomField.setText(tableModel.getValueAt(selectedRow, 1).toString());
      depActivoCombo.setSelectedItem(tableModel.getValueAt(selectedRow, 2).toString());
    }
  }

  private void loadDepartamentos() {
    try {
      tableModel.setRowCount(0);
      for (Departamento departamento : departamentoService.getAllDepartamentos()) {
        tableModel.addRow(new Object[] {
            departamento.getDepCod(),
            departamento.getDepNom(),
            departamento.getDepActivo()
        });
      }
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(this, "Error al cargar departamentos: " + e.getMessage());
    }
  }
}