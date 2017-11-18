import java.util.*;

public class Player {
    ArrayList<Card> hand;
    private int balance, bet, score;
    private String username;
    public Player(String playerName) {
        hand = new ArrayList<Card>();
        username = playerName;
        score = 0;
        bet = 0;
        balance = 1000;
    }
    public String getUsername() {
        return username;
    }
    public String getScore() {
        if(score > 21) {
            return "BUST";
        } else {
            return String.valueOf(score);
        }
    }
    public Card getCard(int index) {
        return hand.get(index);
    }
    public void clearHand() {
        hand.clear();
    }
    public void hit(Card c) {
        hand.add(c);
        score += c.getValue();
    }
    public void bet(int amount) {
        if(amount > balance) {
            System.out.print("You don't have enough money.");
        } else {
            bet = amount;
        }
    }
    public boolean checkBust() {
        return(score > 21);
    }
    public void displayHand() { //print the player's hand as ascii art xd
        String border = "+-----+  ";
        for(int j = 0; j < hand.size(); j++) {
            System.out.print(border);
        }
        System.out.printf("%n");
        for(int j = 0; j < hand.size(); j++) {
            System.out.printf("|%-2s   |  ", hand.get(j).getRank());
        }
        System.out.printf("%n");
        for(int j = 0; j < hand.size(); j++) {
            System.out.printf("|  %s  |  ", hand.get(j).getSuit());
        }
        System.out.printf("%n");
        for(int j = 0; j < hand.size(); j++) {
            System.out.printf("|   %2s|  " ,hand.get(j).getRank());
        }
        System.out.printf("%n");
        for(int j = 0; j < hand.size(); j++) {
            System.out.print(border);
        }
        System.out.printf("%n");
    }
}
