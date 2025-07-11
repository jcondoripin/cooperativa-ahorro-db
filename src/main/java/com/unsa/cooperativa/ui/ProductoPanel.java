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

import com.unsa.cooperativa.entity.Producto;
import com.unsa.cooperativa.entity.TipoSocio;
import com.unsa.cooperativa.service.ProductoService;
import com.unsa.cooperativa.service.TipoSocioService;

public class ProductoPanel extends JPanel {
  private final ProductoService productoService;
  private final TipoSocioService tipoSocioService;
  private final JTextField proCodField = new JTextField(5);
  private final JTextField proIdenField = new JTextField(20);
  private final JTextField proDesField = new JTextField(20);
  private final JComboBox<Integer> tipSocioCodCombo = new JComboBox<>();
  private final JTextField proMonField = new JTextField(10);
  private final JComboBox<String> proActivoCombo = new JComboBox<>(new String[] { "1", "0" });
  private final JTable table;
  private final DefaultTableModel tableModel;

  public ProductoPanel(ProductoService productoService, TipoSocioService tipoSocioService) {
    this.productoService = productoService;
    this.tipoSocioService = tipoSocioService;
    setLayout(new BorderLayout());

    // Form Panel
    JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));
    formPanel.add(new JLabel("Código:"));
    formPanel.add(proCodField);
    formPanel.add(new JLabel("Identificador:"));
    formPanel.add(proIdenField);
    formPanel.add(new JLabel("Descripción:"));
    formPanel.add(proDesField);
    formPanel.add(new JLabel("Tipo Socio:"));
    formPanel.add(tipSocioCodCombo);
    formPanel.add(new JLabel("Monto:"));
    formPanel.add(proMonField);
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
    String[] columns = { "Código", "Identificador", "Descripción", "Tipo Socio", "Monto", "Activo" };
    tableModel = new DefaultTableModel(columns, 0);
    table = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(table);

    // Add components
    add(formPanel, BorderLayout.NORTH);
    add(scrollPane, BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);

    // Event Listeners
    createButton.addActionListener(e -> createProducto());
    updateButton.addActionListener(e -> updateProducto());
    deleteButton.addActionListener(e -> deleteProducto());
    clearButton.addActionListener(e -> clearForm());
    table.getSelectionModel().addListSelectionListener(e -> selectProducto());

    // Load initial data
    loadProductos();
    loadTipoSocios();
  }

  private void loadTipoSocios() {
    try {
      tipSocioCodCombo.removeAllItems();
      for (TipoSocio tipoSocio : tipoSocioService.getAllTipoSocios()) {
        tipSocioCodCombo.addItem(tipoSocio.getTipSocioCod());
      }
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(this, "Error al cargar tipos de socio: " + e.getMessage());
    }
  }

  private void createProducto() {
    try {
      Producto producto = new Producto(
          0, // Auto-incremented by DB
          proIdenField.getText(),
          proDesField.getText(),
          (Integer) tipSocioCodCombo.getSelectedItem(),
          Double.parseDouble(proMonField.getText()),
          (String) proActivoCombo.getSelectedItem());
      productoService.createProducto(producto);
      loadProductos();
      clearForm();
      JOptionPane.showMessageDialog(this, "Producto creado exitosamente");
    } catch (SQLException | IllegalArgumentException e) {
      JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
  }

  private void updateProducto() {
    try {
      Producto producto = new Producto(
          Integer.parseInt(proCodField.getText()),
          proIdenField.getText(),
          proDesField.getText(),
          (Integer) tipSocioCodCombo.getSelectedItem(),
          Double.parseDouble(proMonField.getText()),
          (String) proActivoCombo.getSelectedItem());
      productoService.updateProducto(producto);
      loadProductos();
      clearForm();
      JOptionPane.showMessageDialog(this, "Producto actualizado exitosamente");
    } catch (SQLException | IllegalArgumentException e) {
      JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
  }

  private void deleteProducto() {
    try {
      int proCod = Integer.parseInt(proCodField.getText());
      productoService.deleteProducto(proCod);
      loadProductos();
      clearForm();
      JOptionPane.showMessageDialog(this, "Producto eliminado exitosamente");
    } catch (SQLException | NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
  }

  private void clearForm() {
    proCodField.setText("");
    proIdenField.setText("");
    proDesField.setText("");
    tipSocioCodCombo.setSelectedIndex(0);
    proMonField.setText("");
    proActivoCombo.setSelectedIndex(0);
  }

  private void selectProducto() {
    int selectedRow = table.getSelectedRow();
    if (selectedRow >= 0) {
      proCodField.setText(tableModel.getValueAt(selectedRow, 0).toString());
      proIdenField.setText(tableModel.getValueAt(selectedRow, 1).toString());
      proDesField.setText(tableModel.getValueAt(selectedRow, 2).toString());
      tipSocioCodCombo.setSelectedItem(Integer.valueOf(tableModel.getValueAt(selectedRow, 3).toString()));
      proMonField.setText(tableModel.getValueAt(selectedRow, 4).toString());
      proActivoCombo.setSelectedItem(tableModel.getValueAt(selectedRow, 5).toString());
    }
  }

  private void loadProductos() {
    try {
      tableModel.setRowCount(0);
      for (Producto producto : productoService.getAllProductos()) {
        tableModel.addRow(new Object[] {
            producto.getProCod(),
            producto.getProIden(),
            producto.getProDes(),
            producto.getTipSocioCod(),
            producto.getProMon(),
            producto.getProActivo()
        });
      }
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(this, "Error al cargar productos: " + e.getMessage());
    }
  }
}