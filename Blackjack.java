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
        boolean play = true;

        clearScreen();

        System.out.print("Number of players: ");
        int playerCount = in.nextInt();
        bj.addPlayers(playerCount);        

        newGame();

        System.out.printf("%n");
        while(play) { 
        	//bet
            System.out.printf("Betting Phase%n");
        	for(int i = 0; i < playerCount; i++) {
        		Player p = bj.getPlayer(i);
        		System.out.printf("%s's current balance: $%d%n", p.getUsername(), p.getBalance());
        		while(true) {
        			System.out.print("Bet: $");
        			int bet = Math.abs(in.nextInt());
        			if(bet > p.getBalance()) {
        				System.out.println("You don't have enough money.");
        			} else {
        				p.bet(bet);
        				break;
        			}
        		} 
        	}

            System.out.printf("%n");
        	//players' turns
        	for(int i = 0; i < playerCount; i++) {
        		boolean playerTurn = true;
        		displayGame();
        		Player p = bj.getPlayer(i);
        		while(playerTurn && p.getScore() < 21) { 
        			System.out.printf("%s's turn%n", p.getUsername());
        			System.out.println("Press [1] to hit.");
        			System.out.println("Press [2] to stand.");
        			if(p.getBet() <= p.getBalance()) {
        				System.out.println("Press [3] to double down.");
        			}
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
        				case 3:
        				hit(i);
        				p.bet(2*p.getBet());
        				playerTurn = false;
        				displayGame();
        				break;
        			}
        		}
        		System.out.printf("%n");
        	}

        	//dealer's turn 
        	dealer.revealCard();
        	dealer.hit(deck.draw());
        	dealerTurn();
        	displayGame();

        	//winner 
        	determineWinner();
            displayCurrentStandings();

            System.out.printf("%n%n");
            boolean options = true;
            while(options) {
            	System.out.println("[1] Replay");
            	System.out.println("[2] Add a player");
            	System.out.println("[3] Remove a player");
            	System.out.println("[4] Quit");
            	int input = in.nextInt();
            	switch(input) {
            		case 1:
            			options = false;
                        replay();
            			break;
            		case 2:
            			System.out.print("Player name: ");
            			bj.addPlayers(1);
            			break;
            		case 3:
            			System.out.print("");
            			break;
            		case 4:
            			System.out.println("Thanks for playing!");
                        options = false; 
            			play = false;
            			break;
            	}
            }
        }
    }
    public static void displayCurrentStandings(){
        for(int i = 0; i < bj.getPlayers().size(); i++){
            System.out.printf("%s:%17s", bj.getPlayer(i).getUsername(), "");     
        }

        System.out.printf("%n");
        for(int i = 0; i < bj.getPlayers().size(); i++){
            System.out.printf("Balance: %-9d", bj.getPlayer(i).getBalance());     
        }
        System.out.printf("%n");
        for(int i = 0; i < bj.getPlayers().size(); i++){
            System.out.printf("Wins: %-12d", bj.getPlayer(i).getWins());     
        }
        System.out.printf("%n");
        for(int i = 0; i < bj.getPlayers().size(); i++){
            System.out.printf("Loses: %-11d", bj.getGamesPlayed() - bj.getPlayer(i).getWins());     
        }
        System.out.printf("%n");

    }
    public static void displayGame() {
        clearScreen();
    	dealer.displayHand();
    	bj.displaySidebySide();
    }
    
    public static void newGame() {
        for(int i = 0; i < bj.getPlayers().size(); i++) {
            for(int j = 0; j < 2; j++) {
            	Player p = bj.getPlayers().get(i);
                p.hit(deck.draw()); 
            }
        }
        dealer.hit(deck.draw());
    }
    public static void clearScreen() {
    	System.out.print("\033[H\033[2J");  
    	System.out.flush(); 
        AsciiArt.printHeader();
    }
    public static void clrscr() {
    	for(int i = 0; i <= 56; i++) {
    		System.out.println();
    	}
	}
    public static void hit(int playerIndex) {
        Card c = deck.draw();
        bj.getPlayer(playerIndex).hit(c);
        bj.getPlayer(playerIndex).setScore();
    } 
    public static void dealerTurn() {
        while(dealer.getScore() < 17) {
            dealer.hit(deck.draw());
            dealer.setScore();
        }
    }
    public static void determineWinner() {
    	int highscore = dealer.getScore();
    	boolean dealerWin = true;
        for(int i = 0; i < bj.getPlayers().size(); i++) {
        	if(bj.getPlayer(i).getScore() >= highscore && !bj.getPlayer(i).checkBust()) {
        		highscore = bj.getPlayer(i).getScore();
        		dealerWin = false;
        	}
        }
        System.out.println("WINNER: ");
        if(dealerWin) {
        	System.out.println("Dealer");
        } else {
        	if(dealer.getScore() == highscore) {
        		System.out.println("Dealer");
                for(int i = 0; i < bj.getPlayers().size(); i++){
                    Player p = bj.getPlayer(i);
                    p.betLose();
                }
        	}
        	for(int i = 0; i < bj.getPlayers().size(); i++) {
                Player p = bj.getPlayer(i);
        		if(p.getScore() == highscore) {
        			System.out.println(p.getUsername());
                    p.betWin();
                    p.increaseWins(); 
                } else {
                    p.betLose();
                }
        	}
        }
        bj.increaseGamesPlayed();
        System.out.printf("%n");
    }
    public static void addPlayers(){
        
    }
    /*
    public static void updateBalance() {
    	for(int i = 0; i < bj.getPlayers().size(); i++) {

    	}
    }
    */
    public static void replay() {
        for(int i = 0; i < bj.getPlayers().size(); i++) {
            bj.getPlayer(i).clearHand();
        }
        dealer.clearHand();
        deck.resetDeck();
        clearScreen();
    }
    
}
