package Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDb {

    private static final String URL = "jdbc:mysql://localhost:3306/sistemaAsistencia";
    private static final String User = "root";
    private static final String Pass = "1234";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, User, Pass);
    }
}
