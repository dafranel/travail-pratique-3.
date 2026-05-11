package com.example.travailpratique3.model;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * Classe représentant une balle rebondissante.
 *
 * Chaque balle possède :
 * - un type : roche, papier ou ciseaux;
 * - une image;
 * - une vitesse horizontale;
 * - une vitesse verticale;
 * - un contour noir.
 */
public class Balle extends Circle {

    /** Vitesse horizontale de la balle. */
    private double vitesseX;

    /** Vitesse verticale de la balle. */
    private double vitesseY;

    /** Type actuel de la balle. */
    private com.example.travailpratique3.model.TypeBalle type;

    /**
     * Constructeur d'une balle.
     *
     * @param x position horizontale
     * @param y position verticale
     * @param rayon rayon de la balle
     * @param type type roche, papier ou ciseaux
     * @param image image associée au type
     */
    public Balle(double x, double y, double rayon, com.example.travailpratique3.model.TypeBalle type, Image image) {
        super(x, y, rayon);

        this.type = type;

        this.vitesseX = Math.random() * 4 + 2;
        this.vitesseY = Math.random() * 4 + 2;

        if (Math.random() < 0.5) {
            vitesseX = -vitesseX;
        }

        if (Math.random() < 0.5) {
            vitesseY = -vitesseY;
        }

        setFill(new ImagePattern(image));
        setStroke(Color.BLACK);
        setStrokeWidth(1);
    }

    /**
     * Déplace la balle selon sa vitesse.
     */
    public void deplacer() {
        setCenterX(getCenterX() + vitesseX);
        setCenterY(getCenterY() + vitesseY);
    }

    /**
     * Inverse la direction horizontale.
     */
    public void inverserX() {
        vitesseX = -vitesseX;
    }

    /**
     * Inverse la direction verticale.
     */
    public void inverserY() {
        vitesseY = -vitesseY;
    }

    /**
     * Retourne le type de la balle.
     *
     * @return type actuel
     */
    public TypeBalle getType() {
        return type;
    }

    /**
     * Modifie le type et l'image de la balle.
     *
     * @param type nouveau type
     * @param image nouvelle image
     */
    public void setType(TypeBalle type, Image image) {
        this.type = type;
        setFill(new ImagePattern(image));
    }
}