import java.util.*;

public class Player {
    ArrayList<Card> hand;
    int points, balance, bet;
    String username;
    public Player(String playerName) {
        hand = new ArrayList<Card>();
        username = playerName;
        points = 0;
        bet = 0;
        balance = 1000;
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
    public void bet(int amount) {
   		if(amount > balance) {
   			System.out.print("You don't have enough money.");
   		} else {
   			bet = amount;
    	}
    }
    public Card getCard(int index){
        return hand.get(index);
    }
    public void displayHand(){ //print the player's hand as ascii art xd
        String border = "+-----+  ";
        for(int j = 0; j < hand.size(); j++){
             System.out.print(border);
        }
        System.out.println("");
        for(int j = 0; j < hand.size(); j++){
            String padding = " ";
            if(hand.get(j).getRank().length() == 2){ //account for "10" having two digits
                padding = "";
            }
            System.out.print("|" + hand.get(j).getRank()+ padding + "   |  ");
        }
        System.out.println("");
        for(int j = 0; j < hand.size(); j++){
            System.out.print("|  " +  hand.get(j).getSuit() + "  |  ");
        }
        System.out.println("");
        for(int j = 0; j < hand.size(); j++){
            String padding = " ";
            if(hand.get(j).getRank().length() == 2){ //account for "10" having two digits
                padding = "";
            }
            System.out.print("|   " + padding + hand.get(j).getRank() + "|  ");
        }
        System.out.println("");
        for(int j = 0; j < hand.size(); j++){
            System.out.print(border);
        }
        System.out.println("");
    }
}
