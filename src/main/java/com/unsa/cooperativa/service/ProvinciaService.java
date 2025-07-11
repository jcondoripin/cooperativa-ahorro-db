package com.unsa.cooperativa.service;

import com.unsa.cooperativa.entity.Provincia;

import java.sql.SQLException;
import java.util.List;

public interface ProvinciaService {
  void createProvincia(Provincia provincia) throws SQLException;

  Provincia getProvincia(int proCod) throws SQLException;

  void updateProvincia(Provincia provincia) throws SQLException;

  void deleteProvincia(int proCod) throws SQLException;

  List<Provincia> getAllProvincias() throws SQLException;
}