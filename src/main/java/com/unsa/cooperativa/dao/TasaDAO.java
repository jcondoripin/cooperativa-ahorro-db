package com.unsa.cooperativa.dao;

import com.unsa.cooperativa.entity.Tasa;

import java.sql.SQLException;
import java.util.List;

public interface TasaDAO {
  void create(Tasa tasa) throws SQLException;

  Tasa read(int tasCodigo) throws SQLException;

  void update(Tasa tasa) throws SQLException;

  void delete(int tasCodigo) throws SQLException;

  List<Tasa> getAll() throws SQLException;
}