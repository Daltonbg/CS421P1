
import java.util.ArrayList;

/**
 * Board class that contains the main algorithm for search and board
 * filling/creation
 *
 * @author Dalton Bilau Goncalves
 */
public class KnightBoard {

    private Position[][] board;
    private int heuristic;
    private int size;
    private int xStart;
    private int yStart;
    private int movesCount;
    private int nextPlacement;
    private boolean solutionFound;
    // Basic Search
    int[] xMove = {-2, -1, 1, 2, 2, 1, -1, -2};
    int[] yMove = {1, 2, 2, 1, -1, -2, -2, -1};

    /**
     * Constructor for KnightBoard class, approprietly initalizes variables and
     * initialize methods to create the matrix and initialize the filling of the
     * board
     *
     * @param heuristic method of filling
     * @param size size of the board
     * @param xStart starting x position for filling
     * @param yStart starting y position for filling
     */
    public KnightBoard(int heuristic, int size, int xStart, int yStart) {
        //Initializers for the constructor
        this.heuristic = heuristic;
        this.size = size;
        this.xStart = xStart;
        this.yStart = yStart;

        //Initializes the board
        this.movesCount = 1;
        this.nextPlacement = 1;
        board = new Position[size][size];
        writeBoard();

        //Places initial move
        Position initial = board[xStart][yStart];
        initial.setFilling(nextPlacement);
        nextPlacement++;

        //Initializes the iterations for each type
        //of search method, if found sets solutionFound to true so that
        //toString method knows what headline to print
        if (heuristic == 0) {
            if (basicSearch(initial)) {
                solutionFound = true;
            }
        } else if (heuristic == 1) {
            if (heuristicI(initial)) {
                solutionFound = true;
            }
        } else {
            if (heuristicII(initial)) {
                solutionFound = true;
            }
        }
    }

    //========================================= Methods for board construction =========================================
    /**
     * Simple helper function that fills the board with Positions these position
     * will initialize with filling 0 and distance from border calculated
     */
    private void writeBoard() {
        int distance = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                distance = calculateDistance(j, i);
                board[i][j] = new Position(j, i, distance);
            }
        }
    }

    /**
     * Helped method that calculates distance from each position to the border
     * Used when building the board with all its positions
     *
     * @param x the x axis of the position
     * @param y the y axis of the position
     * @return distance between that specific position and the border
     */
    protected int calculateDistance(int x, int y) {
        int distance = 0;
        int mid = (size - 1) / 2; // middle index
        if (x <= mid && y <= mid) {
            distance = x + y;
        } else if (x <= mid && y > mid) {
            distance = x + (size - 1 - y);
        } else if (x > mid && y <= mid) {
            distance = (size - 1 - x) + y;
        } else {
            distance = (size - 1 - x) + (size - 1 - y);
        }
        return distance;
    }

    //============================================ Helper method for search ============================================
    /**
     * Helper getter method that gets a specific position from the built board
     *
     * @param x the x axis of the position
     * @param y the y axis of the position
     * @return the specific object position in the board
     */
    public Position getPosition(int x, int y) {
        return board[y][x];
    }

    /**
     * Helped method that checks if position is valid
     *
     * @param x the x axis of the position
     * @param y the y axis of the position
     * @return true if position is valid, false otherwise
     */
    protected boolean isValid(int x, int y) {
        if (x < size && y < size && x >= 0 && y >= 0) {
            return true;
        }
        return false;
    }

    /**
     * Simple helper method that helps by counting the next moves Used in
     * HeuritisticII so that the best path can be decided
     *
     * @param pos position that we will be counting the next moves
     * @return the number of moves
     */
    protected int countMoves(Position pos) {
        int count = 0;
        int[] xMove = {-2, -1, 1, 2, 2, 1, -1, -2};
        int[] yMove = {1, 2, 2, 1, -1, -2, -2, -1};

        for (int i = 0; i < 8; i++) {
            int xNext = pos.getX() + xMove[i];
            int yNext = pos.getY() + yMove[i];
            if (isValid(xNext, yNext) && getPosition(xNext, yNext).getFilling() == 0) {
                count++;
            }
        }
        return count;
    }

    //============================================ Recursive search methods ============================================
    /**
     * Recursive method used in basicSearch, recursively fills the board till
     * all the positions are found and filled by simply picking moves based on a
     * clockwise position
     *
     * @param current current position to fill
     * @return true if board is filled, false if cannot find a fill and needs to
     * backtrack
     */
    private boolean basicSearch(Position current) {
        if (nextPlacement > (size * size)) {
            return true;
        } else {
            current.calculateMoves(this);
            ArrayList<Position> eligibleMoves = current.getEligibleMoves();
            for (int i = 0; i < current.numberMoves(); i++) {
                Position next = eligibleMoves.get(i);
                if (next != null) {
                    if (next.getFilling() == 0) {
                        next.setFilling(nextPlacement);
                        nextPlacement++;
                        movesCount++;
                        if (basicSearch(next) == false) {
                            next.setFilling(0);
                            nextPlacement--;
                            continue;
                        } else {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Recursive method used in heuristicI, recursively fills the board till all
     * the positions are found and filled by trying to find the allowed move
     * closer to the border, if two equal moves then the first move in a
     * clockwise rotation
     *
     * @param current current position to fill
     * @return true if board is filled, false if cannot find a fill and needs to
     * backtrack
     */
    private boolean heuristicI(Position current) {
        if (nextPlacement > (size * size)) {
            return true;
        } else {
            current.calculateMoves(this);
            current.sortEligible();
            ArrayList<Position> eligibleMoves = current.getEligibleMoves();
            for (int i = 0; i < current.numberMoves(); i++) {
                Position next = eligibleMoves.get(i);
                if (next != null) {
                    if (next.getFilling() == 0) {
                        next.setFilling(nextPlacement);
                        nextPlacement++;
                        movesCount++;
                        if (heuristicI(next) == false) {
                            next.setFilling(0);
                            nextPlacement--;
                            continue;
                        } else {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Recursive method used in heuristicII, recursively fills the board till
     * all the positions are found and filled. Count next moves so as to find
     * the optimal position
     *
     * @param current current position to fill
     * @return true if board is filled, false if cannot find a fill and needs to
     * backtrack
     */
    private boolean heuristicII(Position current) {
        if (nextPlacement > (size * size)) {
            return true;
        } else {
            current.calculateMoves(this);
            current.sortEligibleII(this);
            ArrayList<Position> eligibleMoves = current.getEligibleMoves();
            for (int i = 0; i < current.numberMoves(); i++) {
                Position next = eligibleMoves.get(i);
                if (next != null) {
                    if (next.getFilling() == 0) {
                        next.setFilling(nextPlacement);
                        nextPlacement++;
                        movesCount++;
                        if (heuristicII(next) == false) {
                            next.setFilling(0);
                            nextPlacement--;
                            continue;
                        } else {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    //============================================ Finalizer/Printing method ===========================================
    /**
     * Method that creates a string and inform the user of moves count and
     * prints the board
     */
    public String toString() {
        String newMatrixString = "The total number of moves is " + movesCount + "\n";
        if (solutionFound == true) {
            for (int i = 0; i < size; i++) {
                newMatrixString += "\t";
                for (int j = 0; j < size; j++) {
                    newMatrixString += board[i][j].getFilling() + " ";
                }
                newMatrixString += "\n";
            }
        } else {
            newMatrixString += "No solution found! \n";
        }
        return newMatrixString;
    }
}
