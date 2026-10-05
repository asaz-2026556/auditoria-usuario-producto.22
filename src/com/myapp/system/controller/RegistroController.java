package com.myapp.system.controller;
import com.myapp.main.App;
import com.myapp.system.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistroController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtUsuario;
    @FXML private PasswordField pwdContrasena;
    @FXML private TextField txtEmail;

    @FXML
    private void onGuardar() {
        if (txtNombre.getText().isBlank() || txtUsuario.getText().isBlank()
                || pwdContrasena.getText().isBlank() || txtEmail.getText().isBlank()) {
            Alert error = new Alert(Alert.AlertType.WARNING);
            error.setHeaderText(null);
            error.setContentText("Debes llenar todos los campos");
            error.showAndWait();
            return;
        }

        User nuevo = new User();
        nuevo.setName(txtNombre.getText());
        nuevo.setUser(txtUsuario.getText());
        nuevo.setPassword(pwdContrasena.getText());
        nuevo.setEmail(txtEmail.getText());

        Alert exito = new Alert(Alert.AlertType.INFORMATION);
        exito.setTitle("Registro");
        exito.setHeaderText(null);
        exito.setContentText("Usuario " + nuevo.getUser() + " registrado con éxito");
        exito.showAndWait();

        App.cambiarVista("LoginView.fxml", "Iniciar Sesión");
    }

    @FXML
    private void onCancelar() {
        App.cambiarVista("LoginView.fxml", "Iniciar Sesión");
    }
}