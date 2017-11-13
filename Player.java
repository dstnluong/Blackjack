import java.util.*;

public class Player {
    ArrayList<Card> hand;
    int points;
    public Player() {
        hand = new ArrayList<Card>();
        points = 0;
    }
    public int getPoints() {
        return points;
    }
    public void hit(Card c) {
    	hand.add(c);
    }
}
