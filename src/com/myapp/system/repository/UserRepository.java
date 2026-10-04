package com.myapp.system.repository;

import com.myapp.system.model.User;
import java.sql.CallableStatement;
import org.allansaz.system.config.ConexionDB;
import java.sql.SQLException;

public class UserRepository implements UserInterface {

//Atributos
    private CallableStatement callSP;
    private ConexionDB conexionDB = ConexionDB.getInstanciaConexionDB();
    
    @Override
    public void create(User user) {
        try {
            callSP = conexionDB.getConnection().prepareCall("{call sp_crear_users(?, ?, ?, ?, ?)}");
            callSP.setString(1, user.getName());
            callSP.setString(2, user.getLastname());
            callSP.setString(3, user.getEmail());
            callSP.setString(4, user.getUser());
            callSP.setString(5, user.getPassword());
            
            callSP.execute();
            callSP.close();//libera recursos en memoria
            
            
        } catch (SQLException e) {
            System.out.println("Error al Crear usuario Repository :C");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}