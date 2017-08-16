/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Byron Pin
 */
public class ConexionOracle {
    private Connection conexionBD;
    public Connection getConexion() {
        return conexionBD;
    }       
    public void setConexion(Connection conexionBD) {
        this.conexionBD = conexionBD;
    }
    public ConexionOracle conectar() {
        try {
          Class.forName("oracle.jdbc.OracleDriver");// carga el driver y oracle 

         String BaseDeDatos = "jdbc:oracle:thin:@localhost:1521:XE"; //crea una variable con la direccion el puerto y la instancia (express)
         conexionBD = DriverManager.getConnection(BaseDeDatos, "system","123456");  // carga la conexion (usuario contraseña)

         if (conexionBD != null) {
             JOptionPane.showMessageDialog(null, "Conectado a la base de datos oracle 12g!");
         } else {
             JOptionPane.showMessageDialog(null, "Error en la Conexión !");
         }
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e.getMessage()+"aqui es");
        }
        return this;
    }
    
    public boolean ejecutar(String sql) { //
        try {
            Statement sentencia; // objetos para sentencias de oracle 
            sentencia = getConexion().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY); // crea l0s parametros de embio y r
            sentencia.executeUpdate(sql); //ejecuta el insert delete y el updte 
            getConexion().commit();
            sentencia.close();
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }        return true;
    }
}
