package com.unsa.cooperativa.service;

import com.unsa.cooperativa.entity.Cooperativa;

import java.sql.SQLException;
import java.util.List;

public interface CooperativaService {
  void createCooperativa(Cooperativa cooperativa) throws SQLException;

  Cooperativa getCooperativa(int cooCod) throws SQLException;

  void updateCooperativa(Cooperativa cooperativa) throws SQLException;

  void deleteCooperativa(int cooCod) throws SQLException;

  List<Cooperativa> getAllCooperativas() throws SQLException;
}