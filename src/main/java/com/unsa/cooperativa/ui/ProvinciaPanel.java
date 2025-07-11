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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.unsa.cooperativa.entity.Departamento;
import com.unsa.cooperativa.entity.Provincia;
import com.unsa.cooperativa.service.DepartamentoService;
import com.unsa.cooperativa.service.ProvinciaService;

public class ProvinciaPanel extends JPanel {
  private final ProvinciaService provinciaService;
  private final DepartamentoService departamentoService;
  private final JTextField proCodField = new JTextField(5);
  private final JTextField proNomField = new JTextField(20);
  private final JComboBox<Integer> depCodCombo = new JComboBox<>();
  private final JComboBox<String> proActivoCombo = new JComboBox<>(new String[] { "1", "0" });
  private final JTable table;
  private final DefaultTableModel tableModel;

  public ProvinciaPanel(ProvinciaService provinciaService, DepartamentoService departamentoService) {
    this.provinciaService = provinciaService;
    this.departamentoService = departamentoService;
    setLayout(new BorderLayout());

    // Form Panel
    JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));
    formPanel.add(new JLabel("Código:"));
    formPanel.add(proCodField);
    formPanel.add(new JLabel("Nombre:"));
    formPanel.add(proNomField);
    formPanel.add(new JLabel("Departamento:"));
    formPanel.add(depCodCombo);
    formPanel.add(new JLabel("Activo:"));
    formPanel.add(proActivoCombo);

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
    String[] columns = { "Código", "Nombre", "Departamento", "Activo" };
    tableModel = new DefaultTableModel(columns, 0);
    table = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(table);

    // Add components
    add(formPanel, BorderLayout.NORTH);
    add(scrollPane, BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);

    // Event Listeners
    createButton.addActionListener(e -> createProvincia());
    updateButton.addActionListener(e -> updateProvincia());
    deleteButton.addActionListener(e -> deleteProvincia());
    clearButton.addActionListener(e -> clearForm());
    table.getSelectionModel().addListSelectionListener(e -> selectProvincia());

    // Load initial data
    loadProvincias();
    loadDepartamentos();
  }

  private void loadDepartamentos() {
    try {
      depCodCombo.removeAllItems();
      for (Departamento departamento : departamentoService.getAllDepartamentos()) {
        depCodCombo.addItem(departamento.getDepCod());
      }
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(this, "Error al cargar departamentos: " + e.getMessage());
    }
  }

  private void createProvincia() {
    try {
      Provincia provincia = new Provincia(
          0, // Auto-incremented by DB
          proNomField.getText(),
          (Integer) depCodCombo.getSelectedItem(),
          (String) proActivoCombo.getSelectedItem());
      provinciaService.createProvincia(provincia);
      loadProvincias();
      clearForm();
      JOptionPane.showMessageDialog(this, "Provincia creada exitosamente");
    } catch (SQLException | IllegalArgumentException e) {
      JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
  }

  private void updateProvincia() {
    try {
      Provincia provincia = new Provincia(
          Integer.parseInt(proCodField.getText()),
          proNomField.getText(),
          (Integer) depCodCombo.getSelectedItem(),
          (String) proActivoCombo.getSelectedItem());
      provinciaService.updateProvincia(provincia);
      loadProvincias();
      clearForm();
      JOptionPane.showMessageDialog(this, "Provincia actualizada exitosamente");
    } catch (SQLException | IllegalArgumentException e) {
      JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
  }

  private void deleteProvincia() {
    try {
      int proCod = Integer.parseInt(proCodField.getText());
      provinciaService.deleteProvincia(proCod);
      loadProvincias();
      clearForm();
      JOptionPane.showMessageDialog(this, "Provincia eliminada exitosamente");
    } catch (SQLException | NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
  }

  private void clearForm() {
    proCodField.setText("");
    proNomField.setText("");
    depCodCombo.setSelectedIndex(0);
    proActivoCombo.setSelectedIndex(0);
  }

  private void selectProvincia() {
    int selectedRow = table.getSelectedRow();
    if (selectedRow >= 0) {
      proCodField.setText(tableModel.getValueAt(selectedRow, 0).toString());
      proNomField.setText(tableModel.getValueAt(selectedRow, 1).toString());
      depCodCombo.setSelectedItem(Integer.valueOf(tableModel.getValueAt(selectedRow, 2).toString()));
      proActivoCombo.setSelectedItem(tableModel.getValueAt(selectedRow, 3).toString());
    }
  }

  private void loadProvincias() {
    try {
      tableModel.setRowCount(0);
      for (Provincia provincia : provinciaService.getAllProvincias()) {
        tableModel.addRow(new Object[] {
            provincia.getProCod(),
            provincia.getProNom(),
            provincia.getDepCod(),
            provincia.getProActivo()
        });
      }
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(this, "Error al cargar provincias: " + e.getMessage());
    }
  }
}