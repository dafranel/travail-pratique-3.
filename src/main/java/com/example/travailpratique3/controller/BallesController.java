/********************************************************************************************
 * NOM : Chendjou Talla
 * PRÉNOM : Dany Frank Nelson
 * COURS : 420-45P-SI – Programmation d’environnements graphiques
 * TRAVAIL PRATIQUE : TP3 – Jeu éducatif JavaFX
 * ENSEIGNANT : François
 * DATE DE REMISE : Dimanche 17 mai 2026
 *
 * BUT DU PROGRAMME :
 * Cette classe contrôle le jeu des balles rebondissantes basé sur
 * le principe Roche-Papier-Ciseaux.
 *
 * Elle gère :
 * - l’ajout et le retrait des balles;
 * - le déplacement des balles;
 * - les rebonds sur les bordures;
 * - les collisions Roche-Papier-Ciseaux;
 * - les sons au survol des boutons;
 * - la pause et la reprise de l’animation.
 ********************************************************************************************/

package com.example.travailpratique3.controller;

import com.example.travailpratique3.model.Balle;
import com.example.travailpratique3.model.TypeBalle;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

/**
 * Contrôleur du jeu de balles rebondissantes.
 *
 * Cette classe est liée au fichier FXML de la page des balles.
 * Elle permet de gérer les actions de l’utilisateur ainsi que
 * l’animation des balles dans la zone de jeu.
 */
public class BallesController {

    /**
     * Bouton permettant d’ajouter trois balles.
     */
    @FXML
    private Button btnAjouterTrois;

    /**
     * Bouton permettant d’ajouter une seule balle.
     */
    @FXML
    private Button btnAjouterUne;

    /**
     * Bouton permettant de retirer la dernière balle ajoutée.
     */
    @FXML
    private Button btnRetirerUne;

    /**
     * Zone graphique dans laquelle les balles se déplacent.
     */
    @FXML
    private Pane zoneJeu;

    /**
     * Liste contenant toutes les balles actuellement présentes dans le jeu.
     */
    private final List<Balle> balles = new ArrayList<>();

    /**
     * Compteur permettant de respecter l’ordre d’insertion :
     * Roche, Papier, Ciseaux.
     */
    private int compteurInsertion = 0;

    /**
     * Image utilisée pour les balles de type Roche.
     */
    private Image imageRoche;

    /**
     * Image utilisée pour les balles de type Papier.
     */
    private Image imagePapier;

    /**
     * Image utilisée pour les balles de type Ciseaux.
     */
    private Image imageCiseaux;

    /**
     * Animation principale utilisée pour déplacer les balles.
     */
    private AnimationTimer animation;

    /**
     * Méthode appelée automatiquement au chargement de la page FXML.
     *
     * Elle charge les images, ajoute les sons au survol des boutons
     * et démarre l’animation du jeu.
     */
    @FXML
    public void initialize() {
        // Chargement des images utilisées par les trois types de balles
        imageRoche = new Image(getClass().getResourceAsStream(
                "/com/example/travailpratique3/images/roche.png"
        ));

        imagePapier = new Image(getClass().getResourceAsStream(
                "/com/example/travailpratique3/images/papier.png"
        ));

        imageCiseaux = new Image(getClass().getResourceAsStream(
                "/com/example/travailpratique3/images/ciseaux.png"
        ));

        // Ajout d’un effet sonore lorsque la souris survole les boutons
        ajouterSonsSurvol();

        // Démarrage de l’animation des balles
        demarrerAnimation();
    }

    /**
     * Ajoute trois balles dans la zone de jeu.
     *
     * L’ordre Roche, Papier, Ciseaux est respecté.
     */
    @FXML
    private void ajouterTroisBalles() {
        ajouterBalle();
        ajouterBalle();
        ajouterBalle();
    }

    /**
     * Ajoute une seule balle dans la zone de jeu.
     *
     * @param event événement de souris déclenché par le clic
     */
    @FXML
    private void ajouterUneBalle(MouseEvent event) {
        ajouterBalle();
    }

