package com.unsa.cooperativa.service;

import com.unsa.cooperativa.dao.ProvinciaDAO;
import com.unsa.cooperativa.entity.Provincia;

import java.sql.SQLException;
import java.util.List;

public class ProvinciaServiceImpl implements ProvinciaService {
  private final ProvinciaDAO provinciaDAO;

  public ProvinciaServiceImpl(ProvinciaDAO provinciaDAO) {
    this.provinciaDAO = provinciaDAO;
  }

  @Override
  public void createProvincia(Provincia provincia) throws SQLException {
    validateProvincia(provincia);
    provinciaDAO.create(provincia);
  }

  @Override
  public Provincia getProvincia(int proCod) throws SQLException {
    return provinciaDAO.read(proCod);
  }

  @Override
  public void updateProvincia(Provincia provincia) throws SQLException {
    validateProvincia(provincia);
    provinciaDAO.update(provincia);
  }

  @Override
  public void deleteProvincia(int proCod) throws SQLException {
    provinciaDAO.delete(proCod);
  }

  @Override
  public List<Provincia> getAllProvincias() throws SQLException {
    return provinciaDAO.getAll();
  }

  private void validateProvincia(Provincia provincia) {
    if (provincia.getProNom() == null || provincia.getProNom().isEmpty()) {
      throw new IllegalArgumentException("Nombre de provincia es requerido");
    }
    if (provincia.getDepCod() <= 0) {
      throw new IllegalArgumentException("Código de departamento válido es requerido");
    }
  }
}