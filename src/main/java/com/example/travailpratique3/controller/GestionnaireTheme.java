/********************************************************************************************
 * NOM : Chendjou Talla
 * PRÉNOM : Dany Frank Nelson
 * COURS : 420-45P-SI – Programmation d’environnements graphiques
 * TRAVAIL PRATIQUE : TP3 – Jeu éducatif JavaFX
 * ENSEIGNANT : François
 * DATE DE REMISE : Dimanche 17 mai 2026
 *
 * BUT DU PROGRAMME :
 * Cette classe permet de gérer les thèmes CSS de l’application.
 * Elle permet de basculer entre le thème clair et le thème sombre.
 ********************************************************************************************/

package com.example.travailpratique3.controller;

import javafx.scene.Scene;

/**
 * Gestionnaire des thèmes CSS de l'application.
 *
 * Cette classe utilise le modèle Singleton afin qu’un seul
 * gestionnaire de thème soit utilisé dans tout le programme.
 */
public class GestionnaireTheme {

    /**
     * Instance unique du gestionnaire.
     */
    private static GestionnaireTheme instance;

    /**
     * Indique si le thème sombre est actuellement actif.
     */
    private boolean themeSombre = false;

    /**
     * Constructeur privé.
     *
     * Empêche la création directe d’objets GestionnaireTheme.
     */
    private GestionnaireTheme() {
    }

    /**
     * Retourne l’unique instance du gestionnaire de thème.
     *
     * Si aucune instance n’existe encore, elle est créée.
     *
     * @return instance unique de GestionnaireTheme
     */
    public static GestionnaireTheme getInstance() {

        // Création de l'instance seulement lors du premier appel.
        if (instance == null) {
            instance = new GestionnaireTheme();
        }

        return instance;
    }

    /**
     * Change le thème CSS actuellement appliqué à la scène.
     *
     * Si le thème sombre est actif, le thème clair sera appliqué.
     * Sinon, le thème sombre sera chargé.
     *
     * @param scene scène JavaFX à modifier
     */
    public void changerTheme(Scene scene) {

        // Supprime les anciens fichiers CSS avant de charger le nouveau thème.
        scene.getStylesheets().clear();

        // Si le thème sombre est actif, on revient au thème clair.
        if (themeSombre) {

            scene.getStylesheets().add(
                    getClass()
                            .getResource(
                                    "/com/example/travailpratique3/css/theme-clair.css"
                            )
                            .toExternalForm()
            );

            themeSombre = false;

        } else {

            // Sinon, on applique le thème sombre.
            scene.getStylesheets().add(
                    getClass()
                            .getResource(
                                    "/com/example/travailpratique3/css/theme-sombre.css"
                            )
                            .toExternalForm()
            );

            themeSombre = true;
        }
    }
}