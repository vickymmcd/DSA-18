import java.util.Arrays;

public class LocksAndKeys {

    private static void swap(char[] A, int i, int j) {
        char t = A[i];
        A[i] = A[j];
        A[j] = t;
    }
    static char[][] locksAndKeys(char[] locks, char[] keys) {
        // Returns a 2d char array, where result[0] is the sorted locks, and result[1] is the sorted keys
        char[][] result = new char[2][];
        Arrays.sort(locks);
        Arrays.sort(keys);
        result[0] = locks;
        result[1] = keys;
        return result;
    }
}




