package com.unsa.cooperativa.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.unsa.cooperativa.entity.Rol;

public class RolDAOImpl implements RolDAO {
    private final Connection connection;

    public RolDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Rol rol) throws SQLException {
        String sql = "INSERT INTO Rol (RolRol, RolNom, RolActivo) VALUES (?, ?, ?)";
        try (var ps = connection.prepareStatement(sql)) {
            ps.setString(1, rol.getRolRol());
            ps.setString(2, rol.getRolNom());
            ps.setString(3, rol.getRolActivo());
            ps.executeUpdate();
        }
    }

    @Override
    public Rol read(int rolCod) throws SQLException {
        String sql = "SELECT * FROM Rol WHERE RolCod = ?";
        try (var ps = connection.prepareStatement(sql)) {
            ps.setInt(1, rolCod);
            try (var rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Rol(
                        rs.getInt("RolCod"),
                        rs.getString("RolRol"),
                        rs.getString("RolNom"),
                        rs.getString("RolActivo")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public void update(Rol rol) throws SQLException {
        String sql = "UPDATE Rol SET RolRol = ?, RolNom = ?, RolActivo = ? WHERE RolCod = ?";
        try (var ps = connection.prepareStatement(sql)) {
            ps.setString(1, rol.getRolRol());
            ps.setString(2, rol.getRolNom());
            ps.setString(3, rol.getRolActivo());
            ps.setInt(4, rol.getRolCod());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(int rolCod) throws SQLException {
        String sql = "DELETE FROM Rol WHERE RolCod = ?";
        try (var ps = connection.prepareStatement(sql)) {
            ps.setInt(1, rolCod);
            ps.executeUpdate();
        }
    }

    @Override
    public List<Rol> getAll() throws SQLException {
        List<Rol> roles = new ArrayList<>();
        String sql = "SELECT * FROM Rol";
        try (var ps = connection.prepareStatement(sql); var rs = ps.executeQuery()) {
            while (rs.next()) {
                roles.add(new Rol(
                    rs.getInt("RolCod"),
                    rs.getString("RolRol"),
                    rs.getString("RolNom"),
                    rs.getString("RolActivo")
                ));
            }
        }
        return roles;
    }
}