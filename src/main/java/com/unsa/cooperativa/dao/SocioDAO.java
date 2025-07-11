package com.unsa.cooperativa.dao;

import com.unsa.cooperativa.entity.Socio;

import java.sql.SQLException;
import java.util.List;

public interface SocioDAO {
  void create(Socio socio) throws SQLException;

  Socio read(int socCod) throws SQLException;

  void update(Socio socio) throws SQLException;

  void delete(int socCod) throws SQLException;

  List<Socio> getAll() throws SQLException;
}