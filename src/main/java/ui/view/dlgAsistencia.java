
package ui.view;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme;
import java.awt.Color;
import javax.swing.JOptionPane;
import ui.controller.AsistenciaController;


public class dlgAsistencia extends javax.swing.JDialog {

    
    private AsistenciaController controller;
   
    private int idUsuario;
    
    public dlgAsistencia(java.awt.Frame parent, boolean modal, int idUsuario, AsistenciaController controller) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        this.idUsuario = idUsuario; // guardamos el ID
        this.controller = controller; 
    }
    

public void disenioBotones() {
        btnEntrada.putClientProperty("JButton.buttonType", "roundRect");
        btnEntrada.setForeground(Color.black);
        btnSalida.putClientProperty("JButton.buttonType", "roundRect");
        btnSalida.setForeground(Color.black);
        
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnSalida = new javax.swing.JButton();
        btnEntrada = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSalida.setBackground(new java.awt.Color(37, 99, 235));
        btnSalida.setFont(new java.awt.Font("Cascadia Mono", 1, 18)); // NOI18N
        btnSalida.setForeground(new java.awt.Color(255, 255, 255));
        btnSalida.setText("Salida");
        btnSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalidaActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 340, 330, 70));

        btnEntrada.setBackground(new java.awt.Color(37, 99, 235));
        btnEntrada.setFont(new java.awt.Font("Cascadia Mono", 1, 18)); // NOI18N
        btnEntrada.setForeground(new java.awt.Color(255, 255, 255));
        btnEntrada.setText("Ingreso");
        btnEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntradaActionPerformed(evt);
            }
        });
        jPanel1.add(btnEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 330, 70));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -4, 550, 610));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalidaActionPerformed
        // TODO add your handling code here:
        try {
            controller.registrarSalida(idUsuario);
            JOptionPane.showMessageDialog(this, "Salida Exitosa!!");
            this.dispose();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al registrar la Salida", "Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSalidaActionPerformed

    private void btnEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntradaActionPerformed
        // TODO add your handling code here:
        try {
            controller.registrarIngreso(idUsuario);
            JOptionPane.showMessageDialog(this, "Ingreso exitoso!!");
            this.dispose();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al registrar Ingreso", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEntradaActionPerformed

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
            java.util.logging.Logger.getLogger(dlgAsistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlgAsistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlgAsistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlgAsistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        FlatMaterialLighterIJTheme.setup();

        /* Create and display the dialog */
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrada;
    private javax.swing.JButton btnSalida;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
