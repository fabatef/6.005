import java.util.HashSet;
import java.util.Set;


/**
 * Solver for Sudoku puzzles.  
 * 
 * A Sudoku puzzle is a square NxN grid of cells in which the numbers 1..N 
 * should be written so that each number appears exactly once in each row, column, and block
 * (where a block is a sqrt(N) x sqrt(N) grid of cells, and the whole grid is tiled by N blocks).
 * See http://en.wikipedia.org/wiki/Sudoku for more information.
 * 
 */
public class Sudoku {
    public static final int BLOCK_SIZE = 3;
    public static final int PUZZLE_SIZE = BLOCK_SIZE * BLOCK_SIZE;
    
    /**
     * In the methods of this class, the int[][] puzzle parameter has the following
     * preconditions:
     *   - it's a square array:  
     *        puzzle.length==PUZZLE_SIZE
     *        puzzle[i].length==PUZZLE_SIZE for all 0<=i<PUZZLE_SIZE
     *   - containing only digits or blanks:
     *        puzzle[i][j] in {0,...,PUZZLE_SIZE} for all 0<=i,j<PUZZLE_SIZE
     *   - no positive digit appears more than once in any row, column, or block
     */
    
    /**
     * @param puzzle (see above for precondition on puzzle)
     * @param row    0 <= row < PUZZLE_SIZE
     * @param column 0 <= column < PUZZLE_SIZE
     * @return the set of digits that are entered in the same row of the puzzle 
     * as the cell at [row][column].  All elements in the set are in [1...PUZZLE_SIZE].
     */
    public static Set<Integer> getRow(int[][] puzzle, int row, int column) {
        Set<Integer> digits = new HashSet<>();
        for (int i = 0; i < PUZZLE_SIZE; ++i) {
            digits.add(puzzle[row][i]);
        }
        digits.remove(0); // don't include empty cells
        return digits;
    }

    /**
     * @param puzzle (see above for precondition on puzzle)
     * @param row    0 <= row < PUZZLE_SIZE
     * @param column 0 <= column < PUZZLE_SIZE
     * @return the set of digits that are entered in the same column of the puzzle 
     * as the cell at [row][column].  All elements in the set are in [1...PUZZLE_SIZE].
     */
    public static Set<Integer> getColumn(int[][] puzzle, int row, int column) {
        Set<Integer> digits = new HashSet<>();
        for (int i = 0; i < PUZZLE_SIZE; ++i) {
            digits.add(puzzle[i][column]);
        }
        digits.remove(0); // don't include empty cells
        return digits;
    }

    /**
     * @param puzzle (see above for precondition on puzzle)
     * @param row    0 <= row < PUZZLE_SIZE
     * @param column 0 <= column < PUZZLE_SIZE
     * @return the set of digits that are entered in the same block of the puzzle 
     * as the cell at [row][column].  All elements in the set are in [1...PUZZLE_SIZE].
     */
    public static Set<Integer> getBlock(int[][] puzzle, int row, int column) {
        Set<Integer> digits = new HashSet<>();
        int firstRowOfBlock = (row / BLOCK_SIZE) * BLOCK_SIZE;
        int firstColumnOfBlock = (column / BLOCK_SIZE) * BLOCK_SIZE;
        for (int i = 0; i < PUZZLE_SIZE; ++i) {
            int cellRow = firstRowOfBlock + (i / BLOCK_SIZE);
            int cellColumn = firstColumnOfBlock + (i % BLOCK_SIZE); 
            digits.add(puzzle[cellRow][cellColumn]);
        }
        digits.remove(0); // don't include empty cells
        return digits;
    }
    

