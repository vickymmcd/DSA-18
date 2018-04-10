import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

// use this class if you are designing your own Rubik's cube implementation
public class RubiksCube {

    /**
     * Class to represent Cubie
     */
    public class Cubie{
        String color1;
        String color2;
        String color3;

        public Cubie(String a, String b, String c){
            color1 = a;
            color2 = b;
            color3 = c;
        }
    }

    /**
     * Class to represent Face
     */
    public class Face{
        Cubie cubie1;
        Cubie cubie2;
        Cubie cubie3;
        Cubie cubie4;
        String faceLocation;

        public Face(Cubie a, int acol, int aloc, Cubie b, int bcol, int bloc,
                    Cubie c, int ccol, int cloc, Cubie d, int dcol, int dloc, String loc){
            cubie1 = a;
            cubie2 = b;
            cubie3 = c;
            cubie4 = d;
            faceLocation = loc;
        }
    }

    // initialize a solved rubiks cube
    public RubiksCube() {
        Cubie cubie1 = new Cubie("yellow", "orange", "green");
        Cubie cubie2 = new Cubie("yellow", "red", "green");
        Cubie cubie3 = new Cubie("yellow", "orange", "blue");
        Cubie cubie4 = new Cubie("yellow", "red", "blue");
        Face f0 = new Face(cubie1, 0, 0, cubie2, 0, 1, cubie3,
                0, 2, cubie4, 0, 3, "f0");

        Cubie cubie5 = new Cubie("white", "red", "blue");
        Cubie cubie6 = new Cubie("white", "orange", "blue");
        Cubie cubie7 = new Cubie("white", "red", "green");
        Cubie cubie8 = new Cubie("white", "orange", "green");
        Face f1 = new Face(cubie5, 0, 0, cubie6, 0, 1, cubie7,
                0, 2, cubie8, 0, 3, "f1");


    }


    // creates a copy of the rubics cube
    public RubiksCube(RubiksCube r) {
        // TODO
    }

    // return true if this rubik's cube is equal to the other rubik's cube
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RubiksCube))
            return false;
        RubiksCube other = (RubiksCube) obj;
        // TODO
        return false;
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
        // TODO
        return 0;
    }

    public boolean isSolved() {
        // TODO
        return false;
    }


    // given a list of rotations, return a rubik's cube with the rotations applied
    public RubiksCube rotate(List<Character> c) {
        RubiksCube rub = this;
        for (char r : c) {
            rub = rub.rotate(r);
        }
        return rub;
    }


    // Given a character in ['u', 'U', 'r', 'R', 'f', 'F'], return a new rubik's cube with the rotation applied
    // Do not modify this rubik's cube.
    public RubiksCube rotate(char c) {
        // TODO
        return this;
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
    public List<Character> solve() {
        // TODO
        return new ArrayList<>();
    }

}



