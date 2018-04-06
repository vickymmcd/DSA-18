import java.util.LinkedList;
import java.util.List;

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
        // TODO: Your code here
        System.out.println("hello");
        System.out.println(mytree.inversions);
        System.out.println("lc " + mytree.root.leftChild);


        if(mytree.inversions%2 == 0) return true;
        return false;
    }

    public void printBoard(){
        for(int x=0; x<tiles.length; x++){
            for(int y=0; y<tiles.length; y++){
                System.out.print(tiles[x][y] + " ");
            }
            System.out.println();
        }
    }

    /*
     * Return all neighboring boards in the state tree
     */
    public Iterable<Board> neighbors() {
        // TODO: Your code here
        return null;
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
        int[][] initState = {{8, 1, 2}, {0, 4, 3}, {7, 6, 5}};
        Board board = new Board(initState);

        System.out.println("Size: " + board.size());
        System.out.println("Solvable: " + board.solvable());
        System.out.println("Manhattan: " + board.manhattan());
        System.out.println("Is goal: " + board.isGoal());
        System.out.println("Neighbors:");

        Iterable<Board> it = board.neighbors();
    }
}
