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

import com.unsa.cooperativa.entity.Cooperativa;
import com.unsa.cooperativa.entity.Distrito;
import com.unsa.cooperativa.entity.Socio;
import com.unsa.cooperativa.service.CooperativaService;
import com.unsa.cooperativa.service.DistritoService;
import com.unsa.cooperativa.service.SocioService;

public class SocioPanel extends JPanel {
  private final SocioService socioService;
  private final CooperativaService cooperativaService;
  private final DistritoService distritoService;
  private final JTextField socCodField = new JTextField(5);
  private final JTextField socIdenField = new JTextField(20);
  private final JTextField socApePatField = new JTextField(20);
  private final JTextField socApeMatField = new JTextField(20);
  private final JTextField socNomField = new JTextField(20);
  private final JTextField socFechaNacimientoField = new JTextField(10);
  private final JTextField socCorField = new JTextField(20);
  private final JTextField socTipProField = new JTextField(10);
  private final JTextField socCtaField = new JTextField(20);
  private final JComboBox<Integer> socCooCombo = new JComboBox<>();
  private final JComboBox<Integer> disCodCombo = new JComboBox<>();
  private final JComboBox<String> socActivoCombo = new JComboBox<>(new String[] { "1", "0" });
  private final JTable table;
  private final DefaultTableModel tableModel;
  private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

  public SocioPanel(SocioService socioService, CooperativaService cooperativaService, DistritoService distritoService) {
    this.socioService = socioService;
    this.cooperativaService = cooperativaService;
    this.distritoService = distritoService;
    setLayout(new BorderLayout());

    // Form Panel
    JPanel formPanel = new JPanel(new GridLayout(12, 2, 5, 5));
    formPanel.add(new JLabel("Código:"));
    formPanel.add(socCodField);
    formPanel.add(new JLabel("DNI:"));
    formPanel.add(socIdenField);
    formPanel.add(new JLabel("Apellido Paterno:"));
    formPanel.add(socApePatField);
    formPanel.add(new JLabel("Apellido Materno:"));
    formPanel.add(socApeMatField);
    formPanel.add(new JLabel("Nombre:"));
    formPanel.add(socNomField);
    formPanel.add(new JLabel("Fecha Nacimiento (yyyy-MM-dd):"));
    formPanel.add(socFechaNacimientoField);
    formPanel.add(new JLabel("Correo:"));
    formPanel.add(socCorField);
    formPanel.add(new JLabel("Tipo Producto:"));
    formPanel.add(socTipProField);
    formPanel.add(new JLabel("Cuenta:"));
    formPanel.add(socCtaField);
    formPanel.add(new JLabel("Cooperativa:"));
    formPanel.add(socCooCombo);
    formPanel.add(new JLabel("Distrito:"));
    formPanel.add(disCodCombo);
    formPanel.add(new JLabel("Activo:"));
    formPanel.add(socActivoCombo);

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
    String[] columns = { "Código", "DNI", "Ape. Paterno", "Ape. Materno", "Nombre", "Fecha Nac.", "Correo",
        "Tipo Prod.", "Cuenta", "Cooperativa", "Distrito", "Activo" };
    tableModel = new DefaultTableModel(columns, 0);
    table = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(table);

    // Add components
    add(formPanel, BorderLayout.NORTH);
    add(scrollPane, BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);

    // Event Listeners
    createButton.addActionListener(e -> createSocio());
    updateButton.addActionListener(e -> updateSocio());
    deleteButton.addActionListener(e -> deleteSocio());
    clearButton.addActionListener(e -> clearForm());
    table.getSelectionModel().addListSelectionListener(e -> selectSocio());

    // Load initial data
    loadSocios();
    loadCombos();
  }

  private void loadCombos() {
    try {
      socCooCombo.removeAllItems();
      for (Cooperativa coop : cooperativaService.getAllCooperativas()) {
        socCooCombo.addItem(coop.getCooCod());
      }
      disCodCombo.removeAllItems();
      for (Distrito distrito : distritoService.getAllDistritos()) {
        disCodCombo.addItem(distrito.getDisCod());
      }
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(this, "Error loading combos: " + e.getMessage());
    }
  }

