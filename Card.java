public class Card {
	private String rank, suit; 
	private int value;

	public Card(String cardRank, String cardSuit) {
		rank = cardRank;		
		suit = cardSuit;
		value = 0;
	}
	public String getRank() {
		return rank;
	}
	public String getSuit() {
		return suit;
	}
	public int getValue() {
		return value;
	}
	//assign value to card
	public void setValue() { 
		if(rank.matches("\\d")) { //checks if the rank is a digit
		    value = Integer.parseInt(rank);
		} else if(rank.equals("A")) {
			value = 11;
		} else {
			value = 10;
		}
	}
	//reduce ace value from 11 to 1
	public void demoteAce() { 
		value = 1;
	}
    public void promoteAce(){
        value = 11;
    }
}
