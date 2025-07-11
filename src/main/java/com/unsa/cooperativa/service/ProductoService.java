package com.unsa.cooperativa.service;

import com.unsa.cooperativa.entity.Producto;

import java.sql.SQLException;
import java.util.List;

public interface ProductoService {
  void createProducto(Producto producto) throws SQLException;

  Producto getProducto(int proCod) throws SQLException;

  void updateProducto(Producto producto) throws SQLException;

  void deleteProducto(int proCod) throws SQLException;

  List<Producto> getAllProductos() throws SQLException;
}