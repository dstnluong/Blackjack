import java.util.*;

public class Dealer {
    ArrayList<Card> hand;
    private int points;
    private String username;
    private boolean hidecard;
    public Dealer() {
        hand = new ArrayList<Card>();
        username = "Dealer";
        points = 0;
        hidecard = true;
    }
    public String getUsername(){
        return username;
    }
    public int getPoints() {
        return points;
    }
    public void hit(Card c) {
        hand.add(c);
    }
    public void revealCard(Card c) {
        hand.add(0, c);
        hidecard = false;
    }
    public void clearHand() {
        hand.clear();
    } 
    public void displayHand() { //print the player's hand as ascii art xd
        System.out.printf("%n");
        System.out.printf("%s%n", username);
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
            System.out.printf("|%-2s   |  ", hand.get(j).getRank());
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
            System.out.printf("|   %2s|  ", hand.get(j).getRank());
        }
        System.out.print("\n");

        if(hidecard){
            System.out.print(border);
        }
        for(int j = 0; j < hand.size(); j++) {
            System.out.print(border);
        }
        System.out.println();
    }
}
