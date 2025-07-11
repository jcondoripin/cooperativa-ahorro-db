package com.unsa.cooperativa.dao;

import com.unsa.cooperativa.entity.Distrito;

import java.sql.SQLException;
import java.util.List;

public interface DistritoDAO {
  void create(Distrito distrito) throws SQLException;

  Distrito read(int disCod) throws SQLException;

  void update(Distrito distrito) throws SQLException;

  void delete(int disCod) throws SQLException;

  List<Distrito> getAll() throws SQLException;
}