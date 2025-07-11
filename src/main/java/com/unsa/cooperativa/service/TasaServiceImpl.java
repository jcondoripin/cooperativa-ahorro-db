package com.unsa.cooperativa.service;

import com.unsa.cooperativa.dao.TasaDAO;
import com.unsa.cooperativa.entity.Tasa;

import java.sql.SQLException;
import java.util.List;

public class TasaServiceImpl implements TasaService {
  private final TasaDAO tasaDAO;

  public TasaServiceImpl(TasaDAO tasaDAO) {
    this.tasaDAO = tasaDAO;
  }

  @Override
  public void createTasa(Tasa tasa) throws SQLException {
    validateTasa(tasa);
    tasaDAO.create(tasa);
  }

  @Override
  public Tasa getTasa(int tasCodigo) throws SQLException {
    return tasaDAO.read(tasCodigo);
  }

  @Override
  public void updateTasa(Tasa tasa) throws SQLException {
    validateTasa(tasa);
    tasaDAO.update(tasa);
  }

  @Override
  public void deleteTasa(int tasCodigo) throws SQLException {
    tasaDAO.delete(tasCodigo);
  }

  @Override
  public List<Tasa> getAllTasas() throws SQLException {
    return tasaDAO.getAll();
  }

  private void validateTasa(Tasa tasa) {
    if (tasa.getTasIden() == null || tasa.getTasIden().isEmpty()) {
      throw new IllegalArgumentException("Identificador de tasa es requerido");
    }
    if (tasa.getTasDesc() == null || tasa.getTasDesc().isEmpty()) {
      throw new IllegalArgumentException("Descripción de tasa es requerida");
    }
    if (tasa.getTasTasa() < 0) {
      throw new IllegalArgumentException("Tasa debe ser no negativa");
    }
    if (tasa.getTasPlazoDias() <= 0) {
      throw new IllegalArgumentException("Plazo en días debe ser positivo");
    }
    if (tasa.getTasFechaInicio() == null) {
      throw new IllegalArgumentException("Fecha de inicio es requerida");
    }
    if (tasa.getTasFechaFin() == null) {
      throw new IllegalArgumentException("Fecha de fin es requerida");
    }
  }
}