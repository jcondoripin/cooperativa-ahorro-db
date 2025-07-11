package com.unsa.cooperativa.service;

import com.unsa.cooperativa.dao.DepartamentoDAO;
import com.unsa.cooperativa.entity.Departamento;

import java.sql.SQLException;
import java.util.List;

public class DepartamentoServiceImpl implements DepartamentoService {
  private final DepartamentoDAO departamentoDAO;

  public DepartamentoServiceImpl(DepartamentoDAO departamentoDAO) {
    this.departamentoDAO = departamentoDAO;
  }

  @Override
  public void createDepartamento(Departamento departamento) throws SQLException {
    validateDepartamento(departamento);
    departamentoDAO.create(departamento);
  }

  @Override
  public Departamento getDepartamento(int depCod) throws SQLException {
    return departamentoDAO.read(depCod);
  }

  @Override
  public void updateDepartamento(Departamento departamento) throws SQLException {
    validateDepartamento(departamento);
    departamentoDAO.update(departamento);
  }

  @Override
  public void deleteDepartamento(int depCod) throws SQLException {
    departamentoDAO.delete(depCod);
  }

  @Override
  public List<Departamento> getAllDepartamentos() throws SQLException {
    return departamentoDAO.getAll();
  }

  private void validateDepartamento(Departamento departamento) {
    if (departamento.getDepNom() == null || departamento.getDepNom().isEmpty()) {
      throw new IllegalArgumentException("Nombre de departamento es requerido");
    }
  }
}