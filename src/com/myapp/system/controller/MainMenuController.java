/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myapp.system.controller;

import com.myapp.main.App;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainMenuController {

    @FXML private Label lblUsuario;
    @FXML private Label lblContenido;

    @FXML
    public void initialize() {
        lblUsuario.setText(App.usuarioActual);
        lblContenido.setText("Bienvenido, " + App.usuarioActual);
    }

    @FXML
    private void onInicio() {
        lblContenido.setText("Inicio");
    }

    @FXML
    private void onPerfil() {
        lblContenido.setText("Perfil");
    }

    @FXML
    private void onConfiguracion() {
        lblContenido.setText("Configuración");
    }

    @FXML
    private void onCerrarSesion() {
        App.usuarioActual = "";
        App.cambiarVista("LoginView.fxml", "Iniciar Sesión");
    }
}