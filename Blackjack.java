import java.util.*;
import java.lang.*;
public class Blackjack {

    private static Game bj;
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        bj = new Game();
        boolean play = true;
        boolean quit = true;

        bj.clearScreen();
        System.out.printf("Welcome to Blackjack!%n");

        while(quit) {    
            System.out.printf("[1] New Game%n");
            System.out.printf("[2] Quit%n");
            int choice = in.nextInt();
            switch(choice) {
                case 1: 
                    bj.clearScreen();
                    System.out.printf("Number of players: "); //prompt for players
                    int playerCount = in.nextInt();
                    if(playerCount > 0) {
                        bj.addPlayers(playerCount);
                    } else {
                        System.out.printf("Need more players.%n%n");
                        play = false;
                    }

                    bj.newGame();
                    while(play) { 
                        bj.clearScreen();
                        
                        //bet
                        System.out.printf("Betting Phase%n%n"); //place bets
                    	for(int i = 0; i < bj.getSize(); i++) {
                    		Player p = bj.getPlayer(i);
                            //show balances
                            System.out.printf("%s's current balance: $%d%n", p.getUsername(), p.getBalance()); 
                    		while(true) {
                    			System.out.print("Bet: $");
                    			int bet = Math.abs(in.nextInt());
                    			if(bet > p.getBalance()) { // check if bet is possible
                    				System.out.println("You don't have enough money.");
                    			} else { // place bet
                    				p.bet(bet);
                    				break;
                    			}
                    		} 
                    	}
                        
                        //players' turns
                        bj.clearScreen();
                    	for(int i = 0; i < bj.getSize(); i++) { // player turns
                    		boolean playerTurn = true;
                    		Player p = bj.getPlayer(i);
                    		while(playerTurn && p.getScore() < 21) { //play turn while not busted
                                bj.clearScreen();
                                bj.displayGame();
                    			System.out.printf("%s's turn%n", p.getUsername());
                    			System.out.println("Press [1] to hit.");
                    			System.out.println("Press [2] to stand.");
                    			if(2 * p.getBet() <= p.getBalance()) { //allow double down if possible
                    				System.out.println("Press [3] to double down.");
                    			}
                    			int input = in.nextInt();
                    			switch(input) {
                    				case 1: //hit
                    		      		bj.hit(i);
                    	   		        break;
                    				case 2: //stand
                                        playerTurn = false;
                    				    break;
                    				case 3: //double down
                                        if(2 * p.getBet() <= p.getBalance()) {
                                            bj.hit(i);
                    				        p.bet(2 * p.getBet());
                                            playerTurn = false;
                                        }
                    				    break;
                                    default:
                                        continue;
                    			}
                    		}
                    		System.out.printf("%n");
                    	}

                    	//dealer's turn 
                    	bj.dealerTurn();
                        bj.clearScreen();
                    	bj.displayGame();

                    	//winner 
                    	bj.determineWinner(); //update standings and pay bets
                        bj.removeBankrupt();
                        if(bj.getSize() == 0) {
                            System.out.printf("%nThanks for playing!%n");
                            play = false;
                            quit = false;
                            break;
                        }
                        System.out.printf("%n");

                        boolean options = true;
                        while(options) {
                        	System.out.println("[1] Replay");
                            System.out.println("[2] View current standings");
                        	System.out.println("[3] Add players");
                        	System.out.println("[4] Remove players");
                        	System.out.println("[5] Quit");
                        	int input = in.nextInt();
                        	switch(input) {
                        		case 1: //reset game
                                    bj.replay();
                                    options = false;
                        			break;
                                case 2: // viewing standings
                                    bj.clearScreen();
                                    bj.displayCurrentStandings();
                                    break;
                        		case 3: //removing players
                        			System.out.print("How many players to add? "); 
                                    int add = in.nextInt();
                                    bj.addPlayers(add);
                                    bj.clearScreen();
                                    bj.displayCurrentStandings();
                                    break;
                        		case 4: //adding players
                        			System.out.print("How many players to remove? ");
                                    int remove = in.nextInt();
                                    if(remove >= bj.getSize()) { //prevents from removing too many
                                        System.out.printf("Can't remove that many.%n%n");
                                    } else {
                                        bj.removePlayers(remove);
                                        bj.clearScreen();
                                        bj.displayCurrentStandings();
                                    }
                                    break;
                        		case 5: // end game
                        			System.out.printf("%nThanks for playing!%n");
                                    options = false;
                                    play = false;
                                    quit = false;
                                    break;
                                default:
                                    continue;
                                }
                        	}
                        }
                    break;
                case 2:
                    System.out.printf("%nCome back soon!%n");
                    quit = false;
                    break;
                default: 
                    System.out.printf("%nInvalid option. Try again.%n%n");
                    break;
            }        
        }
    }
}
