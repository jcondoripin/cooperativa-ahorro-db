package com.unsa.cooperativa.service;

import com.unsa.cooperativa.entity.TipoSocio;

import java.sql.SQLException;
import java.util.List;

public interface TipoSocioService {
  void createTipoSocio(TipoSocio tipoSocio) throws SQLException;

  TipoSocio getTipoSocio(int tipSocioCod) throws SQLException;

  void updateTipoSocio(TipoSocio tipoSocio) throws SQLException;

  void deleteTipoSocio(int tipSocioCod) throws SQLException;

  List<TipoSocio> getAllTipoSocios() throws SQLException;
}