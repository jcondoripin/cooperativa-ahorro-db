package com.unsa.cooperativa.service;

import com.unsa.cooperativa.entity.Socio;

import java.sql.SQLException;
import java.util.List;

public interface SocioService {
  void createSocio(Socio socio) throws SQLException;

  Socio getSocio(int socCod) throws SQLException;

  void updateSocio(Socio socio) throws SQLException;

  void deleteSocio(int socCod) throws SQLException;

  List<Socio> getAllSocios() throws SQLException;
}