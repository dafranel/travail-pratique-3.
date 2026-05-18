/********************************************************************************************
 * NOM : Chendjou Talla
 * PRÉNOM : Dany Frank Nelson
 * COURS : 420-45P-SI – Programmation d’environnements graphiques
 * TRAVAIL PRATIQUE : TP3 – Jeu éducatif JavaFX
 * ENSEIGNANT : François
 * DATE DE REMISE : Dimanche 17 mai 2026
 *
 * BUT DU PROGRAMME :
 * Cette classe représente le point d’entrée principal de l’application JavaFX.
 *
 * Elle permet :
 * - de charger le menu principal;
 * - d’appliquer le thème CSS;
 * - de configurer la fenêtre principale;
 * - de démarrer l’application.
 *
 * PATRON DE CONCEPTION UTILISÉ :
 * Singleton
 *
 * CATÉGORIE :
 * Patron de création
 *
 * Le patron de conception utilisé dans mon jeu de quiz est le Singleton.
 * Il s’agit d’un patron de création, car il contrôle la création d’un objet unique.
 *
 * Dans mon projet, le Singleton est utilisé dans la classe GestionnaireTheme.
 * Cette classe permet de gérer le changement entre le thème clair et le thème sombre du quiz.
 * Dans le QuizController, j’appelle GestionnaireTheme.getInstance().changerTheme(stage.getScene())
 * pour appliquer le thème choisi à la scène.
 *
 * L’avantage de ce patron est qu’il évite de créer plusieurs objets responsables du même travail.
 * Il centralise la gestion des thèmes, réduit la répétition du code et rend l’application plus facile à maintenir.
 ********************************************************************************************************************/

package com.example.travailpratique3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe principale de l'application JavaFX.
 *
 * Cette classe démarre le programme et affiche
 * la fenêtre principale contenant le menu du projet.
 */
public class HelloApplication extends Application {

    /**
     * Méthode appelée automatiquement au démarrage de l’application.
     *
     * Elle charge le fichier FXML principal, applique le thème CSS
     * et configure la fenêtre JavaFX.
     *
     * @param stage fenêtre principale de l’application
     * @throws Exception exception possible lors du chargement du FXML
     */
    @Override
    public void start(Stage stage) throws Exception {

        // Charge le fichier FXML du menu principal.
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/com/example/travailpratique3/fxml/menu.fxml"
                )
        );

        // Création de la scène principale.
        Scene scene = new Scene(loader.load(), 1000, 650);

        // Application du thème clair par défaut.
        scene.getStylesheets().add(
                getClass()
                        .getResource(
                                "/com/example/travailpratique3/css/theme-clair.css"
                        )
                        .toExternalForm()
        );

        // Titre affiché dans la fenêtre.
        stage.setTitle("Travail Pratique 3 - JavaFX");

        // Dimensions de la fenêtre.
        stage.setWidth(1000);
        stage.setHeight(650);

        // Empêche le redimensionnement de la fenêtre.
        stage.setResizable(false);

        // Ajoute la scène à la fenêtre.
        stage.setScene(scene);

        // Affiche la fenêtre à l’écran.
        stage.show();
    }

    /**
     * Point d’entrée principal du programme.
     *
     * @param args arguments du programme
     */
    public static void main(String[] args) {

        // Lance l’application JavaFX.
        launch();
    }
}