package org.tienda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.tienda.config.DBConnection;
import org.tienda.model.User;

public class UserDAO {

    private static final Logger logger = Logger.getLogger(UserDAO.class.getName());
    private final Connection conn = DBConnection.getConnection();

    public boolean register(User user) {
        String sql = "INSERT INTO usuarios (nombre, email, password) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getNombre());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            return stmt.executeUpdate() == 1;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al registrar usuario", e);
            return false;
        }
    }

    public User login(String email, String password) {
        String sql = "SELECT * FROM usuarios WHERE email = ? AND password = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setNombre(rs.getString("nombre"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error en login", e);
        }
        return null;
    }
}
