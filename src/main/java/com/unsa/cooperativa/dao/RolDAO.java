package com.unsa.cooperativa.dao;

import java.sql.SQLException;
import java.util.List;

import com.unsa.cooperativa.entity.Rol;

public interface RolDAO {
    void create(Rol rol) throws SQLException;

    Rol read(int rolCod) throws SQLException;

    void update(Rol rol) throws SQLException;

    void delete(int rolCod) throws SQLException;

    List<Rol> getAll() throws SQLException;
}