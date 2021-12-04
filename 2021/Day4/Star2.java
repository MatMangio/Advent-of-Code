import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.LinkedList;

public class Star2 {
    public static void main(String[] args) {
        //Read the extraction numbers
        Scanner in = new Scanner(System.in);
        String[] numStrings = in.nextLine().split(",");
        int[] extracted = new int[numStrings.length];
        for (int i = 0; i < numStrings.length; i++) {
            extracted[i] = Integer.parseInt(numStrings[i]);
        }
        
        //Read the boards
        List<BingoBoard> boards = new ArrayList<BingoBoard>();
        while(in.hasNext()) {
            boards.add(new BingoBoard(in, 5));
        }

        System.out.println(boards.get(1).contains(16));

        //Infere the losing board
        int last = 0;
        BingoBoard loser = boards.get(0);
        for (int num : extracted) {
            last = num;
            List<Integer> mustRemove = new LinkedList<Integer>();
            for (int i = 0; i < boards.size(); i++) {
                boards.get(i).markNumber(num);
                if (boards.get(i).isWinning()) {
                    loser = boards.get(i);
                    mustRemove.add(i);
                }
            }

            int removed = 0;
            for (int pos : mustRemove) {
                boards.remove(pos - removed);
                removed++;
            }

            if (boards.size() < 1) break;
        }
        System.out.println("\nLast number: " + last);

        //Calulate and print the score
        int score = 0;
        Iterator<BingoBoard.Cell> iter = loser.iterator();
        while (iter.hasNext()) {
            BingoBoard.Cell c = iter.next();
            if (!c.marked()) {
                score += c.num();
            }
        }
        score *= last;
        System.out.println("Losing board:\n" + loser);
        System.out.println("Score: " + score);
    }
}
