/********************************************************************************************
 * NOM : Chendjou Talla
 * PRÉNOM : Dany Frank Nelson
 * COURS : 420-45P-SI – Programmation d’environnements graphiques
 * TRAVAIL PRATIQUE : TP3 – Jeu éducatif JavaFX
 * ENSEIGNANT : François
 * DATE DE REMISE : Dimanche 17 mai 2026
 *
 * BUT DU PROGRAMME :
 * Cette classe représente une balle du jeu Roche-Papier-Ciseaux.
 * Chaque balle possède une position, une vitesse, une image et un type.
 *
 * Les balles peuvent :
 * - se déplacer;
 * - rebondir sur les bordures;
 * - changer de type après une collision.
 ********************************************************************************************/

package com.example.travailpratique3.model;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * Représente une balle rebondissante dans le jeu.
 *
 * Cette classe hérite de Circle afin de profiter directement
 * des fonctionnalités graphiques de JavaFX.
 */
public class Balle extends Circle {

    /**
     * Vitesse horizontale de la balle.
     */
    private double vitesseX;

    /**
     * Vitesse verticale de la balle.
     */
    private double vitesseY;

    /**
     * Type de la balle :
     * Roche, Papier ou Ciseaux.
     */
    private TypeBalle type;

    /**
     * Constructeur de la balle.
     *
     * @param x position horizontale de départ
     * @param y position verticale de départ
     * @param rayon rayon de la balle
     * @param type type de la balle
     * @param image image utilisée pour afficher la balle
     */
    public Balle(double x, double y, double rayon,
                 TypeBalle type, Image image) {

        // Appelle le constructeur de Circle.
        super(x, y, rayon);

        this.type = type;

        // Génère une vitesse aléatoire entre 2 et 6.
        vitesseX = Math.random() * 4 + 2;
        vitesseY = Math.random() * 4 + 2;

        // Une chance sur deux d’aller vers la gauche.
        if (Math.random() < 0.5) {
            vitesseX = -vitesseX;
        }

        // Une chance sur deux d’aller vers le haut.
        if (Math.random() < 0.5) {
            vitesseY = -vitesseY;
        }

        // Applique l’image à la balle.
        setFill(new ImagePattern(image));

        // Ajoute une bordure noire autour de la balle.
        setStroke(Color.BLACK);
        setStrokeWidth(1);
    }

    /**
     * Déplace la balle selon sa vitesse actuelle.
     */
    public void deplacer() {

        // Déplacement horizontal.
        setCenterX(getCenterX() + vitesseX);

        // Déplacement vertical.
        setCenterY(getCenterY() + vitesseY);
    }

    /**
     * Inverse le déplacement horizontal.
     *
     * Utilisé lorsqu’une balle touche
     * la bordure gauche ou droite.
     */
    public void inverserX() {
        vitesseX = -vitesseX;
    }

    /**
     * Inverse le déplacement vertical.
     *
     * Utilisé lorsqu’une balle touche
     * la bordure du haut ou du bas.
     */
    public void inverserY() {
        vitesseY = -vitesseY;
    }

    /**
     * Retourne le type actuel de la balle.
     *
     * @return type de la balle
     */
    public TypeBalle getType() {
        return type;
    }

    /**
     * Modifie le type et l’image de la balle.
     *
     * Cette méthode est utilisée après une collision
     * Roche-Papier-Ciseaux.
     *
     * @param type nouveau type de balle
     * @param image nouvelle image associée
     */
    public void setType(TypeBalle type, Image image) {

        this.type = type;

        // Change l’image affichée.
        setFill(new ImagePattern(image));
    }
}