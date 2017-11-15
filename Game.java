import java.util.*;

public class Game {
	ArrayList<Player> players;
	public Game() {
		players = new ArrayList<Player>();
	}
	//get player at specific index
	public Player getPlayer(int index) {
		return players.get(index);
	}
	public void addPlayer(Player newPlayer) {
		players.add(newPlayer);
	}
	public void removePlayer(Player oldPlayer) {
		players.remove(oldPlayer);
	}
	//following method isn't really necessary, just needed to test if adding players worked
	public void printPlayers() {
		for(int i = 0; i < players.size(); i++) {
			Player p = players.get(i);
			System.out.print(p.getUsername() + "  ");
		}
	}
}
