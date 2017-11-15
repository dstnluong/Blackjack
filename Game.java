import java.util.*;

public class Game {
	ArrayList<Player> players;
	public Game() {
		players = new ArrayList<Player>();
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
	public void printPlayers() {
		for(int i = 0; i < players.size(); i++) {
			Player p = players.get(i);
			System.out.print(p.getUsername() + "  ");
		}
	}
}
