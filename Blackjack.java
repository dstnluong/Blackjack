import java.util.*;

public class Blackjack {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean playing = true;
        while(playing) { 
        input = new Scanner(System.in);

        System.out.printf("Number of players: ");
        int playerCount = input.nextInt();
        input.nextLine();
        for (int i = 0; i < playerCount; i++) {
            System.out.printf("Player %d's name: ", i + 1);
        }
            /*
            System.out.println("1.New Game");
            System.out.println("2.Quit");
            int userInput = in.nextInt();
            switch(userInput){
                case(1):
                case(2):
                    System.out.println("Thanks for playing!");
                    playing = false;
            }
            */
        }
    }
}
