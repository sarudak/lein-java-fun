import java.util.*;

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
            .allMatch(this::checkSudokuSet);
        boolean columnsAreValid =
            Arrays.stream(getColumns(board))
            .allMatch(this::checkSudokuSet);
            
        return rowsAreValid && columnsAreValid;
    }

    private int[][] getColumns(int[][] board)
    {
        int [][] transposedBoard = new int[9][9];
        for(int r = 0; r < 9; r++)
        {
            for(int c = 0; c < 9; c++)
            {
                transposedBoard[c][r] = board[r][c];
            }
        }
        return transposedBoard;
    }

    private boolean checkSudokuSet(int[] sudokuSet)
    {
        Set sudokuNumbers = new HashSet();
        for(int item : sudokuSet)
        {
            if(!validSudokuNumbers.contains(item))
                return false;
            sudokuNumbers.add(item);
        }
        return sudokuNumbers.size() == 9;
    }
}
