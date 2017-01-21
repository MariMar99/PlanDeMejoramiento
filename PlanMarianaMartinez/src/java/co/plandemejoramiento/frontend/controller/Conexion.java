/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.plandemejoramiento.frontend.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

private static Connection conn;

private Conexion(){
    String driver="com.mysql.jdbc.Driver";
    String url="jdbc:mysql://localhost:3306/planconcesionario";
    String user="root";
    String password="";
try{
    Class.forName(driver).newInstance();
    conn=DriverManager.getConnection(url, user, password);
} catch (Exception e){
    e.printStackTrace();
}
}

public static Connection getInstance(){
    if (conn==null){
    new Conexion();
    }
    return conn;
}

public static void cerrarConexion(){
    try {
        conn.close();
        } catch (SQLException e) {
        e.printStackTrace();
        }
    }

public static void main(String[] args) {
    System.out.println(Conexion.getInstance());
}

}
