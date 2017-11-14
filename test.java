public class test {
    public static void main (String [] args){
        Deck vegas = new Deck();
        vegas.shuffle();
        Player andrew = new Player("Andrew");
        andrew.hit(vegas.getCard(0));
        System.out.println(andrew.getCard(0).getRank() + andrew.getCard(0).getSuit());
        System.out.println(vegas.getCard(0).getRank() + vegas.getCard(0).getSuit());
    }
}
