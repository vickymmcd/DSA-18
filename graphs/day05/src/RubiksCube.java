import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

// use this class if you are designing your own Rubik's cube implementation
public class RubiksCube {

    Face f0;
    Face f1;
    Face r0;
    Face r1;
    Face u0;
    Face u1;
    Face[] faces;
    RubiksCube prev;
    char prevrot;
    private State solutionState;
    boolean solved;

    /**
     * State class to make the cost calculations simple
     * This class holds a cube state and all of its attributes
     */
    private class State implements Comparable<State>{
        // Each state needs to keep track of its cost and the previous state
        private RubiksCube cube;
        private int moves; // equal to g-cost in A*
        public int cost; // equal to f-cost in A*
        private State prev;
        private char prevRot;

        public State(RubiksCube cube, int moves, State prev, char prevRot) {
            this.cube = cube;
            this.moves = moves;
            this.prev = prev;
            this.prevRot = prevRot;
            cost = moves + cube.getHeuristic();
        }

        @Override
        public boolean equals(Object s) {
            if (s == this) return true;
            if (s == null) return false;
            if (!(s instanceof State)) return false;
            return ((State) s).cube.equals(this.cube);
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

    /**
     * Class to represent Cubie
     */
    public class Cubie{
        String[] colors;

        public Cubie(String a, String b, String c){
            colors = new String[]{a, b, c};
        }

//        @Override
//        public boolean equals(Object obj) {
//            if (!(obj instanceof Cubie)) {
//                return false;
//            }
//
//            Cubie other = (Cubie) obj;
//
//            for (int i = 0; i < colors.length; i++) {
//                if (!other.colors[i].equals(this.colors[i])) {
//                    return false;
//                }
//            }
//
//        return true;
//        }
    }

    /**
     * Class to represent Face
     */
    public class Face{
        String faceLocation;
        Cubie[] cubies;
        String[] cubiecols;

        /**
         * Don't need to include location as parameter just input them in order of location
         */
        public Face(Cubie a, String acol, Cubie b, String bcol,
                    Cubie c, String ccol, Cubie d, String dcol, String loc){
            faceLocation = loc;
            cubies = new Cubie[]{a, b, c, d};
            cubiecols = new String[]{acol, bcol, ccol, dcol};
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Face)) {
                return false;
            }

            Face other = (Face) obj;

            for (int i = 0; i < this.cubiecols.length; i++) {
                if (!other.cubiecols[i].equals(this.cubiecols[i])) {
                    return false;
                }
            }

            return true;
        }
    }

    // initialize a solved rubiks cube
    public RubiksCube() {
        Cubie cubie1 = new Cubie("yellow", "orange", "green");
        Cubie cubie2 = new Cubie("yellow", "red", "green");
        Cubie cubie3 = new Cubie("yellow", "orange", "blue");
        Cubie cubie4 = new Cubie("yellow", "red", "blue");

        // yellow
        f0 = new Face(cubie1, cubie1.colors[0], cubie2, cubie2.colors[0], cubie3,
                cubie3.colors[0], cubie4, cubie4.colors[0], "f0");

        Cubie cubie5 = new Cubie("white", "orange", "blue");
        Cubie cubie6 = new Cubie("white", "red", "blue");
        Cubie cubie7 = new Cubie("white", "orange", "green");
        Cubie cubie8 = new Cubie("white", "red", "green");

        // white
        f1 = new Face(cubie5, cubie5.colors[0], cubie6, cubie6.colors[0], cubie7,
                cubie7.colors[0], cubie8, cubie8.colors[0], "f1");

        // red
        u0 = new Face(cubie2, cubie2.colors[1], cubie6, cubie6.colors[1], cubie4, cubie4.colors[1], cubie8, cubie8.colors[1], "u0");

        // orange
        u1 = new Face(cubie5, cubie5.colors[1], cubie1, cubie1.colors[1], cubie7, cubie7.colors[1], cubie3, cubie3.colors[1], "u1");

        // green
        r1 = new Face(cubie8, cubie8.colors[2], cubie7, cubie7.colors[2], cubie1, cubie1.colors[2], cubie2, cubie2.colors[2], "r1");

        // blue
        r0 = new Face(cubie3, cubie3.colors[2], cubie4, cubie4.colors[2], cubie6, cubie6.colors[2], cubie5, cubie5.colors[2], "r0");

        faces = new Face[]{f0, f1, r0, r1, u0, u1};


        prev = null;
        prevrot = 0;

    }

