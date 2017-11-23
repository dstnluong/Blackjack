import java.util.*;

public class Game {
    private ArrayList<Player> players;
    private Deck deck;
    private Dealer dealer;
    int gamesPlayed;
    
    public Game() {
		players = new ArrayList<Player>();
        deck = new Deck();
        dealer = new Dealer(); 
        gamesPlayed = 0;
    }
    public Player getPlayer(int index) {   //get player at specific index
        return players.get(index);
    }
    public int getSize(){
        return players.size();
    }
    public void newGame() {
        for(int i = 0; i < players.size(); i++) { //each player draws 2 cards
            for(int j = 0; j < 2; j++) {
                Player p = players.get(i);
                p.hit(deck.draw()); 
            }
        }
        dealer.hit(deck.draw()); //dealer draws one
    }
    public int getGamesPlayed() {
        return gamesPlayed;
    }
    public void increaseGamesPlayed() { //increment total games (not in use rn)
        gamesPlayed++;
    }
    //add parameter amount of players 
    public void removeBankrupt(){
        int size = players.size();
         for(int i = 0; i < size; i++) { //remove players with no money
            Player p = players.get(i);
            if(p.getBalance() <= 0) {
                System.out.printf("%n%s went bankrupt.%n", p.getUsername());
                System.out.printf("%s has been removed.%n", p.getUsername());
                players.remove(i);
                size--;
                i--;
            }
        }
    }
    public void addPlayers(int add) { //add new player
        Scanner in = new Scanner(System.in);
        int numOfPlayers = players.size();
        System.out.printf("%n");
        for(int i = numOfPlayers ; i < numOfPlayers + add; i++) { //adding players
            while(true) {
                boolean sameName = false;
                System.out.printf("Player %d's name: ", i + 1);
                String name = in.next();
                for(int j = 0; j < players.size(); j++) { //search if username is already taken
                    if(name.equals(getPlayer(j).getUsername())) {
                        System.out.println("Username is already taken. Try again.");
                        sameName = true;
                    }
                }
                if(!sameName) { //adds if username did not match an existing username
                    Player p = new Player(name);
                    players.add(p);
                    break;
                }
            }
        }
        System.out.printf("%n");
    }
    public void hit(int index) {
        Card c = deck.draw();
        players.get(index).hit(c);
        players.get(index).setScore();
    }
    public void dealerTurn() { //automates dealer turn
        while(dealer.getScore() < 17) { //stands on soft 17
            dealer.hit(deck.draw()); 
            dealer.setScore();
        }
        dealer.revealCard(); //remove card back
    }
    public void clearScreen() { //clears screen
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
        AsciiArt.printHeader(); //prints header immediately after
    }
    public void displayGame() { //main display
        dealer.displayHand(); //prints dealer
        displaySidebySide();// prints player hands
    }
    public void removePlayers(int remove) {     //remove parameter amount of players
        Scanner in = new Scanner(System.in);
        System.out.printf("%n");
        for(int i = 0; i < remove; i++) {//repeats for amount of wanted players to remove
            while(true) {
                int index = 0;
                boolean dne = true;
                System.out.print("Player name: "); //prompt for name of player to remove
                String name = in.next();
                for(int j = 0; j < players.size(); j++) { // search for player
                    if(players.get(j).getUsername().equals(name)) { //get index of player or returns in player does not exist
                        index = j;
                        dne = false;
                    }
                }
                if(dne) {
                    System.out.printf("%s does not exist.%n", name); //player not found
                } else {
                    players.remove(index); //remove player found at the index
                    System.out.printf("%s has been removed.%n", name);
                    break;
                }
            }
        }
        System.out.printf("%n");
    }
    public void replay() { //reset everything
        for(int i = 0; i < players.size(); i++) { //clear hands and bets
            players.get(i).reset();
        }
        dealer.reset(); //clears hand
        deck.resetDeck(); //new deck and reshuffles
        newGame(); //initial deal
    }
    public void determineWinner() {
        System.out.printf("Winners:%n");
        for(int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            int moneyWon = 0;
            if(p.checkBust()) { //player bust = auto lose
                moneyWon = -1 * p.getBet();
            } else if(dealer.checkBust()) { //dealer bust = auto win
                moneyWon = p.getBet();
            } else if (p.getScore() > dealer.getScore()) { // win pay 1:1
                moneyWon = p.getBet();
            } else if (p.getScore() < dealer.getScore()) { //lose negative bet
                moneyWon -= p.getBet();
            } else if (p.getScore() == dealer.getScore()) { // draw
                moneyWon = 0;
            }
            if(moneyWon > 0) {
                System.out.printf("%s ", p.getUsername());
            }
            if(p.getScore() == 21 && moneyWon > 0) { // blackjacks pays 3:2
                moneyWon *= 1.5;
            }
            p.updateStandings(moneyWon);    
            p.setBalance(p.getBalance() + moneyWon);
        }
        System.out.printf("%n");
    }
    public void displayCurrentStandings() { // prints balanace, wins, loses, and draws
        System.out.printf("%nCurrent Standings:%n");
        if(players.size() > 0) {
            for(int i = 0; i < players.size(); i++) { //username
                System.out.printf("%-18s", players.get(i).getUsername(), "");     
            }
            System.out.printf("%n");

            for(int i = 0; i < players.size(); i++) { // balance
                System.out.printf("Balance: $%-8d", players.get(i).getBalance());     
            }
            System.out.printf("%n");
            for(int i = 0; i < players.size(); i++) { //wins
                System.out.printf("Wins: %-12d", players.get(i).getWins());     
            }
            System.out.printf("%n");
            for(int i = 0; i < players.size(); i++) { // loses
                System.out.printf("Loses: %-11d", players.get(i).getLoses());     
            }
            System.out.printf("%n");
            for(int i = 0; i < players.size(); i++) { // draws
                System.out.printf("Draws: %-11d", players.get(i).getDraws());     
            }
            System.out.printf("%n%n");
        } else {
            System.out.printf("%nThere are currently no players.%n%n");
        }
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
                if(players.get(i).getScore() == 21) {
                    System.out.printf("Bet: $%-12d", (int)(players.get(i).getBet()*1.5));
                } else {
                    System.out.printf("Bet: $%-12d", players.get(i).getBet());
                }
                for(int j = 0; j < players.get(i).getHandSize() - 2; j++) {
                    System.out.printf("%9s", " ");
                }
            }
            System.out.printf("%n");
        }
        System.out.printf("%n");
    }
}
