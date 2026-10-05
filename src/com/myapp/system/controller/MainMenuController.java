package com.myapp.controller;

import com.myapp.main.App;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class MainMenuController {

    @FXML private Label lblUsuario;
    @FXML private Label lblContenido;
    @FXML private StackPane contenedorCentral;

    @FXML
    public void initialize() {
        if (lblUsuario != null) {
            lblUsuario.setText(App.usuarioActual);
        }
        mostrarTexto("Bienvenido, " + (App.usuarioActual != null ? App.usuarioActual : "Usuario"));
    }

    @FXML
    private void onInicio() {
        mostrarTexto("Pantalla de Inicio");
    }

    @FXML
    private void onPerfil() {
        mostrarTexto("Perfil del Usuario: " + App.usuarioActual);
    }

    @FXML
    private void onConfiguracion() {
        mostrarTexto("Ajustes y Configuración del Sistema");
    }

    // Carga la pantalla de ventas dentro del área central
    @FXML
    private void onVentas() {
        try {
            Parent vista = FXMLLoader.load(getClass().getResource("/com/myapp/view/VentaView.fxml"));
            contenedorCentral.getChildren().setAll(vista);
        } catch (IOException e) {
            System.err.println("Error al cargar VentaView.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void onCerrarSesion() {
        App.usuarioActual = "";
        App.cambiarVista("LoginView.fxml", "Iniciar Sesión");
    }

    // Método corregido para renderizar dinámicamente el texto en el centro
    private void mostrarTexto(String texto) {
        Label nuevoTexto = new Label(texto);
        nuevoTexto.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #333333;");
        contenedorCentral.getChildren().setAll(nuevoTexto);
    }
}