/********************************************************************************************
 * NOM : Chendjou Talla
 * PRÉNOM : Dany Frank Nelson
 * COURS : 420-45P-SI – Programmation d’environnements graphiques
 * TRAVAIL PRATIQUE : TP3 – Jeu éducatif JavaFX
 * ENSEIGNANT : François
 * DATE DE REMISE : Dimanche 17 mai 2026
 *
 * BUT DU PROGRAMME :
 * Cette classe contrôle le menu principal de l’application.
 * Elle permet de naviguer entre les pages du projet et de quitter l’application.
 ********************************************************************************************/

package com.example.travailpratique3.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

/**
 * Contrôleur du menu principal.
 *
 * Cette classe gère les boutons du menu et charge les différentes vues
 * dans la zone centrale de l’application.
 */
public class MenuController {

    /**
     * Bouton qui ouvre la page du jeu des balles rebondissantes.
     */
    @FXML
    private Button btnBalles;

    /**
     * Bouton qui ouvre la page du quiz Québec/Canada.
     */
    @FXML
    private Button btnQuiz;

    /**
     * Bouton qui ouvre la page des règlements.
     */
    @FXML
    private Button btnRegles;

    /**
     * Bouton qui ferme l’application.
     */
    @FXML
    private Button btnQuitter;

    /**
     * Zone centrale où les pages sont affichées.
     */
    @FXML
    private StackPane zoneContenu;

    /**
     * Méthode appelée automatiquement au chargement du fichier FXML.
     *
     * Elle ajoute les sons au survol des boutons et affiche la page
     * des balles rebondissantes par défaut.
     */
    @FXML
    public void initialize() {
        // Ajoute un son lorsque la souris passe sur les boutons du menu.
        ajouterSonsSurvol();

        // Affiche la première page au démarrage de l’application.
        ouvrirBalles();
    }

    /**
     * Ouvre la page du jeu des balles rebondissantes.
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
     * Ouvre la page des règlements des jeux.
     */
    @FXML
    private void ouvrirRegles() {
        chargerVue("regles.fxml");
    }

    /**
     * Ferme complètement l’application.
     */
    @FXML
    private void quitterApplication() {
        Platform.exit();
    }

    /**
     * Ajoute un son au survol des boutons du menu.
     *
     * Cela rend l’interface plus interactive pour l’utilisateur.
     */
    private void ajouterSonsSurvol() {
        Button[] boutons = {btnBalles, btnQuiz, btnRegles, btnQuitter};

        for (Button bouton : boutons) {
            // Sécurité : évite une erreur si un bouton n’est pas lié au FXML.
            if (bouton != null) {
                bouton.setOnMouseEntered(event -> Audio.jouerSon("survol.mp3"));
            }
        }
    }

    /**
     * Charge une vue FXML dans la zone centrale.
     *
     * Avant de changer de page, la musique de fond est arrêtée afin
     * qu’elle ne continue pas à jouer lorsqu’on quitte le quiz.
     *
     * @param fichier nom du fichier FXML à charger
     */
    private void chargerVue(String fichier) {
        try {
            // Arrête la musique de fond avant de changer d’écran.
            Audio.arreterMusiqueFond();

            // Charge la page demandée depuis le dossier fxml.
            Node vue = FXMLLoader.load(
                    getClass().getResource(
                            "/com/example/travailpratique3/fxml/" + fichier
                    )
            );

            // Remplace l’ancienne page par la nouvelle dans la zone centrale.
            zoneContenu.getChildren().setAll(vue);

        } catch (Exception e) {
            // Affiche l’erreur dans la console pour faciliter le débogage.
            e.printStackTrace();
        }
    }
}