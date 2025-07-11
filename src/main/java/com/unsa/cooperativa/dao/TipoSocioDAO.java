package com.unsa.cooperativa.dao;

import com.unsa.cooperativa.entity.TipoSocio;

import java.sql.SQLException;
import java.util.List;

public interface TipoSocioDAO {
  void create(TipoSocio tipoSocio) throws SQLException;

  TipoSocio read(int tipSocioCod) throws SQLException;

  void update(TipoSocio tipoSocio) throws SQLException;

  void delete(int tipSocioCod) throws SQLException;

  List<TipoSocio> getAll() throws SQLException;
}