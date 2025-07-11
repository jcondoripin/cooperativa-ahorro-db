package com.unsa.cooperativa.dao;

import java.sql.SQLException;
import java.util.List;

import com.unsa.cooperativa.entity.Usuario;

public interface UsuarioDAO {
    void create(Usuario usuario) throws SQLException;

    Usuario read(int usuCod) throws SQLException;

    void update(Usuario usuario) throws SQLException;

    void delete(int usuCod) throws SQLException;

    List<Usuario> getAll() throws SQLException;
}