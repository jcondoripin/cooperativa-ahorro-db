package com.unsa.cooperativa.service;

import java.sql.SQLException;
import java.util.List;

import com.unsa.cooperativa.entity.Usuario;

public interface UsuarioService {
    void createUsuario(Usuario usuario) throws SQLException;

    Usuario getUsuario(int usuCod) throws SQLException;

    void updateUsuario(Usuario usuario) throws SQLException;

    void deleteUsuario(int usuCod) throws SQLException;

    List<Usuario> getAllUsuarios() throws SQLException;
}