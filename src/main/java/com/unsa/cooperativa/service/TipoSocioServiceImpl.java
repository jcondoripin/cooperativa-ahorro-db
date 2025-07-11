package com.unsa.cooperativa.service;

import com.unsa.cooperativa.dao.TipoSocioDAO;
import com.unsa.cooperativa.entity.TipoSocio;

import java.sql.SQLException;
import java.util.List;

public class TipoSocioServiceImpl implements TipoSocioService {
  private final TipoSocioDAO tipoSocioDAO;

  public TipoSocioServiceImpl(TipoSocioDAO tipoSocioDAO) {
    this.tipoSocioDAO = tipoSocioDAO;
  }

  @Override
  public void createTipoSocio(TipoSocio tipoSocio) throws SQLException {
    validateTipoSocio(tipoSocio);
    tipoSocioDAO.create(tipoSocio);
  }

  @Override
  public TipoSocio getTipoSocio(int tipSocioCod) throws SQLException {
    return tipoSocioDAO.read(tipSocioCod);
  }

  @Override
  public void updateTipoSocio(TipoSocio tipoSocio) throws SQLException {
    validateTipoSocio(tipoSocio);
    tipoSocioDAO.update(tipoSocio);
  }

  @Override
  public void deleteTipoSocio(int tipSocioCod) throws SQLException {
    tipoSocioDAO.delete(tipSocioCod);
  }

  @Override
  public List<TipoSocio> getAllTipoSocios() throws SQLException {
    return tipoSocioDAO.getAll();
  }

  private void validateTipoSocio(TipoSocio tipoSocio) {
    if (tipoSocio.getTipSocioNom() == null || tipoSocio.getTipSocioNom().isEmpty()) {
      throw new IllegalArgumentException("Nombre de tipo de socio es requerido");
    }
  }
}