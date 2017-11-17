import java.util.*;

public class Game {
	ArrayList<Player> players;
    private Dealer dealer; 
    private Deck deck;
	public Game() {
		players = new ArrayList<Player>();
        dealer = new Dealer(); 
        deck = new Deck();
	}
	//get player at specific index
    public void newGame() {
        dealer.hit(deck.draw()); //dealer draws one
        for(int i = 0; i < players.size(); i++) {
            for(int j = 0; j < 2; j++){ 
                players.get(i).hit(deck.draw()); //dealer draws 2 cards
            }
        }
    }
	public Player getPlayer(int index) {
		return players.get(index);
	}
	public void addPlayer(String username) {
        Player p = new Player(username);
		players.add(p);
	}
	public void removePlayer(String username) {
		// players.remove(oldPlayer); // need to fix
	}
    public void displayGame() {
        System.out.println("Dealer");
        dealer.displayHand();
        for(int i = 0; i < players.size(); i++) {
        	System.out.println();
            System.out.println("Player " + (i+1) + ": " + players.get(i).getUsername());
            players.get(i).displayHand();
            System.out.print("Score: ");
            players.get(i).getScore();
            System.out.println();
        }
    }
    public void replay() {
        for(int i = 0; i < players.size(); i++) {
            players.get(i).clearHand();
        }
        dealer.clearHand();
        deck.resetDeck();
        newGame();
    }
	//following method isn't really necessary, just needed to test if adding players worked
	public void printPlayers() {
		for(int i = 0; i < players.size(); i++) {
			Player p = players.get(i);
			System.out.print(p.getUsername() + "  ");
		}
	}
}
