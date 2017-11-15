import java.util.*;

public class test {
    public static void main (String [] args){
        Scanner in = new Scanner(System.in);
        Game blackjack = new Game();
        Deck vegas = new Deck();
        vegas.shuffle();

        System.out.print("How many players? ");
        int numPlayers = in.nextInt();
        for(int i = 1; i <= numPlayers; i++) {
            System.out.print("Player " + i + " name: ");
            Player p = new Player(in.next());
            blackjack.addPlayer(p);
        }
        System.out.println();

        for(int i = 0; i < numPlayers; i++){
            blackjack.getPlayer(i).hit(vegas.getCard(2*i));
            blackjack.getPlayer(i).hit(vegas.getCard(2*i + 1));
            System.out.println("Player: " + blackjack.getPlayer(i).getUsername());
            blackjack.getPlayer(i).displayHand();
        }

        Dealer dustin = new Dealer();
        Player andrew = new Player("Andrew");
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
