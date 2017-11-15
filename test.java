public class test {
    public static void main (String [] args){
        Deck vegas = new Deck();
        vegas.shuffle();
        Player andrew = new Player("Andrew");
        Dealer dustin = new Dealer();
        for(int i = 0; i < 3; i++){
            andrew.hit(vegas.getCard(i));
            dustin.hit(vegas.getCard(i + 5));
        }
        System.out.println(dustin.getUsername());
        dustin.displayHand(true);
        System.out.println("Player: " + andrew.getUsername());
        andrew.displayHand();
    }
}
