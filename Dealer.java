import java.util.ArrayList;

public class Dealer {
    private ArrayList<Card> hand;
    private int score;
    private boolean hidecard, bust;
    public Dealer() {
        hand = new ArrayList<Card>();
        score = 0;
        hidecard = true;
        bust = false;
    }
    public int getScore() {
        return score;
    }
    public boolean checkBust() {
        return bust;
    }
    //return score to print
    public String getScoreString() { 
        if(score > 21) {
            bust = true;
            return "BUST";
        } else {
            return String.valueOf(score);
        }
    }
    // add card and update score
    public void hit(Card c) { 
        hand.add(c);
        setScore();
    }
    public int getHandSize() {
        return hand.size();
    }
    public void setScore() { 
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
    //remove cardback
    public void revealCard() { 
        hidecard = false;
    }
    //add cardback, clear hand, reset score
    public void reset() { 
        hidecard = true;
        hand.clear();
        score = 0;
        bust = false;
    } 
    //print the player's hand as ascii art xd
    public void displayHand() { 
        System.out.println("Dealer");
        String border = "+-----+  ";
        String cardback = "+++++++  ";

        for(int i = 0; i < hand.size(); i++) { // border
             System.out.print(border);
        }
        if(hidecard) {
            System.out.print(border);
        }
        System.out.print("\n");

        for(int i = 0; i < hand.size(); i++) { //rank
            System.out.printf("|%-5s|  ", hand.get(i).getRank());
        }
        if(hidecard) {
            System.out.print(cardback);
        }
        System.out.print("\n");
 
        for(int i = 0; i < hand.size(); i++) { //suit
            System.out.printf("|  %s  |  ", hand.get(i).getSuit());
        }
        if(hidecard) {
            System.out.print(cardback);
        } 
        System.out.print("\n");

        for(int i = 0; i < hand.size(); i++) { //rank
            System.out.printf("|%5s|  ", hand.get(i).getRank());
        }
        if(hidecard) {
            System.out.print(cardback);
        }
        System.out.print("\n");

        for(int i = 0; i < hand.size(); i++) { // border
            System.out.print(border);
        }
        if(hidecard) {
            System.out.print(border);
        }
        System.out.printf("%n");

        System.out.printf("Score: %-11s%n%n", getScoreString());
    }
}