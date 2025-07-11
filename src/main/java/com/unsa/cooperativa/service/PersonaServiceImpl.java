package com.unsa.cooperativa.service;

import com.unsa.cooperativa.dao.PersonaDAO;
import com.unsa.cooperativa.entity.Persona;

import java.sql.SQLException;
import java.util.List;

public class PersonaServiceImpl implements PersonaService {
  private final PersonaDAO personaDAO;

  public PersonaServiceImpl(PersonaDAO personaDAO) {
    this.personaDAO = personaDAO;
  }

  @Override
  public void createPersona(Persona persona) throws SQLException {
    validatePersona(persona);
    personaDAO.create(persona);
  }

  @Override
  public Persona getPersona(int perCod) throws SQLException {
    return personaDAO.read(perCod);
  }

  @Override
  public void updatePersona(Persona persona) throws SQLException {
    validatePersona(persona);
    personaDAO.update(persona);
  }

  @Override
  public void deletePersona(int perCod) throws SQLException {
    personaDAO.delete(perCod);
  }

  @Override
  public List<Persona> getAllPersonas() throws SQLException {
    return personaDAO.getAll();
  }

  private void validatePersona(Persona persona) {
    if (persona.getPerIden() == null || persona.getPerIden().isEmpty()) {
      throw new IllegalArgumentException("DNI de persona es requerido");
    }
    if (persona.getPerApePat() == null || persona.getPerApePat().isEmpty()) {
      throw new IllegalArgumentException("Apellido paterno es requerido");
    }
    if (persona.getPerApeMat() == null || persona.getPerApeMat().isEmpty()) {
      throw new IllegalArgumentException("Apellido materno es requerido");
    }
    if (persona.getPerNom() == null || persona.getPerNom().isEmpty()) {
      throw new IllegalArgumentException("Nombre es requerido");
    }
    if (persona.getPerFechaNacimiento() == null) {
      throw new IllegalArgumentException("Fecha de nacimiento es requerida");
    }
    if (persona.getPerCoo() <= 0) {
      throw new IllegalArgumentException("Código de cooperativa válido es requerido");
    }
  }
}