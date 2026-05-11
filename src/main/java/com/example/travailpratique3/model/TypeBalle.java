package com.example.travailpratique3.model;

/**
 * Représente les types possibles d'une balle.
 */
public enum TypeBalle {
    ROCHE,
    PAPIER,
    CISEAUX;

    /**
     * Vérifie si ce type gagne contre l'autre.
     *
     * @param autre type adverse
     * @return true si ce type gagne
     */
    public boolean gagneContre(TypeBalle autre) {
        return (this == ROCHE && autre == CISEAUX)
                || (this == PAPIER && autre == ROCHE)
                || (this == CISEAUX && autre == PAPIER);
    }
}