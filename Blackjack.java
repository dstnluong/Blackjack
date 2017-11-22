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
        AsciiArt.printHeader();

        System.out.print("Number of players: ");
        int playerCount = in.nextInt();
        bj.addPlayers(playerCount, 0);        

        newGame();
        System.out.printf("%n");

        while(play) { 
        	//bet
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

        	//players' turns
        	for(int i = 0; i < playerCount; i++) {
        		boolean playerTurn = true;
        		displayGame();
        		Player p = bj.getPlayer(i);
        		while(playerTurn && p.getScore() < 21) { 
        			System.out.printf("%s's turn%n", p.getUsername());
        			System.out.println("Press [1] to hit.");
        			System.out.println("Press [2] to stand.");
        			if(2*p.getBet() <= p.getBalance()) {
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
                        default:
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
            for(int i = 0; i < playerCount; i++) {
                bj.getPlayer(i).updateBalance(dealer.getScore());
                if(bj.getPlayer(i).getBalance() <= 0) {
                    bj.getPlayers().remove(i);
                    playerCount--;
                }
            }

            System.out.printf("%n%n");
            //boolean options = true;
            while(true) {
            	System.out.println("[1] Replay");
            	System.out.println("[2] Add players");
            	System.out.println("[3] Remove players");
            	System.out.println("[4] Quit");
            	int input = in.nextInt();
            	switch(input) {
            		case 1:
                        replay();
                        break;
            		case 2:
            			System.out.print("How many players? ");
                        int add = in.nextInt();
                        bj.addPlayers(playerCount + add, playerCount);
                        playerCount += add;
                        break;
            		case 3:
            			System.out.print("How many players? ");
                        int remove = in.nextInt();
                        bj.removePlayers(remove);
                        playerCount -= remove;
            			break;
            		case 4:
            			System.out.printf("%nThanks for playing!%n");
                        play = false;
                        break;
                    default: 
                        continue;
            	}
                break;
            }
        }
    }

    public static void newGame() {
        for(int i = 0; i < bj.getPlayers().size(); i++) {
            for(int j = 0; j < 2; j++) {
                Player p = bj.getPlayers().get(i);
                p.hit(deck.draw()); 
            }
        }
        dealer.hideCard();
        dealer.hit(deck.draw());
        clearScreen();
    }
    public static void displayGame() {
        clearScreen();
        AsciiArt.printHeader();
    	dealer.displayHand();
    	bj.displaySidebySide();
    }
    public static void clearScreen() {
    	System.out.print("\033[H\033[2J");  
    	System.out.flush(); 
    }
    public static void hit(int index) {
        Card c = deck.draw();
        bj.getPlayer(index).hit(c);
        bj.getPlayer(index).setScore();
    } 
    public static void dealerTurn() {
        while(dealer.getScore() < 17) {
            dealer.hit(deck.draw());
            dealer.setScore();
        }
    }
    public static void determineWinner() {
        int highscore = 0;
        if(dealer.getScore() <= 21) {
    	   highscore = dealer.getScore();
        }
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
        	}
        	for(int i = 0; i < bj.getPlayers().size(); i++) {
        		if(bj.getPlayer(i).getScore() == highscore) {
        			System.out.println(bj.getPlayer(i).getUsername());
        		}
        	}
        }
    }
    public static void replay() {
        for(int i = 0; i < bj.getPlayers().size(); i++) {
            bj.getPlayer(i).clearHand();
        }
        dealer.clearHand();
        deck.resetDeck();
        newGame();
    }
}
