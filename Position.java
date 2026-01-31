
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Helper class that defines "Position" which will go inside the board. Each
 * position defined will go inside a specific place in the board.
 *
 * @author Dalton Bilau Goncalves
 */
public class Position {

    int x;
    int y;
    int filling;
    int distance;
    ArrayList<Position> eligibleMoves;

    int[] yMove = {-2, -1, 1, 2, 2, 1, -1, -2};
    int[] xMove = {1, 2, 2, 1, -1, -2, -2, -1};

    /**
     * Constructor for the Position class, simply initializes a position
     *
     * @param x the x axis for the position
     * @param y the y axis for the position
     * @param distance the distance from the border
     */
    public Position(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.filling = 0;
        this.distance = distance;
        this.eligibleMoves = new ArrayList<Position>();
    }

    /**
     * Method that calculates all the moves for the board, used inside the
     * search methods to find the eligible moves
     *
     * @param board The KnightBoard board that we will be checking for eligible
     * positions
     */
    protected void calculateMoves(KnightBoard board) {
        eligibleMoves.clear();
        int xNext;
        int yNext;
        for (int i = 0; i < 8; i++) {
            xNext = x + xMove[i];
            yNext = y + yMove[i];
            if (board.isValid(xNext, yNext)) {
                eligibleMoves.add(board.getPosition(xNext, yNext));
            }
        }
    }

    /**
     * Simple helper method that simply sorts all the eligible moves based on
     * distance and(if equal) separate by clockwise direction
     */
    protected void sortEligible() {
        eligibleMoves.sort(Comparator.comparingInt(pos -> pos.distance));
    }

    /**
     * Helper sort used for HeuristicII that helps sorting the eligible moves
     * based on number of moves they might have next
     *
     * @param board The KnightBoard board that we will be checking for next
     * moves
     */
    protected void sortEligibleII(KnightBoard board) {
        eligibleMoves.sort((p1, p2) -> {
            int next1 = board.countMoves(p1);
            int next2 = board.countMoves(p2);
            return Integer.compare(next1, next2);
        });
    }

    /**
     * Getter method for the filling inside the board
     *
     * @return the filling, aka, position of the found position
     */
    protected int getFilling() {
        return filling;
    }

    /**
     * Setter method for the filling
     *
     * @param newFilling new filling, aka, position of the found position
     */
    protected void setFilling(int newFilling) {
        filling = newFilling;
    }

    /**
     * Getter method for the distance from the board
     *
     * @return the distance from the board
     */
    protected int getDistance() {
        return distance;
    }

    /**
     * Getter for the number of eligible moves a position has
     *
     * @return the number of eligible moves the position has
     */
    protected int numberMoves() {
        return eligibleMoves.size();
    }

    /**
     * Getter for an ArrayList of all the eligible moves
     *
     * @return A copy of the eligible moves list
     */
    protected ArrayList<Position> getEligibleMoves() {
        return eligibleMoves;
    }

    /**
     * Getter for the x axis of the position
     *
     * @return the x axis of the position
     */
    protected int getX() {
        return x;
    }

    /**
     * Getter for the y axis of the position
     *
     * @return the y axis of the position
     */
    protected int getY() {
        return y;
    }
}
