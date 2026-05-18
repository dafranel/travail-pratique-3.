/********************************************************************************************
 * NOM : Chendjou Talla
 * PRÉNOM : Dany Frank Nelson
 * COURS : 420-45P-SI – Programmation d’environnements graphiques
 * TRAVAIL PRATIQUE : TP3 – Jeu éducatif JavaFX
 * ENSEIGNANT : François
 * DATE DE REMISE : Dimanche 17 mai 2026
 *
 * BUT DU PROGRAMME :
 * Cette classe représente une question du quiz Québec/Canada.
 *
 * Chaque question contient :
 * - un énoncé;
 * - quatre choix de réponse;
 * - l’index de la bonne réponse.
 ********************************************************************************************/

package com.example.travailpratique3.model;

/**
 * Représente une question du quiz.
 *
 * Cette classe sert à stocker toutes les informations
 * nécessaires pour afficher une question et vérifier
 * la bonne réponse.
 */
public class Question {

    /**
     * Texte de la question.
     */
    private final String texte;

    /**
     * Tableau contenant les choix de réponse.
     */
    private final String[] choix;

    /**
     * Position de la bonne réponse dans le tableau.
     */
    private final int bonneReponse;

    /**
     * Constructeur de la classe Question.
     *
     * @param texte texte de la question
     * @param choix tableau contenant les choix de réponse
     * @param bonneReponse index de la bonne réponse
     */
    public Question(String texte, String[] choix, int bonneReponse) {

        this.texte = texte;
        this.choix = choix;
        this.bonneReponse = bonneReponse;
    }

    /**
     * Retourne le texte de la question.
     *
     * @return texte de la question
     */
    public String getTexte() {
        return texte;
    }

    /**
     * Retourne les choix de réponse.
     *
     * @return tableau des choix
     */
    public String[] getChoix() {
        return choix;
    }

    /**
     * Retourne l’index de la bonne réponse.
     *
     * @return index de la bonne réponse
     */
    public int getBonneReponse() {
        return bonneReponse;
    }
}