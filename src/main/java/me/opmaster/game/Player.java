package me.opmaster.game;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> hand = new ArrayList<>();

    public void draw(Card card) {
        hand.add(card);
    }

    public void clearHand() {
        hand.removeAll(hand);
    }

    public int getHandValue() {
        int totalValue = 0;
        int amountOfAces = 0;
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getCardValue() == 11) {
                amountOfAces += 1;
            }
            totalValue += hand.get(i).getCardValue();
        }
        if (totalValue <= 21) {
            return totalValue;
        }
        while (0 < amountOfAces) {
            totalValue -= 10;
            amountOfAces -= 1;
            if (totalValue <= 21) {
                return totalValue;
            }
        }
        return totalValue;
    }


    public String toString() {
        return name + hand.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
