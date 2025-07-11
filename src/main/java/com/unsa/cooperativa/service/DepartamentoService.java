package com.unsa.cooperativa.service;

import com.unsa.cooperativa.entity.Departamento;

import java.sql.SQLException;
import java.util.List;

public interface DepartamentoService {
  void createDepartamento(Departamento departamento) throws SQLException;

  Departamento getDepartamento(int depCod) throws SQLException;

  void updateDepartamento(Departamento departamento) throws SQLException;

  void deleteDepartamento(int depCod) throws SQLException;

  List<Departamento> getAllDepartamentos() throws SQLException;
}