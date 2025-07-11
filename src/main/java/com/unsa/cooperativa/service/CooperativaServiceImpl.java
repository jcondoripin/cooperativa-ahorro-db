package com.unsa.cooperativa.service;

import java.sql.SQLException;
import java.util.List;

import com.unsa.cooperativa.dao.CooperativaDAO;
import com.unsa.cooperativa.entity.Cooperativa;

public class CooperativaServiceImpl implements CooperativaService {
  private final CooperativaDAO cooperativaDAO;

  public CooperativaServiceImpl(CooperativaDAO cooperativaDAO) {
    this.cooperativaDAO = cooperativaDAO;
  }

  @Override
  public void createCooperativa(Cooperativa cooperativa) throws SQLException {
    validateCooperativa(cooperativa);
    cooperativaDAO.create(cooperativa);
  }

  @Override
  public Cooperativa getCooperativa(int cooCod) throws SQLException {
    return cooperativaDAO.read(cooCod);
  }

  @Override
  public void updateCooperativa(Cooperativa cooperativa) throws SQLException {
    validateCooperativa(cooperativa);
    cooperativaDAO.update(cooperativa);
  }

  @Override
  public void deleteCooperativa(int cooCod) throws SQLException {
    cooperativaDAO.delete(cooCod);
  }

  @Override
  public List<Cooperativa> getAllCooperativas() throws SQLException {
    return cooperativaDAO.getAll();
  }

  private void validateCooperativa(Cooperativa cooperativa) {
    if (cooperativa.getCooIden() == null || cooperativa.getCooIden().isEmpty()) {
      throw new IllegalArgumentException("Identificador de cooperativa es requerido");
    }
    if (cooperativa.getCooNom() == null || cooperativa.getCooNom().isEmpty()) {
      throw new IllegalArgumentException("Nombre de cooperativa es requerido");
    }
    if (cooperativa.getCooUsu() <= 0) {
      throw new IllegalArgumentException("Usuario válido es requerido");
    }
  }
}