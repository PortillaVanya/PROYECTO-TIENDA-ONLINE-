package org.tienda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.tienda.config.DBConnection;
import org.tienda.model.Product;

public class ProductDAO {
    private static final Logger logger = Logger.getLogger(ProductDAO.class.getName());
    private final Connection conn = DBConnection.getConnection();

    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecio(rs.getDouble("precio"));
                p.setCategoriaId(rs.getInt("categoria_id"));
                list.add(p);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al obtener productos", e);
        }

        return list;
    }
}

