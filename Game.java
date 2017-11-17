import java.util.*;

public class Game {
	private ArrayList<Player> players;
    private Dealer dealer; 
    private Deck deck;
	public Game() {
		players = new ArrayList<Player>();
        dealer = new Dealer(); 
        deck = new Deck();
	}
	//get player at specific index
    public void newGame(){
        dealer.hit(deck.draw());
        for(int i = 0; i < players.size(); i++){
            for(int j = 0; i < 2; i++){ 
                players.get(i).hit(deck.draw());
            }
        }
    }
	public Player getPlayer(int index) {
		return players.get(index);
	}
	public void addPlayer(Player newPlayer) {
		players.add(newPlayer);
	}
	public void removePlayer(Player oldPlayer) {
		players.remove(oldPlayer);
	}
    public void replay(){
        for(int i = 0; i < players.size(); i++){
            players.get(i).clearHand();
        }
        dealer.clearHand();
        deck.resetDeck();
    }
	//following method isn't really necessary, just needed to test if adding players worked
	public void printPlayers() {
		for(int i = 0; i < players.size(); i++) {
			Player p = players.get(i);
			System.out.print(p.getUsername() + "  ");
		}
	}
}
