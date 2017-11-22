import java.util.*;

public class Dealer {
    ArrayList<Card> hand;
    private int score;
    private String username;
    private boolean hidecard;
    public Dealer() {
        hand = new ArrayList<Card>();
        username = "Dealer";
        score = 0;
        hidecard = true;
    }
    public String getUsername(){
        return username;
    }
    public String getScoreString() {
        if(score > 21) {
            return "BUST";
        } else {
            return String.valueOf(score);
        }
    }
    public int getHandSize(){
        return hand.size();
    }
    public int getScore() {
        return score;
    }
    public void hit(Card c) {
        hidecard = true;
        hand.add(c);
        setScore();
    }
    public void setScore(){ // this is actually so mf ugly
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
    public void revealCard() {
        hidecard = false;
    }
    public void reset() {
        hidecard = true;
        hand.clear();
        score = 0;
    } 
    public void displayHand() { //print the player's hand as ascii art xd
        System.out.printf("%n%s%n", username);
        String border = "+-----+  ";
        String cardback = "+++++++  ";

        if(hidecard) {
            System.out.print(border);
        }
        for(int j = 0; j < hand.size(); j++) {
             System.out.print(border);
        }
        System.out.print("\n");

        if(hidecard) {
            System.out.print(cardback);
        }
        for(int j = 0; j < hand.size(); j++) {
            System.out.printf("|%-5s|  ", hand.get(j).getRank());
        }
        System.out.print("\n");

        if(hidecard){
            System.out.print(cardback);
        } 
        for(int j = 0; j < hand.size(); j++) {
            System.out.printf("|  %s  |  ", hand.get(j).getSuit());
        }
        System.out.print("\n");

        if(hidecard){
            System.out.print(cardback);
        }
        for(int j = 0; j < hand.size(); j++) {
            System.out.printf("|%5s|  ", hand.get(j).getRank());
        }
        System.out.print("\n");

        if(hidecard){
            System.out.print(border);
        }
        for(int j = 0; j < hand.size(); j++) {
            System.out.print(border);
        }
        System.out.printf("%n");

        System.out.printf("Score: %-11s%n%n", getScoreString());
    }
}
