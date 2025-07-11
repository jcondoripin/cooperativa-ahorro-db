package com.unsa.cooperativa.service;

import com.unsa.cooperativa.dao.ProductoDAO;
import com.unsa.cooperativa.entity.Producto;

import java.sql.SQLException;
import java.util.List;

public class ProductoServiceImpl implements ProductoService {
  private final ProductoDAO productoDAO;

  public ProductoServiceImpl(ProductoDAO productoDAO) {
    this.productoDAO = productoDAO;
  }

  @Override
  public void createProducto(Producto producto) throws SQLException {
    validateProducto(producto);
    productoDAO.create(producto);
  }

  @Override
  public Producto getProducto(int proCod) throws SQLException {
    return productoDAO.read(proCod);
  }

  @Override
  public void updateProducto(Producto producto) throws SQLException {
    validateProducto(producto);
    productoDAO.update(producto);
  }

  @Override
  public void deleteProducto(int proCod) throws SQLException {
    productoDAO.delete(proCod);
  }

  @Override
  public List<Producto> getAllProductos() throws SQLException {
    return productoDAO.getAll();
  }

  private void validateProducto(Producto producto) {
    if (producto.getProIden() == null || producto.getProIden().isEmpty()) {
      throw new IllegalArgumentException("Identificador de producto es requerido");
    }
    if (producto.getProDes() == null || producto.getProDes().isEmpty()) {
      throw new IllegalArgumentException("Descripción de producto es requerida");
    }
    if (producto.getTipSocioCod() <= 0) {
      throw new IllegalArgumentException("Código de tipo de socio válido es requerido");
    }
    if (producto.getProMon() < 0) {
      throw new IllegalArgumentException("Monto debe ser no negativo");
    }
  }
}