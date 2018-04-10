/**
 * Solver definition for the 8 Puzzle challenge
 * Construct a tree of board states using A* to find a path to the goal
 */

import java.util.*;

public class Solver {

    public int minMoves = -1;
    private State solutionState;
    private boolean solved = false;

    /**
     * State class to make the cost calculations simple
     * This class holds a board state and all of its attributes
     */
    private class State implements Comparable<State>{
        // Each state needs to keep track of its cost and the previous state
        private Board board;
        private int moves; // equal to g-cost in A*
        public int cost; // equal to f-cost in A*
        private State prev;

        public State(Board board, int moves, State prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
            // TODO
            cost = moves + board.manhattan();
        }

        @Override
        public boolean equals(Object s) {
            if (s == this) return true;
            if (s == null) return false;
            if (!(s instanceof State)) return false;
            return ((State) s).board.equals(this.board);
        }

        @Override
        public int compareTo(State s){
            return cost - s.cost;
        }
    }

    /*
     * Return the root state of a given state
     */
    private State root(State state) {
        while(state.prev != null){
            state = state.prev;
        }
        return state;
    }

    /*
     * A* Solver
     * Find a solution to the initial board using A* to generate the state tree
     * and a identify the shortest path to the the goal state
     * This is the constructor for the solver class
     */
    public Solver(Board initial) {
        solutionState = new State(initial, 0, null);
        solveIt();

    }

    public boolean solveIt(){
        PriorityQueue<State> open = new PriorityQueue<>();
        ArrayList<State> closed = new ArrayList<>();
        State u;
        boolean ignore;

        open.add(this.solutionState);

        if(!isSolvable()){
            solved = false;
            return false;
        }

        while(!open.isEmpty()){
            State q = open.poll();
            for (Board b: q.board.neighbors()) {
                u = new State(b,q.moves+1, q);
                if(b.isGoal()){
                    this.solutionState = u;
                    this.solved = true;
                    this.minMoves = u.moves;
                    return true;
                }
                ignore = false;

                for (State s: open) {
                    if(s.equals(u) && s.cost <= u.cost){
                        ignore = true;
                        break;
                    }
                }

                for (State s: closed){
                    if(s.equals(u) && s.cost <= u.cost){
                        ignore = true;
                        break;
                    }
                }

                if(!ignore){
                    open.add(u);

                }
            }
            closed.add(q);

        }
        return false;
    }

    /*
     * Is the input board a solvable state
     * Research how to check this without exploring all states
     */
    public boolean isSolvable() {
        return solutionState.board.solvable();
    }

    /*
     * Return the sequence of boards in a shortest solution, null if unsolvable
     */
    public Iterable<Board> solution() {
        State state = this.solutionState;
        ArrayList<Board> boards = new ArrayList<>();
        if(!isSolvable()){
            return null;
        }
        else{
            while(state != null){
                boards.add(state.board);
                state = state.prev;
            }
        }
        return boards;
    }

    public State find(Iterable<State> iter, Board b) {
        for (State s : iter) {
            if (s.board.equals(b)) {
                return s;
            }
        }
        return null;
    }

    /*
     * Debugging space
     */
    public static void main(String[] args) {
        int[][] initState = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        Board initial = new Board(initState);

        Solver solver = new Solver(initial);
    }


}
