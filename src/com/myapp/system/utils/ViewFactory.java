package com.myapp.system.utils;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import com.myapp.system.ClasePrincipal;

public class ViewFactory {

//Atributos
    private final String PATH_VIEWS = "/org/allansaz/system/view/";

//Metodos
    public Scene loadFileFXML(String nameFile, int width, int height) {
        String pathOfFile = PATH_VIEWS + nameFile;

        try {
            //Llamar al FXMLLoader
            FXMLLoader loadFXML = new FXMLLoader();
            //Obtener la URL del Archivo, viene de la Clase Main
            URL urlFile = ClasePrincipal.class.getResource(pathOfFile);
            loadFXML.setBuilderFactory(new JavaFXBuilderFactory());
            loadFXML.setLocation(urlFile);

            return new Scene(loadFXML.load(), width, height);

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void loadScene(String nameFile) {
        Scene scene = null;
        try {
            switch (nameFile) {
                case "login" ->
                    scene = loadFileFXML("LoginView.fxml", 400, 500);
                case "register" -> {
                    SceneManager.getInstanciaSceneManger().getStagePrincipal().setTitle("Registro de Usuarios");
                    SceneManager.getInstanciaSceneManger().getStagePrincipal().setResizable(false);
                    scene = loadFileFXML("RegisterView.fxml", 350, 400);
                }

                default ->
                    scene = loadFileFXML("LoginView.fxml", 400, 500);
            }
            SceneManager.getInstanciaSceneManger().changeScene(scene);
        } catch (NullPointerException e) {
            System.out.println("Error load Scene");
            //Alert
        }
    }

    public void viewRegister() {
        loadScene("register");
    }

    public void viewLogin() {
        loadScene("login");
    }

}
