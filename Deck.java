import java.util.*; 
public class Deck {
    ArrayList<Card> deck;
    private String[] rank, suit;
    private int drawCounter;
    public Deck() {
        deck = new ArrayList<Card>();
        drawCounter = -1;
        fillDeck(); //fill deck
        shuffle(); //randomize cards
    }
    public void fillDeck() { //fill deck with 52 cards
        rank = new String []{"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    	suit = new String []{"\u2665","\u2666","\u2663","\u2660"};
        for(int i = 0; i < 13; i++) {
        	for(int j = 0; j < 4; j++) {
        		Card c = new Card(rank[i], suit[j]);
                c.setValue();
        		deck.add(c);
        	}
        }
    }
    public void shuffle() { //randomize deck
    	Collections.shuffle(deck);
    }
    public Card draw() { //return a card and move draw index
        //incrementDrawCounter();
        drawCounter++;
        return deck.get(drawCounter);
    }
    public void resetDeck() { //reset draw counter and reshuffle
        drawCounter = -1;
        shuffle();
    }
}
