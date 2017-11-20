import java.util.*;

public class Blackjack {
        private static Game bj;
        private static Deck deck;
        private static Dealer dealer;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        bj = new Game();
        deck = new Deck();
        dealer = new Dealer(); 
        boolean playing = true;
        //while(playing) { 
        System.out.print("Number of players: ");
        int playerCount = in.nextInt();
        for(int i = 0; i < playerCount; i++) {
            System.out.printf("Player %d's name: ", i + 1);
        	bj.addPlayer(in.next());
        }
        newGame();

        for(int i = 0; i < playerCount; i++) {
        	boolean playerTurn = true;
        	while(playerTurn) { 
        		System.out.printf("%s's turn%n", bj.getPlayer(i).getUsername());
        		System.out.println("Press [1] to hit.");
        		System.out.println("Press [2] to stand.");
        		int input = in.nextInt();
        		switch(input) {
        			case 1: 
        				System.out.println("temp1");
        				break;
        			case 2: 
        				System.out.println("temp2");
        				break;
        		}
        	}
        }
            /*
            System.out.println("1.New Game");
            System.out.println("2.Quit");
            int userInput = in.nextInt();
            switch(userInput){
                case(1):
                case(2):
                    System.out.println("Thanks for playing!");
                    playing = false;
            }
            */
        //}
    }
    public static void displayGame() {
    	dealer.displayHand();
    	bj.displaySidebySide();
    }
    public static void newGame() {
        for(int i = 0; i < bj.getPlayers().size(); i++) {
            for(int j = 0; j < 2; j++) {
            	Player temp = bj.getPlayers().get(i);
                temp.hit(deck.draw()); 
            }
        }
        dealer.hit(deck.draw());
        dealer.displayHand();
        bj.displaySidebySide();
    }
    
    /*
    public void replay() {
        for(int i = 0; i < players.size(); i++) {
            players.get(i).clearHand();
        }
        dealer.clearHand();
        deck.resetDeck();
        newGame();
    }
    */
    
}
