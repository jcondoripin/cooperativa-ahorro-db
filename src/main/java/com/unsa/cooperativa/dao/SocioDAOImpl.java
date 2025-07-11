package com.unsa.cooperativa.dao;

import com.unsa.cooperativa.entity.Socio;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class SocioDAOImpl implements SocioDAO {
  private final Connection connection;

  public SocioDAOImpl(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void create(Socio socio) throws SQLException {
    String sql = "INSERT INTO Socio (SocIden, SocApePat, SocApeMat, SocNom, SocFechaNacimiento, SocCor, SocTipPro, SocCta, SocCoo, DisCod, SocActivo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try (var ps = connection.prepareStatement(sql)) {
      ps.setString(1, socio.getSocIden());
      ps.setString(2, socio.getSocApePat());
      ps.setString(3, socio.getSocApeMat());
      ps.setString(4, socio.getSocNom());
      ps.setDate(5, new Date(socio.getSocFechaNacimiento().getTime()));
      ps.setString(6, socio.getSocCor());
      ps.setString(7, socio.getSocTipPro());
      ps.setString(8, socio.getSocCta());
      ps.setInt(9, socio.getSocCoo());
      ps.setInt(10, socio.getDisCod());
      ps.setString(11, socio.getSocActivo());
      ps.executeUpdate();
    }
  }

  @Override
  public Socio read(int socCod) throws SQLException {
    String sql = "SELECT * FROM Socio WHERE SocCod = ?";
    try (var ps = connection.prepareStatement(sql)) {
      ps.setInt(1, socCod);
      try (var rs = ps.executeQuery()) {
        if (rs.next()) {
          return new Socio(
              rs.getInt("SocCod"),
              rs.getString("SocIden"),
              rs.getString("SocApePat"),
              rs.getString("SocApeMat"),
              rs.getString("SocNom"),
              rs.getDate("SocFechaNacimiento"),
              rs.getString("SocCor"),
              rs.getString("SocTipPro"),
              rs.getString("SocCta"),
              rs.getInt("SocCoo"),
              rs.getInt("DisCod"),
              rs.getString("SocActivo"));
        }
      }
    }
    return null;
  }

  @Override
  public void update(Socio socio) throws SQLException {
    String sql = "UPDATE Socio SET SocIden = ?, SocApePat = ?, SocApeMat = ?, SocNom = ?, SocFechaNacimiento = ?, SocCor = ?, SocTipPro = ?, SocCta = ?, SocCoo = ?, DisCod = ?, SocActivo = ? WHERE SocCod = ?";
    try (var ps = connection.prepareStatement(sql)) {
      ps.setString(1, socio.getSocIden());
      ps.setString(2, socio.getSocApePat());
      ps.setString(3, socio.getSocApeMat());
      ps.setString(4, socio.getSocNom());
      ps.setDate(5, new Date(socio.getSocFechaNacimiento().getTime()));
      ps.setString(6, socio.getSocCor());
      ps.setString(7, socio.getSocTipPro());
      ps.setString(8, socio.getSocCta());
      ps.setInt(9, socio.getSocCoo());
      ps.setInt(10, socio.getDisCod());
      ps.setString(11, socio.getSocActivo());
      ps.setInt(12, socio.getSocCod());
      ps.executeUpdate();
    }
  }

  @Override
  public void delete(int socCod) throws SQLException {
    String sql = "DELETE FROM Socio WHERE SocCod = ?";
    try (var ps = connection.prepareStatement(sql)) {
      ps.setInt(1, socCod);
      ps.executeUpdate();
    }
  }

  @Override
  public List<Socio> getAll() throws SQLException {
    List<Socio> socios = new ArrayList<>();
    String sql = "SELECT * FROM Socio";
    try (var ps = connection.prepareStatement(sql); var rs = ps.executeQuery()) {
      while (rs.next()) {
        socios.add(new Socio(
            rs.getInt("SocCod"),
            rs.getString("SocIden"),
            rs.getString("SocApePat"),
            rs.getString("SocApeMat"),
            rs.getString("SocNom"),
            rs.getDate("SocFechaNacimiento"),
            rs.getString("SocCor"),
            rs.getString("SocTipPro"),
            rs.getString("SocCta"),
            rs.getInt("SocCoo"),
            rs.getInt("DisCod"),
            rs.getString("SocActivo")));
      }
    }
    return socios;
  }
}