import java.util.*;

public class Dealer {
    ArrayList<Card> hand;
    private int score;
    private String username;
    private boolean hidecard, bust;
    public Dealer() {
        hand = new ArrayList<Card>();
        username = "Dealer";
        score = 0;
        hidecard = true;
        bust = false;
    }
    public String getUsername() {
        return username;
    }
    public int getScore() {
        return score;
    }
    public boolean checkBust() {
        return bust;
    }
    public String getScoreString() { //return score to print
        if(score > 21) {
            bust = true;
            return "BUST";
        } else {
            return String.valueOf(score);
        }
    }
    public void hit(Card c) { // add card and update score
        hand.add(c);
        setScore();
    }
    public int getHandSize() {
        return hand.size();
    }
    public void setScore() { // this is actually so mf ugly
        score = 0;
        for(int i = 0; i < hand.size(); i++) { //get initial score
            score += hand.get(i).getValue();
        }
        if(score > 21) {
            for(int i = 0; i < hand.size(); i++) { //if score if over 21 demote ace if possible
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
    }
    public void revealCard() { //remove cardback
        hidecard = false;
    }
    public void reset() { //add cardback, clear hand, reset score
        hidecard = true;
        hand.clear();
        score = 0;
        bust = false;
    } 
    //print the player's hand as ascii art xd
    public void displayHand() { 
        System.out.printf("%s%n", username);
        String border = "+-----+  ";
        String cardback = "+++++++  ";

        if(hidecard) {
            System.out.print(border);
        }
        for(int j = 0; j < hand.size(); j++) { // border
             System.out.print(border);
        }
        System.out.print("\n");

        if(hidecard) {
            System.out.print(cardback);
        }
        for(int j = 0; j < hand.size(); j++) { //rank
            System.out.printf("|%-5s|  ", hand.get(j).getRank());
        }
        System.out.print("\n");

        if(hidecard) {
            System.out.print(cardback);
        } 
        for(int j = 0; j < hand.size(); j++) { //suit
            System.out.printf("|  %s  |  ", hand.get(j).getSuit());
        }
        System.out.print("\n");

        if(hidecard) {
            System.out.print(cardback);
        }
        for(int j = 0; j < hand.size(); j++) { //rank
            System.out.printf("|%5s|  ", hand.get(j).getRank());
        }
        System.out.print("\n");

        if(hidecard) {
            System.out.print(border);
        }
        for(int j = 0; j < hand.size(); j++) { // border
            System.out.print(border);
        }
        System.out.printf("%n");

        System.out.printf("Score: %-11s%n%n", getScoreString());
    }
}
