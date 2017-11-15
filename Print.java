public class Print {
    public void Print(){
        String [] suit = {"\u2666", "\u2663"};
        String [] rank = {"3", "A"};
        String border = "+-----+  ";
        for(int j = 0; j < suit.length; j++){
             System.out.print(border);
        }
        System.out.println("");
        for(int j = 0; j < suit.length; j++){
            System.out.print("|" + rank[j]+ "    |  ");
        }
        System.out.println("");
        for(int j = 0; j < suit.length; j++){
            System.out.print("|  " + suit[j] + "  |  ");
        }
        System.out.println("");
        for(int j = 0; j < suit.length; j++){
            System.out.print("|    " + rank[j] + "|  ");
        }
        System.out.println("");
        for(int j = 0; j < suit.length; j++){
            System.out.print(border);
        }
        System.out.println("");
    }
}
