import java.util.*;

public class Dealer {
    ArrayList<Card> hand;
    int points;
    String username;
    public Dealer(){
        hand = new ArrayList<Card>();
        username = "Dealer";
        points = 0;
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
    public void displayHand(){ //print the player's hand as ascii art xd
        String border = "+-----+  ";
        String cardback = "+++++++  ";

        System.out.print(border);
        for(int j = 0; j < hand.size(); j++){
             System.out.print(border);
        }

        System.out.println(cardback);
        for(int j = 0; j < hand.size(); j++){
            String padding = " ";
            if(hand.get(j).getRank().length() == 2){ //account for "10" having two digits
                padding = "";
            }
            System.out.print("|" + hand.get(j).getRank()+ padding + "   |  ");
        }

        System.out.println(cardback);
        for(int j = 0; j < hand.size(); j++){
            System.out.print("|  " +  hand.get(j).getSuit() + "  |  ");
        }

        System.out.println(cardback);
        for(int j = 0; j < hand.size(); j++){
            String padding = " ";
            if(hand.get(j).getRank().length() == 2){ //account for "10" having two digits
                padding = "";
            }
            System.out.print("|   " + padding + hand.get(j).getRank() + "|  ");
        }
        
        System.out.println(cardback);
        for(int j = 0; j < hand.size(); j++){
            System.out.print(border);
        }
        System.out.println("");
    }
}
