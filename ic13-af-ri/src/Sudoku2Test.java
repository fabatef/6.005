import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;


public class Sudoku2Test {
    // Testing strategy.
    //
    // For getRow/getColumn/getBlock, partition into 
    //    row=0, row=max, 0<row<max
    //    column=0, column=max, 0<column<max
    //    block is on left, middle, right
    //    block is on top, middle, bottom
    //    # blanks in row/col/block: 0, 1, >1, all
    //
    // For solve/isSolved, partition into:
    //    # blanks in puzzle: 0, 1, >1, all
    //    # blanks in one row:    0, 1, >1, all
    //    # blanks in one column: 0, 1, >1, all
    //    # blanks in one block:  0, 1, >1, all
    

    private static int[][] solvedPuzzle = new int[][] {
        { 2, 4, 8,  3, 9, 5,  7, 1, 6, },
        { 5, 7, 1,  6, 2, 8,  3, 4, 9, },
        { 9, 3, 6,  7, 4, 1,  5, 8, 2, },

        { 6, 8, 2,  5, 3, 9,  1, 7, 4, },
        { 3, 5, 9,  1, 7, 4,  6, 2, 8, },
        { 7, 1, 4,  8, 6, 2,  9, 5, 3, },

        { 8, 6, 3,  4, 1, 7,  2, 9, 5, },
        { 1, 9, 5,  2, 8, 6,  4, 3, 7, },
        { 4, 2, 7,  9, 5, 3,  8, 6, 1, },
    };

    
    // covers row=0
    @Test
    public void testTopRow() {
        Sudoku2 sudoku = new Sudoku2(solvedPuzzle);
        assertEquals(new HashSet<>(Arrays.asList(2, 4, 8,  3, 9, 5,  7, 1, 6)), 
                     sudoku.getRow(0, 0));
    }

    // covers 0<row<max
    @Test
    public void testInnerRow() {
        Sudoku2 sudoku = new Sudoku2(solvedPuzzle);
        assertEquals(new HashSet<>(Arrays.asList(6, 8, 2,  5, 3, 9,  1, 7, 4)), 
                     sudoku.getRow(3, 0));
    }

    // covers row=max
    @Test
    public void testBottomRow() {
        Sudoku2 sudoku = new Sudoku2(solvedPuzzle);
        assertEquals(new HashSet<>(Arrays.asList(4, 2, 7,  9, 5, 3,  8, 6, 1)), 
                     sudoku.getRow(8, 0));
    }

    
    // covers column=0
    @Test
    public void testLeftColumn() {
        Sudoku2 sudoku = new Sudoku2(solvedPuzzle);
        assertEquals(new HashSet<>(Arrays.asList(2, 5, 9,  6, 3, 7,  8, 1, 4)), 
                     sudoku.getColumn(4, 0));
    }

    // covers 0<column<max
    @Test
    public void testInnerColumn() {
        Sudoku2 sudoku = new Sudoku2(solvedPuzzle);
        assertEquals(new HashSet<>(Arrays.asList(5, 8, 1,  9, 4, 2,  7, 6, 3)), 
                     sudoku.getColumn(7, 5));
    }

    // covers column=max
    @Test
    public void testRightColumn() {
        Sudoku2 sudoku = new Sudoku2(solvedPuzzle);
        assertEquals(new HashSet<>(Arrays.asList(6, 9, 2,  4, 8, 3,  5, 7, 1)), 
                     sudoku.getColumn(1, 8));
    }

    
    @Test
    public void testNorthwestBlock() {
        Sudoku2 sudoku = new Sudoku2(solvedPuzzle);
        assertEquals(new HashSet<>(Arrays.asList(2, 4, 8,  5, 7, 1,  9, 3, 6)), 
                     sudoku.getBlock(0, 0));
    }
    
    @Test
    public void testSouthBlock() {
        Sudoku2 sudoku = new Sudoku2(solvedPuzzle);
        assertEquals(new HashSet<>(Arrays.asList(4, 1, 7,  2, 8, 6,  9, 5, 3)), 
                     sudoku.getBlock(7, 5));
    }
    
