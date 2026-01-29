
import java.util.ArrayList;

public class KnightBoard {

    private Position[][] board;
    private int heuristic;
    private int size;
    private int xStart;
    private int yStart;
    private int movesCount;
    private int nextPlacement;
    private boolean successfulPlacement;
    // Basic Search
    int[] xMove = { -2, -1, 1, 2, 2, 1, -1, -2 };
    int[] yMove = { 1, 2, 2, 1, -1, -2, -2, -1 };

    /**
     * Constructor for KnightBoard class, approprietly initalizes variables and
     * initialize methods to create the matrix and initialize the filling of the
     * board
     *
     * @param heuristic method of filling
     * @param size      size of the board
     * @param xStart    starting x position for filling
     * @param yStart    starting y position for filling
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
        this.successfulPlacement = false;

        Position initial = board[xStart][yStart];
        initial.setFilling(nextPlacement);
        nextPlacement++;

        if (heuristic == 0) {
            basicSearch(initial);
        } else if (heuristic == 1) {

        } else {

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
                if (j <= size / 2 && i <= size / 2) {
                    distance = j + i;
                } else if (j <= size / 2 && i >= size / 2) {
                    distance = j + size - i;
                } else if (j >= size / 2 && i <= size / 2) {
                    distance = size - j + i;
                } else {
                    distance = size * 2 - i - j;
                }
                board[i][j] = new Position(i, j, distance);
            }
        }
    }

    // @FIXIT
    private boolean basicSearch(Position current) {
        if (nextPlacement > (size * size)) {
            return true;
        } else {
            for (int i = 0; i < 8; i++) {
                Position next = isValid(current, i);
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
    private Position isValid(Position current, int i) {
        int xNext = current.x + xMove[i];
        int yNext = current.y + yMove[i];
        if (xNext < size && yNext < size && xNext >= 0 && yNext >= 0) {
            return board[xNext][yNext];
        }
        return null;
    }

    // @FIXIT
    private boolean heuristicI(Position current) {
        if (nextPlacement > (size * size)) {
            return true;
        } else {
            ArrayList<Position> eligibleMoves = null;
            for (int i = 0; i < size; i++) {
                Position nextMove = isValid(current, i);
                if (nextMove != null) {
                    eligibleMoves.add(nextMove);
                }
            }
            int distance = 0;

            //Object distances;
            eligibleMoves.sort((m1, m2) ->{

            int diff = m1.getDistance() - (m2.getDistance());
            
            return (diff != 0) ? diff : })//MAYBE WHAT I COULD DO IS ADD WHICH PLACES EACH POSITION
            //CAN GO TO WHEN I CREATE THEM, EXAMPLE WHEN I CREATE THE POSITION AT 0,0
            //I ALSO ATTACH IT TO ALL THE POSITIONS THAT ARE ELIGIBLE BY DOING THE CALCULATION THERE
            //THIS WAY I CAN DO A GETPOSITIONCLOCK, something like that, WHERE I CAN GET THIS ARRAY
            //THAT STORES THEM ALL
            }
        }

    // @FIXIT
    // private boolean

    // @FIXIT, COMPARATOR WHERE DISTANCE FIRST, IF THE SAME THEN WHOEVER COMES IN
    // THE CLOCK FIRST

    // @FIXIT
    public String toString() {
        String newMatrixString = "The total number of moves is " + movesCount + "\n";
        for (int i = 0; i < size; i++) {
            newMatrixString += "\t";
            for (int j = 0; j < size; j++) {
                newMatrixString += board[i][j].getFilling() + " ";
            }
            newMatrixString += "\n";
        }
        return newMatrixString;

    }

}
