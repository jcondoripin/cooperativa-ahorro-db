package com.unsa.cooperativa.service;

import com.unsa.cooperativa.dao.DistritoDAO;
import com.unsa.cooperativa.entity.Distrito;

import java.sql.SQLException;
import java.util.List;

public class DistritoServiceImpl implements DistritoService {
  private final DistritoDAO distritoDAO;

  public DistritoServiceImpl(DistritoDAO distritoDAO) {
    this.distritoDAO = distritoDAO;
  }

  @Override
  public void createDistrito(Distrito distrito) throws SQLException {
    validateDistrito(distrito);
    distritoDAO.create(distrito);
  }

  @Override
  public Distrito getDistrito(int disCod) throws SQLException {
    return distritoDAO.read(disCod);
  }

  @Override
  public void updateDistrito(Distrito distrito) throws SQLException {
    validateDistrito(distrito);
    distritoDAO.update(distrito);
  }

  @Override
  public void deleteDistrito(int disCod) throws SQLException {
    distritoDAO.delete(disCod);
  }

  @Override
  public List<Distrito> getAllDistritos() throws SQLException {
    return distritoDAO.getAll();
  }

  private void validateDistrito(Distrito distrito) {
    if (distrito.getDisNom() == null || distrito.getDisNom().isEmpty()) {
      throw new IllegalArgumentException("Nombre de distrito es requerido");
    }
    if (distrito.getProCod() <= 0) {
      throw new IllegalArgumentException("Código de provincia válido es requerido");
    }
  }
}