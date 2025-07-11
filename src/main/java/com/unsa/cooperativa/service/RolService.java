package com.unsa.cooperativa.service;

import com.unsa.cooperativa.entity.Rol;

import java.sql.SQLException;
import java.util.List;

public interface RolService {
  void createRol(Rol rol) throws SQLException;

  Rol getRol(int rolCod) throws SQLException;

  void updateRol(Rol rol) throws SQLException;

  void deleteRol(int rolCod) throws SQLException;

  List<Rol> getAllRoles() throws SQLException;
}