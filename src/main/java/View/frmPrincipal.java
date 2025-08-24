package View;

import Controller.AsistenciaController;
import Controller.UsuarioController;
import Model.Usuario;
import Repository.ConexionDb;
import Repository.UsuarioRepository;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme;
import java.awt.Color;
import java.util.Arrays;
import javax.swing.JOptionPane;


public class frmPrincipal extends javax.swing.JFrame {


    public frmPrincipal() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        disenioBotones();
    }

    public void disenioBotones() {
        btnIngresar.putClientProperty("JButton.buttonType", "roundRect");
        btnIngresar.setForeground(Color.black);
        btnAdmi.putClientProperty("JButton.buttonType", "roundRect");
        btnAdmi.setForeground(Color.black);

    }

    public void ingresarLogin() {
        String email = txtUsuario.getText();
        char[] pass = pswPass.getPassword();

        if (email.isEmpty() || pass.length == 0) {
            JOptionPane.showMessageDialog(null, "Debe ingresar contraseña y usuario", "Error", JOptionPane.ERROR_MESSAGE);
            return; 
        }

        String contrasenia = new String(pass);
        Arrays.fill(pass, ' '); 

        try {
            // Llamar al controller de usuarios
            UsuarioController usuarioController = new UsuarioController(new UsuarioRepository(new ConexionDb()));

            // Intentar login
            Usuario usuario = usuarioController.login(email, contrasenia);

            if (usuario != null) {
                // Usuario validado
                JOptionPane.showMessageDialog(this, "Bienvenido " + usuario.getNombre());

               
                jdlAsistencia asistencia = new jdlAsistencia(this, true, usuario.getId());
                asistencia.setVisible(true);

                
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al validar usuario", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnIngresar = new javax.swing.JButton();
        txtUsuario = new javax.swing.JTextField();
        pswPass = new javax.swing.JPasswordField();
        btnAdmi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnIngresar.setFont(new java.awt.Font("Cascadia Mono", 1, 14)); // NOI18N
        btnIngresar.setForeground(new java.awt.Color(0, 0, 0));
        btnIngresar.setText("ingresar");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });
        jPanel2.add(btnIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, 330, 50));

        txtUsuario.setFont(new java.awt.Font("Cascadia Mono", 1, 12)); // NOI18N
        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });
        jPanel2.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 330, 50));

        pswPass.setFont(new java.awt.Font("Cascadia Mono", 1, 12)); // NOI18N
        pswPass.setText("jPasswordField1");
        jPanel2.add(pswPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 330, 50));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(573, 81, 460, 510));

        btnAdmi.setFont(new java.awt.Font("Cascadia Mono", 1, 14)); // NOI18N
        btnAdmi.setForeground(new java.awt.Color(0, 0, 0));
        btnAdmi.setText("administrador");
        btnAdmi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdmiActionPerformed(evt);
            }
        });
        jPanel1.add(btnAdmi, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 540, 240, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdmiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdmiActionPerformed
        // TODO add your handling code here:
        frmReportes reporte = new frmReportes();
        reporte.setVisible(true);
    }//GEN-LAST:event_btnAdmiActionPerformed

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        // TODO add your handling code here:
        ingresarLogin();
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        FlatMaterialLighterIJTheme.setup();
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdmi;
    private javax.swing.JButton btnIngresar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField pswPass;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
