package com.example.lab3;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.Random;

public class HelloController {

    public Button resetButton;
    @FXML
    private GridPane grid;
    @FXML
    private Label resultLabel;
    @FXML
    private Label scoreLabel;
    private Model model;
    private Button[][] buttons;

    public void initialize() {
        model = new Model();
        buttons = new Button[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button();
                button.setPrefSize(100, 100);
                button.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;");
                final int row = i;
                final int col = j;
                button.setOnAction(e -> handlePlayerMove(row, col));
                grid.add(button, j, i);
                buttons[i][j] = button;
            }
        }
        updateScoreLabel();
    }

    private void handlePlayerMove(int row, int col) {
        if (model.makeMove(row, col, model.getCurrentPlayer())) {
            buttons[row][col].setText(String.valueOf(model.getCurrentPlayer()));
            checkGameStatus();
            model.switchPlayer();
            computerMove();
        }
    }

    private void computerMove() {
        Random random = new Random();
        boolean validMove = false;
        while (!validMove) {
            int row = random.nextInt(3);
            int col = random.nextInt(3);
            validMove = model.makeMove(row, col, model.getCurrentPlayer());
            if (validMove) {
                buttons[row][col].setText(String.valueOf(model.getCurrentPlayer()));
                checkGameStatus();
                model.switchPlayer();
            }
        }
    }

    private void checkGameStatus() {
        char winner = model.checkWinner();
        if (winner == 'X') {
            resultLabel.setText("PLAYER WINS!");
            resultLabel.setStyle("-fx-font-size: 25px;-fx-font-weight: bold; -fx-text-fill: white;");
            model.updateScore('X');
            resetGame();
        } else if (winner == 'O') {
            resultLabel.setText("COMPUTER WINS!");
            resultLabel.setStyle("-fx-font-size: 25px;-fx-font-weight: bold; -fx-text-fill: white;");
            model.updateScore('O');
            resetGame();
        } else if (winner == 'D') {
            resultLabel.setText("It's a draw!");
            resultLabel.setStyle("-fx-font-size: 25px;-fx-font-weight: bold; -fx-text-fill: white;");
            resetGame();
        }
    }


    @FXML
    private void resetGame() {
        model.resetBoard();
        updateScoreLabel();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");

            }
        }
        updateScoreLabel();
    }


    private void updateScoreLabel() {
        scoreLabel.setText("Player: " + model.getPlayerScore() + " | Computer: " + model.getComputerScore());

    }

    public void resetGameButton(ActionEvent actionEvent) {
        model.resetScores();
        resetGame();
        updateScoreLabel();
    }
}