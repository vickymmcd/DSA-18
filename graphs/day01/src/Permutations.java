import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Permutations {

    public static List<List<Integer>> permutations(List<Integer> A) {
        List<List<Integer>> permutations = new LinkedList<>();
        Set<Integer> mySet = new HashSet<>(A);
        LinkedList<Integer> list = new LinkedList<>();
        backtrack(list, mySet, permutations);
        return permutations;
    }

}
