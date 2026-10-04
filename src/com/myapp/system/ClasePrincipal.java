package com.myapp.system;

import javafx.application.Application;
import javafx.stage.Stage;
import com.myapp.system.utils.SceneManager;
import com.myapp.system.utils.ViewFactory;

public class ClasePrincipal extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stageRoot) {
        SceneManager.getInstanciaSceneManger().setStagePrincipal(stageRoot);
        ViewFactory viewFacto = new ViewFactory();
        viewFacto.viewLogin();
    }

}
