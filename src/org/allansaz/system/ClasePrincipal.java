package org.allansaz.system;

import javafx.application.Application;
import javafx.stage.Stage;
import org.allansaz.system.utils.SceneManager;
import org.allansaz.system.utils.ViewFactory;

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
