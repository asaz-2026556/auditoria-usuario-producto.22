package com.myapp.system.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Singleton: solo existe una instancia de esta clase
public class ConexionDB {

    private static ConexionDB instancia;

    private ConexionDB() {
    }

    public static ConexionDB getInstancia() {
        if (instancia == null) {
            instancia = new ConexionDB();
        }
        return instancia;
    }

    public Connection getConexion() throws SQLException {
        String url = "jdbc:mysql://" + Enviroment.LOCATION_SERVICE + "/" + Enviroment.DATA_BASE
                + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        return DriverManager.getConnection(url, Enviroment.USER, Enviroment.PASSWORD);
    }
}