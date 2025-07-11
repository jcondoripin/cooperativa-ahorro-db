package com.unsa.cooperativa.dao;

import com.unsa.cooperativa.entity.Provincia;

import java.sql.SQLException;
import java.util.List;

public interface ProvinciaDAO {
  void create(Provincia provincia) throws SQLException;

  Provincia read(int proCod) throws SQLException;

  void update(Provincia provincia) throws SQLException;

  void delete(int proCod) throws SQLException;

  List<Provincia> getAll() throws SQLException;
}