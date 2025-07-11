package com.unsa.cooperativa.dao;

import com.unsa.cooperativa.entity.TipoSocio;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoSocioDAOImpl implements TipoSocioDAO {
  private final Connection connection;

  public TipoSocioDAOImpl(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void create(TipoSocio tipoSocio) throws SQLException {
    String sql = "INSERT INTO TipoSocio (TipSocioNom, TipActivo) VALUES (?, ?)";
    try (var ps = connection.prepareStatement(sql)) {
      ps.setString(1, tipoSocio.getTipSocioNom());
      ps.setString(2, tipoSocio.getTipActivo());
      ps.executeUpdate();
    }
  }

  @Override
  public TipoSocio read(int tipSocioCod) throws SQLException {
    String sql = "SELECT * FROM TipoSocio WHERE TipSocioCod = ?";
    try (var ps = connection.prepareStatement(sql)) {
      ps.setInt(1, tipSocioCod);
      try (var rs = ps.executeQuery()) {
        if (rs.next()) {
          return new TipoSocio(
              rs.getInt("TipSocioCod"),
              rs.getString("TipSocioNom"),
              rs.getString("TipActivo"));
        }
      }
    }
    return null;
  }

  @Override
  public void update(TipoSocio tipoSocio) throws SQLException {
    String sql = "UPDATE TipoSocio SET TipSocioNom = ?, TipActivo = ? WHERE TipSocioCod = ?";
    try (var ps = connection.prepareStatement(sql)) {
      ps.setString(1, tipoSocio.getTipSocioNom());
      ps.setString(2, tipoSocio.getTipActivo());
      ps.setInt(3, tipoSocio.getTipSocioCod());
      ps.executeUpdate();
    }
  }

  @Override
  public void delete(int tipSocioCod) throws SQLException {
    String sql = "DELETE FROM TipoSocio WHERE TipSocioCod = ?";
    try (var ps = connection.prepareStatement(sql)) {
      ps.setInt(1, tipSocioCod);
      ps.executeUpdate();
    }
  }

  @Override
  public List<TipoSocio> getAll() throws SQLException {
    List<TipoSocio> tipoSocios = new ArrayList<>();
    String sql = "SELECT * FROM TipoSocio";
    try (var ps = connection.prepareStatement(sql); var rs = ps.executeQuery()) {
      while (rs.next()) {
        tipoSocios.add(new TipoSocio(
            rs.getInt("TipSocioCod"),
            rs.getString("TipSocioNom"),
            rs.getString("TipActivo")));
      }
    }
    return tipoSocios;
  }
}