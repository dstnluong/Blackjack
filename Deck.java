import java.util.*;

public class Deck {
    ArrayList<Card> deck;
    String[] rank, suit;
    public Deck() {
    	rank = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    	suit = {"\u2665","\u2666","\u2663","\u2660"};
        deck = new ArrayList<Card>();
        for(int i = 0; i < 13; i++) {
        	for(int j = 0; j < 4; j++) {
        		Card c = new Card(rank[i], suit[j]);
        		deck.addCard(c);
        	}
        }
    }
    public void shuffle() {
    	Collections.shuffle(deck);
    }
    public Card getCard(int index) {
    	return deck.get(index);
    }
}
