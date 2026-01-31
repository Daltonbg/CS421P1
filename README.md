****************
* Programing Project 1
* CS421
* 1/31/2026
* Dalton Bilau Goncalves
****************

OVERVIEW:

The program has 3 class, KnightTour being the driver class for the program.
Given a board size the program can find the best way to fill out a board
in a way that all positions are filled. The eligible positions are 2 rows and 1 column
from the current position OR 1 row and 2 columns.


INCLUDED FILES:

 e.g.
 * KnightBoard.java - main algorithm file
 * KnightTour.java - driver file
 * Position.java - helper class file
 * README - this file


COMPILING AND RUNNING:

 From the directory containing all source files, compile the
 driver class (and all dependencies) with the command:
 $ javac KnightTour.java

 Run the compiled class file with the command:
 $ java KnightTour <0/1/2 (no/heuristicI/heuristicII search)> <n> <x> <y>

Where:
 * The first argument (0,1,2) decides what type of search to use
 * The second argument the size of the board
 * The third and fourth argument are the axis of the position where to initialize the board

 Console output will give the results after the program finishes.


PROGRAM DESIGN AND IMPORTANT CONCEPTS:

The program will make use of three different searches to find the correct order
to fill the positions on the board. The first search, basic search, will simply
follow an order for look for positions in a clockwise manner. The second method
HeuristicI will look for the positions closer to the border first and then follow
the same clockwise order. Finally, the final and most efficient search method
will simply look for each possible position and all the possible moves the board
will have if selected that one.

If no more eligible moves the board will back track and try a different move.
All the eligible moves are saved, meaning that as the board goes through it they
can move back if needed.

Here is the requested output as requested by the professor:

$ java KnightTour 0 7 1 1
The total number of moves is 254727174
        21 46 41 2 23 26 9
        40 1 22 27 10 3 24
        47 20 45 42 25 8 11
        44 39 34 19 28 15 4
        33 48 43 36 7 12 29
        38 35 18 31 14 5 16
        49 32 37 6 17 30 13

$ java KnightTour 1 7 1 1
The total number of moves is 810
        21 44 11 2 23 36 13
        10 1 22 43 12 3 24
        45 20 9 40 35 14 37
        8 33 42 49 38 25 4
        19 46 39 34 41 28 15
        32 7 48 17 30 5 26
        47 18 31 6 27 16 29

$ java KnightTour 2 7 1 1
The total number of moves is 172
        49 44 11 2 29 42 13
        10 1 46 43 12 3 28
        45 48 9 30 41 14 35
        8 25 40 47 36 27 4
        39 22 31 26 17 34 15
        24 7 20 37 32 5 18
        21 38 23 6 19 16 33

DISCUSSION:
 
This was a very nice to work on. It was fun to figure out a way and realize
different ways I could code for that specific type of search/iteration.
I feel like this search-related algorithm is perfect for this class
and certainly will prove itself useful once I am working in the industry.
I had a lot of fun working on it.

