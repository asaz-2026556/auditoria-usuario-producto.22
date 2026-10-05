package com.myapp.system.repository;

import com.myapp.system.config.ConexionDB;
import com.myapp.system.model.User;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class UserRepository {

    public void create(User user) {
        try (Connection con = ConexionDB.getInstancia().getConexion();
                CallableStatement callSP = con.prepareCall("{call sp_crear_users(?, ?, ?, ?, ?)}")) {
            callSP.setString(1, user.getName());
            callSP.setString(2, user.getLastname());
            callSP.setString(3, user.getEmail());
            callSP.setString(4, user.getUser());
            callSP.setString(5, user.getPassword());
            callSP.execute();
        } catch (SQLException e) {
            System.out.println("Error al crear usuario: " + e.getMessage());
        }
    }
}