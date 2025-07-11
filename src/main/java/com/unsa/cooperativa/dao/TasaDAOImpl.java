package com.unsa.cooperativa.dao;

import com.unsa.cooperativa.entity.Tasa;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class TasaDAOImpl implements TasaDAO {
  private final Connection connection;

  public TasaDAOImpl(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void create(Tasa tasa) throws SQLException {
    String sql = "INSERT INTO Tasa (TasIden, TasDesc, TasTasa, TasPlazoDias, TasFechaInicio, TasFechaFin, TasActivo) VALUES (?, ?, ?, ?, ?, ?, ?)";
    try (var ps = connection.prepareStatement(sql)) {
      ps.setString(1, tasa.getTasIden());
      ps.setString(2, tasa.getTasDesc());
      ps.setDouble(3, tasa.getTasTasa());
      ps.setInt(4, tasa.getTasPlazoDias());
      ps.setDate(5, new Date(tasa.getTasFechaInicio().getTime()));
      ps.setDate(6, new Date(tasa.getTasFechaFin().getTime()));
      ps.setString(7, tasa.getTasActivo());
      ps.executeUpdate();
    }
  }

  @Override
  public Tasa read(int tasCodigo) throws SQLException {
    String sql = "SELECT * FROM Tasa WHERE TasCodigo = ?";
    try (var ps = connection.prepareStatement(sql)) {
      ps.setInt(1, tasCodigo);
      try (var rs = ps.executeQuery()) {
        if (rs.next()) {
          return new Tasa(
              rs.getInt("TasCodigo"),
              rs.getString("TasIden"),
              rs.getString("TasDesc"),
              rs.getDouble("TasTasa"),
              rs.getInt("TasPlazoDias"),
              rs.getDate("TasFechaInicio"),
              rs.getDate("TasFechaFin"),
              rs.getString("TasActivo"));
        }
      }
    }
    return null;
  }

  @Override
  public void update(Tasa tasa) throws SQLException {
    String sql = "UPDATE Tasa SET TasIden = ?, TasDesc = ?, TasTasa = ?, TasPlazoDias = ?, TasFechaInicio = ?, TasFechaFin = ?, TasActivo = ? WHERE TasCodigo = ?";
    try (var ps = connection.prepareStatement(sql)) {
      ps.setString(1, tasa.getTasIden());
      ps.setString(2, tasa.getTasDesc());
      ps.setDouble(3, tasa.getTasTasa());
      ps.setInt(4, tasa.getTasPlazoDias());
      ps.setDate(5, new Date(tasa.getTasFechaInicio().getTime()));
      ps.setDate(6, new Date(tasa.getTasFechaFin().getTime()));
      ps.setString(7, tasa.getTasActivo());
      ps.setInt(8, tasa.getTasCodigo());
      ps.executeUpdate();
    }
  }

  @Override
  public void delete(int tasCodigo) throws SQLException {
    String sql = "DELETE FROM Tasa WHERE TasCodigo = ?";
    try (var ps = connection.prepareStatement(sql)) {
      ps.setInt(1, tasCodigo);
      ps.executeUpdate();
    }
  }

  @Override
  public List<Tasa> getAll() throws SQLException {
    List<Tasa> tasas = new ArrayList<>();
    String sql = "SELECT * FROM Tasa";
    try (var ps = connection.prepareStatement(sql); var rs = ps.executeQuery()) {
      while (rs.next()) {
        tasas.add(new Tasa(
            rs.getInt("TasCodigo"),
            rs.getString("TasIden"),
            rs.getString("TasDesc"),
            rs.getDouble("TasTasa"),
            rs.getInt("TasPlazoDias"),
            rs.getDate("TasFechaInicio"),
            rs.getDate("TasFechaFin"),
            rs.getString("TasActivo")));
      }
    }
    return tasas;
  }
}