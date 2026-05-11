package com.example.travailpratique3.controller;

import com.example.travailpratique3.model.Question;
import com.example.travailpratique3.util.GestionnaireTheme;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Contrôleur du quiz Québec/Canada.
 */
public class QuizController {

    @FXML private Label labelProgression;
    @FXML private Label labelQuestion;
    @FXML private Label labelFeedback;
    @FXML private Label labelScore;

    @FXML private Button btn1;
    @FXML private Button btn2;
    @FXML private Button btn3;
    @FXML private Button btn4;

    private final List<Question> questions = new ArrayList<>();

    private int indexQuestion = 0;
    private int score = 0;
    private boolean reponseDonnee = false;

    @FXML
    public void initialize() {
        chargerQuestions();
        afficherQuestion();
    }

    private void chargerQuestions() {
        questions.clear();

        questions.add(new Question("En quelle année la Confédération canadienne a-t-elle eu lieu ?",
                new String[]{"1608", "1763", "1867", "1982"}, 2));

        questions.add(new Question("Quelle est la capitale de la province de Québec ?",
                new String[]{"Montréal", "Québec", "Gatineau", "Laval"}, 1));

        questions.add(new Question("Qui est considéré comme le fondateur de la ville de Québec ?",
                new String[]{"Jacques Cartier", "Samuel de Champlain", "Paul Chomedey de Maisonneuve", "René Lévesque"}, 1));

        questions.add(new Question("Quel fleuve traverse une grande partie du Québec ?",
                new String[]{"Le fleuve Saint-Laurent", "Le fleuve Mississippi", "Le fleuve Fraser", "Le fleuve Yukon"}, 0));

        questions.add(new Question("En quelle année la Charte canadienne des droits et libertés est-elle entrée en vigueur ?",
                new String[]{"1867", "1931", "1960", "1982"}, 3));

        questions.add(new Question("Qui a été premier ministre du Québec lors du référendum de 1980 ?",
                new String[]{"Jean Charest", "René Lévesque", "Robert Bourassa", "Pierre Elliott Trudeau"}, 1));

        questions.add(new Question("Quelle province canadienne est la plus peuplée ?",
                new String[]{"Québec", "Ontario", "Alberta", "Manitoba"}, 1));

        questions.add(new Question("Quel symbole est au centre du drapeau canadien ?",
                new String[]{"Un castor", "Une fleur de lys", "Une feuille d'érable", "Une étoile"}, 2));

        questions.add(new Question("Quel artiste québécois est associé à la chanson « Mon pays » ?",
                new String[]{"Gilles Vigneault", "Robert Charlebois", "Céline Dion", "Félix Leclerc"}, 0));

        questions.add(new Question("Quel sport est souvent considéré comme le sport national d’hiver du Canada ?",
                new String[]{"Le soccer", "Le hockey sur glace", "Le baseball", "Le basketball"}, 1));
    }

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

    private void verifierReponse(int choix) {
        if (reponseDonnee) {
            return;
        }

        reponseDonnee = true;

        Question question = questions.get(indexQuestion);
        int bonne = question.getBonneReponse();

        if (choix == bonne) {
            score++;
            labelFeedback.setText("Bonne réponse !");
        } else {
            labelFeedback.setText("Mauvaise réponse. La bonne réponse était : " + question.getChoix()[bonne]);
        }

        colorerReponses(choix, bonne);
        labelScore.setText("Score : " + score + " / " + questions.size());
        activerBoutons(false);
    }

    @FXML private void reponse1() { verifierReponse(0); }
    @FXML private void reponse2() { verifierReponse(1); }
    @FXML private void reponse3() { verifierReponse(2); }
    @FXML private void reponse4() { verifierReponse(3); }

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

    private void afficherFinQuiz() {
        labelProgression.setText("Quiz terminé");
        labelQuestion.setText("Résultat final : " + score + " / " + questions.size());

        if (score >= 8) {
            labelFeedback.setText("Excellent ! Tu connais très bien le Québec et le Canada.");
        } else if (score >= 5) {
            labelFeedback.setText("Bon résultat ! Tu as de bonnes bases.");
        } else {
            labelFeedback.setText("À réviser, mais tu peux t'améliorer rapidement.");
        }

        labelScore.setText("Note : " + (score * 100 / questions.size()) + " %");

        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");

        activerBoutons(false);
        nettoyerStylesBoutons();
    }

    @FXML
    private void recommencerQuiz() {
        indexQuestion = 0;
        score = 0;
        reponseDonnee = false;
        afficherQuestion();
    }

    @FXML
    private void changerTheme() {
        Stage stage = (Stage) labelQuestion.getScene().getWindow();
        GestionnaireTheme.getInstance().changerTheme(stage.getScene());
    }

    private void activerBoutons(boolean actif) {
        btn1.setDisable(!actif);
        btn2.setDisable(!actif);
        btn3.setDisable(!actif);
        btn4.setDisable(!actif);
    }

    private void colorerReponses(int choixUtilisateur, int bonneReponse) {
        Button[] boutons = {btn1, btn2, btn3, btn4};

        boutons[bonneReponse].getStyleClass().add("bonne-reponse");

        if (choixUtilisateur != bonneReponse) {
            boutons[choixUtilisateur].getStyleClass().add("mauvaise-reponse");
        }
    }

    private void nettoyerStylesBoutons() {
        Button[] boutons = {btn1, btn2, btn3, btn4};

        for (Button bouton : boutons) {
            bouton.getStyleClass().remove("bonne-reponse");
            bouton.getStyleClass().remove("mauvaise-reponse");
        }
    }
}