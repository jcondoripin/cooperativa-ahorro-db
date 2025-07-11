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
import com.unsa.cooperativa.entity.Persona;
import com.unsa.cooperativa.service.CooperativaService;
import com.unsa.cooperativa.service.PersonaService;

public class PersonaPanel extends JPanel {
  private final PersonaService personaService;
  private final CooperativaService cooperativaService;
  private final JTextField perCodField = new JTextField(5);
  private final JTextField perIdenField = new JTextField(20);
  private final JTextField perApePatField = new JTextField(20);
  private final JTextField perApeMatField = new JTextField(20);
  private final JTextField perNomField = new JTextField(20);
  private final JTextField perFechaNacimientoField = new JTextField(10);
  private final JTextField perCorField = new JTextField(20);
  private final JTextField perFotField = new JTextField(20);
  private final JComboBox<Integer> perCooCombo = new JComboBox<>();
  private final JComboBox<String> perActivoCombo = new JComboBox<>(new String[] { "1", "0" });
  private final JTable table;
  private final DefaultTableModel tableModel;
  private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

  public PersonaPanel(PersonaService personaService, CooperativaService cooperativaService) {
    this.personaService = personaService;
    this.cooperativaService = cooperativaService;
    setLayout(new BorderLayout());

    // Form Panel
    JPanel formPanel = new JPanel(new GridLayout(10, 2, 5, 5));
    formPanel.add(new JLabel("Código:"));
    formPanel.add(perCodField);
    formPanel.add(new JLabel("DNI:"));
    formPanel.add(perIdenField);
    formPanel.add(new JLabel("Apellido Paterno:"));
    formPanel.add(perApePatField);
    formPanel.add(new JLabel("Apellido Materno:"));
    formPanel.add(perApeMatField);
    formPanel.add(new JLabel("Nombre:"));
    formPanel.add(perNomField);
    formPanel.add(new JLabel("Fecha Nacimiento (yyyy-MM-dd):"));
    formPanel.add(perFechaNacimientoField);
    formPanel.add(new JLabel("Correo:"));
    formPanel.add(perCorField);
    formPanel.add(new JLabel("Foto:"));
    formPanel.add(perFotField);
    formPanel.add(new JLabel("Cooperativa:"));
    formPanel.add(perCooCombo);
    formPanel.add(new JLabel("Activo:"));
    formPanel.add(perActivoCombo);

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
    String[] columns = { "Código", "DNI", "Ape. Paterno", "Ape. Materno", "Nombre", "Fecha Nac.", "Correo", "Foto",
        "Cooperativa", "Activo" };
    tableModel = new DefaultTableModel(columns, 0);
    table = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(table);

    // Add components
    add(formPanel, BorderLayout.NORTH);
    add(scrollPane, BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);

    // Event Listeners
    createButton.addActionListener(e -> createPersona());
    updateButton.addActionListener(e -> updatePersona());
    deleteButton.addActionListener(e -> deletePersona());
    clearButton.addActionListener(e -> clearForm());
    table.getSelectionModel().addListSelectionListener(e -> selectPersona());

    // Load initial data
    loadPersonas();
    loadCooperativas();
  }

  private void loadCooperativas() {
    try {
      perCooCombo.removeAllItems();
      for (Cooperativa cooperativa : cooperativaService.getAllCooperativas()) {
        perCooCombo.addItem(cooperativa.getCooCod());
      }
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(this, "Error al cargar cooperativas: " + e.getMessage());
    }
  }

  private void createPersona() {
    try {
      Persona persona = new Persona(
          0, // Auto-incremented by DB
          perIdenField.getText(),
          perApePatField.getText(),
          perApeMatField.getText(),
          perNomField.getText(),
          dateFormat.parse(perFechaNacimientoField.getText()),
          perCorField.getText(),
          perFotField.getText(),
          (Integer) perCooCombo.getSelectedItem(),
          (String) perActivoCombo.getSelectedItem());
      personaService.createPersona(persona);
      loadPersonas();
      clearForm();
      JOptionPane.showMessageDialog(this, "Persona creada exitosamente");
    } catch (SQLException | ParseException | IllegalArgumentException e) {
      JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
  }

  private void updatePersona() {
    try {
      Persona persona = new Persona(
          Integer.parseInt(perCodField.getText()),
          perIdenField.getText(),
          perApePatField.getText(),
          perApeMatField.getText(),
          perNomField.getText(),
          dateFormat.parse(perFechaNacimientoField.getText()),
          perCorField.getText(),
          perFotField.getText(),
          (Integer) perCooCombo.getSelectedItem(),
          (String) perActivoCombo.getSelectedItem());
      personaService.updatePersona(persona);
      loadPersonas();
      clearForm();
      JOptionPane.showMessageDialog(this, "Persona actualizada exitosamente");
    } catch (SQLException | ParseException | IllegalArgumentException e) {
      JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
  }

  private void deletePersona() {
    try {
      int perCod = Integer.parseInt(perCodField.getText());
      personaService.deletePersona(perCod);
      loadPersonas();
      clearForm();
      JOptionPane.showMessageDialog(this, "Persona eliminada exitosamente");
    } catch (SQLException | NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
  }

  private void clearForm() {
    perCodField.setText("");
    perIdenField.setText("");
    perApePatField.setText("");
    perApeMatField.setText("");
    perNomField.setText("");
    perFechaNacimientoField.setText("");
    perCorField.setText("");
    perFotField.setText("");
    perCooCombo.setSelectedIndex(0);
    perActivoCombo.setSelectedIndex(0);
  }

  private void selectPersona() {
    int selectedRow = table.getSelectedRow();
    if (selectedRow >= 0) {
      perCodField.setText(tableModel.getValueAt(selectedRow, 0).toString());
      perIdenField.setText(tableModel.getValueAt(selectedRow, 1).toString());
      perApePatField.setText(tableModel.getValueAt(selectedRow, 2).toString());
      perApeMatField.setText(tableModel.getValueAt(selectedRow, 3).toString());
      perNomField.setText(tableModel.getValueAt(selectedRow, 4).toString());
      perFechaNacimientoField.setText(tableModel.getValueAt(selectedRow, 5).toString());
      perCorField.setText(tableModel.getValueAt(selectedRow, 6).toString());
      perFotField.setText(tableModel.getValueAt(selectedRow, 7).toString());
      perCooCombo.setSelectedItem(Integer.valueOf(tableModel.getValueAt(selectedRow, 8).toString()));
      perActivoCombo.setSelectedItem(tableModel.getValueAt(selectedRow, 9).toString());
    }
  }

  private void loadPersonas() {
    try {
      tableModel.setRowCount(0);
      for (Persona persona : personaService.getAllPersonas()) {
        tableModel.addRow(new Object[] {
            persona.getPerCod(),
            persona.getPerIden(),
            persona.getPerApePat(),
            persona.getPerApeMat(),
            persona.getPerNom(),
            dateFormat.format(persona.getPerFechaNacimiento()),
            persona.getPerCor(),
            persona.getPerFot(),
            persona.getPerCoo(),
            persona.getPerActivo()
        });
      }
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(this, "Error al cargar personas: " + e.getMessage());
    }
  }
}