/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import domain.Asistencia;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author carlo
 */
public interface IAsistenciaService {

    // FILTRAR ATRASOS
    List<Asistencia> obtenerAtrasos(LocalDate desde, LocalDate hasta) throws SQLException;

    // FILTRAR INASISTENCIAS
    List<Asistencia> obtenerInasistencias(LocalDate desde, LocalDate hasta) throws SQLException;

    // OBTENER REPORTE COMPLETO
    List<Asistencia> obtenerReporte(LocalDate desde, LocalDate hasta) throws SQLException;

    // FILTRAR SALIDAS ANTICIPADAS
    List<Asistencia> obtenerSalidasAnticipadas(LocalDate desde, LocalDate hasta) throws SQLException;

    // REGISTRO DE INGRESO
    void registrarIngreso(int idUsuario) throws SQLException;

    // REGISTRO DE SALIDA
    void registrarSalida(int idUsuario) throws SQLException;
    
}
