import java.util.*;
public class Player {
    private ArrayList<Card> hand;
    private int balance, bet, score, wins;
    private boolean bust;
    private String username;
    public Player(String playerName) {
        hand = new ArrayList<Card>();
        username = playerName;
        score = 0;
        bet = 0;
        balance = 1000;
        bust = false;
        wins = 0;
    }
    public Card getCard(int index) {
        return hand.get(index);
    }
    public String getUsername() {
        return username;
    }
    public void bet(int amount) {
        bet = amount;
    }
    public int getBet() {
        return bet;
    }
    public int getBalance() {
        return balance;
    }
    public int getScore() {
        return score;
    }
    public String getScoreString() {
        if(score > 21) {
            bust = true;
            return "BUST";
        } else {
            return String.valueOf(score);
        }
    }
    public int getHandSize() {
        return hand.size();
    }
    public boolean checkBust() {
        return bust;
    }
    public int getWins() {
        return wins;
    }
    public void increaseWins() {
        wins++;
    }
    public void reset() {
        hand.clear();
        bet = 0;
        score = 0;
    }
    public void hit(Card c) {
        hand.add(c);
        setScore();
    }
    public void setScore() { // this is actually so mf ugly
        score = 0;
        for(int i = 0; i < hand.size(); i++){
            score += hand.get(i).getValue();
        }
        if(score > 21) {
            for(int i = 0; i < hand.size(); i++){
                Card a = hand.get(i);
                if(a.getRank().equals("A")){
                    a.demoteAce();
                }
            }
        }
        score = 0;
        for(int i = 0; i < hand.size(); i++){
            score += hand.get(i).getValue();
        }
    }
    public void updateBalance(int dealerScore) {
        if(checkBust()) {
            if(dealerScore <= 21) {
                balance -= bet;
            }
        } else {
            if(dealerScore > 21) {
                balance += bet;
            } else if (score > dealerScore) {
                balance += bet;
            } else {
                balance -= bet;
            }
        }
    }
    //print the player's hand as ascii art xd
    public void displayHand() { 
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
