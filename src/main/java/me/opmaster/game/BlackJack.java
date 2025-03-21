package me.opmaster.game;

import java.util.ArrayList;
import java.util.List;

public class BlackJack {
    private Deck deck = new Deck();
    private Player dealer = new Player();
    private List<Player> players = new ArrayList<>();
    private int currentPlayer = 0;
    private boolean gameInProgress = false;
    private int amountOfDecks;
    private boolean isPlayerTurn = false;

    public BlackJack(int amountOfDecks, int amountOfPlayers) {
        dealer.setName("Dealer");
        this.amountOfDecks = amountOfDecks;
        deck.initialize(amountOfDecks, true);
        for (int i = 0; i < amountOfPlayers; i++) {
            players.add(new Player());
        }
    }

    private void nextTurn() {
        if (currentPlayer < players.size() - 1) {
            currentPlayer++;
            System.out.println(players.get(currentPlayer).toString());
            return;
        }
        isPlayerTurn = false;
        dealersTurn();
    }

    public void startGame() {
        System.out.println("New Game started.");
        if (gameInProgress) {
            return;
        }
        gameInProgress = true;
        for (int i = 0; i < players.size(); i++) {
            players.get(i).draw(deck.draw());
            players.get(i).draw(deck.draw());
            System.out.println(players.get(i).toString());
        }
        dealer.draw(deck.draw());
        System.out.println(dealer.toString());
        currentPlayer = 0;
        isPlayerTurn = true;
        System.out.println(players.get(currentPlayer).toString());
    }

    private void dealersTurn() {
        while (dealer.getHandValue() < 17) {
            dealer.draw(deck.draw());
        }
        System.out.println(dealer.toString());
        evaluation();
    }

    private void evaluation() {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getHandValue() > dealer.getHandValue() && players.get(i).getHandValue() <=21) {
                System.out.println(players.get(i).getName() + " won.");
            } else if(players.get(i).getHandValue() <= 21 && dealer.getHandValue() > 21) {
                System.out.println(players.get(i).getName() + " won.");
            } else {
                System.out.println(players.get(i).getName() + " lost.");
            }
        }
    }

    private void endGame() {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).clearHand();
        }
        dealer.clearHand();
        deck.initialize(amountOfDecks, true);
        gameInProgress = false;
    }

    public void hit() {
        players.get(currentPlayer).draw(deck.draw());
        if (players.get(currentPlayer).getHandValue() > 21) {
            System.out.println(players.get(currentPlayer).toString());
            nextTurn();
        } else System.out.println(players.get(currentPlayer).toString());
    }

    public void stand() {
        nextTurn();
    }

    /*public void doubleDown() {

    }*/

    /*public void split() {

    }*/

    public void setPlayerName(int player, String name) {
        players.get(player).setName(name);
    }

    public String getPlayerName(int player) {
        return players.get(player).getName();
    }

    public boolean getIsPlayerTurn() {
        return isPlayerTurn;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }
}