    // creates a copy of the rubix cube
    public RubiksCube(RubiksCube r) {
        Face[] newFaces = new Face[6];
        for(int i=0; i<r.faces.length; i++) {
            Face newFace = new Face(r.faces[i].cubies[0], r.faces[i].cubiecols[0],
                    r.faces[i].cubies[1], r.faces[i].cubiecols[1], r.faces[i].cubies[2],
                    r.faces[i].cubiecols[2], r.faces[i].cubies[3], r.faces[i].cubiecols[3],
                    r.faces[i].faceLocation);
            newFaces[i] = newFace;
        }

        f0 = newFaces[0];
        f1 = newFaces[1];
        r0 = newFaces[2];
        r1 = newFaces[3];
        u0 = newFaces[4];
        u1 = newFaces[5];


        faces = new Face[]{f0, f1, r0, r1, u0, u1};

        prev = null;
        prevrot = 0;
    }

    // return true if this rubik's cube is equal to the other rubik's cube
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RubiksCube))
            return false;
        RubiksCube other = (RubiksCube) obj;
        if (other.hashCode() != this.hashCode()) {
            return false;
        }

        for (int i = 0; i < this.faces.length; i++) {
           if (!other.faces[i].equals(faces[i])) {
               return false;
           }
        }
        return true;
    }

    public int getHeuristic(){
        int sum = 0;
        for(int i=0; i<faces.length; i++){
            for(int j=1; j<faces[i].cubiecols.length; j++){
                if(!faces[i].cubiecols[j-1].equals(faces[i].cubiecols[j])){
                    sum = sum+1;
                }
            }
        }
        return sum;
    }

    /**
     * return a hashCode for this rubik's cube.
     *
     * Your hashCode must follow this specification:
     *   if A.equals(B), then A.hashCode() == B.hashCode()
     *
     * Note that this does NOT mean:
     *   if A.hashCode() == B.hashCode(), then A.equals(B)
     */
    @Override
    public int hashCode() {
        int sum = 0;
        for(int j=0; j<u0.cubiecols.length; j++){
            if(u0.cubiecols[j].equals("orange"))
                sum = sum+1;
            else if(u0.cubiecols[j].equals("green"))
                sum = sum+2;
            else if(u0.cubiecols[j].equals("red"))
                sum = sum+3;
            else if(u0.cubiecols[j].equals("blue"))
                sum = sum+4;
            else if(u0.cubiecols[j].equals("white"))
                sum = sum+5;
            else if(u0.cubiecols[j].equals("yellow"))
                sum = sum+6;
        }for(int j=0; j<u1.cubiecols.length; j++){
            if(u1.cubiecols[j].equals("orange"))
                sum = sum+1;
            else if(u1.cubiecols[j].equals("green"))
                sum = sum+2;
            else if(u1.cubiecols[j].equals("red"))
                sum = sum+3;
            else if(u1.cubiecols[j].equals("blue"))
                sum = sum+4;
            else if(u1.cubiecols[j].equals("white"))
                sum = sum+5;
            else if(u1.cubiecols[j].equals("yellow"))
                sum = sum+6;
        }

        return sum;
    }

    public boolean isSolved() {
        for(int i=0; i<faces.length; i++){
            for(int j=1; j<faces[i].cubiecols.length; j++){
                if(!faces[i].cubiecols[j-1].equals(faces[i].cubiecols[j])){
                    return false;
                }
            }
        }
        return true;
    }


    // given a list of rotations, return a rubik's cube with the rotations applied
    public RubiksCube rotate(List<Character> c) {
        RubiksCube rub = this;
        for (char r : c) {
            rub = rub.rotate(r);
        }
        return rub;
    }

    public void rotateAround(Face top, Face around1, Face around2, Face around3, Face around4,
                             int index1, int index2, int index3, int index4, boolean reverse){
        // changing the Cubie orientation on the upper face
        Cubie temp;
        String tempcol;
        Cubie temp2;
        String temp2col;
        if(!reverse) {
            temp = top.cubies[0];
            tempcol = top.cubiecols[0];
            temp2 = top.cubies[1];
            temp2col = top.cubiecols[1];
            top.cubies[0] = top.cubies[2];
            top.cubiecols[0] = top.cubiecols[2];
            top.cubies[1] = temp;
            top.cubiecols[1] = tempcol;
            temp = top.cubies[3];
            tempcol = top.cubiecols[3];
            top.cubies[3] = temp2;
            top.cubiecols[3] = temp2col;
            top.cubies[2] = temp;
            top.cubiecols[2] = tempcol;
        }
        else {
            temp = top.cubies[2];
            tempcol = top.cubiecols[2];
            temp2 = top.cubies[3];
            temp2col = top.cubiecols[3];
            top.cubies[2] = top.cubies[0];
            top.cubiecols[2] = top.cubiecols[0];
            top.cubies[3] = temp;
            top.cubiecols[3] = tempcol;
            temp = top.cubies[1];
            tempcol = top.cubiecols[1];
            top.cubies[1] = temp2;
            top.cubiecols[1] = temp2col;
            top.cubies[0] = temp;
            top.cubiecols[0] = tempcol;
        }

        // changing the orientation of front and right faces accordingly
        temp = around1.cubies[index1];
        tempcol = around1.cubiecols[index1];
        temp2 = around1.cubies[index2];
        temp2col = around1.cubiecols[index2];
        around1.cubies[index1] = around2.cubies[index1];
        around1.cubiecols[index1] = around2.cubiecols[index1];
        around1.cubies[index2] = around2.cubies[index2];
        around1.cubiecols[index2] = around2.cubiecols[index2];

        Cubie temp3 = around3.cubies[index3];
        String temp3col = around3.cubiecols[index3];
        Cubie temp4 = around3.cubies[index4];
        String temp4col = around3.cubiecols[index4];
        around3.cubies[index3] = temp;
        around3.cubiecols[index3] = tempcol;
        around3.cubies[index4] = temp2;
        around3.cubiecols[index4] = temp2col;

        temp = around4.cubies[index3];
        tempcol = around4.cubiecols[index3];
        temp2 = around4.cubies[index4];
        temp2col = around4.cubiecols[index4];
        around4.cubies[index3] = temp3;
        around4.cubiecols[index3] = temp3col;
        around4.cubies[index4] = temp4;
        around4.cubiecols[index4] = temp4col;

        around2.cubies[index1] = temp;
        around2.cubiecols[index1] = tempcol;
        around2.cubies[index2] = temp2;
        around2.cubiecols[index2] = temp2col;
    }

    public RubiksCube rotateu() {
        RubiksCube copy = new RubiksCube(this);

        // changing the Cubie orientation on the upper face
        String temp = copy.u0.cubiecols[0];
        String temp2 = copy.u0.cubiecols[1];
        copy.u0.cubiecols[0] = copy.u0.cubiecols[2];
        copy.u0.cubiecols[1] = temp;
        temp = copy.u0.cubiecols[3];
        copy.u0.cubiecols[3] = temp2;
        copy.u0.cubiecols[2] = temp;

        // changing the orientation of front and right faces accordingly
        temp = copy.f0.cubiecols[0];
        temp2 = copy.f0.cubiecols[1];
        copy.f0.cubiecols[0] = copy.r0.cubiecols[0];
        copy.f0.cubiecols[1] = copy.r0.cubiecols[1];

        String temp3 = copy.r1.cubiecols[0];
        String temp4 = copy.r1.cubiecols[1];
        copy.r1.cubiecols[0] = temp;
        copy.r1.cubiecols[1] = temp2;

        temp = copy.f1.cubiecols[0];
        temp2 = copy.f1.cubiecols[1];
        copy.f1.cubiecols[0] = temp3;
        copy.f1.cubiecols[1] = temp4;

        copy.r0.cubiecols[0] = temp;
        copy.r0.cubiecols[1] = temp2;

        return copy;
    }

    public RubiksCube rotateU() {
        RubiksCube copy = new RubiksCube(this);

        // changing the Cubie orientation on the upper face
        String temp = copy.u0.cubiecols[0];
        String temp2 = copy.u0.cubiecols[2];
        copy.u0.cubiecols[0] = copy.u0.cubiecols[1];
        copy.u0.cubiecols[2] = temp;
        copy.u0.cubiecols[1] = copy.u0.cubiecols[3];
        copy.u0.cubiecols[3] = temp2;

        // changing the orientation of front and right faces accordingly
        temp = copy.r0.cubiecols[0];
        temp2 = copy.r0.cubiecols[1];
        copy.r0.cubiecols[0] = copy.f0.cubiecols[0];
        copy.r0.cubiecols[1] = copy.f0.cubiecols[1];

        String temp3 = copy.f1.cubiecols[0];
        String temp4 = copy.f1.cubiecols[1];
        copy.f1.cubiecols[0] = temp;
        copy.f1.cubiecols[1] = temp2;

        temp = copy.r1.cubiecols[0];
        temp2 = copy.r1.cubiecols[1];
        copy.r1.cubiecols[0] = temp3;
        copy.r1.cubiecols[1] = temp4;

        copy.f0.cubiecols[0] = temp;
        copy.f0.cubiecols[1] = temp2;

        return copy;
    }

    public RubiksCube rotater() {
        RubiksCube copy = new RubiksCube(this);

        // changing the Cubie orientation on the upper face
        String temp = copy.r0.cubiecols[0];
        String temp2 = copy.r0.cubiecols[1];
        copy.r0.cubiecols[0] = copy.r0.cubiecols[2];
        copy.r0.cubiecols[1] = temp;
        temp = copy.r0.cubiecols[3];
        copy.r0.cubiecols[3] = temp2;
        copy.r0.cubiecols[2] = temp;


        // changing the orientation of front and right faces accordingly
        temp = copy.u0.cubiecols[1];
        temp2 = copy.u0.cubiecols[3];
        copy.u0.cubiecols[1] = copy.f0.cubiecols[1];
        copy.u0.cubiecols[3] = copy.f0.cubiecols[3];

        // 1, 3 from u0 -> 0, 2 in f1
        String temp3 = copy.f1.cubiecols[0];
        String temp4 = copy.f1.cubiecols[2];
        copy.f1.cubiecols[2] = temp;
        copy.f1.cubiecols[0] = temp2;

        // 0, 2 from f1 -> 1, 3 in u1
        temp = copy.u1.cubiecols[1];
        temp2 = copy.u1.cubiecols[3];
        copy.u1.cubiecols[3] = temp3;
        copy.u1.cubiecols[1] = temp4;

        copy.f0.cubiecols[1] = temp;
        copy.f0.cubiecols[3] = temp2;

        return copy;
    }

    public RubiksCube rotateR() {
        RubiksCube copy = new RubiksCube(this);

        // changing the Cubie orientation on the upper face
        String temp = copy.r0.cubiecols[0];
        String temp2 = copy.r0.cubiecols[2];
        copy.r0.cubiecols[0] = copy.r0.cubiecols[1];
        copy.r0.cubiecols[2] = temp;
        copy.r0.cubiecols[1] = copy.r0.cubiecols[3];
        copy.r0.cubiecols[3] = temp2;


        // changing the orientation of front and right faces accordingly
        temp = copy.f0.cubiecols[1];
        temp2 = copy.f0.cubiecols[3];
        copy.f0.cubiecols[1] = copy.u0.cubiecols[1];
        copy.f0.cubiecols[3] = copy.u0.cubiecols[3];

        String temp3 = copy.u1.cubiecols[1];
        String temp4 = copy.u1.cubiecols[3];
        copy.u1.cubiecols[1] = temp;
        copy.u1.cubiecols[3] = temp2;

        temp = copy.f1.cubiecols[0];
        temp2 = copy.f1.cubiecols[2];
        copy.f1.cubiecols[2] = temp3;
        copy.f1.cubiecols[0] = temp4;

        copy.u0.cubiecols[3] = temp;
        copy.u0.cubiecols[1] = temp2;

        return copy;
    }

    public RubiksCube rotatef() {
        RubiksCube copy = new RubiksCube(this);

        // changing the Cubie orientation on the upper face
        String temp = copy.f0.cubiecols[0];
        String temp2 = copy.f0.cubiecols[1];
        copy.f0.cubiecols[0] = copy.f0.cubiecols[2];
        copy.f0.cubiecols[1] = temp;
        temp = copy.f0.cubiecols[3];
        copy.f0.cubiecols[3] = temp2;
        copy.f0.cubiecols[2] = temp;

        // changing the orientation of front and right faces accordingly
        temp = copy.r0.cubiecols[0];
        temp2 = copy.r0.cubiecols[2];
        copy.r0.cubiecols[0] = copy.u0.cubiecols[2];
        copy.r0.cubiecols[2] = copy.u0.cubiecols[3];

        copy.u0.cubiecols[3] = copy.r1.cubiecols[1];
        copy.u0.cubiecols[2] = copy.r1.cubiecols[3];

        copy.r1.cubiecols[1] = copy.u1.cubiecols[1];
        copy.r1.cubiecols[3] = copy.u1.cubiecols[0];

        copy.u1.cubiecols[1] = temp;
        copy.u1.cubiecols[0] = temp2;

        return copy;
    }

    public RubiksCube rotateF() {
        RubiksCube copy = new RubiksCube(this);

        // changing the Cubie orientation on the upper face
        String temp = copy.f0.cubiecols[0];
        String temp2 = copy.f0.cubiecols[2];
        copy.f0.cubiecols[0] = copy.f0.cubiecols[1];
        copy.f0.cubiecols[2] = temp;
        copy.f0.cubiecols[1] = copy.f0.cubiecols[3];
        copy.f0.cubiecols[3] = temp2;

        // changing the orientation of front and right faces accordingly
        temp = copy.r1.cubiecols[1];
        temp2 = copy.r1.cubiecols[3];
        copy.r1.cubiecols[3] = copy.u0.cubiecols[2];
        copy.r1.cubiecols[1] = copy.u0.cubiecols[3];

        copy.u0.cubiecols[2] = copy.r0.cubiecols[0];
        copy.u0.cubiecols[3] = copy.r0.cubiecols[2];

        copy.r0.cubiecols[2] = copy.u1.cubiecols[0];
        copy.r0.cubiecols[0] = copy.u1.cubiecols[1];

        copy.u1.cubiecols[1] = temp;
        copy.u1.cubiecols[0] = temp2;

        return copy;
    }


    // Given a character in ['u', 'U', 'r', 'R', 'f', 'F'], return a new rubik's cube with the rotation applied
    // Do not modify this rubik's cube.
    public RubiksCube rotate1(char c) {
        RubiksCube copy = new RubiksCube(this);

        if (c == 'u'){
            rotateAround(copy.u0, copy.r1, copy.f0, copy.f1, copy.r0,
                    0, 1, 0, 1, false);
        }
        else if (c == 'U'){
            rotateAround(copy.u0, copy.r0, copy.f0, copy.f1, copy.r1,
                    0, 1, 0, 1,true);
        }
        else if(c == 'r'){
            rotateAround(copy.r0, copy.f0, copy.u1, copy.u0, copy.f1,
                    1, 3, 1,3, false);
        }
        else if(c == 'R'){
            rotateAround(copy.r0, copy.f0, copy.u0, copy.u1, copy.f1,
                    1, 3, 1, 3, true);
        }
        else if(c == 'f'){
            rotateAround(copy.f0, copy.u1, copy.r0, copy.r1, copy.u0,
                    0, 2,0,2, false);
        }
        else if(c == 'F'){
            rotateAround(copy.f0, copy.u1, copy.r1, copy.r0, copy.u0,
                    0, 2,0,2, true);
        }

        return copy;
    }

    public RubiksCube rotate(char c) {
        RubiksCube copy;

        if (c == 'u'){
            copy = rotateu();
        }
        else if (c == 'U'){
            copy = rotateU();
        }
        else if(c == 'r'){
            copy = rotater();
        }
        else if(c == 'R'){
            copy = rotateR();
        }
        else if(c == 'f'){
            copy = rotatef();
        }
        else if(c == 'F'){
            copy = rotateF();
        }
        else{
            throw new IllegalArgumentException();
        }

        return copy;
    }

    public void printCube(){
        for(int i=0; i<faces.length; i++){
            System.out.println(faces[i].faceLocation);
            System.out.print(faces[i].cubiecols[0] + " ");
            System.out.println(faces[i].cubiecols[1]);
            System.out.print(faces[i].cubiecols[2] + " ");
            System.out.println(faces[i].cubiecols[3]);
            System.out.println();
            System.out.println();
        }
    }

    public void printFace(Face f){
        System.out.println(f.faceLocation);
        System.out.print(f.cubiecols[0] + " ");
        System.out.println(f.cubiecols[1]);
        System.out.print(f.cubiecols[2] + " ");
        System.out.println(f.cubiecols[3]);
        System.out.println();
        System.out.println();
    }

    // returns a random scrambled rubik's cube by applying random rotations
    public static RubiksCube scrambledCube(int numTurns) {
        RubiksCube r = new RubiksCube();
        char[] listTurns = getScramble(numTurns);
        for (int i = 0; i < numTurns; i++) {
            r = r.rotate(listTurns[i]);
        }
        return r;
    }

    public static char[] getScramble(int size){
        char[] listTurns = new char[size];
        for (int i = 0; i < size; i++) {
            switch (ThreadLocalRandom.current().nextInt(0, 6)) {
                case 0:
                    listTurns[i] = 'u';
                    break;
                case 1:
                    listTurns[i] = 'U';
                    break;
                case 2:
                    listTurns[i] = 'r';
                    break;
                case 3:
                    listTurns[i] = 'R';
                    break;
                case 4:
                    listTurns[i] = 'f';
                    break;
                case 5:
                    listTurns[i] = 'F';
                    break;
            }
        }
        return listTurns;
    }


    // return the list of rotations needed to solve a rubik's cube
    public List<Character> solveBFS() {
        char[] turns = new char[]{'u', 'U', 'r', 'R', 'f', 'F'};
        HashSet<RubiksCube> visited = new HashSet<>();
        LinkedList<RubiksCube> queue = new LinkedList<>();
        ArrayList<Character> rotations = new ArrayList<>();

        visited.add(this);
        queue.add(this);

        while(queue.size() != 0){
            RubiksCube r = queue.poll();
            for(int i=0; i<turns.length; i++){
                RubiksCube x = r.rotate(turns[i]);
                if(x.isSolved()){
                    x.prev = r;
                    x.prevrot = turns[i];
                    while(x.prev != null){
                        rotations.add(x.prevrot);
                        x = x.prev;
                    }
                    Collections.reverse(rotations);
                    System.out.println("-----------------------------------------------------------------------------------");
                    System.out.println(rotations.size());
                    System.out.println(rotations);
                    return rotations;
                }
                if(!visited.contains(x)){
                    x.prev = r;
                    x.prevrot = turns[i];
                    visited.add(x);
                    queue.add(x);
                }
            }
        }
        return new ArrayList<>();
    }

    public boolean solveIt(){
        PriorityQueue<State> open = new PriorityQueue<>();
        ArrayList<State> closed = new ArrayList<>();
        State u;
        boolean ignore;
        char[] turns = new char[]{'u', 'U', 'r', 'R', 'f', 'F'};

        solutionState = new State(this, 0, null, '0');

        open.add(this.solutionState);

        while(!open.isEmpty()){
            State q = open.poll();
            for(int i=0; i<turns.length; i++){
                RubiksCube b = q.cube.rotate(turns[i]);
                u = new State(b,q.moves+1, q, turns[i]);
                System.out.println(q.moves);
                if(b.isSolved()){
                    this.solutionState = u;
                    this.solved = true;
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
            if(q.moves > 10){
                break;
            }

        }
        return false;
    }

    public List<Character> solve() {
        //return solveBFS();
        solveIt();
        State state = this.solutionState;
        ArrayList<Character> rotations = new ArrayList<>();
        while(state != null){
            if (state.prevRot != '0')
                rotations.add(state.prevRot);
            state = state.prev;
        }
        Collections.reverse(rotations);
        System.out.println(rotations);
        return rotations;
    }

    public static void main(String[] args){
        RubiksCube mycube = new RubiksCube();
        //mycube.printCube();
        RubiksCube r = mycube;
        RubiksCube u = mycube;
        r = r.rotate('f');
        //r = r.rotate('U');

        u = u.rotate1('f');
        //u = u.rotate1('U');
        System.out.println(u.equals(r));

        //r = r.rotater();
        //r = r.rotateu();

        //r.printCube();

/*

        System.out.println(r.equals(mycube));
        for (int i = 0; i < 6; i++) {
            r = mycube.rotate('r');
            r = r.rotate('u');
            r = r.rotate('R');
            r = r.rotate('U');
            System.out.println("-----------------------------------------------------");
            r.printCube();
        }

        r.printCube();*/
    }

}



