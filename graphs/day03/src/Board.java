import java.util.*;

/**
 * Board definition for the 8 Puzzle challenge
 */
public class Board {

    private int n;
    public int[][] tiles;

    //TODO
    // Create a 2D array representing the solved board state
    private int[][] goal = {{1,2,3},{4,5,6},{7,8,0}};

    /*
     * Set the global board size and tile state
     */
    public Board(int[][] b) {
        this.n = b.length;
        this.tiles = b;
    }

    /*
     * Size of the board 
     (equal to 3 for 8 puzzle, 4 for 15 puzzle, 5 for 24 puzzle, etc)
     */
    private int size() {
        return n;
    }

    /*
     * Sum of the manhattan distances between the tiles and the goal
     */
    public int manhattan() {
        int dist = 0;
        for(int ti = 0; ti < tiles.length;ti++)

        {
            for(int tj = 0; tj < tiles.length; tj++) {

             int tileToCheck = tiles[ti][tj];

                for (int gi = 0; gi < goal.length; gi++) {
                    for (int gj = 0; gj < goal[0].length; gj++)

                    {
                        if(goal[gi][gj] == tileToCheck && tileToCheck != 0)
                        {
                            dist = Math.abs(gi-ti) + Math.abs(gj-tj) + dist;
                        }
                    }
                }
            }
        }

        return dist;
    }

    /*
     * Compare the current state to the goal state
     */
    public boolean isGoal() {
        return(manhattan() == 0);
    }

    /*
     * Returns true if the board is solvable
     * Research how to check this without exploring all states
     */
    public boolean solvable() {
        //determine if number of inversions is even or odd
        //can check for inversions by checking if one is bigger than one that comes after it

        AVLTree<Integer> mytree = new AVLTree<Integer>();

        int inversions = 0;

        for(int row = 0; row<tiles.length; row++)
        {
            for(int col = 0; col<tiles.length; col++)
            {
                int toCheck = tiles[row][col];
                if(toCheck != 0) {
                    mytree.add(toCheck);
                }
            }
        }


        return(mytree.inversions%2 == 0);
    }

    public static void printBoard(Board b){
        for(int x=0; x<b.tiles.length; x++){
            for(int y=0; y<b.tiles.length; y++){
                System.out.print(b.tiles[x][y] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Creates a deep copy of the input array and returns it
     */
    private static int[][] copyOf(int[][] A) {
        int[][] B = new int[A.length][A[0].length];
        for (int i = 0; i < A.length; i++)
            System.arraycopy(A[i], 0, B[i], 0, A[0].length);
        return B;
    }

    /*
     * Return all neighboring boards in the state tree
     */
    public Iterable<Board> neighbors() {
        // TODO: Your code here
        ArrayList<Board> boards = new ArrayList<Board>();
        int[][] temp;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(tiles[i][j] == 0){
                    if(i+1 < n){
                        temp = copyOf(tiles);
                        temp[i][j] = temp[i+1][j];
                        temp[i+1][j] = 0;
                        boards.add(new Board(temp));
                    }
                    if(i-1 >= 0){
                        temp = copyOf(tiles);
                        temp[i][j] = temp[i-1][j];
                        temp[i-1][j] = 0;
                        boards.add(new Board(temp));
                    }
                    if(j+1 < n){
                        temp = copyOf(tiles);
                        temp[i][j] = temp[i][j+1];
                        temp[i][j+1] = 0;
                        boards.add(new Board(temp));
                    }
                    if(j-1 >= 0){
                        temp = copyOf(tiles);
                        temp[i][j] = temp[i][j-1];
                        temp[i][j-1] = 0;
                        boards.add(new Board(temp));
                    }
                }

            }
        }
        return boards;
    }

    /*
     * Check if this board equals a given board state
     */
    @Override
    public boolean equals(Object x) {
        // Check if the board equals an input Board object
        if (x == this) return true;
        if (x == null) return false;
        if (!(x instanceof Board)) return false;
        // Check if the same size
        Board y = (Board) x;
        if (y.tiles.length != n || y.tiles[0].length != n) {
            return false;
        }
        // Check if the same tile configuration
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.tiles[i][j] != y.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // DEBUG - Your solution can include whatever output you find useful
        int[][] initState = {{2, 1, 3}, {4, 0, 5}, {7, 8, 6}};
        Board board = new Board(initState);

        System.out.println("Size: " + board.size());
        System.out.println("Solvable: " + board.solvable());
        System.out.println("Manhattan: " + board.manhattan());
        System.out.println("Is goal: " + board.isGoal());
        System.out.println("Neighbors:");

        Iterable<Board> it = board.neighbors();
        for (Board b: it) {
            printBoard(b);
        }
    }
}
