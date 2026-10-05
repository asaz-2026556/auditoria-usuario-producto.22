package com.myapp.system.controller;

import com.myapp.main.App;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML private TextField txtUsuario;
    @FXML private PasswordField pwdContrasena;

    @FXML
    private void onIngresar() {
        String usuario = txtUsuario.getText();
        String contrasena = pwdContrasena.getText();

        if (usuario.equals("admin") && contrasena.equals("123")) {
            App.usuarioActual = usuario;
            App.cambiarVista("MainMenuView.fxml", "Menú Principal");
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText(null);
            alerta.setContentText("Usuario o contraseña incorrectos");
            alerta.showAndWait();
        }
    }

    @FXML
    private void onCrearCuenta() {
        App.cambiarVista("RegistroView.fxml", "Crear Cuenta");
    }
}