package com.unsa.cooperativa.dao;

import com.unsa.cooperativa.entity.Producto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOImpl implements ProductoDAO {
  private final Connection connection;

  public ProductoDAOImpl(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void create(Producto producto) throws SQLException {
    String sql = "INSERT INTO Producto (ProIden, ProDes, TipSocioCod, ProMon, ProActivo) VALUES (?, ?, ?, ?, ?)";
    try (var ps = connection.prepareStatement(sql)) {
      ps.setString(1, producto.getProIden());
      ps.setString(2, producto.getProDes());
      ps.setInt(3, producto.getTipSocioCod());
      ps.setDouble(4, producto.getProMon());
      ps.setString(5, producto.getProActivo());
      ps.executeUpdate();
    }
  }

  @Override
  public Producto read(int proCod) throws SQLException {
    String sql = "SELECT * FROM Producto WHERE ProCod = ?";
    try (var ps = connection.prepareStatement(sql)) {
      ps.setInt(1, proCod);
      try (var rs = ps.executeQuery()) {
        if (rs.next()) {
          return new Producto(
              rs.getInt("ProCod"),
              rs.getString("ProIden"),
              rs.getString("ProDes"),
              rs.getInt("TipSocioCod"),
              rs.getDouble("ProMon"),
              rs.getString("ProActivo"));
        }
      }
    }
    return null;
  }

  @Override
  public void update(Producto producto) throws SQLException {
    String sql = "UPDATE Producto SET ProIden = ?, ProDes = ?, TipSocioCod = ?, ProMon = ?, ProActivo = ? WHERE ProCod = ?";
    try (var ps = connection.prepareStatement(sql)) {
      ps.setString(1, producto.getProIden());
      ps.setString(2, producto.getProDes());
      ps.setInt(3, producto.getTipSocioCod());
      ps.setDouble(4, producto.getProMon());
      ps.setString(5, producto.getProActivo());
      ps.setInt(6, producto.getProCod());
      ps.executeUpdate();
    }
  }

  @Override
  public void delete(int proCod) throws SQLException {
    String sql = "DELETE FROM Producto WHERE ProCod = ?";
    try (var ps = connection.prepareStatement(sql)) {
      ps.setInt(1, proCod);
      ps.executeUpdate();
    }
  }

  @Override
  public List<Producto> getAll() throws SQLException {
    List<Producto> productos = new ArrayList<>();
    String sql = "SELECT * FROM Producto";
    try (var ps = connection.prepareStatement(sql); var rs = ps.executeQuery()) {
      while (rs.next()) {
        productos.add(new Producto(
            rs.getInt("ProCod"),
            rs.getString("ProIden"),
            rs.getString("ProDes"),
            rs.getInt("TipSocioCod"),
            rs.getDouble("ProMon"),
            rs.getString("ProActivo")));
      }
    }
    return productos;
  }
}