    /**
     * Retire la dernière balle ajoutée dans la zone de jeu.
     *
     * Si aucune balle n’est présente, aucune action n’est effectuée.
     *
     * @param event événement de souris déclenché par le clic
     */
    @FXML
    private void retirerUneBalle(MouseEvent event) {
        // Vérifie qu’il y a au moins une balle à retirer
        if (!balles.isEmpty()) {
            // Récupère et retire la dernière balle de la liste
            Balle derniere = balles.remove(balles.size() - 1);

            // Retire la balle de l’affichage graphique
            zoneJeu.getChildren().remove(derniere);
        }
    }

    /**
     * Met l’animation en pause lorsque le clic de souris est maintenu.
     *
     * @param event événement de souris
     */
    @FXML
    private void mettreEnPause(MouseEvent event) {
        // Vérifie que l’animation existe avant de l’arrêter
        if (animation != null) {
            animation.stop();
        }
    }

    /**
     * Reprend l’animation lorsque le clic de souris est relâché.
     *
     * @param event événement de souris
     */
    @FXML
    private void reprendreAnimation(MouseEvent event) {
        // Vérifie que l’animation existe avant de la redémarrer
        if (animation != null) {
            animation.start();
        }
    }

    /**
     * Ajoute une balle à une position aléatoire dans la zone de jeu.
     *
     * La balle ajoutée respecte l’ordre Roche, Papier, Ciseaux.
     */
    private void ajouterBalle() {
        // Rayon minimal exigé pour une balle
        double rayon = 20;

        // Récupère la taille actuelle de la zone de jeu
        double largeur = zoneJeu.getWidth();
        double hauteur = zoneJeu.getHeight();

        // Valeurs de sécurité si la zone n’est pas encore complètement chargée
        if (largeur <= rayon * 2 || hauteur <= rayon * 2) {
            largeur = 700;
            hauteur = 430;
        }

        // Génère une position aléatoire en évitant que la balle dépasse les bordures
        double x = rayon + Math.random() * (largeur - rayon * 2);
        double y = rayon + Math.random() * (hauteur - rayon * 2);

        // Détermine le prochain type de balle selon l’ordre prévu
        TypeBalle type = prochainType();

        // Récupère l’image correspondant au type de balle
        Image image = imageSelonType(type);

        // Création de la balle
        Balle balle = new Balle(x, y, rayon, type, image);

        // Ajout de la balle dans la liste logique
        balles.add(balle);

        // Ajout de la balle dans la zone graphique
        zoneJeu.getChildren().add(balle);
    }

    /**
     * Retourne le prochain type de balle selon l’ordre :
     * Roche, Papier, Ciseaux.
     *
     * @return le prochain type de balle à créer
     */
    private TypeBalle prochainType() {
        TypeBalle type;

        // Premier élément de l’ordre : Roche
        if (compteurInsertion % 3 == 0) {
            type = TypeBalle.ROCHE;

            // Deuxième élément de l’ordre : Papier
        } else if (compteurInsertion % 3 == 1) {
            type = TypeBalle.PAPIER;

            // Troisième élément de l’ordre : Ciseaux
        } else {
            type = TypeBalle.CISEAUX;
        }

        // Prépare le prochain ajout
        compteurInsertion++;

        return type;
    }

    /**
     * Retourne l’image correspondant au type de balle reçu.
     *
     * @param type type de la balle
     * @return image associée au type de balle
     */
    private Image imageSelonType(TypeBalle type) {
        return switch (type) {
            case ROCHE -> imageRoche;
            case PAPIER -> imagePapier;
            case CISEAUX -> imageCiseaux;
        };
    }