    @Test
    public void testEastBlock() {
        Sudoku2 sudoku = new Sudoku2(solvedPuzzle);
        assertEquals(new HashSet<>(Arrays.asList(1, 7, 4,  6, 2, 8,  9, 5, 3)), 
                     sudoku.getBlock(4, 8));
    }
    
    // covers no blanks, 0 blanks in a row/column/block
    @Test
    public void testSolveAlreadySolved() {
        int[][] puzzle = new int[][] {
            { 2, 4, 8,  3, 9, 5,  7, 1, 6, },
            { 5, 7, 1,  6, 2, 8,  3, 4, 9, },
            { 9, 3, 6,  7, 4, 1,  5, 8, 2, },

            { 6, 8, 2,  5, 3, 9,  1, 7, 4, },
            { 3, 5, 9,  1, 7, 4,  6, 2, 8, },
            { 7, 1, 4,  8, 6, 2,  9, 5, 3, },

            { 8, 6, 3,  4, 1, 7,  2, 9, 5, },
            { 1, 9, 5,  2, 8, 6,  4, 3, 7, },
            { 4, 2, 7,  9, 5, 3,  8, 6, 1, },
        };
        Sudoku2 sudoku = new Sudoku2(puzzle);        
        assertEquals(true, sudoku.solve());
        assertEquals(true, sudoku.isSolved());
    }
    

    // covers 1 blank, 0 blanks in a row/column/block
    @Test
    public void testSolveOneEmptyCell() {
        int[][] puzzle = new int[][] {
            { 2, 4, 8,  3, 9, 5,  7, 1, 6, },
            { 5, 7, 1,  6, 2, 8,  3, 4, 9, },
            { 9, 3, 6,  7, 4, 1,  5, 8, 2, },

            { 6, 8, 2,  0, 3, 9,  1, 7, 4, },
            { 3, 5, 9,  1, 7, 4,  6, 2, 8, },
            { 7, 1, 4,  8, 6, 2,  9, 5, 3, },

            { 8, 6, 3,  4, 1, 7,  2, 9, 5, },
            { 1, 9, 5,  2, 8, 6,  4, 3, 7, },
            { 4, 2, 7,  9, 5, 3,  8, 6, 1, },
        };
        Sudoku2 sudoku = new Sudoku2(puzzle);        
        assertEquals(true, sudoku.solve());
        assertEquals(true, sudoku.isSolved());
    }

    // covers >1 blanks in a row/column/block/puzzle
    @Test
    public void testSolveSeveralEmptyCells() {
        int[][] puzzle = new int[][] {
            { 2, 4, 8,  3, 9, 5,  7, 1, 6, },
            { 5, 7, 1,  6, 2, 8,  3, 4, 9, },
            { 9, 3, 6,  7, 4, 1,  5, 8, 2, },

            { 6, 8, 2,  0, 3, 9,  1, 7, 4, },
            { 3, 5, 9,  1, 7, 4,  6, 2, 8, },
            { 7, 1, 4,  8, 6, 2,  9, 5, 3, },

            { 8, 6, 3,  4, 1, 7,  0, 9, 5, },
            { 1, 9, 5,  0, 8, 6,  4, 3, 0, },
            { 4, 2, 7,  9, 5, 3,  8, 6, 1, },
        };
        Sudoku2 sudoku = new Sudoku2(puzzle);        
        assertEquals(true, sudoku.solve());
        assertEquals(true, sudoku.isSolved());
    }

    // covers all blanks, >1 blank in a row/column/block
    @Test
    public void testSolveEmptyPuzzle() {
        Sudoku2 sudoku = new Sudoku2(9);        
        assertEquals(true, sudoku.solve());
        assertEquals(true, sudoku.isSolved());
    }

    /**
     * Tests that assertions are enabled.
     */
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false;
    }
}
