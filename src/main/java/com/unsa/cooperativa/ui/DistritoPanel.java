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

import com.unsa.cooperativa.entity.Distrito;
import com.unsa.cooperativa.entity.Provincia;
import com.unsa.cooperativa.service.DistritoService;
import com.unsa.cooperativa.service.ProvinciaService;

public class DistritoPanel extends JPanel {
  private final DistritoService distritoService;
  private final ProvinciaService provinciaService;
  private final JTextField disCodField = new JTextField(5);
  private final JTextField disNomField = new JTextField(20);
  private final JComboBox<Integer> proCodCombo = new JComboBox<>();
  private final JComboBox<String> disActivoCombo = new JComboBox<>(new String[] { "1", "0" });
  private final JTable table;
  private final DefaultTableModel tableModel;

  public DistritoPanel(DistritoService distritoService, ProvinciaService provinciaService) {
    this.distritoService = distritoService;
    this.provinciaService = provinciaService;
    setLayout(new BorderLayout());

    // Form Panel
    JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));
    formPanel.add(new JLabel("Código:"));
    formPanel.add(disCodField);
    formPanel.add(new JLabel("Nombre:"));
    formPanel.add(disNomField);
    formPanel.add(new JLabel("Provincia:"));
    formPanel.add(proCodCombo);
    formPanel.add(new JLabel("Activo:"));
    formPanel.add(disActivoCombo);

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
    String[] columns = { "Código", "Nombre", "Provincia", "Activo" };
    tableModel = new DefaultTableModel(columns, 0);
    table = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(table);

    // Add components
    add(formPanel, BorderLayout.NORTH);
    add(scrollPane, BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);

    // Event Listeners
    createButton.addActionListener(e -> createDistrito());
    updateButton.addActionListener(e -> updateDistrito());
    deleteButton.addActionListener(e -> deleteDistrito());
    clearButton.addActionListener(e -> clearForm());
    table.getSelectionModel().addListSelectionListener(e -> selectDistrito());

    // Load initial data
    loadDistritos();
    loadProvincias();
  }

  private void loadProvincias() {
    try {
      proCodCombo.removeAllItems();
      for (Provincia provincia : provinciaService.getAllProvincias()) {
        proCodCombo.addItem(provincia.getProCod());
      }
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(this, "Error al cargar provincias: " + e.getMessage());
    }
  }

  private void createDistrito() {
    try {
      Distrito distrito = new Distrito(
          0, // Auto-incremented by DB
          disNomField.getText(),
          (Integer) proCodCombo.getSelectedItem(),
          (String) disActivoCombo.getSelectedItem());
      distritoService.createDistrito(distrito);
      loadDistritos();
      clearForm();
      JOptionPane.showMessageDialog(this, "Distrito creado exitosamente");
    } catch (SQLException | IllegalArgumentException e) {
      JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
  }

  private void updateDistrito() {
    try {
      Distrito distrito = new Distrito(
          Integer.parseInt(disCodField.getText()),
          disNomField.getText(),
          (Integer) proCodCombo.getSelectedItem(),
          (String) disActivoCombo.getSelectedItem());
      distritoService.updateDistrito(distrito);
      loadDistritos();
      clearForm();
      JOptionPane.showMessageDialog(this, "Distrito actualizado exitosamente");
    } catch (SQLException | IllegalArgumentException e) {
      JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
  }

  private void deleteDistrito() {
    try {
      int disCod = Integer.parseInt(disCodField.getText());
      distritoService.deleteDistrito(disCod);
      loadDistritos();
      clearForm();
      JOptionPane.showMessageDialog(this, "Distrito eliminado exitosamente");
    } catch (SQLException | NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
  }

  private void clearForm() {
    disCodField.setText("");
    disNomField.setText("");
    proCodCombo.setSelectedIndex(0);
    disActivoCombo.setSelectedIndex(0);
  }

  private void selectDistrito() {
    int selectedRow = table.getSelectedRow();
    if (selectedRow >= 0) {
      disCodField.setText(tableModel.getValueAt(selectedRow, 0).toString());
      disNomField.setText(tableModel.getValueAt(selectedRow, 1).toString());
      proCodCombo.setSelectedItem(Integer.valueOf(tableModel.getValueAt(selectedRow, 2).toString()));
      disActivoCombo.setSelectedItem(tableModel.getValueAt(selectedRow, 3).toString());
    }
  }

  private void loadDistritos() {
    try {
      tableModel.setRowCount(0);
      for (Distrito distrito : distritoService.getAllDistritos()) {
        tableModel.addRow(new Object[] {
            distrito.getDisCod(),
            distrito.getDisNom(),
            distrito.getProCod(),
            distrito.getDisActivo()
        });
      }
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(this, "Error al cargar distritos: " + e.getMessage());
    }
  }
}