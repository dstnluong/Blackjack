public class Card {
	String name; String suit; int value;
	public Card(String cardName, String cardSuit, int cardValue) {
		name =  cardName;
        suit = cardSuit;
		value = cardValue;
	}
	public String getName() {
		return name;
	}
    public String getSuit(){
        return suit;
    }
	public int getValue() {
		return value;
	}
}
