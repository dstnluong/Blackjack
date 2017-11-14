import java.util.*;

public class Game {
	public static void main(String[] args) {
        Deck vegas = new Deck();
        Scanner in = new Scanner(System.in);
       	System.out.print("How many players? ");
       	int players = in.nextInt();
        for(int i = 1; i <= players; i++) {
        	System.out.print("PLAYER " + i + ": ");
        	Player a = new Player(in.next());
        }
   	}
}
