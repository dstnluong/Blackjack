import java.util.*;
public class Player {
    private ArrayList<Card> hand;
    private int balance, bet, score, wins, loses, draws;
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
        loses = 0;
        draws = 0;
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
    public int getLoses() {
        return loses;
    }
    public int getDraws() {
        return draws;
    }
    public void setBalance(int newBalance) {
        balance = newBalance;
    }
    public void reset() {
        hand.clear();
        bet = 0;
        score = 0;
        bust = false;
    }
    public void hit(Card c) {
        hand.add(c);
        setScore();
    }
    public void setScore() { // this is actually so mf ugly
        score = 0;
        for(int i = 0; i < hand.size(); i++) { //chck initial score
            score += hand.get(i).getValue();
        }
        if(score > 21) {
            for(int i = 0; i < hand.size(); i++) { //if 21 demote aces if possible
                Card a = hand.get(i);
                if(a.getRank().equals("A")) {
                    a.demoteAce();
                }
            }
        }
        score = 0;
        for(int i = 0; i < hand.size(); i++) { // check score again
            score += hand.get(i).getValue();
        }
        if(score > 21) {
            bust = true;
        } 
    }
    public void updateStandings(int moneyWon) { //update wins, loses, and draws based on money diff
        if(moneyWon > 0) { 
            wins++;
        } else if(moneyWon < 0) {
            loses++;
        } else if(moneyWon == 0) {
            draws++;
        }
    }
}