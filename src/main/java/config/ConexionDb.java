package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDb {

    private final String url;
    private final String user;
    private final String pass;

    public ConexionDb(AppProperties appProperties) {
        this.url = appProperties.getDbUrl();
        this.user = appProperties.getDbUser();
        this.pass = appProperties.getDbPass();
    }

    public Connection getConnection() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexión a base de datos OK");
            return conn;
        } catch (SQLException e) {
            System.err.println("No se pudo conectar a la base de datos: " + e.getMessage());
            throw e;
        }
    }
}
