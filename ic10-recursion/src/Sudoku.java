
/**
 * Solver for Sudoku puzzles.  
 * 
 * A Sudoku puzzle is a square NxN grid of cells in which the numbers 1..N 
 * should be written so that each number appears exactly once in each row, column, and block
 * (where a block is a sqrt(N) x sqrt(N) grid of cells, and the whole grid is tiled by N blocks).
 * See http://en.wikipedia.org/wiki/Sudoku for more information.
 */
public class Sudoku {
    public static final int PUZZLE_SIZE = 9;
    public static final int BLOCK_SIZE = 3;
    
    /**
     * @param puzzle where
     *               puzzle.length= PUZZLE_SIZE and puzzle[i].length= PUZZLE_SIZE for 0<= i< PUZZLE_SIZE 
     *               puzzle[i][j] must come from  [0...PUZZLE_SIZE] for all valid i,j
     *               all the positive digits in puzzle[i] must not repeat in puzzle[i] for all valid i
     *               for all i != k (where i,j,k are valid indexes), if puzzle[i][j] == puzzle[k][j], then both are 0 
     * @param row    0 <= row < PUZZLE_SIZE
     * @param column 0 <= column < PUZZLE_SIZE
     * @return the row of the puzzle that contains the cell at [row][column],
     *  using 1...PUZZLE_SIZE for cells that contain numbers, and 0 for empty cells.
     */
    public static int[] getRow(int[][] puzzle, int row, int column) {
    	
    	
    	return puzzle[row];
        
    }

    /**
     * @param puzzle (TODO: preconditions on puzzle)
     * @param row    0 <= row < PUZZLE_SIZE
     * @param column 0 <= column < PUZZLE_SIZE
     * @return the column of the puzzle that contains the cell at [row][column],
     *  using 1...PUZZLE_SIZE for cells that contain numbers, and 0 for empty cells.
     */
    public static int[] getColumn(int[][] puzzle, int row, int column) {
        int[] cells = new int[PUZZLE_SIZE];
        for (int i = 0; i < PUZZLE_SIZE; ++i) {
            cells[i] = puzzle[i][column];
        }
        return cells;
    }

    /**
     * @param puzzle (TODO: preconditions on puzzle)
     * @param row    0 <= row < PUZZLE_SIZE
     * @param column 0 <= column < PUZZLE_SIZE
     * @return the block of the puzzle that contains the cell at [row][column],
     *  using 1...PUZZLE_SIZE for cells that contain numbers, and 0 for empty cells.
     *  The block's cells are represented in the returned array from left to right, 
     *  top to bottom.
     */
    public static int[] getBlock(int[][] puzzle, int row, int column) {
        int[] cells = new int[PUZZLE_SIZE];
        int firstRowOfBlock = (row / BLOCK_SIZE) * BLOCK_SIZE;
        int firstColumnOfBlock = (column / BLOCK_SIZE) * BLOCK_SIZE;
        for (int i = 0; i < PUZZLE_SIZE; ++i) {
            int cellRow = firstRowOfBlock + (i / BLOCK_SIZE);
            int cellColumn = firstColumnOfBlock + (i % BLOCK_SIZE); 
            cells[i] = puzzle[cellRow][cellColumn];
        }
        return cells;        
    }
    

    /**
     * @param puzzle (TODO: preconditions on puzzle)
     * @return true if the puzzle can be solved, and modifies
     * puzzle to fill in blank cells with a solution.
     * Returns false if no solution exists.
     */    
    public static boolean solve(int[][] puzzle) {
        throw new RuntimeException("TODO: implement me");
        
        // sketch of implementation:
        //  - find an empty cell
        //  - try all numbers that aren't already used in its row, column, block
        //  - fill the remaining empty cells
    }
    
    /**
     * @param puzzle (TODO: preconditions on puzzle)
     * @return true if and only if each row, column, and block of
     * the puzzle uses all the digits 1...PUZZLE_SIZE exactly once.
     */
    public static boolean isSolved(int[][] puzzle) {
        throw new RuntimeException("TODO: implement me");
        
        // sketch of implementation:
        //   for every cell in the puzzle,
        //     make sure its row, column, and block have exactly the digits 1...PUZZLE_SIZE
    }
    
    /**
     * @param puzzle (TODO: preconditions on puzzle)
     * @return string representation of puzzle, suitable for printing.
     */
    public static String toString(int[][] puzzle) {
        String result = "";
        for (int row = 0; row < PUZZLE_SIZE; ++row) {
            if (row > 0 && row % BLOCK_SIZE == 0) {
                result += "\n";                    
            }
            for (int column = 0; column < PUZZLE_SIZE; ++column) {
                if (column > 0 && column % BLOCK_SIZE == 0) {
                    result += " ";                    
                }
                int cell = puzzle[row][column];
                if (cell == 0) {
                    result += "_";
                } else {
                    result += puzzle[row][column];
                }
            }
            result += "\n";
        }
        return result;
    }
    
    /**
     * @param puzzle (TODO: preconditions on puzzle)
     * @return a copy of puzzle, so that mutating puzzle
     * won't affect the copy.
     */
    public static int[][] copyPuzzle(int[][] puzzle) {
        int[][] newPuzzle = new int[PUZZLE_SIZE][PUZZLE_SIZE];
        for (int i = 0; i < PUZZLE_SIZE; ++i) {
            for (int j = 0; j < PUZZLE_SIZE; ++j) {
                newPuzzle[i][j] = puzzle[i][j];
            }
        }
        return newPuzzle;
    }
}
