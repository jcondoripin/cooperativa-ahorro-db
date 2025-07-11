package com.unsa.cooperativa.service;

import java.sql.SQLException;
import java.util.List;

import com.unsa.cooperativa.entity.Persona;

public interface PersonaService {
    void createPersona(Persona persona) throws SQLException;

    Persona getPersona(int perCod) throws SQLException;

    void updatePersona(Persona persona) throws SQLException;

    void deletePersona(int perCod) throws SQLException;

    List<Persona> getAllPersonas() throws SQLException;
}