package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppProperties {

    private Properties properties;

    public AppProperties() {
        properties = new Properties();
        // Leer desde el classpath
        try (InputStream input = AppProperties.class.getClassLoader()
                .getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new RuntimeException(
                        "No se encontró application.properties en src/main/resources");
            }
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getJornadaInicio() {
        return properties.getProperty("jornada.inicio");
    }

    public String getJornadaFin() {
        return properties.getProperty("jornada.fin");
    }

    public String getDbUrl() {
        return properties.getProperty("db.url");
    }

    public String getDbUser() {
        return properties.getProperty("db.user");
    }

    public String getDbPass() {
        return properties.getProperty("db.pass");
    }
}
