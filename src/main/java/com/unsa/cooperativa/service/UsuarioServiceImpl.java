package com.unsa.cooperativa.service;

import com.unsa.cooperativa.dao.UsuarioDAO;
import com.unsa.cooperativa.entity.Usuario;

import java.sql.SQLException;
import java.util.List;

public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioDAO usuarioDAO;

    public UsuarioServiceImpl(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    public void createUsuario(Usuario usuario) throws SQLException {
        validateUsuario(usuario);
        usuarioDAO.create(usuario);
    }

    @Override
    public Usuario getUsuario(int usuCod) throws SQLException {
        return usuarioDAO.read(usuCod);
    }

    @Override
    public void updateUsuario(Usuario usuario) throws SQLException {
        validateUsuario(usuario);
        usuarioDAO.update(usuario);
    }

    @Override
    public void deleteUsuario(int usuCod) throws SQLException {
        usuarioDAO.delete(usuCod);
    }

    @Override
    public List<Usuario> getAllUsuarios() throws SQLException {
        return usuarioDAO.getAll();
    }

    private void validateUsuario(Usuario usuario) {
        if (usuario.getUsuIde() == null || usuario.getUsuIde().isEmpty()) {
            throw new IllegalArgumentException("Usuario ID is required");
        }
        if (usuario.getUsuUsu() == null || usuario.getUsuUsu().isEmpty()) {
            throw new IllegalArgumentException("Username is required");
        }
        if (usuario.getUsuPas() == null || usuario.getUsuPas().isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }
    }
}