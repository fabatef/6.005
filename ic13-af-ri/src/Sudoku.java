import java.util.HashSet;
import java.util.Set;


/**
 * Mutable type representing Sudoku puzzles.  
 * 
 * A Sudoku puzzle is a square NxN grid of cells in which the numbers 1..N 
 * should be written so that each number appears exactly once in each row, column, and block
 * (where a block is a sqrt(N) x sqrt(N) grid of cells, and the whole grid is tiled by N blocks).
 * See http://en.wikipedia.org/wiki/Sudoku for more information.
 * 
 */
public class Sudoku {
    private final int[][] puzzle;
    private final int blockSize;
    private final int puzzleSize;
    
    // Abstraction function:
    //  represents a 2D square(puzzleSize X puzzleSize) Sudoku puzzle
    //  where the square at the ith row and jth column contains the number 
    //  at puzzle[i][j] or a blank if puzzle[i][j] == 0
    
    // Rep invariant:
    //    - sqrt(puzzleSize) * sqrt(puzzleSize)= blockSize
    //    - blockSize >=0
    //    - puzzleSize must be a perfect square
    //    - puzzleSize ==  puzzle[row].length
    //    - containing only digits or blanks:
    //         puzzle[i][j] in {0,...,puzzleSize} for all 0<=i,j<puzzleSize
    //    - no positive digit appears more than once in any row, column, or block   
    
    // Safety from rep exposure:
    //    All fields are private. The only mutable types in  the rep are int[][] and int[] and the only
    //    public method that takes or returns these types is Sudoku(int[][]), and it makes a defensive
    //    copy of its parameter before putting it into the rep. The copy producer Sudoku(sudoku) also makes a
    //    defensive copy of the puzzle array.

    
    
    //////////////////////////////////////////
    // private helper methods
    
    // assert the rep invariant
    private void checkRep() {
        // TODO: implement the part of the rep invariant you added above
    	assert ( blockSize*blockSize == puzzleSize);
    	assert ( blockSize>= 0);
 
        
        for (int row = 0; row < puzzleSize; ++row) {
        	assert(puzzleSize == puzzle[row].length);
            Set<Integer> digits = new HashSet<>();
            for (int column = 0; column < puzzleSize; ++column) {
                int nextDigit = puzzle[row][column];
                assert !digits.contains(nextDigit);
                if (nextDigit != 0) {
                    digits.add(nextDigit);
                }
            }
        }
        
        for (int column = 0; column < puzzleSize; ++column) {
            Set<Integer> digits = new HashSet<>();
            for (int row = 0; row < puzzleSize; ++row) {
                int nextDigit = puzzle[row][column];
                assert !digits.contains(nextDigit);
                if (nextDigit != 0) {
                    digits.add(nextDigit);
                }
            }
        }

        for (int block = 0; block < puzzleSize; ++block) {
            Set<Integer> digits = new HashSet<>();
            for (int r = 0; r < blockSize; ++r) {
                for (int c = 0; c < blockSize; ++c) {
                    int row = (block/blockSize)*blockSize + r;
                    int column = (block%blockSize)*blockSize + c;
                    int nextDigit = puzzle[row][column];
                    assert !digits.contains(nextDigit);
                    if (nextDigit != 0) {
                        digits.add(nextDigit);
                    }
                }
            }
        }
    }
    
