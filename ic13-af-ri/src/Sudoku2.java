import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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
public class Sudoku2 {
    private final List<List<Optional<Integer>>> puzzle;
    
    // Abstraction function:
    //     TODO
    
    // Rep invariant:
    //    - TODO: some conditions are missing here
    //    - containing only digits or blanks:
    //         puzzle.get(i).get(j) is either empty or contains {1,...,puzzle.size()} for all 0<=i,j<puzzle.size()
    //    - no positive digit appears more than once in any row, column, or block   
    
    // Safety from rep exposure:
    //     TODO
    
    
    //////////////////////////////////////////
    // private helper methods
    
    // assert the rep invariant
    private void checkRep() {
        /* TODO: implement the part of the rep invariant you added above */
        
        for (int row = 0; row < getPuzzleSize(); ++row) {
            Set<Integer> digits = new HashSet<>();
            for (int column = 0; column < getPuzzleSize(); ++column) {
                Optional<Integer> cell = puzzle.get(row).get(column);
                if (cell.isPresent()) {
                    assert !digits.contains(cell.get());
                    digits.add(cell.get());
                }
            }
        }
        
        for (int column = 0; column < getPuzzleSize(); ++column) {
            Set<Integer> digits = new HashSet<>();
            for (int row = 0; row < getPuzzleSize(); ++row) {
                Optional<Integer> cell = puzzle.get(row).get(column);
                if (cell.isPresent()) {
                    assert !digits.contains(cell.get());
                    digits.add(cell.get());
                }
            }
        }

        for (int block = 0; block < getPuzzleSize(); ++block) {
            Set<Integer> digits = new HashSet<>();
            for (int r = 0; r < getBlockSize(); ++r) {
                for (int c = 0; c < getBlockSize(); ++c) {
                    int row = (block/getBlockSize())*getBlockSize() + r;
                    int column = (block%getBlockSize())*getBlockSize() + c;
                    Optional<Integer> cell = puzzle.get(row).get(column);
                    if (cell.isPresent()) {
                        assert !digits.contains(cell.get());
                        digits.add(cell.get());
                    }
                }
            }
        }
    }
    
    /**
     * @return the size of this puzzle, e.g. 9 for a 9x9 Sudoku.
     */
    private int getPuzzleSize() {
        return puzzle.size();
    }
    
    /**
     * @return the block size of this puzzle, e.g. 3 for a 9x9 Sudoku.
     */
    private int getBlockSize() {
        return (int) Math.sqrt(getPuzzleSize());
    }


    /**
     * @param puzzle (see rep invariant for precondition on puzzle)
     * @return a copy of puzzle, so that mutating the original puzzle
     * won't affect the copy.
     */
    private static List<List<Optional<Integer>>> copyPuzzle(final List<List<Optional<Integer>>> puzzle) {
        final List<List<Optional<Integer>>> newPuzzle = new ArrayList<>();
        for (List<Optional<Integer>> row : puzzle) {
            List<Optional<Integer>> newRow = new ArrayList<>();
            newPuzzle.add(newRow);            
            for (Optional<Integer> cell : row) {
                newRow.add(cell);
            }
        }
        return newPuzzle;
    }

    /**
     * Make an empty puzzle rep, with all cells blank.
     * @param size size of puzzle, must be perfect square
     * @return blank puzzle of size x size
     */
    private static List<List<Optional<Integer>>> makeEmptyPuzzle(int size) {
        final List<List<Optional<Integer>>> puzzle = new ArrayList<>();
        for (int i = 0; i < size; ++i) {
            List<Optional<Integer>> row = new ArrayList<>();
            puzzle.add(row);
            for (int j = 0; j < size; ++j) {
                row.add(Optional.empty());
            }
        }
        return puzzle;
    }
    
    //////////////////////////////////////////
    // public operations
    
    /**
     * Make an empty Sudoku, with only blank squares, no digits.
     * @param puzzleSize size of the puzzle.  Must be a perfect square.
     * Typical Sudoku puzzles have puzzleSize==9.
     */
    public Sudoku2(int puzzleSize) {
        this.puzzle = makeEmptyPuzzle(puzzleSize);
        checkRep();
    }
    
    /**
     * Make a Sudoku and initializes its squares.
     * @param entries must be a 2D square array of dimension puzzleSizexpuzzleSize,
     * where entries[row][column] is one of the digits 1-9 (or 0 to represent a blank square),
     * and the entries follow the Sudoku row, column, and block constraints.
     */
    public Sudoku2(int[][] entries) {
        int size = entries.length;
        this.puzzle = makeEmptyPuzzle(size);
        for (int row = 0; row < size; ++row) {
            for (int column = 0; column < size; ++column) {
                int cell = entries[row][column];
                if (cell != 0) {
                    puzzle.get(row).set(column, Optional.of(cell));
                }
            }
        }
        checkRep();
    }
    
