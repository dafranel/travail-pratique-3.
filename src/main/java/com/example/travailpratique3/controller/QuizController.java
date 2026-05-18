/********************************************************************************************
 * NOM : Chendjou Talla
 * PRÉNOM : Dany Frank Nelson
 * COURS : 420-45P-SI – Programmation d’environnements graphiques
 * TRAVAIL PRATIQUE : TP3 – Jeu éducatif JavaFX
 * ENSEIGNANT : François
 * DATE DE REMISE : Dimanche 17 mai 2026
 *
 * BUT DU PROGRAMME :
 * Cette classe contrôle le quiz éducatif sur le Québec et le Canada.
 * Elle affiche les questions, vérifie les réponses, calcule le score,
 * joue les sons et permet de changer le thème visuel.
 ********************************************************************************************/

package com.example.travailpratique3.controller;

import com.example.travailpratique3.model.Question;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Contrôleur du quiz Québec/Canada.
 *
 * Cette classe fait le lien entre le fichier FXML et la logique du quiz.
 */
public class QuizController {

    @FXML
    private Button btnQuestionSuivante;

    @FXML
    private Button btnRecommencer;

    @FXML
    private Button btnChangerTheme;

    @FXML
    private Label labelProgression;

    @FXML
    private Label labelQuestion;

    @FXML
    private Label labelFeedback;

    @FXML
    private Label labelScore;

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private Button btn3;

    @FXML
    private Button btn4;

    /**
     * Liste contenant toutes les questions du quiz.
     */
    private final List<Question> questions = new ArrayList<>();

    /**
     * Position de la question actuelle dans la liste.
     */
    private int indexQuestion = 0;

    /**
     * Score obtenu par l’utilisateur.
     */
    private int score = 0;

    /**
     * Indique si l’utilisateur a déjà répondu à la question actuelle.
     */
    private boolean reponseDonnee = false;

    /**
     * Méthode appelée automatiquement au chargement du FXML.
     */
    @FXML
    public void initialize() {
        chargerQuestions();
        afficherQuestion();
        ajouterSonsSurvol();

        // Lance la musique de fond du quiz.
        Audio.jouerMusiqueFond("quiz.mp3");
    }

    /**
     * Charge les questions du quiz.
     */
    private void chargerQuestions() {
        questions.clear();

        questions.add(new Question("En quelle année la Confédération canadienne a-t-elle eu lieu ?",
                new String[]{"1608", "1763", "1867", "1982"}, 2));

        questions.add(new Question("Quelle est la capitale de la province de Québec ?",
                new String[]{"Montréal", "Québec", "Gatineau", "Laval"}, 1));

        questions.add(new Question("Qui est considéré comme le fondateur de la ville de Québec ?",
                new String[]{"Jacques Cartier", "Samuel de Champlain", "Maisonneuve", "René Lévesque"}, 1));

        questions.add(new Question("Quel fleuve traverse une grande partie du Québec ?",
                new String[]{"Saint-Laurent", "Mississippi", "Fraser", "Yukon"}, 0));

        questions.add(new Question("En quelle année la Charte canadienne des droits et libertés est-elle entrée en vigueur ?",
                new String[]{"1867", "1931", "1960", "1982"}, 3));

        questions.add(new Question("Qui a été premier ministre du Québec lors du référendum de 1980 ?",
                new String[]{"Jean Charest", "René Lévesque", "Robert Bourassa", "Pierre Elliott Trudeau"}, 1));

        questions.add(new Question("Quelle province canadienne est la plus peuplée ?",
                new String[]{"Québec", "Ontario", "Alberta", "Manitoba"}, 1));

        questions.add(new Question("Quel symbole est au centre du drapeau canadien ?",
                new String[]{"Un castor", "Une fleur de lys", "Une feuille d’érable", "Une étoile"}, 2));

        questions.add(new Question("Quel artiste québécois est associé à la chanson « Mon pays » ?",
                new String[]{"Gilles Vigneault", "Robert Charlebois", "Céline Dion", "Félix Leclerc"}, 0));

        questions.add(new Question("Quel sport est souvent considéré comme le sport national d’hiver du Canada ?",
                new String[]{"Soccer", "Hockey sur glace", "Baseball", "Basketball"}, 1));

        questions.add(new Question("Quelle ville est la plus grande du Québec ?",
                new String[]{"Québec", "Laval", "Montréal", "Sherbrooke"}, 2));

        questions.add(new Question("Quel animal est un symbole important du Canada ?",
                new String[]{"Lion", "Castor", "Kangourou", "Aigle"}, 1));

        questions.add(new Question("Quel est le nom du parlement fédéral du Canada ?",
                new String[]{"Assemblée nationale", "Parlement du Canada", "Sénat du Québec", "Conseil fédéral"}, 1));

        questions.add(new Question("Quelle est la capitale du Canada ?",
                new String[]{"Toronto", "Montréal", "Ottawa", "Vancouver"}, 2));

        questions.add(new Question("Quel événement est célébré le 1er juillet au Canada ?",
                new String[]{"Fête nationale du Québec", "Jour du Souvenir", "Fête du Canada", "Action de grâce"}, 2));

        questions.add(new Question("Quel événement est célébré le 24 juin au Québec ?",
                new String[]{"Fête du Travail", "Fête nationale du Québec", "Noël", "Fête du Canada"}, 1));

        questions.add(new Question("Qui est Céline Dion ?",
                new String[]{"Une chanteuse québécoise", "Une politicienne", "Une exploratrice", "Une athlète"}, 0));

        questions.add(new Question("Quel est le nom de l’Assemblée législative du Québec ?",
                new String[]{"Assemblée nationale", "Chambre des communes", "Sénat", "Conseil municipal"}, 0));

        questions.add(new Question("Quel océan se trouve à l’est du Canada ?",
                new String[]{"Pacifique", "Atlantique", "Indien", "Arctique"}, 1));

        questions.add(new Question("Quelle province canadienne est majoritairement francophone ?",
                new String[]{"Ontario", "Québec", "Alberta", "Saskatchewan"}, 1));
    }

