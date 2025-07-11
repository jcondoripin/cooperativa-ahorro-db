package com.unsa.cooperativa.service;

import com.unsa.cooperativa.entity.UsuarioRol;

import java.sql.SQLException;
import java.util.List;

public interface UsuarioRolService {
  void createUsuarioRol(UsuarioRol usuarioRol) throws SQLException;

  UsuarioRol getUsuarioRol(int usuCod, int rolCod) throws SQLException;

  void deleteUsuarioRol(int usuCod, int rolCod) throws SQLException;

  List<UsuarioRol> getAllUsuarioRoles() throws SQLException;
}