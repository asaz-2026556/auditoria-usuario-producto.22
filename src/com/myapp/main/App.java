package com.myapp.main;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static Stage stage;
    public static String usuarioActual = "";

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        cambiarVista("LoginView.fxml", "Iniciar Sesión");
    }


    public static void cambiarVista(String archivo, String titulo) {
        try {
            Parent raiz = FXMLLoader.load(App.class.getResource("/com/myapp/system/view/" + archivo));
            stage.setScene(new Scene(raiz));
            stage.setTitle(titulo);
            stage.sizeToScene();
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}