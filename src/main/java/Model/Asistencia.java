package Model;

import java.time.LocalDate;
import java.time.LocalTime;
import Model.Usuario;

public class Asistencia {

    private Usuario usuario;   // El usuario asociado
    private LocalDate fecha;   // Fecha de la incidencia
    private LocalTime hora;    // Hora de la incidencia (puede ser null en caso de inasistencia)
    private String tipo;       // Tipo: "ATRASO", "SALIDA ANTICIPADA" o "INASISTENCIA"

    public Asistencia() {
    }

    public Asistencia(Usuario usuario, LocalDate fecha, LocalTime hora, String tipo) {
        this.usuario = usuario;
        this.fecha = fecha;
        this.hora = hora;
        this.tipo = tipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Asistencia{" +
                "usuario=" + usuario.getNombre() +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
