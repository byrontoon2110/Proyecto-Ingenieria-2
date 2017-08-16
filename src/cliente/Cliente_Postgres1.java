/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.swing.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Byron
 */
public class Cliente_Postgres1 extends javax.swing.JFrame {
    //Metodo para guardar los datos en un archivo txt, al momento que la
    //base de datos falle.
    //Aplicando mensajes de alerta usando Spring MVC
    
    String barra = File.separator;
    String ubicacion= System.getProperty("user.dir")+barra+"Registro"+barra;
    
    File contenedor = new File (ubicacion);
    File []registros = contenedor.listFiles();
    
                  
        String url="jdbc:postgresql://localhost:5454/CLIENTE?autoReconnect=true&relaxAutoCommit=true";
        String usuario="postgres";
        String contraseña="123";
        Connection con=null;
        Statement stmt=null;
        ResultSet rs=null;
    /**
     * Creates new form INSER_CONS_POSTGRESQL
     */
    public Cliente_Postgres1() {
        initComponents();
        setTitle("PostgreSQL");
        consultaDB();
        setLocationRelativeTo(null);
        MostrarCombo();
        
    }
    //Se crea el metodo crear mas el uso del framework Spring
    public void Crear() throws FileNotFoundException{
        String archivo=txtid1.getText()+".registro";
        File crear_ubicacion = new File(ubicacion);
        File crear_archivo = new File (ubicacion+archivo);
        
        if(txtid1.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane,"No hay ID");
                       
        }else{
            try{
                 if(crear_archivo.exists()){
                JOptionPane.showMessageDialog(rootPane,"El Registro ya existe");
            }else{
                crear_ubicacion.mkdirs();
                Formatter crea = new Formatter(ubicacion+archivo);
                crea.format("%s\r\n%s", "Cedula="+txtid1.getText(),
                        "Detalles="+txtdetalles.getText());
                crea.close();
                 JOptionPane.showMessageDialog(rootPane,"El Registro ha sido creado");
                 jComboBox1.removeAllItems();
                 registros = contenedor.listFiles();
                 MostrarCombo();
            }
            }catch (Exception e){
                JOptionPane.showMessageDialog(rootPane,"No se pudo crear");
            }
           
        } 
        
    }
    public void Mostrar(){
        File url = new File(ubicacion+txtid1.getText()+".registro");
        if (txtid1.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Indique La Cedula A Mostrar");
        }else{
            if(url.exists()){
                try{
                    FileInputStream fis = new FileInputStream(url);
                    Properties mostrar= new Properties();
                    mostrar.load(fis);
                    
                    txtdetalles.setText(mostrar.getProperty("Detalles"));
                } catch (Exception e){
                    
                }
                
            }else{
              JOptionPane.showMessageDialog(rootPane,"El Resgistro no Existe");  
            }
        }
    }
    public void Eliminar(){
        File url = new File (ubicacion+txtid1.getText()+".registro");
        String btns []= {"Eliminar","Cancelar"}; //se crea dos botones por medio de Spring aplicando un string
        if(txtid1.getText().equals("")){
           JOptionPane.showMessageDialog(rootPane,"Indique Que Resgistro desea Eliminar");
        }else{
            if(url.exists()){
                
                try{
                    
                    FileInputStream cerrar= new FileInputStream(url);
                    cerrar.close();
                    System.gc();
                    
                    int seguro = JOptionPane.showOptionDialog(rootPane,
                            "¿Esta seguro de eliminar el registro?"+txtid1.getText(),
                            "Eliminacion",0,0,null,btns,null);
                    if(seguro == JOptionPane.YES_OPTION){
                        url.delete();
                         JOptionPane.showMessageDialog(rootPane,"El Registro ha sido Eliminado");
                         jComboBox1.removeItem(txtid1.getText());
                    }
                    if (seguro == JOptionPane.NO_OPTION){
                        
                    }                      
                      
                   
                    
                }catch (Exception e){
                    
                }
                
            }else{
                JOptionPane.showMessageDialog(rootPane,"Este registro no existe");
            }
        }
    }
    public void MostrarCombo(){
        for (int i=0;i<registros.length;i++){
            jComboBox1.addItem(registros[i].getName().replace(".registro",""));
        }
    }
    private void consultaDB(){
        try{
            con = (Connection)DriverManager.getConnection(url,usuario,contraseña);
           if(con!=null)
                System.out.println("Conectado a la base de datos");
                stmt=(Statement)con.createStatement();
                
        }catch(Exception e){
        JOptionPane.showMessageDialog(null, "Error al conectar");
    
    }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Cedula = new javax.swing.JLabel();
        Detalles = new javax.swing.JLabel();
        txtdetalles = new javax.swing.JTextField();
        btnguardar = new javax.swing.JToggleButton();
        btnconsulta = new javax.swing.JToggleButton();
        jButton1 = new javax.swing.JButton();
        btneliminar = new javax.swing.JToggleButton();
        jLabel3 = new javax.swing.JLabel();
        txtid1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 204, 204));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(0, 0, 0)));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        Cedula.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 20)); // NOI18N
        Cedula.setText("Cédula:");

        Detalles.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 20)); // NOI18N
        Detalles.setText("Detalles:");

        txtdetalles.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtdetalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdetallesActionPerformed(evt);
            }
        });

        btnguardar.setFont(new java.awt.Font("Bell MT", 0, 20)); // NOI18N
        btnguardar.setText("Guardar");
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        btnconsulta.setFont(new java.awt.Font("Bell MT", 0, 20)); // NOI18N
        btnconsulta.setText("Consultar ");
        btnconsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnconsultaActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Bell MT", 0, 20)); // NOI18N
        jButton1.setText("Menu Principal");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btneliminar.setFont(new java.awt.Font("Bell MT", 0, 20)); // NOI18N
        btneliminar.setText("Eliminar ");
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Bell MT", 0, 45)); // NOI18N
        jLabel3.setText("Registro de Cita Medica");

        txtid1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtid1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtid1ActionPerformed(evt);
            }
        });

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(310, 310, 310))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(295, 295, 295)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(138, 138, 138)
                                .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(89, 89, 89)
                                .addComponent(btnconsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Cedula)
                                    .addComponent(Detalles))
                                .addGap(159, 159, 159)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtid1, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                                    .addComponent(txtdetalles))))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtid1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Cedula)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Detalles)
                    .addComponent(txtdetalles, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnconsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );

        jLabel1.setFont(new java.awt.Font("Baskerville Old Face", 0, 50)); // NOI18N
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(11, 11, 11)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Menu btn2 = new Menu ();
        btn2.setVisible (true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnconsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnconsultaActionPerformed
        // TODO add your handling code here:
        Mostrar();
      
        try{
            rs = stmt.executeQuery("Select * from cita where CLIENTE_ID='"+Integer.parseInt(txtid1.getText())+"'");
            while (rs.next()){
                txtdetalles.setText(rs.getString("detalles"));
                
            }

        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR AL CONSULTAR");

        }
    }//GEN-LAST:event_btnconsultaActionPerformed


    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        try {
            // TODO add your handling code here:
            Crear();
            //ConexionPostgresql c=new ConexionPostgresql().conectar();
            
            // c.ejecutar("insert into cita values('"+Integer.parseInt(txtid1.getText())
            //   +"','"+txtdetalles.getText()+"')");
            
            // JOptionPane.showMessageDialog(null, "La cita a sido asignada ");
            //Borrar Campos//
            // txtid1.setText("");
            //txtdetalles.setText(""); //
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Cliente_Postgres1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        // TODO add your handling code here:
        Eliminar();
               try{
            rs = stmt.executeQuery("delete from cita where CLIENTE_ID='"+Integer.parseInt(txtid1.getText())+"'");
            }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "La cita a sido eliminada");

        }
    }//GEN-LAST:event_btneliminarActionPerformed

    private void txtdetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdetallesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdetallesActionPerformed

    private void txtid1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtid1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtid1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        String copiar = (String) jComboBox1.getSelectedItem();
        txtid1.setText(copiar);
        Mostrar();
    }//GEN-LAST:event_jComboBox1ActionPerformed

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
            java.util.logging.Logger.getLogger(Cliente_Postgres1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente_Postgres1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente_Postgres1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente_Postgres1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cliente_Postgres1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Cedula;
    private javax.swing.JLabel Detalles;
    private javax.swing.JToggleButton btnconsulta;
    private javax.swing.JToggleButton btneliminar;
    private javax.swing.JToggleButton btnguardar;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtdetalles;
    private javax.swing.JTextField txtid1;
    // End of variables declaration//GEN-END:variables
}
