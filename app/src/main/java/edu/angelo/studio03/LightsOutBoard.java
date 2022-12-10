package edu.angelo.studio03;

// Darian Espinoza
// CS 3372

import java.util.Random;

public class LightsOutBoard {

    private final int numRows;

    private final int numColumns;

    private final int numColors;

    private int[][] lights;

    private static final Random random = new Random();

    public LightsOutBoard(int numRows, int numColumns, int numColors) {
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.numColors = numColors;
        this.lights = new int[numRows][numColumns];
        randomize();
    }

    public LightsOutBoard(int numRows, int numColumns) {
        this(numRows, numColumns, 2);
    }

    /**
     * Get the light value from a given row and column
     * @param row value of the row needed
     * @param column value of the column needed
     * @return
     */
    public int getLightColor(int row, int column) {
        try {
            return lights[row][column];
        } catch (ArrayIndexOutOfBoundsException ex) {
            // out of the bounds of the array, return 0
            return 0;
        }
    }

    /**
     * Used to decrement a given light value or wrap it around if it's 0
     * @param row value of the row needed
     * @param column value of the column needed
     */
    private void flip(int row, int column) {
        try {
            // initialize value of desired point in array to reduce clutter
            int val = lights[row][column];
            if (val > 0) {
                lights[row][column] = val - 1;
            } else {
                // val is 0, so wrap around to highest number
                lights[row][column] = numColors;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            // not a valid place in array, do nothing
        }
    }

    /**
     * Uses flip method to decrement given light and all the valid ones around it
     * @param row value of the row needed
     * @param column value of the column needed
     */
    public void click(int row, int column) {
        // call flip on given light
        flip(row, column);
        // call flip on light to the left
        flip(row, column - 1);
        // call flip on light to the right
        flip(row, column + 1);
        // call flip on light above given
        flip(row - 1, column);
        // call flip on light below given
        flip(row + 1, column);
    }

    /**
     * Same as previous click method, but accepts more than one click at a time
     * @param row value of the row needed
     * @param column value of the column needed
     * @param timesToClick
     */
    public void click(int row, int column, int timesToClick) {
        // use a for loop to call two-param click timesToClick times
        int index;
        for (index = 0; index < timesToClick; index += 1) {
            click(row, column);
        }
    }

    /**
     * Checks if all the light values of the board are 0 or not
     * @return boolean that indicates whether or not the board is cleared
     */
    public boolean isCleared() {
        int rowIndex;
        int colIndex;
        // run through each row
        for (rowIndex = 0; rowIndex < this.numRows; rowIndex += 1) {
            // run through each column in given row
            for (colIndex = 0; colIndex < this.numColumns; colIndex += 1) {
                // if any value in lights array is not 0, return false
                if (lights[rowIndex][colIndex] != 0) {
                    return false;
                }
            }
        }
        // reached end of array, all zeroes found, so return true
        return true;
    }

    /**
     * Initializes the board to 0, then uses Random to generate a random
     * light value for each space on the board, calls itself if the board
     * happens to end up cleared
     */
    public void randomize() {
        int rowIndex;
        int colIndex;
        // create random variable to use for number generation
        Random randGen = new Random();
        // run through each row
        for (rowIndex = 0; rowIndex < this.numRows; rowIndex += 1) {
            // run through each column in given row
            for (colIndex = 0; colIndex < this.numColumns; colIndex += 1) {
                // reset the element to 0
                lights[rowIndex][colIndex] = 0;
                // use randGen to set the light to a random int
                lights[rowIndex][colIndex] = randGen.nextInt(this.numColors);
            }
        }
        // check to ensure the board isn't cleared
        if (isCleared()) {
            // call randomize again since it happens to be cleared
            randomize();
        }
    }

    /**
     * Converts the lights board into a string formatted nicely for output
     * @return the string containing the entire board and its values
     */
    public String toString() {
        int rowIndex;
        int colIndex;
        String boardString = "";
        // run through each row
        for (rowIndex = 0; rowIndex < this.numRows; rowIndex += 1) {
            // start the row with "["
            boardString += "[";
            // run through each column in given row and add it to boardString
            for (colIndex = 0; colIndex < this.numColumns; colIndex += 1) {
                boardString += "" + this.lights[rowIndex][colIndex];
            }
            // end of row, add "]" and newline
            boardString += "]\n";
        }
        return boardString;
    }

    public static void main(String[] args) {
        LightsOutBoard board = new LightsOutBoard(4, 3, 3);
        System.out.print(board);
        System.out.println("-----");
        board.click(0, 2);
        System.out.print(board);
        System.out.println("");

        LightsOutBoard board2 = new LightsOutBoard(6, 7,5);
        System.out.print(board2);
        System.out.println("--------");
        // use 3 param click
        board2.click(1,1,3);
        System.out.print(board2);
        System.out.println("");

        // 2 param constructor
        LightsOutBoard board3 = new LightsOutBoard(3, 3);
        System.out.print(board3);
        System.out.println("-----");
        board3.click(2, 1);
        System.out.print(board3);
        System.out.println("-----");
        board3.click(0,0);
        board3.click(1,0);
        System.out.print(board3);
        if (board3.isCleared()) {
            System.out.println("You win!");
        } else {
            System.out.println("You haven't won yet.");
        }
    }
}

