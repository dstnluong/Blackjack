public class Card {
	String rank, suit; 
	int value;
	public Card(String cardRank, String cardSuit, int cardValue) {
		rank =  cardRank;
        suit = cardSuit;
		value = cardValue;
	}
	public String getRank() {
		return rank;
	}
    public String getSuit(){
        return suit;
    }
	public int getValue() {
		return value;
	}
}