    /**
     * Démarre l’animation principale du jeu.
     *
     * L’animation appelle continuellement la méthode mettreAJourBalles().
     */
    private void demarrerAnimation() {
        animation = new AnimationTimer() {
            /**
             * Méthode appelée automatiquement à chaque frame.
             *
             * @param now temps actuel fourni par JavaFX
             */
            @Override
            public void handle(long now) {
                mettreAJourBalles();
            }
        };

        // Lance l’animation
        animation.start();
    }

    /**
     * Met à jour toutes les balles.
     *
     * Cette méthode :
     * - déplace les balles;
     * - vérifie les rebonds sur les bordures;
     * - vérifie les collisions entre balles.
     */
    private void mettreAJourBalles() {
        // Déplace chaque balle et vérifie les rebonds
        for (Balle balle : balles) {
            balle.deplacer();
            verifierRebond(balle);
        }

        // Vérifie les collisions entre les balles
        verifierCollisions();
    }

    /**
     * Vérifie si une balle touche une bordure de la zone de jeu.
     *
     * Si la balle touche une bordure horizontale ou verticale,
     * sa direction est inversée.
     *
     * @param balle balle à vérifier
     */
    private void verifierRebond(Balle balle) {
        double rayon = balle.getRadius();

        // Collision avec la bordure gauche ou droite
        if (balle.getCenterX() - rayon <= 0
                || balle.getCenterX() + rayon >= zoneJeu.getWidth()) {
            balle.inverserX();
        }

        // Collision avec la bordure du haut ou du bas
        if (balle.getCenterY() - rayon <= 0
                || balle.getCenterY() + rayon >= zoneJeu.getHeight()) {
            balle.inverserY();
        }
    }

    /**
     * Vérifie les collisions entre toutes les balles.
     *
     * Lorsqu’une collision est détectée, un combat
     * Roche-Papier-Ciseaux est appliqué.
     */
    private void verifierCollisions() {
        // Première balle à comparer
        for (int i = 0; i < balles.size(); i++) {

            // Deuxième balle à comparer
            for (int j = i + 1; j < balles.size(); j++) {
                Balle b1 = balles.get(i);
                Balle b2 = balles.get(j);

                // Distance horizontale entre les deux balles
                double dx = b1.getCenterX() - b2.getCenterX();

                // Distance verticale entre les deux balles
                double dy = b1.getCenterY() - b2.getCenterY();

                // Distance réelle entre les centres des deux balles
                double distance = Math.sqrt(dx * dx + dy * dy);

                // Si la distance est plus petite que la somme des rayons,
                // les balles se touchent
                if (distance <= b1.getRadius() + b2.getRadius()) {
                    appliquerCombat(b1, b2);
                }
            }
        }
    }

    /**
     * Ajoute un son au survol des boutons de la page des balles.
     */
    private void ajouterSonsSurvol() {
        // Tableau contenant les boutons concernés
        Button[] boutons = {btnAjouterTrois, btnAjouterUne, btnRetirerUne};

        // Ajoute le son de survol à chaque bouton
        for (Button bouton : boutons) {
            bouton.setOnMouseEntered(event -> Audio.jouerSon("survol.mp3"));
        }
    }

    /**
     * Applique le combat Roche-Papier-Ciseaux entre deux balles.
     *
     * La balle perdante prend le type et l’image de la balle gagnante.
     * Les balles conservent leur trajectoire.
     *
     * @param b1 première balle
     * @param b2 deuxième balle
     */
    private void appliquerCombat(Balle b1, Balle b2) {
        // Si les deux balles ont le même type, il n’y a aucun gagnant
        if (b1.getType() == b2.getType()) {
            return;
        }

        Balle gagnante;
        Balle perdante;

        // Détermine la balle gagnante selon les règles Roche-Papier-Ciseaux
        if (b1.getType().gagneContre(b2.getType())) {
            gagnante = b1;
            perdante = b2;
        } else {
            gagnante = b2;
            perdante = b1;
        }

        // La balle perdante prend le type et l’image de la balle gagnante
        perdante.setType(gagnante.getType(), imageSelonType(gagnante.getType()));
    }
}