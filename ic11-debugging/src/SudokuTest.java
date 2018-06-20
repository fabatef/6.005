import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;


public class SudokuTest {
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
        assertEquals(new HashSet<>(Arrays.asList(2, 4, 8,  3, 9, 5,  7, 1, 6)), 
                     Sudoku.getRow(solvedPuzzle, 0, 0));
    }

    // covers 0<row<max
    @Test
    public void testInnerRow() {
        assertEquals(new HashSet<>(Arrays.asList(6, 8, 2,  5, 3, 9,  1, 7, 4)), 
                     Sudoku.getRow(solvedPuzzle, 3, 0));
    }

    // covers row=max
    @Test
    public void testBottomRow() {
        assertEquals(new HashSet<>(Arrays.asList(4, 2, 7,  9, 5, 3,  8, 6, 1)), 
                     Sudoku.getRow(solvedPuzzle, 8, 0));
    }

    
    // covers column=0
    @Test
    public void testLeftColumn() {
        assertEquals(new HashSet<>(Arrays.asList(2, 5, 9,  6, 3, 7,  8, 1, 4)), 
                     Sudoku.getColumn(solvedPuzzle, 4, 0));
    }

    // covers 0<column<max
    @Test
    public void testInnerColumn() {
        assertEquals(new HashSet<>(Arrays.asList(5, 8, 1,  9, 4, 2,  7, 6, 3)), 
                     Sudoku.getColumn(solvedPuzzle, 7, 5));
    }

    // covers column=max
    @Test
    public void testRightColumn() {
        assertEquals(new HashSet<>(Arrays.asList(6, 9, 2,  4, 8, 3,  5, 7, 1)), 
                     Sudoku.getColumn(solvedPuzzle, 1, 8));
    }

    
    @Test
    public void testNorthwestBlock() {
        assertEquals(new HashSet<>(Arrays.asList(2, 4, 8,  5, 7, 1,  9, 3, 6)), 
                     Sudoku.getBlock(solvedPuzzle, 0, 0));
    }
    
    @Test
    public void testSouthBlock() {
        assertEquals(new HashSet<>(Arrays.asList(4, 1, 7,  2, 8, 6,  9, 5, 3)), 
                     Sudoku.getBlock(solvedPuzzle, 7, 5));
    }
    
    @Test
    public void testEastBlock() {
        assertEquals(new HashSet<>(Arrays.asList(1, 7, 4,  6, 2, 8,  9, 5, 3)), 
                     Sudoku.getBlock(solvedPuzzle, 4, 8));
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
        int[][] solution = Sudoku.copyPuzzle(puzzle);
        
        assertEquals(true, Sudoku.solve(puzzle));
        assert2DArrayEquals(solution, puzzle);
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
        int[][] solution = Sudoku.copyPuzzle(puzzle);
        solution[3][3] = 5;
        
        assertEquals(true, Sudoku.solve(puzzle));
        assert2DArrayEquals(solution, puzzle);
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
        int[][] solution = Sudoku.copyPuzzle(puzzle);
        solution[3][3] = 5;
        solution[7][3] = 2;
        solution[6][6] = 2;
        solution[7][8] = 7;
        
        assertEquals(true, Sudoku.solve(puzzle));
        assert2DArrayEquals(solution, puzzle);
    }

    // covers all blanks, >1 blank in a row/column/block
    @Test
    public void testSolveEmptyPuzzle() {
        // start with a solved puzzle
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
        
        // FAILS empty it completely, one row at a time
 //       for (int[] row : puzzle) {
 //           Arrays.fill(row, 0);
 //      }
        
        //using binary search to minimize the problem
        
        //FAILS 4 empty rows
//        for (int i=0; i<4; ++i){
//        	Arrays.fill(puzzle[i],0);
//        }
        
          //FAILS 2 empty rows
//        for(int i = 0; i<2 ; ++i){
//        	Arrays.fill(puzzle[i], 0);
//        }
        
        //FAILS 1 empty rows
//        for(int i=0; i<1; ++i){
//        	Arrays.fill(puzzle[i], 0);
//        }
        
        //0 empty rows passes...
        
        for (int i=0; i<2; ++i){
        	Arrays.fill(puzzle[i], 2, 4, 0);
        	Arrays.fill(puzzle[i], 5, 8, 0);
        }
        
        // solve the empty puzzle
        System.out.println("before:\n" + Sudoku.toString(puzzle));
        assertEquals(true, Sudoku.solve(puzzle));
        System.out.println("after:\n" + Sudoku.toString(puzzle));
        
        // we have to depend on isSolved() here, because
        // solve() is free to return *any* solution, not
        // necessarily the one we started with
        assertEquals(true, Sudoku.isSolved(puzzle));
    }
    
    private static void assert2DArrayEquals(int[][] expected, int[][] actual) {
        assertEquals(expected.length, actual.length);
        for (int i = 0; i < expected.length; ++i) {
            assertEquals(expected[i].length, actual[i].length);
            for (int j = 0; j < expected[i].length; ++j) {
                assertEquals(expected[i][j], actual[i][j]);
            }
        }
    }
    
}
