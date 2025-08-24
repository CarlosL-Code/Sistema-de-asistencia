package Repository;

import Model.Asistencia;
import Model.Usuario;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AsistenciaRepository {

    
    private ConexionDb con;

    public AsistenciaRepository(ConexionDb con) {
        this.con = con;
    }

    // Registrar entrada o salida
    public void registrarEntradaSalida(int idUsuario, String accion) throws SQLException {
        String sql = "INSERT INTO registro_asistencia (ID_usuario, accion, ts) VALUES (?,?,now())";
        try (Connection conn = con.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ps.setString(2, accion);
            ps.executeUpdate();
        }
    }
    
    

    // Obtener reporte de atrasos, salidas anticipadas e inasistencias
    public List<Asistencia> obtenerReporte(LocalDate fechaDesde, LocalDate fechaHasta) throws SQLException {
        
        //Esto es una consulta unificada
        String sql
                = "WITH RECURSIVE calendario AS ( "
                + "    SELECT DATE(?) AS dia "
                + "    UNION ALL "
                + "    SELECT DATE_ADD(dia, INTERVAL 1 DAY) "
                + "    FROM calendario "
                + "    WHERE dia < DATE(?) "
                + ") "
                + "SELECT r.nombre, r.fecha, r.hora, r.tipo "
                + "FROM ( "
                + "    SELECT u.nombre AS nombre, DATE(r.ts) AS fecha, TIME(r.ts) AS hora, 'ATRASO' AS tipo "
                + "    FROM registro_asistencia r "
                + "    JOIN usuario u ON u.ID_usuario = r.ID_usuario "
                + "    WHERE r.accion = 'ENTRADA' AND TIME(r.ts) > '09:30:00' "
                + "      AND DATE(r.ts) BETWEEN ? AND ? AND u.tipoDeUsuario = 'USER' "
                + "    UNION ALL "
                + "    SELECT u.nombre AS nombre, DATE(r.ts) AS fecha, TIME(r.ts) AS hora, 'SALIDA ANTICIPADA' AS tipo "
                + "    FROM registro_asistencia r "
                + "    JOIN usuario u ON u.ID_usuario = r.ID_usuario "
                + "    WHERE r.accion = 'SALIDA' AND TIME(r.ts) < '17:30:00' "
                + "      AND DATE(r.ts) BETWEEN ? AND ? AND u.tipoDeUsuario = 'USER' "
                + "    UNION ALL "
                + "    SELECT u.nombre AS nombre, c.dia AS fecha, NULL AS hora, 'INASISTENCIA' AS tipo "
                + "    FROM usuario u "
                + "    CROSS JOIN calendario c "
                + "    LEFT JOIN registro_asistencia r ON r.ID_usuario = u.ID_usuario AND DATE(r.ts) = c.dia "
                + "    WHERE r.ID_usuario IS NULL AND u.tipoDeUsuario = 'USER' "
                + ") AS r "
                + "ORDER BY r.fecha, r.nombre, r.tipo;";
        

        try (Connection conn = con.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, fechaDesde.toString());
            ps.setString(2, fechaHasta.toString());
            ps.setString(3, fechaDesde.toString());
            ps.setString(4, fechaHasta.toString());
            ps.setString(5, fechaDesde.toString());
            ps.setString(6, fechaHasta.toString());

            ResultSet rs = ps.executeQuery();
            List<Asistencia> lista = new ArrayList<>();

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                LocalDate fecha = rs.getDate("fecha").toLocalDate();
                Time t = rs.getTime("hora");
                LocalTime hora = (t != null) ? t.toLocalTime() : null;
                String tipo = rs.getString("tipo");

                Usuario usuario = new Usuario();
                usuario.setNombre(nombre);

                Asistencia asistencia = new Asistencia(usuario, fecha, hora, tipo);
                lista.add(asistencia);
            }

            return lista;
        }
    }
    
}
