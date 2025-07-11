package com.unsa.cooperativa.dao;

import com.unsa.cooperativa.entity.Producto;

import java.sql.SQLException;
import java.util.List;

public interface ProductoDAO {
  void create(Producto producto) throws SQLException;

  Producto read(int proCod) throws SQLException;

  void update(Producto producto) throws SQLException;

  void delete(int proCod) throws SQLException;

  List<Producto> getAll() throws SQLException;
}