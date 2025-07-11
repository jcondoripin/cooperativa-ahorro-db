package com.unsa.cooperativa.dao;

import com.unsa.cooperativa.entity.Provincia;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProvinciaDAOImpl implements ProvinciaDAO {
  private final Connection connection;

  public ProvinciaDAOImpl(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void create(Provincia provincia) throws SQLException {
    String sql = "INSERT INTO Provincia (ProNom, DepCod, ProActivo) VALUES (?, ?, ?)";
    try (var ps = connection.prepareStatement(sql)) {
      ps.setString(1, provincia.getProNom());
      ps.setInt(2, provincia.getDepCod());
      ps.setString(3, provincia.getProActivo());
      ps.executeUpdate();
    }
  }

  @Override
  public Provincia read(int proCod) throws SQLException {
    String sql = "SELECT * FROM Provincia WHERE ProCod = ?";
    try (var ps = connection.prepareStatement(sql)) {
      ps.setInt(1, proCod);
      try (var rs = ps.executeQuery()) {
        if (rs.next()) {
          return new Provincia(
              rs.getInt("ProCod"),
              rs.getString("ProNom"),
              rs.getInt("DepCod"),
              rs.getString("ProActivo"));
        }
      }
    }
    return null;
  }

  @Override
  public void update(Provincia provincia) throws SQLException {
    String sql = "UPDATE Provincia SET ProNom = ?, DepCod = ?, ProActivo = ? WHERE ProCod = ?";
    try (var ps = connection.prepareStatement(sql)) {
      ps.setString(1, provincia.getProNom());
      ps.setInt(2, provincia.getDepCod());
      ps.setString(3, provincia.getProActivo());
      ps.setInt(4, provincia.getProCod());
      ps.executeUpdate();
    }
  }

  @Override
  public void delete(int proCod) throws SQLException {
    String sql = "DELETE FROM Provincia WHERE ProCod = ?";
    try (var ps = connection.prepareStatement(sql)) {
      ps.setInt(1, proCod);
      ps.executeUpdate();
    }
  }

  @Override
  public List<Provincia> getAll() throws SQLException {
    List<Provincia> provincias = new ArrayList<>();
    String sql = "SELECT * FROM Provincia";
    try (var ps = connection.prepareStatement(sql); var rs = ps.executeQuery()) {
      while (rs.next()) {
        provincias.add(new Provincia(
            rs.getInt("ProCod"),
            rs.getString("ProNom"),
            rs.getInt("DepCod"),
            rs.getString("ProActivo")));
      }
    }
    return provincias;
  }
}