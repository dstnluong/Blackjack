public class test {
    public static void main (String [] args){
        Deck vegas = new Deck();
        vegas.fillDeck();
        vegas.shuffle();
        for(int i = 0; i < vegas.size(); i++){
            System.out.print(vegas.getRank());
        }
    }
}
