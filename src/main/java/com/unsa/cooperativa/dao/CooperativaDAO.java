package com.unsa.cooperativa.dao;

import com.unsa.cooperativa.entity.Cooperativa;

import java.sql.SQLException;
import java.util.List;

public interface CooperativaDAO {
  void create(Cooperativa cooperativa) throws SQLException;

  Cooperativa read(int cooCod) throws SQLException;

  void update(Cooperativa cooperativa) throws SQLException;

  void delete(int cooCod) throws SQLException;

  List<Cooperativa> getAll() throws SQLException;
}