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
            //Primer empleado
            pstmtInsertEmpleado.setInt(1, 4);
            pstmtInsertEmpleado.setLong(2, 44163996);
            pstmtInsertEmpleado.setString(3, "Cardona");
            pstmtInsertEmpleado.setString(4, "Silver Mateo");
            pstmtInsertEmpleado.setInt(5, 1);
            pstmtInsertEmpleado.setInt(6, 1);
            pstmtInsertEmpleado.executeUpdate();
            
            //Segundo empleado
            pstmtInsertEmpleado.setInt(1, 5);
            pstmtInsertEmpleado.setLong(2, 41920402);
            pstmtInsertEmpleado.setString(3, "Ferron");
            pstmtInsertEmpleado.setString(4, "Rodrigo");
            pstmtInsertEmpleado.setInt(5, 1);
            pstmtInsertEmpleado.setInt(6, 1);
            pstmtInsertEmpleado.executeUpdate();
            
            // Tercer empleado
            pstmtInsertEmpleado.setInt(1, 6);
            pstmtInsertEmpleado.setLong(2, 34567890L);
            pstmtInsertEmpleado.setString(3, "Moran");
            pstmtInsertEmpleado.setString(4, "Ramiro");
            pstmtInsertEmpleado.setInt(5, 3);
            pstmtInsertEmpleado.setInt(6, 1);
            
            
            int filas = pstmtInsertEmpleado.executeUpdate();
            if(filas>0){
                JOptionPane.showMessageDialog(null, "Trabajador agregado ");
            }
            //---------agregar 2 herramientaas
            String sqlInsertHerramienta = "INSERT INTO herramienta (nombre, descripcion, stock, estado)"
                    + " VALUES (?, ?, ?, ?)";
            
            PreparedStatement pstmtInsertHerramienta = conn.prepareStatement(sqlInsertHerramienta);
            
           pstmtInsertHerramienta.setString(1, "RotoMartillo");
            pstmtInsertHerramienta.setString(2, "electrico");
            pstmtInsertHerramienta.setInt(3, 20);
            pstmtInsertHerramienta.setInt(4, 1);
            pstmtInsertHerramienta.executeUpdate();
            
            pstmtInsertHerramienta.setString(1, "Motosierra");
            pstmtInsertHerramienta.setString(2, "combustible");
            pstmtInsertHerramienta.setInt(3, 20);
            pstmtInsertHerramienta.setInt(4, 1);
            pstmtInsertHerramienta.executeUpdate();
            
            
            //Listar las herramientas
            String sqlListarHerramientas = "SELECT * FROM herramienta WHERE stock > 10";
            Statement stmtListarHerramientas = conn.createStatement();
            ResultSet rsListarHerramientas = stmtListarHerramientas.executeQuery(sqlListarHerramientas);
            while (rsListarHerramientas.next()) {
                System.out.println(rsListarHerramientas.getString("nombre"));
            }
            
            // Dar de baja al primer empleado ingresado a la base de datos
            String sqlDarDeBajaEmpleado = "UPDATE empleado SET estado = 0 WHERE id_empleado = 1";
            Statement stmtDarDeBajaEmpleado = conn.createStatement();
            
            stmtDarDeBajaEmpleado.executeUpdate(sqlDarDeBajaEmpleado);
            
            
            
            
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