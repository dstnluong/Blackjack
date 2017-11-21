import java.util.*;
import java.lang.*;
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

        clearScreen();
        //AsciiArt.printHeader();

        System.out.print("Number of players: ");
        int playerCount = in.nextInt();
        for(int i = 0; i < playerCount; i++) {
            System.out.printf("Player %d's name: ", i + 1);
        	bj.addPlayer(in.next());
        }
        newGame();

        for(int i = 0; i < bj.players.size(); i++) {
        	boolean playerTurn = true;
            bj.displayPlayer(i);
        	while(playerTurn && !busted(i)) { 
        		System.out.printf("%s's turn%n", bj.getPlayer(i).getUsername());
        		System.out.println("Press [1] to hit.");
        		System.out.println("Press [2] to stand.");
        		int input = in.nextInt();
        		switch(input) {
        			case 1: 
                        hit(i);
                        displayGame();
        				break;
        			case 2: 
                        playerTurn = false;
                        displayGame();
        				break;
        		}
        	}
            System.out.printf("%n");
        }

        dealer.revealCard();
        dealer.hit(deck.draw());
        displayGame();
        dealerTurn();
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
        clearScreen();
        AsciiArt.printHeader();
    	dealer.displayHand();
    	bj.displaySidebySide();
    }
    
    public static void newGame() {
        for(int i = 0; i < bj.players.size(); i++) {
            for(int j = 0; j < 2; j++) {
            	Player temp = bj.getPlayers().get(i);
                temp.hit(deck.draw()); 
            }
        }
        dealer.hit(deck.draw());
        //dealer.displayHand();
        //bj.displaySidebySide();
    }
    public static void clearScreen() {
    	System.out.print("\033[H\033[2J");  
    	System.out.flush(); 
    }
    public static void clrscr() {
    	for(int i = 0; i <= 56; i++) {
    		System.out.println();
    	}
	}
    public static boolean busted(int playerIndex) {
        return bj.getPlayer(playerIndex).getScore() > 21;
    }
    public static void hit(int playerIndex) {
        Card c = deck.draw();
        bj.getPlayer(playerIndex).hit(c);
        bj.getPlayer(playerIndex).setScore();
    } 
    public static void dealerTurn() {
        while(dealer.getScore() < 17){
            dealer.hit(deck.draw());
            dealer.setScore();
            displayGame();
        }
    }
    /*
    public static String[] getWinners(){
        bj.getPlayers();

        String [] winners = new String[bj.numOfPlayers()];

        int highestScore = 0;
        for(int i = 0; i < bj.numOfPlayers();i++){
            int String = bj.getPlayer(i);
            if(players(i).getScore().matches("\\d")){
                int playerScore = String.valueOf(players(i).getScore());
                highestScore = Math.max(highestScore, playerScore);
            }
        }
    }
    */
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