    /**
     * @param puzzle (see above for precondition on puzzle)
     * @return true if the puzzle can be solved, and modifies
     * puzzle to fill in blank cells with a solution.
     * Returns false if no solution exists.
     */    
    public static boolean solve(int[][] puzzle) {
        assertValid(puzzle);
        //assrtValid can be used as debugging tool
        // find an empty cell
        for (int row = 0; row < PUZZLE_SIZE; ++row) {
            for (int column = 0; column < PUZZLE_SIZE; ++column) {
                if (puzzle[row][column] == 0) {
                    // found an empty cell; try to fill it
                    Set<Integer> alreadyUsed = new HashSet<>();
                    alreadyUsed.addAll(getRow(puzzle, row, column));
                    alreadyUsed.addAll(getColumn(puzzle, row, column));
                    alreadyUsed.addAll(getBlock(puzzle, row, column));
                    assertValid(puzzle);

                    for (int digit = 1; digit <= PUZZLE_SIZE; ++digit) {
                        if (!alreadyUsed.contains(digit)) {
                            puzzle[row][column] = digit;
                            //System.out.println(digit);
                            assertValid(puzzle);  // shows the digit already appears in the row
                            if (solve(puzzle)) {
                                return true;
                            }
                        }
                    }
                    
                    return false; // nothing works for this cell! give up and backtrack
                }
            }
        }
        return true; // no empty cells found, so puzzle is already solved
    }
    
    /**
     * @param puzzle (see above for precondition on puzzle)
     * @return true if and only if each row, column, and block of
     * the puzzle uses all the digits 1...PUZZLE_SIZE exactly once.
     */
    public static boolean isSolved(int[][] puzzle) {
        for (int row = 0; row < PUZZLE_SIZE; ++row) {
            if (getRow(puzzle, row, 0).size() != PUZZLE_SIZE) return false;
        }
        for (int column = 0; column < PUZZLE_SIZE; ++column) {
            if (getColumn(puzzle, 0, column).size() != PUZZLE_SIZE) return false;
        }
        for (int row = 0; row < PUZZLE_SIZE; row += BLOCK_SIZE) {
            for (int column = 0; column < PUZZLE_SIZE; column += BLOCK_SIZE) {
                if (getBlock(puzzle, row, column).size() != PUZZLE_SIZE) return false;
            }
        }
        return true;
    }
    
    /**
     * @param puzzle (see above for precondition on puzzle)
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
     * @param puzzle (see above for precondition on puzzle)
     * @return a copy of puzzle, so that mutating the original puzzle
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

    // asserts that puzzle follows the precondition at the top of this class
    private static void assertValid(int[][] puzzle) {
        assert puzzle.length == PUZZLE_SIZE;
        for (int[] row : puzzle) {
            assert row.length == PUZZLE_SIZE;
        }
        
        for (int row = 0; row < PUZZLE_SIZE; ++row) {
            Set<Integer> digits = new HashSet<>();
            for (int column = 0; column < PUZZLE_SIZE; ++column) {
                int nextDigit = puzzle[row][column];
                assert !digits.contains(nextDigit);  //inserting a breakpoint. When the program runs, it stops here.
                if (nextDigit != 0) {
                    digits.add(nextDigit);
                }
            }
        }
        
        for (int column = 0; column < PUZZLE_SIZE; ++column) {
            Set<Integer> digits = new HashSet<>();
            for (int row = 0; row < PUZZLE_SIZE; ++row) {
                int nextDigit = puzzle[row][column];
                assert !digits.contains(nextDigit);
                if (nextDigit != 0) {
                    digits.add(nextDigit);
                }
            }
        }

        for (int block = 0; block < PUZZLE_SIZE; ++block) {
            Set<Integer> digits = new HashSet<>();
            for (int r = 0; r < BLOCK_SIZE; ++r) {
                for (int c = 0; c < BLOCK_SIZE; ++c) {
                    int row = (block/BLOCK_SIZE)*BLOCK_SIZE + r;
                    int column = (block%BLOCK_SIZE)*BLOCK_SIZE + c;
                    int nextDigit = puzzle[row][column];
                    assert !digits.contains(nextDigit);
                    if (nextDigit != 0) {
                        digits.add(nextDigit);
                    }
                }
            }
        }
    }
}
