import java.util.*;
import java.util.stream.*;

public class SudokuChecker{
    private Set validSudokuNumbers;

    public SudokuChecker()
    {
        //There must be a more concise way to do this
        validSudokuNumbers = new HashSet();
        validSudokuNumbers.add(1);
        validSudokuNumbers.add(2);
        validSudokuNumbers.add(3);
        validSudokuNumbers.add(4);
        validSudokuNumbers.add(5);
        validSudokuNumbers.add(6);
        validSudokuNumbers.add(7);
        validSudokuNumbers.add(8);
        validSudokuNumbers.add(9);
    }

    public boolean CheckIsValid(int[][] board)
    {
        boolean rowsAreValid = 
            Arrays.stream(board)
            .allMatch(row -> checkSudokuSet(Arrays.stream(row).boxed()));
        boolean columnsAreValid =
            Arrays.stream(getColumns(board))
            .allMatch(row -> checkSudokuSet(Arrays.stream(row).boxed()));
        boolean squaresAreValid = getSquares(board).stream()
            .allMatch(row -> checkSudokuSet(row.stream()));
            
        return rowsAreValid && columnsAreValid && squaresAreValid;
    }

    private List<List<Integer>> getSquares(int[][] board)
    {
        List<List<Integer>> squares = new LinkedList<List<Integer>>();
        for(int top = 0; top < 3; top++)
            for(int left = 0; left < 3; left++)
                squares.add(getSquare(top * 3, left * 3, board));

        return squares;
    }

    private List<Integer> getSquare(int top, int left, int[][] board)
    {
        List<Integer> collector = new LinkedList<Integer>();
        for(int x = left; x < left + 3; x++)
            for(int y = top; y < top + 3; y++)
                collector.add(board[y][x]);

        return collector;
    }

    private int[][] getColumns(int[][] board)
    {
        int [][] transposedBoard = new int[9][9];
        for(int r = 0; r < 9; r++)
            for(int c = 0; c < 9; c++)
                transposedBoard[c][r] = board[r][c];

        return transposedBoard;
    }

    private boolean checkSudokuSet(Stream<Integer> sudokuNumbers)
    {
        Set sudokuSet = sudokuNumbers.collect(Collectors.toSet());
        return sudokuSet.containsAll(validSudokuNumbers);
    }
}
