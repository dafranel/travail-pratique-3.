package com.example.travailpratique3.controller;

import com.example.travailpratique3.model.Balle;
import com.example.travailpratique3.model.TypeBalle;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

/**
 * Contrôleur du jeu de balles rebondissantes.
 */
public class BallesController {

    @FXML
    private Pane zoneJeu;

    private final List<Balle> balles = new ArrayList<>();
    private int compteurInsertion = 0;

    private Image imageRoche;
    private Image imagePapier;
    private Image imageCiseaux;

    private AnimationTimer animation;

    @FXML
    public void initialize() {
        imageRoche = new Image(getClass().getResourceAsStream("/com/example/travailpratique3/images/roche.png"));
        imagePapier = new Image(getClass().getResourceAsStream("/com/example/travailpratique3/images/papier.png"));
        imageCiseaux = new Image(getClass().getResourceAsStream("/com/example/travailpratique3/images/ciseaux.png"));

        demarrerAnimation();
    }

    @FXML
    private void ajouterTroisBalles() {
        ajouterBalle();
        ajouterBalle();
        ajouterBalle();
    }

    @FXML
    private void ajouterUneBalle(MouseEvent event) {
        ajouterBalle();
    }

    @FXML
    private void retirerUneBalle(MouseEvent event) {
        if (!balles.isEmpty()) {
            Balle derniere = balles.remove(balles.size() - 1);
            zoneJeu.getChildren().remove(derniere);
        }
    }

    private void ajouterBalle() {
        double rayon = 20;

        double largeur = zoneJeu.getWidth();
        double hauteur = zoneJeu.getHeight();

        if (largeur <= rayon * 2 || hauteur <= rayon * 2) {
            largeur = 700;
            hauteur = 430;
        }

        double x = rayon + Math.random() * (largeur - rayon * 2);
        double y = rayon + Math.random() * (hauteur - rayon * 2);

        TypeBalle type = prochainType();
        Image image = imageSelonType(type);

        Balle balle = new Balle(x, y, rayon, type, image);

        balles.add(balle);
        zoneJeu.getChildren().add(balle);
    }

    private TypeBalle prochainType() {
        TypeBalle type;

        if (compteurInsertion % 3 == 0) {
            type = TypeBalle.ROCHE;
        } else if (compteurInsertion % 3 == 1) {
            type = TypeBalle.PAPIER;
        } else {
            type = TypeBalle.CISEAUX;
        }

        compteurInsertion++;
        return type;
    }

    private Image imageSelonType(TypeBalle type) {
        return switch (type) {
            case ROCHE -> imageRoche;
            case PAPIER -> imagePapier;
            case CISEAUX -> imageCiseaux;
        };
    }

    private void demarrerAnimation() {
        animation = new AnimationTimer() {
            @Override
            public void handle(long now) {
                mettreAJourBalles();
            }
        };

        animation.start();
    }

    private void mettreAJourBalles() {
        for (Balle balle : balles) {
            balle.deplacer();
            verifierRebond(balle);
        }
    }

    private void verifierRebond(Balle balle) {
        double rayon = balle.getRadius();

        if (balle.getCenterX() - rayon <= 0 || balle.getCenterX() + rayon >= zoneJeu.getWidth()) {
            balle.inverserX();
        }

        if (balle.getCenterY() - rayon <= 0 || balle.getCenterY() + rayon >= zoneJeu.getHeight()) {
            balle.inverserY();
        }
    }
}