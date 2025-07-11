package com.unsa.cooperativa.dao;

import com.unsa.cooperativa.entity.Persona;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class PersonaDAOImpl implements PersonaDAO {
  private final Connection connection;

  public PersonaDAOImpl(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void create(Persona persona) throws SQLException {
    String sql = "INSERT INTO Persona (PerIden, PerApePat, PerApeMat, PerNom, PerFechaNacimiento, PerCor, PerFot, PerCoo, PerActivo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try (var ps = connection.prepareStatement(sql)) {
      ps.setString(1, persona.getPerIden());
      ps.setString(2, persona.getPerApePat());
      ps.setString(3, persona.getPerApeMat());
      ps.setString(4, persona.getPerNom());
      ps.setDate(5, new Date(persona.getPerFechaNacimiento().getTime()));
      ps.setString(6, persona.getPerCor());
      ps.setString(7, persona.getPerFot());
      ps.setInt(8, persona.getPerCoo());
      ps.setString(9, persona.getPerActivo());
      ps.executeUpdate();
    }
  }

  @Override
  public Persona read(int perCod) throws SQLException {
    String sql = "SELECT * FROM Persona WHERE PerCod = ?";
    try (var ps = connection.prepareStatement(sql)) {
      ps.setInt(1, perCod);
      try (var rs = ps.executeQuery()) {
        if (rs.next()) {
          return new Persona(
              rs.getInt("PerCod"),
              rs.getString("PerIden"),
              rs.getString("PerApePat"),
              rs.getString("PerApeMat"),
              rs.getString("PerNom"),
              rs.getDate("PerFechaNacimiento"),
              rs.getString("PerCor"),
              rs.getString("PerFot"),
              rs.getInt("PerCoo"),
              rs.getString("PerActivo"));
        }
      }
    }
    return null;
  }

  @Override
  public void update(Persona persona) throws SQLException {
    String sql = "UPDATE Persona SET PerIden = ?, PerApePat = ?, PerApeMat = ?, PerNom = ?, PerFechaNacimiento = ?, PerCor = ?, PerFot = ?, PerCoo = ?, PerActivo = ? WHERE PerCod = ?";
    try (var ps = connection.prepareStatement(sql)) {
      ps.setString(1, persona.getPerIden());
      ps.setString(2, persona.getPerApePat());
      ps.setString(3, persona.getPerApeMat());
      ps.setString(4, persona.getPerNom());
      ps.setDate(5, new Date(persona.getPerFechaNacimiento().getTime()));
      ps.setString(6, persona.getPerCor());
      ps.setString(7, persona.getPerFot());
      ps.setInt(8, persona.getPerCoo());
      ps.setString(9, persona.getPerActivo());
      ps.setInt(10, persona.getPerCod());
      ps.executeUpdate();
    }
  }

  @Override
  public void delete(int perCod) throws SQLException {
    String sql = "DELETE FROM Persona WHERE PerCod = ?";
    try (var ps = connection.prepareStatement(sql)) {
      ps.setInt(1, perCod);
      ps.executeUpdate();
    }
  }

  @Override
  public List<Persona> getAll() throws SQLException {
    List<Persona> personas = new ArrayList<>();
    String sql = "SELECT * FROM Persona";
    try (var ps = connection.prepareStatement(sql); var rs = ps.executeQuery()) {
      while (rs.next()) {
        personas.add(new Persona(
            rs.getInt("PerCod"),
            rs.getString("PerIden"),
            rs.getString("PerApePat"),
            rs.getString("PerApeMat"),
            rs.getString("PerNom"),
            rs.getDate("PerFechaNacimiento"),
            rs.getString("PerCor"),
            rs.getString("PerFot"),
            rs.getInt("PerCoo"),
            rs.getString("PerActivo")));
      }
    }
    return personas;
  }
}