  private void createSocio() {
    try {
      Socio socio = new Socio(
          0, // Auto-incremented by DB
          socIdenField.getText(),
          socApePatField.getText(),
          socApeMatField.getText(),
          socNomField.getText(),
          dateFormat.parse(socFechaNacimientoField.getText()),
          socCorField.getText(),
          socTipProField.getText(),
          socCtaField.getText(),
          (Integer) socCooCombo.getSelectedItem(),
          (Integer) disCodCombo.getSelectedItem(),
          (String) socActivoCombo.getSelectedItem());
      socioService.createSocio(socio);
      loadSocios();
      clearForm();
      JOptionPane.showMessageDialog(this, "Socio creado exitosamente");
    } catch (SQLException | ParseException | IllegalArgumentException e) {
      JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
  }

  private void updateSocio() {
    try {
      Socio socio = new Socio(
          Integer.parseInt(socCodField.getText()),
          socIdenField.getText(),
          socApePatField.getText(),
          socApeMatField.getText(),
          socNomField.getText(),
          dateFormat.parse(socFechaNacimientoField.getText()),
          socCorField.getText(),
          socTipProField.getText(),
          socCtaField.getText(),
          (Integer) socCooCombo.getSelectedItem(),
          (Integer) disCodCombo.getSelectedItem(),
          (String) socActivoCombo.getSelectedItem());
      socioService.updateSocio(socio);
      loadSocios();
      clearForm();
      JOptionPane.showMessageDialog(this, "Socio actualizado exitosamente");
    } catch (SQLException | ParseException | IllegalArgumentException e) {
      JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
  }

  private void deleteSocio() {
    try {
      int socCod = Integer.parseInt(socCodField.getText());
      socioService.deleteSocio(socCod);
      loadSocios();
      clearForm();
      JOptionPane.showMessageDialog(this, "Socio eliminado exitosamente");
    } catch (SQLException | NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
  }

  private void clearForm() {
    socCodField.setText("");
    socIdenField.setText("");
    socApePatField.setText("");
    socApeMatField.setText("");
    socNomField.setText("");
    socFechaNacimientoField.setText("");
    socCorField.setText("");
    socTipProField.setText("");
    socCtaField.setText("");
    socCooCombo.setSelectedIndex(0);
    disCodCombo.setSelectedIndex(0);
    socActivoCombo.setSelectedIndex(0);
  }

  private void selectSocio() {
    int selectedRow = table.getSelectedRow();
    if (selectedRow >= 0) {
      socCodField.setText(tableModel.getValueAt(selectedRow, 0).toString());
      socIdenField.setText(tableModel.getValueAt(selectedRow, 1).toString());
      socApePatField.setText(tableModel.getValueAt(selectedRow, 2).toString());
      socApeMatField.setText(tableModel.getValueAt(selectedRow, 3).toString());
      socNomField.setText(tableModel.getValueAt(selectedRow, 4).toString());
      socFechaNacimientoField.setText(tableModel.getValueAt(selectedRow, 5).toString());
      socCorField.setText(tableModel.getValueAt(selectedRow, 6).toString());
      socTipProField.setText(tableModel.getValueAt(selectedRow, 7).toString());
      socCtaField.setText(tableModel.getValueAt(selectedRow, 8).toString());
      socCooCombo.setSelectedItem(Integer.valueOf(tableModel.getValueAt(selectedRow, 9).toString()));
      disCodCombo.setSelectedItem(Integer.valueOf(tableModel.getValueAt(selectedRow, 10).toString()));
      socActivoCombo.setSelectedItem(tableModel.getValueAt(selectedRow, 11).toString());
    }
  }

  private void loadSocios() {
    try {
      tableModel.setRowCount(0);
      for (Socio socio : socioService.getAllSocios()) {
        tableModel.addRow(new Object[] {
            socio.getSocCod(),
            socio.getSocIden(),
            socio.getSocApePat(),
            socio.getSocApeMat(),
            socio.getSocNom(),
            dateFormat.format(socio.getSocFechaNacimiento()),
            socio.getSocCor(),
            socio.getSocTipPro(),
            socio.getSocCta(),
            socio.getSocCoo(),
            socio.getDisCod(),
            socio.getSocActivo()
        });
      }
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(this, "Error loading socios: " + e.getMessage());
    }
  }
}