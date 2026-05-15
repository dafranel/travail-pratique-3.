package com.example.travailpratique3.controller;

import javafx.scene.media.AudioClip;

/**
 * Classe utilitaire permettant de jouer les sons de l'application.
 */
public class Audio {

    /**
     * Joue un son depuis le dossier audio.
     *
     * @param nomFichier nom du fichier audio
     */
    public static void jouerSon(String nomFichier) {
        try {
            AudioClip son = new AudioClip(
                    Audio.class.getResource(
                            "/com/example/travailpratique3/audio/" + nomFichier
                    ).toExternalForm()
            );

            son.play();

        } catch (Exception e) {
            System.out.println("Erreur audio : " + nomFichier);
        }
    }
}