    /**
     * Copy a Sudoku.
     */
    public Sudoku2(Sudoku2 that) {
        this.puzzle = copyPuzzle(that.puzzle);
        checkRep();
    }
    
    
    
    // public operations
    
    /**
     * @param row    0 <= row < puzzleSize
     * @param column 0 <= column < puzzleSize
     * @return the set of digits that are entered in the same row of the puzzle 
     * as the cell at [row][column].  All elements in the set are in [1...puzzleSize].
     */
    public Set<Integer> getRow(int row, int column) {
        Set<Integer> digits = new HashSet<>();
        for (int i = 0; i < getPuzzleSize(); ++i) {
            Optional<Integer> cell = puzzle.get(row).get(i);
            if (cell.isPresent()) {
                digits.add(cell.get());
            }
        }
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
        for (int i = 0; i < getPuzzleSize(); ++i) {
            Optional<Integer> cell = puzzle.get(i).get(column);
            if (cell.isPresent()) {
                digits.add(cell.get());
            }
        }
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
        int firstRowOfBlock = (row / getBlockSize()) * getBlockSize();
        int firstColumnOfBlock = (column / getBlockSize()) * getBlockSize();
        for (int i = 0; i < getPuzzleSize(); ++i) {
            int cellRow = firstRowOfBlock + (i / getBlockSize());
            int cellColumn = firstColumnOfBlock + (i % getBlockSize()); 
            Optional<Integer> cell = puzzle.get(cellRow).get(cellColumn);
            if (cell.isPresent()) {
                digits.add(cell.get());
            }
        }
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
        for (int row = 0; row < getPuzzleSize(); ++row) {
            for (int column = 0; column < getPuzzleSize(); ++column) {
                if (!puzzle.get(row).get(column).isPresent()) {
                    // found an empty cell; try to fill it
                    Set<Integer> alreadyUsed = new HashSet<>();
                    alreadyUsed.addAll(getRow(row, column));
                    alreadyUsed.addAll(getColumn(row, column));
                    alreadyUsed.addAll(getBlock(row, column));

                    for (int digit = 1; digit <= getPuzzleSize(); ++digit) {
                        if (!alreadyUsed.contains(digit)) {
                            puzzle.get(row).set(column, Optional.of(digit));
                            checkRep();
                            if (solve()) {
                                checkRep();
                                return true;
                            }
                            // couldn't solve it with that choice,
                            // so clear the cell again and backtrack
                            puzzle.get(row).set(column, Optional.empty());
                            checkRep();
                        }
                    }

                    checkRep();
                    return false; // nothing works for this cell! give up and backtrack
                }
            }
        }

        checkRep();
        return true; // no empty cells found, so puzzle is already solved
    }
    
    /**
     * @return true if and only if each row, column, and block of
     * the puzzle uses all the digits 1...puzzleSize exactly once.
     */
    public boolean isSolved() {
        try {
            for (int row = 0; row < getPuzzleSize(); ++row) {
                if (getRow(row, 0).size() != getPuzzleSize()) return false;
            }
            for (int column = 0; column < getPuzzleSize(); ++column) {
                if (getColumn(0, column).size() != getPuzzleSize()) return false;
            }
            for (int row = 0; row < getPuzzleSize(); row += getBlockSize()) {
                for (int column = 0; column < getPuzzleSize(); column += getBlockSize()) {
                    if (getBlock(row, column).size() != getPuzzleSize()) return false;
                }
            }
            
            return true;
        } finally {
            checkRep();
        }
    }
    
    /**
     * @return string representation of puzzle, suitable for printing.
     */
    public String toString() {
        String result = "";
        for (int row = 0; row < getPuzzleSize(); ++row) {
            if (row > 0 && row % getBlockSize() == 0) {
                result += "\n";                    
            }
            for (int column = 0; column < getPuzzleSize(); ++column) {
                if (column > 0 && column % getBlockSize() == 0) {
                    result += " ";                    
                }
                Optional<Integer> cell = puzzle.get(row).get(column);
                if (cell.isPresent()) {
                    result += puzzle.get(row).get(column).get();
                } else {
                    result += "_";
                }
            }
            result += "\n";
        }
        checkRep();
        return result;
    }

}
