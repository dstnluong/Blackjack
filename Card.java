public class Card {
	String rank, suit; 
	int value;
	public Card(String cardRank, String cardSuit) {
		rank =  cardRank;
		suit = cardSuit;
		value = 0;
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
