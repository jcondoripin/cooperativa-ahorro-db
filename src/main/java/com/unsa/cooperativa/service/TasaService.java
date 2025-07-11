package com.unsa.cooperativa.service;

import com.unsa.cooperativa.entity.Tasa;

import java.sql.SQLException;
import java.util.List;

public interface TasaService {
  void createTasa(Tasa tasa) throws SQLException;

  Tasa getTasa(int tasCodigo) throws SQLException;

  void updateTasa(Tasa tasa) throws SQLException;

  void deleteTasa(int tasCodigo) throws SQLException;

  List<Tasa> getAllTasas() throws SQLException;
}