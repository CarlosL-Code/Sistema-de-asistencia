
package View;

import Controller.AsistenciaController;
import Model.Asistencia;
import Repository.AsistenciaRepository;
import Repository.ConexionDb;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme;
import java.util.Date;
import java.time.ZoneId;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class frmReportes extends javax.swing.JFrame {

    AsistenciaController asistencia = new AsistenciaController(new AsistenciaRepository(new ConexionDb()));

    
    public frmReportes() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);

    }

    // Método para llenar la JTable
    private void mostrarEnTabla(List<Asistencia> lista) {
        DefaultTableModel model = (DefaultTableModel) tblReportes.getModel();
        model.setRowCount(0); // Limpiar la tabla antes de agregar filas
        if (lista != null) {
            for (Asistencia a : lista) {
                model.addRow(new Object[]{
                    a.getUsuario().getNombre(),
                    a.getFecha(),
                    a.getHora(),
                    a.getTipo()
                });
            }
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnAtrasos = new javax.swing.JButton();
        btnSalidasAnticipadas = new javax.swing.JButton();
        btnInasistencias = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnGestionUsuarios = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblReportes = new javax.swing.JTable();
        btnSalir = new javax.swing.JButton();
        calendarioHasta = new com.toedter.calendar.JDateChooser();
        calendarioDesde = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAtrasos.setFont(new java.awt.Font("Cascadia Mono", 1, 12)); // NOI18N
        btnAtrasos.setForeground(new java.awt.Color(0, 0, 0));
        btnAtrasos.setText("atrasos");
        btnAtrasos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasosActionPerformed(evt);
            }
        });
        jPanel2.add(btnAtrasos, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 190, 40));

        btnSalidasAnticipadas.setFont(new java.awt.Font("Cascadia Mono", 1, 12)); // NOI18N
        btnSalidasAnticipadas.setForeground(new java.awt.Color(0, 0, 0));
        btnSalidasAnticipadas.setText("salidas anticipadas");
        btnSalidasAnticipadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalidasAnticipadasActionPerformed(evt);
            }
        });
        jPanel2.add(btnSalidasAnticipadas, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, 180, 40));

        btnInasistencias.setFont(new java.awt.Font("Cascadia Mono", 1, 12)); // NOI18N
        btnInasistencias.setForeground(new java.awt.Color(0, 0, 0));
        btnInasistencias.setText("inasistencias");
        btnInasistencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInasistenciasActionPerformed(evt);
            }
        });
        jPanel2.add(btnInasistencias, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 40, 180, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 30, 740, 120));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnGestionUsuarios.setFont(new java.awt.Font("Cascadia Mono", 1, 14)); // NOI18N
        btnGestionUsuarios.setForeground(new java.awt.Color(0, 0, 0));
        btnGestionUsuarios.setText("Gestión de Usuarios");
        jPanel3.add(btnGestionUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 36, 210, 50));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(837, 30, 250, 120));

        tblReportes.setFont(new java.awt.Font("Cascadia Mono", 0, 14)); // NOI18N
        tblReportes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "NOMBRE", "FECHA", "HORA", "TIPO DE EVENTO"
            }
        ));
        tblReportes.setMinimumSize(null);
        jScrollPane1.setViewportView(tblReportes);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 181, 1060, 410));

        btnSalir.setFont(new java.awt.Font("Cascadia Mono", 1, 12)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(0, 0, 0));
        btnSalir.setText("salir");
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 600, 160, 50));

        calendarioHasta.setFont(new java.awt.Font("Cascadia Mono", 1, 12)); // NOI18N
        jPanel1.add(calendarioHasta, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 154, 190, -1));

        calendarioDesde.setFont(new java.awt.Font("Cascadia Mono", 1, 12)); // NOI18N
        jPanel1.add(calendarioDesde, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 154, 190, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1120, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAtrasosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasosActionPerformed
        // TODO add your handling code here:
        // Obtener fechas desde los JDateChooser
        Date desde = calendarioDesde.getDate();
        Date hasta = calendarioHasta.getDate();

        if (desde == null || hasta == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar ambas fechas");
            return;
        }

        // Convertir Date a LocalDate
        LocalDate fechaDesde = desde.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fechaHasta = hasta.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Llamar al controller para obtener atrasos
        List<Asistencia> atrasos = asistencia.obtenerAtrasos(fechaDesde, fechaHasta);

        // Mostrar los resultados en la JTable
        mostrarEnTabla(atrasos);
    }//GEN-LAST:event_btnAtrasosActionPerformed

    private void btnSalidasAnticipadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalidasAnticipadasActionPerformed
        // TODO add your handling code here:
        Date desde = calendarioDesde.getDate();
        Date hasta = calendarioHasta.getDate();
        
        if(desde == null || hasta == null){
            JOptionPane.showMessageDialog(this, "Debes seleccionar ambas fechas");
            return;
        }
        
         // Convertir Date a LocalDate
        LocalDate fechaDesde = desde.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fechaHasta = hasta.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
        
        // Llamar al controller para obtener atrasos
        List<Asistencia> anticipadas = asistencia.obtenerSalidasAnticipadas(fechaDesde, fechaHasta);

        // Mostrar los resultados en la JTable
        mostrarEnTabla(anticipadas);
        
    }//GEN-LAST:event_btnSalidasAnticipadasActionPerformed

    private void btnInasistenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInasistenciasActionPerformed
        // TODO add your handling code here:
         Date desde = calendarioDesde.getDate();
        Date hasta = calendarioHasta.getDate();
        
        if(desde == null || hasta == null){
            JOptionPane.showMessageDialog(this, "Debes seleccionar ambas fechas");
            return;
        }
        
         // Convertir Date a LocalDate
        LocalDate fechaDesde = desde.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fechaHasta = hasta.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
        List<Asistencia> inasistencia = asistencia.obtenerInasistencias(fechaDesde, fechaHasta);
        
        mostrarEnTabla(inasistencia);
    }//GEN-LAST:event_btnInasistenciasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmReportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmReportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmReportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmReportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        FlatMaterialLighterIJTheme.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmReportes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtrasos;
    private javax.swing.JButton btnGestionUsuarios;
    private javax.swing.JButton btnInasistencias;
    private javax.swing.JButton btnSalidasAnticipadas;
    private javax.swing.JButton btnSalir;
    private com.toedter.calendar.JDateChooser calendarioDesde;
    private com.toedter.calendar.JDateChooser calendarioHasta;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblReportes;
    // End of variables declaration//GEN-END:variables
}
