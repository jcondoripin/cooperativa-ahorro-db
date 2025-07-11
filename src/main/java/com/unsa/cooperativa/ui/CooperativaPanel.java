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

import com.unsa.cooperativa.entity.Cooperativa;
import com.unsa.cooperativa.entity.Usuario;
import com.unsa.cooperativa.service.CooperativaService;
import com.unsa.cooperativa.service.UsuarioService;

public class CooperativaPanel extends JPanel {
  private final CooperativaService cooperativaService;
  private final UsuarioService usuarioService;
  private final JTextField cooCodField = new JTextField(5);
  private final JTextField cooIdenField = new JTextField(20);
  private final JTextField cooNomField = new JTextField(20);
  private final JTextField cooSigField = new JTextField(20);
  private final JTextField cooDirField = new JTextField(20);
  private final JTextField cooTelField = new JTextField(20);
  private final JTextField cooCorField = new JTextField(20);
  private final JTextField cooSloField = new JTextField(20);
  private final JTextField cooLogField = new JTextField(20);
  private final JComboBox<Integer> cooUsuCombo = new JComboBox<>();
  private final JComboBox<String> cooActivoCombo = new JComboBox<>(new String[] { "1", "0" });
  private final JTable table;
  private final DefaultTableModel tableModel;

  public CooperativaPanel(CooperativaService cooperativaService, UsuarioService usuarioService) {
    this.cooperativaService = cooperativaService;
    this.usuarioService = usuarioService;
    setLayout(new BorderLayout());

    // Form Panel
    JPanel formPanel = new JPanel(new GridLayout(11, 2, 5, 5));
    formPanel.add(new JLabel("Código:"));
    formPanel.add(cooCodField);
    formPanel.add(new JLabel("RUC:"));
    formPanel.add(cooIdenField);
    formPanel.add(new JLabel("Nombre:"));
    formPanel.add(cooNomField);
    formPanel.add(new JLabel("Siglas:"));
    formPanel.add(cooSigField);
    formPanel.add(new JLabel("Dirección:"));
    formPanel.add(cooDirField);
    formPanel.add(new JLabel("Teléfono:"));
    formPanel.add(cooTelField);
    formPanel.add(new JLabel("Correo:"));
    formPanel.add(cooCorField);
    formPanel.add(new JLabel("Slogan:"));
    formPanel.add(cooSloField);
    formPanel.add(new JLabel("Logo:"));
    formPanel.add(cooLogField);
    formPanel.add(new JLabel("Usuario:"));
    formPanel.add(cooUsuCombo);
    formPanel.add(new JLabel("Activo:"));
    formPanel.add(cooActivoCombo);

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
    String[] columns = { "Código", "RUC", "Nombre", "Siglas", "Dirección", "Teléfono", "Correo", "Slogan", "Logo",
        "Usuario", "Activo" };
    tableModel = new DefaultTableModel(columns, 0);
    table = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(table);

    // Add components
    add(formPanel, BorderLayout.NORTH);
    add(scrollPane, BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);

    // Event Listeners
    createButton.addActionListener(e -> createCooperativa());
    updateButton.addActionListener(e -> updateCooperativa());
    deleteButton.addActionListener(e -> deleteCooperativa());
    clearButton.addActionListener(e -> clearForm());
    table.getSelectionModel().addListSelectionListener(e -> selectCooperativa());

    // Load initial data
    loadCooperativas();
    loadUsuarios();
  }

  private void loadUsuarios() {
    try {
      cooUsuCombo.removeAllItems();
      for (Usuario usuario : usuarioService.getAllUsuarios()) {
        cooUsuCombo.addItem(usuario.getUsuCod());
      }
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(this, "Error al cargar usuarios: " + e.getMessage());
    }
  }

