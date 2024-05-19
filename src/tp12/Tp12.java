/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp12;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import javax.swing.JOptionPane;

public class Tp12 {

   
    public static void main(String[] args) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String URL = "jdbc:mariadb://localhost:3306/construirsa_tp12";
            String USUARIO="root";
            String PASSWORD ="";
            Connection conn=DriverManager.getConnection(URL,USUARIO,PASSWORD);
            
           
            String sqlInsertEmpleado = "INSERT INTO empleado (id_empleado, dni, apellido, nombre, acceso, estado)"
                    + " VALUES (?, ?, ?, ?, ?, ?)";
            
            PreparedStatement pstmtInsertEmpleado = conn.prepareStatement(sqlInsertEmpleado);
            //primer empleado
            pstmtInsertEmpleado.setInt(1, 4);
            pstmtInsertEmpleado.setLong(2, 35767572L);
            pstmtInsertEmpleado.setString(3, "Cardona");
            pstmtInsertEmpleado.setString(4, "Silver Mateo");
            pstmtInsertEmpleado.setInt(5, 1);
            pstmtInsertEmpleado.setInt(6, 1);
            pstmtInsertEmpleado.executeUpdate();
            
            
            
            
            
            
        }catch(ClassNotFoundException cnf){
            JOptionPane.showMessageDialog(null, "Error al cargar driver");
        }catch(SQLException sql){
            System.out.println(sql.getErrorCode());
            if(sql.getErrorCode() == 1062){
                JOptionPane.showMessageDialog(null, "Ya exite un duplicado de ese DNI");
            }else if(sql.getErrorCode() ==1049){
                JOptionPane.showMessageDialog(null, "La base de datos ya existe");
            }else
            JOptionPane.showMessageDialog(null,"Error al cargar el db");
        }
    }
    
}