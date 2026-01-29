
import java.util.ArrayList;
import java.util.Comparator;

public class Position {

    int x;
    int y;
    int filling;
    int distance;
    ArrayList<Position> eligibleMoves;

    int[] yMove = {-2, -1, 1, 2, 2, 1, -1, -2};
    int[] xMove = {1, 2, 2, 1, -1, -2, -2, -1};

    public Position(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.filling = 0;
        this.distance = distance;
        this.eligibleMoves = new ArrayList<Position>();
    }

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

    protected void sortEligible() {
        eligibleMoves.sort(Comparator.comparingInt(pos -> pos.distance));
    }

    protected void sortEligibleII(KnightBoard board) {
        eligibleMoves.sort((p1, p2) -> {
            int onward1 = board.countMoves(p1);
            int onward2 = board.countMoves(p2);
            return Integer.compare(onward1, onward2);
        });
    }

    protected int getFilling() {
        return filling;
    }

    protected void setFilling(int newFilling) {
        filling = newFilling;
    }

    protected int getDistance() {
        return distance;
    }

    protected int numberMoves() {
        return eligibleMoves.size();
    }

    protected ArrayList<Position> getEligibleMoves() {
        return eligibleMoves;
    }

    protected int getX() {
        return x;
    }

    protected int getY() {
        return y;
    }
}
