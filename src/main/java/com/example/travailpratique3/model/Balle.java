package com.example.travailpratique3.model;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * Représente une balle rebondissante.
 */
public class Balle extends Circle {

    private double vitesseX;
    private double vitesseY;
    private TypeBalle type;

    public Balle(double x, double y, double rayon, TypeBalle type, Image image) {
        super(x, y, rayon);

        this.type = type;

        vitesseX = Math.random() * 4 + 2;
        vitesseY = Math.random() * 4 + 2;

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

    public void deplacer() {
        setCenterX(getCenterX() + vitesseX);
        setCenterY(getCenterY() + vitesseY);
    }

    public void inverserX() {
        vitesseX = -vitesseX;
    }

    public void inverserY() {
        vitesseY = -vitesseY;
    }

    public TypeBalle getType() {
        return type;
    }

    public void setType(TypeBalle type, Image image) {
        this.type = type;
        setFill(new ImagePattern(image));
    }
}