import java.util.Iterator;
import java.util.Scanner;

/**
 * OVERVIEW: an object of this class represents a mutable NxN bingo board of cells containing positive numbers, where all cells start unmarked.
 * An exemple of 5x5 board may look like this (where * next to the number indicates that the cell is marked):
 *      5   1   2   3*  7*
 *      12  4*  17* 8   9
 *      22* 45  69  42  0
 *      15  37  92* 88  63
 *      77  49  56  53  27
 */

public class BingoBoard implements Iterable<BingoBoard.Cell> {
    //Attributes
    /**
     * The matrix of Cells storing the state of the board
     */
    private Cell[][] board;

    /**
     * Cell record containing its number and wheter the cell is marked or not
     */
    public record Cell(int num, boolean marked){};

    //Abstraction function and representation invariant
    /**
     * RepOk(this) = board.length == board[i].length for all i = 1, ..., board.length &&
     *               board[i][j].num >= 0 for all i, j = 1, ..., board.length
     * 
     * AF(this) = bingo board where the cell in pos. (i, j) contains board[i][j].num, and is marked iif board[i][j].marked == true
     */
    private boolean repOk() {
        for (Cell[] row : board) {
            if (row.length != board.length) return false;
            for (Cell cell : row) {
                if (cell.num < 0) return false;
            }
        }
        return true;
    }
    
    //Constructors
    /**
     * EFFECTS: constructs a dim x dim bingo board, filling the cells with the input taken from the Scanner.
     */
    public BingoBoard(Scanner input, int dim) {
        board = new Cell[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (!input.hasNext()) throw new NotEnoughInputInIterator("BingoBoard.java/BingoBoard(Scanner, int): not enough input was passed in the Scanner");
                String nStr = input.next();
                try {
                    int n = Integer.parseInt(nStr);
                    board[i][j] = new Cell(n, false);
                } catch (RuntimeException e) {}
            }
        }
        assert repOk();
    }

    /**
     * EFFECTS: constructs a dim x dim bingo board, filling the cells with the input taken from the Integer iterator.
     */
    public BingoBoard(Iterator<Integer> input, int dim) {
        board = new Cell[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (!input.hasNext()) 
                    throw new NotEnoughInputInIterator("BingoBoard.java/BingoBoard(Iterator<Integer>, int): not enough input was passed to the Iterator");
                board[i][j] = new Cell(input.next(), false);
            }
        }
        assert repOk();
    }

    //Methods
    /**
     * EFFECTS: returns true if the board has at least one row or one column of contigous marked cells; false otherwise.
     */
    public boolean isWinning() {
        //Checks columns
        for (int j = 0; j < board.length; j++) {
            if (isWinningOnColumn(j)) return true;
        }
        //Checks rows
        for (int i = 0; i < board.length; i++) {
            if (isWinningOnRow(i)) return true;
        }
        return false;
    }

    /**
     * EFFECTS: returns true if row i on the board has all its cells marked, false otherwise; 
     * if row i doesn't exist, throws an IndexOutOfBoundsException.
     */
    public boolean isWinningOnRow(int i) {
        if (i >= board.length) throw new IndexOutOfBoundsException("BingoBoard/isWinningOnRow(int): non-existing row index");

        for (int j = 0; j < board.length; j++) {
            if (!board[i][j].marked) return false;
        }
        return true;
    }

    /**
     * EFFECTS: returns true if column j on the board has all its cells marked, false otherwise; 
     * if column j doesn't exist, throws an IndexOutOfBoundsException.
     */
    public boolean isWinningOnColumn(int j) {
        if (j >= board.length) throw new IndexOutOfBoundsException("BingoBoard/isWinningOnColumn(int): non-existing column index");

        for (int i = 0; i < board.length; i++) {
            if (!board[i][j].marked) return false;
        }
        return true;
    }

    /**
     * EFFECTS: returns true if the board contains the element k, false otherwise.
     */
    public boolean contains(int k) {
        try {
            find(k);
        } catch (ElementNotFoundException e) {
            return false;
        }
        return true;
    }

    /**
     * EFFECTS: returns an array of two integers representing the coordinates on the board of the element k;
     * if the element k is not on the board throws an ElementNotFoundException.
     */
    public int[] find(int k) throws ElementNotFoundException {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j].num == k) {
                    int[] coord = new int[2];
                    coord[0] = i;
                    coord[1] = j;
                    return coord;
                }
            }
        }
        throw new ElementNotFoundException("BingoBoard/find(int): element not on the board");
    }

    /**
     * MODIFIES: this
     * EFFECTS: marks the cell with the number k on the board; if the cell is not present, does nothing.
     */
    public void markNumber(int k) {
        try {
            int[] coord = find(k);
            markPosition(coord[0], coord[1]);
        } catch (ElementNotFoundException e) {}
        assert repOk();
    }

    /**
     * MODIFIES: this
     * EFFECTS: marks the cell in the position (i, j) on the board;
     * if one of the coordinates is out of bounds, throws an IndexOutOfBoundsException.
     */
    public void markPosition(int i, int j) {
        if (i >= board.length) throw new IndexOutOfBoundsException("BingoBoard/isWinningOnRow(int): non-existing row index");
        if (j >= board.length) throw new IndexOutOfBoundsException("BingoBoard/isWinningOnColumn(int): non-existing column index");
        board[i][j] = new Cell(board[i][j].num, true);
        assert repOk();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Cell[] row : board) {
            for (Cell cell : row) {
                if (cell.num < 10) sb.append(" ");
                if (cell.marked) sb.append("*");
                else sb.append(" ");
                sb.append(cell.num + "  ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public Iterator<Cell> iterator() {
        return new Iterator<Cell>() {
            int i = 0;
            int j = 0;
            
            public boolean hasNext() {
                return (i < board.length && j < board.length);
            }

            public Cell next() {
                Cell curr = board[i][j];
                if (j == board.length - 1) {
                    j = 0;
                    i = i + 1;
                } else {
                    j++;
                }
                return curr;
            }
        };
    }
}