    /**
     * @param puzzle (see rep invariant for precondition on puzzle)
     * @return a copy of puzzle, so that mutating the original puzzle
     * won't affect the copy.
     */
    private static int[][] copyPuzzle(final int[][] puzzle) {
        final int size = puzzle.length;
        final int[][] newPuzzle = new int[size][size];
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                newPuzzle[i][j] = puzzle[i][j];
            }
        }
        return newPuzzle;
    }


    
    //////////////////////////////////////////
    // public operations
    
    /**
     * Make an empty Sudoku, with only blank squares, no digits.
     * @param puzzleSize size of the puzzle.  Must be a perfect square.
     * Typical Sudoku puzzles have puzzleSize==9.
     */
    public Sudoku(int puzzleSize) {
        this.puzzleSize = puzzleSize;
        this.puzzle = new int[puzzleSize][puzzleSize];
        this.blockSize = (int) Math.sqrt(puzzleSize); // TODO: fix this
        // Java fills the arrays with 0 by default. */
    }
    
    /**
     * Make a Sudoku and initializes its squares.
     * @param entries must be a 2D square array of dimension puzzleSize x puzzleSize,
     * where puzzleSize is a perfect square.
     * where entries[row][column] is one of the digits 1-9 (or 0 to represent a blank square),
     * and the entries follow the Sudoku row, column, and block constraints.
     */
    public Sudoku(int[][] entries) {
        this.puzzleSize = entries.length;
        this.blockSize = (int) Math.sqrt(puzzleSize); // TODO: fix this
        this.puzzle = copyPuzzle(entries);
        checkRep();
    }
    
    /**
     * Copy a Sudoku.
     */
    public Sudoku(Sudoku that) {
        this.puzzleSize = that.puzzleSize;
        this.blockSize = that.blockSize;
        this.puzzle = copyPuzzle(that.puzzle);
        checkRep();
    }
    
    
    /**
     * @param row    0 <= row < puzzleSize
     * @param column 0 <= column < puzzleSize
     * @return the set of digits that are entered in the same row of the puzzle 
     * as the cell at [row][column].  All elements in the set are in [1...puzzleSize].
     */
    public Set<Integer> getRow(int row, int column) {
        Set<Integer> digits = new HashSet<>();
        for (int i = 0; i < puzzleSize; ++i) {
            digits.add(puzzle[row][i]);
        }
        digits.remove(0); // don't include empty cells
        checkRep();
        return digits;
        
    }

    /**
     * @param row    0 <= row < puzzleSize
     * @param column 0 <= column < puzzleSize
     * @return the set of digits that are entered in the same column of the puzzle 
     * as the cell at [row][column].  All elements in the set are in [1...puzzleSize].
     */
    public Set<Integer> getColumn(int row, int column) {
        Set<Integer> digits = new HashSet<>();
        for (int i = 0; i < puzzleSize; ++i) {
            digits.add(puzzle[i][column]);
        }
        digits.remove(0); // don't include empty cells
        checkRep();
        return digits;
    }

    /**
     * @param row    0 <= row < puzzleSize
     * @param column 0 <= column < puzzleSize
     * @return the set of digits that are entered in the same block of the puzzle 
     * as the cell at [row][column].  All elements in the set are in [1...puzzleSize].
     */
    public Set<Integer> getBlock(int row, int column) {
        Set<Integer> digits = new HashSet<>();
        int firstRowOfBlock = (row / blockSize) * blockSize;
        int firstColumnOfBlock = (column / blockSize) * blockSize;
        for (int i = 0; i < puzzleSize; ++i) {
            int cellRow = firstRowOfBlock + (i / blockSize);
            int cellColumn = firstColumnOfBlock + (i % blockSize); 
            digits.add(puzzle[cellRow][cellColumn]);
        }
        digits.remove(0); // don't include empty cells
        checkRep();
        return digits;
    }
    

    /**
     * @return true if the puzzle can be solved, and modifies
     * puzzle to fill in blank cells with a solution.
     * Returns false if no solution exists.
     */    
    public boolean solve() {
        // find an empty cell
    try{
    		
        for (int row = 0; row < puzzleSize; ++row) {
            for (int column = 0; column < puzzleSize; ++column) {
                if (puzzle[row][column] == 0) {
                    // found an empty cell; try to fill it
                    Set<Integer> alreadyUsed = new HashSet<>();
                    alreadyUsed.addAll(getRow(row, column));
                    alreadyUsed.addAll(getColumn(row, column));
                    alreadyUsed.addAll(getBlock(row, column));

                    for (int digit = 1; digit <= puzzleSize; ++digit) {
                        if (!alreadyUsed.contains(digit)) {
                            puzzle[row][column] = digit;
                            if (solve()) {
                                return true;
                            }
                            // couldn't solve it with that choice,
                            // so clear the cell again and backtrack
                            puzzle[row][column] = 0;
                        }
                    }
                    //checkRep();                    
                    return false; // nothing works for this cell! give up and backtrack
                }
            }
        }
        //checkRep();
        return true; // no empty cells found, so puzzle is already solved
    } 
    
    finally {
    	checkRep();
    }
    }
    /**
     * @return true if and only if each row, column, and block of
     * the puzzle uses all the digits 1...puzzleSize exactly once.
     */
    public boolean isSolved() {
        for (int row = 0; row < puzzleSize; ++row) {
            if (getRow(row, 0).size() != puzzleSize) return false;
        }
        for (int column = 0; column < puzzleSize; ++column) {
            if (getColumn(0, column).size() != puzzleSize) return false;
        }
        for (int row = 0; row < puzzleSize; row += blockSize) {
            for (int column = 0; column < puzzleSize; column += blockSize) {
                if (getBlock(row, column).size() != puzzleSize) return false;
            }
        }
        checkRep();
        return true;
    }
    
    /**
     * @return string representation of puzzle, suitable for printing.
     */
    public String toString() {
        String result = "";
        for (int row = 0; row < puzzleSize; ++row) {
            if (row > 0 && row % blockSize == 0) {
                result += "\n";                    
            }
            for (int column = 0; column < puzzleSize; ++column) {
                if (column > 0 && column % blockSize == 0) {
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
        checkRep();
        return result;
    }
    
}
