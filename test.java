public class test {
    public static void main (String [] args){
        Deck vegas = new Deck();
        vegas.fillDeck();
        vegas.shuffle();
        for(int i = 0; i < 52; i++){
            System.out.println(i + " "+ vegas.getRank(i) + vegas.getSuit(i));
        }
    }
}
