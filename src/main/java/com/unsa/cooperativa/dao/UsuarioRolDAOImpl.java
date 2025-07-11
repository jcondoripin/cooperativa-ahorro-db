package com.unsa.cooperativa.dao;

import com.unsa.cooperativa.entity.UsuarioRol;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRolDAOImpl implements UsuarioRolDAO {
  private final Connection connection;

  public UsuarioRolDAOImpl(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void create(UsuarioRol usuarioRol) throws SQLException {
    String sql = "INSERT INTO UsuarioRol (UsuCod, RolCod) VALUES (?, ?)";
    try (var ps = connection.prepareStatement(sql)) {
      ps.setInt(1, usuarioRol.getUsuCod());
      ps.setInt(2, usuarioRol.getRolCod());
      ps.executeUpdate();
    }
  }

  @Override
  public UsuarioRol read(int usuCod, int rolCod) throws SQLException {
    String sql = "SELECT * FROM UsuarioRol WHERE UsuCod = ? AND RolCod = ?";
    try (var ps = connection.prepareStatement(sql)) {
      ps.setInt(1, usuCod);
      ps.setInt(2, rolCod);
      try (var rs = ps.executeQuery()) {
        if (rs.next()) {
          return new UsuarioRol(
              rs.getInt("UsuCod"),
              rs.getInt("RolCod"));
        }
      }
    }
    return null;
  }

  @Override
  public void delete(int usuCod, int rolCod) throws SQLException {
    String sql = "DELETE FROM UsuarioRol WHERE UsuCod = ? AND RolCod = ?";
    try (var ps = connection.prepareStatement(sql)) {
      ps.setInt(1, usuCod);
      ps.setInt(2, rolCod);
      ps.executeUpdate();
    }
  }

  @Override
  public List<UsuarioRol> getAll() throws SQLException {
    List<UsuarioRol> usuarioRoles = new ArrayList<>();
    String sql = "SELECT * FROM UsuarioRol";
    try (var ps = connection.prepareStatement(sql); var rs = ps.executeQuery()) {
      while (rs.next()) {
        usuarioRoles.add(new UsuarioRol(
            rs.getInt("UsuCod"),
            rs.getInt("RolCod")));
      }
    }
    return usuarioRoles;
  }
}