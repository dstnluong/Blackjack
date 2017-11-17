import java.util.*;

public class Dealer {
    ArrayList<Card> hand;
    private int points;
    private String username;
    private boolean hidecard;
    public Dealer(){
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
    public void revealCard(){
        hidecard = false;
    }
    public void clearHand(){
        hand.clear();
    } 
    public void displayHand(){ //print the player's hand as ascii art xd
        String border = "+-----+  ";
        String cardback = "+++++++  ";

        if(hidecard){
            System.out.print(border);
        }
        for(int j = 0; j < hand.size(); j++){
             System.out.print(border);
        }

        System.out.print("\n");
        if(hidecard) {
            System.out.print(cardback);
        }
        for(int j = 0; j < hand.size(); j++){
            String padding = " ";
            if(hand.get(j).getRank().length() == 2){ //account for "10" having two digits
                padding = "";
            }
            System.out.print("|" + hand.get(j).getRank()+ padding + "   |  ");
        }
        System.out.print("\n");
        if(hidecard){
            System.out.print(cardback);
        } 
        for(int j = 0; j < hand.size(); j++){
            System.out.print("|  " +  hand.get(j).getSuit() + "  |  ");
        }
        System.out.print("\n");
        if(hidecard){
            System.out.print(cardback);
        }
        for(int j = 0; j < hand.size(); j++){
            String padding = " ";
            if(hand.get(j).getRank().length() == 2){ //account for "10" having two digits
                padding = "";
            }
            System.out.print("|   " + padding + hand.get(j).getRank() + "|  ");
        }
        System.out.print("\n");
        if(hidecard){
            System.out.print(border);
        }
        for(int j = 0; j < hand.size(); j++){
            System.out.print(border);
        }
        System.out.println();
    }
}
