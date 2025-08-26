package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDb {

    // Timeout de conexión y lectura en milisegundos
    private static final String URL = "jdbc:mysql://localhost:3306/sistemaAsistencia?connectTimeout=5000&socketTimeout=5000";
    private static final String User = "root";
    private static final String Pass = "";

    public static Connection getConnection() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(URL, User, Pass);
            System.out.println("Conexión a base de datos OK");
            return conn;
        } catch (SQLException e) {
            System.err.println("No se pudo conectar a la base de datos: " + e.getMessage());
            throw e;
        }
    }
}
