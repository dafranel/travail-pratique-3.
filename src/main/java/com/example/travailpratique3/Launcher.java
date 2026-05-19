/********************************************************************************************
 * NOM : Chendjou Talla
 * PRÉNOM : Dany Frank Nelson
 * COURS : 420-45P-SI – Programmation d’environnements graphiques
 * TRAVAIL PRATIQUE : TP3 – Jeu éducatif JavaFX
 * ENSEIGNANT : François
 * DATE DE REMISE : Dimanche 17 mai 2026
 *
 * BUT DU PROGRAMME :
 * Cette classe sert de lanceur pour l’application JavaFX.
 *
 * Elle permet de démarrer correctement l’application
 * en appelant la classe principale HelloApplication.
 ********************************************************************************************/

package com.example.travailpratique3;

import javafx.application.Application;

/**
 * Classe servant à lancer l’application JavaFX.
 *
 * Cette classe est utile dans certains environnements
 * afin d’éviter des problèmes liés au lancement de JavaFX.
 */
public class Launcher {

    /**
     * Point d’entrée principal du programme.
     *
     * Cette méthode lance la classe HelloApplication.
     *
     * @param args arguments du programme
     */
    public static void main(String[] args) {

        // Lance l'application JavaFX.
        Application.launch(HelloApplication.class, args);
    }
}