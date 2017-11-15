public class test {
    public static void main (String [] args){
        Deck vegas = new Deck();
        vegas.shuffle();
        Player andrew = new Player("Andrew");
        System.out.println("Player: " + andrew.getUsername());
        for(int i = 0; i < 3; i++){
            andrew.hit(vegas.getCard(i));
        }
        andrew.displayHand();
    }
}
