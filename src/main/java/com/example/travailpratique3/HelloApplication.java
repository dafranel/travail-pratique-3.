package com.example.travailpratique3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
Patron de conception utilisé : Singleton
Catégorie : patron de création

Le Singleton est utilisé dans la classe GestionnaireTheme.
Il permet d’avoir une seule instance responsable du changement de thème CSS.

Utilité :
Il centralise la gestion des thèmes visuels de l’application.

Gains :
- code plus propre;
- maintenance plus facile;
- meilleure organisation MVC.
*/

/**
 * Classe principale de l'application JavaFX du Travail Pratique 3.
 */
public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/travailpratique3/fxml/menu.fxml")
        );

        Scene scene = new Scene(loader.load(), 1000, 650);

        scene.getStylesheets().add(
                getClass().getResource("/com/example/travailpratique3/css/theme-clair.css").toExternalForm()
        );

        stage.setTitle("Travail Pratique 3 - JavaFX");
        stage.setWidth(1000);
        stage.setHeight(650);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}