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

    /**
     * Class to represent Cubie
     */
    public class Cubie{
        String[] colors;

        public Cubie(String a, String b, String c){
            colors = new String[]{a, b, c};
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Cubie)) {
                return false;
            }

            Cubie other = (Cubie) obj;

            for (int i = 0; i < colors.length; i++) {
                if (!other.colors[i].equals(this.colors[i])) {
                    return false;
                }
            }

        return true;
        }
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

            for (int i = 0; i < this.cubies.length; i++) {
                if (!other.cubies[i].equals(this.cubies[i])) {
                    return false;
                }
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
        f0 = new Face(cubie1, cubie1.colors[0], cubie2, cubie2.colors[0], cubie3,
                cubie3.colors[0], cubie4, cubie4.colors[0], "f0");

        Cubie cubie5 = new Cubie("white", "orange", "blue");
        Cubie cubie6 = new Cubie("white", "red", "blue");
        Cubie cubie7 = new Cubie("white", "orange", "green");
        Cubie cubie8 = new Cubie("white", "red", "green");

        f1 = new Face(cubie5, cubie5.colors[0], cubie6, cubie6.colors[0], cubie7,
                cubie7.colors[0], cubie8, cubie8.colors[0], "f1");

        r0 = new Face(cubie2, cubie2.colors[1], cubie6, cubie6.colors[1], cubie4, cubie4.colors[1], cubie8, cubie8.colors[1], "r0");

        r1 = new Face(cubie5, cubie5.colors[1], cubie1, cubie1.colors[1], cubie7, cubie7.colors[1], cubie3, cubie3.colors[1], "r1");

        u0 = new Face(cubie8, cubie8.colors[2], cubie7, cubie7.colors[2], cubie1, cubie1.colors[2], cubie2, cubie2.colors[2], "u0");

        u1 = new Face(cubie3, cubie3.colors[2], cubie4, cubie4.colors[2], cubie6, cubie6.colors[2], cubie5, cubie5.colors[2], "u1");

        faces = new Face[]{f0, f1, r0, r1, u0, u1};

        Face[] faces = {f0, f1, r0, r1, u0, u1};



    }


    // creates a copy of the rubics cube
    public RubiksCube(RubiksCube r) {
        Face newF;
        Face[] newFaces = new Face[6];


        for (int i = 0; i < r.faces.length; i++) {
            newF = new Face(r.faces[i].cubies[0], r.faces[i].cubiecols[0], r.faces[i].cubies[1], r.faces[i].cubiecols[1], r.faces[i].cubies[2], r.faces[i].cubiecols[2], r.faces[i].cubies[3], r.faces[i].cubiecols[3], r.faces[i].faceLocation);
            newFaces[i] = newF;
        }

        f0 = newFaces[0];
        f1 = newFaces[1];
        r0 = newFaces[2];
        r1 = newFaces[3];
        u0 = newFaces[4];
        u1 = newFaces[5];

        Face[] faces = {f0, f1, r0, r1, u0, u1};
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

    public void rotateAround(Face top, Face around1, Face around2, Face around3, Face around4, int index1, int index2){
        // changing the Cubie orientation on the upper face
        Cubie temp = top.cubies[0];
        String tempcol = top.cubiecols[0];
        Cubie temp2 = top.cubies[1];
        String temp2col = top.cubiecols[1];
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

        // changing the orientation of front and right faces accordingly
        temp = around1.cubies[index1];
        tempcol = around1.cubiecols[index1];
        temp2 = around1.cubies[index2];
        temp2col = around1.cubiecols[index2];
        around1.cubies[index1] = around2.cubies[index1];
        around1.cubiecols[index1] = around2.cubiecols[index1];
        around1.cubies[index2] = around2.cubies[index2];
        around1.cubiecols[index2] = around2.cubiecols[index2];

        Cubie temp3 = around3.cubies[index1];
        String temp3col = around3.cubiecols[index1];
        Cubie temp4 = around3.cubies[index2];
        String temp4col = around3.cubiecols[index2];
        around3.cubies[index1] = temp;
        around3.cubiecols[index1] = tempcol;
        around3.cubies[index2] = temp2;
        around3.cubiecols[index2] = temp2col;

        temp = around4.cubies[index1];
        tempcol = around4.cubiecols[index1];
        temp2 = around4.cubies[index2];
        temp2col = around4.cubiecols[index2];
        around4.cubies[index1] = temp3;
        around4.cubiecols[index1] = temp3col;
        around4.cubies[index2] = temp4;
        around4.cubiecols[index2] = temp4col;

        around2.cubies[index1] = temp;
        around2.cubiecols[index1] = tempcol;
        around2.cubies[index2] = temp2;
        around2.cubiecols[index2] = temp2col;
    }


    // Given a character in ['u', 'U', 'r', 'R', 'f', 'F'], return a new rubik's cube with the rotation applied
    // Do not modify this rubik's cube.
    public RubiksCube rotate(char c) {
        // TODO
        if (c == 'u'){
            rotateAround(u0, f0, r0, r1, f1, 0, 1);
        }
        else if (c == 'U'){

        }

        return this;
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

    public static void main(String[] args){
        RubiksCube mycube = new RubiksCube();
        mycube.printCube();
        mycube.rotate('u');
        mycube.printCube();
    }

}



