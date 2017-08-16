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
public class ConexionSqlserver {
    private Connection conexionBD;
    public Connection getConexion() {
        return conexionBD;
    }       
    public void setConexion(Connection conexionBD) {
        this.conexionBD = conexionBD;
    }
    public ConexionSqlserver conectar() {
        try {
          Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");// carga el driver y oracle 

          String BaseDeDatos = "jdbc:sqlserver://Sleider:1433;databaseName=BDD_PARRALES;integratedSecurity=true";
         
         conexionBD = DriverManager.getConnection(BaseDeDatos);  // carga la conexion (usuario contraseña)

         if (conexionBD != null) {
             JOptionPane.showMessageDialog(null, "Conectado a la base de datos SQL Server 2016!");
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
