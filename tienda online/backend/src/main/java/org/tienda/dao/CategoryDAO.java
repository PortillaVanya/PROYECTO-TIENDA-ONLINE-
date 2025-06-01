package org.tienda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.tienda.config.DBConnection;
import org.tienda.model.Category;

public class CategoryDAO {

    private static final Logger logger = Logger.getLogger(CategoryDAO.class.getName());
    private final Connection conn = DBConnection.getConnection();

    public List<Category> getAll() {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM categorias";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Category c = new Category();
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                list.add(c);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al obtener categorías", e);
        }

        return list;
    }
}
