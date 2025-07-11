package com.unsa.cooperativa.service;

import com.unsa.cooperativa.dao.UsuarioRolDAO;
import com.unsa.cooperativa.entity.UsuarioRol;

import java.sql.SQLException;
import java.util.List;

public class UsuarioRolServiceImpl implements UsuarioRolService {
  private final UsuarioRolDAO usuarioRolDAO;

  public UsuarioRolServiceImpl(UsuarioRolDAO usuarioRolDAO) {
    this.usuarioRolDAO = usuarioRolDAO;
  }

  @Override
  public void createUsuarioRol(UsuarioRol usuarioRol) throws SQLException {
    validateUsuarioRol(usuarioRol);
    usuarioRolDAO.create(usuarioRol);
  }

  @Override
  public UsuarioRol getUsuarioRol(int usuCod, int rolCod) throws SQLException {
    return usuarioRolDAO.read(usuCod, rolCod);
  }

  @Override
  public void deleteUsuarioRol(int usuCod, int rolCod) throws SQLException {
    usuarioRolDAO.delete(usuCod, rolCod);
  }

  @Override
  public List<UsuarioRol> getAllUsuarioRoles() throws SQLException {
    return usuarioRolDAO.getAll();
  }

  private void validateUsuarioRol(UsuarioRol usuarioRol) {
    if (usuarioRol.getUsuCod() <= 0) {
      throw new IllegalArgumentException("Código de usuario válido es requerido");
    }
    if (usuarioRol.getRolCod() <= 0) {
      throw new IllegalArgumentException("Código de rol válido es requerido");
    }
  }
}