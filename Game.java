import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private ArrayList<Player> players;
    private Scanner in;
    private Deck deck;
    private Dealer dealer;
    private int gamesPlayed;
    
    public Game() {
        players = new ArrayList<Player>();
        in = new Scanner(System.in);
        deck = new Deck();
        dealer = new Dealer(); 
        gamesPlayed = 0;
    }
    //get player at specific index
    public Player getPlayer(int index) {  
        return players.get(index);
    }
    //returns number of players
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
    public void hit(int index) {
        Card c = deck.draw();
        players.get(index).hit(c);
        players.get(index).setScore();
    }
    //automates dealer turn
    public void dealerTurn() { 
        while(dealer.getScore() < 17) { //stands on soft 17
            dealer.hit(deck.draw()); 
            dealer.setScore();
        }
        dealer.revealCard(); //remove card back
    }
    //clears ide
    public void clearScreen() { 
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
        AsciiArt.printHeader(); //prints header immediately after
    }
    //main display
    public void displayGame() { 
        dealer.displayHand(); //prints dealer
        displaySidebySide();// prints player hands
    }
    //add parameter amount of players 
    public void addPlayers(int add) { 
        System.out.printf("%n");
        int numOfPlayers = players.size();
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
    //remove parameter amount of players
    public void removePlayers(int remove) {
        System.out.printf("%n");
        for(int i = 0; i < remove; i++) { //repeats for amount of wanted players to remove
            while(true) {
                int index = 0;
                boolean dne = true;
                System.out.print("Player name: "); //prompt for name of player to remove
                String name = in.next();
                for(int j = 0; j < players.size(); j++) { // search for player
                    if(players.get(j).getUsername().equals(name)) { //get index of player or returns if player dne
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
    //reset the game with a number of players
    public void replay() { 
        for(int i = 0; i < players.size(); i++) { //clear hands and bets
            players.get(i).reset();
        }
        dealer.reset(); //clears hand
        deck.resetDeck(); //new deck and reshuffles
        newGame(); //initial deal
    }
    public void determineWinner() {
        System.out.println("Winners:");
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
                System.out.printf("%s  ", p.getUsername());
            }
            if(p.getScore() == 21 && moneyWon > 0) { // blackjacks pays 3:2
                moneyWon *= 1.5;
            }
            p.updateStandings(moneyWon);    
            p.setBalance(p.getBalance() + moneyWon);
        }
        System.out.printf("%n");
    }
    // prints balances, wins, loses, and draws
    public void displayCurrentStandings() { 
        System.out.printf("%nCurrent Standings%n%n");
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
    //prints the hands of 3 players in a linear fashion
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