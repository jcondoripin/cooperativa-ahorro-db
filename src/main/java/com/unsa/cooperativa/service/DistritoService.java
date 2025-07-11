package com.unsa.cooperativa.service;

import com.unsa.cooperativa.entity.Distrito;

import java.sql.SQLException;
import java.util.List;

public interface DistritoService {
  void createDistrito(Distrito distrito) throws SQLException;

  Distrito getDistrito(int disCod) throws SQLException;

  void updateDistrito(Distrito distrito) throws SQLException;

  void deleteDistrito(int disCod) throws SQLException;

  List<Distrito> getAllDistritos() throws SQLException;
}