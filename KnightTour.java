
/**
 * Driver class that has the "main" method and initializes the board
 *
 * @author Dalton Bilau Goncalves
 */
public class KnightTour {

    /**
     * Main method that will initialize the board
     *
     * @param args Usage: java KnightTour <0/1/2 (no/heuristicI/heuristicII search)> <n> <x> <y>
     */
    public static void main(String[] args) {

        //Parses through args to check any errors
        if (args.length != 4) {
            System.out.println("Argument numbers needs to be 4, and it is: " + args.length);
            commands();
            throw new IllegalArgumentException("Incorrect argument numbers");
        }

        int heuristic = Integer.parseInt(args[0]);
        int size = Integer.parseInt(args[1]);
        int xStart = Integer.parseInt(args[2]);
        int yStart = Integer.parseInt(args[3]);

        if (heuristic > 2) {
            commands();
            throw new IllegalArgumentException("Heuristics can only be 0, 1 or 2");
        } else if (size < 3) {
            commands();
            throw new IllegalArgumentException("Size needs to be bigger than 2");
        } else if (xStart > size || yStart > size) {
            commands();
            throw new IllegalArgumentException("Starting position cannot be bigger than size");
        } else if (xStart < 0 || yStart < 0) {
            commands();
            throw new IllegalArgumentException("Starting position needs to be a positive number");
        }

        //Creates the board and prints it
        KnightBoard kb = new KnightBoard(heuristic, size, xStart, yStart);
        System.out.println(kb);
    }

    /**
     * Simple helper method that prints the usage and a simple explanation.
     */
    private static void commands() {
        System.out.println("Usage: java KnightTour <0/1/2 (no/heuristicI/heuristicII search)> <n> <x> <y>");
        System.out.println("Where you can select 0-2 for a type of search option");
        System.out.println("Followed by a number n, bigger than 2, for the size of the board");
        System.out.println("And finally the starting position x and y");
    }
}
