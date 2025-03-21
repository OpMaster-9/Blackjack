package me.opmaster.cli;

import me.opmaster.game.BlackJack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class CLI {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BlackJack game;
    public void start() {
        boolean correctNumber = false;
        int playerAmount = 1;
        int deckamount = 1;

        System.out.println("How many players?");

        while (!correctNumber) {
            String answer;
            try {
                answer = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                playerAmount = Integer.parseInt(answer);
                correctNumber = true;
            } catch (NumberFormatException e) {
                System.out.println("Not a number. How many players?");
            }
        }
        correctNumber = false;
        System.out.println("How many decks of cards?");
        while (!correctNumber) {
            String answer;
            try {
                answer = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                deckamount = Integer.parseInt(answer);
                correctNumber = true;
            } catch (NumberFormatException e) {
                System.out.println("Not a number. How many decks of cards?");
            }
        }

        game = new BlackJack(deckamount, playerAmount);

        for (int i = 0; i < playerAmount; i++) {
            System.out.println("Name of player " + i + "?");
            String name;
            try {
                name = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            game.setPlayerName(i, name);
        }
        startGame();
    }

    public void startGame() {
        game.startGame();
        while (game.getIsPlayerTurn()) {
            System.out.println(game.getPlayerName(game.getCurrentPlayer()) + " stand or hit?");
            String standOrHit;
            try {
                standOrHit = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (Objects.equals(standOrHit.toLowerCase(), "stand")) {
                game.stand();
            } else if (Objects.equals(standOrHit.toLowerCase(), "hit")) {
                game.hit();
            }
        }
    }
}
