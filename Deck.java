import java.util.*;

public class Deck {
    ArrayList<Card> deck;
    String[] rank, suit;
    int drawCounter;
    public Deck() {
        deck = new ArrayList<Card>();
        drawCounter = -1;
        fillDeck();
        shuffle();
    }
    public void fillDeck(){
        rank = new String []{"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    	suit = new String []{"\u2665","\u2666","\u2663","\u2660"};
        for(int i = 0; i < 13; i++) {
        	for(int j = 0; j < 4; j++) {
        		Card c = new Card(rank[i], suit[j]);
        		deck.add(c);
        	}
        }
    }
    public void shuffle() {
    	Collections.shuffle(deck);
    }
    public void increaseDrawCounter(){
        drawCounter++;
    }
    public Card getCard(){
        increaseDrawCounter();
        return deck.get(drawCounter);
    }
    /*
    public String getRank(int index) {
    	return deck.get(index).getRank();
    }
    public String getSuit(int index) {
    	return deck.get(index).getSuit();
    }
    public int getValue(int index) {
    	return deck.get(index).getValue();
    }
    */
}
