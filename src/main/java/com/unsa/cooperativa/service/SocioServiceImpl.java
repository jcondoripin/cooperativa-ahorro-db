package com.unsa.cooperativa.service;

import com.unsa.cooperativa.dao.SocioDAO;
import com.unsa.cooperativa.entity.Socio;

import java.sql.SQLException;
import java.util.List;

public class SocioServiceImpl implements SocioService {
  private final SocioDAO socioDAO;

  public SocioServiceImpl(SocioDAO socioDAO) {
    this.socioDAO = socioDAO;
  }

  @Override
  public void createSocio(Socio socio) throws SQLException {
    validateSocio(socio);
    socioDAO.create(socio);
  }

  @Override
  public Socio getSocio(int socCod) throws SQLException {
    return socioDAO.read(socCod);
  }

  @Override
  public void updateSocio(Socio socio) throws SQLException {
    validateSocio(socio);
    socioDAO.update(socio);
  }

  @Override
  public void deleteSocio(int socCod) throws SQLException {
    socioDAO.delete(socCod);
  }

  @Override
  public List<Socio> getAllSocios() throws SQLException {
    return socioDAO.getAll();
  }

  private void validateSocio(Socio socio) {
    if (socio.getSocIden() == null || socio.getSocIden().isEmpty()) {
      throw new IllegalArgumentException("Socio ID is required");
    }
    if (socio.getSocApePat() == null || socio.getSocApePat().isEmpty()) {
      throw new IllegalArgumentException("Apellido Paterno is required");
    }
    if (socio.getSocApeMat() == null || socio.getSocApeMat().isEmpty()) {
      throw new IllegalArgumentException("Apellido Materno is required");
    }
    if (socio.getSocNom() == null || socio.getSocNom().isEmpty()) {
      throw new IllegalArgumentException("Nombre is required");
    }
    if (socio.getSocFechaNacimiento() == null) {
      throw new IllegalArgumentException("Fecha de Nacimiento is required");
    }
    if (socio.getSocCoo() <= 0) {
      throw new IllegalArgumentException("Valid Cooperativa ID is required");
    }
    if (socio.getDisCod() <= 0) {
      throw new IllegalArgumentException("Valid Distrito ID is required");
    }
  }
}