package com.unsa.cooperativa.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.unsa.cooperativa.entity.Tasa;
import com.unsa.cooperativa.service.TasaService;

public class TasaPanel extends JPanel {
  private final TasaService tasaService;
  private final JTextField tasCodigoField = new JTextField(5);
  private final JTextField tasIdenField = new JTextField(20);
  private final JTextField tasDescField = new JTextField(20);
  private final JTextField tasTasaField = new JTextField(10);
  private final JTextField tasPlazoDiasField = new JTextField(10);
  private final JTextField tasFechaInicioField = new JTextField(10);
  private final JTextField tasFechaFinField = new JTextField(10);
  private final JComboBox<String> tasActivoCombo = new JComboBox<>(new String[] { "1", "0" });
  private final JTable table;
  private final DefaultTableModel tableModel;
  private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

  public TasaPanel(TasaService tasaService) {
    this.tasaService = tasaService;
    setLayout(new BorderLayout());

    // Form Panel
    JPanel formPanel = new JPanel(new GridLayout(8, 2, 5, 5));
    formPanel.add(new JLabel("Código:"));
    formPanel.add(tasCodigoField);
    formPanel.add(new JLabel("Identificador:"));
    formPanel.add(tasIdenField);
    formPanel.add(new JLabel("Descripción:"));
    formPanel.add(tasDescField);
    formPanel.add(new JLabel("Tasa (%):"));
    formPanel.add(tasTasaField);
    formPanel.add(new JLabel("Plazo (días):"));
    formPanel.add(tasPlazoDiasField);
    formPanel.add(new JLabel("Fecha Inicio (yyyy-MM-dd):"));
    formPanel.add(tasFechaInicioField);
    formPanel.add(new JLabel("Fecha Fin (yyyy-MM-dd):"));
    formPanel.add(tasFechaFinField);
    formPanel.add(new JLabel("Activo:"));
    formPanel.add(tasActivoCombo);

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
    String[] columns = { "Código", "Identificador", "Descripción", "Tasa", "Plazo (días)", "Fecha Inicio", "Fecha Fin",
        "Activo" };
    tableModel = new DefaultTableModel(columns, 0);
    table = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(table);

    // Add components
    add(formPanel, BorderLayout.NORTH);
    add(scrollPane, BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);

    // Event Listeners
    createButton.addActionListener(e -> createTasa());
    updateButton.addActionListener(e -> updateTasa());
    deleteButton.addActionListener(e -> deleteTasa());
    clearButton.addActionListener(e -> clearForm());
    table.getSelectionModel().addListSelectionListener(e -> selectTasa());

    // Load initial data
    loadTasas();
  }

  private void createTasa() {
    try {
      Tasa tasa = new Tasa(
          0, // Auto-incremented by DB
          tasIdenField.getText(),
          tasDescField.getText(),
          Double.parseDouble(tasTasaField.getText()),
          Integer.parseInt(tasPlazoDiasField.getText()),
          dateFormat.parse(tasFechaInicioField.getText()),
          dateFormat.parse(tasFechaFinField.getText()),
          (String) tasActivoCombo.getSelectedItem());
      tasaService.createTasa(tasa);
      loadTasas();
      clearForm();
      JOptionPane.showMessageDialog(this, "Tasa creada exitosamente");
    } catch (SQLException | ParseException | IllegalArgumentException e) {
      JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
  }

  private void updateTasa() {
    try {
      Tasa tasa = new Tasa(
          Integer.parseInt(tasCodigoField.getText()),
          tasIdenField.getText(),
          tasDescField.getText(),
          Double.parseDouble(tasTasaField.getText()),
          Integer.parseInt(tasPlazoDiasField.getText()),
          dateFormat.parse(tasFechaInicioField.getText()),
          dateFormat.parse(tasFechaFinField.getText()),
          (String) tasActivoCombo.getSelectedItem());
      tasaService.updateTasa(tasa);
      loadTasas();
      clearForm();
      JOptionPane.showMessageDialog(this, "Tasa actualizada exitosamente");
    } catch (SQLException | ParseException | IllegalArgumentException e) {
      JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
  }

  private void deleteTasa() {
    try {
      int tasCodigo = Integer.parseInt(tasCodigoField.getText());
      tasaService.deleteTasa(tasCodigo);
      loadTasas();
      clearForm();
      JOptionPane.showMessageDialog(this, "Tasa eliminada exitosamente");
    } catch (SQLException | NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
  }

  private void clearForm() {
    tasCodigoField.setText("");
    tasIdenField.setText("");
    tasDescField.setText("");
    tasTasaField.setText("");
    tasPlazoDiasField.setText("");
    tasFechaInicioField.setText("");
    tasFechaFinField.setText("");
    tasActivoCombo.setSelectedIndex(0);
  }

  private void selectTasa() {
    int selectedRow = table.getSelectedRow();
    if (selectedRow >= 0) {
      tasCodigoField.setText(tableModel.getValueAt(selectedRow, 0).toString());
      tasIdenField.setText(tableModel.getValueAt(selectedRow, 1).toString());
      tasDescField.setText(tableModel.getValueAt(selectedRow, 2).toString());
      tasTasaField.setText(tableModel.getValueAt(selectedRow, 3).toString());
      tasPlazoDiasField.setText(tableModel.getValueAt(selectedRow, 4).toString());
      tasFechaInicioField.setText(tableModel.getValueAt(selectedRow, 5).toString());
      tasFechaFinField.setText(tableModel.getValueAt(selectedRow, 6).toString());
      tasActivoCombo.setSelectedItem(tableModel.getValueAt(selectedRow, 7).toString());
    }
  }

  private void loadTasas() {
    try {
      tableModel.setRowCount(0);
      for (Tasa tasa : tasaService.getAllTasas()) {
        tableModel.addRow(new Object[] {
            tasa.getTasCodigo(),
            tasa.getTasIden(),
            tasa.getTasDesc(),
            tasa.getTasTasa(),
            tasa.getTasPlazoDias(),
            dateFormat.format(tasa.getTasFechaInicio()),
            dateFormat.format(tasa.getTasFechaFin()),
            tasa.getTasActivo()
        });
      }
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(this, "Error al cargar tasas: " + e.getMessage());
    }
  }
}