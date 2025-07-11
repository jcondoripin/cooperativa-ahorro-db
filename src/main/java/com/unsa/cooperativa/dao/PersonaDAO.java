package com.unsa.cooperativa.dao;

import com.unsa.cooperativa.entity.Persona;

import java.sql.SQLException;
import java.util.List;

public interface PersonaDAO {
  void create(Persona persona) throws SQLException;

  Persona read(int perCod) throws SQLException;

  void update(Persona persona) throws SQLException;

  void delete(int perCod) throws SQLException;

  List<Persona> getAll() throws SQLException;
}