    /**
     * Affiche la question actuelle et ses choix de réponse.
     */
    private void afficherQuestion() {
        Question question = questions.get(indexQuestion);

        labelProgression.setText("Question " + (indexQuestion + 1) + " / " + questions.size());
        labelQuestion.setText(question.getTexte());

        btn1.setText(question.getChoix()[0]);
        btn2.setText(question.getChoix()[1]);
        btn3.setText(question.getChoix()[2]);
        btn4.setText(question.getChoix()[3]);

        labelFeedback.setText("");
        labelScore.setText("Score : " + score + " / " + questions.size());

        reponseDonnee = false;

        activerBoutons(true);
        nettoyerStylesBoutons();
    }

    /**
     * Ajoute un son lorsque la souris survole les boutons du quiz.
     */
    private void ajouterSonsSurvol() {
        Button[] boutons = {
                btn1, btn2, btn3, btn4,
                btnQuestionSuivante, btnRecommencer, btnChangerTheme
        };

        for (Button bouton : boutons) {
            if (bouton != null) {
                bouton.setOnMouseEntered(event -> Audio.jouerSon("survol.mp3"));
            }
        }
    }

    /**
     * Vérifie la réponse choisie.
     *
     * @param choix index du choix sélectionné
     */
    private void verifierReponse(int choix) {
        // Empêche de répondre plusieurs fois à la même question.
        if (reponseDonnee) {
            return;
        }

        reponseDonnee = true;

        Question question = questions.get(indexQuestion);
        int bonne = question.getBonneReponse();

        if (choix == bonne) {
            score++;
            labelFeedback.setText("Bonne réponse !");
            Audio.jouerSon("correct.mp3");
        } else {
            labelFeedback.setText("Mauvaise réponse. La bonne réponse était : " + question.getChoix()[bonne]);
            Audio.jouerSon("erreur.mp3");
        }

        colorerReponses(choix, bonne);
        labelScore.setText("Score : " + score + " / " + questions.size());

        // On bloque les boutons après la réponse.
        activerBoutons(false);
    }

    @FXML
    private void reponse1() {
        verifierReponse(0);
    }

    @FXML
    private void reponse2() {
        verifierReponse(1);
    }

    @FXML
    private void reponse3() {
        verifierReponse(2);
    }

    @FXML
    private void reponse4() {
        verifierReponse(3);
    }

    /**
     * Passe à la question suivante.
     */
    @FXML
    private void questionSuivante() {
        if (!reponseDonnee) {
            labelFeedback.setText("Choisis une réponse avant de continuer.");
            return;
        }

        indexQuestion++;

        if (indexQuestion < questions.size()) {
            afficherQuestion();
        } else {
            afficherFinQuiz();
        }
    }

    /**
     * Affiche le résultat final du quiz.
     */
    private void afficherFinQuiz() {
        labelProgression.setText("Quiz terminé");
        labelQuestion.setText("Résultat final : " + score + " / " + questions.size());

        if (score >= 17) {
            labelFeedback.setText("Excellent ! Tu connais très bien le Québec et le Canada.");
        } else if (score >= 12) {
            labelFeedback.setText("Très bon résultat !");
        } else if (score >= 8) {
            labelFeedback.setText("Bon effort, mais il y a encore des choses à réviser.");
        } else {
            labelFeedback.setText("À réviser. Tu peux t’améliorer rapidement !");
        }

        labelScore.setText("Note : " + (score * 100 / questions.size()) + " %");

        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");

        activerBoutons(false);
        nettoyerStylesBoutons();
    }

    /**
     * Recommence le quiz depuis le début.
     */
    @FXML
    private void recommencerQuiz() {
        indexQuestion = 0;
        score = 0;
        reponseDonnee = false;

        afficherQuestion();
    }

    /**
     * Change le thème visuel de l’application.
     */
    @FXML
    private void changerTheme() {
        Stage stage = (Stage) labelQuestion.getScene().getWindow();
        GestionnaireTheme.getInstance().changerTheme(stage.getScene());
    }

    /**
     * Active ou désactive les boutons de réponse.
     *
     * @param actif true pour activer les boutons, false pour les désactiver
     */
    private void activerBoutons(boolean actif) {
        btn1.setDisable(!actif);
        btn2.setDisable(!actif);
        btn3.setDisable(!actif);
        btn4.setDisable(!actif);
    }

    /**
     * Colore la bonne réponse et la mauvaise réponse choisie.
     *
     * @param choixUtilisateur réponse choisie par l’utilisateur
     * @param bonneReponse bonne réponse
     */
    private void colorerReponses(int choixUtilisateur, int bonneReponse) {
        Button[] boutons = {btn1, btn2, btn3, btn4};

        boutons[bonneReponse].getStyleClass().add("bonne-reponse");

        if (choixUtilisateur != bonneReponse) {
            boutons[choixUtilisateur].getStyleClass().add("mauvaise-reponse");
        }
    }

    /**
     * Retire les anciennes couleurs des boutons.
     */
    private void nettoyerStylesBoutons() {
        Button[] boutons = {btn1, btn2, btn3, btn4};

        for (Button bouton : boutons) {
            bouton.getStyleClass().remove("bonne-reponse");
            bouton.getStyleClass().remove("mauvaise-reponse");
        }
    }
}