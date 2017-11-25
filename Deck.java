import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> deck;
    private String[] rank, suit;
    private int drawCounter;
    public Deck() {
        deck = new ArrayList<Card>();
        drawCounter = -1;
        fillDeck(); //fill deck
        shuffle(); //shuffle cards
    }
    //fill deck with 52 cards
    public void fillDeck() { 
        rank = new String[] {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    	suit = new String[] {"\u2665","\u2666","\u2663","\u2660"};
        for(int i = 0; i < 13; i++) {
        	for(int j = 0; j < 4; j++) {
        		Card c = new Card(rank[i], suit[j]);
                c.setValue();
        		deck.add(c);
        	}
        }
    }
    //randomize card positions in deck
    public void shuffle() { 
    	Collections.shuffle(deck);
    }
    //return a card and move draw index
    public Card draw() { 
        drawCounter++; 
        return deck.get(drawCounter);
    }
    //reset draw counter and reshuffle
    public void resetDeck() { 
        drawCounter = -1;
        shuffle();
    }
}