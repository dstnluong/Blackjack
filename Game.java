import java.util.*;

public class Game {
    ArrayList<Player> players;
    int gamesPlayed;
	public Game() {
		players = new ArrayList<Player>();
        gamesPlayed = 0;
	}
	public ArrayList<Player> getPlayers() {
		return players;
	}
	//get player at specific index
	public Player getPlayer(int index) {
		return players.get(index);
	}
    public int getGamesPlayed(){
        return gamesPlayed;
    }
    public void increaseGamesPlayed(){
        gamesPlayed++;
    }
    //add parameter amount of players 
	public void addPlayers(int add) {
        Scanner in = new Scanner(System.in);
        int numOfPlayers = players.size();
        System.out.printf("%n");
        for(int i = numOfPlayers ; i < numOfPlayers + add; i++) {
            while(true) {
                boolean sameName = false;
                System.out.printf("Player %d's name: ", i + 1);
                String name = in.next();
                for(int j = 0; j < players.size(); j++) {
                    if(name.equals(getPlayer(j).getUsername())) {
                        System.out.println("Username is already taken. Try again.");
                        sameName = true;
                    }
                }
                if(!sameName) {
                    Player p = new Player(name);
                    players.add(p);
                    break;
                }
            }
        }
        System.out.printf("%n");
	}
    //remove parameter amount of players
	public void removePlayers(int remove) {
		Scanner in = new Scanner(System.in);
        System.out.printf("%n");
        for(int i = 0; i < remove; i++) {
            while(true) {
                int index = 0;
                boolean dne = true;
                System.out.print("Player name: ");
                String name = in.next();
                for(int j = 0; j < players.size(); j++) {
                    if(players.get(j).getUsername().equals(name)) {
                        index = j;
                        dne = false;
                    }
                }
                if(dne) {
                    System.out.printf("%s does not exist.%n", name);
                } else {
                    players.remove(index);
                    System.out.printf("%s has been removed.%n", name);
                    break;
                }
            }
        }
        System.out.printf("%n");
	}
    //prints 3 players' hands in a linear fashion
    public void displaySidebySide() {
        String border = "+-----+";
        int playersperline = 3;

        for(int s = 0; s < players.size(); s = s + playersperline) {

            for(int i = s; i < s + playersperline && i < players.size(); i++) { //username
                System.out.printf("%-18s", players.get(i).getUsername());
                for(int j = 0; j < players.get(i).getHandSize() - 2; j++) {
                    System.out.printf("%9s", " ");
                }
            }
            System.out.printf("%n");

            for(int i = s; i < s + playersperline && i < players.size(); i++) { // border
                for(int j = 0; j < players.get(i).getHandSize(); j++) {
                    System.out.printf("%-9s", border);
                }                
            }
            System.out.printf("%n");

            for(int i = s; i < s + playersperline && i < players.size(); i++) { //rank
                for(int j = 0; j < players.get(i).getHandSize(); j++) {
                    System.out.printf("|%-5s|  ", players.get(i).getCard(j).getRank());
                }
            }
            System.out.printf("%n");

            for(int i = s; i < s + playersperline && i < players.size(); i++) { //suit
                for(int j = 0; j < players.get(i).getHandSize(); j++) {
                    System.out.printf("|%3s  |  ", players.get(i).getCard(j).getSuit());
                }
            }
            System.out.printf("%n");

            for(int i = s; i < s + playersperline && i < players.size(); i++) { //rank
                for(int j = 0; j < players.get(i).getHandSize(); j++) {
                    System.out.printf("|%5s|  ", players.get(i).getCard(j).getRank());
                }
            }
            System.out.printf("%n");
 
            for(int i = s; i < s + playersperline && i < players.size(); i++) { //border
                for(int j = 0; j < players.get(i).getHandSize(); j++) {
                    System.out.printf("%-9s", border);
                }
            }
            System.out.printf("%n"); 

            for(int i = s; i < s + playersperline && i < players.size(); i++) { //score
                System.out.printf("Score: %-11s", players.get(i).getScoreString());
                for(int j = 0; j < players.get(i).getHandSize() - 2; j++) {
                    System.out.printf("%9s", " ");
                }
            }
            System.out.printf("%n");

            for(int i = s; i < s + playersperline && i < players.size(); i++) { //bet
                System.out.printf("Bet: $%-12d", players.get(i).getBet());
                for(int j = 0; j < players.get(i).getHandSize() - 2; j++) {
                    System.out.printf("%9s", " ");
                }
            }
            System.out.printf("%n");
        }
        System.out.printf("%n");
    }
}
