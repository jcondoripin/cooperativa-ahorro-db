package com.unsa.cooperativa.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.unsa.cooperativa.entity.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {
    private final Connection connection;

    public UsuarioDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO Usuario (UsuIde, UsuUsu, UsuPas, UsuEmp, UsuActivo) VALUES (?, ?, ?, ?, ?)";
        try (var ps = connection.prepareStatement(sql)) {
            ps.setString(1, usuario.getUsuIde());
            ps.setString(2, usuario.getUsuUsu());
            ps.setString(3, usuario.getUsuPas());
            ps.setString(4, usuario.getUsuEmp());
            ps.setString(5, usuario.getUsuActivo());
            ps.executeUpdate();
        }
    }

    @Override
    public Usuario read(int usuCod) throws SQLException {
        String sql = "SELECT * FROM Usuario WHERE UsuCod = ?";
        try (var ps = connection.prepareStatement(sql)) {
            ps.setInt(1, usuCod);
            try (var rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                            rs.getInt("UsuCod"),
                            rs.getString("UsuIde"),
                            rs.getString("UsuUsu"),
                            rs.getString("UsuPas"),
                            rs.getString("UsuEmp"),
                            rs.getString("UsuActivo"));
                }
            }
        }
        return null;
    }

    @Override
    public void update(Usuario usuario) throws SQLException {
        String sql = "UPDATE Usuario SET UsuIde = ?, UsuUsu = ?, UsuPas = ?, UsuEmp = ?, UsuActivo = ? WHERE UsuCod = ?";
        try (var ps = connection.prepareStatement(sql)) {
            ps.setString(1, usuario.getUsuIde());
            ps.setString(2, usuario.getUsuUsu());
            ps.setString(3, usuario.getUsuPas());
            ps.setString(4, usuario.getUsuEmp());
            ps.setString(5, usuario.getUsuActivo());
            ps.setInt(6, usuario.getUsuCod());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(int usuCod) throws SQLException {
        String sql = "DELETE FROM Usuario WHERE UsuCod = ?";
        try (var ps = connection.prepareStatement(sql)) {
            ps.setInt(1, usuCod);
            ps.executeUpdate();
        }
    }

    @Override
    public List<Usuario> getAll() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuario";
        try (var ps = connection.prepareStatement(sql); var rs = ps.executeQuery()) {
            while (rs.next()) {
                usuarios.add(new Usuario(
                        rs.getInt("UsuCod"),
                        rs.getString("UsuIde"),
                        rs.getString("UsuUsu"),
                        rs.getString("UsuPas"),
                        rs.getString("UsuEmp"),
                        rs.getString("UsuActivo")));
            }
        }
        return usuarios;
    }
}