import java.lang.Object;

public class Card {
	String rank, suit; 
	int value;
	public Card(String cardRank, String cardSuit) {
		rank = cardRank;		
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
		if(rank.matches(".*[A-Z].*")) {
			value = 10;
		} else if(rank.equals("A")) {
			value = 11;
		} else {
			value = Integer.parseInt(rank);
		}
		return value;
	}
}
