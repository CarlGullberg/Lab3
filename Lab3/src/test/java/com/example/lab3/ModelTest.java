package com.example.lab3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
class ModelTest {
    private Model model;

    @BeforeEach
    void setUp() {
        model = new Model();
        model.resetBoard();
    }


    @Test
    void checkForAValidMove() {
        assertTrue(model.makeMove(0, 0, 'X'), "Första draget på en tom ruta borde vara giltigt");
        assertFalse(model.makeMove(0, 0, 'O'), "Drag på en redan fylld ruta borde vara ogiltigt och returnera false");
        assertTrue(model.makeMove(1, 1, 'O'), "Drag på en tom ruta borde vara giltigt och returnera true");
        assertFalse(model.makeMove(1, 1, 'X'), "Drag på en redan fylld ruta borde vara ogiltigt och returnera false");

    }

    @Test
    void testIfRoundIsFinishedOrNot() {
        model.makeMove(0, 0, 'X');
        model.makeMove(0, 1, 'X');
        model.makeMove(0, 2, 'X');
        assertEquals('X', model.checkWinner(), "Spelare X borde ha vunnit horisontellt på första raden");

        model.resetBoard();
        model.makeMove(1, 0, 'O');
        model.makeMove(1, 1, 'O');
        model.makeMove(1, 2, 'O');
        assertEquals('O', model.checkWinner(), "Spelare O borde ha vunnit horisontellt på andra raden");

        model.resetBoard();
        model.makeMove(2, 0, 'X');
        model.makeMove(2, 1, 'X');
        model.makeMove(2, 2, 'X');
        assertEquals('X', model.checkWinner(), "Spelare X borde ha vunnit horisontellt på tredje raden");

        // Testa vertikala vinster
        model.resetBoard();
        model.makeMove(0, 0, 'O');
        model.makeMove(1, 0, 'O');
        model.makeMove(2, 0, 'O');
        assertEquals('O', model.checkWinner(), "Spelare O borde ha vunnit vertikalt i första kolumnen");

        model.resetBoard();
        model.makeMove(0, 1, 'X');
        model.makeMove(1, 1, 'X');
        model.makeMove(2, 1, 'X');
        assertEquals('X', model.checkWinner(), "Spelare X borde ha vunnit vertikalt i andra kolumnen");

        model.resetBoard();
        model.makeMove(0, 2, 'O');
        model.makeMove(1, 2, 'O');
        model.makeMove(2, 2, 'O');
        assertEquals('O', model.checkWinner(), "Spelare O borde ha vunnit vertikalt i tredje kolumnen");

        // Testa diagonala vinster
        model.resetBoard();
        model.makeMove(0, 0, 'X');
        model.makeMove(1, 1, 'X');
        model.makeMove(2, 2, 'X');
        assertEquals('X', model.checkWinner(), "Spelare X borde ha vunnit diagonalt från övre vänstra till nedre högra");

        model.resetBoard();
        model.makeMove(0, 2, 'O');
        model.makeMove(1, 1, 'O');
        model.makeMove(2, 0, 'O');
        assertEquals('O', model.checkWinner(), "Spelare O borde ha vunnit diagonalt från övre högra till nedre vänstra");

        // Testa ingen vinnare än
        model.resetBoard();
        model.makeMove(0, 0, 'X');
        model.makeMove(1, 1, 'O');
        assertEquals('-', model.checkWinner(), "Spelet borde pågå utan vinnare än");

        // Testa oavgjort
        model.resetBoard();
        model.makeMove(0, 0, 'X');
        model.makeMove(0, 1, 'O');
        model.makeMove(0, 2, 'X');
        model.makeMove(1, 0, 'X');
        model.makeMove(1, 1, 'O');
        model.makeMove(1, 2, 'X');
        model.makeMove(2, 0, 'O');
        model.makeMove(2, 1, 'X');
        model.makeMove(2, 2, 'O');
        assertEquals('D', model.checkWinner(), "Spelet borde vara oavgjort");
    }

}