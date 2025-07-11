package com.unsa.cooperativa.dao;

import com.unsa.cooperativa.entity.Departamento;

import java.sql.SQLException;
import java.util.List;

public interface DepartamentoDAO {
  void create(Departamento departamento) throws SQLException;

  Departamento read(int depCod) throws SQLException;

  void update(Departamento departamento) throws SQLException;

  void delete(int depCod) throws SQLException;

  List<Departamento> getAll() throws SQLException;
}