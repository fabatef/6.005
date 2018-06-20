import java.util.HashSet;
import java.util.Set;


public class Sudoku {

    // Louis Reasoner likes all his variables up at the top
    // where he can keep a close eye on them.
    //public static Sudoku sudoku;
   // public static int puzzleSize;
    private int[][] puzzle;
   // public static int row, column, block;
   // public static int blockSize, firstRow, firstColumn;
   // public static HashSet<Integer> numbers;
    
    public static void main(String[] args) {
        int puzzleSize = 4;
        Sudoku sudoku = new Sudoku(puzzleSize);
        do {
            sudoku.fillRandomly();
            sudoku.print();
        } while (!sudoku.isSolved());
        System.out.println("solved!");
    }




    
    public Sudoku(int puzzleSize) {
    	
        puzzle = new int[puzzleSize][puzzleSize];
    }
    
    public void fillRandomly() {
        for (int row = 0; row < puzzle.length; ++row) {
            for (int column = 0; column < puzzle.length; ++column) {
                puzzle[row][column] = (int) (Math.random() * puzzle.length) + 1;
            }
        }
    }
    
    public boolean isSolved() {
        for (int row = 0; row < puzzle.length; ++row) {
            Set<Integer> numbers = new HashSet<>();
            for (int column = 0; column < puzzle.length; ++column) {
                if (numbers.contains(puzzle[row][column])) {
                    return false;
                }
                numbers.add(puzzle[row][column]);
            }
        }
        
        for (int column = 0; column < puzzle.length; ++column) {
            Set<Integer>numbers = new HashSet<>();
            for (int row = 0; row < puzzle.length; ++row) {
                if (numbers.contains(puzzle[row][column])) {
                    return false;
                }
                numbers.add(puzzle[row][column]);
            }
        }
                
        for (int block = 0; block < puzzle.length; ++block) {
            Set<Integer> numbers = new HashSet<>();
            int blockSize = (int)Math.sqrt(puzzle.length);
            int firstRow = block / blockSize;
            int firstColumn = block % blockSize;
            for (int row = 0; row < blockSize; ++row) {
                for (int column = 0; column < blockSize; ++column) {
                    if (numbers.contains(puzzle[firstRow+row][firstColumn+column])) {
                        return false;
                    }
                    numbers.add(puzzle[firstRow+row][firstColumn+column]);
                }
            }
        }
        
        return true;
    }

    public void print() {
        for (int row = 0; row < puzzle.length; ++row) {
            for (int column = 0; column < puzzle.length; ++column) {
                System.out.print(puzzle[row][column]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
