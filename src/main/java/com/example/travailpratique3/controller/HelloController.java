/********************************************************************************************
 * NOM : Chendjou Talla
 * PRÉNOM : Dany Frank Nelson
 * COURS : 420-45P-SI – Programmation d’environnements graphiques
 * TRAVAIL PRATIQUE : TP3 – Jeu éducatif JavaFX
 * ENSEIGNANT : François
 * DATE DE REMISE : Dimanche 17 mai 2026
 *
 * BUT DU PROGRAMME :
 * Cette classe contrôle une interface JavaFX simple contenant
 * un texte de bienvenue et un bouton.
 *
 * Lorsque l’utilisateur clique sur le bouton,
 * le texte affiché dans le Label est modifié.
 ********************************************************************************************/

package com.example.travailpratique3.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Contrôleur principal de l’interface JavaFX.
 *
 * Cette classe permet de gérer les interactions
 * entre le fichier FXML et l’application.
 */
public class HelloController {

    /**
     * Label affichant le message de bienvenue.
     */
    @FXML
    private Label welcomeText;

    /**
     * Méthode appelée lorsque l’utilisateur clique sur le bouton.
     *
     * Le texte du Label est remplacé par un message de bienvenue.
     */
    @FXML
    protected void onHelloButtonClick() {

        // Change le texte affiché dans le Label.
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}