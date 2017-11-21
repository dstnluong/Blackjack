import java.util.*;

public class Game {
    ArrayList<Player> players;
	public Game() {
		players = new ArrayList<Player>();
	}
	public ArrayList<Player> getPlayers() {
		return players;
	}
	//get player at specific index
	public Player getPlayer(int index) {
		return players.get(index);
	}
	public void addPlayer(String username) {
        Player p = new Player(username);
		players.add(p);
	}
	public void removePlayer(String username) {
		// players.remove(username); // need to fix
	}
    public void displaySidebySide(){
        String border = "+-----+";
        int playersperline = 3;

        for(int s = 0; s < players.size(); s = s + playersperline) {

            for(int i = s; i < s + playersperline && i < players.size(); i++){ //username
                System.out.printf("%-18s", players.get(i).getUsername());
                for(int j = 0; j < players.get(i).getHandSize() - 2; j++){
                    System.out.printf("%9s", " ");
                }
            }
            System.out.printf("%n");

            for(int i = s; i < s + playersperline && i < players.size(); i++){ // border
                for(int j = 0; j < players.get(i).getHandSize(); j++){
                    System.out.printf("%-9s", border);
                }                
            }
            System.out.printf("%n");

            for(int i = s; i < s + playersperline && i < players.size(); i++){ //rank
                for(int j = 0; j < players.get(i).getHandSize(); j++){
                    System.out.printf("|%-5s|  ", players.get(i).getCard(j).getRank());
                }
            }
            System.out.printf("%n");

            for(int i = s; i < s + playersperline && i < players.size(); i++){ //suit
                for(int j = 0; j < players.get(i).getHandSize(); j++){
                    System.out.printf("|%3s  |  ", players.get(i).getCard(j).getSuit());
                }
            }
            System.out.printf("%n");

            for(int i = s; i < s + playersperline && i < players.size(); i++){ //rank
                for(int j = 0; j < players.get(i).getHandSize(); j++){
                    System.out.printf("|%5s|  ", players.get(i).getCard(j).getRank());
                }
            }
            System.out.printf("%n");
 
            for(int i = s; i < s + playersperline && i < players.size(); i++){ //border
                for(int j = 0; j < players.get(i).getHandSize(); j++){
                    System.out.printf("%-9s", border);
                }
            }
           System.out.printf("%n"); 

            for(int i = s; i < s + playersperline && i < players.size(); i++){ //score
                System.out.printf("Score: %-11s", players.get(i).getScoreString());
                for(int j = 0; j < players.get(i).getHandSize() - 2; j++){
                    System.out.printf("%9s", " ");
                }
            }
            System.out.printf("%n");
        }
        System.out.printf("%n");
    }
}
