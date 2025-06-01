package org.tienda.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    private static final Logger LOGGER = Logger.getLogger(DBConnection.class.getName());
    private static Connection connection = null;

    static {
        try {
            Properties props = new Properties();
            InputStream in = DBConnection.class.getClassLoader().getResourceAsStream("db.properties");

            if (in == null) {
                throw new IOException("Archivo db.properties no encontrado");
            }

            props.load(in);
            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");

            connection = DriverManager.getConnection(url, user, password);
            LOGGER.info("Conexión exitosa a la base de datos.");

        } catch (IOException | SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error al conectar con la base de datos: " + ex.getMessage(), ex);
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}


