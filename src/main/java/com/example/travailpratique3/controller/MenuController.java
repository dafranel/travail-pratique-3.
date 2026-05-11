package com.example.travailpratique3.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

/**
 * Contrôleur du menu principal.
 */
public class MenuController {

    /**
     * Zone centrale où les différentes pages sont affichées.
     */
    @FXML
    private StackPane zoneContenu;

    /**
     * Charge la page par défaut au démarrage.
     */
    @FXML
    public void initialize() {
        ouvrirBalles();
    }

    /**
     * Ouvre la page des balles rebondissantes.
     */
    @FXML
    private void ouvrirBalles() {
        chargerVue("balles.fxml");
    }

    /**
     * Ouvre la page du quiz Québec/Canada.
     */
    @FXML
    private void ouvrirQuiz() {
        chargerVue("quiz.fxml");
    }

    /**
     * Ouvre la page des règlements.
     */
    @FXML
    private void ouvrirRegles() {
        chargerVue("regles.fxml");
    }

    /**
     * Ouvre la page vidéo.
     */
    @FXML
    private void ouvrirVideo() {
        chargerVue("video.fxml");
    }

    /**
     * Ferme complètement l'application.
     */
    @FXML
    private void quitterApplication() {
        Platform.exit();
    }

    /**
     * Charge une vue FXML dans la zone centrale.
     *
     * @param fichier nom du fichier à charger
     */
    private void chargerVue(String fichier) {
        try {
            Node vue = FXMLLoader.load(
                    getClass().getResource("/com/example/travailpratique3/fxml/" + fichier)
            );

            zoneContenu.getChildren().setAll(vue);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}