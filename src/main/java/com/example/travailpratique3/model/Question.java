package com.example.travailpratique3.model;

/**
 * Représente une question du quiz Québec/Canada.
 */
public class Question {

    private final String texte;
    private final String[] choix;
    private final int bonneReponse;

    public Question(String texte, String[] choix, int bonneReponse) {
        this.texte = texte;
        this.choix = choix;
        this.bonneReponse = bonneReponse;
    }

    public String getTexte() {
        return texte;
    }

    public String[] getChoix() {
        return choix;
    }

    public int getBonneReponse() {
        return bonneReponse;
    }
}