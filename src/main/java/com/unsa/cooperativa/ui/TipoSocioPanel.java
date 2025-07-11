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

import com.unsa.cooperativa.entity.TipoSocio;
import com.unsa.cooperativa.service.TipoSocioService;

public class TipoSocioPanel extends JPanel {
  private final TipoSocioService tipoSocioService;
  private final JTextField tipSocioCodField = new JTextField(5);
  private final JTextField tipSocioNomField = new JTextField(20);
  private final JComboBox<String> tipActivoCombo = new JComboBox<>(new String[] { "1", "0" });
  private final JTable table;
  private final DefaultTableModel tableModel;

  public TipoSocioPanel(TipoSocioService tipoSocioService) {
    this.tipoSocioService = tipoSocioService;
    setLayout(new BorderLayout());

    // Form Panel
    JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));
    formPanel.add(new JLabel("Código:"));
    formPanel.add(tipSocioCodField);
    formPanel.add(new JLabel("Nombre:"));
    formPanel.add(tipSocioNomField);
    formPanel.add(new JLabel("Activo:"));
    formPanel.add(tipActivoCombo);

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
    createButton.addActionListener(e -> createTipoSocio());
    updateButton.addActionListener(e -> updateTipoSocio());
    deleteButton.addActionListener(e -> deleteTipoSocio());
    clearButton.addActionListener(e -> clearForm());
    table.getSelectionModel().addListSelectionListener(e -> selectTipoSocio());

    // Load initial data
    loadTipoSocios();
  }

  private void createTipoSocio() {
    try {
      TipoSocio tipoSocio = new TipoSocio(
          0, // Auto-incremented by DB
          tipSocioNomField.getText(),
          (String) tipActivoCombo.getSelectedItem());
      tipoSocioService.createTipoSocio(tipoSocio);
      loadTipoSocios();
      clearForm();
      JOptionPane.showMessageDialog(this, "Tipo de socio creado exitosamente");
    } catch (SQLException | IllegalArgumentException e) {
      JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
  }

  private void updateTipoSocio() {
    try {
      TipoSocio tipoSocio = new TipoSocio(
          Integer.parseInt(tipSocioCodField.getText()),
          tipSocioNomField.getText(),
          (String) tipActivoCombo.getSelectedItem());
      tipoSocioService.updateTipoSocio(tipoSocio);
      loadTipoSocios();
      clearForm();
      JOptionPane.showMessageDialog(this, "Tipo de socio actualizado exitosamente");
    } catch (SQLException | IllegalArgumentException e) {
      JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
  }

  private void deleteTipoSocio() {
    try {
      int tipSocioCod = Integer.parseInt(tipSocioCodField.getText());
      tipoSocioService.deleteTipoSocio(tipSocioCod);
      loadTipoSocios();
      clearForm();
      JOptionPane.showMessageDialog(this, "Tipo de socio eliminado exitosamente");
    } catch (SQLException | NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
  }

  private void clearForm() {
    tipSocioCodField.setText("");
    tipSocioNomField.setText("");
    tipActivoCombo.setSelectedIndex(0);
  }

  private void selectTipoSocio() {
    int selectedRow = table.getSelectedRow();
    if (selectedRow >= 0) {
      tipSocioCodField.setText(tableModel.getValueAt(selectedRow, 0).toString());
      tipSocioNomField.setText(tableModel.getValueAt(selectedRow, 1).toString());
      tipActivoCombo.setSelectedItem(tableModel.getValueAt(selectedRow, 2).toString());
    }
  }

  private void loadTipoSocios() {
    try {
      tableModel.setRowCount(0);
      for (TipoSocio tipoSocio : tipoSocioService.getAllTipoSocios()) {
        tableModel.addRow(new Object[] {
            tipoSocio.getTipSocioCod(),
            tipoSocio.getTipSocioNom(),
            tipoSocio.getTipActivo()
        });
      }
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(this, "Error al cargar tipos de socio: " + e.getMessage());
    }
  }
}