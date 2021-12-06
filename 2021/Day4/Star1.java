import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Star1 {
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
        in.close();

        //Infere the winning board
        int last = 0;
        BingoBoard winner = boards.get(0);
        for (int num : extracted) {
            boolean thereIsWinner = false;
            last = num;
            for (BingoBoard bingoBoard : boards) {
                winner = bingoBoard;
                bingoBoard.markNumber(num);
                if (bingoBoard.isWinning()) {
                    thereIsWinner = true;
                    break;
                }
            }
            if (thereIsWinner) break;
        }

        //Calulate and print the score
        int score = 0;
        Iterator<BingoBoard.Cell> iter = winner.iterator();
        while (iter.hasNext()) {
            BingoBoard.Cell c = iter.next();
            if (!c.marked()) {
                score += c.num();
            }
        }
        score *= last;
        System.out.println("Winning board:\n" + winner);
        System.out.println("Score: " + score);
    }
}