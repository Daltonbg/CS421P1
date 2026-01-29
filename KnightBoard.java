
import java.util.ArrayList;

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
        this.heuristic = heuristic;
        this.size = size;
        this.xStart = xStart;
        this.yStart = yStart;

        this.movesCount = 1; // @FIXIT initial counts as movecount?
        this.nextPlacement = 1;
        board = new Position[size][size];
        writeBoard();

        Position initial = board[xStart][yStart];
        initial.setFilling(nextPlacement);
        nextPlacement++;

        if (heuristic == 0) {
            if (basicSearch(initial)){
                solutionFound = true;
            }
        } else if (heuristic == 1) {
            if(heuristicI(initial)){
                solutionFound = true;
            }
        } else {
            if(heuristicII(initial)){
                solutionFound = true;
            }
        }
    }

    /**
     * Simple helper function that fills the board with Positions These
     * positions will be 0 if empty
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

    public Position getPosition(int x, int y) {
        return board[y][x];
    }

    //@FIXIT
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

    // @FIXIT
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

    // @FIXIT
    protected boolean isValid(int x, int y) {
        if (x < size && y < size && x >= 0 && y >= 0) {
            return true;
        }
        return false;
    }

    // @FIXIT
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

    // @FIXIT
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

    // @FIXIT
    // private boolean
    // @FIXIT, COMPARATOR WHERE DISTANCE FIRST, IF THE SAME THEN WHOEVER COMES IN
    // THE CLOCK FIRST
    // @FIXIT
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
            newMatrixString += "No solution found!";
        }
        return newMatrixString;

    }

}
