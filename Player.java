import java.util.*;

public class Player {
    ArrayList<Card> hand;
    int points, balance, bet;
    public Player() {
        hand = new ArrayList<Card>();
        points = 0;
        bet = 0;
        balance = 1000;
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
}
