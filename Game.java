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
            System.out.printf("Player %s:%n", players.get(i).getUsername());
            players.get(i).displayHand();
            System.out.printf("Score: %s%n", players.get(i).getScore());
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
    public void displaySidebySide(){
        String border = "+-----+";
        int playersperline = 3;
        for(int s = 0; s < players.size(); s = s + playersperline) {
            for(int i = s; i < s + ((players.size() - s) % playersperline) + 1; i++){
                System.out.printf("%-18s", players.get(i).getUsername());
                for(int j = 0; j < players.get(i).getHandSize() - 2; j++){
                    System.out.printf("%9s", " ");
                }
            }
            System.out.printf("%n");

            for(int i = s; i < s + playersperline; i++){
                for(int j = 0; j < players.get(i).getHandSize(); j++){
                    System.out.printf("%-9s", border);
                }                
            }
            System.out.printf("%n");

            for(int i = s; i < s + playersperline; i++){
                for(int j = 0; j < players.get(i).getHandSize(); j++){
                    System.out.printf("|%-5s|  ", players.get(i).getCard(j).getRank());
                }
            }
            System.out.printf("%n");

            for(int i = s; i < s + playersperline; i++){
                for(int j = 0; j < players.get(i).getHandSize(); j++){
                    System.out.printf("|%3s  |  ", players.get(i).getCard(j).getSuit());
                }
            }
            System.out.printf("%n");

            for(int i = s; i < s + playersperline; i++){
                for(int j = 0; j < players.get(i).getHandSize(); j++){
                    System.out.printf("|%5s|  ", players.get(i).getCard(j).getRank());
                }
            }
            System.out.printf("%n");
 
            for(int i = s; i < s + playersperline; i++){
                for(int j = 0; j < players.get(i).getHandSize(); j++){
                    System.out.printf("%-9s", border);
                }
            }

            System.out.printf("%n%n");
        }
    }
}
