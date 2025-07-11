package com.unsa.cooperativa.dao;

import com.unsa.cooperativa.entity.Distrito;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DistritoDAOImpl implements DistritoDAO {
  private final Connection connection;

  public DistritoDAOImpl(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void create(Distrito distrito) throws SQLException {
    String sql = "INSERT INTO Distrito (DisNom, ProCod, DisActivo) VALUES (?, ?, ?)";
    try (var ps = connection.prepareStatement(sql)) {
      ps.setString(1, distrito.getDisNom());
      ps.setInt(2, distrito.getProCod());
      ps.setString(3, distrito.getDisActivo());
      ps.executeUpdate();
    }
  }

  @Override
  public Distrito read(int disCod) throws SQLException {
    String sql = "SELECT * FROM Distrito WHERE DisCod = ?";
    try (var ps = connection.prepareStatement(sql)) {
      ps.setInt(1, disCod);
      try (var rs = ps.executeQuery()) {
        if (rs.next()) {
          return new Distrito(
              rs.getInt("DisCod"),
              rs.getString("DisNom"),
              rs.getInt("ProCod"),
              rs.getString("DisActivo"));
        }
      }
    }
    return null;
  }

  @Override
  public void update(Distrito distrito) throws SQLException {
    String sql = "UPDATE Distrito SET DisNom = ?, ProCod = ?, DisActivo = ? WHERE DisCod = ?";
    try (var ps = connection.prepareStatement(sql)) {
      ps.setString(1, distrito.getDisNom());
      ps.setInt(2, distrito.getProCod());
      ps.setString(3, distrito.getDisActivo());
      ps.setInt(4, distrito.getDisCod());
      ps.executeUpdate();
    }
  }

  @Override
  public void delete(int disCod) throws SQLException {
    String sql = "DELETE FROM Distrito WHERE DisCod = ?";
    try (var ps = connection.prepareStatement(sql)) {
      ps.setInt(1, disCod);
      ps.executeUpdate();
    }
  }

  @Override
  public List<Distrito> getAll() throws SQLException {
    List<Distrito> distritos = new ArrayList<>();
    String sql = "SELECT * FROM Distrito";
    try (var ps = connection.prepareStatement(sql); var rs = ps.executeQuery()) {
      while (rs.next()) {
        distritos.add(new Distrito(
            rs.getInt("DisCod"),
            rs.getString("DisNom"),
            rs.getInt("ProCod"),
            rs.getString("DisActivo")));
      }
    }
    return distritos;
  }
}