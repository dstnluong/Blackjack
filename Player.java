import java.util.ArrayList;

public class Player {
    private ArrayList<Card> hand;
    private String username;
    private int balance, bet, score, wins, loses, draws;
    private boolean bust;
    public Player(String playerName) {
        hand = new ArrayList<Card>();
        username = playerName;
        balance = 1000;
        bet = 0;
        score = 0;
        wins = 0;
        loses = 0;
        draws = 0;
        bust = false;
    }
    public Card getCard(int index) {
        return hand.get(index);
    }
    public String getUsername() {
        return username;
    }
    public int getBalance() {
        return balance;
    }
    public void bet(int amount) {
        bet = amount;
    }
    public int getBet() {
        return bet;
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
    public void setScore() { 
        score = 0;
        for(int i = 0; i < hand.size(); i++) { //check initial score
            score += hand.get(i).getValue();
        }
        if(score > 21) {
            for(int i = 0; i < hand.size(); i++) { //if 21 demote aces if possible
                Card a = hand.get(i);
                if(a.getRank().equals("A") && a.getValue() == 11) {
                    a.demoteAce();
                    break;
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
    //update wins, loses, and draws based on money differences
    public void updateStandings(int moneyWon) { 
        if(moneyWon > 0) { 
            wins++;
        } else if(moneyWon < 0) {
            loses++;
        } else if(moneyWon == 0) {
            draws++;
        }
    }
}