  private void createCooperativa() {
    try {
      Cooperativa cooperativa = new Cooperativa(
          0, // Auto-incremented by DB
          cooIdenField.getText(),
          cooNomField.getText(),
          cooSigField.getText(),
          cooDirField.getText(),
          cooTelField.getText(),
          cooCorField.getText(),
          cooSloField.getText(),
          cooLogField.getText(),
          (Integer) cooUsuCombo.getSelectedItem(),
          (String) cooActivoCombo.getSelectedItem());
      cooperativaService.createCooperativa(cooperativa);
      loadCooperativas();
      clearForm();
      JOptionPane.showMessageDialog(this, "Cooperativa creada exitosamente");
    } catch (SQLException | IllegalArgumentException e) {
      JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
  }

  private void updateCooperativa() {
    try {
      Cooperativa cooperativa = new Cooperativa(
          Integer.parseInt(cooCodField.getText()),
          cooIdenField.getText(),
          cooNomField.getText(),
          cooSigField.getText(),
          cooDirField.getText(),
          cooTelField.getText(),
          cooCorField.getText(),
          cooSloField.getText(),
          cooLogField.getText(),
          (Integer) cooUsuCombo.getSelectedItem(),
          (String) cooActivoCombo.getSelectedItem());
      cooperativaService.updateCooperativa(cooperativa);
      loadCooperativas();
      clearForm();
      JOptionPane.showMessageDialog(this, "Cooperativa actualizada exitosamente");
    } catch (SQLException | IllegalArgumentException e) {
      JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
  }

  private void deleteCooperativa() {
    try {
      int cooCod = Integer.parseInt(cooCodField.getText());
      cooperativaService.deleteCooperativa(cooCod);
      loadCooperativas();
      clearForm();
      JOptionPane.showMessageDialog(this, "Cooperativa eliminada exitosamente");
    } catch (SQLException | NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
  }

  private void clearForm() {
    cooCodField.setText("");
    cooIdenField.setText("");
    cooNomField.setText("");
    cooSigField.setText("");
    cooDirField.setText("");
    cooTelField.setText("");
    cooCorField.setText("");
    cooSloField.setText("");
    cooLogField.setText("");
    cooUsuCombo.setSelectedIndex(0);
    cooActivoCombo.setSelectedIndex(0);
  }

  private void selectCooperativa() {
    int selectedRow = table.getSelectedRow();
    if (selectedRow >= 0) {
      cooCodField.setText(tableModel.getValueAt(selectedRow, 0).toString());
      cooIdenField.setText(tableModel.getValueAt(selectedRow, 1).toString());
      cooNomField.setText(tableModel.getValueAt(selectedRow, 2).toString());
      cooSigField.setText(tableModel.getValueAt(selectedRow, 3).toString());
      cooDirField.setText(tableModel.getValueAt(selectedRow, 4).toString());
      cooTelField.setText(tableModel.getValueAt(selectedRow, 5).toString());
      cooCorField.setText(tableModel.getValueAt(selectedRow, 6).toString());
      cooSloField.setText(tableModel.getValueAt(selectedRow, 7).toString());
      cooLogField.setText(tableModel.getValueAt(selectedRow, 8).toString());
      cooUsuCombo.setSelectedItem(Integer.valueOf(tableModel.getValueAt(selectedRow, 9).toString()));
      cooActivoCombo.setSelectedItem(tableModel.getValueAt(selectedRow, 10).toString());
    }
  }

  private void loadCooperativas() {
    try {
      tableModel.setRowCount(0);
      for (Cooperativa cooperativa : cooperativaService.getAllCooperativas()) {
        tableModel.addRow(new Object[] {
            cooperativa.getCooCod(),
            cooperativa.getCooIden(),
            cooperativa.getCooNom(),
            cooperativa.getCooSig(),
            cooperativa.getCooDir(),
            cooperativa.getCooTel(),
            cooperativa.getCooCor(),
            cooperativa.getCooSlo(),
            cooperativa.getCooLog(),
            cooperativa.getCooUsu(),
            cooperativa.getCooActivo()
        });
      }
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(this, "Error al cargar cooperativas: " + e.getMessage());
    }
  }
}