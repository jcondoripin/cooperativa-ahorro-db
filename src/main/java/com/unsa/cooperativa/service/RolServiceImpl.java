package com.unsa.cooperativa.service;

import java.sql.SQLException;
import java.util.List;

import com.unsa.cooperativa.dao.RolDAO;
import com.unsa.cooperativa.entity.Rol;

public class RolServiceImpl implements RolService {
  private final RolDAO rolDAO;

  public RolServiceImpl(RolDAO rolDAO) {
    this.rolDAO = rolDAO;
  }

  @Override
  public void createRol(Rol rol) throws SQLException {
    validateRol(rol);
    rolDAO.create(rol);
  }

  @Override
  public Rol getRol(int rolCod) throws SQLException {
    return rolDAO.read(rolCod);
  }

  @Override
  public void updateRol(Rol rol) throws SQLException {
    validateRol(rol);
    rolDAO.update(rol);
  }

  @Override
  public void deleteRol(int rolCod) throws SQLException {
    rolDAO.delete(rolCod);
  }

  @Override
  public List<Rol> getAllRoles() throws SQLException {
    return rolDAO.getAll();
  }

  private void validateRol(Rol rol) {
    if (rol.getRolRol() == null || rol.getRolRol().isEmpty()) {
      throw new IllegalArgumentException("Rol identifier is required");
    }
    if (rol.getRolNom() == null || rol.getRolNom().isEmpty()) {
      throw new IllegalArgumentException("Rol name is required");
    }
  }
}