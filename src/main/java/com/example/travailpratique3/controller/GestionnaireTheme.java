package com.example.travailpratique3.controller;

import javafx.scene.Scene;

/**
 * Singleton responsable du changement de thème CSS.
 */
public class GestionnaireTheme {

    private static GestionnaireTheme instance;
    private boolean themeSombre = false;

    private GestionnaireTheme() {
    }

    public static GestionnaireTheme getInstance() {
        if (instance == null) {
            instance = new GestionnaireTheme();
        }

        return instance;
    }

    public void changerTheme(Scene scene) {
        scene.getStylesheets().clear();

        if (themeSombre) {
            scene.getStylesheets().add(
                    getClass().getResource("/com/example/travailpratique3/css/theme-clair.css")
                            .toExternalForm()
            );
            themeSombre = false;
        } else {
            scene.getStylesheets().add(
                    getClass().getResource("/com/example/travailpratique3/css/theme-sombre.css")
                            .toExternalForm()
            );
            themeSombre = true;
        }
    }
}