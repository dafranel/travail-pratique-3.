package com.example.travailpratique3.controller;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Classe utilitaire permettant de gérer les sons
 * et les musiques de fond de l'application.
 *
 * Cette classe centralise :
 * - les effets sonores;
 * - la musique de fond;
 * - l'arrêt de la musique;
 * - la lecture des fichiers audio.
 *
 * Les méthodes sont statiques afin d’être utilisées
 * facilement dans tous les contrôleurs du projet.
 *
 * Exemple :
 * Audio.jouerSon("bonne.mp3");
 * Audio.jouerMusiqueFond("quiz.mp3");
 */
public class Audio {

    /**
     * Lecteur utilisé pour la musique de fond.
     *
     * Cette variable est statique afin qu’une seule
     * musique puisse jouer dans toute l’application.
     */
    private static MediaPlayer musiqueFond;

    /**
     * Constructeur privé empêchant l’instanciation
     * de la classe utilitaire Audio.
     *
     * Cette classe contient uniquement des méthodes statiques.
     */
    private Audio() {
    }

    /**
     * Joue un effet sonore court.
     *
     * Cette méthode est utilisée pour :
     * - les bonnes réponses;
     * - les mauvaises réponses;
     * - les collisions;
     * - le survol des boutons;
     * - les rebonds.
     *
     * @param nomFichier nom du fichier audio à jouer
     */
    public static void jouerSon(String nomFichier) {

        try {

            /*
             * Création d’un objet AudioClip
             * permettant la lecture rapide
             * d’un petit effet sonore.
             */
            AudioClip son = new AudioClip(

                    /*
                     * Récupération du fichier audio
                     * dans le dossier resources/audio.
                     */
                    Audio.class.getResource(

                            "/com/example/travailpratique3/audio/"
                                    + nomFichier

                    ).toExternalForm()
            );

            /*
             * Lecture immédiate du son.
             */
            son.play();

        } catch (Exception e) {

            /*
             * Message affiché si le fichier
             * audio est introuvable ou invalide.
             */
            System.out.println(
                    "Erreur audio : " + nomFichier
            );
        }
    }

    /**
     * Joue une musique de fond en boucle.
     *
     * Cette méthode est utilisée principalement
     * pour la musique du quiz.
     *
     * La musique précédente est arrêtée automatiquement
     * avant d’en démarrer une nouvelle.
     *
     * @param nomFichier nom du fichier audio
     */
    public static void jouerMusiqueFond(String nomFichier) {

        try {

            /*
             * Arrête la musique précédente
             * avant d’en démarrer une nouvelle.
             */
            arreterMusiqueFond();

            /*
             * Création du média audio.
             */
            Media media = new Media(

                    Audio.class.getResource(

                            "/com/example/travailpratique3/audio/"
                                    + nomFichier

                    ).toExternalForm()
            );

            /*
             * Création du lecteur multimédia.
             */
            musiqueFond = new MediaPlayer(media);

            /*
             * Réglage du volume.
             *
             * 0 = aucun son
             * 1 = volume maximum
             */
            musiqueFond.setVolume(0.25);

            /*
             * Lecture infinie de la musique.
             */
            musiqueFond.setCycleCount(
                    MediaPlayer.INDEFINITE
            );

            /*
             * Démarrage de la musique.
             */
            musiqueFond.play();

        } catch (Exception e) {

            /*
             * Message affiché si la musique
             * ne peut pas être chargée.
             */
            System.out.println(
                    "Erreur musique : "
                            + nomFichier
            );
        }
    }

    /**
     * Arrête complètement la musique de fond.
     *
     * Cette méthode :
     * - arrête la lecture;
     * - libère la mémoire;
     * - remet le lecteur à null.
     */
    public static void arreterMusiqueFond() {

        /*
         * Vérifie si une musique existe.
         */
        if (musiqueFond != null) {

            /*
             * Arrêt de la lecture.
             */
            musiqueFond.stop();

            /*
             * Libération des ressources mémoire.
             */
            musiqueFond.dispose();

            /*
             * Réinitialisation du lecteur.
             */
            musiqueFond = null;
        }
    }
}