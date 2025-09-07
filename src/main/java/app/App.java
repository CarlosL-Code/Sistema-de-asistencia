package app;

import com.formdev.flatlaf.FlatLightLaf;
import config.AppProperties;
import config.ConexionDb;
import repository.AsistenciaRepository;
import repository.UsuarioRepository;
import service.AsistenciaService;
import service.IAsistenciaService;
import service.IUsuarioService;
import service.UsuarioService;
import ui.controller.AsistenciaController;
import ui.controller.UsuarioController;
import ui.view.FrmLogin;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class App {

    public static void main(String[] args) {

        // -------------------------------
        // 0. Look and Feel
        // -------------------------------
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        // -------------------------------
        // 1. Cargar AppProperties
        // -------------------------------
        AppProperties props = new AppProperties(); // ya carga automáticamente el archivo

        // -------------------------------
        // 2. Crear conexión
        // -------------------------------
        ConexionDb conexion = new ConexionDb(props);

        // -------------------------------
        // 3. Crear repositorios
        // -------------------------------
        UsuarioRepository usuarioRepo = new UsuarioRepository(conexion);
        AsistenciaRepository asistenciaRepo = new AsistenciaRepository(conexion);

        // -------------------------------
        // 4. Crear servicios
        // -------------------------------
        IUsuarioService usuarioService = new UsuarioService(usuarioRepo); // sin props
        IAsistenciaService asistenciaService = new AsistenciaService(asistenciaRepo, props); // con props

        // -------------------------------
        // 5. Crear controladores
        // -------------------------------
        UsuarioController usuarioController = new UsuarioController(usuarioService);
        AsistenciaController asistenciaController = new AsistenciaController(asistenciaService);

        // -------------------------------
        // 6. Crear vista de login
        // -------------------------------
        java.awt.EventQueue.invokeLater(() -> {
            FrmLogin login = new FrmLogin(usuarioController, asistenciaController);
            login.setVisible(true);
        });
    }
}
