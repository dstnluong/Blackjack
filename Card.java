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
		if(rank.matches("\\d")) { //checks if the rank is a digit
			value = Integer.parseInt(rank);
		} else if(rank.equals("A")) {
			value = 11;
		} else {
			value = 10;
		}
		return value;
	}
}
