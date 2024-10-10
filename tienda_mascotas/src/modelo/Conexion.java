package modelo;

import vista.vista;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    vista v = new vista();
    private String db="tiendamascotasjava";
    private String url="jdbc:mysql://localhost:3306/"+db;
    private String user="root";
    private String pass="0000";

    Connection conec=null;
    public  Connection Conecta(){
        try {
            conec= DriverManager.getConnection(url,user,pass);

        }catch (Exception e){
            v.imprimir("Base de datos no conectada"+ e);
            System.out.println("Base de datos no conectada"+ e);
        }
        return  conec;
    }
}

