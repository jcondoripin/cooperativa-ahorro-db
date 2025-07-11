package com.unsa.cooperativa.dao;

import com.unsa.cooperativa.entity.UsuarioRol;

import java.sql.SQLException;
import java.util.List;

public interface UsuarioRolDAO {
  void create(UsuarioRol usuarioRol) throws SQLException;

  UsuarioRol read(int usuCod, int rolCod) throws SQLException;

  void delete(int usuCod, int rolCod) throws SQLException;

  List<UsuarioRol> getAll() throws SQLException;
}