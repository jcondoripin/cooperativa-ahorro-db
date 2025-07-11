package com.unsa.cooperativa.dao;

import com.unsa.cooperativa.entity.Departamento;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoDAOImpl implements DepartamentoDAO {
  private final Connection connection;

  public DepartamentoDAOImpl(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void create(Departamento departamento) throws SQLException {
    String sql = "INSERT INTO Departamento (DepNom, DepActivo) VALUES (?, ?)";
    try (var ps = connection.prepareStatement(sql)) {
      ps.setString(1, departamento.getDepNom());
      ps.setString(2, departamento.getDepActivo());
      ps.executeUpdate();
    }
  }

  @Override
  public Departamento read(int depCod) throws SQLException {
    String sql = "SELECT * FROM Departamento WHERE DepCod = ?";
    try (var ps = connection.prepareStatement(sql)) {
      ps.setInt(1, depCod);
      try (var rs = ps.executeQuery()) {
        if (rs.next()) {
          return new Departamento(
              rs.getInt("DepCod"),
              rs.getString("DepNom"),
              rs.getString("DepActivo"));
        }
      }
    }
    return null;
  }

  @Override
  public void update(Departamento departamento) throws SQLException {
    String sql = "UPDATE Departamento SET DepNom = ?, DepActivo = ? WHERE DepCod = ?";
    try (var ps = connection.prepareStatement(sql)) {
      ps.setString(1, departamento.getDepNom());
      ps.setString(2, departamento.getDepActivo());
      ps.setInt(3, departamento.getDepCod());
      ps.executeUpdate();
    }
  }

  @Override
  public void delete(int depCod) throws SQLException {
    String sql = "DELETE FROM Departamento WHERE DepCod = ?";
    try (var ps = connection.prepareStatement(sql)) {
      ps.setInt(1, depCod);
      ps.executeUpdate();
    }
  }

  @Override
  public List<Departamento> getAll() throws SQLException {
    List<Departamento> departamentos = new ArrayList<>();
    String sql = "SELECT * FROM Departamento";
    try (var ps = connection.prepareStatement(sql); var rs = ps.executeQuery()) {
      while (rs.next()) {
        departamentos.add(new Departamento(
            rs.getInt("DepCod"),
            rs.getString("DepNom"),
            rs.getString("DepActivo")));
      }
    }
    return departamentos;
  }
}