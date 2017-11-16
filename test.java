import java.util.*;

public class test {
    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);
        Game blackjack = new Game();
        Deck vegas = new Deck();
        Dealer larry = new Dealer();

        //determines # of players and adds them to arraylist of players
        System.out.print("How many players? ");
        int numPlayers = in.nextInt();
        for(int i = 1; i <= numPlayers; i++) {
            System.out.print("Player " + i + " name: ");
            Player p = new Player(in.next());
            blackjack.addPlayer(p);
        }
        System.out.println();

        //dealer hits
        larry.hit(vegas.getCard()); 
        larry.hit(vegas.getCard());
        //print dealer hand
        System.out.println(larry.getUsername());
        larry.displayHand(false);
        System.out.println(larry.getUsername());
        larry.displayHand(true);

        //tentative code; just gives each player two random cards from the beginning of the deck
        for(int i = 0; i < numPlayers; i++) {
            Player temp = blackjack.getPlayer(i);
            temp.hit(vegas.getCard());
            temp.addScore(vegas.getCard().getValue());
            temp.hit(vegas.getCard());
            temp.addScore(vegas.getCard().getValue());
            System.out.println("Player: " + temp.getUsername());
            temp.displayHand();
            System.out.println("Score: " + temp.getScore());
        }

        /*
        //dealer + player code
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
        */
    }
}
