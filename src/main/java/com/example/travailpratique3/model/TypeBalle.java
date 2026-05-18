/********************************************************************************************
 * NOM : Chendjou Talla
 * PRÉNOM : Dany Frank Nelson
 * COURS : 420-45P-SI – Programmation d’environnements graphiques
 * TRAVAIL PRATIQUE : TP3 – Jeu éducatif JavaFX
 * ENSEIGNANT : François
 * DATE DE REMISE : Dimanche 17 mai 2026
 *
 * BUT DU PROGRAMME :
 * Cette énumération représente les différents types
 * possibles des balles dans le jeu Roche-Papier-Ciseaux.
 *
 * Les types disponibles sont :
 * - Roche;
 * - Papier;
 * - Ciseaux.
 ********************************************************************************************/

package com.example.travailpratique3.model;

/**
 * Représente les types possibles d'une balle.
 *
 * Cette énumération permet aussi de vérifier
 * quel type gagne contre un autre.
 */
public enum TypeBalle {

    /**
     * Type Roche.
     */
    ROCHE,

    /**
     * Type Papier.
     */
    PAPIER,

    /**
     * Type Ciseaux.
     */
    CISEAUX;

    /**
     * Vérifie si le type actuel gagne contre un autre type.
     *
     * Règles :
     * - Roche bat Ciseaux;
     * - Papier bat Roche;
     * - Ciseaux bat Papier.
     *
     * @param autre type adverse
     * @return true si le type actuel gagne
     */
    public boolean gagneContre(TypeBalle autre) {

        return (this == ROCHE && autre == CISEAUX)

                || (this == PAPIER && autre == ROCHE)

                || (this == CISEAUX && autre == PAPIER);
    }
}