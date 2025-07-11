package com.unsa.cooperativa.dao;

import com.unsa.cooperativa.entity.Cooperativa;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CooperativaDAOImpl implements CooperativaDAO {
  private final Connection connection;

  public CooperativaDAOImpl(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void create(Cooperativa cooperativa) throws SQLException {
    String sql = "INSERT INTO Cooperativa (CooIden, CooNom, CooSig, CooDir, CooTel, CooCor, CooSlo, CooLog, CoTarjeta, CooUsu, CooActivo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try (var ps = connection.prepareStatement(sql)) {
      ps.setString(1, cooperativa.getCooIden());
      ps.setString(2, cooperativa.getCooNom());
      ps.setString(3, cooperativa.getCooSig());
      ps.setString(4, cooperativa.getCooDir());
      ps.setString(5, cooperativa.getCooTel());
      ps.setString(6, cooperativa.getCooCor());
      ps.setString(7, cooperativa.getCooSlo());
      ps.setString(8, cooperativa.getCooLog());
      ps.setInt(9, cooperativa.getCooUsu());
      ps.setString(10, cooperativa.getCooActivo());
      ps.executeUpdate();
    }
  }

  @Override
  public Cooperativa read(int cooCod) throws SQLException {
    String sql = "SELECT * FROM Cooperativa WHERE CooCod = ?";
    try (var ps = connection.prepareStatement(sql)) {
      ps.setInt(1, cooCod);
      try (var rs = ps.executeQuery()) {
        if (rs.next()) {
          return new Cooperativa(
              rs.getInt("CooCod"),
              rs.getString("CooIden"),
              rs.getString("CooNom"),
              rs.getString("CooSig"),
              rs.getString("CooDir"),
              rs.getString("CooTel"),
              rs.getString("CooCor"),
              rs.getString("CooSlo"),
              rs.getString("CooLog"),
              rs.getInt("CooUsu"),
              rs.getString("CooActivo"));
        }
      }
    }
    return null;
  }

  @Override
  public void update(Cooperativa cooperativa) throws SQLException {
    String sql = "UPDATE Cooperativa SET CooIden = ?, CooNom = ?, CooSig = ?, CooDir = ?, CooTel = ?, CooCor = ?, CooSlo = ?, CooLog = ?, CooUsu = ?, CooActivo = ? WHERE CooCod = ?";
    try (var ps = connection.prepareStatement(sql)) {
      ps.setString(1, cooperativa.getCooIden());
      ps.setString(2, cooperativa.getCooNom());
      ps.setString(3, cooperativa.getCooSig());
      ps.setString(4, cooperativa.getCooDir());
      ps.setString(5, cooperativa.getCooTel());
      ps.setString(6, cooperativa.getCooCor());
      ps.setString(7, cooperativa.getCooSlo());
      ps.setString(8, cooperativa.getCooLog());
      ps.setInt(9, cooperativa.getCooUsu());
      ps.setString(10, cooperativa.getCooActivo());
      ps.setInt(11, cooperativa.getCooCod());
      ps.executeUpdate();
    }
  }

  @Override
  public void delete(int cooCod) throws SQLException {
    String sql = "DELETE FROM Cooperativa WHERE CooCod = ?";
    try (var ps = connection.prepareStatement(sql)) {
      ps.setInt(1, cooCod);
      ps.executeUpdate();
    }
  }

  @Override
  public List<Cooperativa> getAll() throws SQLException {
    List<Cooperativa> cooperativas = new ArrayList<>();
    String sql = "SELECT * FROM Cooperativa";
    try (var ps = connection.prepareStatement(sql); var rs = ps.executeQuery()) {
      while (rs.next()) {
        cooperativas.add(new Cooperativa(
            rs.getInt("CooCod"),
            rs.getString("CooIden"),
            rs.getString("CooNom"),
            rs.getString("CooSig"),
            rs.getString("CooDir"),
            rs.getString("CooTel"),
            rs.getString("CooCor"),
            rs.getString("CooSlo"),
            rs.getString("CooLog"),
            rs.getInt("CooUsu"),
            rs.getString("CooActivo")));
      }
    }
    return cooperativas;
  }
}